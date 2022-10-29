package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.UserDetailsResponse;
import com.rony.e_commerceapp.databinding.ActivityProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    UserDetailsResponse userDetailsResponse;
    Dialog dialog;

    AppCompatButton updateProfileButton;
    EditText nameEditText, emailEditText, numberEditText, addressEditText;

    String name, email, number, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RetrofitClient.getRetrofitClient(this).getUserDetails().enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                if (response.isSuccessful()){
                    userDetailsResponse = response.body();
                    assert userDetailsResponse != null;
                    binding.nameTextView.setText(userDetailsResponse.user.name);
                    binding.emailTextView.setText(userDetailsResponse.user.email);
                    binding.numberTextView.setText(userDetailsResponse.user.phone);
                }
            }

            @Override
            public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.editImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog = new Dialog(ProfileActivity.this);
                dialog.setContentView(R.layout.user_details_layout);
                dialog.show();

                updateProfileButton = dialog.findViewById(R.id.placeOrderButton);
                nameEditText = dialog.findViewById(R.id.nameEditText);
                emailEditText = dialog.findViewById(R.id.emailEditText);
                numberEditText = dialog.findViewById(R.id.numberEditText);
                addressEditText = dialog.findViewById(R.id.addressEditText);


                updateProfileButton.setText("Update Profile");

                addressEditText.setLines(3);

                updateProfileButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        name = nameEditText.getText().toString().trim();
                        email = emailEditText.getText().toString().trim();
                        number = numberEditText.getText().toString().trim();
                        address = addressEditText.getText().toString().trim();
                    }
                });


            }
        });

    }
}