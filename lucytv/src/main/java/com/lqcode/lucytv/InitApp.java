package com.lqcode.lucytv;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liqiong.lucy.module.impl.LucyKernel;

/**
 * Created by Administrator on 2016/9/13.
 */
public class InitApp {
    public static void initialize(Context context) {
        //
        LucyKernel.getInstance().initialize(context);
        //图片
        Fresco.initialize(context);
        //
        initSettings();
    }
    private static void initSettings(){
        TVSettings settings= TVSettings.getInstance();
        settings.setServerHead("http");
        settings.setServerIP("192.168.1.104");
        settings.setServerName("LucyTV");
        settings.setServerPort(":8080");
    }
}
