package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityFeedBackBinding;

@Route(path = "/test/feed_back_activity")
public class FeedBackActivity extends AppCompatActivity {

    private ActivityFeedBackBinding feedBackBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedBackBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed_back);
        initToolbar();
        initCommitBtn();
    }

    private void initToolbar() {
        feedBackBinding.feedbackToolbar.setTitle("联系我们");
        feedBackBinding.feedbackToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(feedBackBinding.feedbackToolbar);
        feedBackBinding.feedbackToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initCommitBtn() {
        feedBackBinding.feedbackCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FeedBackActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
