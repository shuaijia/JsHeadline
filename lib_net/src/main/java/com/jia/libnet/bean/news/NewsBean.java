package com.jia.libnet.bean.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Created by jia on 2018/4/2.
 * 人之所以能，是相信能。
 */

public class NewsBean  {


    /**
     * has_more : true
     * message : success
     * data : [{"media_name":"新华网","abstract":"中国国家主席习近平将应邀出席博鳌亚洲论坛2018年年会开幕式并发表重要主旨演讲，会见与会外国国家元首、政府首脑和国际组织负责人。","impression_count":0,"image_list":[],"media_avatar_url":"http://p1.pstatp.com/large/dcc0005c79a11921544","external_visit_count":0,"article_type":0,"more_mode":false,"tag":"","is_favorite":0,"has_m3u8_video":0,"keywords":"国家元首,古特雷斯,联合国秘书长,国务委员,奥地利总统","has_mp4_video":0,"favorite_count":0,"display_url":"http://toutiao.com/group/6540037168423764493/","article_sub_type":0,"bury_count":0,"title":"习近平将出席博鳌亚洲论坛2018年年会开幕式","datetime":"2018-04-03 20:18","aggr_type":0,"has_video":false,"share_url":"http://toutiao.com/group/6540037168423764493/?iid=75274805517&app=news_article","id":6540037168423764493,"source":"新华网","comment_count":0,"article_url":"http://www.xinhuanet.com/politics/2018-04/03/c_1122630387.htm","create_time":1522721063,"recommend":0,"tips":0,"is_digg":0,"item_source_url":"/group/6540037168423764493/","media_url":"http://toutiao.com/m4377795668/","display_time":1522720897,"publish_time":1522720897,"go_detail_count":0,"group_flags":0,"middle_mode":false,"display_title":" ","gallary_image_count":0,"item_seo_url":"/i6540037168423764493/","tag_id":6540037168423764493,"source_url":"/group/6540037168423764493/","article_genre":"article","item_id":0,"natant_level":0,"large_mode":false,"seo_url":"/i6540037168423764493/","repin_count":0,"url":"http://www.xinhuanet.com/politics/2018-04/03/c_1122630387.htm","level":0,"digg_count":0,"behot_time":1522757933,"hot":0,"preload_web":2,"comments_count":0,"has_image":false,"is_bury":0,"group_id":6540037168423764493}]
     * next : {"max_behot_time":1522757933}
     */

    private boolean has_more;
    private String message;
    private NextEntity next;
    private List<DataEntity> data;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NextEntity getNext() {
        return next;
    }

    public void setNext(NextEntity next) {
        this.next = next;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class NextEntity {
        /**
         * max_behot_time : 1522757933
         */

        private int max_behot_time;

        public int getMax_behot_time() {
            return max_behot_time;
        }

        public void setMax_behot_time(int max_behot_time) {
            this.max_behot_time = max_behot_time;
        }
    }

    public static class DataEntity {
        /**
         * media_name : 新华网
         * abstract : 中国国家主席习近平将应邀出席博鳌亚洲论坛2018年年会开幕式并发表重要主旨演讲，会见与会外国国家元首、政府首脑和国际组织负责人。
         * impression_count : 0
         * image_list : []
         * media_avatar_url : http://p1.pstatp.com/large/dcc0005c79a11921544
         * external_visit_count : 0
         * article_type : 0
         * more_mode : false
         * tag :
         * is_favorite : 0
         * has_m3u8_video : 0
         * keywords : 国家元首,古特雷斯,联合国秘书长,国务委员,奥地利总统
         * has_mp4_video : 0
         * favorite_count : 0
         * display_url : http://toutiao.com/group/6540037168423764493/
         * article_sub_type : 0
         * bury_count : 0
         * title : 习近平将出席博鳌亚洲论坛2018年年会开幕式
         * datetime : 2018-04-03 20:18
         * aggr_type : 0
         * has_video : false
         * share_url : http://toutiao.com/group/6540037168423764493/?iid=75274805517&app=news_article
         * id : 6540037168423764493
         * source : 新华网
         * comment_count : 0
         * article_url : http://www.xinhuanet.com/politics/2018-04/03/c_1122630387.htm
         * create_time : 1522721063
         * recommend : 0
         * tips : 0
         * is_digg : 0
         * item_source_url : /group/6540037168423764493/
         * media_url : http://toutiao.com/m4377795668/
         * display_time : 1522720897
         * publish_time : 1522720897
         * go_detail_count : 0
         * group_flags : 0
         * middle_mode : false
         * display_title :
         * gallary_image_count : 0
         * item_seo_url : /i6540037168423764493/
         * tag_id : 6540037168423764493
         * source_url : /group/6540037168423764493/
         * article_genre : article
         * item_id : 0
         * natant_level : 0
         * large_mode : false
         * seo_url : /i6540037168423764493/
         * repin_count : 0
         * url : http://www.xinhuanet.com/politics/2018-04/03/c_1122630387.htm
         * level : 0
         * digg_count : 0
         * behot_time : 1522757933
         * hot : 0
         * preload_web : 2
         * comments_count : 0
         * has_image : false
         * is_bury : 0
         * group_id : 6540037168423764493
         */

        private String media_name;
        @SerializedName("abstract")
        private String abstractX;
        private int impression_count;
        private String media_avatar_url;
        private int external_visit_count;
        private int article_type;
        private boolean more_mode;
        private String tag;
        private int is_favorite;
        private int has_m3u8_video;
        private String keywords;
        private int has_mp4_video;
        private int favorite_count;
        private String display_url;
        private int article_sub_type;
        private int bury_count;
        private String title;
        private String datetime;
        private int aggr_type;
        private boolean has_video;
        private String share_url;
        private long id;
        private String source;
        private int comment_count;
        private String article_url;
        private int create_time;
        private int recommend;
        private int tips;
        private int is_digg;
        private String item_source_url;
        private String media_url;
        private int display_time;
        private int publish_time;
        private int go_detail_count;
        private int group_flags;
        private boolean middle_mode;
        private String display_title;
        private int gallary_image_count;
        private String item_seo_url;
        private long tag_id;
        private String source_url;
        private String article_genre;
        private long item_id;
        private int natant_level;
        private boolean large_mode;
        private String seo_url;
        private int repin_count;
        private String url;
        private int level;
        private int digg_count;
        private int behot_time;
        private int hot;
        private int preload_web;
        private int comments_count;
        private boolean has_image;
        private int is_bury;
        private long group_id;
        private List<?> image_list;

        public String getMedia_name() {
            return media_name;
        }

        public void setMedia_name(String media_name) {
            this.media_name = media_name;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public int getImpression_count() {
            return impression_count;
        }

        public void setImpression_count(int impression_count) {
            this.impression_count = impression_count;
        }

        public String getMedia_avatar_url() {
            return media_avatar_url;
        }

        public void setMedia_avatar_url(String media_avatar_url) {
            this.media_avatar_url = media_avatar_url;
        }

        public int getExternal_visit_count() {
            return external_visit_count;
        }

        public void setExternal_visit_count(int external_visit_count) {
            this.external_visit_count = external_visit_count;
        }

        public int getArticle_type() {
            return article_type;
        }

        public void setArticle_type(int article_type) {
            this.article_type = article_type;
        }

        public boolean isMore_mode() {
            return more_mode;
        }

        public void setMore_mode(boolean more_mode) {
            this.more_mode = more_mode;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(int is_favorite) {
            this.is_favorite = is_favorite;
        }

        public int getHas_m3u8_video() {
            return has_m3u8_video;
        }

        public void setHas_m3u8_video(int has_m3u8_video) {
            this.has_m3u8_video = has_m3u8_video;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getHas_mp4_video() {
            return has_mp4_video;
        }

        public void setHas_mp4_video(int has_mp4_video) {
            this.has_mp4_video = has_mp4_video;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public String getDisplay_url() {
            return display_url;
        }

        public void setDisplay_url(String display_url) {
            this.display_url = display_url;
        }

        public int getArticle_sub_type() {
            return article_sub_type;
        }

        public void setArticle_sub_type(int article_sub_type) {
            this.article_sub_type = article_sub_type;
        }

        public int getBury_count() {
            return bury_count;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public int getAggr_type() {
            return aggr_type;
        }

        public void setAggr_type(int aggr_type) {
            this.aggr_type = aggr_type;
        }

        public boolean isHas_video() {
            return has_video;
        }

        public void setHas_video(boolean has_video) {
            this.has_video = has_video;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public int getTips() {
            return tips;
        }

        public void setTips(int tips) {
            this.tips = tips;
        }

        public int getIs_digg() {
            return is_digg;
        }

        public void setIs_digg(int is_digg) {
            this.is_digg = is_digg;
        }

        public String getItem_source_url() {
            return item_source_url;
        }

        public void setItem_source_url(String item_source_url) {
            this.item_source_url = item_source_url;
        }

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }

        public int getDisplay_time() {
            return display_time;
        }

        public void setDisplay_time(int display_time) {
            this.display_time = display_time;
        }

        public int getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(int publish_time) {
            this.publish_time = publish_time;
        }

        public int getGo_detail_count() {
            return go_detail_count;
        }

        public void setGo_detail_count(int go_detail_count) {
            this.go_detail_count = go_detail_count;
        }

        public int getGroup_flags() {
            return group_flags;
        }

        public void setGroup_flags(int group_flags) {
            this.group_flags = group_flags;
        }

        public boolean isMiddle_mode() {
            return middle_mode;
        }

        public void setMiddle_mode(boolean middle_mode) {
            this.middle_mode = middle_mode;
        }

        public String getDisplay_title() {
            return display_title;
        }

        public void setDisplay_title(String display_title) {
            this.display_title = display_title;
        }

        public int getGallary_image_count() {
            return gallary_image_count;
        }

        public void setGallary_image_count(int gallary_image_count) {
            this.gallary_image_count = gallary_image_count;
        }

        public String getItem_seo_url() {
            return item_seo_url;
        }

        public void setItem_seo_url(String item_seo_url) {
            this.item_seo_url = item_seo_url;
        }

        public long getTag_id() {
            return tag_id;
        }

        public void setTag_id(long tag_id) {
            this.tag_id = tag_id;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public String getArticle_genre() {
            return article_genre;
        }

        public void setArticle_genre(String article_genre) {
            this.article_genre = article_genre;
        }

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public int getNatant_level() {
            return natant_level;
        }

        public void setNatant_level(int natant_level) {
            this.natant_level = natant_level;
        }

        public boolean isLarge_mode() {
            return large_mode;
        }

        public void setLarge_mode(boolean large_mode) {
            this.large_mode = large_mode;
        }

        public String getSeo_url() {
            return seo_url;
        }

        public void setSeo_url(String seo_url) {
            this.seo_url = seo_url;
        }

        public int getRepin_count() {
            return repin_count;
        }

        public void setRepin_count(int repin_count) {
            this.repin_count = repin_count;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public int getBehot_time() {
            return behot_time;
        }

        public void setBehot_time(int behot_time) {
            this.behot_time = behot_time;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getPreload_web() {
            return preload_web;
        }

        public void setPreload_web(int preload_web) {
            this.preload_web = preload_web;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public boolean isHas_image() {
            return has_image;
        }

        public void setHas_image(boolean has_image) {
            this.has_image = has_image;
        }

        public int getIs_bury() {
            return is_bury;
        }

        public void setIs_bury(int is_bury) {
            this.is_bury = is_bury;
        }

        public long getGroup_id() {
            return group_id;
        }

        public void setGroup_id(long group_id) {
            this.group_id = group_id;
        }

        public List<?> getImage_list() {
            return image_list;
        }

        public void setImage_list(List<?> image_list) {
            this.image_list = image_list;
        }
    }
}
