package com.e.jia.news.contract;

import com.jia.libnet.bean.news.NewsBean;

/**
 * Description: 新闻列表 契约类
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */

public class NewsListContract {

    public interface NewsListView {

        void onRefreshSuccess(NewsBean bean);

        void onRefreshFail(String info);
    }


    public interface NewsListModel {

        void getNewsByTag(String tag, OnNewsListCallback callback);

    }

    public interface OnNewsListCallback {
        void onSuccess(NewsBean bean);

        void onFail(String info);
    }
}
