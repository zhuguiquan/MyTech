package com.wd.tech.view.fragment.xiaoxi;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.xiaoxi.GroupDetailsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.xiaoxi.AddGroupActivity;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindGroupFragment extends BaseFragment<TechPresenter> {

    @BindView(R.id.sou)
    ImageView sou;
    @BindView(R.id.query)
    EditText query;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ll)
    LinearLayout ll;
    private int id;
    @Override
    protected void initView(View view) {
        ll.setVisibility(View.GONE);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_find_group;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.sou, R.id.ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sou:
                //查找群
                String id = query.getText().toString().trim();
                HashMap<String, Object> map = new HashMap<>();
                map.put("groupId", id);
                mPresenter.getDoParams(MyUrls.BASE_GROUP_DETAILS, GroupDetailsBean.class, map);
                break;
            case R.id.ll:
                //申请加群
                Intent intent = new Intent(getContext(), AddGroupActivity.class);
                intent.putExtra("id",this.id);
                startActivity(intent);
                break;
        }
    }
}
