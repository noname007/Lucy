package com.liqiong.lucy.http;

import com.liqiong.lucy.module.impl.LucyController;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiQiong on 2016/8/27.16:20
 */
public class OkHttpRequest extends ARequest {

    private OkHttpClient client = new OkHttpClient();

    @Override
    public void _connect(String url, RequestCallBack callBack) {
        new RequestThread(url,callBack).start();
    }

    class RequestThread extends Thread {
        private String url;
        private RequestCallBack callBack;
        public RequestThread(String url,RequestCallBack callBack) {
            this.url = url;
            this.callBack=callBack;
        }

        @Override
        public void run() {
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
