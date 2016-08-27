package com.liqiong.lucy.http;

/**
 * Created by LiQiong on 2016/8/27.16:09
 */
public interface IRequest {
    public void _connect(String url, RequestCallBack callBack);
}

interface RequestCallBack {
    public void _onSuccess(String result);

    public void _onFail(String result);
}