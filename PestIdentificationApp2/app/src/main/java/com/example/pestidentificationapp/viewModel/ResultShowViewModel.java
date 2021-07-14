package com.example.pestidentificationapp.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.model.IdentificationResultShow;
import com.example.pestidentificationapp.model.MyPhoto;
import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.model.response.ResponsePossibility;
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
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultShowViewModel extends AndroidViewModel {

    private MutableLiveData<Event<String>> networkError = new MutableLiveData<>();
    private static String TAG = "network";

    public ResultShowViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<List<ResponsePossibility>> uploadPicture(MyPhoto photo) {
        final MutableLiveData<List<ResponsePossibility>> result = new MutableLiveData<>();
        File file = new File(photo.getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", photo.getName(), requestBody);
        Call<List<ResponsePossibility>> call = NetWorkUtil.getBaseService().uploadImage(f);
        call.enqueue(new Callback<List<ResponsePossibility>>() {
            @Override
            public void onResponse(@NotNull Call<List<ResponsePossibility>> call, @NotNull Response<List<ResponsePossibility>> response) {
                List<ResponsePossibility> possibilities = response.body();
                result.postValue(possibilities);
            }

            @Override
            public void onFailure(@NotNull Call<List<ResponsePossibility>> call, @NotNull Throwable t) {
                Log.d("network", "onFailure: percentage|" + t.getMessage());
                networkError.postValue(new Event<>(t.getMessage()));
            }
        });
        return result;
    }

    /**
     * 获取拉丁学名对应的信息
     *
     * @return 昆虫全部信息
     */
    public MutableLiveData<IdentificationResultShow> getPestInformation(final ResponsePossibility p) {
        String latinName = p.getLatinName();
        final MutableLiveData<IdentificationResultShow> result = new MutableLiveData<>();
        Call<Pest> call = NetWorkUtil.getBaseService().getPestInformation(latinName);
        call.enqueue(new Callback<Pest>() {
            @Override
            public void onResponse(@NotNull Call<Pest> call, @NotNull Response<Pest> response) {
                Log.d(TAG, "onResponse: 返回成虫信息");
                Pest pest = response.body();
                assert pest != null;
                IdentificationResultShow i =
                        new IdentificationResultShow(pest, Double.toString(p.getPossibility()));
                result.postValue(i);
            }

            @Override
            public void onFailure(@NotNull Call<Pest> call, @NotNull Throwable t) {
                Log.d("network", "onFailure: information|" + Objects.requireNonNull(t.getMessage()));
                networkError.postValue(new Event<>(t.getMessage()));
            }
        });
        return result;
    }


    public void saveResult(IdentificationResultShow resultShow, String uri) {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(Util.ShpName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        List<HistoryIdentificationResult> results;
        HistoryIdentificationResult historyIdentificationResult =
                new HistoryIdentificationResult(uri,
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
