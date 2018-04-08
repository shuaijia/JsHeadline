package com.e.jia.news.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.e.jia.news.R;
import com.elbbbird.android.socialsdk.SocialSDK;
import com.elbbbird.android.socialsdk.model.SocialShareScene;
import com.elbbbird.android.socialsdk.otto.ShareBusEvent;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.news.NewsBean;

import org.greenrobot.eventbus.Subscribe;

/**
 * 新闻详情界面
 * Created by jia on 2018/4/5.
 */
public class NewsDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout swipe;
    private WebView webView;
    private ImageView backdrop;

    private NewsBean.DataEntity data;

    private String url;
    private String title;
    private String imgUrl;
    private String shareUrl;
    private String desc;

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
        webView.setWebViewClient(new DetailWebViewClient());
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
        shareUrl = getIntent().getStringExtra("shareUrl");
        desc = getIntent().getStringExtra("desc");


        toolbar.setTitle(title);
        if (TextUtils.isEmpty(url)) return;

        webView.loadUrl(url);

        if (TextUtils.isEmpty(imgUrl)) {
            backdrop.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(imgUrl)
                    .into(backdrop);
        }

        SocialSDK.shareTo(this, new SocialShareScene(2, "Headline", title, desc, url, shareUrl));
    }

    class DetailWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.e(TAG, "onReceivedError: " + error.toString());
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e(TAG, "shouldOverrideUrlLoading: " + url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Subscribe
    public void onShareResult(ShareBusEvent event) {
        switch (event.getType()) {
            case ShareBusEvent.TYPE_SUCCESS:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_SUCCESS " + event.getId());
                Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_FAILURE " + e.toString());
                Toast.makeText(this,"分享失败",Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_CANCEL");
                Toast.makeText(this,"分享取消",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
