package com.wd.tech.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.InfoCollectionBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.shoucang.InfoCollectionAdapter;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoCangActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.delete)
    ImageView delete;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {
        getSupportActionBar().hide();
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 10);
        mPresenter.getDoParams(MyUrls.Base_Info_Collection, InfoCollectionBean.class, map);
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
        return R.layout.activity_sho_cang;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof InfoCollectionBean && TextUtils.equals("0000", ((InfoCollectionBean) o).getStatus())) {
            List<InfoCollectionBean.ResultBean> result = ((InfoCollectionBean) o).getResult();
            recycler.setLayoutManager(new LinearLayoutManager(this));
            InfoCollectionAdapter infoCollectionAdapter = new InfoCollectionAdapter(result);
            recycler.setAdapter(infoCollectionAdapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.back, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.delete:
                break;
        }
    }
}
