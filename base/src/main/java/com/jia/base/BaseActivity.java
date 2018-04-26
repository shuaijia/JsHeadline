package com.jia.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.jia.base.annotation.BindEventBus;
import com.jia.base.eventbus.EventBusUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhy.changeskin.SkinManager;

/**
 * Description: Activity 基类
 * Created by jia on 2018/1/19.
 * 人之所以能，是相信能
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends RxAppCompatActivity {

    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().register(this);
        // 若使用BindEventBus注解，则绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.register(this);
        }

        mContext = BaseActivity.this;

        initActivityView(savedInstanceState);

        //创建presenter
        mPresenter = createPresenter();

        // view与presenter绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }

        initView();

        initData();

    }


    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);

    /**
     * 加载页面元素
     */
    protected abstract void initView();

    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract void initData();

    public void setStatusColor(String theme) {
        Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor(theme));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
        // 若使用BindEventBus注解，则解绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.unregister(this);
        }
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}
