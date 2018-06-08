package com.e.jia.video.model;

import com.e.jia.video.contract.VideoDetailContract;
import com.jia.libnet.BaseSubscriber;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.video.VideoCommentBean;

import java.util.List;

/**
 * Description: 视频详情 model实现类
 * Created by jia on 2018/6/8.
 * 人之所以能，是相信能。
 */

public class VideoDetailModelImpl implements VideoDetailContract.VideoDetailModel {


    @Override
    public void getVideoComments(String videoId, final VideoDetailContract.OnVideoDetailCallback callback) {

        HttpMethod.getInstance().getVideoComments(videoId, 0, new BaseSubscriber<List<VideoCommentBean.DataEntity.CommentEntity>>() {
            @Override
            public void onSuccess(List<VideoCommentBean.DataEntity.CommentEntity> commentEntities) {
                if (commentEntities != null) {
                    callback.onSuccess(commentEntities);
                } else {
                    callback.onFail("未获取到数据");
                }
            }

            @Override
            public void onFail(String info) {
                callback.onFail(info);
            }
        });
    }
}
