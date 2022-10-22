package com.rony.e_commerceapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CartResponse;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartResponse> cartResponseList;

    int count = 0;
    String amount;

    public CartAdapter(Context context, List<CartResponse> cartResponseList) {
        this.context = context;
        this.cartResponseList = cartResponseList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameTextView.setText(cartResponseList.get(position).getName());
        holder.priceTextView.setText(cartResponseList.get(position).getPrice());
        holder.amountTextView.setText(cartResponseList.get(position).getAmount());

        Glide.with(context).load(cartResponseList.get(position).getImage()).into(holder.cartImageView);
        holder.increaseCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = cartResponseList.get(position).getAmount();
                count = Integer.parseInt(amount);
                count++;
                System.out.println("Count is + " + count);
                notifyDataSetChanged();
                updatePrice();
            }
        });

    }

    public void updatePrice(){
        int sum = 0, i;
        for (i = 0; i < cartResponseList.size(); i++){
            sum = sum + (Integer.parseInt(cartResponseList.get(i).getPrice()) * Integer.parseInt(cartResponseList.get(i).getAmount()));
        }
        System.out.println("Sum is : " + sum);
    }

    @Override
    public int getItemCount() {
        return cartResponseList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, priceTextView, amountTextView;
        CircleImageView cartImageView;
        ImageView increaseCartImageView, decreaseCartImageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartImageView =itemView.findViewById(R.id.cartImageView);
            nameTextView =itemView.findViewById(R.id.nameTextView);
            priceTextView =itemView.findViewById(R.id.priceTextView);
            amountTextView =itemView.findViewById(R.id.amountTextView);
            increaseCartImageView =itemView.findViewById(R.id.increaseCartImageView);
            decreaseCartImageView =itemView.findViewById(R.id.decreaseCartImageView);

        }
    }

}