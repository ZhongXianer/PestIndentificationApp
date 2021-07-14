package com.example.pestidentificationapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtil {

    public static String BaseUrl = "http://q27g2.natapp1.cc/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static Service BaseService=retrofit.create(Service.class);

    public static Service getBaseService(){
        return BaseService;
    }
}
