package com.example.pestidentificationapp.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BindAdapter<T> extends RecyclerView.Adapter<BindAdapter.BindHolder> {

    private List<T> list = new ArrayList<>();
    private ObservableList<T> observableList;


    private OnRecyclerItemClickListener itemClickListener;


    public BindAdapter(final RecyclerView view, ObservableList<T> observableList) {
        this.observableList = observableList;
        this.list.addAll(observableList);

        //添加数据监听
        observableList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<T>>() {
            @Override
            public void onChanged(ObservableList<T> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<T> sender, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(final ObservableList<T> sender, final int positionStart, final int itemCount) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        list.add(positionStart, sender.get(positionStart));
                        notifyItemRangeInserted(positionStart, itemCount);
                        view.scrollToPosition(positionStart);
                    }
                });
            }

            @Override
            public void onItemRangeMoved(ObservableList<T> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<T> sender, final int positionStart, final int itemCount) {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if (itemCount == 1) {
                            list.remove(positionStart);
                            notifyItemRangeRemoved(positionStart, itemCount);
                            notifyItemRangeChanged(positionStart, getItemCount() - positionStart, new Object());
                        } else {
                            list.clear();
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    public OnRecyclerItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<T> observableList) {
        this.observableList = observableList;
    }

    public void setItemClickListener(OnRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public abstract int onCreateViewHolderLayoutId();


    @NonNull
    @Override
    public BindHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        onCreateViewHolderLayoutId(), parent, false);
        return new BindAdapter.BindHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindHolder holder, final int position) {
        holder.bind(this.list.get(holder.getBindingAdapterPosition()),
                new OnRecyclerItemClickListener() {
                    @Override
                    public void onRecyclerItemClick(Object item) {
                        if (itemClickListener != null)
                            itemClickListener.onRecyclerItemClick(item);
                    }
                });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null)
                    itemClickListener.onRecyclerItemClick(getItem(position));
            }
        });
    }

    public T getItem(int position) {
        if (position < this.list.size()) {
            return this.list.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 自定义viewHolder
     */
    public static class BindHolder extends RecyclerView.ViewHolder {

        ViewDataBinding mBinding;

        public BindHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(Object obj, OnRecyclerItemClickListener onRecyclerItemClickListener) {
            this.mBinding.setVariable(BR.item, obj);
            this.mBinding.setVariable(BR.itemClickListener, onRecyclerItemClickListener);
        }
    }
}
