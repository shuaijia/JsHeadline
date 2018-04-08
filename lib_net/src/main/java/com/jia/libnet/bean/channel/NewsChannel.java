package com.jia.libnet.bean.channel;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻频道
 * Created by jia on 2018/4/5.
 */

public class NewsChannel {

    private List<Channel> channels;

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Channel> getSelectedList(){
        List<Channel> list=new ArrayList<>();
        for(Channel item:channels){
            if(item.isSelected) list.add(item);
        }
        return list;
    }

    public List<Channel> getNoSelectedList(){
        List<Channel> list=new ArrayList<>();
        for(Channel item:channels){
            if(!item.isSelected) list.add(item);
        }
        return list;
    }

    public static class Channel{
        String name;
        String tag;// 网络请求时的tag
        boolean isSelected;

        public Channel(String name, String tag, boolean isSelected) {
            this.name = name;
            this.tag = tag;
            this.isSelected = isSelected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @Override
        public String toString() {
            return "Channel{" +
                    "name='" + name + '\'' +
                    ", tag='" + tag + '\'' +
                    ", isSelected=" + isSelected +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsChannel{" +
                "channels=" + channels +
                '}';
    }
}
