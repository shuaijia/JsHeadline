package com.mangosteen.headline.presenter;

import com.jia.base.BasePresenter;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.mangosteen.headline.contract.SearchContract;
import com.mangosteen.headline.model.SearchModelImpl;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Description:
 * Created by jia on 2018/4/28.
 * 人之所以能，是相信能。
 */

public class SearchPresenter extends BasePresenter<SearchContract.SearchView> {

    private SearchModelImpl model;

    public SearchPresenter(LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        model = new SearchModelImpl();
    }

    /**
     * 获取热门搜索
     */
    public void getSearchHotWords() {
        model.getHotWords(new SearchContract.OnGetHotWordCallback() {
            @Override
            public void onSuccess(SearchRecommentBean bean) {
                if (bean.getData() != null && bean.getData().getSuggest_word_list() != null
                        && bean.getData().getSuggest_word_list().size() > 0) {
                    getView().onGetHotWords(bean.getData().getSuggest_word_list());
                } else {
                    getView().onNoHotWords();
                }
            }

            @Override
            public void onFail() {
                getView().onNoHotWords();
            }
        });
    }
}
