package com.e.jia.video.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.e.jia.video.R;
import com.e.jia.video.adapter.VideoListAdapter;
import com.e.jia.video.contract.VideoListContract;
import com.e.jia.video.diffutil.VideoDiffCallback;
import com.e.jia.video.presenter.VideoListPresenter;
import com.jia.base.BaseFragment;
import com.jia.libnet.bean.video.VideoArticleBean;
import com.jia.libui.utils.SPUtils;

import java.util.ArrayList;
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

    private List<VideoArticleBean> list = new ArrayList<>();

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
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("加载中...");

        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getVideoList(category);
                tv_no_data.setVisibility(View.VISIBLE);
                tv_no_data.setText("加载中...");
            }
        });

        adapter = new VideoListAdapter(getContext());
        adapter.setData(list);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setAdapter(adapter);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        mPresenter.getVideoList(category);
    }

    @Override
    public void onResume() {
        super.onResume();
        String theme = SPUtils.getData(getActivity(), "theme", "#3F51B5");
        refresh_layout.setColorSchemeColors(Color.parseColor(theme));
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
        tv_no_data.setVisibility(View.GONE);
        refresh_layout.setRefreshing(false);
        adapter.setData(list);

        // 使用DiffUtil进行刷新
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new VideoDiffCallback(this.list, list));
        diffResult.dispatchUpdatesTo(adapter);
        this.list.clear();
        this.list.addAll(list);
        adapter.setData(this.list);
    }

    @Override
    public void onNoData() {
        refresh_layout.setRefreshing(false);
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("暂无数据");
    }

    @Override
    public void onFail(String info) {
        refresh_layout.setRefreshing(false);
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("" + info);
    }
}
