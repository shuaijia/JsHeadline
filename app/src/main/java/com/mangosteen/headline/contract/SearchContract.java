package com.mangosteen.headline.contract;

import com.jia.libnet.bean.search.SearchRecommentBean;

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
    }

    public interface SearchModel{
        void getHotWords(OnGetHotWordCallback callback);
    }

    public interface OnGetHotWordCallback{
        void onSuccess(SearchRecommentBean searchRecommentBean);

        void onFail();
    }
}
