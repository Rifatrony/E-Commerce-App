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
    CartResponse cartResponse;

    int count = 0;
    String amount;

    public CartAdapter(Context context, CartResponse cartResponse) {
        this.context = context;
        this.cartResponse = cartResponse;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameTextView.setText(cartResponse.data.get(position).product.name);
        holder.singlePriceTextView.setText(cartResponse.data.get(position).price + " Tk.");
        holder.priceTextView.setText("Total: "+ cartResponse.data.get(position).total);
        holder.amountTextView.setText(String.valueOf(cartResponse.data.get(position).quantity));

        Glide.with(context).load(cartResponse.data.get(position).product.thumbnail).into(holder.cartImageView);
        /*holder.increaseCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = cartResponseList.get(position).getAmount();
                count = Integer.parseInt(amount);
                count++;
                System.out.println("Count is + " + count);
                notifyDataSetChanged();
                //updatePrice();
            }
        });*/

    }

    public void updatePrice(){
        /*int sum = 0, i;
        for (i = 0; i < cartResponseList.size(); i++){
            sum = sum + (Integer.parseInt(cartResponseList.get(i).getPrice()) * Integer.parseInt(cartResponseList.get(i).getAmount()));
        }
        System.out.println("Sum is : " + sum);*/
    }

    @Override
    public int getItemCount() {
        return cartResponse.data.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, priceTextView, amountTextView, singlePriceTextView;
        CircleImageView cartImageView;
        ImageView increaseCartImageView, decreaseCartImageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartImageView =itemView.findViewById(R.id.cartImageView);
            nameTextView =itemView.findViewById(R.id.nameTextView);
            singlePriceTextView =itemView.findViewById(R.id.singlePriceTextView);
            priceTextView =itemView.findViewById(R.id.priceTextView);
            amountTextView =itemView.findViewById(R.id.amountTextView);
            increaseCartImageView =itemView.findViewById(R.id.increaseCartImageView);
            decreaseCartImageView =itemView.findViewById(R.id.decreaseCartImageView);

        }
    }

}
