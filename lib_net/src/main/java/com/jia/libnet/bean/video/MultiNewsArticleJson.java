package com.jia.libnet.bean.video;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Created by jia on 2018/5/22.
 * 人之所以能，是相信能。
 */

public class MultiNewsArticleJson {

    /**
     * abstract :
     * allow_download : false
     * article_sub_type : 0
     * article_type : 0
     * ban_comment : 0
     * behot_time : 1526976382
     * bury_count : 0
     * cell_height : 175
     * cell_type : 25
     * comment_count : 0
     * content_decoration :
     * cursor : 1526976382999
     * data_callback : window.refresh
     * data_url : http://ic.snssdk.com/ugc/star/forum_widget_data/?channel_name=entertainment
     * digg_count : 0
     * has_m3u8_video : false
     * has_mp4_video : 0
     * has_video : false
     * hot : 0
     * id : 84526838061
     * ignore_web_transform : 0
     * interaction_data :
     * is_subject : false
     * item_version : 0
     * level : 0
     * log_pb : {"impr_id":"20180522160622010008035073584535"}
     * read_count : 0
     * refresh_interval : 10
     * rid : 20180522160622010008035073584535
     * share_count : 0
     * share_info : null
     * show_dislike : false
     * show_portrait : false
     * show_portrait_article : false
     * template_md5 : 1ca8a1e497ab94f7b095484df2866b26
     * template_url : https://ic.snssdk.com/stream/widget/template/forum_chart/
     * tip : 0
     * user_repin : 0
     * user_verified : 0
     * verified_content :
     * video_style : 0
     */

    @SerializedName("abstract")
    private String abstractX;
    private boolean allow_download;
    private int article_sub_type;
    private int article_type;
    private int ban_comment;
    private int behot_time;
    private int bury_count;
    private int cell_height;
    private int cell_type;
    private int comment_count;
    private String content_decoration;
    private long cursor;
    private String data_callback;
    private String data_url;
    private int digg_count;
    private boolean has_m3u8_video;
    private int has_mp4_video;
    private boolean has_video;
    private int hot;
    private long id;
    private int ignore_web_transform;
    private String interaction_data;
    private boolean is_subject;
    private int item_version;
    private int level;
    private LogPbEntity log_pb;
    private int read_count;
    private int refresh_interval;
    private String rid;
    private int share_count;
    private Object share_info;
    private boolean show_dislike;
    private boolean show_portrait;
    private boolean show_portrait_article;
    private String template_md5;
    private String template_url;
    private int tip;
    private int user_repin;
    private int user_verified;
    private String verified_content;
    private int video_style;

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public boolean isAllow_download() {
        return allow_download;
    }

    public void setAllow_download(boolean allow_download) {
        this.allow_download = allow_download;
    }

    public int getArticle_sub_type() {
        return article_sub_type;
    }

    public void setArticle_sub_type(int article_sub_type) {
        this.article_sub_type = article_sub_type;
    }

    public int getArticle_type() {
        return article_type;
    }

    public void setArticle_type(int article_type) {
        this.article_type = article_type;
    }

    public int getBan_comment() {
        return ban_comment;
    }

    public void setBan_comment(int ban_comment) {
        this.ban_comment = ban_comment;
    }

    public int getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(int behot_time) {
        this.behot_time = behot_time;
    }

    public int getBury_count() {
        return bury_count;
    }

    public void setBury_count(int bury_count) {
        this.bury_count = bury_count;
    }

    public int getCell_height() {
        return cell_height;
    }

    public void setCell_height(int cell_height) {
        this.cell_height = cell_height;
    }

    public int getCell_type() {
        return cell_type;
    }

    public void setCell_type(int cell_type) {
        this.cell_type = cell_type;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getContent_decoration() {
        return content_decoration;
    }

    public void setContent_decoration(String content_decoration) {
        this.content_decoration = content_decoration;
    }

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    public String getData_callback() {
        return data_callback;
    }

    public void setData_callback(String data_callback) {
        this.data_callback = data_callback;
    }

    public String getData_url() {
        return data_url;
    }

    public void setData_url(String data_url) {
        this.data_url = data_url;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public boolean isHas_m3u8_video() {
        return has_m3u8_video;
    }

    public void setHas_m3u8_video(boolean has_m3u8_video) {
        this.has_m3u8_video = has_m3u8_video;
    }

    public int getHas_mp4_video() {
        return has_mp4_video;
    }

    public void setHas_mp4_video(int has_mp4_video) {
        this.has_mp4_video = has_mp4_video;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIgnore_web_transform() {
        return ignore_web_transform;
    }

    public void setIgnore_web_transform(int ignore_web_transform) {
        this.ignore_web_transform = ignore_web_transform;
    }

    public String getInteraction_data() {
        return interaction_data;
    }

    public void setInteraction_data(String interaction_data) {
        this.interaction_data = interaction_data;
    }

    public boolean isIs_subject() {
        return is_subject;
    }

    public void setIs_subject(boolean is_subject) {
        this.is_subject = is_subject;
    }

    public int getItem_version() {
        return item_version;
    }

    public void setItem_version(int item_version) {
        this.item_version = item_version;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LogPbEntity getLog_pb() {
        return log_pb;
    }

    public void setLog_pb(LogPbEntity log_pb) {
        this.log_pb = log_pb;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public int getRefresh_interval() {
        return refresh_interval;
    }

    public void setRefresh_interval(int refresh_interval) {
        this.refresh_interval = refresh_interval;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public Object getShare_info() {
        return share_info;
    }

    public void setShare_info(Object share_info) {
        this.share_info = share_info;
    }

    public boolean isShow_dislike() {
        return show_dislike;
    }

    public void setShow_dislike(boolean show_dislike) {
        this.show_dislike = show_dislike;
    }

    public boolean isShow_portrait() {
        return show_portrait;
    }

    public void setShow_portrait(boolean show_portrait) {
        this.show_portrait = show_portrait;
    }

    public boolean isShow_portrait_article() {
        return show_portrait_article;
    }

    public void setShow_portrait_article(boolean show_portrait_article) {
        this.show_portrait_article = show_portrait_article;
    }

    public String getTemplate_md5() {
        return template_md5;
    }

    public void setTemplate_md5(String template_md5) {
        this.template_md5 = template_md5;
    }

    public String getTemplate_url() {
        return template_url;
    }

    public void setTemplate_url(String template_url) {
        this.template_url = template_url;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getUser_repin() {
        return user_repin;
    }

    public void setUser_repin(int user_repin) {
        this.user_repin = user_repin;
    }

    public int getUser_verified() {
        return user_verified;
    }

    public void setUser_verified(int user_verified) {
        this.user_verified = user_verified;
    }

    public String getVerified_content() {
        return verified_content;
    }

    public void setVerified_content(String verified_content) {
        this.verified_content = verified_content;
    }

    public int getVideo_style() {
        return video_style;
    }

    public void setVideo_style(int video_style) {
        this.video_style = video_style;
    }

    public static class LogPbEntity {
        /**
         * impr_id : 20180522160622010008035073584535
         */

        private String impr_id;

        public String getImpr_id() {
            return impr_id;
        }

        public void setImpr_id(String impr_id) {
            this.impr_id = impr_id;
        }
    }
}
