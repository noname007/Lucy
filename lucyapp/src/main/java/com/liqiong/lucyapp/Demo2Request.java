package com.liqiong.lucyapp;

import com.liqiong.lucy.http.MyRequest;

/**
 * Created by LiQiong on 2016/8/29.17:22
 */
public abstract class Demo2Request extends MyRequest {
    public Demo2Request() {
        this.url = "http://www.baidu.com";
        connect();
    }
}