package com.example.pestidentificationapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityResultShowBinding;
import com.example.pestidentificationapp.model.IdentificationResultShow;
import com.example.pestidentificationapp.model.MyPhoto;
import com.example.pestidentificationapp.other.Event;
import com.example.pestidentificationapp.other.Util;
import com.example.pestidentificationapp.viewModel.ResultShowViewModel;

/**
 * 结果展示界面
 */
@Route(path = "/test/res_show_activity")
public class ResultShowActivity extends AppCompatActivity {

    private ActivityResultShowBinding resultShowBinding;
    private ResultShowViewModel resultShowViewModel = new ResultShowViewModel();
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_show);
        resultShowBinding.setResultShowViewModel(resultShowViewModel);
        sharedPreferences = getSharedPreferences(Util.ShpName, Context.MODE_PRIVATE);
        initToolBar();
        getResult(getPhotoFromMainAct());
        setSaveBtnListener();
    }

    private void registerNetworkError() {
        resultShowViewModel.getNetworkError().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                showToast(stringEvent.peekContent());
            }
        });
    }

    /**
     * 获取识别结果
     *
     * @param photo 用户上传的图片
     */
    private void getResult(MyPhoto photo) {
        registerNetworkError();
        resultShowViewModel.upload(photo).observe(this, new Observer<IdentificationResultShow>() {
            @Override
            public void onChanged(IdentificationResultShow identificationResultShow) {
                resultShowBinding.processGif.setVisibility(View.INVISIBLE);
                resultShowBinding.resultLayout.setVisibility(View.VISIBLE);
                resultShowBinding.setResult(identificationResultShow);
            }
        });
    }

    private void setSaveBtnListener() {
        resultShowBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultShowViewModel.saveResult(sharedPreferences);
                finish();
            }
        });
    }

    /**
     * 获取用户上传的图片,并先更新UI
     */
    private MyPhoto getPhotoFromMainAct() {
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        serializationService.init(this);
        MyPhoto myPhoto = serializationService.parseObject(getIntent().getStringExtra("photo"), MyPhoto.class);
        resultShowBinding.setPicture(myPhoto.getUri());
        return myPhoto;
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        resultShowBinding.resultShowToolbar.setTitle("识别结果");
        resultShowBinding.resultShowToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(resultShowBinding.resultShowToolbar);
        resultShowBinding.resultShowToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultShowActivity.this.finish();
            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
