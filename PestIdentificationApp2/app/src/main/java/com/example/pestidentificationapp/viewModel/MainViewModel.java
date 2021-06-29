package com.example.pestidentificationapp.viewModel;

import android.util.Log;

import com.example.pestidentificationapp.model.TestData;
import com.example.pestidentificationapp.network.Service;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel {

    public void upload(Photo photo) {
        File file = new File(photo.path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", photo.name, requestBody);
//        val fileRQ = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//        val part = MultipartBody.Part.createFormData("file", file.name, fileRQ)
        TestData testData = new TestData();
        testData.setFileName(photo.name);
        testData.setFileType(photo.type);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Map<String, String> data = new HashMap<>();
        data.put("fileName", photo.name);
        data.put("fileType", photo.type);

        Call<String> call = service.testImageUpload(f, data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("请求", "onResponse: 成功");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("请求", "onFailure: 失败");
            }
        });
    }
}
