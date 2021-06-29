package com.example.pestidentificationapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pestidentificationapp.BR;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityHistoryIdentificationBinding;
import com.example.pestidentificationapp.model.IdentificationResult;
import com.example.pestidentificationapp.other.BindAdapter;
import com.example.pestidentificationapp.viewModel.HistoryIdentificationViewModel;

import java.util.Objects;

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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
