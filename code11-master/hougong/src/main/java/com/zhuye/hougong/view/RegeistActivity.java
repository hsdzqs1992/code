package com.zhuye.hougong.view;

import android.content.Intent;
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
import com.zhuye.hougong.bean.Code;
import com.zhuye.hougong.contants.Contants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegeistActivity extends AppCompatActivity {

    @BindView(R.id.regeist_username_tv)
    TextView regeistUsernameTv;
    @BindView(R.id.regeist_username)
    EditText regeistUsername;
    @BindView(R.id.regeist_yanzhengma)
    EditText regeistYanzhengma;
    @BindView(R.id.login_sendcode)
    Button loginSendcode;
    @BindView(R.id.regeist_pass)
    EditText regeistPass;
    @BindView(R.id.regeist_repass)
    EditText regeistRepass;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.activity_regeist)
    LinearLayout activityRegeist;

    String phone;
    String pass;
    String repassword;
    String regeistYanzhengma1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regeist);
        ButterKnife.bind(this);



    }

    @OnClick({R.id.login_sendcode, R.id.login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_sendcode:
                phone=regeistUsername.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegeistActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                OkGo.<String>post(Contants.GET_REGEIST_URL)
                        .params("mobile",phone).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Code code = gson.fromJson(response.body(), Code.class);
                        if(code.getCode()==200){
                            Toast.makeText(RegeistActivity.this,"获取验证码成功，为"+code.getData(),Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(RegeistActivity.this,"获取验证码失败",Toast.LENGTH_SHORT).show();
                        }




                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Toast.makeText(RegeistActivity.this,"获取验证码失败",Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.login_login:
                phone=regeistUsername.getText().toString().trim();
                regeistYanzhengma1=regeistYanzhengma.getText().toString().trim();
                pass=regeistPass.getText().toString().trim();
                repassword=regeistRepass.getText().toString().trim();

                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegeistActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!pass.equals(repassword)){
                    Toast.makeText(RegeistActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                OkGo.<String>post(Contants.REGEIST_URL).params("mobile",phone)
                        .params("password",pass)
                        .params("code",regeistYanzhengma1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                                //JsonObject jsonObject = new JsonObject();
                                //JsonObject s = jsonObject.getAsJsonObject(response.body());
                                //s.get("Code");
                                Toast.makeText(RegeistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegeistActivity.this,LoginActivity.class));

//                                Gson gson = new Gson();
//                                Code code = gson.fromJson(response.body(), Code.class);
//                                if(code.getCode()==200){
//                                    Toast.makeText(RegeistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(RegeistActivity.this,LoginActivity.class));
//                                    finish();
//                                }else {
//                                    Toast.makeText(RegeistActivity.this,"获取验证码失败",Toast.LENGTH_SHORT).show();
//                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                            }
                        });
                break;
        }
    }
}
