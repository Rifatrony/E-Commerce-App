package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.SessionDataModel;
import com.rony.e_commerceapp.Response.UserRegisterResponse;
import com.rony.e_commerceapp.Session.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    PinView pinView;

    String name, email, phone, password, confirmPassword, device_name;

    String MyOtp;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        confirmPassword = getIntent().getStringExtra("confirmPassword");
        device_name = getIntent().getStringExtra("device_name");

        System.out.println("\n\nName is " + name);
        System.out.println("email is " + email);
        System.out.println("phone is " + phone);
        System.out.println("password is " + password);
        System.out.println("confirmPassword is " + confirmPassword);
        System.out.println("device_name is " + device_name);

        sessionManagement = new SessionManagement(this);

        pinView = findViewById(R.id.pinView);
        pinView.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 8){

                    MyOtp = charSequence.toString();
                    CreateNewUser(MyOtp);

                    System.out.println(MyOtp);
                    Toast.makeText(OtpActivity.this, MyOtp, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void CreateNewUser(String otp){

        RetrofitClient.getRetrofitClient(this).createUser(name, email, phone, password, confirmPassword, device_name, otp).enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if (response.isSuccessful()){
                    showToast("Otp Verified and Account Created Successfully...");

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    SessionDataModel dataModel = new SessionDataModel(response.body().getAccess_token(), email, password);
                    sessionManagement.setLoginSession(dataModel);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}