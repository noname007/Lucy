package com.liqiong.lucy.module.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/24.
 */
public class UiHelp extends Module {
    private Toast toast = null;
    private Handler mHandler = null;
    private DisplayMetrics metrics = null;

    @Override
    public void onCreate() {
        toast = Toast.makeText(LucyKernel.getInstance().getContext(), "", Toast.LENGTH_SHORT);
        mHandler = new Handler(Looper.getMainLooper());
        metrics = new DisplayMetrics();
        ((Activity) LucyKernel.getInstance().getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    @Override
    public void onDestroy() {
        Log.e("liqiong", " UiHelp onDestroy");
    }

    public void toast(String value) {
        toast.setText(value);
        toast.show();
    }

    public void toast(int R_String) {
        toast.setText(LucyKernel.getInstance().getContext().getResources().getString(R_String));
        toast.show();
    }

    public boolean postDelayed(Runnable r, long delayMillis) {
        return mHandler.postDelayed(r, delayMillis);
    }

    public boolean post(Runnable r) {
        return mHandler.post(r);
    }

//=====================================================

    public DisplayMetrics getMetrics() {
        return metrics;
    }
}