package com.lqcode.lucytv.network;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.liqiong.lucy.http.MyRequest;
import com.lqcode.lucytv.Constants;
import com.lqcode.lucytv.entity.RealUrlsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public abstract class MovieRealUrlRequest extends MyRequest {

    public abstract void onSuccess(List<RealUrlsEntity> urls);

    public MovieRealUrlRequest(String url) {
        this.url = Constants.movieRealUrl;
        addParam("url", url);
        connect();
    }

    @Override
    public void _onSuccess(String result) {
        List<RealUrlsEntity> urls = new ArrayList<>();
        String[] resultString = result.split("-----------------------------------\n");
        for (String s : resultString) {
            if (!TextUtils.isEmpty(s))
                urls.add(JSON.parseObject(s, RealUrlsEntity.class));
        }
        onSuccess(urls);
    }
}