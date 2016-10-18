package com.lqcode.lucytv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.RealUrlsEntity;
import com.lqcode.lucytv.player.AndroidMediaController;
import com.lqcode.lucytv.player.IjkVideoView;
import com.lqcode.lucytv.player.Settings;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2016/9/4.
 */
public class PlayerActivity extends BaseActivity {
    private IjkVideoView ijkVideoView;
    //    private IMediaController mediaController;
    //視頻是否分段
    private boolean isSegmentation = false;
    private int currentSegmentation = 0;
    private RealUrlsEntity entity = null;
    private View mView;
    private AndroidMediaController mediaController;
    private int fullScreen = 0;

    static {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fullScreen = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE;
        mView = View.inflate(this, R.layout.activity_player, null);
        mView.setSystemUiVisibility(fullScreen);
        setContentView(mView);

        Settings mSettings = new Settings(this);

        ijkVideoView = (IjkVideoView) findViewById(R.id.ijk_video_view);
        Intent mIntent = getIntent();
        String path = mIntent.getStringExtra("path");
        if (TextUtils.isEmpty(path)) {
            entity = (RealUrlsEntity) mIntent.getSerializableExtra("RealUrlsEntity");
            isSegmentation = true;
            currentSegmentation = 0;
        } else {
            Log.e("liqiong", path);
        }
        mediaController = new AndroidMediaController(getContext(), true);
        mediaController.addHideOrShowListener(new AndroidMediaController.OnHideOrShowListener() {
            @Override
            public void hideOrShow(int hide) {
                if (hide != 0) {
                    mView.setSystemUiVisibility(fullScreen);
                }
            }
        });
        ijkVideoView.setMediaController(mediaController);
        if (isSegmentation) {
            ijkVideoView.setVideoPath(entity.getSrc()[currentSegmentation]);
        } else {
            ijkVideoView.setVideoPath(path);
        }

        ijkVideoView.start();
        ijkVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                if (entity.getSrc().length - 1 > currentSegmentation) {
                    ++currentSegmentation;
                    ijkVideoView.setVideoPath(entity.getSrc()[currentSegmentation]);
//                    ijkVideoView.resume();
                    ijkVideoView.start();
                }
            }
        });
        ijkVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.e("error", i + "===" + i1);
                if (i == -10000) {
                    LucyController.uiHelp.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ijkVideoView.resume();
                            ijkVideoView.start();
                        }
                    }, 3000);
//                    ijkVideoView.start();
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
        if (!ijkVideoView.isBackgroundPlayEnabled()) {
            ijkVideoView.stopPlayback();
            ijkVideoView.release(true);
            IjkMediaPlayer.native_profileEnd();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Log.e("liqiong","onKeyDown->KEYCODE_BACK");
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
