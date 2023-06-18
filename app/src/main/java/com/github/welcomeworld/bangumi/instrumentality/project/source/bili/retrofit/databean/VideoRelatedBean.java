package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class VideoRelatedBean {

    @SerializedName("code")
    private long code;
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("message")
    private String message;
    @SerializedName("spec")
    private Spec spec;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public static class Spec {
        @SerializedName("type")
        private long type;
        @SerializedName("card")
        private Card card;

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        public static class Card {
            @SerializedName("id")
            private long id;
            @SerializedName("type")
            private long type;
            @SerializedName("title")
            private String title;
            @SerializedName("desc")
            private String desc;
            @SerializedName("cover")
            private String cover;
            @SerializedName("re_type")
            private long reType;
            @SerializedName("re_value")
            private String reValue;
            @SerializedName("person")
            private String person;
            @SerializedName("ctime")
            private long ctime;
            @SerializedName("mtime")
            private long mtime;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public long getReType() {
                return reType;
            }

            public void setReType(long reType) {
                this.reType = reType;
            }

            public String getReValue() {
                return reValue;
            }

            public void setReValue(String reValue) {
                this.reValue = reValue;
            }

            public String getPerson() {
                return person;
            }

            public void setPerson(String person) {
                this.person = person;
            }

            public long getCtime() {
                return ctime;
            }

            public void setCtime(long ctime) {
                this.ctime = ctime;
            }

            public long getMtime() {
                return mtime;
            }

            public void setMtime(long mtime) {
                this.mtime = mtime;
            }
        }
    }

    public static class Data {
        @SerializedName("aid")
        private long aid;
        @SerializedName("videos")
        private long videos;
        @SerializedName("tid")
        private long tid;
        @SerializedName("tname")
        private String tname;
        @SerializedName("copyright")
        private long copyright;
        @SerializedName("pic")
        private String pic;
        @SerializedName("title")
        private String title;
        @SerializedName("pubdate")
        private long pubdate;
        @SerializedName("ctime")
        private long ctime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("state")
        private long state;
        @SerializedName("duration")
        private long duration;
        @SerializedName("mission_id")
        private long missionId;
        @SerializedName("rights")
        private Rights rights;
        @SerializedName("owner")
        private Owner owner;
        @SerializedName("stat")
        private Stat stat;
        @SerializedName("dynamic")
        private String dynamic;
        @SerializedName("cid")
        private long cid;
        @SerializedName("dimension")
        private Dimension dimension;
        @SerializedName("short_link_v2")
        private String shortLinkV2;
        @SerializedName("first_frame")
        private String firstFrame;
        @SerializedName("pub_location")
        private String pubLocation;
        @SerializedName("bvid")
        private String bvid;
        @SerializedName("season_type")
        private long seasonType;
        @SerializedName("is_ogv")
        private boolean isOgv;
        @SerializedName("ogv_info")
        private Object ogvInfo;
        @SerializedName("rcmd_reason")
        private String rcmdReason;
        @SerializedName("season_id")
        private long seasonId;
        @SerializedName("redirect_url")
        private String redirectUrl;
        @SerializedName("up_from_v2")
        private long upFromV2;

        public long getAid() {
            return aid;
        }

        public void setAid(long aid) {
            this.aid = aid;
        }

        public long getVideos() {
            return videos;
        }

        public void setVideos(long videos) {
            this.videos = videos;
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

        public long getCopyright() {
            return copyright;
        }

        public void setCopyright(long copyright) {
            this.copyright = copyright;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getPubdate() {
            return pubdate;
        }

        public void setPubdate(long pubdate) {
            this.pubdate = pubdate;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public long getState() {
            return state;
        }

        public void setState(long state) {
            this.state = state;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public long getMissionId() {
            return missionId;
        }

        public void setMissionId(long missionId) {
            this.missionId = missionId;
        }

        public Rights getRights() {
            return rights;
        }

        public void setRights(Rights rights) {
            this.rights = rights;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public Stat getStat() {
            return stat;
        }

        public void setStat(Stat stat) {
            this.stat = stat;
        }

        public String getDynamic() {
            return dynamic;
        }

        public void setDynamic(String dynamic) {
            this.dynamic = dynamic;
        }

        public long getCid() {
            return cid;
        }

        public void setCid(long cid) {
            this.cid = cid;
        }

        public Dimension getDimension() {
            return dimension;
        }

        public void setDimension(Dimension dimension) {
            this.dimension = dimension;
        }

        public String getShortLinkV2() {
            return shortLinkV2;
        }

        public void setShortLinkV2(String shortLinkV2) {
            this.shortLinkV2 = shortLinkV2;
        }

        public String getFirstFrame() {
            return firstFrame;
        }

        public void setFirstFrame(String firstFrame) {
            this.firstFrame = firstFrame;
        }

        public String getPubLocation() {
            return pubLocation;
        }

        public void setPubLocation(String pubLocation) {
            this.pubLocation = pubLocation;
        }

        public String getBvid() {
            return bvid;
        }

        public void setBvid(String bvid) {
            this.bvid = bvid;
        }

        public long getSeasonType() {
            return seasonType;
        }

        public void setSeasonType(long seasonType) {
            this.seasonType = seasonType;
        }

        public boolean isIsOgv() {
            return isOgv;
        }

        public void setIsOgv(boolean isOgv) {
            this.isOgv = isOgv;
        }

        public Object getOgvInfo() {
            return ogvInfo;
        }

        public void setOgvInfo(Object ogvInfo) {
            this.ogvInfo = ogvInfo;
        }

        public String getRcmdReason() {
            return rcmdReason;
        }

        public void setRcmdReason(String rcmdReason) {
            this.rcmdReason = rcmdReason;
        }

        public long getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(long seasonId) {
            this.seasonId = seasonId;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public long getUpFromV2() {
            return upFromV2;
        }

        public void setUpFromV2(long upFromV2) {
            this.upFromV2 = upFromV2;
        }

        public static class Rights {
            @SerializedName("bp")
            private long bp;
            @SerializedName("elec")
            private long elec;
            @SerializedName("download")
            private long download;
            @SerializedName("movie")
            private long movie;
            @SerializedName("pay")
            private long pay;
            @SerializedName("hd5")
            private long hd5;
            @SerializedName("no_reprlong")
            private long noReprlong;
            @SerializedName("autoplay")
            private long autoplay;
            @SerializedName("ugc_pay")
            private long ugcPay;
            @SerializedName("is_cooperation")
            private long isCooperation;
            @SerializedName("ugc_pay_preview")
            private long ugcPayPreview;
            @SerializedName("no_background")
            private long noBackground;
            @SerializedName("arc_pay")
            private long arcPay;
            @SerializedName("pay_free_watch")
            private long payFreeWatch;

            public long getBp() {
                return bp;
            }

            public void setBp(long bp) {
                this.bp = bp;
            }

            public long getElec() {
                return elec;
            }

            public void setElec(long elec) {
                this.elec = elec;
            }

            public long getDownload() {
                return download;
            }

            public void setDownload(long download) {
                this.download = download;
            }

            public long getMovie() {
                return movie;
            }

            public void setMovie(long movie) {
                this.movie = movie;
            }

            public long getPay() {
                return pay;
            }

            public void setPay(long pay) {
                this.pay = pay;
            }

            public long getHd5() {
                return hd5;
            }

            public void setHd5(long hd5) {
                this.hd5 = hd5;
            }

            public long getNoReprlong() {
                return noReprlong;
            }

            public void setNoReprlong(long noReprlong) {
                this.noReprlong = noReprlong;
            }

            public long getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(long autoplay) {
                this.autoplay = autoplay;
            }

            public long getUgcPay() {
                return ugcPay;
            }

            public void setUgcPay(long ugcPay) {
                this.ugcPay = ugcPay;
            }

            public long getIsCooperation() {
                return isCooperation;
            }

            public void setIsCooperation(long isCooperation) {
                this.isCooperation = isCooperation;
            }

            public long getUgcPayPreview() {
                return ugcPayPreview;
            }

            public void setUgcPayPreview(long ugcPayPreview) {
                this.ugcPayPreview = ugcPayPreview;
            }

            public long getNoBackground() {
                return noBackground;
            }

            public void setNoBackground(long noBackground) {
                this.noBackground = noBackground;
            }

            public long getArcPay() {
                return arcPay;
            }

            public void setArcPay(long arcPay) {
                this.arcPay = arcPay;
            }

            public long getPayFreeWatch() {
                return payFreeWatch;
            }

            public void setPayFreeWatch(long payFreeWatch) {
                this.payFreeWatch = payFreeWatch;
            }
        }

        public static class Owner {
            @SerializedName("mid")
            private long mid;
            @SerializedName("name")
            private String name;
            @SerializedName("face")
            private String face;

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
        }

        public static class Stat {
            @SerializedName("aid")
            private long aid;
            @SerializedName("view")
            private long view;
            @SerializedName("danmaku")
            private long danmaku;
            @SerializedName("reply")
            private long reply;
            @SerializedName("favorite")
            private long favorite;
            @SerializedName("coin")
            private long coin;
            @SerializedName("share")
            private long share;
            @SerializedName("now_rank")
            private long nowRank;
            @SerializedName("his_rank")
            private long hisRank;
            @SerializedName("like")
            private long like;
            @SerializedName("dislike")
            private long dislike;
            @SerializedName("vt")
            private long vt;
            @SerializedName("vv")
            private long vv;

            public long getAid() {
                return aid;
            }

            public void setAid(long aid) {
                this.aid = aid;
            }

            public long getView() {
                return view;
            }

            public void setView(long view) {
                this.view = view;
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

            public long getNowRank() {
                return nowRank;
            }

            public void setNowRank(long nowRank) {
                this.nowRank = nowRank;
            }

            public long getHisRank() {
                return hisRank;
            }

            public void setHisRank(long hisRank) {
                this.hisRank = hisRank;
            }

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }

            public long getDislike() {
                return dislike;
            }

            public void setDislike(long dislike) {
                this.dislike = dislike;
            }

            public long getVt() {
                return vt;
            }

            public void setVt(long vt) {
                this.vt = vt;
            }

            public long getVv() {
                return vv;
            }

            public void setVv(long vv) {
                this.vv = vv;
            }
        }

        public static class Dimension {
            @SerializedName("width")
            private long width;
            @SerializedName("height")
            private long height;
            @SerializedName("rotate")
            private long rotate;

            public long getWidth() {
                return width;
            }

            public void setWidth(long width) {
                this.width = width;
            }

            public long getHeight() {
                return height;
            }

            public void setHeight(long height) {
                this.height = height;
            }

            public long getRotate() {
                return rotate;
            }

            public void setRotate(long rotate) {
                this.rotate = rotate;
            }
        }
    }
}
