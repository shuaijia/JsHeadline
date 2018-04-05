package com.e.jia.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.jia.news.R;
import com.jia.libnet.bean.channel.NewsChannel;

import java.util.List;

/**
 * Created by jia on 2018/4/5.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {

    private Context context;
    private List<NewsChannel.Channel> list;
    private boolean isMine;

    private boolean isEditable = false;

    private DataChangedListener listener;

    public ChannelAdapter(Context context, List<NewsChannel.Channel> list, boolean isMine) {
        this.context = context;
        this.list = list;
        this.isMine = isMine;
    }

    public void addData(NewsChannel.Channel data) {
        list.add(data);
        notifyItemInserted(list.size() - 1);
    }

    public List<NewsChannel.Channel> getList() {
        return this.list;
    }

    public DataChangedListener getListener() {
        return listener;
    }

    public void setListener(DataChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false);
        return new ChannelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, final int position) {
        holder.tv_channel.setText(list.get(position).getName() + "");

        if (isEditable && isMine) {
            holder.iv_delete.setVisibility(View.VISIBLE);
        } else {
            holder.iv_delete.setVisibility(View.GONE);
        }

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && list.size() > 0) {
                    listener.onDelete(list.get(position));
                    list.remove(position);
//                        notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            }
        });

        holder.tv_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMine) {
                    if (listener != null && list.size() > 0) {
                        listener.onDelete(list.get(position));
                        list.remove(position);
//                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                    }
                }
            }
        });

        holder.tv_channel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (isMine) {
                    if(isEditable){
                        isEditable = false;

                    }else{
                        isEditable = true;

                    }
                    notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_channel;
        public ImageView iv_delete;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            tv_channel = itemView.findViewById(R.id.tv_channel);
            iv_delete = itemView.findViewById(R.id.iv_delete);
        }
    }

    public interface DataChangedListener {

        void onAdd(NewsChannel.Channel channel);

        void onDelete(NewsChannel.Channel channel);
    }
}
