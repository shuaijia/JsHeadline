package com.e.jia.news.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.e.jia.news.R;
import com.e.jia.news.ui.AppBarStateChangeListener;
import com.elbbbird.android.socialsdk.SocialSDK;
import com.elbbbird.android.socialsdk.model.SocialShareScene;
import com.elbbbird.android.socialsdk.otto.ShareBusEvent;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.jia.libnet.bean.news.NewsBean;
import com.jia.libui.utils.SPUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * 新闻详情界面
 * Created by jia on 2018/4/5.
 */
public class NewsDetailActivity extends BaseActivity {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipe;
    private WebView webView;
    private ImageView backdrop;

    private NewsBean.DataEntity data;

    private boolean haveImg = true;

    private String url;
    private String title;
    private String imgUrl;
    private String shareUrl;
    private String desc;
    private String groupId;
    private String itemId;

    private  String theme;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        imgUrl = getIntent().getStringExtra("imgUrl");
        shareUrl = getIntent().getStringExtra("shareUrl");
        desc = getIntent().getStringExtra("desc");
        groupId = getIntent().getStringExtra("groupId");
        itemId = getIntent().getStringExtra("itemId");

        if (TextUtils.isEmpty(imgUrl)) {
            haveImg = false;
            setContentView(R.layout.activity_news_detail_text);
        } else {
            haveImg = true;
            setContentView(R.layout.activity_news_detail_img);
            appbar = findViewById(R.id.appbar);
        }
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        swipe = findViewById(R.id.swipe);
        webView = findViewById(R.id.webView);
        if (haveImg)
            backdrop = findViewById(R.id.backdrop);

        theme = SPUtils.getData(this, "theme", "#3F51B5");
        swipe.setColorSchemeColors(Color.parseColor(theme));
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

        initToolBar();

        initAppBar();

        initWebView();
    }

    private void initAppBar() {
        if (appbar == null) return;
        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbar.setBackgroundColor(Color.parseColor("#00000000"));
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbar.setBackgroundColor(Color.parseColor(theme));
                } else {
                    //中间状态
                    toolbar.setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        toolbar.setTitle(title);

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

        if (TextUtils.isEmpty(url)) return;

        webView.loadUrl(url);

        if (haveImg)
            Glide.with(this)
                    .load(imgUrl)
                    .into(backdrop);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_comment) {

            Intent intent = new Intent(this, NewsCommentActivity.class);
            intent.putExtra("groupId", groupId);
            intent.putExtra("itemId", itemId);
            startActivity(intent);

        } else if (item.getItemId() == R.id.action_personal) {
            Snackbar.make(toolbar, "个人中心", Snackbar.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.action_share) {

            SocialSDK.shareTo(this, new SocialShareScene(2, "Headline", title, desc, url, shareUrl));

        } else if (item.getItemId() == R.id.action_chrome) {

            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }

        return false;
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
