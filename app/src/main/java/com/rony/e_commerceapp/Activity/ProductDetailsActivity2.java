package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.RelatedProductAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.ProductDetailsResponse;
import com.rony.e_commerceapp.Response.TopSellingResponse;
import com.rony.e_commerceapp.databinding.ActivityProductDetails2Binding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity2 extends AppCompatActivity {

    ActivityProductDetails2Binding binding;

    ShimmerFrameLayout shimmerFrameLayout;

    ProductDetailsResponse productDetailsResponse;
    String slug, category;

    int count = 1;

    TopSellingResponse topSellingResponse;
    RecyclerView relatedProductRecyclerView;
    RelatedProductAdapter relatedProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetails2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        slug = getIntent().getStringExtra("slug");

        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fetchRelatedProduct();

        RetrofitClient.getRetrofitClient(this).getProductDetails(slug).enqueue(new Callback<ProductDetailsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                if (response.isSuccessful()){
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                    binding.mainLayout.setVisibility(View.VISIBLE);

                    productDetailsResponse = response.body();
                    Glide.with(getApplicationContext()).load(productDetailsResponse.thumbnail).into(binding.sliderImageView);
                    Glide.with(getApplicationContext()).load(productDetailsResponse.brand.image).into(binding.brandImage);
                    binding.productNameTextView.setText(productDetailsResponse.name);
                    binding.productPriceTextView.setText(String.valueOf(productDetailsResponse.final_price) + " Tk.");
                    binding.productDiscountPriceTextView.setText(productDetailsResponse.price + " Tk.");
                    binding.productDiscountPriceTextView.setPaintFlags(binding.productDiscountPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    binding.categoryNameTextView.setText(productDetailsResponse.category.name);

                    binding.amountTextView.setText(String.valueOf(count));

                    category = productDetailsResponse.category.name;

                    System.out.println("Product Name "+productDetailsResponse.name);

                    RetrofitClient.getRetrofitClient(getApplicationContext()).getRelatedProduct(category).enqueue(new Callback<TopSellingResponse>() {
                        @Override
                        public void onResponse(Call<TopSellingResponse> call, Response<TopSellingResponse> response) {
                            if (response.isSuccessful()){
                                topSellingResponse = response.body();
                                relatedProductAdapter = new RelatedProductAdapter(getApplicationContext(), topSellingResponse);
                                relatedProductRecyclerView.setAdapter(relatedProductAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<TopSellingResponse> call, Throwable t) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {

            }
        });

        binding.increaseCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                binding.amountTextView.setText(String.valueOf(count));
            }
        });

        binding.decreaseCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 1){
                    return;
                }else {
                    count--;
                    binding.amountTextView.setText(String.valueOf(count));
                }

            }
        });


    }

    private void fetchRelatedProduct() {
        relatedProductRecyclerView = findViewById(R.id.relatedProductRecyclerView);
        relatedProductRecyclerView.setHasFixedSize(true);
        relatedProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



    }
}