package com.mangosteen.headline.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jia.base.BaseActivity;
import com.jia.base.BasePresenter;
import com.mangosteen.headline.R;

public class SettingActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
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
