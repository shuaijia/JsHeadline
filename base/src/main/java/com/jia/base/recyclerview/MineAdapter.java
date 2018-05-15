package com.jia.base.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Description:
 * Created by jia on 2018/3/6.
 * 人之所以能，是相信能
 */

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineViewHolder> {



    private Context context;

    private List<String> list;

    private boolean edit = false;

    public MineAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    /**
     * 设置是否编辑
     *
     * @param edit
     */
    public void setEditable(boolean edit) {
        this.edit = edit;
        notifyDataSetChanged();
    }

    public boolean isEdit() {
        return edit;
    }

    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
//        view = LayoutInflater.from(context).inflate(R.layout.item_mine, parent, false);
        return new MineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MineViewHolder holder, final int position) {

        holder.textView.setText(list.get(position));

        if (edit) {
            holder.ivClose.setVisibility(View.VISIBLE);
        } else {
            holder.ivClose.setVisibility(View.GONE);
        }

        holder.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MineViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ImageView ivClose;

        public MineViewHolder(View itemView) {
            super(itemView);

        }

    }
}
