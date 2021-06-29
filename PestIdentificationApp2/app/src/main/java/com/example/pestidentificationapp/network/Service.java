package com.example.pestidentificationapp.network;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {

    @FormUrlEncoded
    @Multipart
    @POST("/upload")
    Call<String> testImageUpload(@Part MultipartBody.Part file,
                                 @FieldMap Map<String,String> data);
}
