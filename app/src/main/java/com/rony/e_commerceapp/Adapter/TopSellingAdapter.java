package com.rony.e_commerceapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.Activity.ProductDetailsActivity;
import com.rony.e_commerceapp.Activity.ProductDetailsActivity2;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.TopSellingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopSellingAdapter extends RecyclerView.Adapter<TopSellingAdapter.TopSellingViewHolder> {

    TopSellingResponse topSellingResponse;
    Context context;

    public TopSellingAdapter(TopSellingResponse topSellingResponse, Context context) {
        this.topSellingResponse = topSellingResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public TopSellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_selling_layout, parent, false);
        return new TopSellingViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TopSellingViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (topSellingResponse.products.data.size()>0){
            holder.nameTextView.setText(topSellingResponse.products.data.get(position).name);
            holder.priceTextView.setText(topSellingResponse.products.data.get(position).price);
            holder.discountTextView.setText(topSellingResponse.products.data.get(position).discount + " %");

            Glide.with(context).load(topSellingResponse.products.data.get(position).thumbnail).into(holder.imageView);

            holder.constraintLayout.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity2.class);
                    intent.putExtra("slug", topSellingResponse.products.data.get(position).slug);
                    /*intent.putExtra("name", topSellingResponse.products.data.get(position).name);
                    intent.putExtra("thumbnail", topSellingResponse.products.data.get(position).thumbnail);
                    intent.putExtra("discount", topSellingResponse.products.data.get(position).discount);
                    intent.putExtra("price", topSellingResponse.products.data.get(position).price);*/
                    context.startActivity(intent);
                }
            });
        }


    }

    private int getRandomColor() {

        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.random_color);
        colorCode.add(R.color.random_color1);
        colorCode.add(R.color.random_color2);
        colorCode.add(R.color.random_color3);
        colorCode.add(R.color.random_color4);
        colorCode.add(R.color.random_color5);
        colorCode.add(R.color.random_color6);
        colorCode.add(R.color.random_color7);
        colorCode.add(R.color.random_color8);
        colorCode.add(R.color.random_color9);


        Random randomColor = new Random();

        int number = randomColor.nextInt(colorCode.size());
        return colorCode.get(number);

    }

    @Override
    public int getItemCount() {
        return topSellingResponse.products.data.size();
    }

    public class TopSellingViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, priceTextView, unitTextView, amountTextView, discountTextView;

        CardView constraintLayout;

        public TopSellingViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            discountTextView = itemView.findViewById(R.id.discountTextView);

        }
    }

}
