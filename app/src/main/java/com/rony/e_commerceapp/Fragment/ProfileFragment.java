package com.rony.e_commerceapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rony.e_commerceapp.Activity.PrivacyPolicyActivity;
import com.rony.e_commerceapp.Activity.ReturnPolicyActivity;
import com.rony.e_commerceapp.Activity.TermsActivity;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    View view;
    FragmentProfileBinding binding;

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


        return view;

    }
}