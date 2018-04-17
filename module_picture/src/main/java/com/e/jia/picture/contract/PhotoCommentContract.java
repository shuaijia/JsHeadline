package com.e.jia.picture.contract;

import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;

import java.util.List;

/**
 * Description: 图片 评论 契约类
 * Created by jia on 2018/4/17.
 * 人之所以能，是相信能。
 */

public class PhotoCommentContract {

    public interface PhotoCommentView {
        void onGetDataList(List<PhotoCommentBean.DataBean> data);

        void noData();

        void onFail(String info);
    }

    public interface PhotoCommentModel {
        void getPhotoCommentList(String groupId, String offset, OnPhotoCommentCallback callback);
    }

    public interface OnPhotoCommentCallback {
        void onSuccess(PhotoCommentBean bean);

        void onFail(String info);
    }
}
