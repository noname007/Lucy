package com.liqiong.lucy.http;

import com.liqiong.lucy.module.Module;

/**
 * Created by LiQiong on 2016/8/27.16:09
 */
public abstract class ARequest extends Module {
    public abstract void _connect(String url, RequestCallBack callBack);

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}

interface RequestCallBack {
    public void _onSuccess(String result);

    public void _onFail(String result);
}