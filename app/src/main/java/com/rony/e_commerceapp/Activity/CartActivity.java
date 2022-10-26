package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.CartAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CartResponse;
import com.rony.e_commerceapp.Response.DeliveryMethodResponse;
import com.rony.e_commerceapp.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    RecyclerView cartRecyclerView;
    CartResponse cartResponse;
    CartAdapter cartAdapter;
    List<DeliveryMethodResponse> deliveryMethodResponseList;

    ArrayList<String> deliverAddressList;
    ArrayList<String> deliverIdList;
    ArrayList<String> deliverChargeList;
    ArrayAdapter<String> deliverChargeAdapter;

    String selectedDeliveryChargeId = null;
    public static String deliveryChargeRate = null;
    String deliveryChargeAddress = null;

    double grand_total = 0, total_amount = 0;
    double sum = 0;
    double finalAmount = 0;

    TextView cartSizeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartSizeTextView =findViewById(R.id.cartSizeTextView);

        //binding.totalTextView.setText(String.valueOf(total_amount));

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        deliverAddressList = new ArrayList<>();
        deliverChargeList = new ArrayList<>();
        deliverIdList = new ArrayList<>();
        deliverChargeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, deliverAddressList);
        binding.deliveryChargeSpinner.setAdapter(deliverChargeAdapter);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //getCart();

        RetrofitClient.getRetrofitClient(this).getDeliveryCharge().enqueue(new Callback<List<DeliveryMethodResponse>>() {
            @Override
            public void onResponse(Call<List<DeliveryMethodResponse>> call, Response<List<DeliveryMethodResponse>> response) {
                if (response.isSuccessful()){
                    deliveryMethodResponseList = response.body();

                    for (int i = 0; i < deliveryMethodResponseList.size(); i ++){
                        deliverAddressList.add(deliveryMethodResponseList.get(i).name);
                        deliverIdList.add(deliveryMethodResponseList.get(i).id);
                        deliverChargeList.add(deliveryMethodResponseList.get(i).rate);
                    }

                    setDeliveryAdapter(deliverAddressList, deliverIdList, deliverChargeList);

                }
            }

            @Override
            public void onFailure(Call<List<DeliveryMethodResponse>> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setDeliveryAdapter(ArrayList<String> deliverAddressList, ArrayList<String> deliverIdList, ArrayList<String> deliverChargeList) {
        deliverChargeAdapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_spinner_dropdown_item, deliverAddressList);
        binding.deliveryChargeSpinner.setAdapter(deliverChargeAdapter);

        binding.deliveryChargeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedDeliveryChargeId = deliverIdList.get(i);
                deliveryChargeRate = deliverChargeList.get(i);
                deliveryChargeAddress = deliverAddressList.get(i);

                grand_total = Double.parseDouble(deliveryChargeRate) + total_amount;
                binding.grandTotalTextView.setText(String.valueOf(grand_total));

                Toast.makeText(CartActivity.this, "Delivery Charge " + deliveryChargeAddress + " " +  deliveryChargeRate + " Tk", Toast.LENGTH_LONG).show();

                getCart(deliveryChargeRate);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getCart(String deliveryChargeRate){
        RetrofitClient.getRetrofitClient(this).getCartItem().enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                finalAmount = 0;
                sum = 0;

                if (response.isSuccessful()){


                    cartResponse = response.body();

                    cartSizeTextView.setText(String.valueOf(cartResponse.data.size()));
                    cartAdapter = new CartAdapter(getApplicationContext(), cartResponse, binding.totalTextView);
                    cartRecyclerView.setAdapter(cartAdapter);

                    for (int i = 0; i < cartResponse.data.size(); i ++){
                        sum = sum + cartResponse.data.get(i).total;
                    }

                    System.out.println("Total is : " + sum);
                    binding.totalTextView.setText(String.valueOf(sum));

                    System.out.println("Delivery Charge is : " + deliveryChargeRate);

                    finalAmount = Double.parseDouble(deliveryChargeRate) + sum;
                    binding.grandTotalTextView.setText(String.valueOf(finalAmount));

                    cartAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();
    }*/

    /*@Override
    protected void onStop() {
        super.onStop();
        cartAdapter.
    }*/

}