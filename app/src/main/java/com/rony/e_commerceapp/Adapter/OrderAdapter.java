package com.rony.e_commerceapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rony.e_commerceapp.Activity.OrderDetailsActivity;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.OrderResponse;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    OrderResponse orderResponse;

    public OrderAdapter(Context context, OrderResponse orderResponse) {
        this.context = context;
        this.orderResponse = orderResponse;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (orderResponse.data.size()> 0){
            holder.orderIdTextView.setText(orderResponse.data.get(position).orderid);
            holder.statusTextView.setText(orderResponse.data.get(position).status);
            holder.quantityTextView.setText(String.valueOf(orderResponse.data.get(position).quantity));

            holder.orderIdTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra("order_id", orderResponse.data.get(position).id);
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return orderResponse.data.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderIdTextView, statusTextView, quantityTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);

        }
    }

}
