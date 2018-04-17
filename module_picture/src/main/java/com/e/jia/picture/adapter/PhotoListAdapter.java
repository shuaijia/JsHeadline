package com.e.jia.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.jia.picture.R;
import com.jia.libnet.bean.photo.PhotoArticleBean;
import com.jia.libui.CircularImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 图片列表 适配器
 * Created by jia on 2018/4/11.
 * 人之所以能，是相信能。
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoListViewHolder> {

    private Context context;
    private List<PhotoArticleBean.DataBean> list = new ArrayList<>();

    private OnItemClickListener listener;

    public PhotoListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PhotoArticleBean.DataBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public PhotoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_photo_article, parent, false);
        return new PhotoListViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(PhotoListViewHolder holder, final int position) {

        Glide.with(context)
                .load(list.get(position).getMedia_avatar_url())
                .into(holder.iv_media);

        Glide.with(context)
                .load("http:" + list.get(position).getImage_list().get(0).getUrl() + "")
                .into(holder.iv_0);
        Glide.with(context)
                .load("http:" + list.get(position).getImage_list().get(1).getUrl() + "")
                .into(holder.iv_1);
        Glide.with(context)
                .load("http:" + list.get(position).getImage_list().get(2).getUrl() + "")
                .into(holder.iv_2);

        holder.tv_title.setText(list.get(position).getTitle() + "");

        holder.tv_extra.setText(list.get(position).getSource() + "-" + list.get(position).getComments_count() + "条评论");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(list.get(position));
            }
        });
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class PhotoListViewHolder extends RecyclerView.ViewHolder {

        CircularImage iv_media;
        TextView tv_extra;
        TextView tv_title;
        ImageView iv_0;
        ImageView iv_1;
        ImageView iv_2;

        public PhotoListViewHolder(View itemView) {
            super(itemView);
            iv_media = itemView.findViewById(R.id.iv_media);
            tv_extra = itemView.findViewById(R.id.tv_extra);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_0 = itemView.findViewById(R.id.iv_0);
            iv_1 = itemView.findViewById(R.id.iv_1);
            iv_2 = itemView.findViewById(R.id.iv_2);
        }
    }

    public interface OnItemClickListener {
        void onClick(PhotoArticleBean.DataBean data);
    }
}
