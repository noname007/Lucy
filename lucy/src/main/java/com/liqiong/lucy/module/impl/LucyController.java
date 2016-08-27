package com.liqiong.lucy.module.impl;

import android.util.Log;

import com.liqiong.lucy.annotation.AutowiredModule;
import com.liqiong.lucy.annotation.impl.AutowiredImpl;
import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/24.
 */
public class LucyController extends Module {


    @AutowiredModule
    public static UiHelp uiHelp;

    @AutowiredModule(clazz = MyHelpImpl.class)
    public static MyHelp myHelp;

    @AutowiredModule
    public static AutowiredImpl autowired;

    @Override
    public void onCreate() {
        Log.e("liqiong", "LucyController onCreate");
    }

    @Override
    public void onDestroy() {
        Log.e("liqiong", " LucyController onDestroy");
    }
}
