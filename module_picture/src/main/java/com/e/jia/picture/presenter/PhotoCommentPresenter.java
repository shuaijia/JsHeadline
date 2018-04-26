package com.e.jia.picture.presenter;

import com.e.jia.picture.contract.PhotoCommentContract;
import com.e.jia.picture.model.PhotoCommentModelImpl;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Description:
 * Created by jia on 2018/4/17.
 * 人之所以能，是相信能。
 */

public class PhotoCommentPresenter extends BasePresenter<PhotoCommentContract.PhotoCommentView> {

    private PhotoCommentModelImpl model;

    public PhotoCommentPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new PhotoCommentModelImpl();
    }

    /**
     * 获取 评论 列表
     * @param groupId
     * @param offset
     */
    public void getCommentList(String groupId, String offset) {
        model.getPhotoCommentList(groupId, offset, new PhotoCommentContract.OnPhotoCommentCallback() {
            @Override
            public void onSuccess(PhotoCommentBean bean) {
                if (bean.getData() == null || bean.getData().size() == 0) {
                    getView().noData();
                } else {
                    getView().onGetDataList(bean.getData());
                }
            }

            @Override
            public void onFail(String info) {
                getView().onFail(info + "");
            }
        });
    }
}
