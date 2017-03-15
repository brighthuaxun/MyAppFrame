package com.example.bowen.myappframe.application;

import android.app.Application;

import com.example.bowen.myappframe.util.ZaLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by dongbowen on 2016/6/26.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    //內存泄漏检测
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                ZaLog.e("error", e.getMessage());
            }
        });
        mRefWatcher = LeakCanary.install(this);
    }

    public static BaseApplication getInstance(){
        return instance;
    }

}
