package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.ConsultTitleBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.consult.SearchAdapter;
import com.wd.tech.weight.MyUrls;
import com.wd.tech.weight.OnClickItem;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchListActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.shousuo_yi)
    SearchView shousuoYi;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.recy_title)
    RecyclerView recyTitle;
    private CharSequence query;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        shousuoYi.setQueryHint(name);
        shousuoYi.setIconifiedByDefault(false);


    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof ConsultTitleBean) {
            if (((ConsultTitleBean) o).getStatus().equals("0000")) {
                //使用多列表展示
                List<ConsultTitleBean.ResultBean> result = ((ConsultTitleBean) o).getResult();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyTitle.setLayoutManager(linearLayoutManager);
                SearchAdapter searchAdapter = new SearchAdapter(result);
                recyTitle.setAdapter(searchAdapter);
                searchAdapter.setOnClickItem(new OnClickItem() {
                    @Override
                    public void onClickInt(int id) {
                        Intent intent = new Intent(getApplication(),DetailActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }

                    @Override
                    public void onClickString(String string) {

                    }
                });

                }
            }
    }

    @Override
    public void onFailure(Throwable e) {


    }

    @OnClick({R.id.shousuo_yi, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shousuo_yi:
                query = shousuoYi.getQuery();
                HashMap<String, Object> map = new HashMap<>();
                map.put("title", query);
                map.put("page", 1);
                map.put("count", 10);
                mPresenter.getDoParams(MyUrls.CONSULT_TITLE, ConsultTitleBean.class, map);

                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
