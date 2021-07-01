package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityResultShowBinding;
import com.example.pestidentificationapp.model.Pest;

public class LibraryShowActivity extends AppCompatActivity {

    private ActivityResultShowBinding resultShowBinding;
    private Pest pest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_show);
    }

    private void getData(){

    }

    private void initToolbar() {
        resultShowBinding.resultShowToolbar.setTitle(pest.getName());
        setSupportActionBar(resultShowBinding.resultShowToolbar);
        resultShowBinding.resultShowToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LibraryShowActivity.this.finish();
            }
        });
    }
}
