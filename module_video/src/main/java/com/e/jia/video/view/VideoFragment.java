package com.e.jia.video.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.e.jia.video.R;
import com.e.jia.video.adapter.VideoFragmentPagerAdapter;
import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;
import com.jia.libnet.HttpMethod;
import com.jia.libnet.bean.video.MultiNewsArticleBean;
import com.jia.libutils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by jia on 2018/3/31.
 */

public class VideoFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager vp_video_content;

    private VideoFragmentPagerAdapter adapter;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_video, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        mTabLayout = view.findViewById(R.id.tl_video_titles);
        vp_video_content = view.findViewById(R.id.vp_video_content);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        adapter = new VideoFragmentPagerAdapter(getChildFragmentManager());
        vp_video_content.setAdapter(adapter);
        vp_video_content.setOffscreenPageLimit(10);
        mTabLayout.setupWithViewPager(vp_video_content);



    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
