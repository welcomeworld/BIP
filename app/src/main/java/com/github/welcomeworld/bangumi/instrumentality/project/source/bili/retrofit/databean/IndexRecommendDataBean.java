package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class IndexRecommendDataBean {

    private String param;
    @SerializedName("goto")
    private String gotoX;
    private long idx;
    private long autoplay_card;
    private String title;
    private String cover;
    private String uri;
    private String desc;
    private long play;
    private long danmaku;
    private long reply;
    private long favorite;
    private long coin;
    private long share;
    private long like;
    private long duration;
    private long cid;
    private long tid;
    private String tname;
    private TagBean tag;
    private long ctime;
    private long autoplay;
    private long mid;
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

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public long getAutoplay_card() {
        return autoplay_card;
    }

    public void setAutoplay_card(long autoplay_card) {
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

    public long getPlay() {
        return play;
    }

    public void setPlay(long play) {
        this.play = play;
    }

    public long getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(long danmaku) {
        this.danmaku = danmaku;
    }

    public long getReply() {
        return reply;
    }

    public void setReply(long reply) {
        this.reply = reply;
    }

    public long getFavorite() {
        return favorite;
    }

    public void setFavorite(long favorite) {
        this.favorite = favorite;
    }

    public long getCoin() {
        return coin;
    }

    public void setCoin(long coin) {
        this.coin = coin;
    }

    public long getShare() {
        return share;
    }

    public void setShare(long share) {
        this.share = share;
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
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

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getAutoplay() {
        return autoplay;
    }

    public void setAutoplay(long autoplay) {
        this.autoplay = autoplay;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
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

        private long tag_id;
        private String tag_name;
        private CountBean count;

        public long getTag_id() {
            return tag_id;
        }

        public void setTag_id(long tag_id) {
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

            private long atten;

            public long getAtten() {
                return atten;
            }

            public void setAtten(long atten) {
                this.atten = atten;
            }

            @Override
            public String toString() {
                return "CountBean{" +
                        "atten=" + atten +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "TagBean{" +
                    "tag_id=" + tag_id +
                    ", tag_name='" + tag_name + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

    public static class OfficialBean {

        private long role;
        private String title;

        public long getRole() {
            return role;
        }

        public void setRole(long role) {
            this.role = role;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "OfficialBean{" +
                    "role=" + role +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static class RcmdReasonBean {

        private long id;
        private String content;
        private String bg_color;
        private String icon_location;

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

        @Override
        public String toString() {
            return "RcmdReasonBean{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", bg_color='" + bg_color + '\'' +
                    ", icon_location='" + icon_location + '\'' +
                    '}';
        }
    }

    public static class DislikeReasonsBean {

        private long reason_id;
        private String reason_name;

        public long getReason_id() {
            return reason_id;
        }

        public void setReason_id(long reason_id) {
            this.reason_id = reason_id;
        }

        public String getReason_name() {
            return reason_name;
        }

        public void setReason_name(String reason_name) {
            this.reason_name = reason_name;
        }

        @Override
        public String toString() {
            return "DislikeReasonsBean{" +
                    "reason_id=" + reason_id +
                    ", reason_name='" + reason_name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "IndexRecommendDataBean{" +
                "param='" + param + '\'' +
                ", gotoX='" + gotoX + '\'' +
                ", idx=" + idx +
                ", autoplay_card=" + autoplay_card +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", uri='" + uri + '\'' +
                ", desc='" + desc + '\'' +
                ", play=" + play +
                ", danmaku=" + danmaku +
                ", reply=" + reply +
                ", favorite=" + favorite +
                ", coin=" + coin +
                ", share=" + share +
                ", like=" + like +
                ", duration=" + duration +
                ", cid=" + cid +
                ", tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tag=" + tag +
                ", ctime=" + ctime +
                ", autoplay=" + autoplay +
                ", mid=" + mid +
                ", name='" + name + '\'' +
                ", face='" + face + '\'' +
                ", official=" + official +
                ", rcmd_reason=" + rcmd_reason +
                ", badge='" + badge + '\'' +
                ", square='" + square + '\'' +
                ", dislike_reasons=" + dislike_reasons +
                '}';
    }
}
