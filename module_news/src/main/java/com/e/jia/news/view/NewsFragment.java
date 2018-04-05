package com.e.jia.news.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.e.jia.news.adapter.NewsFragmentPagerAdapter;
import com.e.jia.news.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.gson.Gson;
import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;
import com.jia.base.annotation.BindEventBus;
import com.jia.base.event.NewsChannelEvent;
import com.jia.libnet.bean.channel.NewsChannel;

import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

@BindEventBus
public class NewsFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_channel_add;

    private NewsFragmentPagerAdapter mNewsFragmentPagerAdapter;

    private NewsChannel channels;
    private List<NewsChannel.Channel> selectChannels;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_news, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        mTabLayout = view.findViewById(R.id.tl_news_titles);
        mViewPager = view.findViewById(R.id.vp_news_content);
        iv_channel_add = view.findViewById(R.id.iv_channel_add);

        iv_channel_add.setOnClickListener(this);

        channels = getChannels(getContext());
        selectChannels=channels.getSelectedList();

        // 创建新功能引导
        TapTargetView.showFor(getActivity(),
                TapTarget.forView(view.findViewById(R.id.iv_channel_add), "点击这里，添加频道", "")
                        .outerCircleColor(R.color.colorAccent)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.colorAccent)
                        .descriptionTextSize(10)
                        .textColor(R.color.white)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.white)
                        .drawShadow(false)
                        .cancelable(true)
                        .tintTarget(false)
                        .transparentTarget(false)
                        .targetRadius(50));
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

        mNewsFragmentPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager());
        mNewsFragmentPagerAdapter.setData(selectChannels);
        mViewPager.setAdapter(mNewsFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(50);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar_news, menu);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_channel_add) {

            startActivity(new Intent(getActivity(), ChannelActivity.class));

        }

    }

    @Subscribe()
    public void onEvent(NewsChannelEvent event){
        selectChannels=event.getList();
        initFragmentData(null);
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
