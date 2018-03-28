package com.mangosteen.headline.home.view;

import android.os.Bundle;

import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.base.annotation.BindEventBus;
import com.jia.base.eventbus.Event;
import com.mangosteen.headline.R;

import org.greenrobot.eventbus.Subscribe;

@BindEventBus
public class MainActivity extends BaseActivity {

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
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
    public void onHandleEvent(Event e){

    }
}
