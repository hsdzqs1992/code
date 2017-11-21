package com.zhuye.hougong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuye.hougong.R;
import com.zhuye.hougong.adapter.PaiHangAdapter;
import com.zhuye.hougong.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public class PaiHangFragment extends BaseFragment {
    @BindView(R.id.paihang_tablayout)
    TabLayout paihangTablayout;
    @BindView(R.id.paihang_viewpager)
    ViewPager paihangViewpager;
    Unbinder unbinder;

    private List<String> titles = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.fragment_paihang;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        titles.add("魅力榜");
        titles.add("财富榜");





        return rootView;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initData() {

       paihangViewpager.setAdapter(new PaiHangAdapter(getActivity(),titles));
        paihangTablayout.setupWithViewPager(paihangViewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
