package com.e.jia.news.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.e.jia.news.R;
import com.e.jia.news.adapter.NewsCommentAdapter;
import com.e.jia.news.adapter.NewsListAdapter;
import com.e.jia.news.contract.NewsCommentContract;
import com.e.jia.news.presenter.NewsCommentPresenter;
import com.jia.base.BaseActivity;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libui.utils.SPUtils;

import java.util.List;

/**
 * Description: 新闻评价列表 界面
 * Created by jia on 2018/4/9.
 * 人之所以能，是相信能。
 */
public class NewsCommentActivity extends BaseActivity<NewsCommentContract.NewsCommentView, NewsCommentPresenter>
        implements NewsCommentContract.NewsCommentView, SwipeRefreshLayout.OnRefreshListener {

    private Toolbar toolbar;
    private RecyclerView recycler_view;
    private SwipeRefreshLayout refresh_layout;
    private TextView tv_no_data;

    private NewsCommentAdapter adapter;

    private String groupId;
    private String itemId;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_comment);

    }

    @Override
    protected void initView() {
        if (getIntent() != null) {
            groupId = getIntent().getStringExtra("groupId");
            itemId = getIntent().getStringExtra("itemId");
        } else {
            ToastUtils.showLong("出错了");
            return;
        }

        toolbar = findViewById(R.id.toolbar);
        recycler_view = findViewById(R.id.recycler_view);
        refresh_layout = findViewById(R.id.refresh_layout);
        String theme= SPUtils.getData(this,"theme","#3F51B5");
        refresh_layout.setColorSchemeColors(Color.parseColor(theme));
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setRefreshing(true);
        tv_no_data = findViewById(R.id.tv_no_data);
        tv_no_data.setVisibility(View.GONE);

        initToolBar();

        adapter = new NewsCommentAdapter(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);

        mPresenter.getNewsCommentList(groupId, itemId, "0", "100");
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
    protected NewsCommentPresenter createPresenter() {
        return new NewsCommentPresenter(null);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh(List<NewsCommentBean.DataEntity.CommentsEntity> list) {
        refresh_layout.setRefreshing(false);
        tv_no_data.setVisibility(View.GONE);

        adapter.setData(list);
    }

    @Override
    public void onNoComment() {
        refresh_layout.setRefreshing(false);
        tv_no_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFail(String info) {
        refresh_layout.setRefreshing(false);
        tv_no_data.setVisibility(View.VISIBLE);
        tv_no_data.setText(info);
    }

    @Override
    public void onRefresh() {
        mPresenter.getNewsCommentList(groupId, itemId, "0", "100");
    }
}
