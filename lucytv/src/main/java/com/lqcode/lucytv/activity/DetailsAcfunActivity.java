package com.lqcode.lucytv.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.AcfunItem;
import com.lqcode.lucytv.network.MovieRealUrlRequest;

/**
 * Created by Administrator on 2016/10/5.
 */
public class DetailsAcfunActivity extends BaseActivity {
    private String playerUrl;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        AcfunItem item = (AcfunItem) getIntent().getSerializableExtra("acfun_item");
        LucyController.uiHelp.toast(item.toString());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlayerActivity.class);
                intent.putExtra("path", playerUrl);
                startActivity(intent);
            }
        });
        getPlayerUrlByNet("http://www.acfun.tv/v/ac3096289?id=" + item.getVideoId());
    }

    private void getPlayerUrlByNet(String url) {
        new MovieRealUrlRequest(url) {
            @Override
            public void _onSuccess(String result) {
                if (result != "null") {
                    playerUrl = result;
                    fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark)));
                } else
                    LucyController.uiHelp.toast("decode error!");
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }
}
