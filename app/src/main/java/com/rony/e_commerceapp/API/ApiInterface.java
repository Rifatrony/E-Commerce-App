package com.rony.e_commerceapp.API;

import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.Response.ProductDetailsResponse;
import com.rony.e_commerceapp.Response.RegistrationResponse;
import com.rony.e_commerceapp.Response.SliderResponse;
import com.rony.e_commerceapp.Response.TopSellingResponse;
import com.rony.e_commerceapp.Response.UserRegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    /*Sign Up*/

    @FormUrlEncoded
    @POST("auth/user/registration")
    Call<UserRegisterResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("password_confirmation") String confirmPassword,
            @Field("device_name") String device_name,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("auth/user/registration")
    Call<RegistrationResponse> sendUserData(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("password_confirmation") String confirm_password,
            @Field("device_name") String device_name,
            @Field("otp") String otp
    );

    @GET("category/index")
    Call<CategoryResponse> getCategories();

    /*Get all the categories*/
    @GET("banners")
    Call<SliderResponse> getBanner();

    @GET("product/products")
    Call<TopSellingResponse> getTopSelling(
            @Query("page") int page
    );

    @GET("product/products")
    Call<TopSellingResponse> getCategoryWiseProduct(
            @Query("category") String category,
            @Query("page") int page
    );

    @GET("product/details/{slug}")
    Call<ProductDetailsResponse> getProductDetails(
            @Path("slug") String slug
    );

    @GET("product/details/{slug}")
    Call<List<ProductDetailsResponse>> getProductImageSlider(
            @Path("slug") String slug
    );

    @GET("product/products")
    Call<TopSellingResponse> getRelatedProduct(
            @Query("category") String Category
    );

}
