package com.example.pestidentificationapp.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityResultShowBinding;
import com.example.pestidentificationapp.model.IdentificationResultShow;

/**
 * 结果展示界面
 */
public class ResultShowActivity extends AppCompatActivity {

    private ActivityResultShowBinding resultShowBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_show);
        initToolBar();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        String pestUri = intent.getStringExtra("photoUri");
        IdentificationResultShow identificationResultShow =
                new IdentificationResultShow(pestUri, "害虫", "拉丁名", "杨树，柳树", "中国东北部");
        resultShowBinding.setResult(identificationResultShow);
    }

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
}
