package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BangumiDetailPageBean {

    @SerializedName("code")
    private long code;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private Result result;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        @SerializedName("activity")
        private Activity activity;
        @SerializedName("actors")
        private String actors;
        @SerializedName("alias")
        private String alias;
        @SerializedName("areas")
        private List<Areas> areas;
        @SerializedName("bkg_cover")
        private String bkgCover;
        @SerializedName("cover")
        private String cover;
        @SerializedName("episodes")
        private List<Episodes> episodes;
        @SerializedName("evaluate")
        private String evaluate;
        @SerializedName("freya")
        private Freya freya;
        @SerializedName("jp_title")
        private String jpTitle;
        @SerializedName("link")
        private String link;
        @SerializedName("media_id")
        private long mediaId;
        @SerializedName("mode")
        private long mode;
        @SerializedName("new_ep")
        private NewEp newEp;
        @SerializedName("payment")
        private Payment payment;
        @SerializedName("play_strategy")
        private PlayStrategy playStrategy;
        @SerializedName("positive")
        private Positive positive;
        @SerializedName("publish")
        private Publish publish;
        @SerializedName("record")
        private String record;
        @SerializedName("rights")
        private Rights rights;
        @SerializedName("season_id")
        private long seasonId;
        @SerializedName("season_title")
        private String seasonTitle;
        @SerializedName("seasons")
        private List<Seasons> seasons;
        @SerializedName("section")
        private List<Section> section;
        @SerializedName("series")
        private Series series;
        @SerializedName("share_copy")
        private String shareCopy;
        @SerializedName("share_sub_title")
        private String shareSubTitle;
        @SerializedName("share_url")
        private String shareUrl;
        @SerializedName("show")
        private Show show;
        @SerializedName("show_season_type")
        private long showSeasonType;
        @SerializedName("square_cover")
        private String squareCover;
        @SerializedName("staff")
        private String staff;
        @SerializedName("stat")
        private Stat stat;
        @SerializedName("status")
        private long status;
        @SerializedName("styles")
        private List<String> styles;
        @SerializedName("subtitle")
        private String subtitle;
        @SerializedName("title")
        private String title;
        @SerializedName("total")
        private long total;
        @SerializedName("type")
        private long type;
        @SerializedName("up_info")
        private UpInfo upInfo;
        @SerializedName("user_status")
        private UserStatus userStatus;

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public List<Areas> getAreas() {
            return areas;
        }

        public void setAreas(List<Areas> areas) {
            this.areas = areas;
        }

        public String getBkgCover() {
            return bkgCover;
        }

        public void setBkgCover(String bkgCover) {
            this.bkgCover = bkgCover;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public List<Episodes> getEpisodes() {
            return episodes;
        }

        public void setEpisodes(List<Episodes> episodes) {
            this.episodes = episodes;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public Freya getFreya() {
            return freya;
        }

        public void setFreya(Freya freya) {
            this.freya = freya;
        }

        public String getJpTitle() {
            return jpTitle;
        }

        public void setJpTitle(String jpTitle) {
            this.jpTitle = jpTitle;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public long getMediaId() {
            return mediaId;
        }

        public void setMediaId(long mediaId) {
            this.mediaId = mediaId;
        }

        public long getMode() {
            return mode;
        }

        public void setMode(long mode) {
            this.mode = mode;
        }

        public NewEp getNewEp() {
            return newEp;
        }

        public void setNewEp(NewEp newEp) {
            this.newEp = newEp;
        }

        public Payment getPayment() {
            return payment;
        }

        public void setPayment(Payment payment) {
            this.payment = payment;
        }

        public PlayStrategy getPlayStrategy() {
            return playStrategy;
        }

        public void setPlayStrategy(PlayStrategy playStrategy) {
            this.playStrategy = playStrategy;
        }

        public Positive getPositive() {
            return positive;
        }

        public void setPositive(Positive positive) {
            this.positive = positive;
        }

        public Publish getPublish() {
            return publish;
        }

        public void setPublish(Publish publish) {
            this.publish = publish;
        }

        public String getRecord() {
            return record;
        }

        public void setRecord(String record) {
            this.record = record;
        }

        public Rights getRights() {
            return rights;
        }

        public void setRights(Rights rights) {
            this.rights = rights;
        }

        public long getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(long seasonId) {
            this.seasonId = seasonId;
        }

        public String getSeasonTitle() {
            return seasonTitle;
        }

        public void setSeasonTitle(String seasonTitle) {
            this.seasonTitle = seasonTitle;
        }

        public List<Seasons> getSeasons() {
            return seasons;
        }

        public void setSeasons(List<Seasons> seasons) {
            this.seasons = seasons;
        }

        public List<Section> getSection() {
            return section;
        }

        public void setSection(List<Section> section) {
            this.section = section;
        }

        public Series getSeries() {
            return series;
        }

        public void setSeries(Series series) {
            this.series = series;
        }

        public String getShareCopy() {
            return shareCopy;
        }

        public void setShareCopy(String shareCopy) {
            this.shareCopy = shareCopy;
        }

        public String getShareSubTitle() {
            return shareSubTitle;
        }

        public void setShareSubTitle(String shareSubTitle) {
            this.shareSubTitle = shareSubTitle;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public Show getShow() {
            return show;
        }

        public void setShow(Show show) {
            this.show = show;
        }

        public long getShowSeasonType() {
            return showSeasonType;
        }

        public void setShowSeasonType(long showSeasonType) {
            this.showSeasonType = showSeasonType;
        }

        public String getSquareCover() {
            return squareCover;
        }

        public void setSquareCover(String squareCover) {
            this.squareCover = squareCover;
        }

        public String getStaff() {
            return staff;
        }

        public void setStaff(String staff) {
            this.staff = staff;
        }

        public Stat getStat() {
            return stat;
        }

        public void setStat(Stat stat) {
            this.stat = stat;
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public List<String> getStyles() {
            return styles;
        }

        public void setStyles(List<String> styles) {
            this.styles = styles;
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

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public UpInfo getUpInfo() {
            return upInfo;
        }

        public void setUpInfo(UpInfo upInfo) {
            this.upInfo = upInfo;
        }

        public UserStatus getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
        }

        public static class Activity {
            @SerializedName("head_bg_url")
            private String headBgUrl;
            @SerializedName("id")
            private long id;
            @SerializedName("title")
            private String title;

            public String getHeadBgUrl() {
                return headBgUrl;
            }

            public void setHeadBgUrl(String headBgUrl) {
                this.headBgUrl = headBgUrl;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class Freya {
            @SerializedName("bubble_desc")
            private String bubbleDesc;
            @SerializedName("bubble_show_cnt")
            private long bubbleShowCnt;
            @SerializedName("icon_show")
            private long iconShow;

            public String getBubbleDesc() {
                return bubbleDesc;
            }

            public void setBubbleDesc(String bubbleDesc) {
                this.bubbleDesc = bubbleDesc;
            }

            public long getBubbleShowCnt() {
                return bubbleShowCnt;
            }

            public void setBubbleShowCnt(long bubbleShowCnt) {
                this.bubbleShowCnt = bubbleShowCnt;
            }

            public long getIconShow() {
                return iconShow;
            }

            public void setIconShow(long iconShow) {
                this.iconShow = iconShow;
            }
        }

        public static class NewEp {
            @SerializedName("desc")
            private String desc;
            @SerializedName("id")
            private long id;
            @SerializedName("is_new")
            private long isNew;
            @SerializedName("title")
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getIsNew() {
                return isNew;
            }

            public void setIsNew(long isNew) {
                this.isNew = isNew;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class Payment {
            @SerializedName("discount")
            private long discount;
            @SerializedName("pay_type")
            private PayType payType;
            @SerializedName("price")
            private String price;
            @SerializedName("promotion")
            private String promotion;
            @SerializedName("tip")
            private String tip;
            @SerializedName("view_start_time")
            private long viewStartTime;
            @SerializedName("vip_discount")
            private long vipDiscount;
            @SerializedName("vip_first_promotion")
            private String vipFirstPromotion;
            @SerializedName("vip_price")
            private String vipPrice;
            @SerializedName("vip_promotion")
            private String vipPromotion;

            public long getDiscount() {
                return discount;
            }

            public void setDiscount(long discount) {
                this.discount = discount;
            }

            public PayType getPayType() {
                return payType;
            }

            public void setPayType(PayType payType) {
                this.payType = payType;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPromotion() {
                return promotion;
            }

            public void setPromotion(String promotion) {
                this.promotion = promotion;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public long getViewStartTime() {
                return viewStartTime;
            }

            public void setViewStartTime(long viewStartTime) {
                this.viewStartTime = viewStartTime;
            }

            public long getVipDiscount() {
                return vipDiscount;
            }

            public void setVipDiscount(long vipDiscount) {
                this.vipDiscount = vipDiscount;
            }

            public String getVipFirstPromotion() {
                return vipFirstPromotion;
            }

            public void setVipFirstPromotion(String vipFirstPromotion) {
                this.vipFirstPromotion = vipFirstPromotion;
            }

            public String getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(String vipPrice) {
                this.vipPrice = vipPrice;
            }

            public String getVipPromotion() {
                return vipPromotion;
            }

            public void setVipPromotion(String vipPromotion) {
                this.vipPromotion = vipPromotion;
            }

            public static class PayType {
                @SerializedName("allow_discount")
                private long allowDiscount;
                @SerializedName("allow_pack")
                private long allowPack;
                @SerializedName("allow_ticket")
                private long allowTicket;
                @SerializedName("allow_time_limit")
                private long allowTimeLimit;
                @SerializedName("allow_vip_discount")
                private long allowVipDiscount;
                @SerializedName("forbid_bb")
                private long forbidBb;

                public long getAllowDiscount() {
                    return allowDiscount;
                }

                public void setAllowDiscount(long allowDiscount) {
                    this.allowDiscount = allowDiscount;
                }

                public long getAllowPack() {
                    return allowPack;
                }

                public void setAllowPack(long allowPack) {
                    this.allowPack = allowPack;
                }

                public long getAllowTicket() {
                    return allowTicket;
                }

                public void setAllowTicket(long allowTicket) {
                    this.allowTicket = allowTicket;
                }

                public long getAllowTimeLimit() {
                    return allowTimeLimit;
                }

                public void setAllowTimeLimit(long allowTimeLimit) {
                    this.allowTimeLimit = allowTimeLimit;
                }

                public long getAllowVipDiscount() {
                    return allowVipDiscount;
                }

                public void setAllowVipDiscount(long allowVipDiscount) {
                    this.allowVipDiscount = allowVipDiscount;
                }

                public long getForbidBb() {
                    return forbidBb;
                }

                public void setForbidBb(long forbidBb) {
                    this.forbidBb = forbidBb;
                }
            }
        }

        public static class PlayStrategy {
            @SerializedName("strategies")
            private List<String> strategies;

            public List<String> getStrategies() {
                return strategies;
            }

            public void setStrategies(List<String> strategies) {
                this.strategies = strategies;
            }
        }

        public static class Positive {
            @SerializedName("id")
            private long id;
            @SerializedName("title")
            private String title;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class Publish {
            @SerializedName("is_finish")
            private long isFinish;
            @SerializedName("is_started")
            private long isStarted;
            @SerializedName("pub_time")
            private String pubTime;
            @SerializedName("pub_time_show")
            private String pubTimeShow;
            @SerializedName("unknow_pub_date")
            private long unknowPubDate;
            @SerializedName("weekday")
            private long weekday;

            public long getIsFinish() {
                return isFinish;
            }

            public void setIsFinish(long isFinish) {
                this.isFinish = isFinish;
            }

            public long getIsStarted() {
                return isStarted;
            }

            public void setIsStarted(long isStarted) {
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

            public long getUnknowPubDate() {
                return unknowPubDate;
            }

            public void setUnknowPubDate(long unknowPubDate) {
                this.unknowPubDate = unknowPubDate;
            }

            public long getWeekday() {
                return weekday;
            }

            public void setWeekday(long weekday) {
                this.weekday = weekday;
            }
        }

        public static class Rights {
            @SerializedName("allow_bp")
            private long allowBp;
            @SerializedName("allow_bp_rank")
            private long allowBpRank;
            @SerializedName("allow_download")
            private long allowDownload;
            @SerializedName("allow_review")
            private long allowReview;
            @SerializedName("area_limit")
            private long areaLimit;
            @SerializedName("ban_area_show")
            private long banAreaShow;
            @SerializedName("can_watch")
            private long canWatch;
            @SerializedName("copyright")
            private String copyright;
            @SerializedName("forbid_pre")
            private long forbidPre;
            @SerializedName("freya_white")
            private long freyaWhite;
            @SerializedName("is_cover_show")
            private long isCoverShow;
            @SerializedName("is_preview")
            private long isPreview;
            @SerializedName("only_vip_download")
            private long onlyVipDownload;
            @SerializedName("resource")
            private String resource;
            @SerializedName("watch_platform")
            private long watchPlatform;

            public long getAllowBp() {
                return allowBp;
            }

            public void setAllowBp(long allowBp) {
                this.allowBp = allowBp;
            }

            public long getAllowBpRank() {
                return allowBpRank;
            }

            public void setAllowBpRank(long allowBpRank) {
                this.allowBpRank = allowBpRank;
            }

            public long getAllowDownload() {
                return allowDownload;
            }

            public void setAllowDownload(long allowDownload) {
                this.allowDownload = allowDownload;
            }

            public long getAllowReview() {
                return allowReview;
            }

            public void setAllowReview(long allowReview) {
                this.allowReview = allowReview;
            }

            public long getAreaLimit() {
                return areaLimit;
            }

            public void setAreaLimit(long areaLimit) {
                this.areaLimit = areaLimit;
            }

            public long getBanAreaShow() {
                return banAreaShow;
            }

            public void setBanAreaShow(long banAreaShow) {
                this.banAreaShow = banAreaShow;
            }

            public long getCanWatch() {
                return canWatch;
            }

            public void setCanWatch(long canWatch) {
                this.canWatch = canWatch;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public long getForbidPre() {
                return forbidPre;
            }

            public void setForbidPre(long forbidPre) {
                this.forbidPre = forbidPre;
            }

            public long getFreyaWhite() {
                return freyaWhite;
            }

            public void setFreyaWhite(long freyaWhite) {
                this.freyaWhite = freyaWhite;
            }

            public long getIsCoverShow() {
                return isCoverShow;
            }

            public void setIsCoverShow(long isCoverShow) {
                this.isCoverShow = isCoverShow;
            }

            public long getIsPreview() {
                return isPreview;
            }

            public void setIsPreview(long isPreview) {
                this.isPreview = isPreview;
            }

            public long getOnlyVipDownload() {
                return onlyVipDownload;
            }

            public void setOnlyVipDownload(long onlyVipDownload) {
                this.onlyVipDownload = onlyVipDownload;
            }

            public String getResource() {
                return resource;
            }

            public void setResource(String resource) {
                this.resource = resource;
            }

            public long getWatchPlatform() {
                return watchPlatform;
            }

            public void setWatchPlatform(long watchPlatform) {
                this.watchPlatform = watchPlatform;
            }
        }

        public static class Series {
            @SerializedName("display_type")
            private long displayType;
            @SerializedName("series_id")
            private long seriesId;
            @SerializedName("series_title")
            private String seriesTitle;

            public long getDisplayType() {
                return displayType;
            }

            public void setDisplayType(long displayType) {
                this.displayType = displayType;
            }

            public long getSeriesId() {
                return seriesId;
            }

            public void setSeriesId(long seriesId) {
                this.seriesId = seriesId;
            }

            public String getSeriesTitle() {
                return seriesTitle;
            }

            public void setSeriesTitle(String seriesTitle) {
                this.seriesTitle = seriesTitle;
            }
        }

        public static class Show {
            @SerializedName("wide_screen")
            private long wideScreen;

            public long getWideScreen() {
                return wideScreen;
            }

            public void setWideScreen(long wideScreen) {
                this.wideScreen = wideScreen;
            }
        }

        public static class Stat {
            @SerializedName("coins")
            private long coins;
            @SerializedName("danmakus")
            private long danmakus;
            @SerializedName("favorite")
            private long favorite;
            @SerializedName("favorites")
            private long favorites;
            @SerializedName("likes")
            private long likes;
            @SerializedName("reply")
            private long reply;
            @SerializedName("share")
            private long share;
            @SerializedName("views")
            private long views;

            public long getCoins() {
                return coins;
            }

            public void setCoins(long coins) {
                this.coins = coins;
            }

            public long getDanmakus() {
                return danmakus;
            }

            public void setDanmakus(long danmakus) {
                this.danmakus = danmakus;
            }

            public long getFavorite() {
                return favorite;
            }

            public void setFavorite(long favorite) {
                this.favorite = favorite;
            }

            public long getFavorites() {
                return favorites;
            }

            public void setFavorites(long favorites) {
                this.favorites = favorites;
            }

            public long getLikes() {
                return likes;
            }

            public void setLikes(long likes) {
                this.likes = likes;
            }

            public long getReply() {
                return reply;
            }

            public void setReply(long reply) {
                this.reply = reply;
            }

            public long getShare() {
                return share;
            }

            public void setShare(long share) {
                this.share = share;
            }

            public long getViews() {
                return views;
            }

            public void setViews(long views) {
                this.views = views;
            }
        }

        public static class UpInfo {
            @SerializedName("avatar")
            private String avatar;
            @SerializedName("avatar_subscript_url")
            private String avatarSubscriptUrl;
            @SerializedName("follower")
            private long follower;
            @SerializedName("is_follow")
            private long isFollow;
            @SerializedName("mid")
            private long mid;
            @SerializedName("nickname_color")
            private String nicknameColor;
            @SerializedName("pendant")
            private Pendant pendant;
            @SerializedName("theme_type")
            private long themeType;
            @SerializedName("uname")
            private String uname;
            @SerializedName("verify_type")
            private long verifyType;
            @SerializedName("vip_label")
            private VipLabel vipLabel;
            @SerializedName("vip_status")
            private long vipStatus;
            @SerializedName("vip_type")
            private long vipType;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAvatarSubscriptUrl() {
                return avatarSubscriptUrl;
            }

            public void setAvatarSubscriptUrl(String avatarSubscriptUrl) {
                this.avatarSubscriptUrl = avatarSubscriptUrl;
            }

            public long getFollower() {
                return follower;
            }

            public void setFollower(long follower) {
                this.follower = follower;
            }

            public long getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(long isFollow) {
                this.isFollow = isFollow;
            }

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public String getNicknameColor() {
                return nicknameColor;
            }

            public void setNicknameColor(String nicknameColor) {
                this.nicknameColor = nicknameColor;
            }

            public Pendant getPendant() {
                return pendant;
            }

            public void setPendant(Pendant pendant) {
                this.pendant = pendant;
            }

            public long getThemeType() {
                return themeType;
            }

            public void setThemeType(long themeType) {
                this.themeType = themeType;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public long getVerifyType() {
                return verifyType;
            }

            public void setVerifyType(long verifyType) {
                this.verifyType = verifyType;
            }

            public VipLabel getVipLabel() {
                return vipLabel;
            }

            public void setVipLabel(VipLabel vipLabel) {
                this.vipLabel = vipLabel;
            }

            public long getVipStatus() {
                return vipStatus;
            }

            public void setVipStatus(long vipStatus) {
                this.vipStatus = vipStatus;
            }

            public long getVipType() {
                return vipType;
            }

            public void setVipType(long vipType) {
                this.vipType = vipType;
            }

            public static class Pendant {
                @SerializedName("image")
                private String image;
                @SerializedName("name")
                private String name;
                @SerializedName("pid")
                private long pid;

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

                public long getPid() {
                    return pid;
                }

                public void setPid(long pid) {
                    this.pid = pid;
                }
            }

            public static class VipLabel {
                @SerializedName("bg_color")
                private String bgColor;
                @SerializedName("bg_style")
                private long bgStyle;
                @SerializedName("border_color")
                private String borderColor;
                @SerializedName("text")
                private String text;
                @SerializedName("text_color")
                private String textColor;

                public String getBgColor() {
                    return bgColor;
                }

                public void setBgColor(String bgColor) {
                    this.bgColor = bgColor;
                }

                public long getBgStyle() {
                    return bgStyle;
                }

                public void setBgStyle(long bgStyle) {
                    this.bgStyle = bgStyle;
                }

                public String getBorderColor() {
                    return borderColor;
                }

                public void setBorderColor(String borderColor) {
                    this.borderColor = borderColor;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getTextColor() {
                    return textColor;
                }

                public void setTextColor(String textColor) {
                    this.textColor = textColor;
                }
            }
        }

        public static class UserStatus {
            @SerializedName("area_limit")
            private long areaLimit;
            @SerializedName("ban_area_show")
            private long banAreaShow;
            @SerializedName("follow")
            private long follow;
            @SerializedName("follow_status")
            private long followStatus;
            @SerializedName("login")
            private long login;
            @SerializedName("pay")
            private long pay;
            @SerializedName("pay_pack_paid")
            private long payPackPaid;
            @SerializedName("progress")
            private Progress progress;
            @SerializedName("sponsor")
            private long sponsor;
            @SerializedName("vip_info")
            private VipInfo vipInfo;

            public long getAreaLimit() {
                return areaLimit;
            }

            public void setAreaLimit(long areaLimit) {
                this.areaLimit = areaLimit;
            }

            public long getBanAreaShow() {
                return banAreaShow;
            }

            public void setBanAreaShow(long banAreaShow) {
                this.banAreaShow = banAreaShow;
            }

            public long getFollow() {
                return follow;
            }

            public void setFollow(long follow) {
                this.follow = follow;
            }

            public long getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(long followStatus) {
                this.followStatus = followStatus;
            }

            public long getLogin() {
                return login;
            }

            public void setLogin(long login) {
                this.login = login;
            }

            public long getPay() {
                return pay;
            }

            public void setPay(long pay) {
                this.pay = pay;
            }

            public long getPayPackPaid() {
                return payPackPaid;
            }

            public void setPayPackPaid(long payPackPaid) {
                this.payPackPaid = payPackPaid;
            }

            public Progress getProgress() {
                return progress;
            }

            public void setProgress(Progress progress) {
                this.progress = progress;
            }

            public long getSponsor() {
                return sponsor;
            }

            public void setSponsor(long sponsor) {
                this.sponsor = sponsor;
            }

            public VipInfo getVipInfo() {
                return vipInfo;
            }

            public void setVipInfo(VipInfo vipInfo) {
                this.vipInfo = vipInfo;
            }

            public static class Progress {
                @SerializedName("last_ep_id")
                private long lastEpId;
                @SerializedName("last_ep_index")
                private String lastEpIndex;
                @SerializedName("last_time")
                private long lastTime;

                public long getLastEpId() {
                    return lastEpId;
                }

                public void setLastEpId(long lastEpId) {
                    this.lastEpId = lastEpId;
                }

                public String getLastEpIndex() {
                    return lastEpIndex;
                }

                public void setLastEpIndex(String lastEpIndex) {
                    this.lastEpIndex = lastEpIndex;
                }

                public long getLastTime() {
                    return lastTime;
                }

                public void setLastTime(long lastTime) {
                    this.lastTime = lastTime;
                }
            }

            public static class VipInfo {
                @SerializedName("due_date")
                private long dueDate;
                @SerializedName("status")
                private long status;
                @SerializedName("type")
                private long type;

                public long getDueDate() {
                    return dueDate;
                }

                public void setDueDate(long dueDate) {
                    this.dueDate = dueDate;
                }

                public long getStatus() {
                    return status;
                }

                public void setStatus(long status) {
                    this.status = status;
                }

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }
            }
        }

        public static class Areas {
            @SerializedName("id")
            private long id;
            @SerializedName("name")
            private String name;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Episodes {
            @SerializedName("aid")
            private long aid;
            @SerializedName("badge")
            private String badge;
            @SerializedName("badge_info")
            private BadgeInfo badgeInfo;
            @SerializedName("badge_type")
            private long badgeType;
            @SerializedName("bvid")
            private String bvid;
            @SerializedName("cid")
            private long cid;
            @SerializedName("cover")
            private String cover;
            @SerializedName("dimension")
            private Dimension dimension;
            @SerializedName("duration")
            private long duration;
            @SerializedName("enable_vt")
            private boolean enableVt;
            @SerializedName("from")
            private String from;
            @SerializedName("id")
            private long id;
            @SerializedName("is_view_hide")
            private boolean isViewHide;
            @SerializedName("link")
            private String link;
            @SerializedName("long_title")
            private String longTitle;
            @SerializedName("pub_time")
            private long pubTime;
            @SerializedName("pv")
            private long pv;
            @SerializedName("release_date")
            private String releaseDate;
            @SerializedName("rights")
            private Rights rights;
            @SerializedName("share_copy")
            private String shareCopy;
            @SerializedName("share_url")
            private String shareUrl;
            @SerializedName("short_link")
            private String shortLink;
            @SerializedName("skip")
            private Skip skip;
            @SerializedName("status")
            private long status;
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

            public BadgeInfo getBadgeInfo() {
                return badgeInfo;
            }

            public void setBadgeInfo(BadgeInfo badgeInfo) {
                this.badgeInfo = badgeInfo;
            }

            public long getBadgeType() {
                return badgeType;
            }

            public void setBadgeType(long badgeType) {
                this.badgeType = badgeType;
            }

            public String getBvid() {
                return bvid;
            }

            public void setBvid(String bvid) {
                this.bvid = bvid;
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

            public Dimension getDimension() {
                return dimension;
            }

            public void setDimension(Dimension dimension) {
                this.dimension = dimension;
            }

            public long getDuration() {
                return duration;
            }

            public void setDuration(long duration) {
                this.duration = duration;
            }

            public boolean isEnableVt() {
                return enableVt;
            }

            public void setEnableVt(boolean enableVt) {
                this.enableVt = enableVt;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isIsViewHide() {
                return isViewHide;
            }

            public void setIsViewHide(boolean isViewHide) {
                this.isViewHide = isViewHide;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getLongTitle() {
                return longTitle;
            }

            public void setLongTitle(String longTitle) {
                this.longTitle = longTitle;
            }

            public long getPubTime() {
                return pubTime;
            }

            public void setPubTime(long pubTime) {
                this.pubTime = pubTime;
            }

            public long getPv() {
                return pv;
            }

            public void setPv(long pv) {
                this.pv = pv;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public Rights getRights() {
                return rights;
            }

            public void setRights(Rights rights) {
                this.rights = rights;
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

            public Skip getSkip() {
                return skip;
            }

            public void setSkip(Skip skip) {
                this.skip = skip;
            }

            public long getStatus() {
                return status;
            }

            public void setStatus(long status) {
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

            public static class BadgeInfo {
                @SerializedName("bg_color")
                private String bgColor;
                @SerializedName("bg_color_night")
                private String bgColorNight;
                @SerializedName("text")
                private String text;

                public String getBgColor() {
                    return bgColor;
                }

                public void setBgColor(String bgColor) {
                    this.bgColor = bgColor;
                }

                public String getBgColorNight() {
                    return bgColorNight;
                }

                public void setBgColorNight(String bgColorNight) {
                    this.bgColorNight = bgColorNight;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            public static class Dimension {
                @SerializedName("height")
                private long height;
                @SerializedName("rotate")
                private long rotate;
                @SerializedName("width")
                private long width;

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

                public long getWidth() {
                    return width;
                }

                public void setWidth(long width) {
                    this.width = width;
                }
            }

            public static class Rights {
                @SerializedName("allow_demand")
                private long allowDemand;
                @SerializedName("allow_dm")
                private long allowDm;
                @SerializedName("allow_download")
                private long allowDownload;
                @SerializedName("area_limit")
                private long areaLimit;

                public long getAllowDemand() {
                    return allowDemand;
                }

                public void setAllowDemand(long allowDemand) {
                    this.allowDemand = allowDemand;
                }

                public long getAllowDm() {
                    return allowDm;
                }

                public void setAllowDm(long allowDm) {
                    this.allowDm = allowDm;
                }

                public long getAllowDownload() {
                    return allowDownload;
                }

                public void setAllowDownload(long allowDownload) {
                    this.allowDownload = allowDownload;
                }

                public long getAreaLimit() {
                    return areaLimit;
                }

                public void setAreaLimit(long areaLimit) {
                    this.areaLimit = areaLimit;
                }
            }

            public static class Skip {
                @SerializedName("ed")
                private Ed ed;
                @SerializedName("op")
                private Op op;

                public Ed getEd() {
                    return ed;
                }

                public void setEd(Ed ed) {
                    this.ed = ed;
                }

                public Op getOp() {
                    return op;
                }

                public void setOp(Op op) {
                    this.op = op;
                }

                public static class Ed {
                    @SerializedName("end")
                    private long end;
                    @SerializedName("start")
                    private long start;

                    public long getEnd() {
                        return end;
                    }

                    public void setEnd(long end) {
                        this.end = end;
                    }

                    public long getStart() {
                        return start;
                    }

                    public void setStart(long start) {
                        this.start = start;
                    }
                }

                public static class Op {
                    @SerializedName("end")
                    private long end;
                    @SerializedName("start")
                    private long start;

                    public long getEnd() {
                        return end;
                    }

                    public void setEnd(long end) {
                        this.end = end;
                    }

                    public long getStart() {
                        return start;
                    }

                    public void setStart(long start) {
                        this.start = start;
                    }
                }
            }
        }

        public static class Seasons {
            @SerializedName("badge")
            private String badge;
            @SerializedName("badge_info")
            private BadgeInfo badgeInfo;
            @SerializedName("badge_type")
            private long badgeType;
            @SerializedName("cover")
            private String cover;
            @SerializedName("horizontal_cover_1610")
            private String horizontalCover1610;
            @SerializedName("horizontal_cover_169")
            private String horizontalCover169;
            @SerializedName("media_id")
            private long mediaId;
            @SerializedName("new_ep")
            private NewEp newEp;
            @SerializedName("season_id")
            private long seasonId;
            @SerializedName("season_title")
            private String seasonTitle;
            @SerializedName("season_type")
            private long seasonType;
            @SerializedName("stat")
            private Stat stat;

            public String getBadge() {
                return badge;
            }

            public void setBadge(String badge) {
                this.badge = badge;
            }

            public BadgeInfo getBadgeInfo() {
                return badgeInfo;
            }

            public void setBadgeInfo(BadgeInfo badgeInfo) {
                this.badgeInfo = badgeInfo;
            }

            public long getBadgeType() {
                return badgeType;
            }

            public void setBadgeType(long badgeType) {
                this.badgeType = badgeType;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getHorizontalCover1610() {
                return horizontalCover1610;
            }

            public void setHorizontalCover1610(String horizontalCover1610) {
                this.horizontalCover1610 = horizontalCover1610;
            }

            public String getHorizontalCover169() {
                return horizontalCover169;
            }

            public void setHorizontalCover169(String horizontalCover169) {
                this.horizontalCover169 = horizontalCover169;
            }

            public long getMediaId() {
                return mediaId;
            }

            public void setMediaId(long mediaId) {
                this.mediaId = mediaId;
            }

            public NewEp getNewEp() {
                return newEp;
            }

            public void setNewEp(NewEp newEp) {
                this.newEp = newEp;
            }

            public long getSeasonId() {
                return seasonId;
            }

            public void setSeasonId(long seasonId) {
                this.seasonId = seasonId;
            }

            public String getSeasonTitle() {
                return seasonTitle;
            }

            public void setSeasonTitle(String seasonTitle) {
                this.seasonTitle = seasonTitle;
            }

            public long getSeasonType() {
                return seasonType;
            }

            public void setSeasonType(long seasonType) {
                this.seasonType = seasonType;
            }

            public Stat getStat() {
                return stat;
            }

            public void setStat(Stat stat) {
                this.stat = stat;
            }

            public static class BadgeInfo {
                @SerializedName("bg_color")
                private String bgColor;
                @SerializedName("bg_color_night")
                private String bgColorNight;
                @SerializedName("text")
                private String text;

                public String getBgColor() {
                    return bgColor;
                }

                public void setBgColor(String bgColor) {
                    this.bgColor = bgColor;
                }

                public String getBgColorNight() {
                    return bgColorNight;
                }

                public void setBgColorNight(String bgColorNight) {
                    this.bgColorNight = bgColorNight;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            public static class NewEp {
                @SerializedName("cover")
                private String cover;
                @SerializedName("id")
                private long id;
                @SerializedName("index_show")
                private String indexShow;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getIndexShow() {
                    return indexShow;
                }

                public void setIndexShow(String indexShow) {
                    this.indexShow = indexShow;
                }
            }

            public static class Stat {
                @SerializedName("favorites")
                private long favorites;
                @SerializedName("series_follow")
                private long seriesFollow;
                @SerializedName("views")
                private long views;

                public long getFavorites() {
                    return favorites;
                }

                public void setFavorites(long favorites) {
                    this.favorites = favorites;
                }

                public long getSeriesFollow() {
                    return seriesFollow;
                }

                public void setSeriesFollow(long seriesFollow) {
                    this.seriesFollow = seriesFollow;
                }

                public long getViews() {
                    return views;
                }

                public void setViews(long views) {
                    this.views = views;
                }
            }
        }

        public static class Section {
            @SerializedName("attr")
            private long attr;
            @SerializedName("episode_id")
            private long episodeId;
            @SerializedName("episode_ids")
            private List<?> episodeIds;
            @SerializedName("episodes")
            private List<Episodes> episodes;
            @SerializedName("id")
            private long id;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private long type;

            public long getAttr() {
                return attr;
            }

            public void setAttr(long attr) {
                this.attr = attr;
            }

            public long getEpisodeId() {
                return episodeId;
            }

            public void setEpisodeId(long episodeId) {
                this.episodeId = episodeId;
            }

            public List<?> getEpisodeIds() {
                return episodeIds;
            }

            public void setEpisodeIds(List<?> episodeIds) {
                this.episodeIds = episodeIds;
            }

            public List<Episodes> getEpisodes() {
                return episodes;
            }

            public void setEpisodes(List<Episodes> episodes) {
                this.episodes = episodes;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public static class Episodes {
                @SerializedName("aid")
                private long aid;
                @SerializedName("badge")
                private String badge;
                @SerializedName("badge_info")
                private BadgeInfo badgeInfo;
                @SerializedName("badge_type")
                private long badgeType;
                @SerializedName("bvid")
                private String bvid;
                @SerializedName("cid")
                private long cid;
                @SerializedName("cover")
                private String cover;
                @SerializedName("dimension")
                private Dimension dimension;
                @SerializedName("duration")
                private long duration;
                @SerializedName("enable_vt")
                private boolean enableVt;
                @SerializedName("from")
                private String from;
                @SerializedName("icon_font")
                private IconFont iconFont;
                @SerializedName("id")
                private long id;
                @SerializedName("is_view_hide")
                private boolean isViewHide;
                @SerializedName("link")
                private String link;
                @SerializedName("long_title")
                private String longTitle;
                @SerializedName("pub_time")
                private long pubTime;
                @SerializedName("pv")
                private long pv;
                @SerializedName("release_date")
                private String releaseDate;
                @SerializedName("rights")
                private Rights rights;
                @SerializedName("share_copy")
                private String shareCopy;
                @SerializedName("share_url")
                private String shareUrl;
                @SerializedName("short_link")
                private String shortLink;
                @SerializedName("skip")
                private Skip skip;
                @SerializedName("stat")
                private Stat stat;
                @SerializedName("status")
                private long status;
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

                public BadgeInfo getBadgeInfo() {
                    return badgeInfo;
                }

                public void setBadgeInfo(BadgeInfo badgeInfo) {
                    this.badgeInfo = badgeInfo;
                }

                public long getBadgeType() {
                    return badgeType;
                }

                public void setBadgeType(long badgeType) {
                    this.badgeType = badgeType;
                }

                public String getBvid() {
                    return bvid;
                }

                public void setBvid(String bvid) {
                    this.bvid = bvid;
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

                public Dimension getDimension() {
                    return dimension;
                }

                public void setDimension(Dimension dimension) {
                    this.dimension = dimension;
                }

                public long getDuration() {
                    return duration;
                }

                public void setDuration(long duration) {
                    this.duration = duration;
                }

                public boolean isEnableVt() {
                    return enableVt;
                }

                public void setEnableVt(boolean enableVt) {
                    this.enableVt = enableVt;
                }

                public String getFrom() {
                    return from;
                }

                public void setFrom(String from) {
                    this.from = from;
                }

                public IconFont getIconFont() {
                    return iconFont;
                }

                public void setIconFont(IconFont iconFont) {
                    this.iconFont = iconFont;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public boolean isIsViewHide() {
                    return isViewHide;
                }

                public void setIsViewHide(boolean isViewHide) {
                    this.isViewHide = isViewHide;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getLongTitle() {
                    return longTitle;
                }

                public void setLongTitle(String longTitle) {
                    this.longTitle = longTitle;
                }

                public long getPubTime() {
                    return pubTime;
                }

                public void setPubTime(long pubTime) {
                    this.pubTime = pubTime;
                }

                public long getPv() {
                    return pv;
                }

                public void setPv(long pv) {
                    this.pv = pv;
                }

                public String getReleaseDate() {
                    return releaseDate;
                }

                public void setReleaseDate(String releaseDate) {
                    this.releaseDate = releaseDate;
                }

                public Rights getRights() {
                    return rights;
                }

                public void setRights(Rights rights) {
                    this.rights = rights;
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

                public Skip getSkip() {
                    return skip;
                }

                public void setSkip(Skip skip) {
                    this.skip = skip;
                }

                public Stat getStat() {
                    return stat;
                }

                public void setStat(Stat stat) {
                    this.stat = stat;
                }

                public long getStatus() {
                    return status;
                }

                public void setStatus(long status) {
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

                public static class BadgeInfo {
                    @SerializedName("bg_color")
                    private String bgColor;
                    @SerializedName("bg_color_night")
                    private String bgColorNight;
                    @SerializedName("text")
                    private String text;

                    public String getBgColor() {
                        return bgColor;
                    }

                    public void setBgColor(String bgColor) {
                        this.bgColor = bgColor;
                    }

                    public String getBgColorNight() {
                        return bgColorNight;
                    }

                    public void setBgColorNight(String bgColorNight) {
                        this.bgColorNight = bgColorNight;
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }
                }

                public static class Dimension {
                    @SerializedName("height")
                    private long height;
                    @SerializedName("rotate")
                    private long rotate;
                    @SerializedName("width")
                    private long width;

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

                    public long getWidth() {
                        return width;
                    }

                    public void setWidth(long width) {
                        this.width = width;
                    }
                }

                public static class IconFont {
                    @SerializedName("name")
                    private String name;
                    @SerializedName("text")
                    private String text;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }
                }

                public static class Rights {
                    @SerializedName("allow_demand")
                    private long allowDemand;
                    @SerializedName("allow_dm")
                    private long allowDm;
                    @SerializedName("allow_download")
                    private long allowDownload;
                    @SerializedName("area_limit")
                    private long areaLimit;

                    public long getAllowDemand() {
                        return allowDemand;
                    }

                    public void setAllowDemand(long allowDemand) {
                        this.allowDemand = allowDemand;
                    }

                    public long getAllowDm() {
                        return allowDm;
                    }

                    public void setAllowDm(long allowDm) {
                        this.allowDm = allowDm;
                    }

                    public long getAllowDownload() {
                        return allowDownload;
                    }

                    public void setAllowDownload(long allowDownload) {
                        this.allowDownload = allowDownload;
                    }

                    public long getAreaLimit() {
                        return areaLimit;
                    }

                    public void setAreaLimit(long areaLimit) {
                        this.areaLimit = areaLimit;
                    }
                }

                public static class Skip {
                    @SerializedName("ed")
                    private Ed ed;
                    @SerializedName("op")
                    private Op op;

                    public Ed getEd() {
                        return ed;
                    }

                    public void setEd(Ed ed) {
                        this.ed = ed;
                    }

                    public Op getOp() {
                        return op;
                    }

                    public void setOp(Op op) {
                        this.op = op;
                    }

                    public static class Ed {
                        @SerializedName("end")
                        private long end;
                        @SerializedName("start")
                        private long start;

                        public long getEnd() {
                            return end;
                        }

                        public void setEnd(long end) {
                            this.end = end;
                        }

                        public long getStart() {
                            return start;
                        }

                        public void setStart(long start) {
                            this.start = start;
                        }
                    }

                    public static class Op {
                        @SerializedName("end")
                        private long end;
                        @SerializedName("start")
                        private long start;

                        public long getEnd() {
                            return end;
                        }

                        public void setEnd(long end) {
                            this.end = end;
                        }

                        public long getStart() {
                            return start;
                        }

                        public void setStart(long start) {
                            this.start = start;
                        }
                    }
                }

                public static class Stat {
                    @SerializedName("coin")
                    private long coin;
                    @SerializedName("danmakus")
                    private long danmakus;
                    @SerializedName("likes")
                    private long likes;
                    @SerializedName("play")
                    private long play;
                    @SerializedName("reply")
                    private long reply;
                    @SerializedName("vt")
                    private long vt;

                    public long getCoin() {
                        return coin;
                    }

                    public void setCoin(long coin) {
                        this.coin = coin;
                    }

                    public long getDanmakus() {
                        return danmakus;
                    }

                    public void setDanmakus(long danmakus) {
                        this.danmakus = danmakus;
                    }

                    public long getLikes() {
                        return likes;
                    }

                    public void setLikes(long likes) {
                        this.likes = likes;
                    }

                    public long getPlay() {
                        return play;
                    }

                    public void setPlay(long play) {
                        this.play = play;
                    }

                    public long getReply() {
                        return reply;
                    }

                    public void setReply(long reply) {
                        this.reply = reply;
                    }

                    public long getVt() {
                        return vt;
                    }

                    public void setVt(long vt) {
                        this.vt = vt;
                    }
                }
            }
        }
    }
}