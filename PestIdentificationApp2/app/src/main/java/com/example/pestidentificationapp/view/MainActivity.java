package com.example.pestidentificationapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityMainBinding;
import com.example.pestidentificationapp.other.GlideEngine;
import com.example.pestidentificationapp.viewModel.MainViewModel;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    private int requestCode_easyPhotos = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setMainViewModel(new MainViewModel());
        btnClickListener();
    }

    /**
     * 注册点击事件
     */
    private void btnClickListener() {
        mainBinding.addIdentificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDialog();
            }
        });
        mainBinding.historyIdentificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryIdentificationActivity.class);
                startActivity(intent);
            }
        });
        mainBinding.pestLibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PestLibraryActivity.class);
                startActivity(intent);
            }
        });
        mainBinding.feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    /**
     * 弹出选择框，选择拍照或者从相册中选择
     */
    private void popDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.way_title)
                .setItems(R.array.ways, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0)
                            camera();
                        else chooseFromAlbum();
                    }
                }).show();
    }

    /**
     * 相机
     */
    private void camera() {
        EasyPhotos.createCamera(this, false)
                .setFileProviderAuthority("com.example.pestidentificationapp.fileprovider")
                .start(requestCode_easyPhotos);
    }

    /**
     * 从相册选择
     */
    private void chooseFromAlbum() {
        EasyPhotos.createAlbum(this, false, true, GlideEngine.getInstance())
                .start(requestCode_easyPhotos);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode_easyPhotos && resultCode == RESULT_OK) {

            ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
            mainBinding.getMainViewModel().upload(resultPhotos.get(0));
        }
    }
}
