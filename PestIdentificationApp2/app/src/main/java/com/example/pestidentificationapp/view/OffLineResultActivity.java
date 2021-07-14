package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityOffLineResultBinding;
import com.example.pestidentificationapp.model.HistoryIdentificationResult;
import com.example.pestidentificationapp.model.MyPhoto;
import com.example.pestidentificationapp.viewModel.OffLineResultViewModel;

import java.util.concurrent.ExecutionException;

@Route(path = "/test/offline_result_activity")
public class OffLineResultActivity extends AppCompatActivity {

    private ActivityOffLineResultBinding offLineResultBinding;
    private OffLineResultViewModel offLineResultViewModel;

    private MyPhoto myPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offLineResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_off_line_result);
        offLineResultViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(OffLineResultViewModel.class);
        offLineResultBinding.setOfflineViewModel(offLineResultViewModel);
        initToolbar();
        try {
            getPhotoFromMainAct();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void predict() throws ExecutionException, InterruptedException {
        offLineResultViewModel.predict(myPhoto.getUri()).observe(this, new Observer<HistoryIdentificationResult>() {
            @Override
            public void onChanged(HistoryIdentificationResult historyIdentificationResult) {
                offLineResultBinding.setResult(historyIdentificationResult);
                offLineResultBinding.offlineProcessGif.setVisibility(View.INVISIBLE);
                offLineResultBinding.offlineResultLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getPhotoFromMainAct() throws ExecutionException, InterruptedException {
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        serializationService.init(this);
        myPhoto = serializationService.parseObject(getIntent().getStringExtra("photo"), MyPhoto.class);
        offLineResultBinding.setPicture(myPhoto.getUri());
        predict();
    }

    private void initToolbar() {
        offLineResultBinding.offlineToolbar.setTitle("离线识别结果");
        offLineResultBinding.offlineToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(offLineResultBinding.offlineToolbar);
        offLineResultBinding.offlineToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
