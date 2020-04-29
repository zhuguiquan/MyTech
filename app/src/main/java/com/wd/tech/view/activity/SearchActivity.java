package com.wd.tech.view.activity;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.presenter.TechPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cc.ibooker.zflowlayoutlib.FlowLayout;

public class SearchActivity extends BaseActivity<TechPresenter> {
    private String[] strings = {"区块链","中年危机","锤子科技","子弹短信","民营企业","特斯拉","支付包","资本市场","电视剧"};
    @BindView(R.id.shousuo)
    SearchView shousuo;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.search_flowlayout)
    FlowLayout searchFlowlayout;

    @Override
    protected void initData() {
        shousuo.setQueryHint("搜索文章作者");
        shousuo.setIconifiedByDefault(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < strings.length ; i++) {
            TextView textView = (TextView)inflater.inflate(R.layout.search_tv,searchFlowlayout,false);
            textView.setText(strings[i]);
            searchFlowlayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this,SearchListActivity.class);
                    intent.putExtra("name",textView.getText().toString().trim());
                    startActivity(intent);
                }
            });
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
        return R.layout.activity_search;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.shousuo, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shousuo:
                startActivity(new Intent(this, SearchListActivity.class));
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }
}
