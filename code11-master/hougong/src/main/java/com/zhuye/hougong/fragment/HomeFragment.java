package com.zhuye.hougong.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

/**
 * Created by zzzy on 2017/11/20.
 */

public class HomeFragment extends BaseFragment {





    //private MyToolbar myToolbar;
    private ViewPager mviewpager;
    private PagerSlidingTabStrip mTabStrip;
    @Override
    protected void initView() {



        //Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"iconfont.ttf");
        mviewpager = rootView.findViewById(R.id.home_viewpager);
        mTabStrip=rootView.findViewById(R.id.tab_strip);
        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h6));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);
        //myToolbar = rootView.findViewById(R.id.home_toolbar);

        //initToolBar();
//        mviewpager.setAdapter(new HomePagerAdapter(getActivity().getSupportFragmentManager()));
//        myToolbar.tabs.setViewPager(mviewpager);





    }

    private void initToolBar() {

        //myToolbar.homeLeftIcon.setText(R.string.huangguan);
        //myToolbar.homeLeftIcon.setTypeface(((MainActivity)getActivity()).typeface);
        //myyToolbar.homeRightIcon.setText(R.string.loudou);
       // mToolbar.homeRightIcon.setTypeface(((MainActivity)getActivity()).typeface);
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        super.initData();



        OkGo.<String>post("http://192.168.1.102/gong/index.php/home/hall/lunbo")
                .tag(getActivity())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        HomeBanner homeBanner = gson.fromJson(response.body(), HomeBanner.class);
                        Log.d("------",homeBanner.toString());



                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                      //Log.d("------",response.body());
                    }
                });

    }
}
