package com.example.app2hands.Api;

import com.example.app2hands.Model.Product;

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
    public static final String DOMAIN = "http://192.168.62.159:3000";

    ApiService api = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

//    Auth
    @POST("/auth/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/auth/register")
    Call<Void> executeRegister(@Body HashMap<String, String> map);

    @POST("/auth/change-password")
    Call<Void> executeChangePassword(@Body HashMap<String, String> map);

//    User's products
    @GET("/{userId}/products")
    Call<List<Product>> executeGetUserProducts(@Path("userId") String userId);

    @GET("/selling-products")
    Call<List<Product>> executeGetSellingProducts();

    @Multipart
    @POST("/{userId}/products")
    Call<Void> executeCreateProduct(@Path("userId") String userId,
                                    @Part("name")RequestBody name,
                                    @Part("price")RequestBody price,
                                    @Part("status")RequestBody status,
                                    @Part("type")RequestBody type,
                                    @Part("description")RequestBody description,
                                    @Part MultipartBody.Part image);
}
