package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.databinding.ActivityReturnPolicyBinding;

public class ReturnPolicyActivity extends AppCompatActivity {

    ActivityReturnPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReturnPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}