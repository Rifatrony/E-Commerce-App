package com.rony.e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.OrderAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.OrderResponse;
import com.rony.e_commerceapp.databinding.FragmentOrderBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    View view;
    FragmentOrderBinding binding;
    OrderAdapter orderAdapter;
    OrderResponse orderResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        view = binding.getRoot();


        binding.orderRecyclerView.setHasFixedSize(true);
        binding.orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RetrofitClient.getRetrofitClient(getContext()).getOrder().enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()){
                    orderResponse = response.body();
                    assert orderResponse != null;
                    if (orderResponse.data.size()>0){
                        binding.actionBarLayout.setVisibility(View.VISIBLE);
                        orderAdapter = new OrderAdapter(getContext(), orderResponse);
                        binding.orderRecyclerView.setAdapter(orderAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });


        return  view;
    }
}