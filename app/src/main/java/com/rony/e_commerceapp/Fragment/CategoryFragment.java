package com.rony.e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.AllCategoriesAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.databinding.FragmentCategoryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {


    View view;
    FragmentCategoryBinding binding;
    CategoryResponse categoryResponse;
    AllCategoriesAdapter allCategoriesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentCategoryBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        binding.allCategoriesRecyclerView.setHasFixedSize(true);
        binding.allCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        RetrofitClient.getRetrofitClient(getContext()).getCategories().enqueue(new Callback<CategoryResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    categoryResponse = response.body();
                    allCategoriesAdapter = new AllCategoriesAdapter(getContext(), categoryResponse);
                    binding.allCategoriesRecyclerView.setAdapter(allCategoriesAdapter);

                    allCategoriesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });

        return  view;

    }
}