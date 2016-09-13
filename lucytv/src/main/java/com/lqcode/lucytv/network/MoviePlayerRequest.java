package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/9/11.
 */
public abstract class MoviePlayerRequest extends MyRequest {

    public MoviePlayerRequest(String id) {
        this.url = Constants.moviePlayerUrl + id;
        connect();
    }
}