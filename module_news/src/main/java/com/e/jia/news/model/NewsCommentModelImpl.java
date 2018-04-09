package com.e.jia.news.model;

import com.e.jia.news.contract.NewsCommentContract;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.news.NewsCommentBean;

import rx.Subscriber;

/**
 * Description: 新闻评价列表 model实现类
 * Created by jia on 2018/4/9.
 * 人之所以能，是相信能。
 */

public class NewsCommentModelImpl implements NewsCommentContract.NewsCommentModel {
    @Override
    public void getNewsCommentList(String group_id, String item_id, String offset, String count, final NewsCommentContract.OnGetCommentsCallback callback) {
        HttpMethod.getInstance().getNewsCommentList(group_id, item_id, offset, count, new Subscriber<NewsCommentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFail("网络错误");
            }

            @Override
            public void onNext(NewsCommentBean newsCommentBean) {
                if (newsCommentBean != null) {
                    callback.onSuccess(newsCommentBean);
                } else {
                    callback.onFail("未请求到数据");
                }
            }
        });
    }
}
