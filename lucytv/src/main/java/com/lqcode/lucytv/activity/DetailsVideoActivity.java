package com.lqcode.lucytv.activity;

import android.content.Intent;
import android.text.TextUtils;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;

/**
 * Created by Administrator on 2016/10/8.
 */
public class DetailsVideoActivity extends BaseActivity {
    public void callPlayVideo(String playUrl) {
        if (!TextUtils.isEmpty(playUrl) && playUrl.contains("http://")) {
            Intent intent = new Intent(getContext(), PlayerActivity.class);
            intent.putExtra("path", playUrl);
            startActivity(intent);
        } else {
            LucyController.uiHelp.toast("不能播放！");
        }
    }
}
