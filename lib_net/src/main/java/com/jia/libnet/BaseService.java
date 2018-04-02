package com.jia.libnet;

import com.jia.libnet.bean.news.NewsBean;


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
    Observable<NewsBean> getNewsByTag(@Query("source") String source, @Query("category") String category, @Query("as") String as);
}
