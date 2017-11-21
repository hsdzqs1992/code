package com.andruby.live.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andruby.live.R;
import com.andruby.live.logic.UserInfoMgr;
import com.andruby.live.presenter.PublishSettingPresenter;
import com.andruby.live.presenter.ipresenter.IPublishSettingPresenter;
import com.andruby.live.ui.customviews.CustomSwitch;
import com.andruby.live.utils.Constants;
import com.andruby.live.utils.OtherUtils;
import com.bumptech.glide.Glide;

import java.io.File;

/**
 * @Description: 发布页面
 * @author: Andruby
 * @date: 2016年7月9日
 */
public class PublishSettingActivity extends LiveBaseActivity implements View.OnClickListener,
		RadioGroup.OnCheckedChangeListener, IPublishSettingPresenter.IPublishSettingView {
	private static final String TAG = PublishSettingActivity.class.getSimpleName();

	private TextView BtnBack, btnPublish;
	private Dialog mPicChsDialog;
	private ImageView ivCover;
	private Uri fileUri, cropUri;
	private TextView tvPicTip;
	private TextView tvLBS;
	private TextView tvRecord;
	private CustomSwitch btnLBS;
	private CustomSwitch btnRecord;
	private TextView tvTitle;
	private boolean mPermission = false;
	private RadioGroup mRGBitrate;
	private RadioGroup mRGRecordType;
	private RelativeLayout mRLBitrate;

	private int mRecordType = Constants.RECORD_TYPE_CAMERA;
	private int mBitrateType = Constants.BITRATE_NORMAL;

	private PublishSettingPresenter mPublishSettingPresenter;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_publish_setting;
	}

	@Override
	protected void initView() {
		tvTitle = obtainView(R.id.live_title);
		BtnBack = obtainView(R.id.btn_cancel);
		tvPicTip = obtainView(R.id.tv_pic_tip);
		btnPublish = obtainView(R.id.btn_publish);
		ivCover = obtainView(R.id.cover);
		tvLBS = obtainView(R.id.address);
		tvRecord = obtainView(R.id.tv_record);
		btnLBS = obtainView(R.id.btn_lbs);
		btnRecord = obtainView(R.id.btn_record);
		mRGRecordType = obtainView(R.id.rg_record_type);
		mRGBitrate = obtainView(R.id.rg_bitrate);
		mRLBitrate = obtainView(R.id.rl_bitrate);

		initPicDialog();
	}

	private void initPicDialog() {
		mPicChsDialog = new Dialog(this, R.style.floag_dialog);
		mPicChsDialog.setContentView(R.layout.dialog_pic_choose);
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		Window dlgwin = mPicChsDialog.getWindow();
		WindowManager.LayoutParams lp = dlgwin.getAttributes();
		dlgwin.setGravity(Gravity.BOTTOM);
		lp.width = (int) (display.getWidth()); //设置宽度
		mPicChsDialog.getWindow().setAttributes(lp);
		mPicChsDialog.findViewById(R.id.chos_camera).setOnClickListener(this);
		mPicChsDialog.findViewById(R.id.pic_lib).setOnClickListener(this);
		mPicChsDialog.findViewById(R.id.dialog_btn_cancel).setOnClickListener(this);
	}

	@Override
	protected void initData() {
		String strCover = UserInfoMgr.getInstance().getCoverPic();
		if (!TextUtils.isEmpty(strCover)) {
			Glide.with(this).load(strCover).into(ivCover);
			tvPicTip.setVisibility(View.GONE);
		} else {
			ivCover.setImageResource(R.drawable.publish_background);
		}

		mPublishSettingPresenter = new PublishSettingPresenter(this);
	}

	@Override
	protected void setListener() {
		mRGRecordType.setOnCheckedChangeListener(this);
		mRGBitrate.setOnCheckedChangeListener(this);
		ivCover.setOnClickListener(this);
		BtnBack.setOnClickListener(this);
		btnPublish.setOnClickListener(this);
		btnLBS.setOnClickListener(this);
		btnRecord.setOnClickListener(this);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_cancel:
				finish();
				break;
			case R.id.btn_publish:
				String location = tvLBS.getText().toString().equals(getString(R.string.text_live_lbs_fail)) ||
						tvLBS.getText().toString().equals(getString(R.string.text_live_location)) ?
						getString(R.string.text_live_close_lbs) : tvLBS.getText().toString();
				break;
			case R.id.cover:
				mPicChsDialog.show();
				break;
			case R.id.btn_lbs:
				if (btnLBS.getChecked()) {
					btnLBS.setChecked(false, true);
					tvLBS.setText(R.string.text_live_close_lbs);
				} else {
					btnLBS.setChecked(true, true);
					tvLBS.setText(R.string.text_live_location);
				}
				break;
			case R.id.btn_record:
				if (btnRecord.getChecked()) {
					btnRecord.setChecked(false, true);
					tvRecord.setText(R.string.text_live_record_no);
				} else {
					btnRecord.setChecked(true, true);
					tvRecord.setText(R.string.text_live_record_yes);
				}
				break;
			case R.id.chos_camera:
				mPublishSettingPresenter.pickImage(true, PublishSettingPresenter.PICK_IMAGE_CAMERA);
				mPicChsDialog.dismiss();
				break;
			case R.id.pic_lib:
				mPublishSettingPresenter.pickImage(true, PublishSettingPresenter.PICK_IMAGE_LOCAL);
				mPicChsDialog.dismiss();
				break;
			case R.id.dialog_btn_cancel:
				mPicChsDialog.dismiss();
				break;

		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
				case PublishSettingPresenter.PICK_IMAGE_CAMERA:
					cropUri = mPublishSettingPresenter.cropImage(fileUri);
					break;
				case PublishSettingPresenter.PICK_IMAGE_LOCAL:
					String path = OtherUtils.getPath(this, data.getData());
					if (null != path) {
						Log.d(TAG, "cropImage->path:" + path);
						File file = new File(path);
						cropUri = mPublishSettingPresenter.cropImage(Uri.fromFile(file));
					}
					break;
				case PublishSettingPresenter.CROP_CHOOSE:
					tvPicTip.setVisibility(View.GONE);
					mPublishSettingPresenter.doUploadPic(cropUri.getPath());
					ivCover.setImageBitmap(BitmapFactory.decodeFile(cropUri.getPath()));
					mPublishSettingPresenter.doUploadPic(cropUri.getPath());
					break;

			}
		}


	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.rb_bitrate_slow:
				mBitrateType = Constants.BITRATE_SLOW;
				break;
			case R.id.rb_bitrate_normal:
				mBitrateType = Constants.BITRATE_NORMAL;
				break;
			case R.id.rb_bitrate_fast:
				mBitrateType = Constants.BITRATE_FAST;
				break;
			case R.id.rb_record_camera:
				mRecordType = Constants.RECORD_TYPE_CAMERA;
				mRLBitrate.setVisibility(View.GONE);
				break;
			case R.id.rb_record_screen:
//				try {
//					OtherUtils.checkFloatWindowPermission(PublishSettingActivity.this);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				mRLBitrate.setVisibility(View.VISIBLE);
				mRecordType = Constants.RECORD_TYPE_SCREEN;
				break;
			default:
				break;
		}
	}

	public static void invoke(Context context) {
		Intent intent = new Intent(context, PublishSettingActivity.class);
		context.startActivity(intent);
	}

	@Override
	public void doLocationSuccess(String location) {

	}

	@Override
	public void doLocationFailed() {

	}

	@Override
	public void doUploadSuceess(String url) {

	}

	@Override
	public void doUploadFailed() {

	}

	@Override
	public void doPublishSuccess(String pushUrl) {

	}

	@Override
	public void doPublishFail() {

	}

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void dismissLoading() {

	}

	@Override
	public void showMsg(String msg) {

	}

	@Override
	public void showMsg(int msg) {

	}

	@Override
	public Context getContext() {
		return this;
	}
}
