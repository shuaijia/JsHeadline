package com.e.jia.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.news.NewsBean;

import rx.Subscriber;

/**
 *  列表界面
 * Created by jia on 2018/3/31.
 */

public class NewsPagerFragment extends BaseFragment {

    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recycler_view;

    private String tag="";

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_news_pager, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        refresh_layout = view.findViewById(R.id.refresh_layout);
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh_layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh_layout.setRefreshing(false);
                    }
                }, 500);
            }
        });
//        recycler_view=view.findViewById(R.id.recycler_view);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        HttpMethod.getInstance().getNewsByTag(tag, "A1D5D87595C3288", new Subscriber<NewsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e.toString() );
            }

            @Override
            public void onNext(NewsBean news) {
                Log.e(TAG, "onNext: " +news.toString());
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
