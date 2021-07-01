package com.example.pestidentificationapp.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityHistoryIdentificationBinding;
import com.example.pestidentificationapp.viewModel.HistoryIdentificationViewModel;

/**
 * 历史识别记录
 */
public class HistoryIdentificationActivity extends AppCompatActivity {

    ActivityHistoryIdentificationBinding historyIdentificationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyIdentificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_identification);
        historyIdentificationBinding.setHistoryIdentificationViewModel(new HistoryIdentificationViewModel());
        initToolBar();
        initRecyclerView();
    }

    private void initRecyclerView() {
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
