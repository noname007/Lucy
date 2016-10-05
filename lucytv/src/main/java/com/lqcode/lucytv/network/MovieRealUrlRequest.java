package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/9/11.
 */
public abstract class MovieRealUrlRequest extends MyRequest {

    public MovieRealUrlRequest(String url) {
        this.url = Constants.movieRealUrl;
        addParam("url",url);
        connect();
    }
}