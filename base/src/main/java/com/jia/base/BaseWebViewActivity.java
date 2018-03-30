package com.jia.base;

import android.os.Bundle;

import com.jia.base.annotation.BindEventBus;
import com.jia.base.eventbus.Event;

import org.greenrobot.eventbus.Subscribe;

/**
 * Description:
 * Created by jia on 2018/3/29.
 * 人之所以能，是相信能。
 */
@BindEventBus
public class BaseWebViewActivity extends BaseActivity {

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webview);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Subscribe
    public void onGetEvent(Event event){

    }
}
