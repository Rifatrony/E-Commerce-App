package com.rony.e_commerceapp.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.OrderDetailsResponse;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {
    
    Context context;
    OrderDetailsResponse orderDetailsResponse;

    public OrderItemAdapter(Context context, OrderDetailsResponse orderDetailsResponse) {
        this.context = context;
        this.orderDetailsResponse = orderDetailsResponse;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item_layout, parent, false);
        return new OrderItemViewHolder(view)
;    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        if (orderDetailsResponse.items.size()>0){
            holder.nameTextView.setText(orderDetailsResponse.items.get(position).name);
            holder.unitPriceTextView.setText(orderDetailsResponse.items.get(position).price);
            holder.totalPriceTextView.setText(String.valueOf(orderDetailsResponse.items.get(position).total));
        }
    }

    @Override
    public int getItemCount() {
        return orderDetailsResponse.items.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, unitPriceTextView, totalPriceTextView;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            unitPriceTextView = itemView.findViewById(R.id.unitPriceTextView);
            totalPriceTextView = itemView.findViewById(R.id.totalPriceTextView);
        }
    }

}
