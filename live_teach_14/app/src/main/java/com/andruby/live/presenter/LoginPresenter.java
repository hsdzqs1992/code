package com.andruby.live.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.andruby.live.http.AsyncHttp;
import com.andruby.live.http.data.UserInfo;
import com.andruby.live.http.request.LoginRequest;
import com.andruby.live.http.request.PhoneLoginRequest;
import com.andruby.live.http.request.RequestComm;
import com.andruby.live.http.request.VerifyCodeRequest;
import com.andruby.live.http.response.Response;
import com.andruby.live.logic.IMLogin;
import com.andruby.live.logic.IUserInfoMgrListener;
import com.andruby.live.logic.UserInfoMgr;
import com.andruby.live.presenter.ipresenter.ILoginPresenter;
import com.andruby.live.utils.AsimpleCache.ACache;
import com.andruby.live.utils.AsimpleCache.CacheConstants;
import com.andruby.live.utils.Constants;
import com.andruby.live.utils.OtherUtils;

/**
 * @description: 登陆逻辑管理
 * @author: Andruby
 * @time: 2016/12/18 14:04
 */
public class LoginPresenter extends ILoginPresenter implements IMLogin.IMLoginListener {
	private ILoginView mLoginView;

	private IMLogin mIMLogin = IMLogin.getInstance();

	public LoginPresenter(ILoginView loginView) {
		super(loginView);
		mLoginView = loginView;
	}

	@Override
	public void start() {

	}

	@Override
	public void finish() {

	}


	@Override
	public boolean checkPhoneLogin(String phone, String verifyCode) {
		if (OtherUtils.isPhoneNumValid(phone)) {
			if (OtherUtils.isVerifyCodeValid(verifyCode)) {
				if (OtherUtils.isNetworkAvailable(mLoginView.getContext())) {
					return true;
				} else {
					mLoginView.showMsg("当前无网络连接");
				}
			} else {
				mLoginView.phoneError("验证码错误");
			}
		} else {
			mLoginView.phoneError("手机格式错误");
		}
		mLoginView.dismissLoading();
		return false;
	}

	@Override
	public boolean checkUserNameLogin(String userName, String password) {
		if (OtherUtils.isUsernameVaild(userName)) {
			if (OtherUtils.isPasswordValid(password)) {
				if (OtherUtils.isNetworkAvailable(mLoginView.getContext())) {
					return true;
				} else {
					mLoginView.showMsg("当前无网络连接");
				}
			} else {
				mLoginView.passwordError("密码过短");
			}
		} else {
			mLoginView.usernameError("用户名不符合规范");
		}
		mLoginView.dismissLoading();
		return false;
	}

	@Override
	public void phoneLogin(final String mobile, String verifyCode) {
		if (checkPhoneLogin(mobile, verifyCode)) {
			PhoneLoginRequest req = new PhoneLoginRequest(1200, mobile, verifyCode);
			AsyncHttp.instance().postJson(req, new AsyncHttp.IHttpListener() {
				@Override
				public void onStart(int requestId) {
					mLoginView.showLoading();
				}

				@Override
				public void onSuccess(int requestId, Response response) {
					if (response.status == RequestComm.SUCCESS) {
						ACache.get(mLoginView.getContext()).put(CacheConstants.LOGIN_USERNAME, mobile);
						mLoginView.loginSuccess();
					} else {
						mLoginView.loginFailed(response.status, response.msg);
					}
					mLoginView.dismissLoading();
				}

				@Override
				public void onFailure(int requestId, int httpStatus, Throwable error) {
					mLoginView.verifyCodeFailed("网络异常");
					mLoginView.dismissLoading();
				}
			});
		}
	}

	@Override
	public void usernameLogin(final String userName, final String password) {
		if (checkUserNameLogin(userName, password)) {
			LoginRequest req = new LoginRequest(RequestComm.register, userName, password);
			AsyncHttp.instance().postJson(req, new AsyncHttp.IHttpListener() {
				@Override
				public void onStart(int requestId) {
					mLoginView.showLoading();
				}

				@Override
				public void onSuccess(int requestId, Response response) {
					if (response.status == RequestComm.SUCCESS) {
						UserInfo info = (UserInfo) response.data;
						if (!TextUtils.isEmpty(info.sdkAppId) && !TextUtils.isEmpty(info.sdkAccountType)) {
							try {
								Constants.IMSDK_APPID = Integer.parseInt(info.sdkAppId);
								Constants.IMSDK_ACCOUNT_TYPE = Integer.parseInt(info.sdkAccountType);
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						UserInfo.saveCache(mLoginView.getContext(), info);
						UserInfoMgr.getInstance().setUserInfo(info);
						mIMLogin.imLogin(info.userId, info.sigId);
						mIMLogin.setIMLoginListener(LoginPresenter.this);
						ACache.get(mLoginView.getContext()).put(CacheConstants.LOGIN_USERNAME, userName);
						ACache.get(mLoginView.getContext()).put(CacheConstants.LOGIN_PASSWORD, password);
						ACache.get(mLoginView.getContext()).put("user_id", info.userId);
					} else {
						mLoginView.loginFailed(response.status, response.msg);
						mLoginView.dismissLoading();
					}
				}

				@Override
				public void onFailure(int requestId, int httpStatus, Throwable error) {
					mLoginView.loginFailed(httpStatus, error.getMessage());
					mLoginView.dismissLoading();
				}
			});
		}
	}

	@Override
	public void sendVerifyCode(String phoneNum) {
		if (OtherUtils.isPhoneNumValid(phoneNum)) {
			if (OtherUtils.isNetworkAvailable(mLoginView.getContext())) {
				VerifyCodeRequest req = new VerifyCodeRequest(1000, phoneNum);
				AsyncHttp.instance().postJson(req, new AsyncHttp.IHttpListener() {
					@Override
					public void onStart(int requestId) {
						mLoginView.showLoading();
					}

					@Override
					public void onSuccess(int requestId, Response response) {
						if (response.status == RequestComm.SUCCESS) {
							UserInfo userInfo = (UserInfo) response.data;
							if (null != mLoginView) {
								mLoginView.verifyCodeSuccess(60, 60);
							}
						} else {
							mLoginView.verifyCodeFailed("获取台验证码失败");
						}
						mLoginView.dismissLoading();
					}

					@Override
					public void onFailure(int requestId, int httpStatus, Throwable error) {
						if (null != mLoginView) {
							mLoginView.verifyCodeFailed("获取台验证码失败");
						}
						mLoginView.dismissLoading();
					}
				});
			} else {
				mLoginView.showMsg("当前无网络连接");
			}
		} else {
			mLoginView.phoneError("手机号码不符合规范");
		}
	}

	public void setIMLoginListener() {
		mIMLogin.setIMLoginListener(this);
	}

	public void removeIMLoginListener() {
		mIMLogin.removeIMLoginListener();
	}

	@Override
	public void onSuccess() {
		UserInfoMgr.getInstance().setUserId(mIMLogin.getLastUserInfo().identifier, new IUserInfoMgrListener() {
			@Override
			public void OnQueryUserInfo(int error, String errorMsg) {
				// TODO: 16/8/10
			}

			@Override
			public void OnSetUserInfo(int error, String errorMsg) {
				if (0 != error) {
					mLoginView.showMsg("设置 User ID 失败");
				}
			}
		});

		mLoginView.showMsg("登陆成功");
		mIMLogin.removeIMLoginListener();
		mLoginView.dismissLoading();
		mLoginView.loginSuccess();
	}

	@Override
	public void onFailure(int code, String msg) {
		Log.d("log", "IM Login Error errCode:" + code + " msg:" + msg);
		//被踢下线后弹窗显示被踢
		if (6208 == code) {
			OtherUtils.showKickOutDialog(mLoginView.getContext());
		}
		mLoginView.showMsg("登录失败");
		mLoginView.dismissLoading();
		mLoginView.loginFailed(code, msg);
	}
}
