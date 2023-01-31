package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class SearchResultBean {

    @SerializedName("code")
    private long code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private long ttl;
    @SerializedName("data")
    private DataBean data;

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

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("trackid")
        private String trackid;
        @SerializedName("page")
        private long page;
        @SerializedName("items")
        private ItemsBean items;
        @SerializedName("array")
        private long array;
        @SerializedName("attribute")
        private long attribute;
        @SerializedName("nav")
        private List<NavBean> nav;
        @SerializedName("item")
        private List<ItemBean> item;

        public String getTrackid() {
            return trackid;
        }

        public void setTrackid(String trackid) {
            this.trackid = trackid;
        }

        public long getPage() {
            return page;
        }

        public void setPage(long page) {
            this.page = page;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public long getArray() {
            return array;
        }

        public void setArray(long array) {
            this.array = array;
        }

        public long getAttribute() {
            return attribute;
        }

        public void setAttribute(long attribute) {
            this.attribute = attribute;
        }

        public List<NavBean> getNav() {
            return nav;
        }

        public void setNav(List<NavBean> nav) {
            this.nav = nav;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemsBean {
        }

        public static class NavBean {
            @SerializedName("name")
            private String name;
            @SerializedName("total")
            private long total;
            @SerializedName("pages")
            private long pages;
            @SerializedName("type")
            private long type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getTotal() {
                return total;
            }

            public void setTotal(long total) {
                this.total = total;
            }

            public long getPages() {
                return pages;
            }

            public void setPages(long pages) {
                this.pages = pages;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }
        }

        public static class ItemBean {
            @SerializedName("trackid")
            private String trackid;
            @SerializedName("linktype")
            private String linktype;
            @SerializedName("position")
            private long position;
            @SerializedName("title")
            private String title;
            @SerializedName("cover")
            private String cover;
            @SerializedName("uri")
            private String uri;
            @SerializedName("param")
            private String param;
            @SerializedName("goto")
            private String gotoX;
            @SerializedName("sign")
            private String sign;
            @SerializedName("fans")
            private long fans;
            @SerializedName("level")
            private long level;
            @SerializedName("official_verify")
            private OfficialVerifyBean officialVerify;
            @SerializedName("is_up")
            private boolean isUp;
            @SerializedName("archives")
            private long archives;
            @SerializedName("mid")
            private long mid;
            @SerializedName("play")
            private long play;
            @SerializedName("danmaku")
            private long danmaku;
            @SerializedName("author")
            private String author;
            @SerializedName("desc")
            private String desc;
            @SerializedName("duration")
            private String duration;
            @SerializedName("face")
            private String face;
            @SerializedName("av_items")
            private List<AvItemsBean> avItems;
            @SerializedName("rec_tags")
            private List<String> recTags;
            @SerializedName("new_rec_tags")
            private List<NewRecTagsBean> newRecTags;
            @SerializedName("list")
            private List<ListBean> list;

            public String getTrackid() {
                return trackid;
            }

            public void setTrackid(String trackid) {
                this.trackid = trackid;
            }

            public String getLinktype() {
                return linktype;
            }

            public void setLinktype(String linktype) {
                this.linktype = linktype;
            }

            public long getPosition() {
                return position;
            }

            public void setPosition(long position) {
                this.position = position;
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

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public long getFans() {
                return fans;
            }

            public void setFans(long fans) {
                this.fans = fans;
            }

            public long getLevel() {
                return level;
            }

            public void setLevel(long level) {
                this.level = level;
            }

            public OfficialVerifyBean getOfficialVerify() {
                return officialVerify;
            }

            public void setOfficialVerify(OfficialVerifyBean officialVerify) {
                this.officialVerify = officialVerify;
            }

            public boolean isIsUp() {
                return isUp;
            }

            public void setIsUp(boolean isUp) {
                this.isUp = isUp;
            }

            public long getArchives() {
                return archives;
            }

            public void setArchives(long archives) {
                this.archives = archives;
            }

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
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

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public List<AvItemsBean> getAvItems() {
                return avItems;
            }

            public void setAvItems(List<AvItemsBean> avItems) {
                this.avItems = avItems;
            }

            public List<String> getRecTags() {
                return recTags;
            }

            public void setRecTags(List<String> recTags) {
                this.recTags = recTags;
            }

            public List<NewRecTagsBean> getNewRecTags() {
                return newRecTags;
            }

            public void setNewRecTags(List<NewRecTagsBean> newRecTags) {
                this.newRecTags = newRecTags;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class OfficialVerifyBean {
                @SerializedName("type")
                private long type;
                @SerializedName("desc")
                private String desc;

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }

            public static class AvItemsBean {
                @SerializedName("title")
                private String title;
                @SerializedName("cover")
                private String cover;
                @SerializedName("uri")
                private String uri;
                @SerializedName("param")
                private String param;
                @SerializedName("goto")
                private String gotoX;
                @SerializedName("play")
                private long play;
                @SerializedName("danmaku")
                private long danmaku;
                @SerializedName("ctime")
                private long ctime;
                @SerializedName("duration")
                private String duration;

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

                public long getCtime() {
                    return ctime;
                }

                public void setCtime(long ctime) {
                    this.ctime = ctime;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                @Override
                public String toString() {
                    return "AvItemsBean{" +
                            "title='" + title + '\'' +
                            ", cover='" + cover + '\'' +
                            ", uri='" + uri + '\'' +
                            ", param='" + param + '\'' +
                            ", gotoX='" + gotoX + '\'' +
                            ", play=" + play +
                            ", danmaku=" + danmaku +
                            ", ctime=" + ctime +
                            ", duration='" + duration + '\'' +
                            '}';
                }
            }

            public static class NewRecTagsBean {
                @SerializedName("text")
                private String text;
                @SerializedName("text_color")
                private String textColor;
                @SerializedName("text_color_night")
                private String textColorNight;
                @SerializedName("bg_color")
                private String bgColor;
                @SerializedName("bg_color_night")
                private String bgColorNight;
                @SerializedName("border_color")
                private String borderColor;
                @SerializedName("border_color_night")
                private String borderColorNight;
                @SerializedName("bg_style")
                private long bgStyle;

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

                public String getTextColorNight() {
                    return textColorNight;
                }

                public void setTextColorNight(String textColorNight) {
                    this.textColorNight = textColorNight;
                }

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

                public String getBorderColor() {
                    return borderColor;
                }

                public void setBorderColor(String borderColor) {
                    this.borderColor = borderColor;
                }

                public String getBorderColorNight() {
                    return borderColorNight;
                }

                public void setBorderColorNight(String borderColorNight) {
                    this.borderColorNight = borderColorNight;
                }

                public long getBgStyle() {
                    return bgStyle;
                }

                public void setBgStyle(long bgStyle) {
                    this.bgStyle = bgStyle;
                }

                @Override
                public String toString() {
                    return "NewRecTagsBean{" +
                            "text='" + text + '\'' +
                            ", textColor='" + textColor + '\'' +
                            ", textColorNight='" + textColorNight + '\'' +
                            ", bgColor='" + bgColor + '\'' +
                            ", bgColorNight='" + bgColorNight + '\'' +
                            ", borderColor='" + borderColor + '\'' +
                            ", borderColorNight='" + borderColorNight + '\'' +
                            ", bgStyle=" + bgStyle +
                            '}';
                }
            }

            public static class ListBean {
                @SerializedName("title")
                private String title;
                @SerializedName("param")
                private String param;
                @SerializedName("type")
                private String type;
                @SerializedName("from_source")
                private String fromSource;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getFromSource() {
                    return fromSource;
                }

                public void setFromSource(String fromSource) {
                    this.fromSource = fromSource;
                }

                @Override
                public String toString() {
                    return "ListBean{" +
                            "title='" + title + '\'' +
                            ", param='" + param + '\'' +
                            ", type='" + type + '\'' +
                            ", fromSource='" + fromSource + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ItemBean{" +
                        "trackid='" + trackid + '\'' +
                        ", linktype='" + linktype + '\'' +
                        ", position=" + position +
                        ", title='" + title + '\'' +
                        ", cover='" + cover + '\'' +
                        ", uri='" + uri + '\'' +
                        ", param='" + param + '\'' +
                        ", gotoX='" + gotoX + '\'' +
                        ", sign='" + sign + '\'' +
                        ", fans=" + fans +
                        ", level=" + level +
                        ", officialVerify=" + officialVerify +
                        ", isUp=" + isUp +
                        ", archives=" + archives +
                        ", mid=" + mid +
                        ", play=" + play +
                        ", danmaku=" + danmaku +
                        ", author='" + author + '\'' +
                        ", desc='" + desc + '\'' +
                        ", duration='" + duration + '\'' +
                        ", face='" + face + '\'' +
                        ", avItems=" + avItems +
                        ", recTags=" + recTags +
                        ", newRecTags=" + newRecTags +
                        ", list=" + list +
                        '}';
            }
        }
    }
}
