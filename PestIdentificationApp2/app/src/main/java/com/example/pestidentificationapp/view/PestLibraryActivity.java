package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityPestLibraryBinding;

public class PestLibraryActivity extends AppCompatActivity {

    ActivityPestLibraryBinding pestLibraryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pestLibraryBinding = DataBindingUtil.setContentView(this, R.layout.activity_pest_library);
    }
}
