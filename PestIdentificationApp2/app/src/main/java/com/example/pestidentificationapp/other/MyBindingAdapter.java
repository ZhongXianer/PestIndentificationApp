package com.example.pestidentificationapp.other;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class MyBindingAdapter {

    @BindingAdapter(value = "img")
    public static void loadImage(ImageView imageView, String url) {
        if (url != null)
            Glide.with(imageView.getContext()).load(url).into(imageView);
        Log.d("请求", "loadImage: " + url);
    }

    @BindingAdapter(value = {"layoutId", "list", "itemClickListener"}, requireAll = false)
    public static <T> void setAdapter(BindRecyclerView view,
                                      @LayoutRes final int layoutId,
                                      ObservableList<T> list,
                                      OnRecyclerItemClickListener itemClickListener) {
        if (list == null) {
            return;
        }
        if (view.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    view.getContext(), LinearLayoutManager.VERTICAL, false
            );
            view.setLayoutManager(layoutManager);
        }

        BindAdapter<T> bindAdapter = (BindAdapter<T>) view.getAdapter();
        if (bindAdapter == null || !Objects.equals(bindAdapter.getObservableList(), list)) {
            //创建设配器
            Log.d("list调试", "setAdapter: 创建适配器");
            bindAdapter = new BindAdapter<T>(view, list) {
                @Override
                public int onCreateViewHolderLayoutId() {
                    return layoutId;
                }
            };
        }
        //设置适配器
        view.setAdapter(bindAdapter);

        //item点击事件
        if (itemClickListener != null)
            view.setOnRecyclerItemClickListener(itemClickListener);
    }
}
