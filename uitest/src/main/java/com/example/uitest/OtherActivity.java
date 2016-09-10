package com.example.uitest;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/9/9.
 */
public class OtherActivity extends AppCompatActivity {
    private ImageView iv_main;
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        cardView = (CardView) findViewById(R.id.cardlist_item);
        iv_main = (ImageView) findViewById(R.id.iv_main);

        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.e("liqiong", "onTransitionStart");

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.e("liqiong", "onTransitionEnd");
                animClick();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                Log.e("liqiong", "onTransitionCancel");
            }

            @Override
            public void onTransitionPause(Transition transition) {
                Log.e("liqiong", "onTransitionPause");
            }

            @Override
            public void onTransitionResume(Transition transition) {
                Log.e("liqiong", "onTransitionResume");
            }
        });
    }

    public void animClick() {
        Animator animator= ViewAnimationUtils.createCircularReveal(iv_main,500,500,20,2000);
        animator.setDuration(500);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                iv_main.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                cardView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }
}
