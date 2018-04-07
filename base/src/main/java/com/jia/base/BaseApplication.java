package com.jia.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.Utils;
import com.jia.base.annotation.Action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import dalvik.system.DexFile;

/**
 * Description: Application基类
 * Created by jia on 2018/3/27.
 * 人之所以能，是相信能。
 */

public class BaseApplication extends Application {

    //应用的上下文
    public static Application mContext;
    //主线程
    public static Thread mMainThread;
    //主线程ID
    public static int mMainThreadID = -1;
    //主线程中的Looper
    public static Looper mMainThreadLooper;
    //主线程的Handler
    public static Handler mMainThreadHandler;

    // 路由表
    public HashMap<String, Class> mapping = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        this.mMainThread = Thread.currentThread();
        this.mMainThreadID = android.os.Process.myTid();
        this.mMainThreadLooper = getMainLooper();
        this.mMainThreadHandler = new Handler();

        getAllActivities();


        //初始化开源工具类
        Utils.init(this);
    }

    /**
     * 初始化 路由表
     */
    private void getAllActivities() {
        try {
            DexFile dexFile = new DexFile(this.getPackageResourcePath());
            Enumeration entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String entryName = (String) entries.nextElement();
                // 开始匹配Activity
                if (entryName.contains("Activity")) {
                    // 通过反射获得Activity类
                    Class entryClass = Class.forName(entryName);
                    if (entryClass.isAnnotationPresent(Action.class)) {
                        Action action = (Action) entryClass.getAnnotation(Action.class);
                        this.mapping.put(action.value(), entryClass);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Application getmContext() {
        return mContext;
    }

    public static Thread getmMainThread() {
        return mMainThread;
    }

    public static int getmMainThreadID() {
        return mMainThreadID;
    }

    public static Looper getmMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Handler getmMainThreadHandler() {
        return mMainThreadHandler;
    }
}
