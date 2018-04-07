package com.e.jia.news.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.e.jia.news.R;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.base.annotation.BindEventBus;
import com.jia.libnet.bean.news.NewsBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 新闻详情界面
 * Created by jia on 2018/4/5.
 */
public class NewsDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout swipe;
    private WebView webView;
    private ImageView backdrop;
    private CollapsingToolbarLayout collapsing_toolbar;

    private NewsBean.DataEntity data;

    private String url;
    private String title;
    private String imgUrl;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        swipe = findViewById(R.id.swipe);
        webView = findViewById(R.id.webView);
        backdrop = findViewById(R.id.backdrop);
        collapsing_toolbar=findViewById(R.id.collapsing_toolbar);

        swipe.setColorSchemeResources(R.color.colorPrimary);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

        initToolBar();

        initWebView();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        setSupportActionBar(toolbar);
        //设置导航的图标
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        // 左侧图标点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        settings.setBuiltInZoomControls(false);
        // 缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启DOM storage API功能
        settings.setDomStorageEnabled(true);
        // 开启application Cache功能
        settings.setAppCacheEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (Math.abs(newProgress - 100) < 20) {
                    swipe.setRefreshing(false);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        imgUrl = getIntent().getStringExtra("imgUrl");

        toolbar.setTitle(title);
        collapsing_toolbar.setTitle(title);

        if (TextUtils.isEmpty(url)) return;

        webView.loadUrl(url);

        if (TextUtils.isEmpty(imgUrl)) {
            backdrop.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(imgUrl)
                    .into(backdrop);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_comment) {
            Snackbar.make(toolbar, "搜索", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.action_personal) {
            Snackbar.make(toolbar, "个人中心", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.action_share) {
            Snackbar.make(toolbar, "分享", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.action_chrome) {
            Snackbar.make(toolbar, "浏览器", Snackbar.LENGTH_LONG).show();
        }

        return false;
    }
}
