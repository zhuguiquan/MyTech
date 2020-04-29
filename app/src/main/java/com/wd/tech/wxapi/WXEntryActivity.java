package com.wd.tech.wxapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.WeixinEntity;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.MainActivity;
import com.wd.tech.weight.MyApp;
import com.wd.tech.weight.MyUrls;

import java.util.HashMap;

public class WXEntryActivity extends BaseActivity<TechPresenter> implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    String code;
    SharedPreferences sp;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        MyApp.mWxApi.handleIntent(getIntent(),this);

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_wxentry;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == baseResp.getType()) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        code = ((SendAuth.Resp) baseResp).code;
                        Log.i("codeeeee",code+"");
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        HashMap<String, Object> hashMap = new HashMap<>();
                        HashMap<String, Object> headMap = new HashMap<>();
                        headMap.put("ak", 1);
                        hashMap.put("code", code);
                        mPresenter.postDoHeadParams(MyUrls.WEIXINLOG_URL, WeixinEntity.class,headMap,hashMap);
                        break;
                    case RETURN_MSG_TYPE_SHARE:
                        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                break;

        }
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof WeixinEntity && TextUtils.equals("0000", ((WeixinEntity) o).getStatus())){

            sp = getSharedPreferences("login.dp", MODE_PRIVATE);
            WeixinEntity weixinEntity = (WeixinEntity) o;
            WeixinEntity.ResultBean result = weixinEntity.getResult();
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("headPic",result.getHeadPic());
            edit.putString("nickName",result.getNickName());
            edit.putInt("uid",result.getUserId());
            edit.putString("sid",result.getSessionId());
            edit.putBoolean("b",true);
            edit.commit();

            Intent intent = new Intent(WXEntryActivity.this, MainActivity.class);
            intent.putExtra("login",true);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
