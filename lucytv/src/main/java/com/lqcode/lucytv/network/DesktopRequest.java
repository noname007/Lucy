package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/9/2.
 */
public abstract class DesktopRequest extends MyRequest {
    public DesktopRequest() {
        this.url = Constants.desktopUrl;
        connect();
    }
}