package com.liqiong.lucy.http;

import android.util.Log;

import com.liqiong.lucy.annotation.Autowired;
import com.liqiong.lucy.module.impl.LucyController;

import java.util.HashMap;

/**
 * Created by LiQiong on 2016/8/27.16:44
 */
public abstract class MyRequest implements RequestCallBack {

    protected String url;
    protected String test;
    protected boolean isAddBaseParam = true;
    private HashMap<String, Object> paramMap;

    protected void connect() {
        if (isAddBaseParam)
            addBaseParam();
        LucyController.request._connect(url, paramMap, this);
    }

    protected MyRequest addParam(String key, Object object) {
        if (paramMap == null)
            paramMap = new HashMap<>();
        paramMap.put(key, object);
        return this;
    }

    private MyRequest addBaseParam() {
        if (paramMap == null)
            paramMap = new HashMap<>();

        long currentTimeMillis = System.currentTimeMillis();
        paramMap.put("time", currentTimeMillis);
        long time_encode = currentTimeMillis * 6 + 1991 + 327 + 511 + 2537;
        paramMap.put("t_encode", time_encode);

        return this;
    }
}