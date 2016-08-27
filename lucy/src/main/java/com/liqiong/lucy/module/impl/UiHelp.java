package com.liqiong.lucy.module.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/24.
 */
public class UiHelp extends Module {
    private Toast mToast = null;
    private Handler mHandler = null;

    @Override
    public void onCreate() {
        mToast = Toast.makeText(LucyKernel.getInstance().getContext(), "", Toast.LENGTH_SHORT);
        mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Override
    public void onDestroy() {
        Log.e("liqiong", " UiHelp onDestroy");
    }

    public void toast(String value) {
        mToast.setText(value);
        mToast.show();
    }

    public void toast(int R_String) {
        mToast.setText(LucyKernel.getInstance().getContext().getResources().getString(R_String));
        mToast.show();
    }

    public boolean postDelayed(Runnable r, long delayMillis) {
        return mHandler.postDelayed(r, delayMillis);
    }

    public boolean post(Runnable r) {
        return mHandler.post(r);
    }
}