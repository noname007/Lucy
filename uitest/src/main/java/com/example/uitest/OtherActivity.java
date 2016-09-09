package com.example.uitest;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2016/9/9.
 */
public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        CardView cardView = (CardView) findViewById(R.id.cardlist_item);
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.e("liqiong", "onTransitionStart");

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.e("liqiong", "onTransitionEnd");
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
//        cardView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
//                Log.e("liqiong", view.toString());
//                Log.e("liqiong", i+"");
//                Log.e("liqiong", i1+"");
//                Log.e("liqiong", i2+"");
//                Log.e("liqiong", i3+"");
//                Log.e("liqiong", i4+"");
//                Log.e("liqiong", i5+"");
//                Log.e("liqiong", i6+"");
//                Log.e("liqiong", i7+"");
//            }
//        });

//        cardView.setLayoutAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Log.e("liqiong","onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Log.e("liqiong","onAnimationEnd");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                Log.e("liqiong","onAnimationRepeat");
//            }
//        });

//        cardView.getLayoutTransition().addTransitionListener(new LayoutTransition.TransitionListener() {
//            @Override
//            public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
//                Log.e("liqiong","startTransition");
//            }
//
//            @Override
//            public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
//                Log.e("liqiong","endTransition");
//            }
//        });

//        cardView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Log.e("liqiong","onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Log.e("liqiong","onAnimationEnd");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                Log.e("liqiong","onAnimationRepeat");
//            }
//        });
    }
}
