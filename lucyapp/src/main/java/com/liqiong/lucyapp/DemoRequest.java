package com.liqiong.lucyapp;

import com.liqiong.lucy.http.MyRequest;

/**
 * Created by LiQiong on 2016/8/27.16:54
 */
public abstract class DemoRequest extends MyRequest {
    public DemoRequest() {
        this.url = "http://tieba.baidu.com";
        connent();
    }
}