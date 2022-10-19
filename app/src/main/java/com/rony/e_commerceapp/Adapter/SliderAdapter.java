package com.rony.e_commerceapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.SliderResponse;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    SliderResponse sliderResponse;
    Context context;

    public SliderAdapter(SliderResponse sliderResponse, Context context) {
        this.sliderResponse = sliderResponse;
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_layout, parent, false);
        return new SliderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        if (sliderResponse.data.size()>0){
            Glide.with(context)
                    .load(sliderResponse.data.get(position).image)
                    .into(viewHolder.myimage);
        }

        // Glide is use to load image
        // from url in your imageview.

    }

    @Override
    public int getCount() {
        return sliderResponse.data.size();
    }

    public static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView myimage;

        public SliderAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            myimage = itemView.findViewById(R.id.myimage);
        }
    }

}
