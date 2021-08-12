package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BangumiDetailPageBean{

    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private Result result;
    @SerializedName("message")
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
        private Integer mediaId;
        @SerializedName("mode")
        private Integer mode;
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
        private Integer seasonId;
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
        private Integer status;
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
        private Integer total;
        @SerializedName("type")
        private Integer type;
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

        public Integer getMediaId() {
            return mediaId;
        }

        public void setMediaId(Integer mediaId) {
            this.mediaId = mediaId;
        }

        public Integer getMode() {
            return mode;
        }

        public void setMode(Integer mode) {
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

        public Integer getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(Integer seasonId) {
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
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

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
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
            private Integer id;
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
            private Integer type;

            public String getH5Link() {
                return h5Link;
            }

            public void setH5Link(String h5Link) {
                this.h5Link = h5Link;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
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
                private Integer follower;
                @SerializedName("is_follow")
                private Integer isFollow;
                @SerializedName("mid")
                private Integer mid;
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

                public Integer getFollower() {
                    return follower;
                }

                public void setFollower(Integer follower) {
                    this.follower = follower;
                }

                public Integer getIsFollow() {
                    return isFollow;
                }

                public void setIsFollow(Integer isFollow) {
                    this.isFollow = isFollow;
                }

                public Integer getMid() {
                    return mid;
                }

                public void setMid(Integer mid) {
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
                private Integer follower;
                @SerializedName("is_follow")
                private Integer isFollow;
                @SerializedName("mid")
                private Integer mid;
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

                public Integer getFollower() {
                    return follower;
                }

                public void setFollower(Integer follower) {
                    this.follower = follower;
                }

                public Integer getIsFollow() {
                    return isFollow;
                }

                public void setIsFollow(Integer isFollow) {
                    this.isFollow = isFollow;
                }

                public Integer getMid() {
                    return mid;
                }

                public void setMid(Integer mid) {
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
            private Integer id;
            @SerializedName("is_new")
            private Integer isNew;
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

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getIsNew() {
                return isNew;
            }

            public void setIsNew(Integer isNew) {
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
            private Integer aid;
            @SerializedName("allow_jump")
            private Integer allowJump;
            @SerializedName("cid")
            private Integer cid;
            @SerializedName("duration")
            private Integer duration;
            @SerializedName("type")
            private Integer type;
            @SerializedName("url")
            private String url;

            public Integer getAid() {
                return aid;
            }

            public void setAid(Integer aid) {
                this.aid = aid;
            }

            public Integer getAllowJump() {
                return allowJump;
            }

            public void setAllowJump(Integer allowJump) {
                this.allowJump = allowJump;
            }

            public Integer getCid() {
                return cid;
            }

            public void setCid(Integer cid) {
                this.cid = cid;
            }

            public Integer getDuration() {
                return duration;
            }

            public void setDuration(Integer duration) {
                this.duration = duration;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
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
            private Integer reportType;
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

            public Integer getReportType() {
                return reportType;
            }

            public void setReportType(Integer reportType) {
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
                    private Integer angleStyle;
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
                    private Integer showType;
                    @SerializedName("text_color")
                    private String textColor;
                    @SerializedName("text_night_color")
                    private String textNightColor;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("type")
                    private Integer type;
                    @SerializedName("url")
                    private String url;
                    @SerializedName("url_open_type")
                    private Integer urlOpenType;
                    @SerializedName("view_start_time")
                    private Integer viewStartTime;

                    public Integer getAngleStyle() {
                        return angleStyle;
                    }

                    public void setAngleStyle(Integer angleStyle) {
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

                    public Integer getShowType() {
                        return showType;
                    }

                    public void setShowType(Integer showType) {
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

                    public Integer getType() {
                        return type;
                    }

                    public void setType(Integer type) {
                        this.type = type;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Integer getUrlOpenType() {
                        return urlOpenType;
                    }

                    public void setUrlOpenType(Integer urlOpenType) {
                        this.urlOpenType = urlOpenType;
                    }

                    public Integer getViewStartTime() {
                        return viewStartTime;
                    }

                    public void setViewStartTime(Integer viewStartTime) {
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
                private Integer allowTicket;

                public Integer getAllowTicket() {
                    return allowTicket;
                }

                public void setAllowTicket(Integer allowTicket) {
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
            private Integer recommendShowStrategy;
            @SerializedName("strategies")
            private List<String> strategies;

            public String getAutoPlayToast() {
                return autoPlayToast;
            }

            public void setAutoPlayToast(String autoPlayToast) {
                this.autoPlayToast = autoPlayToast;
            }

            public Integer getRecommendShowStrategy() {
                return recommendShowStrategy;
            }

            public void setRecommendShowStrategy(Integer recommendShowStrategy) {
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
            private Integer ctime;
            @SerializedName("hash1")
            private String hash1;
            @SerializedName("hash2")
            private String hash2;
            @SerializedName("url1")
            private String url1;
            @SerializedName("url2")
            private String url2;

            public Integer getCtime() {
                return ctime;
            }

            public void setCtime(Integer ctime) {
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
            private Integer isFinish;
            @SerializedName("is_started")
            private Integer isStarted;
            @SerializedName("pub_time")
            private String pubTime;
            @SerializedName("pub_time_show")
            private String pubTimeShow;
            @SerializedName("release_date_show")
            private String releaseDateShow;
            @SerializedName("time_length_show")
            private String timeLengthShow;
            @SerializedName("unknow_pub_date")
            private Integer unknowPubDate;
            @SerializedName("weekday")
            private Integer weekday;

            public Integer getIsFinish() {
                return isFinish;
            }

            public void setIsFinish(Integer isFinish) {
                this.isFinish = isFinish;
            }

            public Integer getIsStarted() {
                return isStarted;
            }

            public void setIsStarted(Integer isStarted) {
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

            public Integer getUnknowPubDate() {
                return unknowPubDate;
            }

            public void setUnknowPubDate(Integer unknowPubDate) {
                this.unknowPubDate = unknowPubDate;
            }

            public Integer getWeekday() {
                return weekday;
            }

            public void setWeekday(Integer weekday) {
                this.weekday = weekday;
            }
        }

        public static class Rating {
            @SerializedName("count")
            private Integer count;
            @SerializedName("score")
            private Double score;

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
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
            private Integer allowBp;
            @SerializedName("allow_bp_rank")
            private Integer allowBpRank;
            @SerializedName("allow_download")
            private Integer allowDownload;
            @SerializedName("allow_review")
            private Integer allowReview;
            @SerializedName("area_limit")
            private Integer areaLimit;
            @SerializedName("ban_area_show")
            private Integer banAreaShow;
            @SerializedName("can_watch")
            private Integer canWatch;
            @SerializedName("copyright")
            private String copyright;
            @SerializedName("copyright_name")
            private String copyrightName;
            @SerializedName("forbid_pre")
            private Integer forbidPre;
            @SerializedName("is_cover_show")
            private Integer isCoverShow;
            @SerializedName("is_preview")
            private Integer isPreview;
            @SerializedName("only_vip_download")
            private Integer onlyVipDownload;
            @SerializedName("resource")
            private String resource;
            @SerializedName("watch_platform")
            private Integer watchPlatform;

            public Integer getAllowBp() {
                return allowBp;
            }

            public void setAllowBp(Integer allowBp) {
                this.allowBp = allowBp;
            }

            public Integer getAllowBpRank() {
                return allowBpRank;
            }

            public void setAllowBpRank(Integer allowBpRank) {
                this.allowBpRank = allowBpRank;
            }

            public Integer getAllowDownload() {
                return allowDownload;
            }

            public void setAllowDownload(Integer allowDownload) {
                this.allowDownload = allowDownload;
            }

            public Integer getAllowReview() {
                return allowReview;
            }

            public void setAllowReview(Integer allowReview) {
                this.allowReview = allowReview;
            }

            public Integer getAreaLimit() {
                return areaLimit;
            }

            public void setAreaLimit(Integer areaLimit) {
                this.areaLimit = areaLimit;
            }

            public Integer getBanAreaShow() {
                return banAreaShow;
            }

            public void setBanAreaShow(Integer banAreaShow) {
                this.banAreaShow = banAreaShow;
            }

            public Integer getCanWatch() {
                return canWatch;
            }

            public void setCanWatch(Integer canWatch) {
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

            public Integer getForbidPre() {
                return forbidPre;
            }

            public void setForbidPre(Integer forbidPre) {
                this.forbidPre = forbidPre;
            }

            public Integer getIsCoverShow() {
                return isCoverShow;
            }

            public void setIsCoverShow(Integer isCoverShow) {
                this.isCoverShow = isCoverShow;
            }

            public Integer getIsPreview() {
                return isPreview;
            }

            public void setIsPreview(Integer isPreview) {
                this.isPreview = isPreview;
            }

            public Integer getOnlyVipDownload() {
                return onlyVipDownload;
            }

            public void setOnlyVipDownload(Integer onlyVipDownload) {
                this.onlyVipDownload = onlyVipDownload;
            }

            public String getResource() {
                return resource;
            }

            public void setResource(String resource) {
                this.resource = resource;
            }

            public Integer getWatchPlatform() {
                return watchPlatform;
            }

            public void setWatchPlatform(Integer watchPlatform) {
                this.watchPlatform = watchPlatform;
            }
        }

        public static class Series {
            @SerializedName("series_id")
            private Integer seriesId;
            @SerializedName("series_title")
            private String seriesTitle;

            public Integer getSeriesId() {
                return seriesId;
            }

            public void setSeriesId(Integer seriesId) {
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
            private Integer coins;
            @SerializedName("danmakus")
            private Integer danmakus;
            @SerializedName("favorite")
            private Integer favorite;
            @SerializedName("favorites")
            private Integer favorites;
            @SerializedName("followers")
            private String followers;
            @SerializedName("likes")
            private Integer likes;
            @SerializedName("play")
            private String play;
            @SerializedName("reply")
            private Integer reply;
            @SerializedName("share")
            private Integer share;
            @SerializedName("views")
            private Integer views;

            public Integer getCoins() {
                return coins;
            }

            public void setCoins(Integer coins) {
                this.coins = coins;
            }

            public Integer getDanmakus() {
                return danmakus;
            }

            public void setDanmakus(Integer danmakus) {
                this.danmakus = danmakus;
            }

            public Integer getFavorite() {
                return favorite;
            }

            public void setFavorite(Integer favorite) {
                this.favorite = favorite;
            }

            public Integer getFavorites() {
                return favorites;
            }

            public void setFavorites(Integer favorites) {
                this.favorites = favorites;
            }

            public String getFollowers() {
                return followers;
            }

            public void setFollowers(String followers) {
                this.followers = followers;
            }

            public Integer getLikes() {
                return likes;
            }

            public void setLikes(Integer likes) {
                this.likes = likes;
            }

            public String getPlay() {
                return play;
            }

            public void setPlay(String play) {
                this.play = play;
            }

            public Integer getReply() {
                return reply;
            }

            public void setReply(Integer reply) {
                this.reply = reply;
            }

            public Integer getShare() {
                return share;
            }

            public void setShare(Integer share) {
                this.share = share;
            }

            public Integer getViews() {
                return views;
            }

            public void setViews(Integer views) {
                this.views = views;
            }
        }

        public static class TestSwitch {
            @SerializedName("is_merge_preview_section")
            private Boolean isMergePreviewSection;
            @SerializedName("is_ogv_fav_exp")
            private Boolean isOgvFavExp;
            @SerializedName("movie_mark_action")
            private Integer movieMarkAction;
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

            public Integer getMovieMarkAction() {
                return movieMarkAction;
            }

            public void setMovieMarkAction(Integer movieMarkAction) {
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
            private Integer follower;
            @SerializedName("is_follow")
            private Integer isFollow;
            @SerializedName("mid")
            private Integer mid;
            @SerializedName("pendant")
            private Pendant pendant;
            @SerializedName("theme_type")
            private Integer themeType;
            @SerializedName("uname")
            private String uname;
            @SerializedName("verify_type")
            private Integer verifyType;
            @SerializedName("vip_label")
            private VipLabel vipLabel;
            @SerializedName("vip_status")
            private Integer vipStatus;
            @SerializedName("vip_type")
            private Integer vipType;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Integer getFollower() {
                return follower;
            }

            public void setFollower(Integer follower) {
                this.follower = follower;
            }

            public Integer getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(Integer isFollow) {
                this.isFollow = isFollow;
            }

            public Integer getMid() {
                return mid;
            }

            public void setMid(Integer mid) {
                this.mid = mid;
            }

            public Pendant getPendant() {
                return pendant;
            }

            public void setPendant(Pendant pendant) {
                this.pendant = pendant;
            }

            public Integer getThemeType() {
                return themeType;
            }

            public void setThemeType(Integer themeType) {
                this.themeType = themeType;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public Integer getVerifyType() {
                return verifyType;
            }

            public void setVerifyType(Integer verifyType) {
                this.verifyType = verifyType;
            }

            public VipLabel getVipLabel() {
                return vipLabel;
            }

            public void setVipLabel(VipLabel vipLabel) {
                this.vipLabel = vipLabel;
            }

            public Integer getVipStatus() {
                return vipStatus;
            }

            public void setVipStatus(Integer vipStatus) {
                this.vipStatus = vipStatus;
            }

            public Integer getVipType() {
                return vipType;
            }

            public void setVipType(Integer vipType) {
                this.vipType = vipType;
            }

            public static class Pendant {
                @SerializedName("image")
                private String image;
                @SerializedName("name")
                private String name;
                @SerializedName("pid")
                private Integer pid;

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

                public Integer getPid() {
                    return pid;
                }

                public void setPid(Integer pid) {
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
            private Integer follow;
            @SerializedName("follow_bubble")
            private Integer followBubble;
            @SerializedName("follow_status")
            private Integer followStatus;
            @SerializedName("pay")
            private Integer pay;
            @SerializedName("pay_for")
            private Integer payFor;
            @SerializedName("review")
            private Review review;
            @SerializedName("sponsor")
            private Integer sponsor;
            @SerializedName("vip")
            private Integer vip;
            @SerializedName("vip_frozen")
            private Integer vipFrozen;

            public Integer getFollow() {
                return follow;
            }

            public void setFollow(Integer follow) {
                this.follow = follow;
            }

            public Integer getFollowBubble() {
                return followBubble;
            }

            public void setFollowBubble(Integer followBubble) {
                this.followBubble = followBubble;
            }

            public Integer getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(Integer followStatus) {
                this.followStatus = followStatus;
            }

            public Integer getPay() {
                return pay;
            }

            public void setPay(Integer pay) {
                this.pay = pay;
            }

            public Integer getPayFor() {
                return payFor;
            }

            public void setPayFor(Integer payFor) {
                this.payFor = payFor;
            }

            public Review getReview() {
                return review;
            }

            public void setReview(Review review) {
                this.review = review;
            }

            public Integer getSponsor() {
                return sponsor;
            }

            public void setSponsor(Integer sponsor) {
                this.sponsor = sponsor;
            }

            public Integer getVip() {
                return vip;
            }

            public void setVip(Integer vip) {
                this.vip = vip;
            }

            public Integer getVipFrozen() {
                return vipFrozen;
            }

            public void setVipFrozen(Integer vipFrozen) {
                this.vipFrozen = vipFrozen;
            }

            public static class Review {
                @SerializedName("article_url")
                private String articleUrl;
                @SerializedName("is_open")
                private Integer isOpen;
                @SerializedName("score")
                private Integer score;

                public String getArticleUrl() {
                    return articleUrl;
                }

                public void setArticleUrl(String articleUrl) {
                    this.articleUrl = articleUrl;
                }

                public Integer getIsOpen() {
                    return isOpen;
                }

                public void setIsOpen(Integer isOpen) {
                    this.isOpen = isOpen;
                }

                public Integer getScore() {
                    return score;
                }

                public void setScore(Integer score) {
                    this.score = score;
                }
            }
        }

        public static class Areas {
            @SerializedName("id")
            private Integer id;
            @SerializedName("name")
            private String name;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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
            private Integer id;
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

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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
            private Integer id;
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

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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
                    private Integer aid;
                    @SerializedName("badge")
                    private String badge;
                    @SerializedName("badge_info")
                    private BadgeInfo badgeInfo;
                    @SerializedName("badge_type")
                    private Integer badgeType;
                    @SerializedName("bvid")
                    private String bvid;
                    @SerializedName("cid")
                    private Integer cid;
                    @SerializedName("cover")
                    private String cover;
                    @SerializedName("dimension")
                    private Dimension dimension;
                    @SerializedName("duration")
                    private Integer duration;
                    @SerializedName("ep_index")
                    private Integer epIndex;
                    @SerializedName("from")
                    private String from;
                    @SerializedName("id")
                    private Integer id;
                    @SerializedName("link")
                    private String link;
                    @SerializedName("long_title")
                    private String longTitle;
                    @SerializedName("pub_time")
                    private Integer pubTime;
                    @SerializedName("pv")
                    private Integer pv;
                    @SerializedName("release_date")
                    private String releaseDate;
                    @SerializedName("report")
                    private Report report;
                    @SerializedName("rights")
                    private Rights rights;
                    @SerializedName("section_index")
                    private Integer sectionIndex;
                    @SerializedName("share_copy")
                    private String shareCopy;
                    @SerializedName("share_url")
                    private String shareUrl;
                    @SerializedName("short_link")
                    private String shortLink;
                    @SerializedName("stat")
                    private Stat stat;
                    @SerializedName("status")
                    private Integer status;
                    @SerializedName("subtitle")
                    private String subtitle;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("vid")
                    private String vid;
                    @SerializedName("up_infos")
                    private List<UpInfos> upInfos;

                    public Integer getAid() {
                        return aid;
                    }

                    public void setAid(Integer aid) {
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

                    public Integer getBadgeType() {
                        return badgeType;
                    }

                    public void setBadgeType(Integer badgeType) {
                        this.badgeType = badgeType;
                    }

                    public String getBvid() {
                        return bvid;
                    }

                    public void setBvid(String bvid) {
                        this.bvid = bvid;
                    }

                    public Integer getCid() {
                        return cid;
                    }

                    public void setCid(Integer cid) {
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

                    public Integer getDuration() {
                        return duration;
                    }

                    public void setDuration(Integer duration) {
                        this.duration = duration;
                    }

                    public Integer getEpIndex() {
                        return epIndex;
                    }

                    public void setEpIndex(Integer epIndex) {
                        this.epIndex = epIndex;
                    }

                    public String getFrom() {
                        return from;
                    }

                    public void setFrom(String from) {
                        this.from = from;
                    }

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
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

                    public Integer getPubTime() {
                        return pubTime;
                    }

                    public void setPubTime(Integer pubTime) {
                        this.pubTime = pubTime;
                    }

                    public Integer getPv() {
                        return pv;
                    }

                    public void setPv(Integer pv) {
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

                    public Integer getSectionIndex() {
                        return sectionIndex;
                    }

                    public void setSectionIndex(Integer sectionIndex) {
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

                    public Integer getStatus() {
                        return status;
                    }

                    public void setStatus(Integer status) {
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
                        private Integer height;
                        @SerializedName("rotate")
                        private Integer rotate;
                        @SerializedName("width")
                        private Integer width;

                        public Integer getHeight() {
                            return height;
                        }

                        public void setHeight(Integer height) {
                            this.height = height;
                        }

                        public Integer getRotate() {
                            return rotate;
                        }

                        public void setRotate(Integer rotate) {
                            this.rotate = rotate;
                        }

                        public Integer getWidth() {
                            return width;
                        }

                        public void setWidth(Integer width) {
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
                        private Integer allowDemand;
                        @SerializedName("allow_dm")
                        private Integer allowDm;
                        @SerializedName("allow_download")
                        private Integer allowDownload;
                        @SerializedName("area_limit")
                        private Integer areaLimit;

                        public Integer getAllowDemand() {
                            return allowDemand;
                        }

                        public void setAllowDemand(Integer allowDemand) {
                            this.allowDemand = allowDemand;
                        }

                        public Integer getAllowDm() {
                            return allowDm;
                        }

                        public void setAllowDm(Integer allowDm) {
                            this.allowDm = allowDm;
                        }

                        public Integer getAllowDownload() {
                            return allowDownload;
                        }

                        public void setAllowDownload(Integer allowDownload) {
                            this.allowDownload = allowDownload;
                        }

                        public Integer getAreaLimit() {
                            return areaLimit;
                        }

                        public void setAreaLimit(Integer areaLimit) {
                            this.areaLimit = areaLimit;
                        }
                    }

                    public static class Stat {
                        @SerializedName("coin")
                        private Integer coin;
                        @SerializedName("danmakus")
                        private Integer danmakus;
                        @SerializedName("likes")
                        private Integer likes;
                        @SerializedName("play")
                        private Integer play;
                        @SerializedName("reply")
                        private Integer reply;

                        public Integer getCoin() {
                            return coin;
                        }

                        public void setCoin(Integer coin) {
                            this.coin = coin;
                        }

                        public Integer getDanmakus() {
                            return danmakus;
                        }

                        public void setDanmakus(Integer danmakus) {
                            this.danmakus = danmakus;
                        }

                        public Integer getLikes() {
                            return likes;
                        }

                        public void setLikes(Integer likes) {
                            this.likes = likes;
                        }

                        public Integer getPlay() {
                            return play;
                        }

                        public void setPlay(Integer play) {
                            this.play = play;
                        }

                        public Integer getReply() {
                            return reply;
                        }

                        public void setReply(Integer reply) {
                            this.reply = reply;
                        }
                    }

                    public static class UpInfos {
                        @SerializedName("follower")
                        private Integer follower;
                        @SerializedName("is_follow")
                        private Integer isFollow;
                        @SerializedName("mid")
                        private Integer mid;
                        @SerializedName("title")
                        private String title;

                        public Integer getFollower() {
                            return follower;
                        }

                        public void setFollower(Integer follower) {
                            this.follower = follower;
                        }

                        public Integer getIsFollow() {
                            return isFollow;
                        }

                        public void setIsFollow(Integer isFollow) {
                            this.isFollow = isFollow;
                        }

                        public Integer getMid() {
                            return mid;
                        }

                        public void setMid(Integer mid) {
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
                private Integer hidden;
                @SerializedName("line")
                private Integer line;

                public Integer getHidden() {
                    return hidden;
                }

                public void setHidden(Integer hidden) {
                    this.hidden = hidden;
                }

                public Integer getLine() {
                    return line;
                }

                public void setLine(Integer line) {
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
            private Integer id;
            @SerializedName("name")
            private String name;
            @SerializedName("url")
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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