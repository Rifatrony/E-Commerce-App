package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.API.RetrofitClientWithoutHeader;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.RegistrationResponse;
import com.rony.e_commerceapp.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    String name, email, number, password, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.haveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

    }

    private void checkValidation() {
        name = binding.nameEditText.getText().toString().trim();
        email = binding.emailEditText.getText().toString().trim();
        number = binding.numberEditText.getText().toString().trim();
        password = binding.passwordEditText.getText().toString().trim();
        confirm_password = binding.confirmPasswordEditText.getText().toString().trim();


        if (name.isEmpty()){
            showToast("Enter Name");
            return;
        }

        if (email.isEmpty()){
            showToast("Enter Email");
            return;
        }

        if (number.isEmpty()){
            showToast("Enter Number");
            return;
        }

        if (password.isEmpty()){
            showToast("Enter Password");
            return;
        }

        if (confirm_password.isEmpty()){
            showToast("Confirm Password");
            return;
        }

        if (!password.equals(confirm_password)){
            showToast("Password and confirm password should be same");
            return;
        }
        else {
            SignUpNewUser();
        }

    }

    private void SignUpNewUser() {
        showToast("Clicked");
        RetrofitClient.getRetrofitClient(this).sendUserData(name,email, number, password, confirm_password, "", "").enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()){

                    showToast("OTP send to your phone");

                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", number);
                    intent.putExtra("password", password);
                    intent.putExtra("confirmPassword", confirm_password);
                    intent.putExtra("device_name", "redmi");
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}