package com.e.jia.video.view;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.e.jia.video.R;
import com.e.jia.video.adapter.VideoDetailAdapter;
import com.e.jia.video.contract.VideoDetailContract;
import com.e.jia.video.presenter.VideoDetailPresenter;
import com.jia.base.BaseActivity;
import com.jia.jsplayer.utils.DisplayUtils;
import com.jia.jsplayer.video.JsPlayer;
import com.jia.libnet.bean.video.VideoCommentBean;
import com.jia.libnet.bean.video.VideoDetailBean;
import com.jia.libui.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 视频详情界面
 * Created by jia on 2018/6/7.
 * 人之所以能，是相信能。
 */

public class VideoDetailActivity extends BaseActivity<VideoDetailContract.VideoDetailView, VideoDetailPresenter>
        implements VideoDetailContract.VideoDetailView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private JsPlayer player;
    private SwipeRefreshLayout swipe;
    private RecyclerView mRvInfo;

    private ImageView mHolderIv;
    private ImageView mPlayIv;

    private String videoId;
    private String holderUrl;
    private String mediaName;
    private String mediaAvatar;
    private String title;

    private VideoDetailAdapter adapter;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_detail);
        if (getIntent() != null) {
            videoId = getIntent().getStringExtra("videoId");
            holderUrl = getIntent().getStringExtra("holder");
            mediaName = getIntent().getStringExtra("mediaName");
            mediaAvatar = getIntent().getStringExtra("mediaAvatar");
            title=getIntent().getStringExtra("title");
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
        mPlayIv.setOnClickListener(this);

        Glide.with(mContext)
                .load(holderUrl)
                .into(mHolderIv);

        adapter=new VideoDetailAdapter(mContext);
        mRvInfo.setLayoutManager(new LinearLayoutManager(mContext));
        mRvInfo.setAdapter(adapter);

        swipe.setRefreshing(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        String theme = SPUtils.getData(mContext, "theme", "#3F51B5");
        swipe.setColorSchemeColors(Color.parseColor(theme));
    }

    @Override
    protected VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter(null);
    }

    @Override
    protected void initData() {
        mPresenter.getComments(videoId);
    }

    @Override
    public void onRefresh() {
        mPresenter.getComments(videoId);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_play) {


        }
    }

    @Override
    public void onRefreshSuccess(List<VideoCommentBean.DataEntity.CommentEntity> comments) {
        Log.e(TAG, "onRefreshSuccess: " + comments.toString());
        swipe.setRefreshing(false);
        VideoDetailBean detail=new VideoDetailBean(mediaAvatar,mediaName,title);
        List list=new ArrayList();
        list.add(detail);
        list.addAll(comments);
        adapter.setList(list);
    }

    @Override
    public void onRefreshFail(String errorInfo) {
        Log.e(TAG, "onRefreshFail: " + errorInfo);
        swipe.setRefreshing(false);
        VideoDetailBean detail=new VideoDetailBean(mediaAvatar,mediaName,title);
        List list=new ArrayList();
        list.add(detail);
        adapter.setList(list);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!DisplayUtils.isPortrait(this)) {
            if (!player.isLock()) {
                DisplayUtils.toggleScreenOrientation(this);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
