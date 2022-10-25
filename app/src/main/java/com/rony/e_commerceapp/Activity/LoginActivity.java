package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.API.RetrofitClientWithoutHeader;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.SessionDataModel;
import com.rony.e_commerceapp.Response.UserRegisterResponse;
import com.rony.e_commerceapp.Session.SessionManagement;
import com.rony.e_commerceapp.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    SessionManagement sessionManagement;

    String loginNumber, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManagement = new SessionManagement(this);


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        binding.noAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

    private void userLogin() {

        loginNumber = binding.numberEditText.getText().toString().trim();
        loginPassword = binding.passwordEditText.getText().toString().trim();

        if (loginNumber.isEmpty()) {
            showToast("Enter Number");
            return;
        }
        else if (loginPassword.isEmpty()) {
            showToast("Enter Password");
            return;

        } else if (loginPassword.length() < 8) {
            showToast("Minimum Password is 8");
            return;
        } else {
            Login();

        }
    }


    private void Login() {

        RetrofitClientWithoutHeader.getRetrofitClient().userLogin(loginNumber, loginPassword, "redmi").enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if (response.body() != null){
                    /*signInButton.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);*/
                    showToast("Login Successfully");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    SessionDataModel dataModel = new SessionDataModel(response.body().getAccess_token(), loginNumber, loginPassword);
                    sessionManagement.setLoginSession(dataModel);
                    finish();

                }
                else {
                    /*signInButton.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);*/
                    showToast("Error "+response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                showToast("Failure .........."+t.getLocalizedMessage());

                /*signInButton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);*/
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {

        if (!sessionManagement.getSessionModel().getUserPhone().equals("null")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        super.onStart();

    }
}