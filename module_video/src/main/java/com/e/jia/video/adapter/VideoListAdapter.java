package com.e.jia.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.jia.video.R;
import com.jia.libnet.bean.video.VideoArticleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by jia on 2018/4/13.
 * 人之所以能，是相信能。
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {

    private Context context;

    private List<VideoArticleBean> list = new ArrayList<>();

    public VideoListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<VideoArticleBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VideoListViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getMedia_info().getAvatar_url())
                .into(holder.iv_media);

        StringBuffer sb = new StringBuffer();
        sb.append(list.get(position).getMedia_name() + " ");
        sb.append(list.get(position).getComment_count() + "条评论 ");
        holder.tv_extra.setText(sb);

        holder.tv_title.setText(list.get(position).getTitle() + "");

//        holder.tv_dur.setText(list.get(position).getVideo_duration());

        Glide.with(context)
                .load(list.get(position).getVideo_detail_info().getDetail_video_large_image().getUrl() + "")
                .into(holder.iv_video_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoListViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_media;
        TextView tv_extra;
        ImageView iv_dots;
        TextView tv_title;
        ImageView iv_video_image;
        TextView tv_dur;

        public VideoListViewHolder(View itemView) {
            super(itemView);

            iv_media = itemView.findViewById(R.id.iv_media);
            tv_extra = itemView.findViewById(R.id.tv_extra);
            iv_dots = itemView.findViewById(R.id.iv_dots);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_video_image = itemView.findViewById(R.id.iv_video_image);
            tv_dur = itemView.findViewById(R.id.tv_dur);
        }
    }
}
