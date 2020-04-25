package com.wd.tech.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.arc.LivenessActivity;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye)
    ImageView eye;
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
        if (o instanceof LoginBean&& TextUtils.equals("0000",((LoginBean) o).getStatus())){
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("b",true);
            edit.putInt("uid",((LoginBean) o).getResult().getUserId());
            edit.putString("sid",((LoginBean) o).getResult().getSessionId());
            Toast.makeText(this, ((LoginBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            edit.commit();
            startActivity(this,MainActivity.class);
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

    @OnClick({R.id.eye, R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye:
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
