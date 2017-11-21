package com.zhuye.hougong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.hougong.R;
import com.zhuye.hougong.utils.SpUtils;
import com.zhuye.hougong.view.GuanZhuActivity;
import com.zhuye.hougong.view.LoginActivity;
import com.zhuye.hougong.view.PersonDetailActivity;
import com.zhuye.hougong.view.SettingsActivity;
import com.zhuye.hougong.weidgt.RoundedCornerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public class MeFragment extends Fragment {


    //private MyToolbar myToolbar;

    RoundedCornerImageView cornerImageView;
    ImageView setiv;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_friends)
    TextView tvFriends;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_mee, null);

        //myToolbar = view.findViewById(R.id.mee_toolbar);

        // myToolbar.hideView(myToolbar.homeLeftIcon);
        cornerImageView = view.findViewById(R.id.avatar);
        setiv = view.findViewById(R.id.iv_set);

        initListener();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initListener() {

        cornerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SpUtils.getString(getActivity(), "token", "");
                if (TextUtils.isEmpty(token)) {

                    //没登录
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    //登录
                    startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                }
            }
        });
        setiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_follow, R.id.tv_fans, R.id.tv_friends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow:
                startActivity(new Intent(getActivity(), GuanZhuActivity.class));
                break;
            case R.id.tv_fans:

                break;
            case R.id.tv_friends:

                break;
        }
    }
}
