package com.wd.tech.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.tech.R;
import com.wd.tech.arc.LivenessActivity;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.weight.MyApp;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class LoginActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye)
    CheckBox eye;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_weixin)
    ImageView loginWeixin;
    @BindView(R.id.login_face)
    ImageView loginFace;
    private SharedPreferences sp;

    @Override
    protected void initData() {
        eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    // 如果选中，显示密码
                    eye.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    // 否则隐藏密码
                    eye.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof LoginBean && TextUtils.equals("0000", ((LoginBean) o).getStatus())) {
            LoginBean.ResultBean resultBean = ((LoginBean) o).getResult();
            JMessageClient.login(resultBean.getPhone(), MyApp.s1, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    switch (i) {
                        case 801003:
                            Toast.makeText(LoginActivity.this, "极光用户名不存在", Toast.LENGTH_SHORT).show();
                            break;
                        case 871301:
                            Toast.makeText(LoginActivity.this, "极光密码格式错误", Toast.LENGTH_SHORT).show();
                            break;
                        case 801004:
                            Toast.makeText(LoginActivity.this, "极光密码错误", Toast.LENGTH_SHORT).show();
                            break;
                        case 0:
                            Toast.makeText(LoginActivity.this, "极光登陆成功", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("headPic", resultBean.getHeadPic());
            editor.putString("nickName", resultBean.getNickName());
            editor.putInt("uid", resultBean.getUserId());
            editor.putString("phone", resultBean.getPhone());
            editor.putString("sid", resultBean.getSessionId());
            editor.putBoolean("b", true);
            editor.commit();
            startActivity(LoginActivity.this,MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    //微信登录
    public void wxlogin(View view) {

    }

    //人脸识别
    public void hrlogin(View view) {
        LivenessActivity.flag = 2;
        startActivity(new Intent(LoginActivity.this, LivenessActivity.class));
    }

    @OnClick({R.id.login_weixin, R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_weixin:
                if (MyApp.mWxApi.isWXAppInstalled()){
                   Toast.makeText(this, "没有安装微信", Toast.LENGTH_SHORT).show();
                   return;
               }
                SendAuth.Req req = new SendAuth.Req();
               req.scope="snsapi_userinfo";
               req.state="diandi_wx_login";
               MyApp.mWxApi.sendReq(req);
                break;
            case R.id.register:
                startActivity(LoginActivity.this,RegisterActivity.class);
                break;
            case R.id.login:
                String phon = phone.getText().toString().trim();
                String pw = pwd.getText().toString().trim();
                Log.d("xxxxxx",phon+"");
                Log.d("xxxxxx",pw+"");
                boolean b = Pattern.matches("^1[3|5|7|8][0-9]{9}$", phon);
                if (b){
                    try {
                        String pwd = RsaCoder.encryptByPublicKey(pw);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("phone",phon);
                        map.put("pwd",pwd);
                        Log.d("myPhone",pwd);
                        mPresenter.postDoParams(MyUrls.BASE_LOGIN, LoginBean.class,map);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }
}
