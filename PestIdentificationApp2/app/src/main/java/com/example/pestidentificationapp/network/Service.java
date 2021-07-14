package com.example.pestidentificationapp.network;

import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.model.response.ResponsePossibility;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Service {

    @Multipart
    @POST("predict")
    Call<Pest> testImageUpload(@Part MultipartBody.Part file);

    @GET("all")
    Call<List<Pest>> getAllPests();


    @Multipart
    @POST("predictPercentage")
    Call<List<ResponsePossibility>> uploadImage(@Part MultipartBody.Part file);

    @GET("info/{latin_name}")
    Call<Pest> getPestInformation(@Path("latin_name") String latinName);
}
