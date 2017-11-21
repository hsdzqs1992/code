package com.zhuye.hougong.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.MessagePagerAdapter;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.weidgt.PagerSlidingTabStrip;

/**
 * Created by zzzy on 2017/11/20.
 */

public class MessageFragment extends BaseFragment {

    private ViewPager mviewpager;

    private PagerSlidingTabStrip mTabStrip;
    @Override
    protected void initView() {

        mviewpager = rootView.findViewById(R.id.message_viewpager);

        mTabStrip=rootView.findViewById(R.id.tab_strip_message);
        mTabStrip.setTextColorResource(R.color.white);
        mTabStrip.setIndicatorColorResource(R.color.white);
        mTabStrip.setDividerColor(Color.TRANSPARENT);
        mTabStrip.setTextSelectedColorResource(R.color.white);
        mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h6));
        mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
        mTabStrip.setUnderlineHeight(1);
        mviewpager.setAdapter(new MessagePagerAdapter(getActivity().getSupportFragmentManager()));
        mTabStrip.setViewPager(mviewpager);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_message;
    }
}
