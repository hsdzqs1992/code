package com.zhuye.hougong.adapter;

import android.content.Context;

import com.zhuye.hougong.R;

import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class GuanZhuAdapter extends BaseRecycleAdapter {

    public GuanZhuAdapter(Context conn, List data) {
        super(conn, data);
    }


    @Override
    protected int getResId() {
        return R.layout.guanzhu_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        //holder.getView(R.id.guanzhu_item_tv)
    }


}
