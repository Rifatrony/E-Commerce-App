package com.rony.e_commerceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.Activity.CategoryWiseProductActivity;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    CategoryResponse response;

    public CategoryAdapter(Context context, CategoryResponse response) {
        this.context = context;
        this.response = response;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.round_categories_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        if (response.categories.data.size()>0){
            holder.categoryNameTextView.setText(response.categories.data.get(position).name);
            Glide.with(context)
                    .load(response.categories.data.get(position).image)
                    .into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context, CategoryWiseProductActivity.class);
                    intent.putExtra("slug", response.categories.data.get(position).slug);
                    intent.putExtra("name", response.categories.data.get(position).name);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return response.categories.data.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView categoryNameTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            categoryNameTextView = itemView.findViewById(R.id.categoryNameTextView);

        }
    }

}
