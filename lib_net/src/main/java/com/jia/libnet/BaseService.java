package com.jia.libnet;

import com.jia.libnet.bean.news.NewsBean;
import com.jia.libnet.bean.news.NewsCommentBean;


import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Describtion:
 * Created by jia on 2017/6/6.
 * 人之所以能，是相信能
 */
public interface BaseService {

    @POST("api/article/recent/")
    Observable<NewsBean> getNewsByTag(@Query("source") String source,@Query("count") String count, @Query("category") String category, @Query("as") String as);

    @POST("api/comment/list/")
    Observable<NewsCommentBean> getNewsComment(@Query("group_id") String group_id, @Query("item_id") String item_id, @Query("offset") String offset, @Query("count") String count);
}
