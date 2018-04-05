package com.e.jia.news.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;

import com.e.jia.news.R;
import com.e.jia.news.adapter.ChannelAdapter;
import com.e.jia.news.recycler.ChannelTouchCallback;
import com.google.gson.Gson;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.channel.NewsChannel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by jia on 2018/4/5.
 */

public class ChannelActivity extends BaseActivity {

    private RecyclerView rv_channel_mine;
    private RecyclerView rv_channel_other;
    private Toolbar toolbar;

    private ChannelAdapter mineAdapter;
    private ChannelAdapter otherAdapter;

    private NewsChannel newsChannel;
    private List<NewsChannel.Channel> selectedList;
    private List<NewsChannel.Channel> noSelectedList;

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_channels);
    }

    @Override
    protected void initView() {
        rv_channel_mine = findViewById(R.id.rv_channel_mine);
        rv_channel_other = findViewById(R.id.rv_channel_other);
        toolbar = findViewById(R.id.toolbar);

        rv_channel_mine.setLayoutManager(new GridLayoutManager(this, 4));
        rv_channel_other.setLayoutManager(new GridLayoutManager(this, 4));

        initToolBar();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
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
        newsChannel = getChannels(this);
        selectedList = newsChannel.getSelectedList();
        noSelectedList = newsChannel.getNoSelectedList();

        mineAdapter = new ChannelAdapter(this, selectedList, true);
        otherAdapter = new ChannelAdapter(this, noSelectedList, false);

        itemTouchHelper = new ItemTouchHelper(new ChannelTouchCallback(mineAdapter));
        itemTouchHelper.attachToRecyclerView(rv_channel_mine);

        otherAdapter.setListener(new ChannelAdapter.DataChangedListener() {
            @Override
            public void onAdd(NewsChannel.Channel channel) {

            }

            @Override
            public void onDelete(NewsChannel.Channel channel) {
                mineAdapter.addData(channel);
            }
        });

        mineAdapter.setListener(new ChannelAdapter.DataChangedListener() {
            @Override
            public void onAdd(NewsChannel.Channel channel) {

            }

            @Override
            public void onDelete(NewsChannel.Channel channel) {
                otherAdapter.addData(channel);
            }
        });

        rv_channel_mine.setAdapter(mineAdapter);
        rv_channel_other.setAdapter(otherAdapter);
    }

    public NewsChannel getChannels(Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("Channels.json")));
            String line;

            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(stringBuilder.toString(), NewsChannel.class);
    }
}
