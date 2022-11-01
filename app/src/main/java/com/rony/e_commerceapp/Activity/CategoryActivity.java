package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

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

        binding.shimmerLayout.startShimmer();

        binding.allCategoriesRecyclerView.setHasFixedSize(true);
        binding.allCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RetrofitClient.getRetrofitClient(this).getCategories().enqueue(new Callback<CategoryResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){

                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.allCategoriesRecyclerView.setVisibility(View.VISIBLE);

                    categoryResponse = response.body();
                    allCategoriesAdapter = new AllCategoriesAdapter(getApplicationContext(), categoryResponse);
                    binding.allCategoriesRecyclerView.setAdapter(allCategoriesAdapter);

                    allCategoriesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });

    }
}