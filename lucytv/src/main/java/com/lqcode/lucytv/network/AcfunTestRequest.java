package com.lqcode.lucytv.network;

import android.util.Log;

import com.liqiong.lucy.http.MyRequest;

/**
 * Created by Administrator on 2016/10/10.
 */

public abstract class AcfunTestRequest extends MyRequest {
    public AcfunTestRequest(String path) {
        this.url = path;
        connect();
    }


    @Override
    public void _onFail(String result) {

    }
}
