package com.e.jia.news;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class NewsFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private NewsFragmentPagerAdapter mNewsFragmentPagerAdapter;
    private List<String> mTitles = new ArrayList<>();

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_news, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        mTabLayout = view.findViewById(R.id.tl_news_titles);
        mViewPager = view.findViewById(R.id.vp_news_content);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        mTitles.add("推荐");
        mTitles.add("热点");
        mTitles.add("体育");
        mTitles.add("社会");
        mTitles.add("娱乐");
        mTitles.add("科技");
        mTitles.add("游戏");
        for (String title : mTitles) {
            TabLayout.Tab tab = mTabLayout.newTab(); //创建tab
            tab.setText(title); //设置文字
            mTabLayout.addTab(tab); //添加到tabLayout中
        }

        mNewsFragmentPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager());
        mNewsFragmentPagerAdapter.setData(mTitles);
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

}
