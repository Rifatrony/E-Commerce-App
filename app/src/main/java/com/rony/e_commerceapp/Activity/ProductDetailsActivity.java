package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.databinding.ActivityProductDetailsBinding;

public class ProductDetailsActivity extends AppCompatActivity {

    ActivityProductDetailsBinding binding;
    String name, thumbnail, discount, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*getWindow().getDecorView().getWindowInsetsController().hide(
                android.view.WindowInsets.Type.statusBars()
        );*/

        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = getIntent().getStringExtra("name");
        thumbnail = getIntent().getStringExtra("thumbnail");
        discount = getIntent().getStringExtra("discount");
        price = getIntent().getStringExtra("price");
        System.out.println(name);
        System.out.println(price);
        System.out.println(discount);
        binding.collapsingToolbar.setTitle(name);
        binding.productNameTextView.setText(name);
        binding.productPriceTextView.setText(price);
        //Glide.with(this).load()



    }
}