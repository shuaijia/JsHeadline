package com.mangosteen.headline.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}
