package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class BangumiRelatedBean {

    @SerializedName("code")
    private long code;
    @SerializedName("data")
    private Data data;
    @SerializedName("message")
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Data {
        @SerializedName("relates")
        private List<Relates> relates;
        @SerializedName("season")
        private List<Season> season;

        public List<Relates> getRelates() {
            return relates;
        }

        public void setRelates(List<Relates> relates) {
            this.relates = relates;
        }

        public List<Season> getSeason() {
            return season;
        }

        public void setSeason(List<Season> season) {
            this.season = season;
        }

        public static class Relates {
            @SerializedName("desc1")
            private String desc1;
            @SerializedName("desc2")
            private String desc2;
            @SerializedName("item_id")
            private long itemId;
            @SerializedName("pic")
            private String pic;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private long type;
            @SerializedName("type_name")
            private String typeName;
            @SerializedName("url")
            private String url;

            public String getDesc1() {
                return desc1;
            }

            public void setDesc1(String desc1) {
                this.desc1 = desc1;
            }

            public String getDesc2() {
                return desc2;
            }

            public void setDesc2(String desc2) {
                this.desc2 = desc2;
            }

            public long getItemId() {
                return itemId;
            }

            public void setItemId(long itemId) {
                this.itemId = itemId;
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

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class Season {
            @SerializedName("actor")
            private String actor;
            @SerializedName("badge")
            private String badge;
            @SerializedName("badge_info")
            private BadgeInfo badgeInfo;
            @SerializedName("badge_type")
            private long badgeType;
            @SerializedName("cover")
            private String cover;
            @SerializedName("from")
            private long from;
            @SerializedName("icon_font")
            private IconFont iconFont;
            @SerializedName("new_ep")
            private NewEp newEp;
            @SerializedName("rating")
            private Rating rating;
            @SerializedName("report")
            private Report report;
            @SerializedName("rights")
            private Rights rights;
            @SerializedName("season_id")
            private long seasonId;
            @SerializedName("season_type")
            private long seasonType;
            @SerializedName("stat")
            private Stat stat;
            @SerializedName("styles")
            private List<Styles> styles;
            @SerializedName("subtitle")
            private String subtitle;
            @SerializedName("title")
            private String title;
            @SerializedName("url")
            private String url;
            @SerializedName("user_status")
            private UserStatus userStatus;

            public String getActor() {
                return actor;
            }

            public void setActor(String actor) {
                this.actor = actor;
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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public long getFrom() {
                return from;
            }

            public void setFrom(long from) {
                this.from = from;
            }

            public IconFont getIconFont() {
                return iconFont;
            }

            public void setIconFont(IconFont iconFont) {
                this.iconFont = iconFont;
            }

            public NewEp getNewEp() {
                return newEp;
            }

            public void setNewEp(NewEp newEp) {
                this.newEp = newEp;
            }

            public Rating getRating() {
                return rating;
            }

            public void setRating(Rating rating) {
                this.rating = rating;
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

            public long getSeasonId() {
                return seasonId;
            }

            public void setSeasonId(long seasonId) {
                this.seasonId = seasonId;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public UserStatus getUserStatus() {
                return userStatus;
            }

            public void setUserStatus(UserStatus userStatus) {
                this.userStatus = userStatus;
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

            public static class NewEp {
                @SerializedName("cover")
                private String cover;
                @SerializedName("index_show")
                private String indexShow;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getIndexShow() {
                    return indexShow;
                }

                public void setIndexShow(String indexShow) {
                    this.indexShow = indexShow;
                }
            }

            public static class Rating {
                @SerializedName("count")
                private long count;
                @SerializedName("score")
                private double score;

                public long getCount() {
                    return count;
                }

                public void setCount(long count) {
                    this.count = count;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }
            }

            public static class Report {
                @SerializedName("is_wtgt")
                private long isWtgt;
                @SerializedName("seriesId")
                private long seriesId;

                public long getIsWtgt() {
                    return isWtgt;
                }

                public void setIsWtgt(long isWtgt) {
                    this.isWtgt = isWtgt;
                }

                public long getSeriesId() {
                    return seriesId;
                }

                public void setSeriesId(long seriesId) {
                    this.seriesId = seriesId;
                }
            }

            public static class Rights {
                @SerializedName("can_watch")
                private long canWatch;
                @SerializedName("resource")
                private String resource;

                public long getCanWatch() {
                    return canWatch;
                }

                public void setCanWatch(long canWatch) {
                    this.canWatch = canWatch;
                }

                public String getResource() {
                    return resource;
                }

                public void setResource(String resource) {
                    this.resource = resource;
                }
            }

            public static class Stat {
                @SerializedName("danmaku")
                private long danmaku;
                @SerializedName("follow")
                private long follow;
                @SerializedName("view")
                private long view;

                public long getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(long danmaku) {
                    this.danmaku = danmaku;
                }

                public long getFollow() {
                    return follow;
                }

                public void setFollow(long follow) {
                    this.follow = follow;
                }

                public long getView() {
                    return view;
                }

                public void setView(long view) {
                    this.view = view;
                }
            }

            public static class UserStatus {
                @SerializedName("follow")
                private long follow;

                public long getFollow() {
                    return follow;
                }

                public void setFollow(long follow) {
                    this.follow = follow;
                }
            }

            public static class Styles {
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
        }
    }
}
