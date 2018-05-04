package com.mangosteen.headline;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import yanzhikai.textpath.PathAnimatorListener;
import yanzhikai.textpath.SyncTextPathView;


/**
 * Description: 启动页
 * Created by jia on 2018/4/28.
 * 人之所以能，是相信能。
 */

public class SplashActivity extends Activity {

    private SyncTextPathView path_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        path_tv = findViewById(R.id.path_tv);
        path_tv.setTypeface(Typeface.SANS_SERIF);
        path_tv.setAnimatorListener(new PathAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

        //从无到显示
        path_tv.startAnimation(0,1);
    }
}
