package com.wd.tech.wxapi;

import android.content.Context;

import com.wd.tech.weight.MyApp;


/**
 * date:2020/3/19 0019
 * author:王彦敏(Administrator)
 * function:微信工具类
 */
public class WXUtils {
    //判断是否安装过微信
    public static boolean success(Context context) {
        if (MyApp.mWxApi.isWXAppInstalled()) {
            return true;
        } else {
            return false;
        }
    }
}
