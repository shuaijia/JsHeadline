package com.e.jia.picture.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.e.jia.picture.view.PhotoListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class PhotoFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles={"组图","老照片","故事照片","摄影集"};
    private String[] categorys={"组图","gallery_old_picture","gallery_story","gallery_photograthy"};
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;

    public PhotoFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mFragments.size()) {
            return mFragments.get(position);
        } else {
            PhotoListFragment fragment = new PhotoListFragment();
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
