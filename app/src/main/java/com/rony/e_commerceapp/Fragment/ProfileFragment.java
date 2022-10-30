package com.rony.e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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



        return view;

    }
}