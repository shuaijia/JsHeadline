package com.jia.base;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Description: Presenter的根父类
 * Created by jia on 2016/10/27.
 * 人之所以能，是相信能
 */
public abstract class BasePresenter<T> {

    //View接口类型的软引用
    protected Reference<T> mViewRef;

    private LifecycleProvider<ActivityEvent> provider;

    public BasePresenter(LifecycleProvider<ActivityEvent> provider) {
        this.provider = provider;
    }

    public LifecycleProvider<ActivityEvent> getProvider() {
        return provider;
    }

    public void attachView(T view) {
        //建立关系
        mViewRef = new SoftReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
