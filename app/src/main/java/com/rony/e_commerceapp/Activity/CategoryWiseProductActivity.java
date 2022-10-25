package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.CategoryWiseAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CommonApiResponse;
import com.rony.e_commerceapp.databinding.ActivityCategoryWiseProductBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryWiseProductActivity extends AppCompatActivity {

    ActivityCategoryWiseProductBinding binding;

    String slug, name;
    public int page = 1;

    CommonApiResponse commonApiResponse;
    RecyclerView categoriesRecyclerView;
    CategoryWiseAdapter categoryWiseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryWiseProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.shimmerLayout.startShimmer();

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
        RetrofitClient.getRetrofitClient(this).getCategoryWiseProduct(slug, page).enqueue(new Callback<CommonApiResponse>() {
            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                if (response.isSuccessful()){

                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.nestedScrollView.setVisibility(View.VISIBLE);

                    commonApiResponse = response.body();

                    System.out.println("current page is ----- >" + commonApiResponse.products.pagination.current_page);

                    binding.pageNumberTextView.setText(commonApiResponse.products.pagination.current_page
                            + " of " + commonApiResponse.products.pagination.total_pages);
                    System.out.println("Total page is : " + commonApiResponse.products.pagination.total_pages);


                    /*categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), topSellingResponse);
                    categoriesRecyclerView.setAdapter(categoryWiseAdapter);*/
                    binding.paginationLayout.setVisibility(View.VISIBLE);


                    if (commonApiResponse.products.pagination.current_page == page){
                        categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), commonApiResponse);
                        categoriesRecyclerView.setAdapter(categoryWiseAdapter);
                        categoryWiseAdapter.notifyDataSetChanged();
                        binding.previous.setEnabled(true);
                        binding.previous.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    }

                    if (commonApiResponse.products.pagination.current_page == 1){
                        System.out.println("This is the first page");
                        binding.previous.setEnabled(false);
                    }

                    if (commonApiResponse.products.pagination.total_pages > 1){
                        System.out.println(String.valueOf(page + 1) + " is the next page");
                        categoryWiseAdapter = new CategoryWiseAdapter(getApplicationContext(), commonApiResponse);
                        categoriesRecyclerView.setAdapter(categoryWiseAdapter);
                        categoryWiseAdapter.notifyDataSetChanged();
                        binding.next.setEnabled(true);
                    }

                    if (commonApiResponse.products.pagination.total_pages == commonApiResponse.products.pagination.current_page){
                        System.out.println("This is the last page");
                        binding.next.setEnabled(false);
                    }


                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {

            }
        });

    }
}