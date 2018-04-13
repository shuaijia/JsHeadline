package com.e.jia.video.model;

import android.util.Log;

import com.e.jia.video.contract.VideoListContract;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.video.MultiNewsArticleBean;
import com.jia.libutils.TimeUtil;

import rx.Subscriber;

/**
 * Description:
 * Created by jia on 2018/4/13.
 * 人之所以能，是相信能。
 */

public class VideoListModelImpl implements VideoListContract.VideoListModel {
    @Override
    public void onGetVideoList(String category, final VideoListContract.OnGetVideoListCallback callback) {
        HttpMethod.getInstance().getVideoArticleList(category, TimeUtil.getCurrentTimeStamp(), new Subscriber<MultiNewsArticleBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFail("网络错误");
            }

            @Override
            public void onNext(MultiNewsArticleBean multiNewsArticleBean) {
                if (multiNewsArticleBean != null) {
                    callback.onSuccess(multiNewsArticleBean);
                } else {
                    callback.onFail("为请求到数据");
                }
            }
        });
    }
}
