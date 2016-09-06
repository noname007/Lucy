package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.liqiong.lucy.BaseActivity;
import com.lqcode.lucytv.R;

/**
 * Created by LiQiong on 16/9/1.
 */
public class DetilsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detils);
        String liveName = getIntent().getStringExtra("LiveName");
        TextView textView = (TextView) findViewById(R.id.header);
        textView.setTransitionName("CCTVTextView");
    }
}