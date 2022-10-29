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

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.RelatedProductViewHolder> {

    Context context;
    CommonApiResponse commonApiResponse;

    public RelatedProductAdapter(Context context, CommonApiResponse commonApiResponse) {
        this.context = context;
        this.commonApiResponse = commonApiResponse;
    }

    @NonNull
    @Override
    public RelatedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_selling_layout, parent, false);
        return new RelatedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (commonApiResponse.products.data.size()>0){

            holder.nameTextView.setText(commonApiResponse.products.data.get(position).name);
            holder.priceTextView.setText(commonApiResponse.products.data.get(position).price);
            holder.discountTextView.setText(commonApiResponse.products.data.get(position).discount + " %");

            Glide.with(context).load(commonApiResponse.products.data.get(position).thumbnail).into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("slug", commonApiResponse.products.data.get(position).slug);
                    intent.putExtra("product_id", commonApiResponse.products.data.get(position).id);
                    context.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return commonApiResponse.products.data.size();
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
