package com.zhuye.hougong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zzzy on 2017/11/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        initview();
        initData();
        initListener();
    }

    private void initListener() {
    }

    private void initData() {
    }

    protected void initview() {
    }

    protected abstract int getResId();
}
