package com.lqcode.lucytv.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.Constants;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.CCTVPlayerUrl;
import com.lqcode.lucytv.network.CCTVPlayerUrlRequest;
import com.lqcode.lucytv.tools.UiTool;

/**
 * Created by LiQiong on 16/9/1.
 */
public class DetailsLiveActivity extends DetailsVideoActivity {
    private String liveName;
    private ImageView transitionTempIV;
    private String truePath = null;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        liveName = getIntent().getStringExtra("LiveName");
        transitionTempIV = (ImageView) findViewById(R.id.details_header_transtion_temp_iv);

        handlerHeaderImage();
        playerByNet(liveName);
        playerSetting();
    }

    private void playerSetting() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
//        floatingActionButton.setBackgroundTintList();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPlayVideo(truePath);
            }
        });
    }

    private void handlerHeaderImage() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        SimpleDraweeView headerImage = (SimpleDraweeView) findViewById(R.id.details_movie_pic);
        headerImage.setImageURI(Constants.cctvThumbnailUrl + "?tvName=" + liveName + "&callback=" + System.currentTimeMillis());
        //图片的比例是16:9
        int imageWidth = LucyController.uiHelp.getMetrics().widthPixels;
        int imageHeight = imageWidth / 16 * 9;
        int logoHeight = UiTool.getSizeByView(transitionTempIV).getHeight() * 2;
        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(imageWidth, imageHeight);
        params.setMargins(0, logoHeight, 0, 0);
        headerImage.setLayoutParams(params);
        CoordinatorLayout.LayoutParams aparams = new CoordinatorLayout.LayoutParams(imageWidth, imageHeight + logoHeight);
        appBarLayout.setLayoutParams(aparams);
    }

    int requestCount = 0;

    private void playerByNet(final String tvName) {
        new CCTVPlayerUrlRequest(tvName) {
            @Override
            public void _onSuccess(String result) {
                CCTVPlayerUrl playerUrl = JSON.parseObject(result, CCTVPlayerUrl.class);
                //TODO
//                if (playerUrl.getFlv_url().getFlv2().contains("cloudcdn")) {
                truePath = playerUrl.getFlv_url().getFlv2();
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)));
//                } else {
//                    if (requestCount < 5) {
//                        playerByNet(tvName);
//                        ++requestCount;
//                    }
//                }
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }
}