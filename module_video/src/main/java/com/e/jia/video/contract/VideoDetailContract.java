package com.e.jia.video.contract;

import com.jia.libnet.bean.video.VideoCommentBean;

import java.util.List;

/**
 * Description: 视频详情 契约类
 * Created by jia on 2018/6/8.
 * 人之所以能，是相信能。
 */

public class VideoDetailContract {

    public interface VideoDetailView {
        void onRefreshSuccess(List<VideoCommentBean.DataEntity.CommentEntity> comments);

        void onRefreshFail(String errorInfo);
    }

    public interface VideoDetailModel {
        /**
         * 获取视频评论列表
         * @param videoId
         */
        void getVideoComments(String videoId,OnVideoDetailCallback callback);
    }

    public interface OnVideoDetailCallback {
        void onSuccess(List<VideoCommentBean.DataEntity.CommentEntity> bean);

        void onFail(String errorInfo);
    }
}
