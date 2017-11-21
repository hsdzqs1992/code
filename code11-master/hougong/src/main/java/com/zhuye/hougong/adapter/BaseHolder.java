package com.zhuye.hougong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zzzy on 2017/11/21.
 */

public class BaseHolder extends RecyclerView.ViewHolder {

    private View  rootView;

    public BaseHolder(View itemView) {
        super(itemView);
        rootView = itemView;
    }

    public <V extends View> V getView(int ResId){

        return (V)rootView.findViewById(ResId);
    }

}
