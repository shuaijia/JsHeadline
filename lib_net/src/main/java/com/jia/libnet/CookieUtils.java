package com.jia.libnet;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/**
 * Describtion:
 * Created by jia on 2017/6/7.
 * 人之所以能，是相信能
 */
public class CookieUtils {

    public static String COOKIE = "";

    public static String LOGIN_TYPE = "";

    public static String UNTYXL_LOGIN_TOKEN = "";

    public static String BASE_URL = "";

    public static String LOGINID = "";

    public static String SITE_CODE = "";

    public static String JSESSIONID = "";

    public static String UC00OOIIll11 = "";

    /**
     * 设置cookie
     *
     * @param context
     * @param url
     */
    public static void setCookie(Context context, String url) {
        //创建CookieSyncManager
        CookieSyncManager.createInstance(context);
        //得到CookieManager
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url, "JSESSIONID=" + JSESSIONID);
        cookieManager.setCookie(url, "UC00OOIIll11=" + UC00OOIIll11);
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }
}
