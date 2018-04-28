package com.mangosteen.headline.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.jia.base.BaseActivity;
import com.jia.libnet.bean.news.NewsBean;
import com.jia.libnet.bean.search.SearchRecommentBean;
import com.jia.libui.utils.SPUtils;
import com.mangosteen.headline.R;
import com.mangosteen.headline.adapter.SearchResultAdapter;
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
        implements SearchContract.SearchView, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private String[] resource = {"李健的歌", "父亲写的散文诗", "程序员", "旅行在路上", "梦醒时分", "青春", "娱乐八卦", "办公室", "笔记本", "JAVA", "小米"};

    private Toolbar toolbar;
    private SearchView searchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private TextView tv_hot_info;
    private LinearLayout ll_hot;
    private RelativeLayout ll_content;
    private FlexboxLayout flexbox_layout;
    private SwipeRefreshLayout swipe;
    private RecyclerView rv_search;
    private TextView tv_no_result;

    // 搜索的关键词
    private String keyword = "";

    private SearchResultAdapter adapter;

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
        swipe = findViewById(R.id.swipe);
        rv_search = findViewById(R.id.rv_search);
        tv_no_result = findViewById(R.id.tv_no_result);

        tv_no_result.setVisibility(View.GONE);

        ll_hot.setVisibility(View.VISIBLE);
        ll_content.setVisibility(View.GONE);

        initToolBar();

        rv_search.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchResultAdapter(this);
        rv_search.setAdapter(adapter);

        swipe.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String theme = SPUtils.getData(this, "theme", "#3F51B5");
        swipe.setColorSchemeColors(Color.parseColor(theme));
    }

    /**
     * 初始化 热门 标签
     */
    private void initHotTags(final List<SearchRecommentBean.DataBean.SuggestWordListBean> words) {
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

            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    keyword = words.get(finalI).getWord();
                    mPresenter.getSearchResult(keyword);
                    ll_hot.setVisibility(View.GONE);
                    ll_content.setVisibility(View.VISIBLE);
                    swipe.setRefreshing(true);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            });

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
                    if(ll_content.getVisibility()==View.VISIBLE){
                        ll_content.setVisibility(View.GONE);
                        ll_hot.setVisibility(View.VISIBLE);
                    }else{
                        try {
                            mSearchAutoComplete.setText("");//清除文本
                            //利用反射调用收起SearchView的onCloseClicked()方法
                            Method method = searchView.getClass().getDeclaredMethod("onCloseClicked");
                            method.setAccessible(true);
                            method.invoke(searchView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
        searchView.setOnQueryTextListener(this);
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

    @Override
    public void onGetSearchResults(List<NewsBean.DataEntity> newList) {
        adapter.setList(newList);
        swipe.setRefreshing(false);
        tv_no_result.setVisibility(View.GONE);
    }

    @Override
    public void onNoResult() {
        swipe.setRefreshing(false);
        tv_no_result.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResultFail(String info) {
        ToastUtils.showLong(info);
        swipe.setRefreshing(false);
        tv_no_result.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        mPresenter.getSearchResult(keyword);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        keyword = query;
        ll_hot.setVisibility(View.GONE);
        ll_content.setVisibility(View.VISIBLE);
        swipe.setRefreshing(true);
        mPresenter.getSearchResult(keyword);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
