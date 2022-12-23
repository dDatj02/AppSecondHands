package com.example.app2hands.Api;

import com.example.app2hands.Model.Product;
import com.example.app2hands.Model.User;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    final String IP = "192.168.62.159";
    public static final String DOMAIN = "http://" + IP + ":3000";

    ApiService api = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

//    Auth
    @POST("/auth/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("/auth/register")
    Call<Void> executeRegister(@Body HashMap<String, String> map);

    @POST("/auth/change-password")
    Call<Void> executeChangePassword(@Body HashMap<String, String> map);

    @GET("/selling-products")
    Call<List<Product>> executeGetSellingProducts();

//    User's products
    @GET("/{userId}/products")
    Call<List<Product>> executeGetUserStore(@Path("userId") String userId);

    @GET("/{userId}/buying-orders")
    Call<List<Product>> executeGetUserBuyingOrders(@Path("userId") String userId);

    @GET("/{userId}/done-orders")
    Call<List<Product>> executeGetUserDoneOrders(@Path("userId") String userId);

    @Multipart
    @POST("/{userId}")
    Call<User> executeUpdateProfile(@Path("userId") String userId,
                                    @Part("name")RequestBody name,
                                    @Part("phone")RequestBody phone,
                                    @Part("address")RequestBody address,
                                    @Part MultipartBody.Part avatar);

    @Multipart
    @POST("/{userId}/products/create")
    Call<Void> executeSellProduct(@Path("userId") String userId,
                                    @Part("name")RequestBody name,
                                    @Part("price")RequestBody price,
                                    @Part("status")RequestBody status,
                                    @Part("type")RequestBody type,
                                    @Part("description")RequestBody description,
                                    @Part MultipartBody.Part image);
}
