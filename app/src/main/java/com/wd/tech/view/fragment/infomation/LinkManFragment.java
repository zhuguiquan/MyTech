package com.wd.tech.view.fragment.infomation;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.infomation.FriendGroupBean;
import com.wd.tech.bean.infomation.FriendListBean;
import com.wd.tech.bean.xiaoxi.GroupListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.xiaoxi.ChatMsgActivity;
import com.wd.tech.view.activity.xiaoxi.InfoSelefriendActivity;
import com.wd.tech.view.adapter.GroupListAdapter;
import com.wd.tech.view.adapter.InfoExpandAdapter;
import com.wd.tech.view.adapter.infomation.FriendGroupAdapter;
import com.wd.tech.view.adapter.xiaoxi.GroupChatActivity;
import com.wd.tech.weight.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinkManFragment extends BaseFragment<TechPresenter> {


    @BindView(R.id.query)
    TextView query;
    @BindView(R.id.qunzu)
    TextView qunzu;
    @BindView(R.id.qunrc)
    RecyclerView qunrc;
    @BindView(R.id.linkManRc)
    ExpandableListView linkManRc;
    boolean isClosed=false;
    private FriendGroupAdapter friendGroupAdapter;
    private int positio = -1;
    List<FriendGroupBean.ResultBean> group = new ArrayList<>();
    private InfoExpandAdapter adapter;
    @Override
    protected void initView(View view) {
        qunrc.setLayoutManager(new LinearLayoutManager(getContext()));
        //查询所有分组
        mPresenter.getNoParams(MyUrls.BASE_FIND_ALLGROUP, FriendGroupBean.class);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_link_man;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
//查分组
        if (o instanceof FriendGroupBean && TextUtils.equals("0000", ((FriendGroupBean) o).getStatus())) {
            if (((FriendGroupBean) o).getResult().size() > 0) {
                List<FriendGroupBean.ResultBean> result = ((FriendGroupBean) o).getResult();
                adapter = new InfoExpandAdapter(result);
                linkManRc.setAdapter(adapter);
                adapter.setOnClickListener(new InfoExpandAdapter.OnClickListener() {
                    @Override
                    public void onClick(int position, int groupId) {
                        boolean closed = result.get(position).isClosed();
                        if (closed) {
                            linkManRc.collapseGroup(position);
                        } else {
                            linkManRc.expandGroup(position);
                        }
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("groupId", groupId);
                        //查询分组下所有好友
                        mPresenter.getDoParams(MyUrls.BASE_FINDMAN_BYGROUP, FriendListBean.class, map);
                    }
                });
                //点击子列表
                adapter.setChildClickListener(new InfoExpandAdapter.ChildClickListener() {
                    @Override
                    public void onChildClick(int id, String head, String name) {
                        Intent intent = new Intent(getContext(), ChatMsgActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("head", head);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
            }
        }
        //查分组内成员
        if (o instanceof FriendListBean && TextUtils.equals("0000", ((FriendListBean) o).getStatus())) {
            List<FriendListBean.ResultBean> result = ((FriendListBean) o).getResult();
            if (adapter != null) {
                adapter.addAllChild(positio, result);
            }

        }
       // 查询群
        if (o instanceof GroupListBean &&TextUtils.equals("0000",((GroupListBean) o).getStatus())){
            GroupListAdapter groupListAdapter = new GroupListAdapter(((GroupListBean) o).getResult());
            groupListAdapter.setOnClickListener(new GroupListAdapter.OnClickListener() {
                @Override
                public void onClick(int id,String name) {
                    Intent intent = new Intent(getContext(), GroupChatActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }
            });
            qunrc.setAdapter(groupListAdapter);
        }

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.query, R.id.qunzu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query:
                Intent intent = new Intent(getContext(), InfoSelefriendActivity.class);
                startActivity(intent);
                break;
            case R.id.qunzu:
                isClosed=!isClosed;
                if (isClosed){
                    //查询所有群组
                    //查询所有群组
                    mPresenter.getNoParams(MyUrls.BASE_ALLGROUPS, GroupListBean.class);
                    qunrc.setVisibility(View.VISIBLE);
                }else {
                    qunrc.setVisibility(View.GONE);
                }
                break;
        }
    }
}
