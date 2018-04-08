package com.jia.base.event;

import com.jia.libnet.bean.channel.NewsChannel;

import java.util.List;

/**
 * Created by jia on 2018/4/5.
 */

public class NewsChannelEvent {

    private List<NewsChannel.Channel> list;

    public List<NewsChannel.Channel> getList() {
        return list;
    }

    public void setList(List<NewsChannel.Channel> list) {
        this.list = list;
    }
}
