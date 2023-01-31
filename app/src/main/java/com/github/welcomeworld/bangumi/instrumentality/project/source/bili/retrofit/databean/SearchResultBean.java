package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class SearchResultBean {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private int ttl;
    @SerializedName("data")
    private DataBean data;

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

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
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
        private int page;
        @SerializedName("items")
        private ItemsBean items;
        @SerializedName("array")
        private int array;
        @SerializedName("attribute")
        private int attribute;
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

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public int getArray() {
            return array;
        }

        public void setArray(int array) {
            this.array = array;
        }

        public int getAttribute() {
            return attribute;
        }

        public void setAttribute(int attribute) {
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
            private int total;
            @SerializedName("pages")
            private int pages;
            @SerializedName("type")
            private int type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class ItemBean {
            @SerializedName("trackid")
            private String trackid;
            @SerializedName("linktype")
            private String linktype;
            @SerializedName("position")
            private int position;
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
            private int fans;
            @SerializedName("level")
            private int level;
            @SerializedName("official_verify")
            private OfficialVerifyBean officialVerify;
            @SerializedName("is_up")
            private boolean isUp;
            @SerializedName("archives")
            private int archives;
            @SerializedName("mid")
            private int mid;
            @SerializedName("play")
            private int play;
            @SerializedName("danmaku")
            private int danmaku;
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

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
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

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
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

            public int getArchives() {
                return archives;
            }

            public void setArchives(int archives) {
                this.archives = archives;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
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
                private int type;
                @SerializedName("desc")
                private String desc;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
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
                private int play;
                @SerializedName("danmaku")
                private int danmaku;
                @SerializedName("ctime")
                private int ctime;
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

                public int getCtime() {
                    return ctime;
                }

                public void setCtime(int ctime) {
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
                private int bgStyle;

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

                public int getBgStyle() {
                    return bgStyle;
                }

                public void setBgStyle(int bgStyle) {
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
