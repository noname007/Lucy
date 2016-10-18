package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/10/18.
 */

public abstract class MovieSearchRequest extends MyRequest {
    public MovieSearchRequest(String likeName) {
        this.url = Constants.movieSearchUrl;
        addParam("name", likeName);
        connect();
    }
}
