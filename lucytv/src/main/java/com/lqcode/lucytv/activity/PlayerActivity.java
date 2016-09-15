package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqiong.lucy.BaseActivity;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.player.AndroidMediaController;
import com.lqcode.lucytv.player.IMediaController;
import com.lqcode.lucytv.player.IjkVideoView;

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
        String path = getIntent().getStringExtra("path");
        mediaController=new AndroidMediaController(getContext(),false);
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
