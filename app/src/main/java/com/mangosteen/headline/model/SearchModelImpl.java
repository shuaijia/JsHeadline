package com.mangosteen.headline.model;

import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.jia.libnet.bean.search.SearchResultBean;
import com.mangosteen.headline.contract.SearchContract;

import rx.Subscriber;

/**
 * Description: 搜索实现类
 * Created by jia on 2018/4/28.
 * 人之所以能，是相信能。
 */

public class SearchModelImpl implements SearchContract.SearchModel {
    @Override
    public void getHotWords(final SearchContract.OnGetHotWordCallback callback) {
        HttpMethod.getInstance().getSearchHotWords(new Subscriber<SearchRecommentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFail();
            }

            @Override
            public void onNext(SearchRecommentBean searchRecommentBean) {
                if (searchRecommentBean != null) {
                    callback.onSuccess(searchRecommentBean);
                } else {
                    callback.onFail();
                }
            }
        });
    }

    @Override
    public void getSearchReuslt(String keyword, String cur_tab, int offset, final SearchContract.OnGetSearchResultCallback callback) {
        HttpMethod.getInstance().getSearchResult(keyword, cur_tab, offset, new Subscriber<SearchResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFail("网络错误");
            }

            @Override
            public void onNext(SearchResultBean searchResultBean) {
                if (searchResultBean != null) {
                    callback.onSuccess(searchResultBean);
                } else {
                    callback.onFail("网络错误");
                }
            }
        });
    }
}
