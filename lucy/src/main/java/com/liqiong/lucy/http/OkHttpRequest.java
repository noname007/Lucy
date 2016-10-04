package com.liqiong.lucy.http;

import android.util.Log;

import com.liqiong.lucy.module.impl.LucyController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiQiong on 2016/8/27.16:20
 */
public class OkHttpRequest extends ARequest {

    private OkHttpClient client = new OkHttpClient();

    @Override
    public void _connect(String url, HashMap<String, Object> paramMap, RequestCallBack callBack) {
        new RequestThread(url, paramMap, callBack).start();
    }

    class RequestThread extends Thread {
        private String url;
        private RequestCallBack callBack;

        public RequestThread(String url, HashMap<String, Object> paramMap, RequestCallBack callBack) {
            if (paramMap.size() > 0)
                url += "?";
            Iterator<Map.Entry<String, Object>> iterator = paramMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                url += entry.getKey();
                url += "=";
                url += entry.getValue();
                url += "&";
            }
            this.url = url;
            this.callBack = callBack;
        }

        @Override
        public void run() {
            Log.e("liqiong",this.url);
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                final String result = response.body().string();
                LucyController.uiHelp.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack._onSuccess(result);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
