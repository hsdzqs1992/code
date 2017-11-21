package com.zhuye.hougong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {


    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fargments = new ArrayList<>();

    HomeBanner homeBanner;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add("推荐");
        titles.add("活跃");
        titles.add("新人");
        titles.add("免费");



    }

    public void setData(HomeBanner homeBanner){
        this.homeBanner = homeBanner;
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
