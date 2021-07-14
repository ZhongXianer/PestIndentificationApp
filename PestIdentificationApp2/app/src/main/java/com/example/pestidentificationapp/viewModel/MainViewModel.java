package com.example.pestidentificationapp.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.pestidentificationapp.other.ARouterUtil;

public class MainViewModel extends AndroidViewModel {

    private View.OnClickListener hisItenBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance().build(ARouterUtil.HistoryIdentificationAct).navigation();
        }
    };

    private View.OnClickListener pestLibBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance().build(ARouterUtil.PestLibraryAct).navigation();
        }
    };

    private View.OnClickListener feedBackBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance().build(ARouterUtil.InformationAct).navigation();
        }
    };

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public View.OnClickListener getHisItenBtnListener() {
        return hisItenBtnListener;
    }

    public View.OnClickListener getPestLibBtnListener() {
        return pestLibBtnListener;
    }

    public View.OnClickListener getFeedBackBtnListener() {
        return feedBackBtnListener;
    }
}
