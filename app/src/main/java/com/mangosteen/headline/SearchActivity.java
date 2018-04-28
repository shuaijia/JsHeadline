package com.mangosteen.headline;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description: 搜索界面
 * Created by jia on 2018/4/28.
 * 人之所以能，是相信能。
 */

public class SearchActivity extends BaseActivity {

    private Toolbar toolbar;
    private SearchView searchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);

        initToolBar();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        toolbar.setTitle("搜索");

        setSupportActionBar(toolbar);

        //设置导航的图标
        toolbar.setNavigationIcon(com.e.jia.news.R.drawable.ic_arrow_back_black_24dp);
        // 左侧图标点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchAutoComplete.isShown()) {
                    try {
                        mSearchAutoComplete.setText("");//清除文本
                        //利用反射调用收起SearchView的onCloseClicked()方法
                        Method method = searchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(searchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });
    }

    /**
     * 初始化搜索框
     */
    private void initSearchView() {
        searchView.setSubmitButtonEnabled(true);//显示提交按钮
        searchView.setIconified(false);//设置searchView处于展开状态
        searchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
        searchView.setIconifiedByDefault(true);//默认为true在框内，设置false则在框外
        searchView.setSubmitButtonEnabled(true);// 显示提交按钮
        searchView.setQueryHint("输入关键字");
        mSearchAutoComplete = searchView.findViewById(R.id.search_src_text);
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.white));//设置提示文字颜色
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));//设置内容文字颜色

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(searchView, "搜索" + query, BaseTransientBottomBar.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        initSearchView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}
