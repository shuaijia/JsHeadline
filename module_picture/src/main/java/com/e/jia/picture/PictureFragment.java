package com.e.jia.picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.jia.base.BaseFragment;
import com.jia.base.BasePresenter;

/**
 * Created by jia on 2018/3/31.
 */

public class PictureFragment extends BaseFragment {
    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_picture,null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
