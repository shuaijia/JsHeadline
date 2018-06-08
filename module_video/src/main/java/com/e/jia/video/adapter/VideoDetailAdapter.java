package com.e.jia.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.jia.video.R;
import com.jia.libnet.bean.video.VideoCommentBean;
import com.jia.libnet.bean.video.VideoDetailBean;
import com.jia.libui.CircularImage;

import java.security.PublicKey;
import java.util.List;

/**
 * Description:
 * Created by jia on 2018/6/8.
 * 人之所以能，是相信能。
 */

public class VideoDetailAdapter extends RecyclerView.Adapter {

    public static final int TYPE_DETAIL = 1;
    public static final int TYPE_COMMENT = 2;

    private Context context;
    private List<Object> list;

    public VideoDetailAdapter(Context context) {
        this.context = context;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_DETAIL:
                View v = LayoutInflater.from(context).inflate(R.layout.item_video_info, parent, false);
                viewHolder = new VideoDetailViewHolder(v);
                break;
            case TYPE_COMMENT:
                View v2 = LayoutInflater.from(context).inflate(R.layout.item_video_comment, parent, false);
                viewHolder = new VideoCommentViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoDetailViewHolder) {

            VideoDetailBean detail = (VideoDetailBean) list.get(position);
            ((VideoDetailViewHolder) holder).tv_media_name.setText(detail.getMediaName());
            ((VideoDetailViewHolder) holder).tv_video_title.setText(detail.getTitle());
            Glide.with(context)
                    .load(detail.getMediaAvatarUrl())
                    .into(((VideoDetailViewHolder) holder).iv_media_head);

        } else if (holder instanceof VideoCommentViewHolder) {

            VideoCommentBean.DataEntity.CommentEntity comment = (VideoCommentBean.DataEntity.CommentEntity) list.get(position);
            ((VideoCommentViewHolder) holder).tv_name.setText(comment.getUser_name());
            ((VideoCommentViewHolder) holder).tv_comment.setText(comment.getText());
            Glide.with(context)
                    .load(comment.getUser_profile_image_url())
                    .into(((VideoCommentViewHolder) holder).iv_avatar);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof VideoDetailBean) {
            return TYPE_DETAIL;
        } else {
            return TYPE_COMMENT;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class VideoDetailViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_video_title;
        public CircularImage iv_media_head;
        public TextView tv_media_name;
        public ImageView tv_video_download;
        public ImageView iv_video_share;

        public VideoDetailViewHolder(View itemView) {
            super(itemView);
            tv_video_title = itemView.findViewById(R.id.tv_video_title);
            iv_media_head = itemView.findViewById(R.id.iv_media_head);
            tv_media_name = itemView.findViewById(R.id.tv_media_name);
            tv_video_download = itemView.findViewById(R.id.tv_video_download);
            iv_video_share = itemView.findViewById(R.id.iv_video_share);
        }
    }

    public class VideoCommentViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public TextView tv_comment;
        public CircularImage iv_avatar;

        public VideoCommentViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
