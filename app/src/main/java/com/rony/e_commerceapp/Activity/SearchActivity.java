package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Services.NetworkMonitor;
import com.rony.e_commerceapp.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;

    BroadcastReceiver broadcastReceiver = null;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new Dialog(SearchActivity.this);
        dialog.setContentView(R.layout.connection_layout);

        broadcastReceiver = new NetworkMonitor();
        InternetStatus();

        if (checkNetworkConnection()){
            Toast.makeText(this, "Connected to the Network", Toast.LENGTH_SHORT).show();
            //dialog.dismiss();
        }
        else {

            dialog.show();
        }

    }

    public void InternetStatus(){
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}