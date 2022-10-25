package com.rony.e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.CategoryAdapter;
import com.rony.e_commerceapp.Adapter.SliderAdapter;
import com.rony.e_commerceapp.Adapter.TopSellingAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.Response.SliderResponse;
import com.rony.e_commerceapp.Response.CommonApiResponse;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;
    RecyclerView categoriesRecyclerView;
    CategoryAdapter categoryAdapter;
    CategoryResponse categoryResponse;

    SliderView sliderView;
    SliderResponse sliderResponse;
    SliderAdapter sliderAdapter;

    RecyclerView topSellingRecyclerView;
    CommonApiResponse commonApiResponse;
    TopSellingAdapter topSellingAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        setCategory();
        setSlider();
        setTopSelling();

        return view;
    }

    private void setTopSelling() {
        topSellingRecyclerView = view.findViewById(R.id.topSellingRecyclerView);
        topSellingRecyclerView.setHasFixedSize(true);
        topSellingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RetrofitClient.getRetrofitClient(getContext()).getTopSelling(1).enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                if (response.isSuccessful()){
                    commonApiResponse = response.body();
                    topSellingAdapter = new TopSellingAdapter(commonApiResponse, getContext());
                    topSellingRecyclerView.setAdapter(topSellingAdapter);
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {

            }
        });

    }

    private void setSlider() {
        RetrofitClient.getRetrofitClient(getContext()).getBanner().enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                if (response.isSuccessful()){
                    sliderResponse = response.body();
                    sliderAdapter = new SliderAdapter(sliderResponse, getContext());

                    sliderView = view.findViewById(R.id.slider);
                    sliderView.setSliderAdapter(sliderAdapter);
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();
                }
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {

            }
        });
    }

    private void setCategory() {
        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setHasFixedSize(true);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RetrofitClient.getRetrofitClient(getContext()).getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoryResponse = response.body();
                    categoryAdapter = new CategoryAdapter(getContext(), categoryResponse);
                    categoriesRecyclerView.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }
}