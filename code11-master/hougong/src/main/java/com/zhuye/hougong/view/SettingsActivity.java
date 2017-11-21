package com.zhuye.hougong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.weidgt.MyToolBar2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {


    @BindView(R.id.setting_toobar)
    MyToolBar2 settingToobar;
    @BindView(R.id.stting_bangding)
    ImageView sttingBangding;
    @BindView(R.id.stting_heimingdan)
    ImageView sttingHeimingdan;
    @BindView(R.id.stting_yinsehn)
    ImageView sttingYinsehn;
    @BindView(R.id.stting_feedback)
    ImageView sttingFeedback;
    @BindView(R.id.setting_qingchu)
    TextView settingQingchu;
    @BindView(R.id.setting_qingchuhuancun)
    TextView settingQingchuhuancun;
    @BindView(R.id.stting_guanyu)
    ImageView sttingGuanyu;
    @BindView(R.id.setting_logout)
    TextView settingLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.stting_bangding, R.id.stting_heimingdan, R.id.stting_yinsehn, R.id.stting_feedback, R.id.setting_qingchu, R.id.setting_qingchuhuancun, R.id.stting_guanyu, R.id.setting_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stting_bangding:
                break;
            case R.id.stting_heimingdan:
                break;
            case R.id.stting_yinsehn:
                onYinShen();
                
                break;
            case R.id.stting_feedback:
                startActivity(new Intent(SettingsActivity.this,FeedBackActivity.class));
                break;
            case R.id.setting_qingchu:
                break;
            case R.id.setting_qingchuhuancun:
                break;
            case R.id.stting_guanyu:
                break;
            case R.id.setting_logout:
                break;
        }
    }

    //处理隐身的逻辑
    private void onYinShen() {

    }
}
