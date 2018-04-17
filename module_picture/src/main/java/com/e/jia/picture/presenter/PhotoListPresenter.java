package com.e.jia.picture.presenter;

import com.e.jia.picture.contract.PhotoListContract;
import com.e.jia.picture.model.PhotoListModelImpl;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.photo.PhotoArticleBean;
import com.jia.libutils.TimeUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Description:
 * Created by jia on 2018/4/11.
 * 人之所以能，是相信能。
 */

public class PhotoListPresenter extends BasePresenter<PhotoListContract.PhotoListView> {

    private PhotoListModelImpl model;

    public PhotoListPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new PhotoListModelImpl();
    }

    public void getPhotoList(String category) {
        model.onGetPhotoList(category, TimeUtil.getCurrentTimeStamp(), new PhotoListContract.OnGetListCallback() {
            @Override
            public void onSuccess(PhotoArticleBean bean) {
                if (bean.getData() != null && bean.getData().size() > 0) {
                    getView().onSuccess(bean.getData());
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
