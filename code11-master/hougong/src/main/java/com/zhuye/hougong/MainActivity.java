package com.zhuye.hougong;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.zhuye.hougong.fragment.FindFragment;
import com.zhuye.hougong.fragment.HomeFragment;
import com.zhuye.hougong.fragment.MeFragment;
import com.zhuye.hougong.fragment.MessageFragment;
import com.zhuye.hougong.fragment.PaiHangFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_framelayout)
    FrameLayout homeFramelayout;
    @BindView(android.R.id.content)
    FrameLayout content;
    @BindView(R.id.home_fragmenttabhost)
    FragmentTabHost homeFragmenttabhost;

    private List<String> tabnames = new ArrayList<>();
    private  List<Class> tabfragment = new ArrayList<>();
    private  List<Integer> tabimage = new ArrayList<>();

    //private String[]  tabnames = new String("");
    public Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabnames.add("首页");
        tabnames.add("排行榜");
        tabnames.add("发现");
        tabnames.add("消息");
        tabnames.add("我的");

        tabfragment.add(HomeFragment.class);
        tabfragment.add( PaiHangFragment.class);
        tabfragment.add( FindFragment.class);
        tabfragment.add( MessageFragment.class);
        tabfragment.add( MeFragment.class);


        tabimage.add(R.string.video);
        tabimage.add(R.string.paihang);
        tabimage.add(R.string.find);
        tabimage.add(R.string.message);
        tabimage.add(R.string.me);

        //初始化view
        initView();

    }



    private void initView() {
        //初始化tabhost
        initTab();
    }

    private void initTab() {

        homeFragmenttabhost.setup(this,getSupportFragmentManager(),R.id.home_framelayout);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"iconfont.ttf");
        for(int i = 0; i<5;i++){
            View v= View.inflate(this,R.layout.tabview,null);
            TextView tabiv = v.findViewById(R.id.tab_view);
            //tabiv.setImageResource(R.mipmap.ic_launcher);
            tabiv.setText(tabimage.get(i));
            //tabiv.setTextSize();
            tabiv.setTypeface(typeface);
            TextView tv = v.findViewById(R.id.tab_tv);
            tv.setText(tabnames.get(i));

            TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec(tabnames.get(i)).setIndicator(v);
            homeFragmenttabhost.addTab(view, tabfragment.get(i),null);
        }

//        View v= View.inflate(this,R.layout.im,null);
//        TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec("首页").setIndicator(v);
//        homeFragmenttabhost.addTab(view, HomeFragment.class,null);
    }
}
