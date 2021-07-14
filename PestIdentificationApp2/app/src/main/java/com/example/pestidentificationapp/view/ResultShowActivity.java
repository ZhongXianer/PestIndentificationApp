package com.example.pestidentificationapp.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.R;
import com.example.pestidentificationapp.databinding.ActivityResultShowBinding;
import com.example.pestidentificationapp.model.IdentificationResultShow;
import com.example.pestidentificationapp.model.MyPhoto;
import com.example.pestidentificationapp.model.response.ResponsePossibility;
import com.example.pestidentificationapp.other.Event;
import com.example.pestidentificationapp.other.Util;
import com.example.pestidentificationapp.viewModel.ResultShowViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 结果展示界面
 */
@Route(path = "/test/res_show_activity")
public class ResultShowActivity extends AppCompatActivity {

    private ActivityResultShowBinding resultShowBinding;
    private ResultShowViewModel resultShowViewModel;
    private MutableLiveData<List<IdentificationResultShow>> resultShow = new MutableLiveData<>();
    private List<IdentificationResultShow> list = new ArrayList<>();
    private int toSave = 0;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_result_show);
        resultShowViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(ResultShowViewModel.class);
        resultShowBinding.setResultShowViewModel(resultShowViewModel);
        initToolBar();
        registerResult();
        registerNetworkError();
        getPossibleResults(getPhotoFromMainAct());
        setSaveBtnListener();
    }

    private void registerResult() {
        resultShow.observe(this, new Observer<List<IdentificationResultShow>>() {
            @Override
            public void onChanged(List<IdentificationResultShow> identificationResultShows) {
                ObservableList<IdentificationResultShow> resultShowObservableList = new ObservableArrayList<>();
                Collections.sort(identificationResultShows);
                resultShowObservableList.addAll(identificationResultShows);
                resultShowBinding.setResultList(resultShowObservableList);
            }
        });
    }

    private void registerNetworkError() {
        resultShowViewModel.getNetworkError().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                showToast(stringEvent.peekContent());
            }
        });
    }

    private void getPossibleResults(MyPhoto photo) {
        resultShowViewModel.uploadPicture(photo).observe(this, new Observer<List<ResponsePossibility>>() {
            @Override
            public void onChanged(List<ResponsePossibility> responsePossibilities) {
                for (int i = 0; i < responsePossibilities.size(); i++) {
                    ResponsePossibility p = responsePossibilities.get(i);
                    switch (Util.judgeLatinName(p.getLatinName())) {
                        case 0:
                            list.add(new IdentificationResultShow(i + 1, "未知卵", Double.toString(p.getPossibility())));
                            resultShow.postValue(list);
                            break;
                        case 1:
                            list.add(new IdentificationResultShow(i + 1, "未知幼虫", Double.toString(p.getPossibility())));
                            resultShow.postValue(list);
                            break;
                        case 2:
                            list.add(new IdentificationResultShow(i + 1, "天牛科幼虫", Double.toString(p.getPossibility())));
                            resultShow.postValue(list);
                            break;
                        case 3:
                            ResponsePossibility possibility =
                                    new ResponsePossibility(Util.parseBabyLatinName(p.getLatinName()), p.getPossibility());
                            getPestInformation(possibility, i + 1);
                            break;
                        case 4:
                            getPestInformation(p, i + 1);
                            break;
                        default:
                            break;
                    }
                }
                resultShowBinding.processGif.setVisibility(View.INVISIBLE);
                resultShowBinding.resultLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showChooseDialog() {
        String[] array = new String[list.size()];
        int i = 0;
        for (IdentificationResultShow identificationResultShow : list) {
            array[i] = identificationResultShow.getPestName();
            i++;
        }
        AlertDialog.Builder chooseDialog = new AlertDialog.Builder(ResultShowActivity.this);
        chooseDialog.setIcon(R.mipmap.ic_launcher);
        chooseDialog.setTitle("选择一种结果保存并退出");
        chooseDialog.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                toSave = i;
            }
        });
        chooseDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resultShowViewModel.saveResult(list.get(toSave), url);
                finish();
            }
        });
        chooseDialog.show();
    }

    private void getPestInformation(ResponsePossibility p, final int order) {
        resultShowViewModel.getPestInformation(p).observe(this, new Observer<IdentificationResultShow>() {
            @Override
            public void onChanged(IdentificationResultShow identificationResultShow) {
                identificationResultShow.setOrder(order);
                list.add(identificationResultShow);
                resultShow.postValue(list);
            }
        });
    }

    private void setSaveBtnListener() {
        resultShowBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooseDialog();
            }
        });
    }

    /**
     * 获取用户上传的图片,并先更新UI
     */
    private MyPhoto getPhotoFromMainAct() {
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        serializationService.init(this);
        MyPhoto myPhoto = serializationService.parseObject(getIntent().getStringExtra("photo"), MyPhoto.class);
        resultShowBinding.setPicture(myPhoto.getPath());
        url = myPhoto.getUri();
        return myPhoto;
    }

    /**
     * 初始化toolbar
     */
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

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
