package com.example.pestidentificationapp.viewModel;

import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.model.IdentificationResultShow;
import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.network.NetWorkUtil;
import com.example.pestidentificationapp.other.ARouterUtil;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel {

    private View.OnClickListener hisItenBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance().build(ARouterUtil.HistoryIdentificationAct).navigation();
        }
    };

    private View.OnClickListener pestLibBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance().build(ARouterUtil.PestLibraryAct).navigation();
        }
    };

    private View.OnClickListener feedBackBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance().build(ARouterUtil.FeesBackAct).navigation();
        }
    };

//    /**
//     * 上传图片到服务器
//     *
//     * @param photo
//     */
//    public void upload(final Photo photo) {
//        File file = new File(photo.path);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part f = MultipartBody.Part.createFormData("file", photo.name, requestBody);
//        Call<Pest> call = NetWorkUtil.getBaseService().testImageUpload(f);
//        call.enqueue(new Callback<Pest>() {
//            @Override
//            public void onResponse(Call<Pest> call, Response<Pest> response) {
//                Pest pest = response.body();
//                IdentificationResultShow resultShow = new IdentificationResultShow(photo.uri.toString(),
//                        pest.getName(), pest.getLatinName(), pest.getPlant(), pest.getArea());
//                ARouter.getInstance().build(ARouterUtil.ResultShowAct)
//                        .withObject("result", resultShow)
//                        .navigation();
//                Log.d("请求", "onResponse: 成功  " + response.body().getName());
//            }
//
//            @Override
//            public void onFailure(Call<Pest> call, Throwable t) {
//                Log.d("请求", "onFailure: 失败" + t.getMessage());
//            }
//        });
//    }

    public View.OnClickListener getHisItenBtnListener() {
        return hisItenBtnListener;
    }

    public View.OnClickListener getPestLibBtnListener() {
        return pestLibBtnListener;
    }

    public View.OnClickListener getFeedBackBtnListener() {
        return feedBackBtnListener;
    }
}
