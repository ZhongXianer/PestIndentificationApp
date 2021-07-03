package com.example.pestidentificationapp.network;

import androidx.databinding.BaseObservable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtil {

    public static String BaseUrl = "http://192.168.43.171:5000/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static Service BaseService=retrofit.create(Service.class);

    public static Service getBaseService(){
        return BaseService;
    }
}
