package com.e.jia.picture.model;

import android.util.Log;

import com.e.jia.picture.contract.PhotoListContract;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.photo.PhotoArticleBean;

import rx.Subscriber;

/**
 * Description: 图片文章列表model实现类
 * Created by jia on 2018/4/11.
 * 人之所以能，是相信能。
 */

public class PhotoListModelImpl implements PhotoListContract.PhotoListModel {
    @Override
    public void onGetPhotoList(String category, String time, final PhotoListContract.OnGetListCallback callback) {
        HttpMethod.getInstance().getPhotoArticleList(category, time, new Subscriber<PhotoArticleBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFail("网络错误");
            }

            @Override
            public void onNext(PhotoArticleBean photoArticleBean) {
                Log.e("jia", "onNext: "+photoArticleBean.toString() );
                if (photoArticleBean != null) {
                    callback.onSuccess(photoArticleBean);
                } else {
                    callback.onFail("未加载到数据");
                }
            }
        });
    }
}
