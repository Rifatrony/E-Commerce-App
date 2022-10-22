package com.rony.e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.rony.e_commerceapp.Adapter.CategoryAdapter;
import com.rony.e_commerceapp.Adapter.SliderAdapter;
import com.rony.e_commerceapp.Adapter.TopSellingAdapter;
import com.rony.e_commerceapp.Fragment.FavouriteFragment;
import com.rony.e_commerceapp.Fragment.HomeFragment;
import com.rony.e_commerceapp.Fragment.ProfileFragment;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.Response.SliderResponse;
import com.rony.e_commerceapp.Response.TopSellingResponse;
import com.rony.e_commerceapp.databinding.ActivityMainBinding;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        frameLayout = findViewById(R.id.frameLayout);

        replaceFragment(new HomeFragment());

        binding.menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.END);


                switch (id){
                    case R.id.nav_profile:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_categories:
                        Toast.makeText(MainActivity.this, "AllCategoryActivity", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        break;

                    case R.id.nav_order:
                        Toast.makeText(MainActivity.this, "OrderActivity", Toast.LENGTH_SHORT).show();
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

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.favorite:
                        replaceFragment(new FavouriteFragment());
                        break;

                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;

                    case R.id.menu:
                        drawerLayout.openDrawer(GravityCompat.END);
                        break;

                }

                return true;
            }
        });

        binding.cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });

    }

    private void Logout() {
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
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }
}