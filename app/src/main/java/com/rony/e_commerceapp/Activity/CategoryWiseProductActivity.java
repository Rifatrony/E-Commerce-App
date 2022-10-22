package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.CategoryAdapter;
import com.rony.e_commerceapp.Adapter.CategoryWiseAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.Response.TopSellingResponse;
import com.rony.e_commerceapp.databinding.ActivityCategoryWiseProductBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryWiseProductActivity extends AppCompatActivity {

    ActivityCategoryWiseProductBinding binding;

    String slug, name;

    TopSellingResponse topSellingResponse;
    RecyclerView categoriesRecyclerView;
    CategoryWiseAdapter categoryWiseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryWiseProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        slug = getIntent().getStringExtra("slug");
        name = getIntent().getStringExtra("name");
        System.out.println(slug);

        binding.titleTextView.setText(name);

        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        categoriesRecyclerView.setHasFixedSize(true);

      /*  RetrofitClient.getRetrofitClient(this).getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });*/

        RetrofitClient.getRetrofitClient(this).getCategoryWiseProduct(slug, 1).enqueue(new Callback<TopSellingResponse>() {
            @Override
            public void onResponse(Call<TopSellingResponse> call, Response<TopSellingResponse> response) {
                if (response.isSuccessful()){
                    topSellingResponse = response.body();
                    categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), topSellingResponse);
                    categoriesRecyclerView.setAdapter(categoryWiseAdapter);
                    binding.paginationLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TopSellingResponse> call, Throwable t) {

            }
        });

    }
}