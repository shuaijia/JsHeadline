package com.e.jia.news.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.e.jia.news.R;
import com.e.jia.news.contract.NewsListContract;
import com.e.jia.news.presenter.NewsListPresenter;
import com.jia.base.BaseFragment;
import com.jia.libnet.bean.news.NewsBean;

/**
 * 列表界面
 * Created by jia on 2018/3/31.
 */

public class NewsListFragment extends BaseFragment<NewsListContract.NewsListView, NewsListPresenter>
        implements NewsListContract.NewsListView {

    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recycler_view;

    private String tag = "";

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_news_pager, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        refresh_layout = view.findViewById(R.id.refresh_layout);
        recycler_view = view.findViewById(R.id.recycler_view);
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNewsListByTag(tag);
            }
        });
        refresh_layout.setRefreshing(true);
        mPresenter.getNewsListByTag(tag);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
//        refresh_layout.setRefreshing(true);
    }

    @Override
    protected NewsListPresenter createPresenter() {
//        return new NewsListPresenter(this.<Long>bindUntilEvent(FragmentEvent.DESTROY));
        return new NewsListPresenter(null);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void onRefreshSuccess(NewsBean bean) {
        refresh_layout.setRefreshing(false);
        Log.e(TAG, "onRefreshSuccess: " + bean.toString());
    }

    @Override
    public void onRefreshFail(String info) {
        refresh_layout.setRefreshing(false);
        Log.e(TAG, "onRefreshFail: " + info);
    }
}
