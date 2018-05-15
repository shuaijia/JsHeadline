package com.mangosteen.headline.contract;

import com.jia.libnet.bean.news.NewsBean;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.jia.libnet.bean.search.SearchResultBean;

import java.util.List;

/**
 * Description: 搜索 契约类
 * Created by jia on 2018/4/28.
 * 人之所以能，是相信能。
 */

public class SearchContract {

    public interface SearchView {
        void onGetHotWords(List<SearchRecommentBean.DataBean.SuggestWordListBean> words);

        void onNoHotWords();

        void onGetSearchResults(List<NewsBean.DataEntity> newList);

        void onNoResult();

        void onGetResultFail(String info);
    }

    public interface SearchModel {
        void getHotWords(OnGetHotWordCallback callback);

        void getSearchReuslt(String keyword, String cur_tab, int offset, OnGetSearchResultCallback callback);
    }

    public interface OnGetHotWordCallback {
        void onSuccess(SearchRecommentBean searchRecommentBean);

        void onFail();
    }

    public interface OnGetSearchResultCallback {
        void onSuccess(SearchResultBean bean);

        void onFail(String info);
    }
}
