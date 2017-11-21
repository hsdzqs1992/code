package com.andruby.live.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.andruby.live.R;
import com.andruby.live.fragment.LiveMainFragment;
import com.andruby.live.fragment.UserInfoFragment;

/**
 * @Description: 主界面， 包括直播列表，用户信息页
 * UI使用FragmentTabHost+Fragment
 * 直播列表：LiveMainFragment
 * 个人信息页：UserInfoFragment
 * @author: Andruby
 * @date: 2016年7月8日 下午4:46:44
 */
public class MainActivity extends BaseActivity {

	private FragmentTabHost mTabHost;

	private final Class mTabFangment[] = {LiveMainFragment.class, Fragment.class, UserInfoFragment.class};
	private int mTabIcons[] = {R.drawable.tab_live_selector, R.drawable.tab_room_selector, R.drawable.tab_me_selector};
	private String mTabNames[] = {"video", "publish", "user"};


	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		mTabHost = obtainView(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);
	}

	@Override
	protected void initData() {
		int fragmentCount = mTabFangment.length;
		TabHost.TabSpec tabSpec;
		for (int i = 0; i < fragmentCount; i++) {
			tabSpec = mTabHost.newTabSpec(mTabNames[i]).setIndicator(getTabItemView(i));
			mTabHost.addTab(tabSpec, mTabFangment[i], null);
		}
	}

	@Override
	protected void setListener() {
		mTabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PublishSettingActivity.invoke(MainActivity.this);
			}
		});
	}

	private View getTabItemView(int index) {
		View view;
		if (index % 2 == 0) {
			view = LayoutInflater.from(this).inflate(R.layout.tab_button1, null);
		} else {
			view = LayoutInflater.from(this).inflate(R.layout.tab_button, null);
		}
		ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
		icon.setImageResource(mTabIcons[index]);
		return view;
	}


	public static void invoke(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
	}

}
