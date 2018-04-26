package com.e.jia.picture.diffutil;

import android.support.v7.util.DiffUtil;

import com.jia.libnet.bean.photo.PhotoArticleBean;

import java.util.List;

/**
 * Description:
 * Created by jia on 2018/4/18.
 * 人之所以能，是相信能。
 */

public class PhotoDiffCallback extends DiffUtil.Callback {

    // 新老数据集
    private List<PhotoArticleBean.DataBean> oldList, newList;

    public PhotoDiffCallback(List<PhotoArticleBean.DataBean> oldList, List<PhotoArticleBean.DataBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    /**
     * 返回 老数据集 大小
     *
     * @return
     */
    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    /**
     * 返回 新数据集 大小
     *
     * @return
     */
    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    /**
     * 判断 两个对象是否是相同的Item
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(newItemPosition).getGroup_id() == newList.get(newItemPosition).getGroup_id();
    }

    /**
     * 检查 两个item是否含有相同的数据,DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化,
     * DiffUtil 用这个方法替代equals方法去检查是否相等。所以你可以根据你的UI去改变它的返回值。
     *
     * 例如，如果你用RecyclerView.Adapter 配合DiffUtil使用，你需要返回Item的视觉表现是否相同。
     *
     * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return false表示不同，true表示相同
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PhotoArticleBean.DataBean oldData = oldList.get(oldItemPosition);
        PhotoArticleBean.DataBean newData = newList.get(newItemPosition);
        if (!oldData.getTitle().equals(newData.getTitle()))
            return false;
        return true;
    }
}