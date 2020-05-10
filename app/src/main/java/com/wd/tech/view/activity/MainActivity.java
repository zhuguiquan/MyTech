package com.wd.tech.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.UserInfoBean;
import com.wd.tech.bean.my.FindSingRecordingBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.NetUtil;
import com.wd.tech.view.activity.my.GuanZhuActivity;
import com.wd.tech.view.activity.my.MyDateActivity;
import com.wd.tech.view.activity.my.MyPostActivity;
import com.wd.tech.view.activity.my.SheActivity;
import com.wd.tech.view.activity.my.ShoCangActivity;
import com.wd.tech.view.activity.my.TaskListActivity;
import com.wd.tech.view.activity.my.TongZhiActivity;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.ConsultFragment;
import com.wd.tech.view.fragment.InfoFragment;
import com.wd.tech.weight.MyUrls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.login_iv)
    ImageView loginIv;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bgz)
    ImageView bgz;
    @BindView(R.id.headPic)
    ImageView headPic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dersign)
    TextView dersign;
    @BindView(R.id.shou)
    LinearLayout shou;
    @BindView(R.id.guan)
    LinearLayout guan;
    @BindView(R.id.tie)
    LinearLayout tie;
    @BindView(R.id.tong)
    LinearLayout tong;
    @BindView(R.id.ji)
    LinearLayout ji;
    @BindView(R.id.ren)
    LinearLayout ren;
    @BindView(R.id.she)
    LinearLayout she;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    RadioGroup rg;
    List<Fragment> fglist = new ArrayList<>();
    @BindView(R.id.cont)
    LinearLayout cont;
    @BindView(R.id.menu)
    RelativeLayout menu;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.my_shoucang)
    TextView myShoucang;
    @BindView(R.id.my_guanzhu)
    TextView myGuanzhu;
    @BindView(R.id.my_tiezi)
    TextView myTiezi;
    @BindView(R.id.my_tongzhi)
    TextView myTongzhi;
    @BindView(R.id.my_jifen)
    TextView myJifen;
    @BindView(R.id.my_renwu)
    TextView myRenwu;
    @BindView(R.id.my_shezhi)
    TextView myShezhi;
    @BindView(R.id.qiandao)
    ImageView qiandao;
    private SharedPreferences sp;

    @Override
    protected void initData() {
        //添加数据
        fglist.add(new ConsultFragment());
        fglist.add(new InfoFragment());
        fglist.add(new CommunityFragment());
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fglist.get(position);
            }

            @Override
            public int getCount() {
                return fglist.size();
            }
        });
        //vp 联动 rg
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
        //默认页面
        rg.check(rg.getChildAt(0).getId());
        vp.setCurrentItem(0);
        Intent intent = getIntent();
        boolean bb = intent.getBooleanExtra("bb", false);
        if (bb) {
            rg.check(rg.getChildAt(2).getId());
            vp.setCurrentItem(2);
        }

        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        if (sp.getBoolean("b", false)) {
            ll.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);
            int uid = sp.getInt("uid", -1);
            String sid = sp.getString("sid", "");
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", uid);
            map.put("sessionId", sid);
            mPresenter.getHeadParams(MyUrls.BASE_BYID, UserInfoBean.class, map);
        } else {
            ll.setVisibility(View.VISIBLE);
            rl.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {

        //关闭菜单栏
        getSupportActionBar().hide();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //主内容随着菜单移动
                int width = drawerView.getWidth();
                cont.setTranslationX(width * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserInfoBean && TextUtils.equals("0000", ((UserInfoBean) o).getStatus())) {
            UserInfoBean.ResultBean result = ((UserInfoBean) o).getResult();
            NetUtil.getInstance().getCiclePhoto(result.getHeadPic(), headPic);
            name.setText(result.getNickName());
            dersign.setText(result.getSignature());
        }
        //签到成功
        if (o instanceof FindSingRecordingBean && TextUtils.equals("0000",((FindSingRecordingBean) o).getStatus())){
            List<String> result = ((FindSingRecordingBean) o).getResult();
            Intent intent = new Intent(this,MyDateActivity.class);
            intent.putExtra("tmd",(Serializable) result);
            startActivity(intent);
        }

    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({ R.id.login, R.id.my_shoucang, R.id.my_guanzhu, R.id.my_tiezi, R.id.my_tongzhi, R.id.my_jifen, R.id.my_renwu, R.id.my_shezhi,R.id.qiandao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(this, LoginActivity.class);
                break;
            case R.id.my_shoucang:
                startActivity(this, ShoCangActivity.class);
                break;
            case R.id.my_guanzhu:
                startActivity(this, GuanZhuActivity.class);
                break;
            case R.id.my_tiezi:
                startActivity(this, MyPostActivity.class);
                break;
            case R.id.my_tongzhi:
                startActivity(this, TongZhiActivity.class);
                break;
            case R.id.my_jifen:
                break;
            case R.id.my_renwu:
                startActivity(this, TaskListActivity.class);
                break;
            case R.id.my_shezhi:
                startActivity(this, SheActivity.class);
                break;
            case R.id.qiandao://签到MyDateActivity
                mPresenter.getNoParams(MyUrls.FIND_RECORDING, FindSingRecordingBean.class);
                break;
        }
    }



}
