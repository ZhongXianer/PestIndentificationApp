package com.example.pestidentificationapp.other;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pestidentificationapp.R;

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
            Log.d(String.valueOf(R.string.recyclerview), "getBindAdapter: instance of");
            return (BindAdapter) getAdapter();
        }
        return null;
    }
}
