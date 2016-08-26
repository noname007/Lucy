package com.liqiong.lucy.module.impl;

import android.content.Context;
import android.os.DeadObjectException;
import android.util.Log;
import android.widget.Toast;

import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/24.
 */
public class UiHelp extends Module {
    private Toast mToast = null;

    @Override
    public void onCreate() {
        Log.e("liqiong","UiHelp onCreate!");
        mToast = Toast.makeText(LucyKernel.getInstance().getContext(), "", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDestroy() {
        Log.e("liqiong"," UiHelp onDestroy");
    }

    public void fuck(String value) {
        mToast.setText(value);
        mToast.show();
    }

    public void fuck(int R_String) {
        mToast.setText(LucyKernel.getInstance().getContext().getResources().getString(R_String));
        mToast.show();
    }
}