package com.mangosteen.headline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.e.jia.news.view.NewsFragment;
import com.e.jia.picture.view.PictureFragment;
import com.e.jia.video.view.VideoFragment;
import com.elbbbird.android.socialsdk.SocialSDK;
import com.elbbbird.android.socialsdk.model.SocialShareScene;
import com.elbbbird.android.socialsdk.otto.ShareBusEvent;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.base.annotation.Action;
import com.jia.base.annotation.BindEventBus;
import com.jia.base.eventbus.Event;
import com.mangosteen.headline.dialog.ThemeDialog;
import com.mangosteen.headline.view.AboutActivity;
import com.mangosteen.headline.view.SearchActivity;
import com.mangosteen.headline.view.SettingActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

@BindEventBus
@Action("MainActivity")
public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    //
    private static final int TAB_NEWS = 0;
    private static final int TAB_PICTURE = 1;
    private static final int TAB_VIDEO = 2;

    @BindView(R.id.tab_bottom)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @BindView(R.id.navigation_view)
    NavigationView navigation;

    private String[] titles = {"新闻", "图片", "视频"};
    private Fragment mNewsFragment;
    private Fragment mPictureFragment;
    private Fragment mVideoFragment;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTabLayout();

        initToolBar();

        initNavigationView();

        //状态变化时删除老的Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fm.findFragmentById(R.id.frag_container);
        if (fragment != null) {
            ft.remove(fragment);
            fm.popBackStack();
            ft.commit();
        }

        select(TAB_NEWS);
    }

    private void initNavigationView() {
        navigation.setItemIconTintList(null);// 解决图标没颜色的问题
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_manage:

                        startActivity(new Intent(MainActivity.this, AboutActivity.class));

                        break;
                    case R.id.nav_camera:

                        ThemeDialog dialog = new ThemeDialog(MainActivity.this);
                        dialog.show();

                        break;
                    case R.id.nav_share:

                        startActivity(new Intent(MainActivity.this, SettingActivity.class));

                        break;
                    case R.id.nav_send:

                        SocialSDK.shareTo(MainActivity.this, new SocialShareScene(2, "Headline", "Headline", "你关心的才是头条", "https://github.com/shuaijia", "https://github.com/kb18519142009"));

                        break;
                }
                return true;
            }

        });
    }

    /**
     * 初始化 选项卡
     */
    private void initTabLayout() {
        for (String title : titles) {
            TabLayout.Tab tab = mTabLayout.newTab(); //创建tab
            tab.setText(title); //设置文字
            tab.setIcon(R.mipmap.ic_launcher); //设置图片
            mTabLayout.addTab(tab); //添加到tabLayout中
        }

        mTabLayout.getTabAt(0).setIcon(R.drawable.selector_main_tab_news);
        mTabLayout.getTabAt(1).setIcon(R.drawable.selector_main_tab_img);
        mTabLayout.getTabAt(2).setIcon(R.drawable.selector_main_tab_video);
        mTabLayout.addOnTabSelectedListener(this);
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        //设置导航的图标
        toolbar.setNavigationIcon(com.e.jia.news.R.drawable.ic_menu);
        // 设置主标题
        toolbar.setTitle("新闻");
        setSupportActionBar(toolbar);
        // 左侧图标点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    @Subscribe
    public void onHandleEvent(Event e) {

    }

    private void select(int positon) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        hideAllFragments(transaction);

        switch (positon) {
            case TAB_NEWS:
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                    transaction.add(R.id.frag_container, mNewsFragment);
                } else {
                    transaction.show(mNewsFragment);
                }
                toolbar.setTitle("新闻");
                break;
            case TAB_PICTURE:
                if (mPictureFragment == null) {
                    mPictureFragment = new PictureFragment();
                    transaction.add(R.id.frag_container, mPictureFragment);
                } else {
                    transaction.show(mPictureFragment);
                }
                toolbar.setTitle("图片");
                break;
            case TAB_VIDEO:
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                    transaction.add(R.id.frag_container, mVideoFragment);
                } else {
                    transaction.show(mVideoFragment);
                }
                toolbar.setTitle("视频");
                break;
        }
        transaction.commit();
    }

    private void hideAllFragments(FragmentTransaction transaction) {
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mPictureFragment != null) transaction.hide(mPictureFragment);
        if (mVideoFragment != null) transaction.hide(mVideoFragment);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        select(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                // 跳转设置
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Subscribe
    public void onShareResult(ShareBusEvent event) {
        switch (event.getType()) {
            case ShareBusEvent.TYPE_SUCCESS:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_SUCCESS " + event.getId());
                Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_FAILURE " + e.toString());
                Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_CANCEL");
                Toast.makeText(this, "分享取消", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
