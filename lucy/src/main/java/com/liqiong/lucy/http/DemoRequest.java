package com.liqiong.lucy.http;

/**
 * Created by LiQiong on 2016/8/27.16:54
 */
public abstract class DemoRequest extends MyRequest {
    public DemoRequest() {
        this.url = "http://www.lqcode.com";
        connent();
    }
}