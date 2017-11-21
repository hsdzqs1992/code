package com.andruby.live.presenter.ipresenter;

import android.app.Activity;
import android.net.Uri;

import com.andruby.live.base.BasePresenter;
import com.andruby.live.base.BaseView;


/**
 * @description: 直播发布设置界面逻辑
 * @author: Andruby
 * @time: 2016/12/15 11:54
 */
public abstract class IPublishSettingPresenter implements BasePresenter {
	protected BaseView mBaseView;

	public IPublishSettingPresenter(BaseView baseView) {
		mBaseView = baseView;
	}

	public abstract void doPublish(String title, int recordType, String location, int bitType, boolean isRecord);

	public abstract void doUploadPic(String path);

	public abstract void doLocation();

	public abstract Uri cropImage(Uri imageUri);

	public abstract Uri pickImage(boolean isPermission, int pickType);


	public interface IPublishSettingView extends BaseView {

		/**
		 * 定位成功
		 *
		 * @param location
		 */
		void doLocationSuccess(String location);

		/**
		 * 定位失败
		 */
		void doLocationFailed();

		/**
		 * 图片上传成功
		 *
		 * @param url
		 */
		void doUploadSuceess(String url);

		/**
		 * 图片上传失败
		 */
		void doUploadFailed();

		void doPublishSuccess(String pushUrl);

		void doPublishFail();

		Activity getActivity();
	}
}