package com.rony.e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rony.e_commerceapp.Adapter.CategoryAdapter;
import com.rony.e_commerceapp.Adapter.SliderAdapter;
import com.rony.e_commerceapp.Adapter.TopSellingAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.Response.SliderResponse;
import com.rony.e_commerceapp.Response.TopSellingResponse;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    View view;
    RecyclerView categoriesRecyclerView;
    CategoryAdapter categoryAdapter;
    List<CategoryResponse> categoryResponseList;

    SliderView sliderView;
    List<SliderResponse> sliderResponseList;
    SliderAdapter sliderAdapter;

    RecyclerView topSellingRecyclerView;
    List<TopSellingResponse> topSellingResponseList;
    TopSellingAdapter topSellingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        setCategory();
        setSlider();
        setTopSelling();

        return view;
    }

    private void setTopSelling() {
        topSellingResponseList = new ArrayList<>();
        topSellingRecyclerView = view.findViewById(R.id.topSellingRecyclerView);
        topSellingRecyclerView.setHasFixedSize(true);
        topSellingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topSellingResponseList.add(new TopSellingResponse(R.drawable.fruits, "Fruits", "Kg", 1, 200));
        topSellingResponseList.add(new TopSellingResponse(R.drawable.fruits, "Fruits", "Kg", 1, 200));
        topSellingResponseList.add(new TopSellingResponse(R.drawable.fruits, "Fruits", "Kg", 1, 200));
        topSellingResponseList.add(new TopSellingResponse(R.drawable.fruits, "Fruits", "Kg", 1, 200));
        topSellingResponseList.add(new TopSellingResponse(R.drawable.fruits, "Fruits", "Kg", 1, 200));

        topSellingAdapter = new TopSellingAdapter(topSellingResponseList, getContext());
        topSellingRecyclerView.setAdapter(topSellingAdapter);

    }

    private void setSlider() {
        sliderResponseList = new ArrayList<>();
        sliderResponseList.add(new SliderResponse(R.drawable.fruits));
        sliderResponseList.add(new SliderResponse(R.drawable.vegetables));
        sliderResponseList.add(new SliderResponse(R.drawable.fishes));
        sliderResponseList.add(new SliderResponse(R.drawable.dairy));

        sliderAdapter = new SliderAdapter(sliderResponseList, getContext());

        sliderView = view.findViewById(R.id.slider);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    private void setCategory() {
        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setHasFixedSize(true);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryResponseList = new ArrayList<>();
        categoryResponseList.add(new CategoryResponse("Fruits", R.drawable.fruits));
        categoryResponseList.add(new CategoryResponse("Dairy", R.drawable.dairy));
        categoryResponseList.add(new CategoryResponse("Vegetables", R.drawable.vegetables));
        categoryResponseList.add(new CategoryResponse("Fishes", R.drawable.fishes));
        categoryResponseList.add(new CategoryResponse("Meat", R.drawable.meat));
        categoryResponseList.add(new CategoryResponse("Beverages", R.drawable.vegetables));
        categoryResponseList.add(new CategoryResponse("Baby Food", R.drawable.fruits));
        categoryResponseList.add(new CategoryResponse("Snacks", R.drawable.vegetables));
        categoryAdapter = new CategoryAdapter(getContext(), categoryResponseList);
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }
}