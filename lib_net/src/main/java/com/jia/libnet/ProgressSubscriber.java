package com.jia.libnet;

import android.util.Log;

import rx.Subscriber;

/**
 * Describtion:带有加载中进度条的subscriber
 * Created by jia on 2017/6/7.
 * 人之所以能，是相信能
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {

//    private LoadingDialog dialog;


    public ProgressSubscriber() {

//        dialog = new LoadingDialog(BaseApplication.getApplicationcontext(), R.layout.view_tips_loading2);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setDimAmount(0);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Tag", "onStart");
//        dialog.show();
    }

    @Override
    public void onCompleted() {
        Log.e("Tag", "onCompleted");
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//            }
//        }, 6000);
    }
}
