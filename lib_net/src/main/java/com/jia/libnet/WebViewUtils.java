package com.jia.libnet;

import android.content.Context;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Description:
 * Created by jia on 2018/1/4.
 * 人之所以能，是相信能
 */
public class WebViewUtils {

    public static void configWebView(Context context,WebView webview){
        WebSettings wSet = webview.getSettings();
        //设置是否支持JS交互，不设置页面显示不出来
        wSet.setJavaScriptEnabled(true);
        //设置适应屏幕
        wSet.setUseWideViewPort(true);
        wSet.setLoadWithOverviewMode(true);
        //不支持缩放
        wSet.setSupportZoom(false);
        wSet.setBuiltInZoomControls(false);
        //设置数据格式，这样能在一定程度上节省资源
        wSet.setDefaultTextEncodingName("UTF-8");
        wSet.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //隐藏原生缩放控件
//        wSet.setDisplayZoomControls(false);
//        wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //设置 缓存模式
        wSet.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        wSet.setDomStorageEnabled(true);
        //开启 database storage API 功能
        wSet.setDatabaseEnabled(true);
        String cacheDirPath = context.getFilesDir().getAbsolutePath();
        Log.i("BaseWebViewActivity", "cacheDirPath=" + cacheDirPath);
        //设置数据库缓存路径
        wSet.setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        wSet.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        wSet.setAppCacheEnabled(true);

        wSet.setBuiltInZoomControls(false);

        wSet.setAllowContentAccess(true);

        wSet.setPluginState(WebSettings.PluginState.ON);
    }
}
