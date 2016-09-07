package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.Constants;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.tools.UiTool;

/**
 * Created by LiQiong on 16/9/1.
 */
public class DetailsActivity extends BaseActivity {
    private String liveName;
    private ImageView transitionTempIV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        liveName = getIntent().getStringExtra("LiveName");
        transitionTempIV = (ImageView) findViewById(R.id.details_header_transtion_temp_iv);
        transitionTempIV.setTransitionName("CCTVTextView");

        HandlerHeaderImage();
    }

    private void HandlerHeaderImage() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        SimpleDraweeView headerImage = (SimpleDraweeView) findViewById(R.id.details_header);
        headerImage.setImageURI(Constants.cctvThumnnailUrl + "?tvName=" + liveName + "&callback=" + System.currentTimeMillis());
        //图片的比例是16:9
        int imageWidth = LucyController.uiHelp.getMetrics().widthPixels;
        int imageHeight = imageWidth / 16 * 9;
        int logoHeight=UiTool.getSizeByView(transitionTempIV).getHeight()*2;
        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(imageWidth, imageHeight);
        params.setMargins(0, logoHeight, 0, 0);
        headerImage.setLayoutParams(params);
        CoordinatorLayout.LayoutParams aparams = new CoordinatorLayout.LayoutParams(imageWidth, imageHeight + logoHeight);
        appBarLayout.setLayoutParams(aparams);
        Log.e("width.height-->>>", imageWidth + "xxx" + imageHeight + "yyy" + UiTool.getSizeByView(transitionTempIV).getHeight());
    }
}