package com.e.jia.picture.model;

import com.e.jia.picture.contract.PhotoCommentContract;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;

import rx.Subscriber;

/**
 * Description:
 * Created by jia on 2018/4/17.
 * 人之所以能，是相信能。
 */

public class PhotoCommentModelImpl implements PhotoCommentContract.PhotoCommentModel {
    @Override
    public void getPhotoCommentList(String groupId, String offset, final PhotoCommentContract.OnPhotoCommentCallback callback) {
        HttpMethod.getInstance().getPhotoCommentList(groupId, offset, new Subscriber<PhotoCommentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFail("网络错误");
            }

            @Override
            public void onNext(PhotoCommentBean newsCommentBean) {
                if (newsCommentBean == null) {
                    callback.onFail("未获取到数据");
                } else {
                    callback.onSuccess(newsCommentBean);
                }
            }
        });
    }
}
