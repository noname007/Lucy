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

        String LiveName = getIntent().getStringExtra("LiveName");

        String path = "http://cctv1.vtime.cntv.wscdns.com:8000/live/no/14_/seg0/index.m3" +
                "u8?AUTH=8Cr+Bi27mOjDTa0CTI3TdTIWWYU9wxcGUk/pBiszYyYpqh0GHm48" +
                "brQj1Yn7kqR/icR650ZuDNaRq5o2EGXQUQ==";
        path = "http://59.109.98.91/m3u8/ws_hunanwsHD_800/desc.m3u8?s" +
                "tream_id=ws_hunanwsHD_800&path=59.109.98.94,59.109.99.21&amltag=" +
                "27&mltag=27&platid=10&splatid=1001&qos=4&fcheck=0&uid=3689423543" +
                ".rp&keyitem=dpoOd-l3AnupIUuBI8VtFwmKzWltcfnZpngeV5Yo3I1Oleh6yGrbVeKR" +
                "oRQ.&ntm=1473197400&nkey2=d5ec9d1910b8944e5ba1886cc2f4c433&nkey=ca35" +
                "98df7df42baed3fe2237302e5dc3&tag=live&video_type=m3u8&useloc=0&mslice" +
                "=3&uidx=0&errc=0&gn=1910&lrtmcd=107&buss=27&cips=219.232.34.183&geo=" +
                "CN-1-5-10&tmn=1473179277&pnl=1910,1910,1910,511&ext=m3u8&termid=1&hwt" +
                "ype=un&playid=1&sign=live_web&ostype=Windows10&p1=1&p2=10&p3=-&uuid=B" +
                "28200279DC4CE77D1050D9EDD5DB07C" +
                "62373382_0&vkit=20160831&station=305&tm=1473179179&key=d7426632f95d4585002c7e18b86194ad";

        path = "http://dmav1.junjichu.net/dmaevent?JSv=8.4.3&DMt=e&DMu=" +
                "6s5z3y8xiM4we7ovm9BL&DMac=QkE1NjYxOURBMjEzNjVD.QkQ3O" +
                "EVCQTA3QjIzNTU0&DMec=%E5%9C%A8%E7%BA%BF%E7%BB%9F%E" +
                "8%AE%A1&DMel=%E7%9B%B4%E6%92%AD&DMeo=%E5%B1%B1%E4%" +
                "B8%9C%E5%8D%AB%E8%A7%86%7C474%7Chttp%3A%2F%2Flive.wasu.c" +
                "n%2Fshow%2Fid%2F474%7C65536%7C307200%7C%E5%8D%AB%E8%A7%86%E5%8F%B0%7" +
                "CPC%7CChinaNet%7Clivertmp1-cnc.wasu.cn&DMev=1&DMet=1473221440&DMcs=utf-8&DMr=1";
        ijkVideoView.setVideoPath(path);
        ijkVideoView.start();

    }

}
