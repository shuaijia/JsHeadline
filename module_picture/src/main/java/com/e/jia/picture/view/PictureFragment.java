package com.e.jia.picture.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import com.e.jia.picture.R;
import com.e.jia.picture.adapter.PhotoFragmentPagerAdapter;
import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;
import com.jia.libui.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class PictureFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager vp_img_content;

    private List<String> mTitles = new ArrayList<>();

    private PhotoFragmentPagerAdapter pagerAdapter;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_picture,null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        mTabLayout=view.findViewById(R.id.tl_img_titles);
        vp_img_content=view.findViewById(R.id.vp_img_content);

        String theme= SPUtils.getData(getActivity(),"theme","#3F51B5");
        mTabLayout.setBackgroundColor(Color.parseColor(theme));
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        mTitles.add("全部");
        mTitles.add("老照片");
        mTitles.add("故事照片");
        mTitles.add("摄影集");
        for (String title : mTitles) {
            TabLayout.Tab tab = mTabLayout.newTab(); //创建tab
            tab.setText(title); //设置文字
            mTabLayout.addTab(tab); //添加到tabLayout中
        }

        pagerAdapter = new PhotoFragmentPagerAdapter(getChildFragmentManager());
        vp_img_content.setAdapter(pagerAdapter);
        vp_img_content.setOffscreenPageLimit(10);
        mTabLayout.setupWithViewPager(vp_img_content);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
