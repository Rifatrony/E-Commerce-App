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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.rony.e_commerceapp.Activity.CategoryWiseProductActivity;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.AllCategoriesViewHolder> {

    Context context;
    CategoryResponse categoryResponse;

    public AllCategoriesAdapter(Context context, CategoryResponse categoryResponse) {
        this.context = context;
        this.categoryResponse = categoryResponse;
    }

    @NonNull
    @Override
    public AllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_categories_layout, parent, false);
        return new AllCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (categoryResponse.categories.data.size()>0){
            holder.nameTextView.setText(categoryResponse.categories.data.get(position).name);
            Glide.with(context).load(categoryResponse.categories.data.get(position).image).into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context, CategoryWiseProductActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("slug", categoryResponse.categories.data.get(position).slug);
                    intent.putExtra("name", categoryResponse.categories.data.get(position).name);
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return categoryResponse.categories.data.size();
    }

    public static class AllCategoriesViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView nameTextView;

        public AllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);

        }
    }

}
