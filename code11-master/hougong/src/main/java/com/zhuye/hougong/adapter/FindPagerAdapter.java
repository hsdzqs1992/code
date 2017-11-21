package com.zhuye.hougong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.fragment.find.GuanZhuFragment;
import com.zhuye.hougong.fragment.find.TongChengFragment;
import com.zhuye.hougong.fragment.find.ZuiXinFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class FindPagerAdapter extends FragmentStatePagerAdapter {


    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fargments = new ArrayList<>();

    HomeBanner homeBanner;

    public FindPagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add("最新");
        titles.add("关注");
        titles.add("同城");


        fargments.add(new ZuiXinFragment());
        fargments.add(new GuanZhuFragment());
        fargments.add(new TongChengFragment());



    }





    @Override
    public Fragment getItem(int position) {
        return fargments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(fargments.get(position).rootView);
//        //container.
//        return fargments.get(position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//        container.removeView(((BaseFragment) object).rootView);
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
