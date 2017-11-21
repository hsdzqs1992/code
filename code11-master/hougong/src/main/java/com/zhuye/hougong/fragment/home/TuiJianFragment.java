package com.zhuye.hougong.fragment.home;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.zhuye.hougong.R;
import com.zhuye.hougong.base.BaseFragment;
import com.zhuye.hougong.bean.HomeBanner;
import com.zhuye.hougong.contants.Contants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzzy on 2017/11/21.
 */

public class TuiJianFragment extends BaseFragment {

    SliderLayout sliderShow;




    private HomeBanner homeBanner;

    public TuiJianFragment() {


    }

    public void setData(HomeBanner homeBanner){
        this.homeBanner = homeBanner;
        if(homeBanner!=null){
            List<String> url = new ArrayList();
            url.add(Contants.BASE_URL+homeBanner.getData().get(0).getImg());
            url.add(Contants.BASE_URL+homeBanner.getData().get(1).getImg());
            url.add(Contants.BASE_URL+homeBanner.getData().get(2).getImg());

            sliderShow= (SliderLayout) rootView.findViewById(R.id.slider);

//        DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
//        defaultSliderView.image("");
//        sliderShow.addSlider(defaultSliderView);

            for (int i = 0 ; i<url.size();i++){
                DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
                defaultSliderView.image(url.get(i));
                sliderShow.addSlider(defaultSliderView);
            }
        }
    }

    @Override
    protected void initView() {



    }

    @Override
    protected int getResId() {
        return R.layout.fragment_home_vp;
    }
}
