package com.zhuye.hougong.fragment;

import android.support.v4.view.ViewPager;

import com.zhuye.hougong.MainActivity;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.weidgt.MyToolbar;

/**
 * Created by zzzy on 2017/11/20.
 */

public class FindFragment extends BaseFragment {
    private MyToolbar myToolbar;
    private ViewPager mviewpager;

    @Override
    protected void initView() {
        myToolbar = rootView.findViewById(R.id.find_toolbar);

        initToolBar();
        mviewpager = rootView.findViewById(R.id.find_viewpager);

    }



    private void initToolBar() {

        myToolbar.homeLeftIcon.setText(R.string.huangguan);
        myToolbar.homeLeftIcon.setTypeface(((MainActivity)getActivity()).typeface);
        myToolbar.homeRightIcon.setText(R.string.loudou);
        myToolbar.homeRightIcon.setTypeface(((MainActivity)getActivity()).typeface);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_find;
    }
}
