package com.jia.libnet;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jia.libnet.bean.news.NewsBean;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoArticleBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.jia.libnet.bean.search.SearchResultBean;
import com.jia.libnet.bean.video.MultiNewsArticleBean;
import com.jia.libnet.bean.video.VideoCommentBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by jia on 2017/6/7.
 * 人之所以能，是相信能
 */
public class HttpMethod {

    public static final String TAG = "HttpMethod";

    // 请求超时
    private static final int TIME_OUT = 5;

    private Retrofit retrofit;

    private BaseService service;

    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Log.i("RetrofitLog", "retrofitBack = " + message);
        }
    });


    // 私有构造
    public HttpMethod() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 手动创建okhttpclient，并设置超时，添加和保存cookie
        OkHttpClient okHttpClient = new OkHttpClient();
        final OkHttpClient.Builder buidler = okHttpClient.newBuilder();

        // 设置各超时
        buidler.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        buidler.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        buidler.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        // 设置重定向 其实默认也是true
        buidler.followRedirects(true);

        // stetho调试
        buidler.addNetworkInterceptor(new StethoInterceptor());

        // 保存cookie
        buidler.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (originalResponse.request().url().toString().contains("mobileLogin")) {
                    //这里获取请求返回的cookie
                    if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                        for (int i = 0; i < originalResponse.headers("Set-Cookie").size(); i++) {

                            String key = originalResponse.headers("Set-Cookie").get(i).substring(0, originalResponse.headers("Set-Cookie").get(i).indexOf("="));
                            String value = originalResponse.headers("Set-Cookie").get(i).substring(originalResponse.headers("Set-Cookie").get(i).indexOf("=") + 1);

                            if (key.equals("UC00OOIIll11")) {
                                CookieUtils.UC00OOIIll11 = value;
                            }
                            if (key.equals("JSESSIONID")) {
                                CookieUtils.JSESSIONID = value;
                            }
                        }
                    }
                }
                return originalResponse;
            }
        });

        // 添加cookie
        buidler.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                //添加请求参数
                HttpUrl url = original.url().newBuilder()
                        .addQueryParameter("loginType", CookieUtils.LOGIN_TYPE)
                        .addQueryParameter("params.loginType", CookieUtils.LOGIN_TYPE)
                        .addQueryParameter("unTyxlLoginToken", CookieUtils.UNTYXL_LOGIN_TOKEN)
                        .addQueryParameter("page.searchItem.siteCode", CookieUtils.SITE_CODE)
                        .build();
                //添加cookie
                Request request = original.newBuilder()
                        .addHeader("Cookie", "JSESSIONID=" + CookieUtils.JSESSIONID)
                        .addHeader("Cookie", "UC00OOIIll11=" + CookieUtils.UC00OOIIll11)
                        .method(original.method(), original.body())
                        .url(url)
                        .build();
                return chain.proceed(request);

            }
        });

        buidler.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .client(buidler.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetConfig.BASE_URL)
                .build();

        service = retrofit.create(BaseService.class);
    }

    /**
     * 获取retrofit
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public BaseService getService() {
        return service;
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    // 获取单例
    public static HttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getErrorCode().equals("0")) {
                throw new PreDealException(tHttpResult.getErrorMsg());
            }
            return tHttpResult.getData();
        }
    }

    public void getNewsByTag(String category, String as, Subscriber<NewsBean> subscriber) {
//        service.getNewsByTag("2", category,as)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

    }

    /**
     * 获取新闻评价列表
     *
     * @param groupId    头条号
     * @param itemId     文章号
     * @param offset     偏移量
     * @param count      数量
     * @param subscriber
     */
    public void getNewsCommentList(String groupId, String itemId, String offset, String count, Subscriber<NewsCommentBean> subscriber) {
        service.getNewsComment(groupId, itemId, offset, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 获取 图片 文章 列表
     *
     * @param category
     * @param time
     * @param subscriber
     */
    public void getPhotoArticleList(String category, String time, Subscriber<PhotoArticleBean> subscriber) {
        service.getPhotoArticle(category, time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取 视频 文章 列表
     *
     * @param category
     * @param time
     * @param subscriber
     */
    public void getVideoArticleList(String category, String time, Subscriber<MultiNewsArticleBean> subscriber) {
        service.getVideoArticle(category, time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取 图片 评论数据
     *
     * @param groupId
     * @param offset
     * @param subscriber
     */
    public void getPhotoCommentList(String groupId, String offset, Subscriber<PhotoCommentBean> subscriber) {
        service.getPhotoComment(groupId, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获获取热门搜素列表
     */
    public void getSearchHotWords(Subscriber<SearchRecommentBean> subscriber) {
        service.getSearchRecomment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获获取热门搜素列表
     */
    public void getSearchResult(String keyword, String cur_tab, int offset, Subscriber<SearchResultBean> subscriber) {
        service.getSearchResult(keyword, cur_tab, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获获取热门搜素列表
     */
    public void getVideoComments(String videoId, int offset, Subscriber<List<VideoCommentBean.DataEntity.CommentEntity>> subscriber) {
        service.getVideoComments(videoId, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<VideoCommentBean, List<VideoCommentBean.DataEntity>>() {
                    @Override
                    public List<VideoCommentBean.DataEntity> call(VideoCommentBean bean) {
                        return bean.getData();
                    }
                })
                .map(new Func1<List<VideoCommentBean.DataEntity>, List<VideoCommentBean.DataEntity.CommentEntity>>() {
                    @Override
                    public List<VideoCommentBean.DataEntity.CommentEntity> call(List<VideoCommentBean.DataEntity> dataEntities) {
                        List<VideoCommentBean.DataEntity.CommentEntity> comments = new ArrayList<>();
                        for (VideoCommentBean.DataEntity data : dataEntities) {
                            comments.add(data.getComment());
                        }
                        return comments;
                    }
                })
                .subscribe(subscriber);
    }
}
