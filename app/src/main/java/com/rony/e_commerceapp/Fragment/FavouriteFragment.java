package com.rony.e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.rony.e_commerceapp.R;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {

    View view;
    ImageSlider imageSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourite, container, false);

        imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> imageSliderList = new ArrayList<>();
        imageSliderList.add(new SlideModel(R.drawable.fruits, ScaleTypes.CENTER_CROP));
        imageSliderList.add(new SlideModel(R.drawable.meat, ScaleTypes.CENTER_CROP));
        imageSliderList.add(new SlideModel(R.drawable.vegetables, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageSliderList);

        return  view;
    }
}