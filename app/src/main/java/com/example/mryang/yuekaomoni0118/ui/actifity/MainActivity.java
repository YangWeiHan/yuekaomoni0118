package com.example.mryang.yuekaomoni0118.ui.actifity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.ui.weigeb.CrilceConsuView;

public class MainActivity extends AppCompatActivity {
        private CrilceConsuView draw_circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draw_circle = findViewById(R.id.draw_circle);


        ObjectAnimator setBackGround = ObjectAnimator.ofInt(draw_circle, "backgroundColor", Color.BLUE, Color.RED);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(draw_circle, "translationX", 500);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(draw_circle, "translationY", 800);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        animatorSet.playTogether(translationX,translationY,setBackGround);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }


}
