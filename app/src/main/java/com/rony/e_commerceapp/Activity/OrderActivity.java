package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.OrderAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.OrderResponse;
import com.rony.e_commerceapp.databinding.ActivityOrderBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    OrderAdapter orderAdapter;
    OrderResponse orderResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.orderRecyclerView.setHasFixedSize(true);
        binding.orderRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RetrofitClient.getRetrofitClient(this).getOrder().enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()){
                    orderResponse = response.body();
                    assert orderResponse != null;
                    if (orderResponse.data.size()>0){
                        binding.constraintLayout.setVisibility(View.VISIBLE);
                        orderAdapter = new OrderAdapter(getApplicationContext(), orderResponse);
                        binding.orderRecyclerView.setAdapter(orderAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });

    }
}