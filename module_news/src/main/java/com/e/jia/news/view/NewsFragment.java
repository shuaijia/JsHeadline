package com.e.jia.news.view;

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
import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia on 2018/3/31.
 */

public class NewsFragment extends BaseFragment implements View.OnClickListener{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView iv_channel_add;

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
        iv_channel_add = view.findViewById(R.id.iv_channel_add);

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

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.iv_channel_add) {


        }
    }
}
