package com.liqiong.activityoptionsdemo;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Shader;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ImageView iv;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setTransitionName("imageView");
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, iv, iv.getTransitionName()).toBundle());
            }
        });
    }
}
