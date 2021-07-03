package com.example.pestidentificationapp.viewModel;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.databinding.ListChangeRegistry;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.HistoryIdentificationJson;
import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.other.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HistoryIdentificationViewModel extends ViewModel {

    private ObservableList<HistoryIdentificationResult> historyIdentificationResults = new ObservableArrayList<>();

    public HistoryIdentificationViewModel() {
    }

    public MutableLiveData<List<HistoryIdentificationResult>> getListFromMemory(SharedPreferences sharedPreferences) {
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
