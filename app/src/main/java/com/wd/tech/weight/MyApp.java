package com.wd.tech.weight;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.example.arclibrary.builder.AcrFaceManagerBuilder;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.Constants;

/**
 * date:2020/4/18
 * author:朱贵全(Lenovo)
 * function:全局类
 */
public class MyApp extends Application {
    public static Context mContext;
    public static IWXAPI mWxApi;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        registerTowX();
        mContext=this;
    }
    private void initArcFace() {
        new AcrFaceManagerBuilder().setContext(this)
                .setFreeSdkAppId(Constants.FREESDKAPPID)
                .setFdSdkKey(Constants.FDSDKKEY)
                .setFtSdkKey(Constants.FTSDKKEY)
                .setFrSdkKey(Constants.FRSDKKEY)
                .setLivenessAppId(Constants.LIVENESSAPPID)
                .setLivenessSdkKey(Constants.LIVENESSSDKKEY)
                .create();
    }
    private void registerTowX(){
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, "wx4c96b6b8da494224", true);
        // 将该app注册到微信
        mWxApi.registerApp("wx4c96b6b8da494224");
    }

}
