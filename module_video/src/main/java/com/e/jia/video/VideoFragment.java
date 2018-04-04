package com.e.jia.video;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class VideoFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager vp_video_content;

    private List<String> mTitles = new ArrayList<>();

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_video, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        mTabLayout=view.findViewById(R.id.tl_video_titles);
        vp_video_content=view.findViewById(R.id.vp_video_content);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        mTitles.add("影视");
        mTitles.add("娱乐");
        mTitles.add("呆萌");
        mTitles.add("游戏");
        mTitles.add("原创");
        mTitles.add("开眼");
        mTitles.add("小品");
        mTitles.add("生活");
        for (String title : mTitles) {
            TabLayout.Tab tab = mTabLayout.newTab(); //创建tab
            tab.setText(title); //设置文字
            mTabLayout.addTab(tab); //添加到tabLayout中
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
