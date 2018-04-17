package com.jia.libnet;

import com.jia.libnet.bean.news.NewsBean;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoArticleBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;
import com.jia.libnet.bean.video.MultiNewsArticleBean;

import retrofit2.http.GET;
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

    /**
     * 获取图片标题等信息
     * http://www.toutiao.com/api/article/feed/?category=类型&as=A115C8457F69B85&cp=585F294B8845EE1&_=时间&count=30
     */
    @GET("http://www.toutiao.com/api/pc/feed/?as=A115C8457F69B85&cp=585F294B8845EE1&_signature=l")
    Observable<PhotoArticleBean> getPhotoArticle(
            @Query("category") String category,
            @Query("max_behot_time") String time);

    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getVideoArticle(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    /**
     * 获取新闻评论
     * 按热度排序
     * http://is.snssdk.com/article/v53/tab_comments/?group_id=6314103921648926977&offset=0&tab_index=0
     * 按时间排序
     * http://is.snssdk.com/article/v53/tab_comments/?group_id=6314103921648926977&offset=0&tab_index=1
     *
     * @param groupId 新闻ID
     * @param offset  偏移量
     */
    @GET("http://is.snssdk.com/article/v53/tab_comments/")
    Observable<PhotoCommentBean> getPhotoComment(
            @Query("group_id") String groupId,
            @Query("offset") String offset);
}
