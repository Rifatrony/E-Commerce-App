package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.RelatedProductAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.ProductDetailsResponse;
import com.rony.e_commerceapp.Response.CommonApiResponse;
import com.rony.e_commerceapp.databinding.ActivityProductDetailsBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    ActivityProductDetailsBinding binding;

    ShimmerFrameLayout shimmerFrameLayout;

    ProductDetailsResponse productDetailsResponse;
    String slug, category;

    int count = 1;

    CommonApiResponse commonApiResponse;
    RecyclerView relatedProductRecyclerView;
    RelatedProductAdapter relatedProductAdapter;

    ImageSlider imageSlider;
    ArrayList<SlideModel> imageSliderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        slug = getIntent().getStringExtra("slug");

        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();

        imageSlider = findViewById(R.id.sliderImageView);
        imageSliderList = new ArrayList<>();

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

                    imageSliderList.add(new SlideModel(productDetailsResponse.thumbnail, ScaleTypes.FIT));
                    imageSliderList.add(new SlideModel(productDetailsResponse.image1, ScaleTypes.FIT));
                    imageSliderList.add(new SlideModel(productDetailsResponse.image2, ScaleTypes.FIT));
                    imageSliderList.add(new SlideModel(productDetailsResponse.image3, ScaleTypes.FIT));

                    imageSlider.setImageList(imageSliderList);

                    //Glide.with(getApplicationContext()).load(productDetailsResponse.thumbnail).into(binding.sliderImageView);
                    Glide.with(getApplicationContext()).load(productDetailsResponse.brand.image).into(binding.brandImage);
                    binding.productNameTextView.setText(productDetailsResponse.name);
                    binding.productPriceTextView.setText(String.valueOf(productDetailsResponse.final_price) + " Tk.");
                    binding.productDiscountPriceTextView.setText(productDetailsResponse.price + " Tk.");
                    binding.discountPercentTextView.setText(productDetailsResponse.discount + " %");
                    binding.productDiscountPriceTextView.setPaintFlags(binding.productDiscountPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    binding.categoryNameTextView.setText(productDetailsResponse.category.name);

                    binding.amountTextView.setText(String.valueOf(count));

                    category = productDetailsResponse.category.name;

                    System.out.println("Product Name "+productDetailsResponse.name);

                    RetrofitClient.getRetrofitClient(getApplicationContext()).getRelatedProduct(category).enqueue(new Callback<CommonApiResponse>() {
                        @Override
                        public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                            if (response.isSuccessful()){
                                commonApiResponse = response.body();
                                relatedProductAdapter = new RelatedProductAdapter(getApplicationContext(), commonApiResponse);
                                relatedProductRecyclerView.setAdapter(relatedProductAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<CommonApiResponse> call, Throwable t) {

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