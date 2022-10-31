package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.databinding.ActivityPrivacyPolicyBinding;

public class PrivacyPolicyActivity extends AppCompatActivity {

    ActivityPrivacyPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.textView.setText(getString(R.string.privacy1));
        binding.textView1.setText(getString(R.string.privacy_question_1));
        binding.textView2.setText(getString(R.string.privacy2));
        binding.textView3.setText(getString(R.string.privacy_question_2));
        binding.textView4.setText(getString(R.string.privacy3));
        binding.textView5.setText(getString(R.string.privacy_question_3));
        binding.textView6.setText(getString(R.string.privacy4));
    }
}