package com.wd.tech.view.activity.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.UserInfoBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.NetUtil;
import com.wd.tech.view.activity.MainActivity;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.she_login)
    TextView sheLogin;
    @BindView(R.id.user_headprice)
    ImageView userHeadprice;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.user_qianming)
    ImageView userQianming;
    @BindView(R.id.user_britary)
    TextView userBritary;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_youxiang)
    TextView userYouxiang;
    @BindView(R.id.user_jifen)
    TextView userJifen;
    @BindView(R.id.user_vip)
    TextView userVip;
    @BindView(R.id.user_facebind)
    TextView userFacebind;

    private SharedPreferences sp;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //隐藏标题栏
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", -1);
        String sid = sp.getString("sid", "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", uid);
        map.put("sessionId", sid);
        mPresenter.getHeadParams(MyUrls.BASE_BYID, UserInfoBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_she;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserInfoBean && TextUtils.equals("0000", ((UserInfoBean) o).getStatus())) {
            UserInfoBean.ResultBean result = ((UserInfoBean) o).getResult();
            NetUtil.getInstance().getCiclePhoto(result.getHeadPic(), userHeadprice);
            userBritary.setText(result.getBirthday()+"");
            userFacebind.setText(result.getWhetherFaceId()+"");
            userJifen.setText(result.getIntegral()+"");
            userName.setText(result.getNickName()+"");
            int sex = result.getSex();
            if(sex==1){
                userSex.setText("男");
            }else{
                userSex.setText("女");
            }
            int whetherFaceId = result.getWhetherFaceId();
            if(whetherFaceId==1){
                userVip.setText("已绑定");
            }else{
                userVip.setText("未绑定");
            }

            userPhone.setText(result.getPhone()+"");
            userYouxiang.setText(result.getEmail()+"");
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }




    @OnClick({R.id.user_qianming, R.id.user_facebind,R.id.she_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_qianming:
                break;
            case R.id.user_facebind:
                break;
            case R.id.she_login:
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("b", false);
                editor.commit();
                Intent intent = new Intent(SheActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
