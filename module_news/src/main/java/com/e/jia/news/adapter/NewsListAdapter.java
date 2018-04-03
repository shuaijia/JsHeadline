package com.e.jia.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.jia.news.R;
import com.jia.libnet.bean.news.NewsBean;

import java.util.List;

/**
 * Description: 新闻列表 适配器
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsBaseViewHolder> {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMG = 2;
    public static final int TYPE_VIDEO = 3;

    private Context context;

    private List<NewsBean.DataEntity> list;

    private NewsListAdapter(Context context) {
        this.context = context;
    }

    private void setData(List<NewsBean.DataEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public NewsBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsBaseViewHolder viewHolder;
        switch (viewType) {
            case TYPE_TEXT:
                View v1 = LayoutInflater.from(context).inflate(R.layout.item_news_article_text, parent, false);
                viewHolder = new TextNewsViewHolder(v1);
                break;
            case TYPE_IMG:
                View v2 = LayoutInflater.from(context).inflate(R.layout.item_news_article_img, parent, false);
                viewHolder = new TextNewsViewHolder(v2);
                break;
            case TYPE_VIDEO:
                View v3 = LayoutInflater.from(context).inflate(R.layout.item_news_article_video, parent, false);
                viewHolder = new TextNewsViewHolder(v3);
                break;
            default:
                View v4 = LayoutInflater.from(context).inflate(R.layout.item_news_article_text, parent, false);
                viewHolder = new TextNewsViewHolder(v4);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsBaseViewHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
