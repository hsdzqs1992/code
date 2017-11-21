package com.zhuye.hougong.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.hougong.R;
import com.zhuye.hougong.bean.LoginCode;
import com.zhuye.hougong.contants.Contants;
import com.zhuye.hougong.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_forgetpass)
    TextView loginForgetpass;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.login_weixin)
    TextView loginWeixin;
    @BindView(R.id.login_qq)
    TextView loginQq;
    @BindView(R.id.login_weibo)
    TextView loginWeibo;
    @BindView(R.id.login_regeist)
    Button loginRegeist;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.yibai)
    TextView yibai;

    String phone;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        loginWeixin.setTypeface(typeface);
        loginWeibo.setTypeface(typeface);
        loginQq.setTypeface(typeface);
        yibai.setTypeface(typeface);
    }

    @OnClick({R.id.login_forgetpass, R.id.login_login, R.id.login_weixin, R.id.login_qq, R.id.login_weibo,R.id.login_regeist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forgetpass:
                resetPassword();
                break;
            case R.id.login_login:

                login();

                break;
            case R.id.login_weixin:
                break;
            case R.id.login_qq:
                break;
            case R.id.login_weibo:
                break;
            case R.id.login_regeist:
                startActivity(new Intent(LoginActivity.this,RegeistActivity.class));
                break;
        }
    }

    private void resetPassword() {
        startActivity(new Intent(LoginActivity.this,ForgetPassActivity.class));
    }

    private void login() {

        phone=loginUsername.getText().toString().trim();
        pass=loginPassword.getText().toString().trim();
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(pass)){
            Toast.makeText(LoginActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(Contants.LOGIN_URL)
                .params("mobile",phone)
                .params("password",pass)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        LoginCode code = gson.fromJson(response.body(), LoginCode.class);
                        if(code.getCode().equals("200")){
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(LoginActivity.this,LoginActivity.class));
                            //保存token
                            SpUtils.setString(LoginActivity.this,"token",code.getData().getToken());
                            Toast.makeText(LoginActivity.this,SpUtils.getString(LoginActivity.this,"token",""),Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
