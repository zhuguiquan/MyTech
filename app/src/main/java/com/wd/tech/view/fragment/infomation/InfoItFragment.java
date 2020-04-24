package com.wd.tech.view.fragment.infomation;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.infomation.FriendNoticeBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.infomation.InfoItAdapter;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoItFragment extends BaseFragment<TechPresenter> {


    @BindView(R.id.sousuo)
    EditText sousuo;
    @BindView(R.id.ifit_rc)
    RecyclerView ifitRc;
    private int page=1;

    @Override
    protected void initView(View view) {
        ifitRc.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("count",10);
        mPresenter.getDoParams(MyUrls.BASE_FRIEND_NOTICE, FriendNoticeBean.class,map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_info_it;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {

        if (o instanceof FriendNoticeBean&& TextUtils.equals("0000",((FriendNoticeBean) o).getStatus())){
            if (((FriendNoticeBean) o).getResult().size()>0){
                List<FriendNoticeBean.ResultBean> result = ((FriendNoticeBean) o).getResult();
                InfoItAdapter infoItAdapter = new InfoItAdapter(result);
                ifitRc.setAdapter(infoItAdapter);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.sousuo)
    public void onViewClicked() {
    }
}
