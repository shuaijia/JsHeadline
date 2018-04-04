package com.e.jia.news.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.e.jia.news.R;
import com.jia.libnet.bean.news.NewsBean;
import com.jia.libui.CircularImage;
import com.jia.libui.JsPopupWindow;

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
    private CircularImage iv_media;

    public ImgNewsViewHolder(View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_abstract = itemView.findViewById(R.id.tv_abstract);
        tv_extra = itemView.findViewById(R.id.tv_extra);
        iv_dots = itemView.findViewById(R.id.iv_dots);
        iv_image = itemView.findViewById(R.id.iv_image);
        iv_media = itemView.findViewById(R.id.iv_media);
    }

    @Override
    public void bindView(NewsBean.DataEntity data) {
        tv_title.setText(data.getTitle() + "");
        tv_abstract.setText(data.getAbstractX() + "");

        Glide.with(itemView.getContext())
                .load(data.getImage_list().get(0).getUrl()+"")
                .into(iv_image);

        StringBuffer extra = new StringBuffer();
        extra.append(data.getMedia_name() + " ");
        if (data.getComments_count() == 0) {
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

        iv_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JsPopupWindow.Builder()
                        .setContentViewId(R.layout.popup_news_list)
                        .setContext(itemView.getContext())
                        .setOutSideCancle(true)
                        .setFouse(false)
                        .setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, itemView.getContext().getResources().getDisplayMetrics()))
                        .setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, itemView.getContext().getResources().getDisplayMetrics()))
                        .setAnimation(R.style.anim_pop)
                        .build()
                        .showAsLocation(iv_dots, Gravity.LEFT| Gravity.TOP, 100, 0);
            }
        });
    }

    /*
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
