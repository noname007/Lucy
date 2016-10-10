package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.http.MyRequest;
import com.liqiong.lucy.module.impl.LucyController;
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
        String path = getIntent().getStringExtra("path");
        Log.e("liqiong", path);
        mediaController = new AndroidMediaController(getContext(), false);
        ijkVideoView.setMediaController(mediaController);
        ijkVideoView.setVideoPath(path);
        ijkVideoView.start();
        ijkVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.e("error",i+"==="+i1);
                if(i==-10000){
                    ijkVideoView.resume();
                }
                return true;
            }
        });
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
