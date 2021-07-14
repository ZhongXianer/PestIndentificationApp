package com.example.pestidentificationapp.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.network.NetWorkUtil;
import com.example.pestidentificationapp.other.ARouterUtil;
import com.example.pestidentificationapp.other.Event;
import com.example.pestidentificationapp.other.OnRecyclerItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PestLibraryViewModel extends ViewModel {

    private OnRecyclerItemClickListener libraryItemClickListener = new OnRecyclerItemClickListener() {
        @Override
        public void onRecyclerItemClick(Object item) {
            Pest pest = (Pest) item;
            ARouter.getInstance().build(ARouterUtil.LibraryShowAct)
                    .withObject("pest", pest)
                    .navigation();
        }
    };
    private MutableLiveData<Event<String>> networkError = new MutableLiveData<>();

    public OnRecyclerItemClickListener getLibraryItemClickListener() {
        return libraryItemClickListener;
    }

    /**
     * 从服务器获取所有昆虫的相关信息
     */
    public MutableLiveData<List<Pest>> getPestListFromInt() {
        final MutableLiveData<List<Pest>> pestList = new MutableLiveData<>();
        Call<List<Pest>> call = NetWorkUtil.getBaseService().getAllPests();
        call.enqueue(new Callback<List<Pest>>() {
            @Override
            public void onResponse(@NotNull Call<List<Pest>> call, @NotNull Response<List<Pest>> response) {
                pestList.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Pest>> call, @NotNull Throwable t) {
                networkError.postValue(new Event<>(t.getMessage()));
                Log.d("network", "onFailure: "+t.getMessage());
                Log.d("network", "onFailure: "+networkError.getValue().peekContent());
            }
        });
        return pestList;
    }

    public MutableLiveData<Event<String>> getNetworkError() {
        return networkError;
    }
}
