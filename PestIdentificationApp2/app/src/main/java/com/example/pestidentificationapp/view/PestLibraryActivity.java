package com.example.pestidentificationapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityPestLibraryBinding;
import com.example.pestidentificationapp.model.Pest;
import com.example.pestidentificationapp.other.Event;
import com.example.pestidentificationapp.viewModel.PestLibraryViewModel;

import java.util.List;
import java.util.Objects;

/**
 * 有害昆虫信息库
 */
@Route(path = "/test/pest_lib_activity")
public class PestLibraryActivity extends AppCompatActivity {

    ActivityPestLibraryBinding pestLibraryBinding;
    private PestLibraryViewModel pestLibraryViewModel = new PestLibraryViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pestLibraryBinding = DataBindingUtil.setContentView(this, R.layout.activity_pest_library);
        pestLibraryBinding.setPestLibraryViewModel(pestLibraryViewModel);
        initToolBar();
        getPestList();
    }

    private void getPestList() {
        registerError();
        pestLibraryViewModel.getPestListFromInt().observe(this, new Observer<List<Pest>>() {
            @Override
            public void onChanged(List<Pest> pests) {
                ObservableList<Pest> list = new ObservableArrayList<>();
                list.addAll(pests);
                pestLibraryBinding.setPestList(list);
            }
        });
    }

    private void registerError() {
        pestLibraryViewModel.getNetworkError().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                showToast(stringEvent.peekContent());
            }
        });
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

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
