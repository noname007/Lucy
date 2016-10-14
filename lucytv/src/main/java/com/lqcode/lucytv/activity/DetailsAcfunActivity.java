package com.lqcode.lucytv.activity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.AcfunItem;
import com.lqcode.lucytv.entity.RealUrlsEntity;
import com.lqcode.lucytv.network.MovieRealUrlRequest;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class DetailsAcfunActivity extends DetailsVideoActivity {
    private String playerUrl;
    private FloatingActionButton fab;
    private List<RealUrlsEntity> urlsEntityList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        AcfunItem item = (AcfunItem) getIntent().getSerializableExtra("acfun_item");
        SimpleDraweeView movie_pic = (SimpleDraweeView) findViewById(R.id.details_movie_pic);
        movie_pic.setImageURI(item.getImage());
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        title_tv.setText(item.getTitle());
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] mString = new String[urlsEntityList.size()];
                for (int i = 0; i < urlsEntityList.size(); i++) {
                    mString[i] = urlsEntityList.get(i).getVideo_profile();
                }
                new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert).
                        setTitle("播放列表").setItems(mString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callPlayVideo(urlsEntityList.get(i).getSrc()[0]);
                    }
                }).show();
            }
        });
        getPlayerUrlByNet("http://www.acfun.tv/v/ac3096289?id=" + item.getVideoId());
    }

    private void getPlayerUrlByNet(String url) {
        new MovieRealUrlRequest(url) {
            @Override
            public void onSuccess(List<RealUrlsEntity> urls) {
                urlsEntityList=urls;
                fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)));
            }

//            @Override
//            public void _onSuccess(final String result) {
//                if (result != "null") {
//                    playerUrl = result;
//                    fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)));
//
//                } else
//                    LucyController.uiHelp.toast("decode error!");
//            }

            @Override
            public void _onFail(String result) {

            }
        };
    }
}
