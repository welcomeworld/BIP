package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BangumiDetailPageBean {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @SerializedName("cover")
        private String cover;
        @SerializedName("evaluate")
        private String evaluate;
        @SerializedName("link")
        private String link;
        @SerializedName("media_id")
        private int mediaId;
        @SerializedName("mode")
        private int mode;
        @SerializedName("new_ep")
        private NewEpBean newEp;
        @SerializedName("paster")
        private PasterBean paster;
        @SerializedName("payment")
        private PaymentBean payment;
        @SerializedName("publish")
        private PublishBean publish;
        @SerializedName("rating")
        private RatingBean rating;
        @SerializedName("record")
        private String record;
        @SerializedName("rights")
        private RightsBean rights;
        @SerializedName("season_id")
        private int seasonId;
        @SerializedName("season_title")
        private String seasonTitle;
        @SerializedName("series")
        private SeriesBean series;
        @SerializedName("share_copy")
        private String shareCopy;
        @SerializedName("share_url")
        private String shareUrl;
        @SerializedName("short_link")
        private String shortLink;
        @SerializedName("square_cover")
        private String squareCover;
        @SerializedName("stat")
        private StatBean stat;
        @SerializedName("status")
        private int status;
        @SerializedName("subtitle")
        private String subtitle;
        @SerializedName("title")
        private String title;
        @SerializedName("total")
        private int total;
        @SerializedName("type")
        private int type;
        @SerializedName("up_info")
        private UpInfoBean upInfo;
        @SerializedName("user_status")
        private UserStatusBean userStatus;
        @SerializedName("episodes")
        private List<EpisodesBean> episodes;
        @SerializedName("seasons")
        private List<SeasonsBean> seasons;
        @SerializedName("section")
        private List<SectionBean> section;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getMediaId() {
            return mediaId;
        }

        public void setMediaId(int mediaId) {
            this.mediaId = mediaId;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public NewEpBean getNewEp() {
            return newEp;
        }

        public void setNewEp(NewEpBean newEp) {
            this.newEp = newEp;
        }

        public PasterBean getPaster() {
            return paster;
        }

        public void setPaster(PasterBean paster) {
            this.paster = paster;
        }

        public PaymentBean getPayment() {
            return payment;
        }

        public void setPayment(PaymentBean payment) {
            this.payment = payment;
        }

        public PublishBean getPublish() {
            return publish;
        }

        public void setPublish(PublishBean publish) {
            this.publish = publish;
        }

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getRecord() {
            return record;
        }

        public void setRecord(String record) {
            this.record = record;
        }

        public RightsBean getRights() {
            return rights;
        }

        public void setRights(RightsBean rights) {
            this.rights = rights;
        }

        public int getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(int seasonId) {
            this.seasonId = seasonId;
        }

        public String getSeasonTitle() {
            return seasonTitle;
        }

        public void setSeasonTitle(String seasonTitle) {
            this.seasonTitle = seasonTitle;
        }

        public SeriesBean getSeries() {
            return series;
        }

        public void setSeries(SeriesBean series) {
            this.series = series;
        }

        public String getShareCopy() {
            return shareCopy;
        }

        public void setShareCopy(String shareCopy) {
            this.shareCopy = shareCopy;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getShortLink() {
            return shortLink;
        }

        public void setShortLink(String shortLink) {
            this.shortLink = shortLink;
        }

        public String getSquareCover() {
            return squareCover;
        }

        public void setSquareCover(String squareCover) {
            this.squareCover = squareCover;
        }

        public StatBean getStat() {
            return stat;
        }

        public void setStat(StatBean stat) {
            this.stat = stat;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public UpInfoBean getUpInfo() {
            return upInfo;
        }

        public void setUpInfo(UpInfoBean upInfo) {
            this.upInfo = upInfo;
        }

        public UserStatusBean getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(UserStatusBean userStatus) {
            this.userStatus = userStatus;
        }

        public List<EpisodesBean> getEpisodes() {
            return episodes;
        }

        public void setEpisodes(List<EpisodesBean> episodes) {
            this.episodes = episodes;
        }

        public List<SeasonsBean> getSeasons() {
            return seasons;
        }

        public void setSeasons(List<SeasonsBean> seasons) {
            this.seasons = seasons;
        }

        public List<SectionBean> getSection() {
            return section;
        }

        public void setSection(List<SectionBean> section) {
            this.section = section;
        }

        public static class NewEpBean {
            @SerializedName("desc")
            private String desc;
            @SerializedName("id")
            private int id;
            @SerializedName("is_new")
            private int isNew;
            @SerializedName("more")
            private String more;
            @SerializedName("title")
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public String getMore() {
                return more;
            }

            public void setMore(String more) {
                this.more = more;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class PasterBean {
            @SerializedName("aid")
            private int aid;
            @SerializedName("allow_jump")
            private int allowJump;
            @SerializedName("cid")
            private int cid;
            @SerializedName("duration")
            private int duration;
            @SerializedName("type")
            private int type;
            @SerializedName("url")
            private String url;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getAllowJump() {
                return allowJump;
            }

            public void setAllowJump(int allowJump) {
                this.allowJump = allowJump;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class PaymentBean {
            @SerializedName("dialog")
            private DialogBean dialog;
            @SerializedName("pay_tip")
            private PayTipBean payTip;
            @SerializedName("pay_type")
            private PayTypeBean payType;
            @SerializedName("price")
            private String price;
            @SerializedName("tv_price")
            private String tvPrice;
            @SerializedName("vip_promotion")
            private String vipPromotion;

            public DialogBean getDialog() {
                return dialog;
            }

            public void setDialog(DialogBean dialog) {
                this.dialog = dialog;
            }

            public PayTipBean getPayTip() {
                return payTip;
            }

            public void setPayTip(PayTipBean payTip) {
                this.payTip = payTip;
            }

            public PayTypeBean getPayType() {
                return payType;
            }

            public void setPayType(PayTypeBean payType) {
                this.payType = payType;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTvPrice() {
                return tvPrice;
            }

            public void setTvPrice(String tvPrice) {
                this.tvPrice = tvPrice;
            }

            public String getVipPromotion() {
                return vipPromotion;
            }

            public void setVipPromotion(String vipPromotion) {
                this.vipPromotion = vipPromotion;
            }

            public static class DialogBean {
                @SerializedName("btn_right")
                private BtnRightBean btnRight;
                @SerializedName("desc")
                private String desc;
                @SerializedName("title")
                private String title;

                public BtnRightBean getBtnRight() {
                    return btnRight;
                }

                public void setBtnRight(BtnRightBean btnRight) {
                    this.btnRight = btnRight;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public static class BtnRightBean {
                    @SerializedName("title")
                    private String title;
                    @SerializedName("type")
                    private String type;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }

            public static class PayTipBean {
                @SerializedName("primary")
                private PrimaryBean primary;

                public PrimaryBean getPrimary() {
                    return primary;
                }

                public void setPrimary(PrimaryBean primary) {
                    this.primary = primary;
                }

                public static class PrimaryBean {
                    @SerializedName("sub_title")
                    private String subTitle;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("type")
                    private int type;
                    @SerializedName("url")
                    private String url;

                    public String getSubTitle() {
                        return subTitle;
                    }

                    public void setSubTitle(String subTitle) {
                        this.subTitle = subTitle;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }

            public static class PayTypeBean {
                @SerializedName("allow_ticket")
                private int allowTicket;

                public int getAllowTicket() {
                    return allowTicket;
                }

                public void setAllowTicket(int allowTicket) {
                    this.allowTicket = allowTicket;
                }
            }
        }

        public static class PublishBean {
            @SerializedName("is_finish")
            private int isFinish;
            @SerializedName("is_started")
            private int isStarted;
            @SerializedName("pub_time")
            private String pubTime;
            @SerializedName("pub_time_show")
            private String pubTimeShow;
            @SerializedName("weekday")
            private int weekday;

            public int getIsFinish() {
                return isFinish;
            }

            public void setIsFinish(int isFinish) {
                this.isFinish = isFinish;
            }

            public int getIsStarted() {
                return isStarted;
            }

            public void setIsStarted(int isStarted) {
                this.isStarted = isStarted;
            }

            public String getPubTime() {
                return pubTime;
            }

            public void setPubTime(String pubTime) {
                this.pubTime = pubTime;
            }

            public String getPubTimeShow() {
                return pubTimeShow;
            }

            public void setPubTimeShow(String pubTimeShow) {
                this.pubTimeShow = pubTimeShow;
            }

            public int getWeekday() {
                return weekday;
            }

            public void setWeekday(int weekday) {
                this.weekday = weekday;
            }
        }

        public static class RatingBean {
            @SerializedName("count")
            private int count;
            @SerializedName("score")
            private double score;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }
        }

        public static class RightsBean {
            @SerializedName("allow_bp")
            private int allowBp;
            @SerializedName("allow_bp_rank")
            private int allowBpRank;
            @SerializedName("allow_download")
            private int allowDownload;
            @SerializedName("allow_review")
            private int allowReview;
            @SerializedName("area_limit")
            private int areaLimit;
            @SerializedName("ban_area_show")
            private int banAreaShow;
            @SerializedName("copyright")
            private String copyright;
            @SerializedName("is_cover_show")
            private int isCoverShow;
            @SerializedName("is_preview")
            private int isPreview;
            @SerializedName("watch_platform")
            private int watchPlatform;

            public int getAllowBp() {
                return allowBp;
            }

            public void setAllowBp(int allowBp) {
                this.allowBp = allowBp;
            }

            public int getAllowBpRank() {
                return allowBpRank;
            }

            public void setAllowBpRank(int allowBpRank) {
                this.allowBpRank = allowBpRank;
            }

            public int getAllowDownload() {
                return allowDownload;
            }

            public void setAllowDownload(int allowDownload) {
                this.allowDownload = allowDownload;
            }

            public int getAllowReview() {
                return allowReview;
            }

            public void setAllowReview(int allowReview) {
                this.allowReview = allowReview;
            }

            public int getAreaLimit() {
                return areaLimit;
            }

            public void setAreaLimit(int areaLimit) {
                this.areaLimit = areaLimit;
            }

            public int getBanAreaShow() {
                return banAreaShow;
            }

            public void setBanAreaShow(int banAreaShow) {
                this.banAreaShow = banAreaShow;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public int getIsCoverShow() {
                return isCoverShow;
            }

            public void setIsCoverShow(int isCoverShow) {
                this.isCoverShow = isCoverShow;
            }

            public int getIsPreview() {
                return isPreview;
            }

            public void setIsPreview(int isPreview) {
                this.isPreview = isPreview;
            }

            public int getWatchPlatform() {
                return watchPlatform;
            }

            public void setWatchPlatform(int watchPlatform) {
                this.watchPlatform = watchPlatform;
            }
        }

        public static class SeriesBean {
            @SerializedName("series_id")
            private int seriesId;
            @SerializedName("series_title")
            private String seriesTitle;

            public int getSeriesId() {
                return seriesId;
            }

            public void setSeriesId(int seriesId) {
                this.seriesId = seriesId;
            }

            public String getSeriesTitle() {
                return seriesTitle;
            }

            public void setSeriesTitle(String seriesTitle) {
                this.seriesTitle = seriesTitle;
            }
        }

        public static class StatBean {
            @SerializedName("coins")
            private int coins;
            @SerializedName("danmakus")
            private int danmakus;
            @SerializedName("favorites")
            private int favorites;
            @SerializedName("reply")
            private int reply;
            @SerializedName("share")
            private int share;
            @SerializedName("views")
            private int views;

            public int getCoins() {
                return coins;
            }

            public void setCoins(int coins) {
                this.coins = coins;
            }

            public int getDanmakus() {
                return danmakus;
            }

            public void setDanmakus(int danmakus) {
                this.danmakus = danmakus;
            }

            public int getFavorites() {
                return favorites;
            }

            public void setFavorites(int favorites) {
                this.favorites = favorites;
            }

            public int getReply() {
                return reply;
            }

            public void setReply(int reply) {
                this.reply = reply;
            }

            public int getShare() {
                return share;
            }

            public void setShare(int share) {
                this.share = share;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }
        }

        public static class UpInfoBean {
            @SerializedName("avatar")
            private String avatar;
            @SerializedName("follower")
            private int follower;
            @SerializedName("is_follow")
            private int isFollow;
            @SerializedName("mid")
            private int mid;
            @SerializedName("pendant")
            private PendantBean pendant;
            @SerializedName("uname")
            private String uname;
            @SerializedName("verify_type")
            private int verifyType;
            @SerializedName("vip_status")
            private int vipStatus;
            @SerializedName("vip_type")
            private int vipType;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getFollower() {
                return follower;
            }

            public void setFollower(int follower) {
                this.follower = follower;
            }

            public int getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(int isFollow) {
                this.isFollow = isFollow;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public PendantBean getPendant() {
                return pendant;
            }

            public void setPendant(PendantBean pendant) {
                this.pendant = pendant;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public int getVerifyType() {
                return verifyType;
            }

            public void setVerifyType(int verifyType) {
                this.verifyType = verifyType;
            }

            public int getVipStatus() {
                return vipStatus;
            }

            public void setVipStatus(int vipStatus) {
                this.vipStatus = vipStatus;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public static class PendantBean {
                @SerializedName("image")
                private String image;
                @SerializedName("name")
                private String name;
                @SerializedName("pid")
                private int pid;

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }
            }
        }

        public static class UserStatusBean {
            @SerializedName("follow")
            private int follow;
            @SerializedName("pay")
            private int pay;
            @SerializedName("sponsor")
            private int sponsor;
            @SerializedName("vip")
            private int vip;
            @SerializedName("vip_frozen")
            private int vipFrozen;

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public int getSponsor() {
                return sponsor;
            }

            public void setSponsor(int sponsor) {
                this.sponsor = sponsor;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public int getVipFrozen() {
                return vipFrozen;
            }

            public void setVipFrozen(int vipFrozen) {
                this.vipFrozen = vipFrozen;
            }
        }

        public static class EpisodesBean {
            @SerializedName("aid")
            private long aid;
            @SerializedName("badge")
            private String badge;
            @SerializedName("badge_type")
            private int badgeType;
            @SerializedName("cid")
            private long cid;
            @SerializedName("cover")
            private String cover;
            @SerializedName("dimension")
            private DimensionBean dimension;
            @SerializedName("from")
            private String from;
            @SerializedName("id")
            private int id;
            @SerializedName("long_title")
            private String longTitle;
            @SerializedName("release_date")
            private String releaseDate;
            @SerializedName("share_copy")
            private String shareCopy;
            @SerializedName("share_url")
            private String shareUrl;
            @SerializedName("short_link")
            private String shortLink;
            @SerializedName("status")
            private int status;
            @SerializedName("subtitle")
            private String subtitle;
            @SerializedName("title")
            private String title;
            @SerializedName("vid")
            private String vid;

            public long getAid() {
                return aid;
            }

            public void setAid(long aid) {
                this.aid = aid;
            }

            public String getBadge() {
                return badge;
            }

            public void setBadge(String badge) {
                this.badge = badge;
            }

            public int getBadgeType() {
                return badgeType;
            }

            public void setBadgeType(int badgeType) {
                this.badgeType = badgeType;
            }

            public long getCid() {
                return cid;
            }

            public void setCid(long cid) {
                this.cid = cid;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public DimensionBean getDimension() {
                return dimension;
            }

            public void setDimension(DimensionBean dimension) {
                this.dimension = dimension;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLongTitle() {
                return longTitle;
            }

            public void setLongTitle(String longTitle) {
                this.longTitle = longTitle;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public String getShareCopy() {
                return shareCopy;
            }

            public void setShareCopy(String shareCopy) {
                this.shareCopy = shareCopy;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getShortLink() {
                return shortLink;
            }

            public void setShortLink(String shortLink) {
                this.shortLink = shortLink;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public static class DimensionBean {
                @SerializedName("height")
                private int height;
                @SerializedName("rotate")
                private int rotate;
                @SerializedName("width")
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getRotate() {
                    return rotate;
                }

                public void setRotate(int rotate) {
                    this.rotate = rotate;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }
        }

        public static class SeasonsBean {
            @SerializedName("is_new")
            private int isNew;
            @SerializedName("season_id")
            private int seasonId;
            @SerializedName("season_title")
            private String seasonTitle;

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public int getSeasonId() {
                return seasonId;
            }

            public void setSeasonId(int seasonId) {
                this.seasonId = seasonId;
            }

            public String getSeasonTitle() {
                return seasonTitle;
            }

            public void setSeasonTitle(String seasonTitle) {
                this.seasonTitle = seasonTitle;
            }
        }

        public static class SectionBean {
            @SerializedName("episode_id")
            private int episodeId;
            @SerializedName("id")
            private int id;
            @SerializedName("more")
            private String more;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private int type;
            @SerializedName("episodes")
            private List<EpisodesBeanX> episodes;

            public int getEpisodeId() {
                return episodeId;
            }

            public void setEpisodeId(int episodeId) {
                this.episodeId = episodeId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMore() {
                return more;
            }

            public void setMore(String more) {
                this.more = more;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<EpisodesBeanX> getEpisodes() {
                return episodes;
            }

            public void setEpisodes(List<EpisodesBeanX> episodes) {
                this.episodes = episodes;
            }

            public static class EpisodesBeanX {
                @SerializedName("aid")
                private int aid;
                @SerializedName("badge")
                private String badge;
                @SerializedName("badge_type")
                private int badgeType;
                @SerializedName("cid")
                private int cid;
                @SerializedName("cover")
                private String cover;
                @SerializedName("dimension")
                private DimensionBeanX dimension;
                @SerializedName("from")
                private String from;
                @SerializedName("id")
                private int id;
                @SerializedName("long_title")
                private String longTitle;
                @SerializedName("release_date")
                private String releaseDate;
                @SerializedName("share_copy")
                private String shareCopy;
                @SerializedName("share_url")
                private String shareUrl;
                @SerializedName("short_link")
                private String shortLink;
                @SerializedName("stat")
                private StatBeanX stat;
                @SerializedName("status")
                private int status;
                @SerializedName("subtitle")
                private String subtitle;
                @SerializedName("title")
                private String title;
                @SerializedName("vid")
                private String vid;

                public int getAid() {
                    return aid;
                }

                public void setAid(int aid) {
                    this.aid = aid;
                }

                public String getBadge() {
                    return badge;
                }

                public void setBadge(String badge) {
                    this.badge = badge;
                }

                public int getBadgeType() {
                    return badgeType;
                }

                public void setBadgeType(int badgeType) {
                    this.badgeType = badgeType;
                }

                public int getCid() {
                    return cid;
                }

                public void setCid(int cid) {
                    this.cid = cid;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public DimensionBeanX getDimension() {
                    return dimension;
                }

                public void setDimension(DimensionBeanX dimension) {
                    this.dimension = dimension;
                }

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getLongTitle() {
                    return longTitle;
                }

                public void setLongTitle(String longTitle) {
                    this.longTitle = longTitle;
                }

                public String getReleaseDate() {
                    return releaseDate;
                }

                public void setReleaseDate(String releaseDate) {
                    this.releaseDate = releaseDate;
                }

                public String getShareCopy() {
                    return shareCopy;
                }

                public void setShareCopy(String shareCopy) {
                    this.shareCopy = shareCopy;
                }

                public String getShareUrl() {
                    return shareUrl;
                }

                public void setShareUrl(String shareUrl) {
                    this.shareUrl = shareUrl;
                }

                public String getShortLink() {
                    return shortLink;
                }

                public void setShortLink(String shortLink) {
                    this.shortLink = shortLink;
                }

                public StatBeanX getStat() {
                    return stat;
                }

                public void setStat(StatBeanX stat) {
                    this.stat = stat;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getVid() {
                    return vid;
                }

                public void setVid(String vid) {
                    this.vid = vid;
                }

                public static class DimensionBeanX {
                    @SerializedName("height")
                    private int height;
                    @SerializedName("rotate")
                    private int rotate;
                    @SerializedName("width")
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getRotate() {
                        return rotate;
                    }

                    public void setRotate(int rotate) {
                        this.rotate = rotate;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }

                public static class StatBeanX {
                    @SerializedName("coin")
                    private int coin;
                    @SerializedName("danmakus")
                    private int danmakus;
                    @SerializedName("play")
                    private int play;
                    @SerializedName("reply")
                    private int reply;

                    public int getCoin() {
                        return coin;
                    }

                    public void setCoin(int coin) {
                        this.coin = coin;
                    }

                    public int getDanmakus() {
                        return danmakus;
                    }

                    public void setDanmakus(int danmakus) {
                        this.danmakus = danmakus;
                    }

                    public int getPlay() {
                        return play;
                    }

                    public void setPlay(int play) {
                        this.play = play;
                    }

                    public int getReply() {
                        return reply;
                    }

                    public void setReply(int reply) {
                        this.reply = reply;
                    }
                }
            }
        }
    }

}

