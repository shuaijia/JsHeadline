package com.mangosteen.headline.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.mangosteen.headline.R;

/**
 * Description:
 * Created by jia on 2018/6/5.
 * 人之所以能，是相信能。
 */

public class AboutMeJsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private CollapsingToolbarLayout toolbar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_me_js);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_layout= (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar_layout.setTitle("");


        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

