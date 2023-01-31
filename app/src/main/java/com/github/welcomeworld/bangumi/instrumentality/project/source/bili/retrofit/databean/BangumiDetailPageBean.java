package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class BangumiDetailPageBean {

    @SerializedName("code")
    private Long code;
    @SerializedName("data")
    private Result result;
    @SerializedName("message")
    private String message;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Result {
        @SerializedName("activity_tab")
        private ActivityTab activityTab;
        @SerializedName("actor")
        private Actor actor;
        @SerializedName("alias")
        private String alias;
        @SerializedName("all_buttons")
        private AllButtons allButtons;
        @SerializedName("all_up_infos")
        private AllUpInfos allUpInfos;
        @SerializedName("areas")
        private List<Areas> areas;
        @SerializedName("badge")
        private String badge;
        @SerializedName("badge_info")
        private BadgeInfo badgeInfo;
        @SerializedName("celebrity")
        private List<Celebrity> celebrity;
        @SerializedName("cover")
        private String cover;
        @SerializedName("detail")
        private String detail;
        @SerializedName("dynamic_subtitle")
        private String dynamicSubtitle;
        @SerializedName("evaluate")
        private String evaluate;
        @SerializedName("link")
        private String link;
        @SerializedName("media_id")
        private Long mediaId;
        @SerializedName("mode")
        private Long mode;
        @SerializedName("modules")
        private List<Modules> modules;
        @SerializedName("new_ep")
        private NewEp newEp;
        @SerializedName("origin_name")
        private String originName;
        @SerializedName("paster")
        private Paster paster;
        @SerializedName("payment")
        private Payment payment;
        @SerializedName("play_strategy")
        private PlayStrategy playStrategy;
        @SerializedName("player_icon")
        private PlayerIcon playerIcon;
        @SerializedName("premieres")
        private List<?> premieres;
        @SerializedName("publish")
        private Publish publish;
        @SerializedName("rating")
        private Rating rating;
        @SerializedName("record")
        private String record;
        @SerializedName("refine_cover")
        private String refineCover;
        @SerializedName("reserve")
        private Reserve reserve;
        @SerializedName("rights")
        private Rights rights;
        @SerializedName("season_id")
        private Long seasonId;
        @SerializedName("season_title")
        private String seasonTitle;
        @SerializedName("series")
        private Series series;
        @SerializedName("share_copy")
        private String shareCopy;
        @SerializedName("share_url")
        private String shareUrl;
        @SerializedName("short_link")
        private String shortLink;
        @SerializedName("square_cover")
        private String squareCover;
        @SerializedName("staff")
        private Staff staff;
        @SerializedName("stat")
        private Stat stat;
        @SerializedName("status")
        private Long status;
        @SerializedName("styles")
        private List<Styles> styles;
        @SerializedName("subtitle")
        private String subtitle;
        @SerializedName("tags")
        private List<?> tags;
        @SerializedName("test_switch")
        private TestSwitch testSwitch;
        @SerializedName("title")
        private String title;
        @SerializedName("total")
        private Long total;
        @SerializedName("type")
        private Long type;
        @SerializedName("type_desc")
        private String typeDesc;
        @SerializedName("type_name")
        private String typeName;
        @SerializedName("up_info")
        private UpInfo upInfo;
        @SerializedName("user_status")
        private UserStatus userStatus;

        public ActivityTab getActivityTab() {
            return activityTab;
        }

        public void setActivityTab(ActivityTab activityTab) {
            this.activityTab = activityTab;
        }

        public Actor getActor() {
            return actor;
        }

        public void setActor(Actor actor) {
            this.actor = actor;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public AllButtons getAllButtons() {
            return allButtons;
        }

        public void setAllButtons(AllButtons allButtons) {
            this.allButtons = allButtons;
        }

        public AllUpInfos getAllUpInfos() {
            return allUpInfos;
        }

        public void setAllUpInfos(AllUpInfos allUpInfos) {
            this.allUpInfos = allUpInfos;
        }

        public List<Areas> getAreas() {
            return areas;
        }

        public void setAreas(List<Areas> areas) {
            this.areas = areas;
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

        public List<Celebrity> getCelebrity() {
            return celebrity;
        }

        public void setCelebrity(List<Celebrity> celebrity) {
            this.celebrity = celebrity;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDynamicSubtitle() {
            return dynamicSubtitle;
        }

        public void setDynamicSubtitle(String dynamicSubtitle) {
            this.dynamicSubtitle = dynamicSubtitle;
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

        public Long getMediaId() {
            return mediaId;
        }

        public void setMediaId(Long mediaId) {
            this.mediaId = mediaId;
        }

        public Long getMode() {
            return mode;
        }

        public void setMode(Long mode) {
            this.mode = mode;
        }

        public List<Modules> getModules() {
            return modules;
        }

        public void setModules(List<Modules> modules) {
            this.modules = modules;
        }

        public NewEp getNewEp() {
            return newEp;
        }

        public void setNewEp(NewEp newEp) {
            this.newEp = newEp;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Paster getPaster() {
            return paster;
        }

        public void setPaster(Paster paster) {
            this.paster = paster;
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

        public PlayerIcon getPlayerIcon() {
            return playerIcon;
        }

        public void setPlayerIcon(PlayerIcon playerIcon) {
            this.playerIcon = playerIcon;
        }

        public List<?> getPremieres() {
            return premieres;
        }

        public void setPremieres(List<?> premieres) {
            this.premieres = premieres;
        }

        public Publish getPublish() {
            return publish;
        }

        public void setPublish(Publish publish) {
            this.publish = publish;
        }

        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public String getRecord() {
            return record;
        }

        public void setRecord(String record) {
            this.record = record;
        }

        public String getRefineCover() {
            return refineCover;
        }

        public void setRefineCover(String refineCover) {
            this.refineCover = refineCover;
        }

        public Reserve getReserve() {
            return reserve;
        }

        public void setReserve(Reserve reserve) {
            this.reserve = reserve;
        }

        public Rights getRights() {
            return rights;
        }

        public void setRights(Rights rights) {
            this.rights = rights;
        }

        public Long getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(Long seasonId) {
            this.seasonId = seasonId;
        }

        public String getSeasonTitle() {
            return seasonTitle;
        }

        public void setSeasonTitle(String seasonTitle) {
            this.seasonTitle = seasonTitle;
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

        public Staff getStaff() {
            return staff;
        }

        public void setStaff(Staff staff) {
            this.staff = staff;
        }

        public Stat getStat() {
            return stat;
        }

        public void setStat(Stat stat) {
            this.stat = stat;
        }

        public Long getStatus() {
            return status;
        }

        public void setStatus(Long status) {
            this.status = status;
        }

        public List<Styles> getStyles() {
            return styles;
        }

        public void setStyles(List<Styles> styles) {
            this.styles = styles;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public TestSwitch getTestSwitch() {
            return testSwitch;
        }

        public void setTestSwitch(TestSwitch testSwitch) {
            this.testSwitch = testSwitch;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public Long getType() {
            return type;
        }

        public void setType(Long type) {
            this.type = type;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
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

        public static class ActivityTab {
            @SerializedName("h5_link")
            private String h5Link;
            @SerializedName("id")
            private Long id;
            @SerializedName("link")
            private String link;
            @SerializedName("picurl")
            private String picurl;
            @SerializedName("picurl_selected")
            private String picurlSelected;
            @SerializedName("show_name")
            private String showName;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private Long type;

            public String getH5Link() {
                return h5Link;
            }

            public void setH5Link(String h5Link) {
                this.h5Link = h5Link;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public String getPicurlSelected() {
                return picurlSelected;
            }

            public void setPicurlSelected(String picurlSelected) {
                this.picurlSelected = picurlSelected;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Long getType() {
                return type;
            }

            public void setType(Long type) {
                this.type = type;
            }
        }

        public static class Actor {
            @SerializedName("info")
            private String info;
            @SerializedName("title")
            private String title;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class AllButtons {
            @SerializedName("watch_formal")
            private String watchFormal;

            public String getWatchFormal() {
                return watchFormal;
            }

            public void setWatchFormal(String watchFormal) {
                this.watchFormal = watchFormal;
            }
        }

        public static class AllUpInfos {
            @SerializedName("181960")
            private _$181960 $181960;
            @SerializedName("5632230")
            private _$5632230 $5632230;

            public _$181960 get$181960() {
                return $181960;
            }

            public void set$181960(_$181960 $181960) {
                this.$181960 = $181960;
            }

            public _$5632230 get$5632230() {
                return $5632230;
            }

            public void set$5632230(_$5632230 $5632230) {
                this.$5632230 = $5632230;
            }

            public static class _$181960 {
                @SerializedName("avatar")
                private String avatar;
                @SerializedName("follower")
                private Long follower;
                @SerializedName("is_follow")
                private Long isFollow;
                @SerializedName("mid")
                private Long mid;
                @SerializedName("uname")
                private String uname;
                @SerializedName("vip_label")
                private VipLabel vipLabel;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public Long getFollower() {
                    return follower;
                }

                public void setFollower(Long follower) {
                    this.follower = follower;
                }

                public Long getIsFollow() {
                    return isFollow;
                }

                public void setIsFollow(Long isFollow) {
                    this.isFollow = isFollow;
                }

                public Long getMid() {
                    return mid;
                }

                public void setMid(Long mid) {
                    this.mid = mid;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public VipLabel getVipLabel() {
                    return vipLabel;
                }

                public void setVipLabel(VipLabel vipLabel) {
                    this.vipLabel = vipLabel;
                }

                public static class VipLabel {
                    @SerializedName("label_theme")
                    private String labelTheme;
                    @SerializedName("path")
                    private String path;
                    @SerializedName("text")
                    private String text;

                    public String getLabelTheme() {
                        return labelTheme;
                    }

                    public void setLabelTheme(String labelTheme) {
                        this.labelTheme = labelTheme;
                    }

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }
                }
            }

            public static class _$5632230 {
                @SerializedName("avatar")
                private String avatar;
                @SerializedName("follower")
                private Long follower;
                @SerializedName("is_follow")
                private Long isFollow;
                @SerializedName("mid")
                private Long mid;
                @SerializedName("uname")
                private String uname;
                @SerializedName("vip_label")
                private VipLabel vipLabel;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public Long getFollower() {
                    return follower;
                }

                public void setFollower(Long follower) {
                    this.follower = follower;
                }

                public Long getIsFollow() {
                    return isFollow;
                }

                public void setIsFollow(Long isFollow) {
                    this.isFollow = isFollow;
                }

                public Long getMid() {
                    return mid;
                }

                public void setMid(Long mid) {
                    this.mid = mid;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public VipLabel getVipLabel() {
                    return vipLabel;
                }

                public void setVipLabel(VipLabel vipLabel) {
                    this.vipLabel = vipLabel;
                }

                public static class VipLabel {
                    @SerializedName("label_theme")
                    private String labelTheme;
                    @SerializedName("path")
                    private String path;
                    @SerializedName("text")
                    private String text;

                    public String getLabelTheme() {
                        return labelTheme;
                    }

                    public void setLabelTheme(String labelTheme) {
                        this.labelTheme = labelTheme;
                    }

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }
                }
            }
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
            @SerializedName("desc")
            private String desc;
            @SerializedName("id")
            private Long id;
            @SerializedName("is_new")
            private Long isNew;
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

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getIsNew() {
                return isNew;
            }

            public void setIsNew(Long isNew) {
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

        public static class Paster {
            @SerializedName("aid")
            private Long aid;
            @SerializedName("allow_jump")
            private Long allowJump;
            @SerializedName("cid")
            private Long cid;
            @SerializedName("duration")
            private Long duration;
            @SerializedName("type")
            private Long type;
            @SerializedName("url")
            private String url;

            public Long getAid() {
                return aid;
            }

            public void setAid(Long aid) {
                this.aid = aid;
            }

            public Long getAllowJump() {
                return allowJump;
            }

            public void setAllowJump(Long allowJump) {
                this.allowJump = allowJump;
            }

            public Long getCid() {
                return cid;
            }

            public void setCid(Long cid) {
                this.cid = cid;
            }

            public Long getDuration() {
                return duration;
            }

            public void setDuration(Long duration) {
                this.duration = duration;
            }

            public Long getType() {
                return type;
            }

            public void setType(Long type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class Payment {
            @SerializedName("dialog")
            private Dialog dialog;
            @SerializedName("pay_tip")
            private PayTip payTip;
            @SerializedName("pay_type")
            private PayType payType;
            @SerializedName("price")
            private String price;
            @SerializedName("report_type")
            private Long reportType;
            @SerializedName("tv_price")
            private String tvPrice;
            @SerializedName("vip_pay_link")
            private String vipPayLink;
            @SerializedName("vip_promotion")
            private String vipPromotion;
            @SerializedName("vip_report")
            private VipReport vipReport;

            public Dialog getDialog() {
                return dialog;
            }

            public void setDialog(Dialog dialog) {
                this.dialog = dialog;
            }

            public PayTip getPayTip() {
                return payTip;
            }

            public void setPayTip(PayTip payTip) {
                this.payTip = payTip;
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

            public Long getReportType() {
                return reportType;
            }

            public void setReportType(Long reportType) {
                this.reportType = reportType;
            }

            public String getTvPrice() {
                return tvPrice;
            }

            public void setTvPrice(String tvPrice) {
                this.tvPrice = tvPrice;
            }

            public String getVipPayLink() {
                return vipPayLink;
            }

            public void setVipPayLink(String vipPayLink) {
                this.vipPayLink = vipPayLink;
            }

            public String getVipPromotion() {
                return vipPromotion;
            }

            public void setVipPromotion(String vipPromotion) {
                this.vipPromotion = vipPromotion;
            }

            public VipReport getVipReport() {
                return vipReport;
            }

            public void setVipReport(VipReport vipReport) {
                this.vipReport = vipReport;
            }

            public static class Dialog {
            }

            public static class PayTip {
                @SerializedName("primary")
                private Primary primary;

                public Primary getPrimary() {
                    return primary;
                }

                public void setPrimary(Primary primary) {
                    this.primary = primary;
                }

                public static class Primary {
                    @SerializedName("angle_style")
                    private Long angleStyle;
                    @SerializedName("bg_day_color")
                    private String bgDayColor;
                    @SerializedName("bg_line_color")
                    private String bgLineColor;
                    @SerializedName("bg_night_color")
                    private String bgNightColor;
                    @SerializedName("bg_night_line_color")
                    private String bgNightLineColor;
                    @SerializedName("icon")
                    private String icon;
                    @SerializedName("report")
                    private Report report;
                    @SerializedName("showTypeEnum")
                    private String showTypeEnum;
                    @SerializedName("show_type")
                    private Long showType;
                    @SerializedName("text_color")
                    private String textColor;
                    @SerializedName("text_night_color")
                    private String textNightColor;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("type")
                    private Long type;
                    @SerializedName("url")
                    private String url;
                    @SerializedName("url_open_type")
                    private Long urlOpenType;
                    @SerializedName("view_start_time")
                    private Long viewStartTime;

                    public Long getAngleStyle() {
                        return angleStyle;
                    }

                    public void setAngleStyle(Long angleStyle) {
                        this.angleStyle = angleStyle;
                    }

                    public String getBgDayColor() {
                        return bgDayColor;
                    }

                    public void setBgDayColor(String bgDayColor) {
                        this.bgDayColor = bgDayColor;
                    }

                    public String getBgLineColor() {
                        return bgLineColor;
                    }

                    public void setBgLineColor(String bgLineColor) {
                        this.bgLineColor = bgLineColor;
                    }

                    public String getBgNightColor() {
                        return bgNightColor;
                    }

                    public void setBgNightColor(String bgNightColor) {
                        this.bgNightColor = bgNightColor;
                    }

                    public String getBgNightLineColor() {
                        return bgNightLineColor;
                    }

                    public void setBgNightLineColor(String bgNightLineColor) {
                        this.bgNightLineColor = bgNightLineColor;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }

                    public Report getReport() {
                        return report;
                    }

                    public void setReport(Report report) {
                        this.report = report;
                    }

                    public String getShowTypeEnum() {
                        return showTypeEnum;
                    }

                    public void setShowTypeEnum(String showTypeEnum) {
                        this.showTypeEnum = showTypeEnum;
                    }

                    public Long getShowType() {
                        return showType;
                    }

                    public void setShowType(Long showType) {
                        this.showType = showType;
                    }

                    public String getTextColor() {
                        return textColor;
                    }

                    public void setTextColor(String textColor) {
                        this.textColor = textColor;
                    }

                    public String getTextNightColor() {
                        return textNightColor;
                    }

                    public void setTextNightColor(String textNightColor) {
                        this.textNightColor = textNightColor;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public Long getType() {
                        return type;
                    }

                    public void setType(Long type) {
                        this.type = type;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Long getUrlOpenType() {
                        return urlOpenType;
                    }

                    public void setUrlOpenType(Long urlOpenType) {
                        this.urlOpenType = urlOpenType;
                    }

                    public Long getViewStartTime() {
                        return viewStartTime;
                    }

                    public void setViewStartTime(Long viewStartTime) {
                        this.viewStartTime = viewStartTime;
                    }

                    public static class Report {
                        @SerializedName("click_event_id")
                        private String clickEventId;
                        @SerializedName("extends")
                        private Extends extendsX;
                        @SerializedName("show_event_id")
                        private String showEventId;

                        public String getClickEventId() {
                            return clickEventId;
                        }

                        public void setClickEventId(String clickEventId) {
                            this.clickEventId = clickEventId;
                        }

                        public Extends getExtendsX() {
                            return extendsX;
                        }

                        public void setExtendsX(Extends extendsX) {
                            this.extendsX = extendsX;
                        }

                        public String getShowEventId() {
                            return showEventId;
                        }

                        public void setShowEventId(String showEventId) {
                            this.showEventId = showEventId;
                        }

                        public static class Extends {
                            @SerializedName("season_type")
                            private String seasonType;
                            @SerializedName("season_id")
                            private String seasonId;

                            public String getSeasonType() {
                                return seasonType;
                            }

                            public void setSeasonType(String seasonType) {
                                this.seasonType = seasonType;
                            }

                            public String getSeasonId() {
                                return seasonId;
                            }

                            public void setSeasonId(String seasonId) {
                                this.seasonId = seasonId;
                            }
                        }
                    }
                }
            }

            public static class PayType {
                @SerializedName("allow_ticket")
                private Long allowTicket;

                public Long getAllowTicket() {
                    return allowTicket;
                }

                public void setAllowTicket(Long allowTicket) {
                    this.allowTicket = allowTicket;
                }
            }

            public static class VipReport {
                @SerializedName("season_type")
                private String seasonType;
                @SerializedName("vip_type")
                private String vipType;
                @SerializedName("vip_status")
                private String vipStatus;
                @SerializedName("exp_tag")
                private String expTag;
                @SerializedName("season_id")
                private String seasonId;
                @SerializedName("tips_id")
                private String tipsId;
                @SerializedName("exp_group_tag")
                private String expGroupTag;

                public String getSeasonType() {
                    return seasonType;
                }

                public void setSeasonType(String seasonType) {
                    this.seasonType = seasonType;
                }

                public String getVipType() {
                    return vipType;
                }

                public void setVipType(String vipType) {
                    this.vipType = vipType;
                }

                public String getVipStatus() {
                    return vipStatus;
                }

                public void setVipStatus(String vipStatus) {
                    this.vipStatus = vipStatus;
                }

                public String getExpTag() {
                    return expTag;
                }

                public void setExpTag(String expTag) {
                    this.expTag = expTag;
                }

                public String getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(String seasonId) {
                    this.seasonId = seasonId;
                }

                public String getTipsId() {
                    return tipsId;
                }

                public void setTipsId(String tipsId) {
                    this.tipsId = tipsId;
                }

                public String getExpGroupTag() {
                    return expGroupTag;
                }

                public void setExpGroupTag(String expGroupTag) {
                    this.expGroupTag = expGroupTag;
                }
            }
        }

        public static class PlayStrategy {
            @SerializedName("auto_play_toast")
            private String autoPlayToast;
            @SerializedName("recommend_show_strategy")
            private Long recommendShowStrategy;
            @SerializedName("strategies")
            private List<String> strategies;

            public String getAutoPlayToast() {
                return autoPlayToast;
            }

            public void setAutoPlayToast(String autoPlayToast) {
                this.autoPlayToast = autoPlayToast;
            }

            public Long getRecommendShowStrategy() {
                return recommendShowStrategy;
            }

            public void setRecommendShowStrategy(Long recommendShowStrategy) {
                this.recommendShowStrategy = recommendShowStrategy;
            }

            public List<String> getStrategies() {
                return strategies;
            }

            public void setStrategies(List<String> strategies) {
                this.strategies = strategies;
            }
        }

        public static class PlayerIcon {
            @SerializedName("ctime")
            private Long ctime;
            @SerializedName("hash1")
            private String hash1;
            @SerializedName("hash2")
            private String hash2;
            @SerializedName("url1")
            private String url1;
            @SerializedName("url2")
            private String url2;

            public Long getCtime() {
                return ctime;
            }

            public void setCtime(Long ctime) {
                this.ctime = ctime;
            }

            public String getHash1() {
                return hash1;
            }

            public void setHash1(String hash1) {
                this.hash1 = hash1;
            }

            public String getHash2() {
                return hash2;
            }

            public void setHash2(String hash2) {
                this.hash2 = hash2;
            }

            public String getUrl1() {
                return url1;
            }

            public void setUrl1(String url1) {
                this.url1 = url1;
            }

            public String getUrl2() {
                return url2;
            }

            public void setUrl2(String url2) {
                this.url2 = url2;
            }
        }

        public static class Publish {
            @SerializedName("is_finish")
            private Long isFinish;
            @SerializedName("is_started")
            private Long isStarted;
            @SerializedName("pub_time")
            private String pubTime;
            @SerializedName("pub_time_show")
            private String pubTimeShow;
            @SerializedName("release_date_show")
            private String releaseDateShow;
            @SerializedName("time_length_show")
            private String timeLengthShow;
            @SerializedName("unknow_pub_date")
            private Long unknowPubDate;
            @SerializedName("weekday")
            private Long weekday;

            public Long getIsFinish() {
                return isFinish;
            }

            public void setIsFinish(Long isFinish) {
                this.isFinish = isFinish;
            }

            public Long getIsStarted() {
                return isStarted;
            }

            public void setIsStarted(Long isStarted) {
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

            public String getReleaseDateShow() {
                return releaseDateShow;
            }

            public void setReleaseDateShow(String releaseDateShow) {
                this.releaseDateShow = releaseDateShow;
            }

            public String getTimeLengthShow() {
                return timeLengthShow;
            }

            public void setTimeLengthShow(String timeLengthShow) {
                this.timeLengthShow = timeLengthShow;
            }

            public Long getUnknowPubDate() {
                return unknowPubDate;
            }

            public void setUnknowPubDate(Long unknowPubDate) {
                this.unknowPubDate = unknowPubDate;
            }

            public Long getWeekday() {
                return weekday;
            }

            public void setWeekday(Long weekday) {
                this.weekday = weekday;
            }
        }

        public static class Rating {
            @SerializedName("count")
            private Long count;
            @SerializedName("score")
            private Double score;

            public Long getCount() {
                return count;
            }

            public void setCount(Long count) {
                this.count = count;
            }

            public Double getScore() {
                return score;
            }

            public void setScore(Double score) {
                this.score = score;
            }
        }

        public static class Reserve {
            @SerializedName("episodes")
            private List<?> episodes;
            @SerializedName("tip")
            private String tip;

            public List<?> getEpisodes() {
                return episodes;
            }

            public void setEpisodes(List<?> episodes) {
                this.episodes = episodes;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }
        }

        public static class Rights {
            @SerializedName("allow_bp")
            private Long allowBp;
            @SerializedName("allow_bp_rank")
            private Long allowBpRank;
            @SerializedName("allow_download")
            private Long allowDownload;
            @SerializedName("allow_review")
            private Long allowReview;
            @SerializedName("area_limit")
            private Long areaLimit;
            @SerializedName("ban_area_show")
            private Long banAreaShow;
            @SerializedName("can_watch")
            private Long canWatch;
            @SerializedName("copyright")
            private String copyright;
            @SerializedName("copyright_name")
            private String copyrightName;
            @SerializedName("forbid_pre")
            private Long forbidPre;
            @SerializedName("is_cover_show")
            private Long isCoverShow;
            @SerializedName("is_preview")
            private Long isPreview;
            @SerializedName("only_vip_download")
            private Long onlyVipDownload;
            @SerializedName("resource")
            private String resource;
            @SerializedName("watch_platform")
            private Long watchPlatform;

            public Long getAllowBp() {
                return allowBp;
            }

            public void setAllowBp(Long allowBp) {
                this.allowBp = allowBp;
            }

            public Long getAllowBpRank() {
                return allowBpRank;
            }

            public void setAllowBpRank(Long allowBpRank) {
                this.allowBpRank = allowBpRank;
            }

            public Long getAllowDownload() {
                return allowDownload;
            }

            public void setAllowDownload(Long allowDownload) {
                this.allowDownload = allowDownload;
            }

            public Long getAllowReview() {
                return allowReview;
            }

            public void setAllowReview(Long allowReview) {
                this.allowReview = allowReview;
            }

            public Long getAreaLimit() {
                return areaLimit;
            }

            public void setAreaLimit(Long areaLimit) {
                this.areaLimit = areaLimit;
            }

            public Long getBanAreaShow() {
                return banAreaShow;
            }

            public void setBanAreaShow(Long banAreaShow) {
                this.banAreaShow = banAreaShow;
            }

            public Long getCanWatch() {
                return canWatch;
            }

            public void setCanWatch(Long canWatch) {
                this.canWatch = canWatch;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getCopyrightName() {
                return copyrightName;
            }

            public void setCopyrightName(String copyrightName) {
                this.copyrightName = copyrightName;
            }

            public Long getForbidPre() {
                return forbidPre;
            }

            public void setForbidPre(Long forbidPre) {
                this.forbidPre = forbidPre;
            }

            public Long getIsCoverShow() {
                return isCoverShow;
            }

            public void setIsCoverShow(Long isCoverShow) {
                this.isCoverShow = isCoverShow;
            }

            public Long getIsPreview() {
                return isPreview;
            }

            public void setIsPreview(Long isPreview) {
                this.isPreview = isPreview;
            }

            public Long getOnlyVipDownload() {
                return onlyVipDownload;
            }

            public void setOnlyVipDownload(Long onlyVipDownload) {
                this.onlyVipDownload = onlyVipDownload;
            }

            public String getResource() {
                return resource;
            }

            public void setResource(String resource) {
                this.resource = resource;
            }

            public Long getWatchPlatform() {
                return watchPlatform;
            }

            public void setWatchPlatform(Long watchPlatform) {
                this.watchPlatform = watchPlatform;
            }
        }

        public static class Series {
            @SerializedName("series_id")
            private Long seriesId;
            @SerializedName("series_title")
            private String seriesTitle;

            public Long getSeriesId() {
                return seriesId;
            }

            public void setSeriesId(Long seriesId) {
                this.seriesId = seriesId;
            }

            public String getSeriesTitle() {
                return seriesTitle;
            }

            public void setSeriesTitle(String seriesTitle) {
                this.seriesTitle = seriesTitle;
            }
        }

        public static class Staff {
            @SerializedName("info")
            private String info;
            @SerializedName("title")
            private String title;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class Stat {
            @SerializedName("coins")
            private Long coins;
            @SerializedName("danmakus")
            private Long danmakus;
            @SerializedName("favorite")
            private Long favorite;
            @SerializedName("favorites")
            private Long favorites;
            @SerializedName("followers")
            private String followers;
            @SerializedName("likes")
            private Long likes;
            @SerializedName("play")
            private String play;
            @SerializedName("reply")
            private Long reply;
            @SerializedName("share")
            private Long share;
            @SerializedName("views")
            private Long views;

            public Long getCoins() {
                return coins;
            }

            public void setCoins(Long coins) {
                this.coins = coins;
            }

            public Long getDanmakus() {
                return danmakus;
            }

            public void setDanmakus(Long danmakus) {
                this.danmakus = danmakus;
            }

            public Long getFavorite() {
                return favorite;
            }

            public void setFavorite(Long favorite) {
                this.favorite = favorite;
            }

            public Long getFavorites() {
                return favorites;
            }

            public void setFavorites(Long favorites) {
                this.favorites = favorites;
            }

            public String getFollowers() {
                return followers;
            }

            public void setFollowers(String followers) {
                this.followers = followers;
            }

            public Long getLikes() {
                return likes;
            }

            public void setLikes(Long likes) {
                this.likes = likes;
            }

            public String getPlay() {
                return play;
            }

            public void setPlay(String play) {
                this.play = play;
            }

            public Long getReply() {
                return reply;
            }

            public void setReply(Long reply) {
                this.reply = reply;
            }

            public Long getShare() {
                return share;
            }

            public void setShare(Long share) {
                this.share = share;
            }

            public Long getViews() {
                return views;
            }

            public void setViews(Long views) {
                this.views = views;
            }
        }

        public static class TestSwitch {
            @SerializedName("is_merge_preview_section")
            private Boolean isMergePreviewSection;
            @SerializedName("is_ogv_fav_exp")
            private Boolean isOgvFavExp;
            @SerializedName("movie_mark_action")
            private Long movieMarkAction;
            @SerializedName("was_pugv_style_optimize")
            private Boolean wasPugvStyleOptimize;

            public Boolean getIsMergePreviewSection() {
                return isMergePreviewSection;
            }

            public void setIsMergePreviewSection(Boolean isMergePreviewSection) {
                this.isMergePreviewSection = isMergePreviewSection;
            }

            public Boolean getIsOgvFavExp() {
                return isOgvFavExp;
            }

            public void setIsOgvFavExp(Boolean isOgvFavExp) {
                this.isOgvFavExp = isOgvFavExp;
            }

            public Long getMovieMarkAction() {
                return movieMarkAction;
            }

            public void setMovieMarkAction(Long movieMarkAction) {
                this.movieMarkAction = movieMarkAction;
            }

            public Boolean getWasPugvStyleOptimize() {
                return wasPugvStyleOptimize;
            }

            public void setWasPugvStyleOptimize(Boolean wasPugvStyleOptimize) {
                this.wasPugvStyleOptimize = wasPugvStyleOptimize;
            }
        }

        public static class UpInfo {
            @SerializedName("avatar")
            private String avatar;
            @SerializedName("follower")
            private Long follower;
            @SerializedName("is_follow")
            private Long isFollow;
            @SerializedName("mid")
            private Long mid;
            @SerializedName("pendant")
            private Pendant pendant;
            @SerializedName("theme_type")
            private Long themeType;
            @SerializedName("uname")
            private String uname;
            @SerializedName("verify_type")
            private Long verifyType;
            @SerializedName("vip_label")
            private VipLabel vipLabel;
            @SerializedName("vip_status")
            private Long vipStatus;
            @SerializedName("vip_type")
            private Long vipType;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Long getFollower() {
                return follower;
            }

            public void setFollower(Long follower) {
                this.follower = follower;
            }

            public Long getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(Long isFollow) {
                this.isFollow = isFollow;
            }

            public Long getMid() {
                return mid;
            }

            public void setMid(Long mid) {
                this.mid = mid;
            }

            public Pendant getPendant() {
                return pendant;
            }

            public void setPendant(Pendant pendant) {
                this.pendant = pendant;
            }

            public Long getThemeType() {
                return themeType;
            }

            public void setThemeType(Long themeType) {
                this.themeType = themeType;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public Long getVerifyType() {
                return verifyType;
            }

            public void setVerifyType(Long verifyType) {
                this.verifyType = verifyType;
            }

            public VipLabel getVipLabel() {
                return vipLabel;
            }

            public void setVipLabel(VipLabel vipLabel) {
                this.vipLabel = vipLabel;
            }

            public Long getVipStatus() {
                return vipStatus;
            }

            public void setVipStatus(Long vipStatus) {
                this.vipStatus = vipStatus;
            }

            public Long getVipType() {
                return vipType;
            }

            public void setVipType(Long vipType) {
                this.vipType = vipType;
            }

            public static class Pendant {
                @SerializedName("image")
                private String image;
                @SerializedName("name")
                private String name;
                @SerializedName("pid")
                private Long pid;

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

                public Long getPid() {
                    return pid;
                }

                public void setPid(Long pid) {
                    this.pid = pid;
                }
            }

            public static class VipLabel {
                @SerializedName("label_theme")
                private String labelTheme;
                @SerializedName("path")
                private String path;
                @SerializedName("text")
                private String text;

                public String getLabelTheme() {
                    return labelTheme;
                }

                public void setLabelTheme(String labelTheme) {
                    this.labelTheme = labelTheme;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }
        }

        public static class UserStatus {
            @SerializedName("follow")
            private Long follow;
            @SerializedName("follow_bubble")
            private Long followBubble;
            @SerializedName("follow_status")
            private Long followStatus;
            @SerializedName("pay")
            private Long pay;
            @SerializedName("pay_for")
            private Long payFor;
            @SerializedName("review")
            private Review review;
            @SerializedName("sponsor")
            private Long sponsor;
            @SerializedName("vip")
            private Long vip;
            @SerializedName("vip_frozen")
            private Long vipFrozen;

            public Long getFollow() {
                return follow;
            }

            public void setFollow(Long follow) {
                this.follow = follow;
            }

            public Long getFollowBubble() {
                return followBubble;
            }

            public void setFollowBubble(Long followBubble) {
                this.followBubble = followBubble;
            }

            public Long getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(Long followStatus) {
                this.followStatus = followStatus;
            }

            public Long getPay() {
                return pay;
            }

            public void setPay(Long pay) {
                this.pay = pay;
            }

            public Long getPayFor() {
                return payFor;
            }

            public void setPayFor(Long payFor) {
                this.payFor = payFor;
            }

            public Review getReview() {
                return review;
            }

            public void setReview(Review review) {
                this.review = review;
            }

            public Long getSponsor() {
                return sponsor;
            }

            public void setSponsor(Long sponsor) {
                this.sponsor = sponsor;
            }

            public Long getVip() {
                return vip;
            }

            public void setVip(Long vip) {
                this.vip = vip;
            }

            public Long getVipFrozen() {
                return vipFrozen;
            }

            public void setVipFrozen(Long vipFrozen) {
                this.vipFrozen = vipFrozen;
            }

            public static class Review {
                @SerializedName("article_url")
                private String articleUrl;
                @SerializedName("is_open")
                private Long isOpen;
                @SerializedName("score")
                private Long score;

                public String getArticleUrl() {
                    return articleUrl;
                }

                public void setArticleUrl(String articleUrl) {
                    this.articleUrl = articleUrl;
                }

                public Long getIsOpen() {
                    return isOpen;
                }

                public void setIsOpen(Long isOpen) {
                    this.isOpen = isOpen;
                }

                public Long getScore() {
                    return score;
                }

                public void setScore(Long score) {
                    this.score = score;
                }
            }
        }

        public static class Areas {
            @SerializedName("id")
            private Long id;
            @SerializedName("name")
            private String name;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Celebrity {
            @SerializedName("avatar")
            private String avatar;
            @SerializedName("character_avatar")
            private String characterAvatar;
            @SerializedName("desc")
            private String desc;
            @SerializedName("id")
            private Long id;
            @SerializedName("name")
            private String name;
            @SerializedName("short_desc")
            private String shortDesc;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCharacterAvatar() {
                return characterAvatar;
            }

            public void setCharacterAvatar(String characterAvatar) {
                this.characterAvatar = characterAvatar;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }
        }

        public static class Modules {
            @SerializedName("data")
            private Data data;
            @SerializedName("id")
            private Long id;
            @SerializedName("module_style")
            private ModuleStyle moduleStyle;
            @SerializedName("more")
            private String more;
            @SerializedName("report")
            private Report report;
            @SerializedName("style")
            private String style;
            @SerializedName("title")
            private String title;

            public Data getData() {
                return data;
            }

            public void setData(Data data) {
                this.data = data;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public ModuleStyle getModuleStyle() {
                return moduleStyle;
            }

            public void setModuleStyle(ModuleStyle moduleStyle) {
                this.moduleStyle = moduleStyle;
            }

            public String getMore() {
                return more;
            }

            public void setMore(String more) {
                this.more = more;
            }

            public Report getReport() {
                return report;
            }

            public void setReport(Report report) {
                this.report = report;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public static class Data {
                @SerializedName("episodes")
                private List<Episodes> episodes;

                public List<Episodes> getEpisodes() {
                    return episodes;
                }

                public void setEpisodes(List<Episodes> episodes) {
                    this.episodes = episodes;
                }

                public static class Episodes {
                    @SerializedName("aid")
                    private Long aid;
                    @SerializedName("badge")
                    private String badge;
                    @SerializedName("badge_info")
                    private BadgeInfo badgeInfo;
                    @SerializedName("badge_type")
                    private Long badgeType;
                    @SerializedName("bvid")
                    private String bvid;
                    @SerializedName("cid")
                    private Long cid;
                    @SerializedName("cover")
                    private String cover;
                    @SerializedName("dimension")
                    private Dimension dimension;
                    @SerializedName("duration")
                    private Long duration;
                    @SerializedName("ep_index")
                    private Long epIndex;
                    @SerializedName("from")
                    private String from;
                    @SerializedName("id")
                    private Long id;
                    @SerializedName("link")
                    private String link;
                    @SerializedName("long_title")
                    private String longTitle;
                    @SerializedName("pub_time")
                    private Long pubTime;
                    @SerializedName("pv")
                    private Long pv;
                    @SerializedName("release_date")
                    private String releaseDate;
                    @SerializedName("report")
                    private Report report;
                    @SerializedName("rights")
                    private Rights rights;
                    @SerializedName("section_index")
                    private Long sectionIndex;
                    @SerializedName("share_copy")
                    private String shareCopy;
                    @SerializedName("share_url")
                    private String shareUrl;
                    @SerializedName("short_link")
                    private String shortLink;
                    @SerializedName("stat")
                    private Stat stat;
                    @SerializedName("status")
                    private Long status;
                    @SerializedName("subtitle")
                    private String subtitle;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("vid")
                    private String vid;
                    @SerializedName("up_infos")
                    private List<UpInfos> upInfos;

                    public Long getAid() {
                        return aid;
                    }

                    public void setAid(Long aid) {
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

                    public Long getBadgeType() {
                        return badgeType;
                    }

                    public void setBadgeType(Long badgeType) {
                        this.badgeType = badgeType;
                    }

                    public String getBvid() {
                        return bvid;
                    }

                    public void setBvid(String bvid) {
                        this.bvid = bvid;
                    }

                    public Long getCid() {
                        return cid;
                    }

                    public void setCid(Long cid) {
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

                    public Long getDuration() {
                        return duration;
                    }

                    public void setDuration(Long duration) {
                        this.duration = duration;
                    }

                    public Long getEpIndex() {
                        return epIndex;
                    }

                    public void setEpIndex(Long epIndex) {
                        this.epIndex = epIndex;
                    }

                    public String getFrom() {
                        return from;
                    }

                    public void setFrom(String from) {
                        this.from = from;
                    }

                    public Long getId() {
                        return id;
                    }

                    public void setId(Long id) {
                        this.id = id;
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

                    public Long getPubTime() {
                        return pubTime;
                    }

                    public void setPubTime(Long pubTime) {
                        this.pubTime = pubTime;
                    }

                    public Long getPv() {
                        return pv;
                    }

                    public void setPv(Long pv) {
                        this.pv = pv;
                    }

                    public String getReleaseDate() {
                        return releaseDate;
                    }

                    public void setReleaseDate(String releaseDate) {
                        this.releaseDate = releaseDate;
                    }

                    public Report getReport() {
                        return report;
                    }

                    public void setReport(Report report) {
                        this.report = report;
                    }

                    public Rights getRights() {
                        return rights;
                    }

                    public void setRights(Rights rights) {
                        this.rights = rights;
                    }

                    public Long getSectionIndex() {
                        return sectionIndex;
                    }

                    public void setSectionIndex(Long sectionIndex) {
                        this.sectionIndex = sectionIndex;
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

                    public Stat getStat() {
                        return stat;
                    }

                    public void setStat(Stat stat) {
                        this.stat = stat;
                    }

                    public Long getStatus() {
                        return status;
                    }

                    public void setStatus(Long status) {
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

                    public List<UpInfos> getUpInfos() {
                        return upInfos;
                    }

                    public void setUpInfos(List<UpInfos> upInfos) {
                        this.upInfos = upInfos;
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
                        private Long height;
                        @SerializedName("rotate")
                        private Long rotate;
                        @SerializedName("width")
                        private Long width;

                        public Long getHeight() {
                            return height;
                        }

                        public void setHeight(Long height) {
                            this.height = height;
                        }

                        public Long getRotate() {
                            return rotate;
                        }

                        public void setRotate(Long rotate) {
                            this.rotate = rotate;
                        }

                        public Long getWidth() {
                            return width;
                        }

                        public void setWidth(Long width) {
                            this.width = width;
                        }
                    }

                    public static class Report {
                        @SerializedName("aid")
                        private String aid;
                        @SerializedName("ep_title")
                        private String epTitle;
                        @SerializedName("epid")
                        private String epid;
                        @SerializedName("season_id")
                        private String seasonId;
                        @SerializedName("season_type")
                        private String seasonType;
                        @SerializedName("section_id")
                        private String sectionId;
                        @SerializedName("section_type")
                        private String sectionType;

                        public String getAid() {
                            return aid;
                        }

                        public void setAid(String aid) {
                            this.aid = aid;
                        }

                        public String getEpTitle() {
                            return epTitle;
                        }

                        public void setEpTitle(String epTitle) {
                            this.epTitle = epTitle;
                        }

                        public String getEpid() {
                            return epid;
                        }

                        public void setEpid(String epid) {
                            this.epid = epid;
                        }

                        public String getSeasonId() {
                            return seasonId;
                        }

                        public void setSeasonId(String seasonId) {
                            this.seasonId = seasonId;
                        }

                        public String getSeasonType() {
                            return seasonType;
                        }

                        public void setSeasonType(String seasonType) {
                            this.seasonType = seasonType;
                        }

                        public String getSectionId() {
                            return sectionId;
                        }

                        public void setSectionId(String sectionId) {
                            this.sectionId = sectionId;
                        }

                        public String getSectionType() {
                            return sectionType;
                        }

                        public void setSectionType(String sectionType) {
                            this.sectionType = sectionType;
                        }
                    }

                    public static class Rights {
                        @SerializedName("allow_demand")
                        private Long allowDemand;
                        @SerializedName("allow_dm")
                        private Long allowDm;
                        @SerializedName("allow_download")
                        private Long allowDownload;
                        @SerializedName("area_limit")
                        private Long areaLimit;

                        public Long getAllowDemand() {
                            return allowDemand;
                        }

                        public void setAllowDemand(Long allowDemand) {
                            this.allowDemand = allowDemand;
                        }

                        public Long getAllowDm() {
                            return allowDm;
                        }

                        public void setAllowDm(Long allowDm) {
                            this.allowDm = allowDm;
                        }

                        public Long getAllowDownload() {
                            return allowDownload;
                        }

                        public void setAllowDownload(Long allowDownload) {
                            this.allowDownload = allowDownload;
                        }

                        public Long getAreaLimit() {
                            return areaLimit;
                        }

                        public void setAreaLimit(Long areaLimit) {
                            this.areaLimit = areaLimit;
                        }
                    }

                    public static class Stat {
                        @SerializedName("coin")
                        private Long coin;
                        @SerializedName("danmakus")
                        private Long danmakus;
                        @SerializedName("likes")
                        private Long likes;
                        @SerializedName("play")
                        private Long play;
                        @SerializedName("reply")
                        private Long reply;

                        public Long getCoin() {
                            return coin;
                        }

                        public void setCoin(Long coin) {
                            this.coin = coin;
                        }

                        public Long getDanmakus() {
                            return danmakus;
                        }

                        public void setDanmakus(Long danmakus) {
                            this.danmakus = danmakus;
                        }

                        public Long getLikes() {
                            return likes;
                        }

                        public void setLikes(Long likes) {
                            this.likes = likes;
                        }

                        public Long getPlay() {
                            return play;
                        }

                        public void setPlay(Long play) {
                            this.play = play;
                        }

                        public Long getReply() {
                            return reply;
                        }

                        public void setReply(Long reply) {
                            this.reply = reply;
                        }
                    }

                    public static class UpInfos {
                        @SerializedName("follower")
                        private Long follower;
                        @SerializedName("is_follow")
                        private Long isFollow;
                        @SerializedName("mid")
                        private Long mid;
                        @SerializedName("title")
                        private String title;

                        public Long getFollower() {
                            return follower;
                        }

                        public void setFollower(Long follower) {
                            this.follower = follower;
                        }

                        public Long getIsFollow() {
                            return isFollow;
                        }

                        public void setIsFollow(Long isFollow) {
                            this.isFollow = isFollow;
                        }

                        public Long getMid() {
                            return mid;
                        }

                        public void setMid(Long mid) {
                            this.mid = mid;
                        }

                        public String getTitle() {
                            return title;
                        }

                        public void setTitle(String title) {
                            this.title = title;
                        }
                    }
                }
            }

            public static class ModuleStyle {
                @SerializedName("hidden")
                private Long hidden;
                @SerializedName("line")
                private Long line;

                public Long getHidden() {
                    return hidden;
                }

                public void setHidden(Long hidden) {
                    this.hidden = hidden;
                }

                public Long getLine() {
                    return line;
                }

                public void setLine(Long line) {
                    this.line = line;
                }
            }

            public static class Report {
                @SerializedName("season_id")
                private String seasonId;
                @SerializedName("season_type")
                private String seasonType;
                @SerializedName("sec_title")
                private String secTitle;
                @SerializedName("section_id")
                private String sectionId;
                @SerializedName("section_type")
                private String sectionType;

                public String getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(String seasonId) {
                    this.seasonId = seasonId;
                }

                public String getSeasonType() {
                    return seasonType;
                }

                public void setSeasonType(String seasonType) {
                    this.seasonType = seasonType;
                }

                public String getSecTitle() {
                    return secTitle;
                }

                public void setSecTitle(String secTitle) {
                    this.secTitle = secTitle;
                }

                public String getSectionId() {
                    return sectionId;
                }

                public void setSectionId(String sectionId) {
                    this.sectionId = sectionId;
                }

                public String getSectionType() {
                    return sectionType;
                }

                public void setSectionType(String sectionType) {
                    this.sectionType = sectionType;
                }
            }
        }

        public static class Styles {
            @SerializedName("id")
            private Long id;
            @SerializedName("name")
            private String name;
            @SerializedName("url")
            private String url;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}