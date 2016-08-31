package com.liqiong.activityoptionsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by LiQiong on 2016/8/31.15:55
 */
public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView imageView2= (ImageView) findViewById(R.id.imageView2);
        imageView2.setTransitionName("imageView");

    }
}
