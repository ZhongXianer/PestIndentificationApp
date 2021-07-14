package com.example.pestidentificationapp.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.other.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HistoryIdentificationViewModel extends AndroidViewModel {

    private ObservableList<HistoryIdentificationResult> historyIdentificationResults = new ObservableArrayList<>();

    public HistoryIdentificationViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<HistoryIdentificationResult>> getListFromMemory() {
        SharedPreferences sharedPreferences=getApplication().getSharedPreferences(Util.ShpName, Context.MODE_PRIVATE);
        String s = sharedPreferences.getString(Util.SavedListName, null);
        Log.d("save", "getListFromMemory: "+s);
        MutableLiveData<List<HistoryIdentificationResult>> list = new MutableLiveData<>();
        if (null != s) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<HistoryIdentificationResult>>() {
            }.getType();
            List<HistoryIdentificationResult> savedList = gson.fromJson(s, type);
            list.postValue(savedList);
        }
        return list;
    }

    public ObservableList<HistoryIdentificationResult> getHistoryIdentificationResults() {
        return this.historyIdentificationResults;
    }
}
