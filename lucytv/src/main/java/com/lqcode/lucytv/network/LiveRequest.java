package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/9/5.
 */
public abstract class LiveRequest extends MyRequest {
    public LiveRequest() {
        this.url = Constants.liveUrl;
        connect();
    }
}
