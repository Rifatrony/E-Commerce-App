package com.rony.e_commerceapp.Services;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.rony.e_commerceapp.Activity.MainActivity;
import com.rony.e_commerceapp.R;

public class NetworkMonitor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Dialog dialog =new Dialog(context);
        dialog.setContentView(R.layout.connection_layout);

        if (!checkNetworkConnection(context)){
            //dialog.show();
            Toast.makeText(context, "Network is not connected", Toast.LENGTH_SHORT).show();
        }
        else if (checkNetworkConnection(context)){
            //dialog.dismiss();
            Toast.makeText(context, "Network is connected", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkNetworkConnection(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());

    }
}
