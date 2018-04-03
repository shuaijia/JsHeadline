package com.e.jia.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jia.libnet.bean.news.NewsBean;

/**
 * Description:
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */

public abstract class NewsBaseViewHolder extends RecyclerView.ViewHolder {

    public NewsBaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(NewsBean.DataEntity data);
}
