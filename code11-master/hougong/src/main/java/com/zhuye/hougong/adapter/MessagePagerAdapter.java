package com.zhuye.hougong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.fragment.message.TongHuaFragment;
import com.zhuye.hougong.fragment.message.XiaoxiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class MessagePagerAdapter extends FragmentStatePagerAdapter {


    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fargments = new ArrayList<>();

    HomeBanner homeBanner;

    public MessagePagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add("消息");
        titles.add("通话");



        fargments.add(new XiaoxiFragment());
        fargments.add(new TongHuaFragment());




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
