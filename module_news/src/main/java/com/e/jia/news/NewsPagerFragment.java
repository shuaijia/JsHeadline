package com.e.jia.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;

/**
 *  列表界面
 * Created by jia on 2018/3/31.
 */

public class NewsPagerFragment extends BaseFragment {

    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recycler_view;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_news_pager, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        refresh_layout=view.findViewById(R.id.refresh_layout);
//        recycler_view=view.findViewById(R.id.recycler_view);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
