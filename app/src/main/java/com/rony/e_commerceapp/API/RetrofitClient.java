package com.rony.e_commerceapp.API;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rony.e_commerceapp.Session.SessionManagement;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL= "https://faruqtraders.com/api/v1/";
    public static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient(Context context){

        SessionManagement sessionManagement;
        String token;

        if (retrofit == null){

            sessionManagement = new SessionManagement(context);
            token = "Bearer "+sessionManagement.getSessionModel().getAccessToken();

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {

                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization",token)
                            .build();

                    return chain.proceed(newRequest);

                }
            }).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }

}
