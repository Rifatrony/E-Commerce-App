package com.rony.e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Fragment.AllProductFragment;
import com.rony.e_commerceapp.Fragment.CategoryFragment;
import com.rony.e_commerceapp.Fragment.OrderFragment;
import com.rony.e_commerceapp.Fragment.HomeFragment;
import com.rony.e_commerceapp.Fragment.ProfileFragment;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CartResponse;
import com.rony.e_commerceapp.Response.UserDetailsResponse;
import com.rony.e_commerceapp.Services.NetworkMonitor;
import com.rony.e_commerceapp.Session.SessionManagement;
import com.rony.e_commerceapp.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView cartSizeTextView;
    CartResponse cartResponse;

    FrameLayout frameLayout;
    UserDetailsResponse userDetailsResponse;
    SessionManagement sessionManagement;

    BroadcastReceiver broadcastReceiver = null;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        frameLayout = findViewById(R.id.frameLayout);
        cartSizeTextView = findViewById(R.id.cartSizeTextView);

        dialog = new Dialog(MainActivity.this);

        broadcastReceiver = new NetworkMonitor();
        InternetStatus();

        /*if (checkNetworkConnection()){
            Toast.makeText(this, "Connected to the Network", Toast.LENGTH_SHORT).show();
            //dialog.dismiss();
        }
        else {
            *//*dialog.setContentView(R.layout.connection_layout);
            dialog.show();*//*
        }*/

        sessionManagement = new SessionManagement(this);

        replaceFragment(new HomeFragment());

        binding.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment());
                binding.bottomNavigationView.setSelectedItemId(R.id.home);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);


                switch (id){
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        break;

                    case R.id.nav_categories:
                        startActivity(new Intent(getApplicationContext(), CategoryActivity.class)
                        );
                        break;

                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        break;

                    case R.id.nav_order:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        break;

                    case R.id.nav_logout:
                        Logout();
                        break;

                    case R.id.nav_share:

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Check this application");
                        intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.binaryit.faruqtraders");
                        startActivity(Intent.createChooser(intent,"Share Via"));
                        break;

                    case R.id.nav_contact:
                        Toast.makeText(MainActivity.this, "ContactUsActivity", Toast.LENGTH_SHORT).show();
                        //overridePendingTransition(0,0);
                        break;

                    default:
                        return true;
                }

                return true;
            }
        });

        navigationView.setItemIconTintList(null);

        binding.bottomNavigationView.setSelectedItemId(R.id.home);

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.category:
                        replaceFragment(new CategoryFragment());
                        break;

                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.order:
                        replaceFragment(new OrderFragment());
                        break;

                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;

                    case R.id.all_product:
                        replaceFragment(new AllProductFragment());
                        //drawerLayout.openDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });

        binding.bottomNavigationView.setItemIconTintList(null);

        binding.cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

        fetchUserDetails();

        countCartItem();

    }

    private void fetchUserDetails() {
        RetrofitClient.getRetrofitClient(this).getUserDetails().enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                if (response.isSuccessful()){
                    userDetailsResponse = response.body();

                    NavigationView navigationView = findViewById(R.id.navigation_view);
                    View headerView = navigationView.getHeaderView(0);

                    TextView navUserName = headerView.findViewById(R.id.user_name);
                    TextView navUserEmail = headerView.findViewById(R.id.user_email);
                    TextView navUserNumber = headerView.findViewById(R.id.user_phone);

                    try {
                        navUserName.setText(userDetailsResponse.user.name);
                        navUserEmail.setText(userDetailsResponse.user.email);
                        navUserNumber.setText(userDetailsResponse.user.phone);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserDetailsResponse> call, Throwable t) {

            }
        });
    }

    private void Logout() {

        sessionManagement.removeLoginSession();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (binding.bottomNavigationView.getSelectedItemId() == R.id.home){
            super.onBackPressed();
            finish();
        }
        else {
            binding.bottomNavigationView.setSelectedItemId(R.id.home);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void countCartItem(){
        RetrofitClient.getRetrofitClient(this).getCartItem().enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()){
                    cartResponse = response.body();
                    cartSizeTextView.setText(String.valueOf(cartResponse.data.size()));
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Here "+ t.getMessage(), Toast.LENGTH_SHORT).show();
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

    /*@Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }*/
}