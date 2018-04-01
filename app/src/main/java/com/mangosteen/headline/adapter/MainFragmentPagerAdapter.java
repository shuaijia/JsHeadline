package com.mangosteen.headline.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.e.jia.news.NewsFragment;
import com.e.jia.picture.PictureFragment;
import com.e.jia.video.VideoFragment;

/**
 * Created by jia on 2018/3/31.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private NewsFragment mNewsFragment;
    private PictureFragment mPictureFragment;
    private VideoFragment mVideoFragment;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mNewsFragment=new NewsFragment();
        mPictureFragment=new PictureFragment();
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new NewsFragment();
        } else if (i == 1) {
            return new PictureFragment();
        } else if (i == 2) {
            return new VideoFragment();
        } else {
            return new VideoFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
