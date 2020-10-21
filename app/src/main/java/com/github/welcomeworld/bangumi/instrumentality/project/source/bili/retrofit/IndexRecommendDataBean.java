package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndexRecommendDataBean {

    private String param;
    @SerializedName("goto")
    private String gotoX;
    private int idx;
    private int autoplay_card;
    private String title;
    private String cover;
    private String uri;
    private String desc;
    private int play;
    private int danmaku;
    private int reply;
    private int favorite;
    private int coin;
    private int share;
    private int like;
    private int duration;
    private int cid;
    private int tid;
    private String tname;
    private TagBean tag;
    private int ctime;
    private int autoplay;
    private int mid;
    private String name;
    private String face;
    private OfficialBean official;
    private RcmdReasonBean rcmd_reason;
    private String badge;
    private String square;
    private List<DislikeReasonsBean> dislike_reasons;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getGotoX() {
        return gotoX;
    }

    public void setGotoX(String gotoX) {
        this.gotoX = gotoX;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getAutoplay_card() {
        return autoplay_card;
    }

    public void setAutoplay_card(int autoplay_card) {
        this.autoplay_card = autoplay_card;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public int getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(int danmaku) {
        this.danmaku = danmaku;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public TagBean getTag() {
        return tag;
    }

    public void setTag(TagBean tag) {
        this.tag = tag;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public int getAutoplay() {
        return autoplay;
    }

    public void setAutoplay(int autoplay) {
        this.autoplay = autoplay;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public OfficialBean getOfficial() {
        return official;
    }

    public void setOfficial(OfficialBean official) {
        this.official = official;
    }

    public RcmdReasonBean getRcmd_reason() {
        return rcmd_reason;
    }

    public void setRcmd_reason(RcmdReasonBean rcmd_reason) {
        this.rcmd_reason = rcmd_reason;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public List<DislikeReasonsBean> getDislike_reasons() {
        return dislike_reasons;
    }

    public void setDislike_reasons(List<DislikeReasonsBean> dislike_reasons) {
        this.dislike_reasons = dislike_reasons;
    }

    public static class TagBean {

        private int tag_id;
        private String tag_name;
        private CountBean count;

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public static class CountBean {

            private int atten;

            public int getAtten() {
                return atten;
            }

            public void setAtten(int atten) {
                this.atten = atten;
            }
        }
    }

    public static class OfficialBean {

        private int role;
        private String title;

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class RcmdReasonBean {

        private int id;
        private String content;
        private String bg_color;
        private String icon_location;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getBg_color() {
            return bg_color;
        }

        public void setBg_color(String bg_color) {
            this.bg_color = bg_color;
        }

        public String getIcon_location() {
            return icon_location;
        }

        public void setIcon_location(String icon_location) {
            this.icon_location = icon_location;
        }
    }

    public static class DislikeReasonsBean {

        private int reason_id;
        private String reason_name;

        public int getReason_id() {
            return reason_id;
        }

        public void setReason_id(int reason_id) {
            this.reason_id = reason_id;
        }

        public String getReason_name() {
            return reason_name;
        }

        public void setReason_name(String reason_name) {
            this.reason_name = reason_name;
        }
    }
}
