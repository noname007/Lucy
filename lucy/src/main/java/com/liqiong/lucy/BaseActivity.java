package com.liqiong.lucy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liqiong.lucy.module.impl.LucyKernel;

/**
 * Created by LiQiong on 16/9/1.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        LucyKernel.getInstance().onStart(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LucyKernel.getInstance().onStart(this);
    }
}
