package com.e.jia.news.contract;

import com.jia.libnet.bean.news.NewsCommentBean;

import java.util.List;

/**
 * Description: 新闻评论列表 契约类
 * Created by jia on 2018/4/9.
 * 人之所以能，是相信能。
 */

public class NewsCommentContract {

    public interface NewsCommentView{
        void onRefresh(List<NewsCommentBean.DataEntity.CommentsEntity> list);

        void onNoComment();

        void onFail(String info);
    }

    public interface NewsCommentModel{
        void getNewsCommentList(String group_id,String item_id,String offset,String count,OnGetCommentsCallback callback);
    }

    public interface OnGetCommentsCallback{
        void onSuccess(NewsCommentBean bean);

        void onFail(String info);
    }
}
