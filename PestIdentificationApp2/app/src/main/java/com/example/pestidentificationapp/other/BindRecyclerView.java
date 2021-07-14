package com.example.pestidentificationapp.other;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class BindRecyclerView extends RecyclerView {

    public BindRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        BindAdapter bindAdapter = getBindAdapter();
        if (bindAdapter != null && onRecyclerItemClickListener != null) {
            bindAdapter.setItemClickListener(onRecyclerItemClickListener);
        }
    }

    public BindAdapter getBindAdapter() {
        if (getAdapter() instanceof BindAdapter) {
            return (BindAdapter) getAdapter();
        }
        return null;
    }
}
