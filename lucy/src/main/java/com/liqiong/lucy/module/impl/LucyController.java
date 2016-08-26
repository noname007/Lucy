package com.liqiong.lucy.module.impl;

import android.util.Log;

import com.liqiong.lucy.annotation.Autowired;
import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/24.
 */
public class LucyController extends Module {


    @Autowired
    public static UiHelp uiHelp;

    @Autowired(clazz = MyHelpImpl.class)
    public static MyHelp myHelp;

    @Override
    public void onCreate() {
        Log.e("liqiong", "LucyController onCreate");
    }

    @Override
    public void onDestroy() {
        Log.e("liqiong", " LucyController onDestroy");
    }
}
