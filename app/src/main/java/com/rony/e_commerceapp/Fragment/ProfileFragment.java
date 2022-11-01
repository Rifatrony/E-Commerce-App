package com.rony.e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Activity.LoginActivity;
import com.rony.e_commerceapp.Activity.PrivacyPolicyActivity;
import com.rony.e_commerceapp.Activity.ProfileActivity;
import com.rony.e_commerceapp.Activity.ReturnPolicyActivity;
import com.rony.e_commerceapp.Activity.TermsActivity;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.SuccessResponse;
import com.rony.e_commerceapp.Response.TokenInvokedResponse;
import com.rony.e_commerceapp.Response.UserDetailsResponse;
import com.rony.e_commerceapp.Session.SessionManagement;
import com.rony.e_commerceapp.databinding.FragmentProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    View view;
    FragmentProfileBinding binding;

    UserDetailsResponse userDetailsResponse;
    Dialog dialog, changePasswordDialog;

    AppCompatButton updateProfileButton, changePasswordButton;
    EditText nameEditText, emailEditText, numberEditText, addressEditText;

    EditText oldPasswordEditText, newPasswordEditText, confirmNewPasswordEditText;
    String oldPass, newPass, confirmPass;

    public String name, email, number, address;

    SessionManagement sessionManagement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        binding.privacyPolicyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PrivacyPolicyActivity.class));
            }
        });

        binding.returnPolicyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReturnPolicyActivity.class));
            }
        });


        binding.termsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TermsActivity.class));
            }
        });

        binding.myAccountCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.changePasswordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword();
            }
        });

        binding.orderHistoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.shimmerFrameLayout.startShimmer();

        sessionManagement = new SessionManagement(getContext());

        fetchUserDetails();

        binding.editImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.user_details_layout);
                dialog.show();

                updateProfileButton = dialog.findViewById(R.id.placeOrderButton);
                nameEditText = dialog.findViewById(R.id.nameEditText);
                emailEditText = dialog.findViewById(R.id.emailEditText);
                numberEditText = dialog.findViewById(R.id.numberEditText);
                addressEditText = dialog.findViewById(R.id.addressEditText);
                updateProfileButton.setText("Update Profile");

                addressEditText.setLines(3);

                RetrofitClient.getRetrofitClient(getContext()).getUserDetails().enqueue(new Callback<UserDetailsResponse>() {
                    @Override
                    public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                        if (response.isSuccessful()){

                            userDetailsResponse = response.body();
                            assert userDetailsResponse != null;
                            name = userDetailsResponse.user.name;
                            email = userDetailsResponse.user.email;
                            number = userDetailsResponse.user.phone;
                            address = userDetailsResponse.user.address;

                            nameEditText.setText(name);
                            emailEditText.setText(email);
                            numberEditText.setText(number);
                            addressEditText.setText(address);

                            updateProfileButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String updateName, updateEmail, updateNumber, updateAddress;

                                    updateName = nameEditText.getText().toString().trim();
                                    updateEmail = emailEditText.getText().toString().trim();
                                    updateNumber = numberEditText.getText().toString().trim();
                                    updateAddress = addressEditText.getText().toString().trim();

                                    if (updateName.isEmpty()){
                                        showToast("Name Required");
                                        return;
                                    }

                                    if (updateEmail.isEmpty()){
                                        showToast("Email Required");
                                        return;
                                    }

                                    if (updateNumber.isEmpty()){
                                        showToast("Number Required");
                                        return;
                                    }
                                    if (updateAddress.isEmpty()){
                                        showToast("Address Required");
                                        return;
                                    }

                                    else{
                                        UpdateUser(updateName, updateEmail, updateNumber, updateAddress);
                                    }
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailsResponse> call, Throwable t) {

                    }
                });

            }
        });


        return view;

    }

    private void UpdateUser(String updateName, String updateEmail, String updateNumber, String updateAddress) {

        String userName, userEmail, userNumber, userAddress;
        userName = updateName;
        userEmail = updateEmail;
        userNumber = updateNumber;
        userAddress = updateAddress;

        RetrofitClient.getRetrofitClient(getContext()).updateProfile(userName, userEmail, userNumber, userAddress).enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                if (response.isSuccessful()){
                    showToast("Profile Updated Successfully");
                    dialog.dismiss();
                    fetchUserDetails();
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

            }
        });
    }

    private void fetchUserDetails() {
        RetrofitClient.getRetrofitClient(getContext()).getUserDetails().enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                if (response.isSuccessful()){

                    binding.shimmerFrameLayout.stopShimmer();
                    binding.shimmerFrameLayout.setVisibility(View.GONE);
                    binding.scrollView.setVisibility(View.VISIBLE);

                    userDetailsResponse = response.body();
                    assert userDetailsResponse != null;
                    binding.nameTextView.setText(userDetailsResponse.user.name);
                    binding.emailTextView.setText(userDetailsResponse.user.email);
                    binding.numberTextView.setText(userDetailsResponse.user.phone);

                    try {
                        if (!userDetailsResponse.user.address.isEmpty()){
                            binding.addressTextView.setVisibility(View.VISIBLE);
                            binding.addressTextView.setText(userDetailsResponse.user.address);
                        }
                    }catch (Exception e){

                    }


                }
            }

            @Override
            public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChangePassword() {
        changePasswordDialog = new Dialog(getContext());
        changePasswordDialog.setContentView(R.layout.change_password_layout);
        changePasswordDialog.show();

        changePasswordButton = changePasswordDialog.findViewById(R.id.changePasswordButton);

        oldPasswordEditText = changePasswordDialog.findViewById(R.id.oldPasswordEditText);
        newPasswordEditText = changePasswordDialog.findViewById(R.id.newPasswordEditText);
        confirmNewPasswordEditText = changePasswordDialog.findViewById(R.id.confirmNewPasswordEditText);

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPass = oldPasswordEditText.getText().toString().trim();
                newPass = newPasswordEditText.getText().toString().trim();
                confirmPass = confirmNewPasswordEditText.getText().toString().trim();

                if (oldPass.isEmpty()){
                    showToast("Write old password");
                    return;
                }
                if (newPass.isEmpty()){
                    showToast("Write new password");
                    return;
                }
                if (confirmPass.isEmpty()){
                    showToast("Confirm password");
                    return;
                }

                if (oldPass.equals(newPass)){
                    showToast("Old password and new password can't be same");
                    return;
                }

                if (!newPass.equals(confirmPass)){
                    showToast("New password and confirm password should be same");
                    return;
                }

                else {
                    UpdatePassword(oldPass, newPass, confirmPass);
                }
            }
        });
    }
    private void UpdatePassword(String oldPass, String newPass, String confirmPass) {

        String userOldPass, userNewPass, userConfirmPass;
        userOldPass = oldPass;
        userNewPass = newPass;
        userConfirmPass = confirmPass;

        RetrofitClient.getRetrofitClient(getContext()).changePassword(userOldPass, userNewPass, userConfirmPass).enqueue(new Callback<TokenInvokedResponse>() {
            @Override
            public void onResponse(Call<TokenInvokedResponse> call, Response<TokenInvokedResponse> response) {
                if (response.isSuccessful()){
                    showToast("Password Updated");
                    changePasswordDialog.dismiss();

                    sessionManagement.removeLoginSession();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    //finish();

                }
            }

            @Override
            public void onFailure(Call<TokenInvokedResponse> call, Throwable t) {

                showToast(t.getMessage());
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}