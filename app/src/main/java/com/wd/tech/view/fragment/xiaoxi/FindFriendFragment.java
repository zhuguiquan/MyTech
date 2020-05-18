package com.wd.tech.view.fragment.xiaoxi;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.xiaoxi.InfoSeleFriendBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.NetUtil;
import com.wd.tech.view.activity.xiaoxi.UserInfoMsActivity;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFriendFragment extends BaseFragment<TechPresenter> {


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
    private String phone;

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
        return R.layout.fragment_find_friend;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof InfoSeleFriendBean && TextUtils.equals("0000", ((InfoSeleFriendBean) o).getStatus())) {
            ll.setVisibility(View.VISIBLE);
            InfoSeleFriendBean.ResultBean result = ((InfoSeleFriendBean) o).getResult();
            String headPic = result.getHeadPic();
            NetUtil.getInstance().getPhoto(headPic, head);
            name.setText(result.getNickName());
            phone = result.getPhone();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.sou, R.id.ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sou:
                //查找人
                String name = query.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getContext(), "为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("phone", name);
                mPresenter.getDoParams(MyUrls.BASE_SEUSER_BYPHONE, InfoSeleFriendBean.class, map);
                break;
            case R.id.ll:
                Intent intent = new Intent(getContext(), UserInfoMsActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                break;
        }
    }
}
