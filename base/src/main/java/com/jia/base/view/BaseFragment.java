package com.jia.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jia.base.annotation.BindEventBus;
import com.jia.base.eventbus.EventBusUtils;

/**
 * Description: Fragment基类
 * Created by jia on 2018/3/27.
 * 人之所以能，是相信能。
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 若使用BindEventBus注解，则绑定EventBus
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBusUtils.register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 若使用BindEventBus注解，则解绑定EventBus
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBusUtils.unregister(this);
        }
    }
}
