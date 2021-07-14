package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityInformationBinding;
import com.example.pestidentificationapp.other.ARouterUtil;

@Route(path = "/test/information_activity")
public class InformationActivity extends AppCompatActivity {

    ActivityInformationBinding informationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informationBinding = DataBindingUtil.setContentView(this, R.layout.activity_information);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        informationBinding.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterUtil.FeesBackAct).navigation();
                finish();
            }
        });
    }
}
