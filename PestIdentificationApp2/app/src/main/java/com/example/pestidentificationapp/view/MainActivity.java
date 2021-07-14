package com.example.pestidentificationapp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityMainBinding;
import com.example.pestidentificationapp.model.MyPhoto;
import com.example.pestidentificationapp.other.ARouterUtil;
import com.example.pestidentificationapp.other.GlideEngine;
import com.example.pestidentificationapp.viewModel.MainViewModel;
import com.example.pestidentificationapp.viewModel.ResultShowViewModel;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

/**
 * 主界面
 */
@Route(path = "/test/main_activity")
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    MainViewModel mainViewModel;
    private static final int requestCode_online = 255;
    private static final int requestCode_offline = 253;
    private static final int requestCode_online_cropped = 252;
    private static final int requestCode_offline_cropped = 251;

    private MyPhoto myPhoto = new MyPhoto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);
        mainBinding.setMainViewModel(mainViewModel);
        initView();
        btnClickListener();
    }

    private void initView() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    /**
     * 注册点击事件
     */
    private void btnClickListener() {
        //开始识别
        mainBinding.addIdentificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDialog(true);
            }
        });
        mainBinding.offlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                popDialog(false);
                Toast.makeText(MainActivity.this, "正在开发", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 弹出选择框，选择拍照或者从相册中选择
     */
    private void popDialog(final boolean isOnline) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.way_title)
                .setItems(R.array.ways, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0)
                            camera(isOnline);
                        else chooseFromAlbum(isOnline);
                    }
                }).show();
    }

    /**
     * 相机
     *
     * @param isOnline 是否是在线识别
     */
    private void camera(boolean isOnline) {
        int requestCode;
        if (isOnline) requestCode = requestCode_online;
        else requestCode = requestCode_offline;
        EasyPhotos.createCamera(this, false)
                .setFileProviderAuthority("com.example.pestidentificationapp.fileprovider")
                .start(requestCode);
    }

    /**
     * 相册
     *
     * @param isOnline 是否是在线识别
     */
    private void chooseFromAlbum(boolean isOnline) {
        int requestCode;
        if (isOnline) requestCode = requestCode_online;
        else requestCode = requestCode_offline;
        EasyPhotos.createAlbum(this, false, true, GlideEngine.getInstance())
                .start(requestCode);
    }

    private void gotoCrop(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == requestCode_online) {
                assert data != null;
                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                assert resultPhotos != null;
                Photo photo = resultPhotos.get(0);
                myPhoto.setName(photo.name);
                myPhoto.setUri(photo.uri.toString());
                myPhoto.setPath(photo.path);
                ARouter.getInstance().build(ARouterUtil.ResultShowAct)
                        .withObject("photo", myPhoto)
                        .navigation();
//                CropImage.activity(photo.uri)
//                        .start(this);
            }
            if (requestCode == requestCode_offline) {
                assert data != null;
                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                assert resultPhotos != null;
                Photo photo = resultPhotos.get(0);
                myPhoto.setName(photo.name);
                myPhoto.setUri(photo.uri.toString());
                myPhoto.setPath(photo.path);
                ARouter.getInstance().build(ARouterUtil.OffLineResultAct)
                        .withObject("photo", myPhoto)
                        .navigation();
            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                myPhoto.setUri(resultUri.toString());
                ARouter.getInstance().build(ARouterUtil.ResultShowAct)
                        .withObject("photo", myPhoto)
                        .navigation();
            }
        }
//        if (resultCode == RESULT_OK) {
//            assert data != null;
//            ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
//            assert resultPhotos != null;
//            Photo photo = resultPhotos.get(0);
//            if (requestCode == requestCode_online) {
//                CropImage.activity(photo.uri)
//                        .start(this, ResultShowActivity.class);
//            }
//            if (requestCode == requestCode_offline) {
//                CropImage.activity(photo.uri)
//                        .start(this, OffLineResultActivity.class);
//            }
//            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//                Uri resultUri = result.getUri();
//                MyPhoto myPhoto = new MyPhoto(resultUri.toString(), photo.name, photo.path);
//                ARouter.getInstance().build(ARouterUtil.ResultShowAct)
//                        .withObject("photo", myPhoto)
//                        .navigation();
//            }
//
//        }
    }
}
