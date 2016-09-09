package com.example.uitest;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CardView cardView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = (CardView) findViewById(R.id.cardlist_item);
    }

    public void startOtherActivity(View view) {
        Intent mIntent = new Intent(this, OtherActivity.class);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this, cardView, cardView.getTransitionName()).toBundle());
    }
}