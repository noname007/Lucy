package com.liqiong.lucy.http;

import okhttp3.OkHttpClient;

/**
 * Created by LiQiong on 2016/8/27.15:58
 */
public abstract class HttpRequest implements IRequest {
    OkHttpClient client = new OkHttpClient();
    @Override
    public void _connect(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
