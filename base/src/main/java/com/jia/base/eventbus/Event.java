package com.jia.base.eventbus;

/**
 * Description: 事件的封装类
 * Created by jia on 2018/3/27.
 * 人之所以能，是相信能。
 */

public class Event<T> {
    private int code;
    private T data;


    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
