package com.jia.libnet;

import android.util.Log;


import com.jia.libnet.basemodel.BaseModel;
import com.jia.libnet.exception.ExceptionEngine;

import rx.Subscriber;

/**
 * Description: 回调封装
 * Created by jia on 2017/12/20.
 * 人之所以能，是相信能
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG", "onError: " + e.toString());
        onFail(ExceptionEngine.handleException(e));
    }

    @Override
    public void onNext(T t) {
        if (t instanceof BaseModel) {
            if (t == null) {
                onFail("未请求到数据");
            } else if (!((BaseModel) t).getErrorCode().equals("1")) {
                onFail(((BaseModel) t).getErrorMsg());
            } else {
                onSuccess(t);
            }
        } else {
            onSuccess(t);
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(String info);
}
