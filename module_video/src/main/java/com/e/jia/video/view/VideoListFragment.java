package com.e.jia.video.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.e.jia.video.R;
import com.e.jia.video.adapter.VideoListAdapter;
import com.e.jia.video.contract.VideoListContract;
import com.e.jia.video.presenter.VideoListPresenter;
import com.jia.base.BaseFragment;
import com.jia.libnet.bean.video.VideoArticleBean;

import java.util.List;

/**
 * Description: 视频列表 界面
 * Created by jia on 2018/4/13.
 * 人之所以能，是相信能。
 */

public class VideoListFragment extends BaseFragment<VideoListContract.VideoListView, VideoListPresenter>
        implements VideoListContract.VideoListView {

    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recycler_view;
    private TextView tv_no_data;

    private VideoListAdapter adapter;

    private String category;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_video_list, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        refresh_layout = view.findViewById(R.id.refresh_layout);
        refresh_layout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        recycler_view = view.findViewById(R.id.recycler_view);
        tv_no_data = view.findViewById(R.id.tv_no_data);

        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getVideoList(category);
            }
        });

        adapter=new VideoListAdapter(getContext());
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setAdapter(adapter);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        mPresenter.getVideoList(category);
    }

    @Override
    protected VideoListPresenter createPresenter() {
        return new VideoListPresenter(null);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void onSuccess(List<VideoArticleBean> list) {
        Log.e(TAG, "onSuccess: " + list.toString());
        refresh_layout.setRefreshing(false);
        adapter.setData(list);
    }

    @Override
    public void onNoData() {
        Log.e(TAG, "onNoData: ");
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void onFail(String info) {
        Log.e(TAG, "onFail: " + info);
        refresh_layout.setRefreshing(false);
    }
}
