package com.liqiong.lucy.module.impl;

import android.content.Context;

import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/25.
 */
public final class LucyKernel extends Module {
    private Context context = null;
    private static LucyKernel kernel = null;

    private LucyKernel() {
    }

    public static LucyKernel getInstance() {
        if (kernel == null) kernel = new LucyKernel();
        return kernel;
    }

    /**
     * 初始化所有的組件Module
     */
    public void initialize(Context context) {
        this.context = context;
        exec(LucyController.class);
    }

    /**
     * 需要在所有的Activity 去调用onStart方法
     *
     * @param context
     */
    public void onStart(Context context) {
        this.context = context;
    }


    //******************************************我是可爱的分割线**************************************************

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        allModuleDestory();
    }

    public Context getContext() {
        return context;
    }
}