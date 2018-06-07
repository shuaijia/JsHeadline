package com.e.jia.video.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.e.jia.video.R;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.jsplayer.video.JsPlayer;
import com.jia.libui.utils.SPUtils;

/**
 * Description: 视频详情界面
 * Created by jia on 2018/6/7.
 * 人之所以能，是相信能。
 */

public class VideoDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private JsPlayer player;
    private SwipeRefreshLayout swipe;
    private RecyclerView mRvInfo;

    private ImageView mHolderIv;
    private ImageView mPlayIv;

    private int videoId;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_detail);
        if (getIntent() != null) {
            videoId = getIntent().getIntExtra("videoId", 0);
        } else {
            ToastUtils.showLong("数据错误");
        }
    }

    @Override
    protected void initView() {
        player = findViewById(R.id.player);
        swipe = findViewById(R.id.swipe_video_detail);
        mRvInfo = findViewById(R.id.rv_video_info);
        mHolderIv = findViewById(R.id.iv_video_holder);
        mPlayIv = findViewById(R.id.iv_play);

        swipe.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String theme = SPUtils.getData(mContext, "theme", "#3F51B5");
        swipe.setColorSchemeColors(Color.parseColor(theme));
        swipe.setRefreshing(true);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh() {

    }
}
