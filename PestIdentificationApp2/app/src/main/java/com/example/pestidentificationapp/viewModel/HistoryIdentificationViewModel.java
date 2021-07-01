package com.example.pestidentificationapp.viewModel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.HistoryIdentificationResult;

public class HistoryIdentificationViewModel extends ViewModel {

    private ObservableList<HistoryIdentificationResult> historyIdentificationResults = new ObservableArrayList<>();

    public HistoryIdentificationViewModel() {
        for (int i = 0; i < 10; i++) {
            HistoryIdentificationResult historyIdentificationResult = new HistoryIdentificationResult();
            historyIdentificationResult.setPestName("害虫");
            historyIdentificationResult.setDate("2021/6/28");
            historyIdentificationResult.setTime("17:12:56");
            historyIdentificationResults.add(historyIdentificationResult);
        }
        Log.d("list", "HistoryIdentificationViewModel: "+ historyIdentificationResults.size());
    }

    public ObservableList<HistoryIdentificationResult> getHistoryIdentificationResults() {
        return this.historyIdentificationResults;
    }
}
