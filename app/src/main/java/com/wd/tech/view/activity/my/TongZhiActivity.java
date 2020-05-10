package com.wd.tech.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.bean.my.TongZhiBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.tongzhi.TongZhiAdapter;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TongZhiActivity extends BaseHuaActivity<TechPresenter> {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {
        getSupportActionBar().hide();
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.Base_Tong_Zhi, TongZhiBean.class, map);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_tong_zhi;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if(o instanceof TongZhiBean && TextUtils.equals("0000",((TongZhiBean) o).getStatus())){
            List<TongZhiBean.ResultBean> result = ((TongZhiBean) o).getResult();
            recycler.setLayoutManager(new LinearLayoutManager(this));
            TongZhiAdapter tongZhiAdapter = new TongZhiAdapter(result);
            recycler.setAdapter(tongZhiAdapter);
        }

    }

    @Override
    public void onFailure(Throwable e) {

    }



    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
