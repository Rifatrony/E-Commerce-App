package com.rony.e_commerceapp.API;

import com.rony.e_commerceapp.Response.AddCartBodyResponse;
import com.rony.e_commerceapp.Response.AddCartResponse;
import com.rony.e_commerceapp.Response.CartResponse;
import com.rony.e_commerceapp.Response.CategoryResponse;
import com.rony.e_commerceapp.Response.DeliveryMethodResponse;
import com.rony.e_commerceapp.Response.OrderDetailsResponse;
import com.rony.e_commerceapp.Response.OrderResponse;
import com.rony.e_commerceapp.Response.ProductDetailsResponse;
import com.rony.e_commerceapp.Response.RegistrationResponse;
import com.rony.e_commerceapp.Response.SliderResponse;
import com.rony.e_commerceapp.Response.CommonApiResponse;
import com.rony.e_commerceapp.Response.SuccessResponse;
import com.rony.e_commerceapp.Response.TokenInvokedResponse;
import com.rony.e_commerceapp.Response.UserDetailsResponse;
import com.rony.e_commerceapp.Response.UserRegisterResponse;
import com.rony.e_commerceapp.Response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @FormUrlEncoded
    @POST("auth/user/login")
    Call<UserRegisterResponse> userLogin(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("device_name") String device_name
    );

    @FormUrlEncoded
    @POST("user/profile/update")
    Call<SuccessResponse> updateProfile(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("address") String address

    );

    @FormUrlEncoded
    @POST("user/reset/password")
    Call<TokenInvokedResponse> changePassword(
            @Field("currentpassword") String currentpassword,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation
    );

    @GET("category/index")
    Call<CategoryResponse> getCategories();

    /*Get all the categories*/
    @GET("banners")
    Call<SliderResponse> getBanner();

    @GET("product/products")
    Call<CommonApiResponse> getTopSelling(
            @Query("page") int page
    );

    @GET("product/products")
    Call<CommonApiResponse> getAllProduct(
            @Query("page") int page
    );

    @GET("product/products")
    Call<CommonApiResponse> getCategoryWiseProduct(
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
    Call<CommonApiResponse> getRelatedProduct(
            @Query("category") String Category
    );

    @GET("checkout/delivery/methods")
    Call<List<DeliveryMethodResponse>> getDeliveryCharge();

    @GET("user/dashboard")
    Call<UserDetailsResponse> getUserDetails();

    @GET("carts")
    Call<CartResponse> getCartItem();

    @POST("carts/add/{path}")
    Call<AddCartResponse> addToCart(
            @Path("path") String product_id,
            @Body AddCartBodyResponse addCartBodyResponse
    );

    @DELETE("carts/{path}/delete")
    Call<SuccessResponse> deleteAItem(
            @Path("path") String product_id
    );

    @GET("carts/{path}/increment")
    Call<CartResponse> incrementCart(
            @Path("path") String product_id
    );


    @GET("carts/{path}/decrement")
    Call<CartResponse> decrementCart(
            @Path("path") String product_id
    );

    @FormUrlEncoded
    @POST("checkout/process")
    Call<SuccessResponse> placeOrder(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("delivery_method") String delivery_method,
            @Field("address") String address
    );

    @GET("user/orders")
    Call<OrderResponse> getOrder();

    @GET("user/order/details/{order_id}")
    Call<OrderDetailsResponse> getOrderDetails(
            @Path("order_id") String order_id
    );

    @GET("product/products")
    Call<CommonApiResponse> searchProduct(
            @Query("search") String search
    );


}
