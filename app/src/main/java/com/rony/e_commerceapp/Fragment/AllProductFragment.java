package com.rony.e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rony.e_commerceapp.API.RetrofitClient;
import com.rony.e_commerceapp.Adapter.AllProductAdapter;
import com.rony.e_commerceapp.R;
import com.rony.e_commerceapp.Response.CommonApiResponse;
import com.rony.e_commerceapp.databinding.FragmentAllProductBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllProductFragment extends Fragment {

    FragmentAllProductBinding binding;
    View view;
    AllProductAdapter allProductAdapter;
    CommonApiResponse commonApiResponse;

    public int page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllProductBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        binding.shimmerLayout.startShimmer();

        binding.allProductRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.allProductRecyclerView.setHasFixedSize(true);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page ++;
                fetchAllProduct();
            }
        });

        binding.previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page --;
                fetchAllProduct();
            }
        });

        fetchAllProduct();


        return view;

    }

    private void fetchAllProduct(){
        RetrofitClient.getRetrofitClient(getContext()).getAllProduct(page).enqueue(new Callback<CommonApiResponse>() {
            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                if (response.isSuccessful()){

                    binding.shimmerLayout.stopShimmer();
                    binding.shimmerLayout.setVisibility(View.GONE);
                    binding.nestedScrollView.setVisibility(View.VISIBLE);
                    commonApiResponse = response.body();

                    binding.paginationLayout.setVisibility(View.VISIBLE);

                    System.out.println("current page is ----- >" + commonApiResponse.products.pagination.current_page);

                    binding.pageNumberTextView.setText(commonApiResponse.products.pagination.current_page
                            + " of " + commonApiResponse.products.pagination.total_pages);
                    System.out.println("Total page is : " + commonApiResponse.products.pagination.total_pages);

                    if (commonApiResponse.products.pagination.current_page == page){
                        allProductAdapter = new AllProductAdapter(getContext(), commonApiResponse);
                        binding.allProductRecyclerView.setAdapter(allProductAdapter);
                        allProductAdapter.notifyDataSetChanged();
                        binding.previous.setEnabled(true);
                        binding.previous.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    }

                    if (commonApiResponse.products.pagination.current_page == 1){
                        System.out.println("This is the first page");
                        binding.previous.setEnabled(false);
                    }

                    if (commonApiResponse.products.pagination.total_pages > 1){
                        System.out.println(String.valueOf(page + 1) + " is the next page");
                        allProductAdapter = new AllProductAdapter(getContext(), commonApiResponse);
                        binding.allProductRecyclerView.setAdapter(allProductAdapter);
                        allProductAdapter.notifyDataSetChanged();
                        binding.next.setEnabled(true);
                    }

                    if (commonApiResponse.products.pagination.total_pages == commonApiResponse.products.pagination.current_page){
                        System.out.println("This is the last page");
                        binding.next.setEnabled(false);
                    }

                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {

            }
        });
    }
}