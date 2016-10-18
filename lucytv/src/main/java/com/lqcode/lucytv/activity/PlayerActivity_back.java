package com.lqcode.lucytv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.RealUrlsEntity;
import com.lqcode.lucytv.player.IjkVideoView;
import com.lqcode.lucytv.player.Settings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2016/9/4.
 */
public class PlayerActivity_back extends BaseActivity {
    private IjkVideoView ijkVideoView;
    //    private IMediaController mediaController;
    //視頻是否分段
    private boolean isSegmentation = false;
    private int currentSegmentation = 0;
    private RealUrlsEntity entity = null;
    private SeekBar seekBar;
    private int prveProgress;
    private TextView movieTimeTV;
    private int bufferSize=0;
    static {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View mView = View.inflate(this, R.layout.activity_player, null);
        mView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(mView);

        Settings mSettings = new Settings(this);

        ijkVideoView = (IjkVideoView) findViewById(R.id.ijk_video_view);
        movieTimeTV = (TextView) findViewById(R.id.movie_time_tv);
        Intent mIntent = getIntent();
        String path = mIntent.getStringExtra("path");
        if (TextUtils.isEmpty(path)) {
            entity = (RealUrlsEntity) mIntent.getSerializableExtra("RealUrlsEntity");
            isSegmentation = true;
            currentSegmentation = 0;
        } else {
            Log.e("liqiong", path);
        }
//        mediaController = new AndroidMediaController(getContext(), false);
//        ijkVideoView.setMediaController(mediaController);
        seekBar = (SeekBar) findViewById(R.id.player_seek_bar);
        if (isSegmentation) {
            ijkVideoView.setVideoPath(entity.getSrc()[currentSegmentation]);
        } else {
            ijkVideoView.setVideoPath(path);
        }

        ijkVideoView.start();
        ijkVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.e("liqiong", "onCompletion--->>>>" + seekBar.getProgress());
                prveProgress += seekBar.getProgress();
                if (entity.getSrc().length - 1 > currentSegmentation) {
                    ++currentSegmentation;
                    ijkVideoView.setVideoPath(entity.getSrc()[currentSegmentation]);
//                    ijkVideoView.resume();
                    ijkVideoView.start();
                }
            }
        });
        ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {

                if (isSegmentation) {
                    seekBar.setMax(entity.getTime());
                } else {
                    seekBar.setMax(ijkVideoView.getDuration());
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
                    },2000);
//                    ijkVideoView.start();
                }
                return true;
            }
        });

        ijkVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    if (isSegmentation) {
                        int tempPage = 0;
                        int tempSize = 0;
                        for (int i = 0; i < entity.getSrc().length; i++) {
                            tempPage = i;
                            int time = getTimeByUrl(entity.getSrc()[i]);
                            tempSize += time;
                            if (tempSize > progress) {
                                break;
                            }
                        }
                        int tempProgress = progress;
                        prveProgress = 0;
                        for (int i = 0; i < currentSegmentation; i++) {
                            prveProgress += getTimeByUrl(entity.getSrc()[i]);
                            tempProgress -= getTimeByUrl(entity.getSrc()[i]);
                        }

                        if (currentSegmentation == tempPage) {
                            ijkVideoView.seekTo(tempProgress);
                            ijkVideoView.start();
                        } else {
                            currentSegmentation = tempPage;
                            ijkVideoView.setVideoPath(entity.getSrc()[currentSegmentation]);
                            ijkVideoView.seekTo(tempProgress);
                            ijkVideoView.start();
                        }
                        seekBar.setProgress(progress);
                    } else {
                        ijkVideoView.seekTo(progress);
                        ijkVideoView.start();
                    }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        }).start();
    }

    private void updateSeekBar() {
        LucyController.uiHelp.postDelayed(new Runnable() {
            @Override
            public void run() {
                bufferSize+=ijkVideoView.getBufferPercentage();
                seekBar.setProgress(prveProgress + ijkVideoView.getCurrentPosition());
                int progressSize = seekBar.getProgress();
                LucyController.uiHelp.toast("bufferSize:"+bufferSize);
                int progressSecondSize = progressSize / 1000;
                int progressMinutes = progressSecondSize / 60;
                int progressSecond = progressSecondSize % 60;

                int progressSizeMax = seekBar.getMax();
                int progressSecondSizeMax = progressSizeMax / 1000;
                int progressMinutesMax = progressSecondSizeMax / 60;
                int progressSecondMax = progressSecondSizeMax % 60;

                movieTimeTV.setText(progressMinutes + ":" + progressSecond + "/" + progressMinutesMax + ":" + progressSecondMax);
                updateSeekBar();
            }
        }, 1000);
    }

    private int getTimeByUrl(String url) {
        Pattern pattern = Pattern.compile("d=[0-9]*");
        Matcher matcher = pattern.matcher(url);
        int time = 0;
        if (matcher.find()) {
            time = Integer.parseInt(matcher.group(0).replace("d=", ""));
        }
        return time;
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

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
////        super.onConfigurationChanged(newConfig);
//
//    }
}
