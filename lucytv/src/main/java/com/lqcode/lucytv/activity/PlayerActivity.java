package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqiong.lucy.BaseActivity;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.player.AndroidMediaController;
import com.lqcode.lucytv.player.IMediaController;
import com.lqcode.lucytv.player.IjkVideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2016/9/4.
 */
public class PlayerActivity extends BaseActivity {
    private IjkVideoView ijkVideoView;
    private IMediaController mediaController;

    static {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        IjkMediaPlayer.native_setLogLevel(1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ijkVideoView = (IjkVideoView) findViewById(R.id.ijk_video_view);
//        String path = getIntent().getStringExtra("path");
       String path="http://27.221.44.53/youku/677248408023182AA45B" +
                "C53196/030020010057E50B3DC07A2D9B7D2FC09" +
                "69596-CA93-648F-6114-FF9C02D2456B.mp4";
        mediaController = new AndroidMediaController(getContext(), false);
        ijkVideoView.setMediaController(mediaController);
        ijkVideoView.setVideoPath(path);
        ijkVideoView.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ijkVideoView.stopPlayback();
        ijkVideoView.release(true);
//        ijkVideoView.stopBackgroundPlay();
        IjkMediaPlayer.native_profileEnd();
    }
}
