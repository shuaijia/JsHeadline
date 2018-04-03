package com.e.jia.news.model;

import android.util.Log;

import com.e.jia.news.contract.NewsListContract;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.ToutiaoUtil;
import com.jia.libnet.bean.news.NewsBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description: 新闻列表model实现类
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */

public class NewsListModelImpl implements NewsListContract.NewsListModel {
    /**
     * 根据tag获取列表
     *
     * @param tag
     * @param callback
     */
    @Override
    public void getNewsByTag(String tag, final NewsListContract.OnNewsListCallback callback) {
        HttpMethod.getInstance().getService()
                .getNewsByTag("2", tag, ToutiaoUtil.getAsCp().get("as"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail("网络错误");
                    }

                    @Override
                    public void onNext(NewsBean news) {
                        if (news != null) {
                            callback.onSuccess(news);
                        } else {
                            callback.onFail("未获取到数据");
                        }
                    }
                });
    }
}
