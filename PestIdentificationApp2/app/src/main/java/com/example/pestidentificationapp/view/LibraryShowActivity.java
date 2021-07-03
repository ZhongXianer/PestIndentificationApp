package com.example.pestidentificationapp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityLibraryShowBinding;
import com.example.pestidentificationapp.model.Pest;

@Route(path = "/test/lib_show_activity")
public class LibraryShowActivity extends AppCompatActivity {

    private ActivityLibraryShowBinding libraryShowBinding;
    private Pest pest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libraryShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_library_show);
        getData();
        initToolbar();
    }

    private void getData() {
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        serializationService.init(this);
        pest=serializationService.parseObject(getIntent().getStringExtra("pest"), Pest.class);
        libraryShowBinding.setPest(pest);
    }

    private void initToolbar() {
        libraryShowBinding.libraryShowToolbar.setTitle(pest.getName());
        libraryShowBinding.libraryShowToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(libraryShowBinding.libraryShowToolbar);
        libraryShowBinding.libraryShowToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LibraryShowActivity.this.finish();
            }
        });
    }
}
