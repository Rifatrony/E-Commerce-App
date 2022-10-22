package com.rony.e_commerceapp.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientWithoutHeader {

    public static final String BASE_URL= "https://faruqtraders.com/api/v1/";
    public static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient(){

        if (retrofit == null){

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            Gson gson = new GsonBuilder().create();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }

}
