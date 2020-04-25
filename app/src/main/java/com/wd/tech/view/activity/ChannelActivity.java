package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.ConsultCommentBean;
import com.wd.tech.bean.ConsultCommentListBean;
import com.wd.tech.bean.ConsultListBean;
import com.wd.tech.bean.ConsultTitleBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.ConsultAdapter;
import com.wd.tech.view.adapter.RecommendListAdapter;
import com.wd.tech.weight.MyUrls;
import com.wd.tech.weight.OnClickItem;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.consult_menu)
    ImageView consultMenu;
    @BindView(R.id.channel_name)
    TextView channelName;
    @BindView(R.id.consult_search)
    ImageView consultSearch;
    @BindView(R.id.channel_recycler)
    RecyclerView channelRecycler;

    @Override
    protected void initData() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("plateId", id);
            map.put("page", 1);
            map.put("count", 5);
            mPresenter.getDoParams(MyUrls.CONSULT_CONSULTLIST, ConsultListBean.class, map);
            channelName.setText(name);
        }
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
        return R.layout.activity_channel;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
//列表展示
        if (o instanceof ConsultListBean && TextUtils.equals("0000", ((ConsultListBean) o).getStatus())) {
            List<ConsultListBean.ResultBean> result = ((ConsultListBean) o).getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            RecommendListAdapter consultAdapter = new RecommendListAdapter(result);
            channelRecycler.setLayoutManager(linearLayoutManager);
            channelRecycler.setAdapter(consultAdapter);
            consultAdapter.setOnClickItem(new OnClickItem() {
                @Override
                public void onClickInt(int id) {
                    Intent intent = new Intent(ChannelActivity.this, DetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }

                @Override
                public void onClickString(String string) {

                }
            });
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.consult_menu, R.id.channel_recycler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.consult_menu:
                finish();
                break;
            case R.id.channel_recycler:

                break;
        }
    }
}
