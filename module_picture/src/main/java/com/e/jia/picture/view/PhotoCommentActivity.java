package com.e.jia.picture.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.e.jia.picture.R;
import com.e.jia.picture.adapter.PhotoCommentAdapter;
import com.e.jia.picture.contract.PhotoCommentContract;
import com.e.jia.picture.presenter.PhotoCommentPresenter;
import com.jia.base.BaseActivity;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;

import java.util.List;

/**
 * Description: 图片文章 评论 列表
 * Created by jia on 2018/4/17.
 * 人之所以能，是相信能。
 */
public class PhotoCommentActivity extends BaseActivity<PhotoCommentContract.PhotoCommentView, PhotoCommentPresenter>
        implements PhotoCommentContract.PhotoCommentView, SwipeRefreshLayout.OnRefreshListener {

    private Toolbar toolbar;
    private RecyclerView recycler_view;
    private SwipeRefreshLayout refresh_layout;
    private TextView tv_no_data;

    private PhotoCommentAdapter adapter;

    private String groupId;
    private String itemId;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo_comment);
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        recycler_view = findViewById(R.id.recycler_view);
        refresh_layout = findViewById(R.id.refresh_layout);
        tv_no_data = findViewById(R.id.tv_no_data);
        refresh_layout.setOnRefreshListener(this);

        initToolBar();

        groupId = getIntent().getStringExtra("groupId");
        itemId = getIntent().getStringExtra("itemId");

        if (TextUtils.isEmpty(groupId) || TextUtils.isEmpty(itemId)) {
            tv_no_data.setVisibility(View.VISIBLE);
            tv_no_data.setText("出错了");
            return;
        }

        adapter = new PhotoCommentAdapter(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
    }


    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        toolbar.setTitle("评论");
        setSupportActionBar(toolbar);
        //设置导航的图标
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        // 左侧图标点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected PhotoCommentPresenter createPresenter() {
        return new PhotoCommentPresenter(null);
    }

    @Override
    protected void initData() {
        mPresenter.getCommentList(groupId, "1");
    }

    @Override
    public void onGetDataList(List<PhotoCommentBean.DataBean> data) {
        adapter.setData(data);
    }

    @Override
    public void noData() {
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("未获取到数据");
    }

    @Override
    public void onFail(String info) {
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText("未获取到数据");
    }

    @Override
    public void onRefresh() {
        mPresenter.getCommentList(groupId, "100");
    }
}
