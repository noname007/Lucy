package com.lqcode.lucytv.activity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.entity.RealUrlsEntity;

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

    public void callPlayVideo(RealUrlsEntity entity) {
        if (entity != null) {
            Intent intent = new Intent(getContext(), PlayerActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("RealUrlsEntity", entity);
            intent.putExtras(mBundle);
            startActivity(intent);
        } else {
            LucyController.uiHelp.toast("不能播放！");
        }
    }
}
