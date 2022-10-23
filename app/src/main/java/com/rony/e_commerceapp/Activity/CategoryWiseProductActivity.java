package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
    public int page = 1;

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

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                fetchCategoryWiseProduct();
            }
        });

        binding.previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page--;
                fetchCategoryWiseProduct();
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

        fetchCategoryWiseProduct();

    }

    private void fetchCategoryWiseProduct() {
        RetrofitClient.getRetrofitClient(this).getCategoryWiseProduct(slug, page).enqueue(new Callback<TopSellingResponse>() {
            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onResponse(Call<TopSellingResponse> call, Response<TopSellingResponse> response) {
                if (response.isSuccessful()){
                    topSellingResponse = response.body();

                    System.out.println("current page is ----- >" + topSellingResponse.products.pagination.current_page);

                    binding.pageNumberTextView.setText(topSellingResponse.products.pagination.current_page
                            + " of " + topSellingResponse.products.pagination.total_pages);
                    System.out.println("Total page is : " + topSellingResponse.products.pagination.total_pages);


                    /*categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), topSellingResponse);
                    categoriesRecyclerView.setAdapter(categoryWiseAdapter);*/
                    binding.paginationLayout.setVisibility(View.VISIBLE);


                    if (topSellingResponse.products.pagination.current_page == page){
                        categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), topSellingResponse);
                        categoriesRecyclerView.setAdapter(categoryWiseAdapter);
                        categoryWiseAdapter.notifyDataSetChanged();
                        binding.previous.setEnabled(true);
                        binding.previous.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    }

                    if (topSellingResponse.products.pagination.current_page == 1){
                        System.out.println("This is the first page");
                        binding.previous.setEnabled(false);
                    }

                    if (topSellingResponse.products.pagination.total_pages > 1){
                        System.out.println(String.valueOf(page + 1) + " is the next page");
                        categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), topSellingResponse);
                        categoriesRecyclerView.setAdapter(categoryWiseAdapter);
                        categoryWiseAdapter.notifyDataSetChanged();
                        binding.next.setEnabled(true);
                    }

                    if (topSellingResponse.products.pagination.total_pages == topSellingResponse.products.pagination.current_page){
                        System.out.println("This is the last page");
                        binding.next.setEnabled(false);
                    }


                }
            }

            @Override
            public void onFailure(Call<TopSellingResponse> call, Throwable t) {

            }
        });

    }
}