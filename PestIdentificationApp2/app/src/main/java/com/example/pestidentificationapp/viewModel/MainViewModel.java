package com.example.pestidentificationapp.viewModel;

import android.util.Log;

import com.example.pestidentificationapp.network.NetWorkUtil;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel {

    /**
     * 上传图片到服务器
     * @param photo
     */
    public void upload(Photo photo) {
        File file = new File(photo.path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", photo.name, requestBody);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.43.171:5000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Service service = retrofit.create(Service.class);
        Call<String> call = NetWorkUtil.getBaseService().testImageUpload(f);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("请求", "onResponse: 成功  "+response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("请求", "onFailure: 失败"+t.getMessage());
            }
        });
    }
}
