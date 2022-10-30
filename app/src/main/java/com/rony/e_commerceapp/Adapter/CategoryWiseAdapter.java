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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.Activity.ProductDetailsActivity;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CommonApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryWiseAdapter extends RecyclerView.Adapter<CategoryWiseAdapter.CategoryWiseViewHolder> {

    Context context;
    CommonApiResponse commonApiResponse;

    public CategoryWiseAdapter(Context context, CommonApiResponse commonApiResponse) {
        this.context = context;
        this.commonApiResponse = commonApiResponse;
    }

    @NonNull
    @Override
    public CategoryWiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_wise_product_layout, parent, false);
        return new CategoryWiseViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoryWiseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (commonApiResponse.products.data.size()>0){
            holder.nameTextView.setText(commonApiResponse.products.data.get(position).name);
            holder.priceTextView.setText(commonApiResponse.products.data.get(position).discounted_price + " ৳");
            holder.discountTextView.setText(commonApiResponse.products.data.get(position).discount + " %");

            Glide.with(context).load(commonApiResponse.products.data.get(position).thumbnail).into(holder.imageView);

           // holder.constraintLayout.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("slug", commonApiResponse.products.data.get(position).slug);
                    intent.putExtra("product_id", commonApiResponse.products.data.get(position).id);
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
        return commonApiResponse.products.data.size();
    }

    public static class CategoryWiseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, priceTextView, unitTextView, amountTextView, discountTextView;

        CardView constraintLayout;

        public CategoryWiseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            discountTextView = itemView.findViewById(R.id.discountTextView);

        }
    }

}
