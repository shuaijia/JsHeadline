package com.e.jia.news.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.e.jia.news.R;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;

/**
 * Description: 新闻评价列表 界面
 * Created by jia on 2018/4/9.
 * 人之所以能，是相信能。
 */
public class NewsCommentActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recycler_view;
    private SwipeRefreshLayout refresh_layout;

    private String groupId;
    private String itemId;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_comment);

    }

    @Override
    protected void initView() {
        toolbar=findViewById(R.id.toolbar);
        recycler_view=findViewById(R.id.recycler_view);
        refresh_layout=findViewById(R.id.refresh_layout);

        initToolBar();
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
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        if(getIntent()!=null){
            groupId=getIntent().getStringExtra("groupId");
            itemId=getIntent().getStringExtra("itemId");
        }else{
            ToastUtils.showLong("出错了");
        }
    }
}
