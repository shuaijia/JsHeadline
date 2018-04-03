package com.e.jia.news.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.jia.news.R;
import com.jia.libnet.bean.news.NewsBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 图片新闻
 * Created by jia on 2018/4/3.
 * 人之所以能，是相信能。
 */

public class ImgNewsViewHolder extends NewsBaseViewHolder {

    public ImageView iv_image;
    public TextView tv_title;
    public TextView tv_abstract;// 简介
    public TextView tv_extra;
    public ImageView iv_dots;

    public ImgNewsViewHolder(View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_abstract = itemView.findViewById(R.id.tv_abstract);
        tv_extra = itemView.findViewById(R.id.tv_extra);
        iv_dots = itemView.findViewById(R.id.iv_dots);
        iv_image=itemView.findViewById(R.id.iv_image);
    }

    @Override
    public void bindView(NewsBean.DataEntity data) {
        tv_title.setText(data.getTitle() + "");
        tv_abstract.setText(data.getAbstractX() + "");
        StringBuffer extra=new StringBuffer();
        extra.append(data.getMedia_name()+" ");
        extra.append(data.getComments_count()+" ");
        extra.append(stampToDate(data.getPublish_time()+""));
        tv_extra.setText(extra);

        Glide.with(itemView.getContext())
                .load(data.getItem_source_url())
                .into(iv_image);
    }

    /*
* 将时间戳转换为时间
*/
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
