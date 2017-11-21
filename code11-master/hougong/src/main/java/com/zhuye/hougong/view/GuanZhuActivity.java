package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.zhuye.hougong.R;
import com.zhuye.hougong.weidgt.MyToolBar2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuanZhuActivity extends AppCompatActivity {

    @BindView(R.id.guanzhu_toolbar)
    MyToolBar2 guanzhuToolbar;
    @BindView(R.id.guanzhu_recycleview)
    RecyclerView guanzhuRecycleview;
    @BindView(R.id.guanzhu_refesh)
    MaterialRefreshLayout guanzhuRefesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_zhu);
        ButterKnife.bind(this);

        guanzhuToolbar.titles.setText(R.string.guanzhu);



    }
}
