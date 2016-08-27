package com.liqiong.lucy.http;

import com.liqiong.lucy.annotation.Autowired;
import com.liqiong.lucy.annotation.AutowiredModule;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by LiQiong on 2016/8/27.16:44
 */
public abstract class MyRequest implements RequestCallBack {
    @Autowired(clazz = OkHttpRequest.class)
    private IRequest request;

    protected String url;
    private HashMap<String, Objects> paramMap = new HashMap<>();

    protected void connent() {
        request._connect(url, this);
    }
}