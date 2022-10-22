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
    String name, email, phone, password, confirm_password, device_name = "redmi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });


    }

    private void checkValidation() {
        name = binding.nameEditText.getText().toString();
        email = binding.emailEditText.getText().toString();
        phone = binding.numberEditText.getText().toString();
        password = binding.passwordEditText.getText().toString();
        confirm_password = binding.confirmPasswordEditText.getText().toString();

        if (name.isEmpty()){
            showToast("Name Required");
            return;
        }

        if (email.isEmpty()){
            showToast("Email Required");
            return;
        }

        if (phone.isEmpty()){
            showToast("Number Required");
            return;
        }

        if (password.isEmpty()){
            showToast("Password Required");
            return;
        }

        if (password.length()<8){
            showToast("Minimum password is 8");
            return;
        }

        if (confirm_password.isEmpty()){
            showToast("Confirm Password");
            return;
        }
        if (!password.equals(confirm_password)){
            showToast("Password and Confirm Password Should be Same");
            return;
        }

        else {
            SignUp(name, email, phone, password, confirm_password, device_name);


        }


    }

    private void SignUp(String name, String email, String phone, String password, String confirm_password, String device_name) {

        System.out.println(name);
        System.out.println(email);
        System.out.println(phone);
        System.out.println(password);
        System.out.println(confirm_password);

        RetrofitClientWithoutHeader.getRetrofitClient().sendUserData(name, email, phone, password, confirm_password, device_name, "" ).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()){

                    showToast("OTP send to your phone");

                    Intent intent = new Intent(SignUpActivity.this, OtpActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", phone);
                    intent.putExtra("password", password);
                    intent.putExtra("confirm_password", confirm_password);
                    intent.putExtra("device_name", device_name);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                showToast(t.getMessage());
                System.out.println(t.getMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}