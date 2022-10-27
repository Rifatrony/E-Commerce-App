package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.OrderItemAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.OrderDetailsResponse;
import com.rony.e_commerceapp.databinding.ActivityOrderDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    ActivityOrderDetailsBinding binding;
    String order_id;

    OrderDetailsResponse orderDetailsResponse;
    OrderItemAdapter orderItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.orderItemRecyclerView.setHasFixedSize(true);
        binding.orderItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        order_id = getIntent().getStringExtra("order_id");
        System.out.println("Order Id is "+order_id);

        getOrderDetails();

    }

    private void getOrderDetails() {
        RetrofitClient.getRetrofitClient(this).getOrderDetails(order_id).enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                if (response.isSuccessful()){
                    orderDetailsResponse = response.body();
                    orderItemAdapter = new OrderItemAdapter(getApplicationContext(), orderDetailsResponse);
                    binding.orderItemRecyclerView.setAdapter(orderItemAdapter);

                    binding.statusTextView.setText(orderDetailsResponse.status);
                    binding.subTotalTextView.setText(orderDetailsResponse.sub_total);
                    binding.deliveryChargeTextView.setText(orderDetailsResponse.delivery_rate);
                    binding.totalTextView.setText(orderDetailsResponse.total);
                    binding.deliveryCategoryTextView.setText(orderDetailsResponse.delivery);
                    binding.paymentTextView.setText(orderDetailsResponse.payment);
                    binding.addressTextView.setText(orderDetailsResponse.address);
                    binding.numberTextView.setText(orderDetailsResponse.phone);
                    binding.totalItemTextView.setText(String.valueOf(orderDetailsResponse.items.size()));


                }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {

            }
        });
    }
}