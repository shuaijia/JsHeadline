package com.e.jia.news.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.e.jia.news.view.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<String> mList;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;

    public NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mFragments.size()) {
            return mFragments.get(position);
        } else {
            NewsListFragment fragment = new NewsListFragment();

            switch (position){
                case 0:
                    fragment.setTag("__all__");
                    break;
                case 1:
                    fragment.setTag("news_hot");
                    break;
                case 2:
                    fragment.setTag("news_sports");
                    break;
                case 3:
                    fragment.setTag("news_society");
                    break;
                case 4:
                    fragment.setTag("news_entertainment");
                    break;
                case 5:
                    fragment.setTag("news_tech");
                    break;
                case 6:
                    fragment.setTag("news_game");
                    break;
            }
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
        return mList.get(position);
    }

    public void setData(List<String> list) {
        mList = list;
    }
}
