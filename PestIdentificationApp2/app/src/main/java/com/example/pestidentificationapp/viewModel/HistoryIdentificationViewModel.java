package com.example.pestidentificationapp.viewModel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.example.pestidentificationapp.model.IdentificationResult;

public class HistoryIdentificationViewModel extends ViewModel {

    private ObservableList<IdentificationResult> identificationResults = new ObservableArrayList<>();

    public HistoryIdentificationViewModel() {
        for (int i = 0; i < 10; i++) {
            IdentificationResult identificationResult = new IdentificationResult();
            identificationResult.setPestName("害虫");
            identificationResult.setDate("2021/6/28");
            identificationResult.setTime("17:12:56");
            identificationResults.add(identificationResult);
        }
        Log.d("list", "HistoryIdentificationViewModel: "+identificationResults.size());
    }

    public ObservableList<IdentificationResult> getIdentificationResults() {
        return this.identificationResults;
    }
}
