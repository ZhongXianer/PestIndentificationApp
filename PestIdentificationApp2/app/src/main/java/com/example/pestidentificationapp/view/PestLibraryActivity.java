package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityPestLibraryBinding;
import com.example.pestidentificationapp.viewModel.PestLibraryViewModel;

import java.util.Objects;

/**
 * 有害昆虫信息库
 */
public class PestLibraryActivity extends AppCompatActivity {

    ActivityPestLibraryBinding pestLibraryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pestLibraryBinding = DataBindingUtil.setContentView(this, R.layout.activity_pest_library);
        pestLibraryBinding.setPestLibraryViewModel(new PestLibraryViewModel());
        initToolBar();
    }

    private void initToolBar() {
        pestLibraryBinding.pestLibraryToolbar.setTitle("有害昆虫识别库");
        pestLibraryBinding.pestLibraryToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(pestLibraryBinding.pestLibraryToolbar);
        pestLibraryBinding.pestLibraryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PestLibraryActivity.this.finish();
            }
        });
    }
}
