package com.lqcode.lucytv.activity;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqiong.lucy.BaseActivity;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.player.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2016/9/4.
 */
public class PlayerActivity extends BaseActivity {

    static {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        IjkVideoView ijkVideoView = (IjkVideoView) findViewById(R.id.ijk_video_view);

        String LiveName= getIntent().getStringExtra("LiveName");

        String path = "http://cctv1.vtime.cntv.wscdns.com:8000/live/no/14_/seg0/index.m3" +
                "u8?AUTH=8Cr+Bi27mOjDTa0CTI3TdTIWWYU9wxcGUk/pBiszYyYpqh0GHm48" +
                "brQj1Yn7kqR/icR650ZuDNaRq5o2EGXQUQ==";

        ijkVideoView.setVideoPath(path);
        ijkVideoView.start();

    }

}
