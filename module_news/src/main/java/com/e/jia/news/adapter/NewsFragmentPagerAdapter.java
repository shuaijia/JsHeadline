package com.e.jia.news.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.e.jia.news.view.NewsListFragment;
import com.jia.libnet.bean.channel.NewsChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<NewsChannel.Channel> mList;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;

    public  NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mFragments.size()) {
            return mFragments.get(position);
        } else {
            NewsListFragment fragment = new NewsListFragment();
            fragment.setTag(mList.get(position).getTag());
            mFragments.add(fragment);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getName();
    }

    public void setData(List<NewsChannel.Channel> channelList) {
        mList = channelList;
    }
}
