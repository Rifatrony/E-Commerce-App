package com.rony.e_commerceapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.ProductDetailsResponse;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class ProductImageSliderAdapter extends SliderViewAdapter<ProductImageSliderAdapter.ProductSliderViewHolder> {

    Context context;
    List<ProductDetailsResponse> detailsResponse;

    public ProductImageSliderAdapter(Context context, List<ProductDetailsResponse> detailsResponse) {
        this.context = context;
        this.detailsResponse = detailsResponse;
    }

    @Override
    public ProductSliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_layout, parent, false);
        return new ProductSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductSliderViewHolder viewHolder, int position) {
        if (detailsResponse.size()>0){
            Glide.with(context)
                    .load(detailsResponse.get(position).image1)
                    .into(viewHolder.myimage);
        }
    }

    @Override
    public int getCount() {
        return detailsResponse.size();
    }

    public class  ProductSliderViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView myimage;

        public ProductSliderViewHolder(View itemView) {
            super(itemView);
            myimage = itemView.findViewById(R.id.myimage);
        }
    }


}
