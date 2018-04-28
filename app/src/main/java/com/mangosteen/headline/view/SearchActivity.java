package com.mangosteen.headline.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jia.base.BaseActivity;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.mangosteen.headline.R;
import com.mangosteen.headline.contract.SearchContract;
import com.mangosteen.headline.presenter.SearchPresenter;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Description: 搜索界面
 * Created by jia on 2018/4/28.
 * 人之所以能，是相信能。
 */

public class SearchActivity extends BaseActivity<SearchContract.SearchView, SearchPresenter>
        implements SearchContract.SearchView {

    private String[] resource = {"李健的歌", "父亲写的散文诗", "程序员", "旅行在路上", "梦醒时分", "青春", "娱乐八卦", "办公室", "笔记本", "JAVA", "小米"};

    private Toolbar toolbar;
    private SearchView searchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private TextView tv_hot_info;
    private LinearLayout ll_hot;
    private LinearLayout ll_content;
    private FlexboxLayout flexbox_layout;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        tv_hot_info = findViewById(R.id.tv_hot_info);
        ll_hot = findViewById(R.id.ll_hot);
        ll_content = findViewById(R.id.ll_content);
        flexbox_layout = findViewById(R.id.flexbox_layout);

        ll_hot.setVisibility(View.VISIBLE);
        ll_content.setVisibility(View.GONE);

        initToolBar();
    }

    /**
     * 初始化 热门 标签
     */
    private void initHotTags(List<SearchRecommentBean.DataBean.SuggestWordListBean> words) {
        int size = words.size() > 10 ? 10 : words.size();
        for (int i = 0; i < size; i++) {
            TextView tv = new TextView(this);
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(13.0f);
            tv.setPadding(15, 5, 15, 5);

            tv.setText(words.get(i).getWord());

            switch (words.get(i).getType()) {
                case "hist":
                    tv.setBackgroundResource(R.color.Blue);
                    break;
                case "recom":
                    tv.setBackgroundResource(R.color.Orange);
                    break;
                default:
                    tv.setBackgroundResource(R.color.Green);
                    break;
            }

            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
            lp.leftMargin = 10;
            lp.rightMargin = 10;
            lp.bottomMargin = 7;
            lp.topMargin = 7;
            tv.setLayoutParams(lp);

            flexbox_layout.addView(tv);
        }
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
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(null);
    }

    @Override
    protected void initData() {
        mPresenter.getSearchHotWords();
    }

    @Override
    public void onGetHotWords(List<SearchRecommentBean.DataBean.SuggestWordListBean> words) {
        initHotTags(words);
    }

    @Override
    public void onNoHotWords() {
        tv_hot_info.setText("暂无热门推荐");
    }
}
