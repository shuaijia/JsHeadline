package com.jia.libnet.bean.video;

/**
 * Description:
 * Created by jia on 2018/6/8.
 * 人之所以能，是相信能。
 */

public class VideoDetailBean {

    private String mediaAvatarUrl;
    private String mediaName;
    private String title;

    public VideoDetailBean(String mediaAvatarUrl, String mediaName, String title) {
        this.mediaAvatarUrl = mediaAvatarUrl;
        this.mediaName = mediaName;
        this.title = title;
    }

    public String getMediaAvatarUrl() {
        return mediaAvatarUrl;
    }

    public void setMediaAvatarUrl(String mediaAvatarUrl) {
        this.mediaAvatarUrl = mediaAvatarUrl;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
