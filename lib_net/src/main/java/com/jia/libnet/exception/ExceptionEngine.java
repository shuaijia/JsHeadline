package com.jia.libnet.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Description: 请求返回异常 处理类
 * Created by jia on 2018/3/28.
 * 人之所以能，是相信能。
 */

public class ExceptionEngine {

    public static final int UN_KNOWN_ERROR = 1000;//未知错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;//解析(服务器)数据错误
    public static final int ANALYTIC_CLIENT_DATA_ERROR = 1002;//解析(客户端)数据错误
    public static final int CONNECT_ERROR = 1003;//网络连接错误
    public static final int TIME_OUT_ERROR = 1004;//网络连接超时

    public static String handleException(Throwable e) {
        if (e instanceof HttpException) {             //HTTP错误
            return "网络错误";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            return "解析错误";
        } else if (e instanceof ConnectException) {//连接网络错误
            return "连接失败";
        } else if (e instanceof SocketTimeoutException) {//网络超时

            return "网络超时";
        } else {  //未知错误
            return "未知错误";
        }
    }
}
