package com.example.pestidentificationapp.network;

import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.model.ResponseLibraryDate;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {

    @Multipart
    @POST("predict")
    Call<Pest> testImageUpload(@Part MultipartBody.Part file);

    @GET("all")
    Call<List<Pest>> getAllPests();
}
