package com.wd.tech.view.adapter.xiaoxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.bean.CommunityZanBean;
import com.wd.tech.bean.xiaoxi.GroupHistoryBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.util.RxPartMapUtils;
import com.wd.tech.view.activity.xiaoxi.GroupSetActivity;
import com.wd.tech.weight.Msg;
import com.wd.tech.weight.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;

public class GroupChatActivity extends BaseHuaActivity<TechPresenter> {

    int page = 1;
    @BindView(R.id.comeBack1)
    ImageView comeBack1;
    @BindView(R.id.name1)
    TextView name;
    @BindView(R.id.set1)
    ImageView set;
    @BindView(R.id.rc1)
    RecyclerView rc;
    @BindView(R.id.cont1)
    EditText cont;
    @BindView(R.id.send1)
    Button send;
    private SharedPreferences sp;
    private List<Msg.Message> list = new ArrayList<>();
    private int id;
    private String headPic;
    private String myName;
    private GroupChatAdapter groupChatAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        myName = sp.getString("nickName", "");
        headPic = sp.getString("headPic", "");
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            String nam = intent.getStringExtra("name");
            name.setText(nam);
            HashMap<String, Object> map = new HashMap<>();
            map.put("groupId", id);
            map.put("page", page);
            map.put("count", 10);
            mPresenter.getDoParams(MyUrls.BASE_GROUP_HISTORY, GroupHistoryBean.class, map);
        }
        rc.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_group_chat;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof GroupHistoryBean && TextUtils.equals("0000", ((GroupHistoryBean) o).getStatus())) {
            List<GroupHistoryBean.ResultBean> result = ((GroupHistoryBean) o).getResult();
            for (int i = result.size() - 1; i >= 0; i--) {
                GroupHistoryBean.ResultBean resultBean = result.get(i);
                int userId = resultBean.getUserId();
                Msg.Message messa = null;
                String s = null;
                try {
                    s = RsaCoder.decryptByPublicKey(resultBean.getChatContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (userId == sp.getInt("uid", -1)) {
                    messa = Msg.getInstance().getMessage(s, resultBean.getHeadPic(), resultBean.getNickName(), Msg.TYPE_SENT);
                } else {
                    messa = Msg.getInstance().getMessage(s, resultBean.getHeadPic(), resultBean.getNickName(), Msg.TYPE_RECEIVED);
                }
                list.add(messa);
            }
            groupChatAdapter = new GroupChatAdapter(list);
            rc.setAdapter(groupChatAdapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.comeBack1, R.id.set1, R.id.send1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack1:
                Toast.makeText(this, "哈哈哈", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.set1:
                //群组设置
                Intent intent = new Intent(this, GroupSetActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.send1:
                //发送消息
                String content = cont.getText().toString();
                if (!"".equals(content)) {
                    try {
                        //发送消息
                        String context = RsaCoder.encryptByPublicKey(content);
                        Log.i("context", content);
                        HashMap<String, RequestBody> map = new HashMap<>();
                        map.put("groupId", RxPartMapUtils.toRequestBodyOfText(id + ""));
                        map.put("content", RxPartMapUtils.toRequestBodyOfText(context));
                        mPresenter.postFileParams(MyUrls.BASE_SEND_MSG, CommunityZanBean.class, map);
                        Msg.Message messa = Msg.getInstance().getMessage(content, headPic, myName, Msg.TYPE_SENT);
                        //Message message = JMessageClient.createSingleTextMessage(id, MyApp.s1,context);
                        //JMessageClient.sendMessage(message);
                        list.add(messa);
                        groupChatAdapter.notifyItemInserted(list.size() - 1);
                        rc.scrollToPosition(list.size() - 1);
                        cont.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
