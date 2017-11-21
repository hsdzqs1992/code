package com.andruby.live.presenter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.andruby.live.R;
import com.andruby.live.http.request.UploadPicRequest;
import com.andruby.live.logic.UploadMgr;
import com.andruby.live.logic.UserInfoMgr;
import com.andruby.live.presenter.ipresenter.IPublishSettingPresenter;
import com.andruby.live.utils.AsimpleCache.ACache;
import com.andruby.live.utils.Constants;

import java.io.File;

/**
 * @description: 直播列表管理
 * @author: Andruby
 * @time: 2016/12/18 14:04
 */
public class PublishSettingPresenter extends IPublishSettingPresenter {
	private static final String TAG = PublishSettingPresenter.class.getSimpleName();

	public static final int PICK_IMAGE_CAMERA = 100;
	public static final int PICK_IMAGE_LOCAL = 200;
	public static final int CROP_CHOOSE = 10;

	private IPublishSettingView mPublishSettingView;

	public PublishSettingPresenter(IPublishSettingView baseView) {
		super(baseView);
		mPublishSettingView = baseView;
	}


	@Override
	public void start() {

	}

	@Override
	public void finish() {

	}


	@Override
	public void doPublish(String title, int liveType, String location, int bitrateType, boolean isRecord) {

	}

	@Override
	public void doUploadPic(String path) {
//		try {
//			UploadPicRequest uploadPicRequest = new UploadPicRequest(1000,
//					ACache.get(mPublishSettingView.getContext()).getAsString("user_id"), UploadPicRequest.LIVE_COVER_TYPE, new File(path));
//			AsyncHttp.instance().post(uploadPicRequest, new AsyncHttp.IHttpListener() {
//				@Override
//				public void onStart(int requestId) {
//
//				}
//
//				@Override
//				public void onSuccess(int requestId, Response response) {
//					if(response!=null && response.status == RequestComm.SUCCESS){
//						UploadResp uploadResp = (UploadResp)response.data;
//						Log.i(TAG, "onSuccess: file url:" + uploadResp.getUrl());
//						mPublishSettingView.doUploadSuceess(uploadResp.getUrl());
//					}else{
//						Log.i(TAG, "onSuccess: faile:");
//
//					}
//				}
//
//				@Override
//				public void onFailure(int requestId, int httpStatus, Throwable error) {
//					Log.i(TAG, "onFailure: faile:");
//					mPublishSettingView.doUploadFailed();
//				}
//			});
//
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

		UploadMgr uploadMgr = new UploadMgr(mBaseView.getContext(), new UploadMgr.OnUploadListener() {
			@Override
			public void onUploadResult(int code, String imageId, String url) {
				Log.i(TAG, "onSuccess: file url:" +url);
			}
		});
		uploadMgr.uploadCover(ACache.get(mBaseView.getContext()).getAsString("user_id"), path, UploadPicRequest.LIVE_COVER_TYPE);

	}

	@Override
	public void doLocation() {

	}

	@Override
	public Uri cropImage(Uri imageUri) {
		Uri cropUri = createCoverUri("_crop");
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(imageUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 750);
		intent.putExtra("aspectY", 550);
		intent.putExtra("outputX", 750);
		intent.putExtra("outputY", 550);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		mPublishSettingView.getActivity().startActivityForResult(intent, CROP_CHOOSE);
		return cropUri;
	}

	@Override
	public Uri pickImage(boolean isPermission, int pickType) {
		Uri fileUri = null;
		if (!isPermission) {
			mPublishSettingView.showMsg(R.string.tip_no_permission);
			return null;
		}
		switch (pickType) {
			case PICK_IMAGE_CAMERA:
				fileUri = createCoverUri("");
				Intent intent_photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent_photo.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
				mPublishSettingView.getActivity().startActivityForResult(intent_photo, PICK_IMAGE_CAMERA);
				break;
			case PICK_IMAGE_LOCAL:
				Intent intent_album = new Intent("android.intent.action.GET_CONTENT");
				intent_album.setType("image/*");
				mPublishSettingView.getActivity().startActivityForResult(intent_album, PICK_IMAGE_LOCAL);
				break;

		}
		return fileUri;
	}

	public Uri createCoverUri(String preFileName) {
		String filename = UserInfoMgr.getInstance().getUserId() + preFileName + ".jpg";
		String path = Environment.getExternalStorageDirectory() + "/cniaow";

//		>23
		File outputImage = new File(path, filename);
		if (ContextCompat.checkSelfPermission(mPublishSettingView.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(mPublishSettingView.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.WRITE_PERMISSION_REQ_CODE);
			return null;
		}
		try {
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			if (outputImage.exists()) {
				outputImage.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			mPublishSettingView.showMsg("生成封面失败");
		}
		return Uri.fromFile(outputImage);
	}
}

