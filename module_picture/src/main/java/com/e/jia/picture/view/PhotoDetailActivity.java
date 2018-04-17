package com.e.jia.picture.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.Toast;

import com.e.jia.picture.R;
import com.elbbbird.android.socialsdk.SocialSDK;
import com.elbbbird.android.socialsdk.model.SocialShareScene;
import com.elbbbird.android.socialsdk.otto.ShareBusEvent;
import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;

import org.greenrobot.eventbus.Subscribe;

/**
 * Description: 图片 详情界面
 * Created by jia on 2018/4/17.
 * 人之所以能，是相信能。
 */

public class PhotoDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Toolbar toolbar;
    private WebView webView;
    private SwipeRefreshLayout swipe;

    private String url;
    private String title;
    private String imgUrl;
    private String shareUrl;
    private String desc;
    private String groupId;
    private String itemId;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo_content);
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        imgUrl = getIntent().getStringExtra("imgUrl");
        shareUrl = getIntent().getStringExtra("shareUrl");
        desc = getIntent().getStringExtra("desc");
        groupId = getIntent().getStringExtra("groupId");
        itemId = getIntent().getStringExtra("itemId");

        if (!url.contains("toutiao")) {
            shareUrl = "http://toutiao.com" + shareUrl;
            url = "http://toutiao.com" + url;
        }

        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webView);
        swipe = findViewById(R.id.swipe);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipe.setOnRefreshListener(this);

        initToolBar();

        initWebView();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(url)) return;

        webView.loadUrl(url);
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
    public void onRefresh() {
        webView.reload();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_comment) {

            Intent intent = new Intent(this, PhotoCommentActivity.class);
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
