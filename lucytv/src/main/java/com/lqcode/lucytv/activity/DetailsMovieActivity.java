package com.lqcode.lucytv.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.MovieInfo;
import com.lqcode.lucytv.entity.MovieUrl;
import com.lqcode.lucytv.network.MoviePlayerRequest;
import com.lqcode.lucytv.network.MovieRealUrlRequest;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class DetailsMovieActivity extends BaseActivity {
    private FloatingActionButton fab;
    private List<MovieUrl> movieUrls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MovieInfo movieInfo = (MovieInfo) getIntent().getSerializableExtra("movie_info");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), PlayerActivity.class);
//                intent.putExtra("path", "playerUrl");
//                startActivity(intent);
                final String[] mString = new String[movieUrls.size()];
                for (int i = 0; i < movieUrls.size(); i++) {
                    mString[i] = movieUrls.get(i).getUrl();
                }

                new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert).
                        setTitle("播放列表").setItems(mString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getPlayerUrlByNet(mString[i]);
                    }
                }).show();

            }
        });
        SimpleDraweeView detailsMoviePic = (SimpleDraweeView) findViewById(R.id.details_movie_pic);
        detailsMoviePic.setTransitionName("movie_item_pic");
        detailsMoviePic.setImageURI(movieInfo.getSmallImg());
//        DisplayMetrics metrics = LucyController.uiHelp.getMetrics();
//        detailsMoviePic.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(metrics.widthPixels, metrics.widthPixels / 2));


//        SimpleDraweeView detailsHead = (SimpleDraweeView) findViewById(R.id.details_header);
//        detailsHead.setImageURI("res://com.lqcode.lucytv/"+R.mipmap.avengers);
//        DisplayMetrics metrics = LucyController.uiHelp.getMetrics();
//        detailsHead.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(metrics.widthPixels, metrics.widthPixels / 2));


//        FloatingActionButton floatingActionButton= (FloatingActionButton) findViewById(R.id.fab_test);


        getPlayerListUrlByNet(movieInfo.getUuid());
    }

    private void getPlayerListUrlByNet(String uuid) {
        new MoviePlayerRequest(uuid) {
            @Override
            public void _onSuccess(String result) {
                fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)));
                movieUrls = JSON.parseArray(result, MovieUrl.class);
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }

    private void getPlayerUrlByNet(String url) {
        if(url.length()>50){
            Intent intent = new Intent(getContext(), PlayerActivity.class);
            intent.putExtra("path", url);
            startActivity(intent);
            return;
        }
        new MovieRealUrlRequest(url) {
            @Override
            public void _onSuccess(String result) {
                if (result != "null"){

                    Intent intent = new Intent(getContext(), PlayerActivity.class);
                    intent.putExtra("path", result);
                    startActivity(intent);
                }else{
                    LucyController.uiHelp.toast("decode error!");
                }
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }
}
