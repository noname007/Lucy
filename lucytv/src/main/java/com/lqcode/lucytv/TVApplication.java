package com.lqcode.lucytv;

import android.app.Application;

import com.liqiong.lucy.module.impl.LucyKernel;

/**
 * Created by LiQiong on 16/9/1.
 */
public class TVApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LucyKernel.getInstance().initialize(getApplicationContext());
    }
}