package com.e.jia.picture.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.e.jia.picture.R;
import com.e.jia.picture.adapter.PhotoListAdapter;
import com.e.jia.picture.contract.PhotoListContract;
import com.e.jia.picture.diffutil.PhotoDiffCallback;
import com.e.jia.picture.presenter.PhotoListPresenter;
import com.jia.base.BaseFragment;
import com.jia.libnet.bean.photo.PhotoArticleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 图片列表 界面
 * Created by jia on 2018/4/11.
 * 人之所以能，是相信能。
 */

public class PhotoListFragment extends BaseFragment<PhotoListContract.PhotoListView, PhotoListPresenter>
        implements PhotoListContract.PhotoListView, SwipeRefreshLayout.OnRefreshListener, PhotoListAdapter.OnItemClickListener {

    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recycler_view;
    private TextView tv_no_data;

    private PhotoListAdapter adapter;

    private List<PhotoArticleBean.DataBean> list = new ArrayList<>();

    private String category = "组图";

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.fragment_photo_list, null);
        return v;
    }

    @Override
    protected void initFragmentChildView(View view) {
        refresh_layout = view.findViewById(R.id.refresh_layout);
        recycler_view = view.findViewById(R.id.recycler_view);
        tv_no_data = view.findViewById(R.id.tv_no_data);
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("加载中...");

        refresh_layout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setRefreshing(true);

        adapter = new PhotoListAdapter(getContext());
        adapter.setData(list);
        adapter.setOnItemClickListener(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setAdapter(adapter);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        mPresenter.getPhotoList(category);
    }

    @Override
    protected PhotoListPresenter createPresenter() {
        return new PhotoListPresenter(null);
    }

    @Override
    public void onRefresh() {
        mPresenter.getPhotoList(category);
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("加载中...");
    }

    @Override
    public void onSuccess(List<PhotoArticleBean.DataBean> list) {
        // 使用DiffUtil进行刷新
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PhotoDiffCallback(this.list, list));
        diffResult.dispatchUpdatesTo(adapter);
        this.list.clear();
        this.list.addAll(list);
        adapter.setData(this.list);

        tv_no_data.setVisibility(View.GONE);
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void onNoData() {
        refresh_layout.setRefreshing(false);
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("暂无数据");
    }

    @Override
    public void onFail(String info) {
        ToastUtils.showLong(info + "");
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText(info + "");
        refresh_layout.setRefreshing(false);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void onClick(PhotoArticleBean.DataBean data) {

        Intent intent = new Intent(getActivity(), PhotoDetailActivity.class);
        intent.putExtra("url", data.getSource_url());
        intent.putExtra("title", data.getSource());
        intent.putExtra("shareUrl", data.getSource_url());
        intent.putExtra("desc", data.getTitle());
        intent.putExtra("groupId", data.getGroup_id());
        intent.putExtra("itemId", data.getGroup_id());
        getActivity().startActivity(intent);

    }
}
