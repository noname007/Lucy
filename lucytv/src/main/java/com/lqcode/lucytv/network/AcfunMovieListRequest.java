package com.lqcode.lucytv.network;

import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;

/**
 * Created by Administrator on 2016/10/5.
 */
public abstract class AcfunMovieListRequest extends MyRequest {
    public AcfunMovieListRequest() {
        this.url = Constants.acfunUrl;
        this.isAddBaseParam = false;
        connect();
    }
}
