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
    private IjkVideoView ijkVideoView;

    static {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ijkVideoView = (IjkVideoView) findViewById(R.id.ijk_video_view);
        String path = getIntent().getStringExtra("path");
        ijkVideoView.setVideoPath(path);
        ijkVideoView.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ijkVideoView.stopPlayback();
    }
}
