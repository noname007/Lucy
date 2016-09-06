package com.lqcode.player;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = "http://cctv1.vtime.cntv.wscdns.com:8000/live/no/14_/seg0/index.m3" +
                "u8?AUTH=8Cr+Bi27mOjDTa0CTI3TdTIWWYU9wxcGUk/pBiszYyYpqh0GHm48" +
                "brQj1Yn7kqR/icR650ZuDNaRq5o2EGXQUQ==";

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        IjkVideoView mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView.setVideoPath(path);
        mVideoView.start();
    }

}
