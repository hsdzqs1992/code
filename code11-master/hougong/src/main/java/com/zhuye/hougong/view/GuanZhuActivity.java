package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.BaseHolder;
import com.zhuye.hougong.adapter.GuanZhuAdapter;
import com.zhuye.hougong.weidgt.MyToolBar2;

import java.util.ArrayList;
import java.util.List;

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

        List<String> data = new ArrayList<>();
        for(int i=0;i <100;i++){
            data.add("sdfasdf"+i);
        }

        final GuanZhuAdapter guanZhuAdapter = new GuanZhuAdapter(this,data);
        guanzhuRecycleview.setAdapter(guanZhuAdapter);
        guanzhuRecycleview.setLayoutManager(new LinearLayoutManager(this));

        guanZhuAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(GuanZhuActivity.this,"haha",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
