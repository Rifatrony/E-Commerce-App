package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.AllCategoriesAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.databinding.ActivityCategoryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;
    AllCategoriesAdapter allCategoriesAdapter;
    CategoryResponse categoryResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.allCategoriesRecyclerView.setHasFixedSize(true);
        binding.allCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        RetrofitClient.getRetrofitClient(this).getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    categoryResponse = response.body();
                    allCategoriesAdapter = new AllCategoriesAdapter(getApplicationContext(), categoryResponse);
                    binding.allCategoriesRecyclerView.setAdapter(allCategoriesAdapter);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });

    }
}