package com.liqiong.lucyapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by LiQiong on 2016/8/30.17:41
 */
public class DetailsActivity extends Activity {

    private ImageView detailsIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsIV = (ImageView) findViewById(R.id.details_iv);


    }
}
