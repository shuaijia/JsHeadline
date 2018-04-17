package com.e.jia.video.contract;

import com.jia.libnet.bean.video.MultiNewsArticleBean;
import com.jia.libnet.bean.video.VideoArticleBean;

import java.util.List;

/**
 * Description: 视频 契约类
 * Created by jia on 2018/4/13.
 * 人之所以能，是相信能。
 */

public class VideoListContract {

    public interface VideoListView {
        void onSuccess(List<VideoArticleBean> list);

        void onNoData();

        void onFail(String info);
    }

    public interface VideoListModel {
        void onGetVideoList(String category, OnGetVideoListCallback callback);
    }

    public interface OnGetVideoListCallback {
        void onSuccess(MultiNewsArticleBean data);

        void onFail(String info);
    }
}
