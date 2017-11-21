package com.zhuye.hougong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseHolder> {

    Context conn;
    List<T> data;
    public BaseRecycleAdapter(Context conn,List<T> data) {
        this.conn= conn;
        this.data= data;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(conn,getResId(),null);

        BaseHolder baseholder = new BaseHolder(view);
        return baseholder;
    }

    protected abstract int getResId();


    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        conver(holder,position);
    }

    protected abstract void conver(BaseHolder holder, int position);


    @Override
    public int getItemCount() {
        return data.size();
    }
}
