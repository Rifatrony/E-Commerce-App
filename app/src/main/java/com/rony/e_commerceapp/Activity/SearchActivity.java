package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.AllProductAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CommonApiResponse;
import com.rony.e_commerceapp.Services.NetworkMonitor;
import com.rony.e_commerceapp.databinding.ActivitySearchBinding;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;

    String searchString, searchText, text;
    CommonApiResponse commonApiResponse;
    AllProductAdapter allProductAdapter;

    BroadcastReceiver broadcastReceiver = null;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new Dialog(SearchActivity.this);
        dialog.setContentView(R.layout.connection_layout);

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        broadcastReceiver = new NetworkMonitor();
        InternetStatus();

        if (checkNetworkConnection()){
            Toast.makeText(this, "Connection come back", Toast.LENGTH_SHORT).show();
            //dialog.dismiss();
        }
        else {

            //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            //dialog.show();
        }

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchString = binding.searchEditText.getText().toString().trim();

                if (!searchString.isEmpty()){
                    searchProduct(searchString);
                }
                else {
                    Toast.makeText(SearchActivity.this, "Write product name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void InternetStatus(){
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    private void searchProduct(String searchString){

        searchText = searchString;

        RetrofitClient.getRetrofitClient(this).searchProduct(searchText).enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                if (response.isSuccessful()){
                    commonApiResponse = response.body();
                    Toast.makeText(SearchActivity.this, "List size is : " + commonApiResponse.products.data.size(), Toast.LENGTH_SHORT).show();

                    binding.searchRecyclerView.setVisibility(View.VISIBLE);
                    binding.searchRecyclerView.setHasFixedSize(true);
                    binding.searchRecyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 3));
                    allProductAdapter = new AllProductAdapter(SearchActivity.this, commonApiResponse);
                    binding.searchRecyclerView.setAdapter(allProductAdapter);

                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {

            }
        });
    }

}