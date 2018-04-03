package com.e.jia.news.presenter;

import com.e.jia.news.contract.NewsListContract;
import com.e.jia.news.model.NewsListModelImpl;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.news.NewsBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Description: 新闻列表主持类
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */

public class NewsListPresenter extends BasePresenter<NewsListContract.NewsListView> {

    private NewsListModelImpl model;

    public NewsListPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new NewsListModelImpl();
    }

    /**
     * 根据标签获取新闻列表
     *
     * @param tag
     */
    public void getNewsListByTag(String tag) {
        model.getNewsByTag(tag, new NewsListContract.OnNewsListCallback() {
            @Override
            public void onSuccess(NewsBean bean) {
                getView().onRefreshSuccess(bean);
            }

            @Override
            public void onFail(String info) {
                getView().onRefreshFail(info);
            }
        });
    }
}
