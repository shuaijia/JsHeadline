package com.e.jia.news.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.jia.news.R;
import com.jia.libnet.bean.news.NewsBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 视频新闻
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */
public class VideoNewsViewHolder extends NewsBaseViewHolder {

    ImageView iv_media;
    TextView tv_extra;
    ImageView iv_dots;
    TextView tv_title;
    ImageView iv_video_image;

    public VideoNewsViewHolder(View itemView) {
        super(itemView);
        iv_media = itemView.findViewById(R.id.iv_media);
        tv_extra = itemView.findViewById(R.id.tv_media);
        iv_dots = itemView.findViewById(R.id.iv_dots);
        tv_title = itemView.findViewById(R.id.tv_title);
        iv_video_image = itemView.findViewById(R.id.iv_video_image);
    }

    @Override
    public void bindView(NewsBean.DataEntity data) {
        tv_title.setText(data.getTitle() + "");
        StringBuffer extra = new StringBuffer();
        if (TextUtils.isEmpty(data.getMedia_name()) || data.getMedia_name().equals("null"))
            extra.append("推广 ");
        else
            extra.append(data.getMedia_name() + " ");
        if (data.getComment_count() == 0) {
            extra.append("暂无评论 ");
        } else {
            extra.append(data.getComments_count() + "条评论 ");
        }

        extra.append(stampToDate(data.getPublish_time() + ""));
        tv_extra.setText(extra);

        if (!TextUtils.isEmpty(data.getMedia_avatar_url())) {
            Glide.with(itemView.getContext())
                    .load(data.getMedia_avatar_url())
                    .into(iv_media);
        } else {
            iv_media.setVisibility(View.GONE);
        }

        Glide.with(itemView.getContext())
                .load(data.getImage_url())
                .into(iv_video_image);
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
