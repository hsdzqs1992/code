package com.zhuye.hougong.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.FindPagerAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

/**
 * Created by zzzy on 2017/11/20.
 */

public class FindFragment extends BaseFragment {
   // private MyToolbar myToolbar;
    private ViewPager mviewpager;

    private PagerSlidingTabStrip mTabStrip;
    @Override
    protected void initView() {
       //myToolbar = rootView.findViewById(R.id.find_toolbar);

        //initToolBar();
        mviewpager = rootView.findViewById(R.id.find_viewpager);

        mTabStrip=rootView.findViewById(R.id.tab_strip_find);
        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h6));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);
        mviewpager.setAdapter(new FindPagerAdapter(getActivity().getSupportFragmentManager()));
        mTabStrip.setViewPager(mviewpager);


    }



    private void initToolBar() {

       // myToolbar.homeLeftIcon.setText(R.string.huangguan);
        //myToolbar.homeLeftIcon.setTypeface(((MainActivity)getActivity()).typeface);
        //myToolbar.homeRightIcon.setText(R.string.loudou);
       // myToolbar.homeRightIcon.setTypeface(((MainActivity)getActivity()).typeface);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_find;
    }
}
