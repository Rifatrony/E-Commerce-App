package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}