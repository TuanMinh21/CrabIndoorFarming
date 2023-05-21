package com.iot.crabindoorfarming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.iot.crabindoorfarming.authentication.signin.SignInActivity;
import com.iot.crabindoorfarming.common.GlobalFunction;
import com.iot.crabindoorfarming.databinding.ActivitySplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        initialAnimation();
        splashDuration();
    }

    private void initialAnimation() {
        Animation topAnimation, bottomAnimation;
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        viewBinding.logo.setAnimation(topAnimation);
        viewBinding.appName.setAnimation(bottomAnimation);
    }

    private void splashDuration() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GlobalFunction.startActivity(SplashScreenActivity.this, SignInActivity.class);
            }
        }, 3000);
    }
}