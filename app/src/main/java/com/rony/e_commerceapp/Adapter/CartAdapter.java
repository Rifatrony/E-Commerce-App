package com.rony.e_commerceapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CartResponse;
import com.rony.e_commerceapp.Response.SuccessResponse;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    CartResponse cartResponse;
    TextView totalTextView;

    int count = 0;
    String amount;
    int qnty = 0;

    public CartAdapter(Context context, CartResponse cartResponse, TextView totalTextView) {
        this.context = context;
        this.cartResponse = cartResponse;
        this.totalTextView = totalTextView;
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

        qnty = 0;

        holder.increaseCartImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                int qnt = Integer.parseInt(cartResponse.data.get(position).quantity);
                qnt++;
                cartResponse.data.get(position).setQuantity(String.valueOf(qnt));
                holder.amountTextView.setText(String.valueOf(qnt));
                System.out.println("Update quantity is : " + qnt);

                double price = qnt * Double.parseDouble(cartResponse.data.get(position).price);
                cartResponse.data.get(position).setTotal(price);

                holder.priceTextView.setText(String.valueOf(price));

                System.out.println("\n\nUpdate Price is : " + price);

                notifyDataSetChanged();

                RetrofitClient.getRetrofitClient(context).incrementCart(cartResponse.data.get(position).product.id).enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        if (response.isSuccessful()  && response.body() != null){

                        }
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {
                    }
                });


            }
        });

        holder.decreaseCartImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                int qnt = Integer.parseInt(cartResponse.data.get(position).quantity);

                if (qnt != 1){
                    qnt--;
                    cartResponse.data.get(position).setQuantity(String.valueOf(qnt));
                    holder.amountTextView.setText(String.valueOf(qnt));
                    double price = qnt * Double.parseDouble(cartResponse.data.get(position).price);
                    cartResponse.data.get(position).setTotal(price);

                    holder.priceTextView.setText(String.valueOf(price));

                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Quantity Can't be Zero", Toast.LENGTH_SHORT).show();
                    return;
                }

                RetrofitClient.getRetrofitClient(context).decrementCart(cartResponse.data.get(position).product.id).enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        if (response.isSuccessful()){

                        }
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {

                    }
                });


            }
        });

        holder.deleteCartItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitClient.getRetrofitClient(context).deleteAItem(cartResponse.data.get(position).product.id).enqueue(new Callback<SuccessResponse>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            updatePrice();
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartResponse.data.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, priceTextView, amountTextView, singlePriceTextView;
        CircleImageView cartImageView;
        ImageView increaseCartImageView, decreaseCartImageView, deleteCartItemImageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartImageView =itemView.findViewById(R.id.cartImageView);
            nameTextView =itemView.findViewById(R.id.nameTextView);
            singlePriceTextView =itemView.findViewById(R.id.singlePriceTextView);
            priceTextView =itemView.findViewById(R.id.priceTextView);
            amountTextView =itemView.findViewById(R.id.amountTextView);
            increaseCartImageView =itemView.findViewById(R.id.increaseCartImageView);
            decreaseCartImageView =itemView.findViewById(R.id.decreaseCartImageView);
            deleteCartItemImageView =itemView.findViewById(R.id.deleteCartItemImageView);

        }
    }

    private void updatePrice(){
        double sum = 0;
        int i;
        for (i = 0; i < cartResponse.data.size(); i++){
            sum = sum + cartResponse.data.get(i).total;
            cartResponse.data.get(i).setTotal(sum);
        }
        totalTextView.setText(String.valueOf(sum));
        System.out.println(String.valueOf(sum));
    }

}
