package com.e.jia.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.jia.picture.R;
import com.jia.libnet.bean.news.NewsCommentBean;
import com.jia.libnet.bean.photo.PhotoCommentBean;
import com.jia.libui.CircularImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 新闻评论 适配器
 * Created by jia on 2018/4/9.
 * 人之所以能，是相信能。
 */
public class PhotoCommentAdapter extends RecyclerView.Adapter<PhotoCommentAdapter.NewsCommentViewHolder> {

    private Context context;

    private List<PhotoCommentBean.DataBean> list = new ArrayList<>();

    public PhotoCommentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PhotoCommentBean.DataBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public NewsCommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_news_comment, parent, false);
        return new NewsCommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsCommentViewHolder holder, int position) {

        PhotoCommentBean.DataBean data = list.get(position);

        Glide.with(context)
                .load(data.getComment().getUser_profile_image_url() + "")
                .into(holder.iv_avatar);

        holder.tv_likes.setText(data.getComment().getDigg_count() == 0 ? "暂无喜欢" : data.getComment().getDigg_count() + "赞");
        holder.tv_text.setText(data.getComment().getText() + "");
        holder.tv_username.setText(data.getComment().getUser_name() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsCommentViewHolder extends RecyclerView.ViewHolder {

        CircularImage iv_avatar;
        TextView tv_username;
        TextView tv_text;
        TextView tv_likes;

        public NewsCommentViewHolder(View itemView) {
            super(itemView);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_text = itemView.findViewById(R.id.tv_text);
            tv_likes = itemView.findViewById(R.id.tv_likes);
        }
    }

}
