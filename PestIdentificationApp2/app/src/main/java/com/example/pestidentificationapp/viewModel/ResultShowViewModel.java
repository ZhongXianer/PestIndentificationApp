package com.example.pestidentificationapp.viewModel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.model.IdentificationResultShow;
import com.example.pestidentificationapp.model.MyPhoto;
import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.network.NetWorkUtil;
import com.example.pestidentificationapp.other.Event;
import com.example.pestidentificationapp.other.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultShowViewModel extends ViewModel {

    private MutableLiveData<Event<String>> networkError = new MutableLiveData<>();
    private MutableLiveData<IdentificationResultShow> result = new MutableLiveData<>();

    public ResultShowViewModel() {

    }

    /**
     * 上传图片到服务器
     *
     * @param photo 要上传的图片
     */
    public MutableLiveData<IdentificationResultShow> upload(final MyPhoto photo) {
        result = new MutableLiveData<>();
        File file = new File(photo.getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", photo.getName(), requestBody);
        Call<Pest> call = NetWorkUtil.getBaseService().testImageUpload(f);
        call.enqueue(new Callback<Pest>() {
            @Override
            public void onResponse(@NotNull Call<Pest> call, @NotNull Response<Pest> response) {
                Pest pest = response.body();
                assert pest != null;
                IdentificationResultShow resultShow = new IdentificationResultShow(photo.getUri(),
                        pest.getName(), pest.getLatinName(), pest.getPlant(), pest.getArea());
                result.postValue(resultShow);
            }

            @Override
            public void onFailure(@NotNull Call<Pest> call, @NotNull Throwable t) {
                networkError.postValue(new Event<>(t.getMessage()));
            }
        });
        return result;
    }

    public void saveResult(SharedPreferences sharedPreferences) {
        IdentificationResultShow resultShow = result.getValue();
        Gson gson = new Gson();
        List<HistoryIdentificationResult> results;
        HistoryIdentificationResult historyIdentificationResult =
                new HistoryIdentificationResult(resultShow.getPestUri(),
                        resultShow.getPestName(),
                        resultShow.getLatinName(),
                        Util.getDate(),
                        Util.getTime());
        String s = sharedPreferences.getString(Util.SavedListName, null);
        if (null == s) {
            results = new ArrayList<>();
            results.add(historyIdentificationResult);
        } else {
            Type type = new TypeToken<List<HistoryIdentificationResult>>() {
            }.getType();
            results = gson.fromJson(s, type);
            results.add(historyIdentificationResult);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String data = gson.toJson(results);
        editor.putString(Util.SavedListName, data);
        editor.apply();
    }

    public LiveData<Event<String>> getNetworkError() {
        return networkError;
    }

}
