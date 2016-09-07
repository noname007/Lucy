package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/9/7.
 */
public abstract class CCTVListRequest extends MyRequest {
    public CCTVListRequest() {
        this.url = Constants.cctvListUrl;
        connent();
    }
}
