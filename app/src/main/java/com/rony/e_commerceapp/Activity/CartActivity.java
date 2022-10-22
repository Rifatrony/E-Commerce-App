package com.rony.e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.rony.e_commerceapp.Adapter.CartAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CartResponse;
import com.rony.e_commerceapp.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    RecyclerView cartRecyclerView;
    List<CartResponse> cartResponseList;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartResponseList= new ArrayList<>();
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Ekel Green Tea AHA BHA PHA Brightening Toner (250ml)", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "TRESemme Keratin Smooth Shampoo, 400ml", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));

        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));

        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));
        cartResponseList.add(new CartResponse(R.drawable.fruits, "Fruits", "380", "1 Kg"));

        cartAdapter = new CartAdapter(this, cartResponseList);
        cartRecyclerView.setAdapter(cartAdapter);


    }
}