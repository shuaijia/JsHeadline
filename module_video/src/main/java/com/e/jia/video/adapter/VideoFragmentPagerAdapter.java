package com.e.jia.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.e.jia.video.view.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 视频首页适配器
 * Created by jia on 2018/4/13.
 * 人之所以能，是相信能。
 */

public class VideoFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles={"推荐","音乐","搞笑","社会","小品","生活","影视","娱乐","游戏"};
    private String[] categorys={"video","subv_voice","subv_funny","subv_society","subv_comedy","subv_life","subv_movie","subv_entertainment","subv_game"};
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;

    public VideoFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mFragments.size()) {
            return mFragments.get(position);
        } else {
            VideoListFragment fragment = new VideoListFragment();
            fragment.setCategory(categorys[position]);
            mFragments.add(fragment);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return categorys.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public String[] getCategorys() {
        return categorys;
    }

    public void setCategorys(String[] categorys) {
        this.categorys = categorys;
    }
}

