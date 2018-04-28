package com.mangosteen.headline.presenter;

import com.jia.base.BasePresenter;
import com.jia.libnet.bean.news.NewsBean;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.jia.libnet.bean.search.SearchResultBean;
import com.mangosteen.headline.contract.SearchContract;
import com.mangosteen.headline.model.SearchModelImpl;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获取搜索结果
     *
     * @param keyword
     */
    public void getSearchResult(String keyword) {
        model.getSearchReuslt(keyword, "1", 1, new SearchContract.OnGetSearchResultCallback() {
            @Override
            public void onSuccess(SearchResultBean bean) {
                if (bean.getData() != null && bean.getData().size() > 0) {
                    List<SearchResultBean.DataBeanX> oldList = bean.getData();
                    List<NewsBean.DataEntity> newList = new ArrayList<>();
                    for (int i = 0; i < oldList.size(); i++) {
                        NewsBean.DataEntity data = new NewsBean.DataEntity();
                        data.setMedia_name(oldList.get(i).getMedia_name());
                        data.setAbstractX(oldList.get(i).getAbstractX());
                        data.setMedia_avatar_url(oldList.get(i).getMedia_url());
                        data.setMedia_url(oldList.get(i).getMedia_url());
                        data.setTag(oldList.get(i).getTag());
                        data.setTitle(oldList.get(i).getTitle());
                        data.setShare_url(oldList.get(i).getShare_url());
                        data.setId(oldList.get(i).getId());
                        data.setSource(oldList.get(i).getSource());
                        data.setComment_count(oldList.get(i).getComment_count());
                        data.setArticle_url(oldList.get(i).getArticle_url());
                        data.setPublish_time(oldList.get(i).getPublish_time());
                        data.setItem_id(oldList.get(i).getItem_id());
                        data.setUrl(oldList.get(i).getUrl());
                        data.setComments_count(oldList.get(i).getComment_count());
                        data.setGroup_id(oldList.get(i).getGroup_id());
                        data.setImage_list(oldList.get(i).getImage_list());
                        data.setImage_url(oldList.get(i).getImage_url());
                        data.setHas_video(oldList.get(i).isHas_video());
                        newList.add(data);
                    }

                    getView().onGetSearchResults(newList);
                } else {
                    getView().onNoResult();
                }
            }

            @Override
            public void onFail(String info) {
                getView().onGetResultFail(info);
            }
        });
    }
}
