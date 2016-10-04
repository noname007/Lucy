package com.liqiong.lucy.http;

import com.liqiong.lucy.annotation.Autowired;
import com.liqiong.lucy.module.impl.LucyController;

import java.util.HashMap;

/**
 * Created by LiQiong on 2016/8/27.16:44
 */
public abstract class MyRequest implements RequestCallBack {

    protected String url;
    protected String test;
    private HashMap<String, Object> paramMap = new HashMap<>();

    protected void connect() {
        LucyController.request._connect(url,paramMap, this);
    }

    protected MyRequest addParam(String key, Object object) {
        paramMap.put(key, object);
        return this;
    }
}