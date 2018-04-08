package com.jia.base.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Description: 事件总线 工具类
 * Created by jia on 2018/3/27.
 * 人之所以能，是相信能。
 */

public class EventBusUtils {

    /**
     * 绑定 接受者
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 解绑定
     * @param subscriber
     */
    public static void unregister(Object subscriber){
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送消息(事件)
     * @param event
     */
    public static void sendEvent(Object event){
        EventBus.getDefault().post(event);
    }

    /**
     * 发送 粘性 事件
     *
     * 粘性事件，在注册之前便把事件发生出去，等到注册之后便会收到最近发送的粘性事件（必须匹配）
     * 注意：只会接收到最近发送的一次粘性事件，之前的会接受不到。
     * @param event
     */
    public static void sendStickyEvent(Object event){
        EventBus.getDefault().postSticky(event);
    }
}
