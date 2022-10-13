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
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.TopSellingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopSellingAdapter extends RecyclerView.Adapter<TopSellingAdapter.TopSellingViewHolder> {

    List<TopSellingResponse> topSellingResponseList;
    Context context;

    public TopSellingAdapter(List<TopSellingResponse> topSellingResponseList, Context context) {
        this.topSellingResponseList = topSellingResponseList;
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
    public void onBindViewHolder(@NonNull TopSellingViewHolder holder, int position) {
        TopSellingResponse response = topSellingResponseList.get(position);
        holder.nameTextView.setText(response.getName());
        holder.priceTextView.setText(String.valueOf(response.getPrice())+ " Tk.");
        holder.unitTextView.setText(response.getUnit());
        holder.amountTextView.setText(String.valueOf(response.getAmount()));
        Glide.with(context).load(response.getImage()).into(holder.imageView);

        holder.constraintLayout.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductDetailsActivity.class));
            }
        });

    }

    private int getRandomColor() {

        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.edit_text_background);
        colorCode.add(R.color.white);
        colorCode.add(R.color.primary_button_background_color);

        Random randomColor = new Random();

        int number = randomColor.nextInt(colorCode.size());
        return colorCode.get(number);

    }

    @Override
    public int getItemCount() {
        return topSellingResponseList.size();
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
            unitTextView = itemView.findViewById(R.id.unitTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            discountTextView = itemView.findViewById(R.id.discountTextView);

        }
    }

}
