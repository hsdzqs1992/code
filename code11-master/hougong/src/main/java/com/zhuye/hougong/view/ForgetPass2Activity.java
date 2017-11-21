package com.zhuye.hougong.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
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

public class ForgetPass2Activity extends AppCompatActivity {

    String phone;
    @BindView(R.id.reforget_passs)
    EditText reforgetPasss;
    @BindView(R.id.reforget_repasss)
    EditText reforgetRepasss;
    @BindView(R.id.login_login)
    Button loginLogin;

    String pass;
    String repass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass2);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");

    }

    @OnClick(R.id.login_login)
    public void onViewClicked() {
        pass= reforgetPasss.getText().toString().trim();
        repass= reforgetRepasss.getText().toString().trim();
        if(TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass)){
            Toast.makeText(ForgetPass2Activity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(repass)){
            Toast.makeText(ForgetPass2Activity.this,"密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }


        //修改密码
        OkGo.<String>post(Contants.forget_pass)
                .params("mobile",phone)
                .params("password",pass).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                Code code = gson.fromJson(response.body(), Code.class);
                if(code.getCode()==200){
                    Toast.makeText(ForgetPass2Activity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    //回到登录界面
                    finish();
                }else {
                    Toast.makeText(ForgetPass2Activity.this,"修给失败",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(ForgetPass2Activity.this,"修改失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
