package com.e.jia.video.presenter;

import android.media.midi.MidiOutputPort;

import com.e.jia.video.contract.VideoDetailContract;
import com.e.jia.video.model.VideoDetailModelImpl;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.video.VideoCommentBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

/**
 * Description: 视频详情 主持类
 * Created by jia on 2018/6/8.
 * 人之所以能，是相信能。
 */

public class VideoDetailPresenter extends BasePresenter<VideoDetailContract.VideoDetailView> {

    private VideoDetailModelImpl model;

    public VideoDetailPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new VideoDetailModelImpl();
    }

    public void getComments(String videoId) {
        model.getVideoComments(videoId, new VideoDetailContract.OnVideoDetailCallback() {
            @Override
            public void onSuccess(List<VideoCommentBean.DataEntity.CommentEntity> bean) {
                if (bean.size() > 0) {
                    getView().onRefreshSuccess(bean);
                } else {
                    getView().onRefreshFail("数据为空");
                }
            }

            @Override
            public void onFail(String errorInfo) {
                getView().onRefreshFail(errorInfo);
            }
        });
    }
}
