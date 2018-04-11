package com.e.jia.picture.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.e.jia.picture.R;
import com.e.jia.picture.adapter.PhotoListAdapter;
import com.e.jia.picture.contract.PhotoListContract;
import com.e.jia.picture.presenter.PhotoListPresenter;
import com.jia.base.BaseFragment;
import com.jia.libnet.bean.photo.PhotoArticleBean;

import java.util.List;

/**
 * Description: 图片列表 界面
 * Created by jia on 2018/4/11.
 * 人之所以能，是相信能。
 */

public class PhotoListFragment extends BaseFragment<PhotoListContract.PhotoListView, PhotoListPresenter>
        implements PhotoListContract.PhotoListView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refresh_layout;
    private RecyclerView recycler_view;
    private TextView tv_no_data;

    private PhotoListAdapter adapter;

    private String category="组图";

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
        tv_no_data.setVisibility(View.GONE);

        refresh_layout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setRefreshing(true);

        adapter = new PhotoListAdapter(getContext());

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
    }

    @Override
    public void onSuccess(List<PhotoArticleBean.DataBean> list) {
        adapter.setData(list);
        tv_no_data.setVisibility(View.GONE);
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void onNoData() {
        tv_no_data.setVisibility(View.VISIBLE);
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void onFail(String info) {
        ToastUtils.showLong(info + "");
        refresh_layout.setRefreshing(false);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
