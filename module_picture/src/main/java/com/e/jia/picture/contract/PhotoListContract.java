package com.e.jia.picture.contract;

import com.jia.libnet.bean.photo.PhotoArticleBean;

import java.util.List;

/**
 * Description: 图片列表 契约类
 * Created by jia on 2018/4/11.
 * 人之所以能，是相信能。
 */

public class PhotoListContract {

    public interface PhotoListView {
        void onSuccess(List<PhotoArticleBean.DataBean> list);

        void onNoData();

        void onFail(String info);
    }

    public interface PhotoListModel {
        void onGetPhotoList(String category, String time, OnGetListCallback callback);
    }

    public interface OnGetListCallback {
        void onSuccess(PhotoArticleBean bean);

        void onFail(String info);
    }
}
