package com.e.jia.video.presenter;

import com.e.jia.video.contract.VideoListContract;
import com.e.jia.video.model.VideoListModelImpl;
import com.google.gson.Gson;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.video.MultiNewsArticleBean;
import com.jia.libnet.bean.video.VideoArticleBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by jia on 2018/4/13.
 * 人之所以能，是相信能。
 */

public class VideoListPresenter extends BasePresenter<VideoListContract.VideoListView> {

    private VideoListModelImpl model;

    public VideoListPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new VideoListModelImpl();
    }

    public void getVideoList(String category) {
        model.onGetVideoList(category, new VideoListContract.OnGetVideoListCallback() {
            @Override
            public void onSuccess(MultiNewsArticleBean data) {
                List<VideoArticleBean> list = new ArrayList<>();
                if (data.getData() != null && data.getData().size() > 0) {

                    for (int i = 0; i < data.getData().size(); i++) {
                        VideoArticleBean bean = new Gson().fromJson(data.getData().get(i).getContent(), VideoArticleBean.class);
                        list.add(bean);
                    }
                    getView().onSuccess(list);
                } else {
                    getView().onNoData();
                }
            }

            @Override
            public void onFail(String info) {
                getView().onFail(info);
            }
        });
    }
}
