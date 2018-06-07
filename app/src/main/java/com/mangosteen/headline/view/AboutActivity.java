package com.mangosteen.headline.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.mangosteen.headline.R;

public class AboutActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView tv_author;
    private TextView tv_version;
    private TextView tv_github_url;
    private TextView tv_points;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initView() {
        tv_author = findViewById(R.id.tv_author);
        tv_version = findViewById(R.id.tv_version);
        tv_github_url = findViewById(R.id.tv_github_url);
        tv_points = findViewById(R.id.tv_points);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        // 左侧图标点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initAuthor();

        initVersion();

        initGithub();

        initPoints();
    }

    private void initAuthor() {

        String names = "康白    贾帅";

        SpannableString spannableString = new SpannableString(names);

        spannableString.setSpan(new ClickableColorSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(AboutActivity.this,AboutMeKbActivity.class));
            }
        }, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ClickableColorSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(AboutActivity.this,AboutMeJsActivity.class));
            }
        }, 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_author.setText(spannableString);
        tv_author.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initVersion() {
        tv_version.setText("v" + getVersionName());
    }

    private void initGithub() {
        final String github = "https://github.com/shuaijia/Headline";

        SpannableString spannableString = new SpannableString(github);

        spannableString.setSpan(new ClickableColorSpan() {
            @Override
            public void onClick(View widget) {
                Uri uri = Uri.parse(github);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }, 0, github.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tv_github_url.setText(spannableString);
        tv_github_url.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initPoints() {
        String points = "- 基本遵循Material Design设计风格；\n" +
                "- 使用经典MVP模式进行封装；\n" +
                "- 模块化开发，结构分为底层net、ui和utils、base库、功能module，主app；\n" +
                "- 网络框架使用Retrofit+RxJava+OkHttp+Glide进行封装；\n" +
                "- 对OkHttp请求头进行处理，添加cookie和保存cookie；\n" +
                "- 使用Retrofit+RxJava做多线程下载、断点续传；\n" +
                "- 对ExoPlayer进行封装，做视频播放器；\n" +
                "- 自定义View实现流畅弹幕；\n" +
                "- 对EventBus进行封装，使用注解来注册，进行消息发送和处理；\n" +
                "- BaseActivity与BaseFragment封装，配合MVP模式框架；\n" +
                "- 使用自定义注解实现Router方案；\n" +
                "- 使用 7.0 新工具 DiffUtil , 不再无脑 notifyDataSetChanged；\n" +
                "- 使用 ItemTouchHelper 实现今日头条的频道排序、频道移动；\n" +
                "- 使用AndroidChangeSkin实现应用内换肤。";

        tv_points.setText(points);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    private String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }

    public abstract class ClickableColorSpan extends ClickableSpan {

        public ClickableColorSpan() {

        }

        @Override
        public void updateDrawState(TextPaint ds) {

            // ds.setColor(ds.linkColor);
            ds.setColor(getResources().getColor(R.color.colorPrimary));
            ds.setUnderlineText(true);

        }
    }
}
