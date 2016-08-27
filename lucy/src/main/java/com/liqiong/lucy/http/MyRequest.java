package com.liqiong.lucy.http;

import java.util.HashMap;
import java.util.Objects;

import okhttp3.OkHttpClient;

/**
 * Created by LiQiong on 2016/8/27.16:44
 */
public abstract class MyRequest extends OkHttpRequest {
    private HashMap<String, Objects> paramMap = new HashMap<>();

    public void connent(String url) {
        _connect(url);
    }
}
