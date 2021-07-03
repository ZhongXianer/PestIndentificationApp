package com.example.pestidentificationapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityHistoryIdentificationBinding;
import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.other.Util;
import com.example.pestidentificationapp.viewModel.HistoryIdentificationViewModel;

import java.util.List;

/**
 * 历史识别记录
 */
@Route(path = "/test/history_iden_activity")
public class HistoryIdentificationActivity extends AppCompatActivity {

    ActivityHistoryIdentificationBinding historyIdentificationBinding;
    HistoryIdentificationViewModel historyIdentificationViewModel = new HistoryIdentificationViewModel();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyIdentificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_identification);
        historyIdentificationBinding.setHistoryIdentificationViewModel(historyIdentificationViewModel);
        sharedPreferences = getSharedPreferences(Util.ShpName, Context.MODE_PRIVATE);
        initToolBar();
        getHistoryResults();
    }

    private void getHistoryResults() {
        historyIdentificationViewModel.getListFromMemory(sharedPreferences).observe(this, new Observer<List<HistoryIdentificationResult>>() {
            @Override
            public void onChanged(List<HistoryIdentificationResult> historyIdentificationResults) {
                ObservableList<HistoryIdentificationResult> list = new ObservableArrayList<>();
                list.addAll(historyIdentificationResults);
                historyIdentificationBinding.setResultList(list);
            }
        });
    }

    private void initToolBar() {
        historyIdentificationBinding.hisIdenToolbar.setTitle("");
        setSupportActionBar(historyIdentificationBinding.hisIdenToolbar);
        historyIdentificationBinding.hisIdenToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryIdentificationActivity.this.finish();
            }
        });
    }
}
