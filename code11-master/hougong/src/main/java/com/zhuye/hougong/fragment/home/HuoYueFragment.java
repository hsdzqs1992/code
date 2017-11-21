package com.zhuye.hougong.fragment.home;

import com.daimajia.slider.library.SliderLayout;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseFragment;

/**
 * Created by zzzy on 2017/11/21.
 */

public class HuoYueFragment extends BaseFragment {

    SliderLayout sliderShow;
    @Override
    protected void initView() {
        sliderShow= (SliderLayout) rootView.findViewById(R.id.slider);


//        DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
//        defaultSliderView.image("");
//        sliderShow.addSlider(defaultSliderView);

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_home_vp;
    }
}
