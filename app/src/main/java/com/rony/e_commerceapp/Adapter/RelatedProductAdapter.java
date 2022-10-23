package com.rony.e_commerceapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.TopSellingResponse;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.RelatedProductViewHolder> {

    Context context;
    TopSellingResponse topSellingResponse;

    public RelatedProductAdapter(Context context, TopSellingResponse topSellingResponse) {
        this.context = context;
        this.topSellingResponse = topSellingResponse;
    }

    @NonNull
    @Override
    public RelatedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_selling_layout, parent, false);
        return new RelatedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductViewHolder holder, int position) {

        if (topSellingResponse.products.data.size()>0){

            holder.nameTextView.setText(topSellingResponse.products.data.get(position).name);
            holder.priceTextView.setText(topSellingResponse.products.data.get(position).price);
            holder.discountTextView.setText(topSellingResponse.products.data.get(position).discount + " %");

            Glide.with(context).load(topSellingResponse.products.data.get(position).thumbnail).into(holder.imageView);

        }

    }

    @Override
    public int getItemCount() {
        return topSellingResponse.products.data.size();
    }


    public static class RelatedProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, priceTextView, unitTextView, amountTextView, discountTextView;

        CardView constraintLayout;

        public RelatedProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            discountTextView = itemView.findViewById(R.id.discountTextView);

        }
    }

}