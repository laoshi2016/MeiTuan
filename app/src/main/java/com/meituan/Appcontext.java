package com.meituan;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;

import util.OkHttpClientUtil;


/**
 * Created by Administrator on 16-3-5.
 */
public class Appcontext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化okhttp网络交互框架
        OkHttpClientUtil.initOkHttp();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

    }
}
