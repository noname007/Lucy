package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/9/11.
 */
public abstract class MovieListRequest extends MyRequest {
    public MovieListRequest(int pageNo){
        this.url= Constants.movieListUrl;
        addParam("pageNo",pageNo);
        connect();
    }
}