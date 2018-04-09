package com.e.jia.news.presenter;

import com.e.jia.news.contract.NewsCommentContract;
import com.e.jia.news.model.NewsCommentModelImpl;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Description: 新闻评价列表 主持类
 * Created by jia on 2018/4/9.
 * 人之所以能，是相信能。
 */

public class NewsCommentPresenter extends BasePresenter<NewsCommentContract.NewsCommentView> {

    private NewsCommentModelImpl model;

    public NewsCommentPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new NewsCommentModelImpl();
    }

    /**
     * 获取列表
     *
     * @param group_id
     * @param item_id
     * @param offset
     * @param count
     */
    public void getNewsCommentList(String group_id, String item_id, String offset, String count) {
        model.getNewsCommentList(group_id, item_id, offset, count, new NewsCommentContract.OnGetCommentsCallback() {
            @Override
            public void onSuccess(NewsCommentBean bean) {
                if (bean.getData().getComments() != null && bean.getData().getComments().size() > 0) {
                    getView().onRefresh(bean.getData().getComments());
                } else {
                    getView().onNoComment();
                }
            }

            @Override
            public void onFail(String info) {
                getView().onFail(info);
            }
        });
    }
}
