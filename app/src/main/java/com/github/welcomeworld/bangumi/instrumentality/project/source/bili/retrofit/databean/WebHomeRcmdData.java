package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class WebHomeRcmdData {

    /**
     * code
     */
    @SerializedName("code")
    private long code;
    /**
     * message
     */
    @SerializedName("message")
    private String message;
    /**
     * ttl
     */
    @SerializedName("ttl")
    private long ttl;
    /**
     * data
     */
    @SerializedName("data")
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * item
         */
        @SerializedName("item")
        private List<Item> item;
        /**
         * sideBarColumn
         */
        @SerializedName("side_bar_column")
        private List<SideBarColumn> sideBarColumn;
        /**
         * businessCard
         */
        @SerializedName("business_card")
        private Object businessCard;
        /**
         * floorInfo
         */
        @SerializedName("floor_info")
        private Object floorInfo;
        /**
         * userFeature
         */
        @SerializedName("user_feature")
        private Object userFeature;
        /**
         * preloadExposePct
         */
        @SerializedName("preload_expose_pct")
        private double preloadExposePct;
        /**
         * preloadFloorExposePct
         */
        @SerializedName("preload_floor_expose_pct")
        private double preloadFloorExposePct;
        /**
         * mid
         */
        @SerializedName("mid")
        private long mid;

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }

        public List<SideBarColumn> getSideBarColumn() {
            return sideBarColumn;
        }

        public void setSideBarColumn(List<SideBarColumn> sideBarColumn) {
            this.sideBarColumn = sideBarColumn;
        }

        public Object getBusinessCard() {
            return businessCard;
        }

        public void setBusinessCard(Object businessCard) {
            this.businessCard = businessCard;
        }

        public Object getFloorInfo() {
            return floorInfo;
        }

        public void setFloorInfo(Object floorInfo) {
            this.floorInfo = floorInfo;
        }

        public Object getUserFeature() {
            return userFeature;
        }

        public void setUserFeature(Object userFeature) {
            this.userFeature = userFeature;
        }

        public double getPreloadExposePct() {
            return preloadExposePct;
        }

        public void setPreloadExposePct(double preloadExposePct) {
            this.preloadExposePct = preloadExposePct;
        }

        public double getPreloadFloorExposePct() {
            return preloadFloorExposePct;
        }

        public void setPreloadFloorExposePct(double preloadFloorExposePct) {
            this.preloadFloorExposePct = preloadFloorExposePct;
        }

        public long getMid() {
            return mid;
        }

        public void setMid(long mid) {
            this.mid = mid;
        }

        public static class Item {
            /**
             * id
             */
            @SerializedName("id")
            private long id;
            /**
             * bvid
             */
            @SerializedName("bvid")
            private String bvid;
            /**
             * cid
             */
            @SerializedName("cid")
            private long cid;
            /**
             * gotoX
             */
            @SerializedName("goto")
            private String gotoX;
            /**
             * uri
             */
            @SerializedName("uri")
            private String uri;
            /**
             * pic
             */
            @SerializedName("pic")
            private String pic;
            /**
             * title
             */
            @SerializedName("title")
            private String title;
            /**
             * duration
             */
            @SerializedName("duration")
            private long duration;
            /**
             * pubdate
             */
            @SerializedName("pubdate")
            private long pubdate;
            /**
             * owner
             */
            @SerializedName("owner")
            private Owner owner;
            /**
             * stat
             */
            @SerializedName("stat")
            private Stat stat;
            /**
             * avFeature
             */
            @SerializedName("av_feature")
            private Object avFeature;
            /**
             * isFollowed
             */
            @SerializedName("is_followed")
            private long isFollowed;
            /**
             * rcmdReason
             */
            @SerializedName("rcmd_reason")
            private RcmdReason rcmdReason;
            /**
             * showInfo
             */
            @SerializedName("show_info")
            private long showInfo;
            /**
             * trackId
             */
            @SerializedName("track_id")
            private String trackId;
            /**
             * pos
             */
            @SerializedName("pos")
            private long pos;
            /**
             * roomInfo
             */
            @SerializedName("room_info")
            private Object roomInfo;
            /**
             * ogvInfo
             */
            @SerializedName("ogv_info")
            private Object ogvInfo;
            /**
             * businessInfo
             */
            @SerializedName("business_info")
            private BusinessInfo businessInfo;
            /**
             * isStock
             */
            @SerializedName("is_stock")
            private long isStock;
            /**
             * comicDetailInfo
             */
            @SerializedName("comic_detail_info")
            private Object comicDetailInfo;
            /**
             * gameDetailInfo
             */
            @SerializedName("game_detail_info")
            private Object gameDetailInfo;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
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

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
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

            public long getDuration() {
                return duration;
            }

            public void setDuration(long duration) {
                this.duration = duration;
            }

            public long getPubdate() {
                return pubdate;
            }

            public void setPubdate(long pubdate) {
                this.pubdate = pubdate;
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

            public Object getAvFeature() {
                return avFeature;
            }

            public void setAvFeature(Object avFeature) {
                this.avFeature = avFeature;
            }

            public long getIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(long isFollowed) {
                this.isFollowed = isFollowed;
            }

            public RcmdReason getRcmdReason() {
                return rcmdReason;
            }

            public void setRcmdReason(RcmdReason rcmdReason) {
                this.rcmdReason = rcmdReason;
            }

            public long getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(long showInfo) {
                this.showInfo = showInfo;
            }

            public String getTrackId() {
                return trackId;
            }

            public void setTrackId(String trackId) {
                this.trackId = trackId;
            }

            public long getPos() {
                return pos;
            }

            public void setPos(long pos) {
                this.pos = pos;
            }

            public Object getRoomInfo() {
                return roomInfo;
            }

            public void setRoomInfo(Object roomInfo) {
                this.roomInfo = roomInfo;
            }

            public Object getOgvInfo() {
                return ogvInfo;
            }

            public void setOgvInfo(Object ogvInfo) {
                this.ogvInfo = ogvInfo;
            }

            public BusinessInfo getBusinessInfo() {
                return businessInfo;
            }

            public void setBusinessInfo(BusinessInfo businessInfo) {
                this.businessInfo = businessInfo;
            }

            public long getIsStock() {
                return isStock;
            }

            public void setIsStock(long isStock) {
                this.isStock = isStock;
            }

            public Object getComicDetailInfo() {
                return comicDetailInfo;
            }

            public void setComicDetailInfo(Object comicDetailInfo) {
                this.comicDetailInfo = comicDetailInfo;
            }

            public Object getGameDetailInfo() {
                return gameDetailInfo;
            }

            public void setGameDetailInfo(Object gameDetailInfo) {
                this.gameDetailInfo = gameDetailInfo;
            }

            public static class Owner {
                /**
                 * mid
                 */
                @SerializedName("mid")
                private long mid;
                /**
                 * name
                 */
                @SerializedName("name")
                private String name;
                /**
                 * face
                 */
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
                /**
                 * view
                 */
                @SerializedName("view")
                private long view;
                /**
                 * like
                 */
                @SerializedName("like")
                private long like;
                /**
                 * danmaku
                 */
                @SerializedName("danmaku")
                private long danmaku;

                public long getView() {
                    return view;
                }

                public void setView(long view) {
                    this.view = view;
                }

                public long getLike() {
                    return like;
                }

                public void setLike(long like) {
                    this.like = like;
                }

                public long getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(long danmaku) {
                    this.danmaku = danmaku;
                }
            }

            public static class RcmdReason {
                /**
                 * content
                 */
                @SerializedName("content")
                private String content;
                /**
                 * reasonType
                 */
                @SerializedName("reason_type")
                private long reasonType;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public long getReasonType() {
                    return reasonType;
                }

                public void setReasonType(long reasonType) {
                    this.reasonType = reasonType;
                }
            }

            public static class BusinessInfo {
                @SerializedName("src_id")
                private long srcId;

                public long getSrcId() {
                    return srcId;
                }

                public void setSrcId(long srcId) {
                    this.srcId = srcId;
                }
            }
        }

        public static class SideBarColumn {
            /**
             * id
             */
            @SerializedName("id")
            private long id;
            /**
             * gotoX
             */
            @SerializedName("goto")
            private String gotoX;
            /**
             * trackId
             */
            @SerializedName("track_id")
            private String trackId;
            /**
             * pos
             */
            @SerializedName("pos")
            private long pos;
            /**
             * cardType
             */
            @SerializedName("card_type")
            private String cardType;
            /**
             * cardTypeEn
             */
            @SerializedName("card_type_en")
            private String cardTypeEn;
            /**
             * cover
             */
            @SerializedName("cover")
            private String cover;
            /**
             * url
             */
            @SerializedName("url")
            private String url;
            /**
             * title
             */
            @SerializedName("title")
            private String title;
            /**
             * subTitle
             */
            @SerializedName("sub_title")
            private String subTitle;
            /**
             * duration
             */
            @SerializedName("duration")
            private long duration;
            /**
             * stats
             */
            @SerializedName("stats")
            private Stats stats;
            /**
             * newEp
             */
            @SerializedName("new_ep")
            private NewEp newEp;
            /**
             * styles
             */
            @SerializedName("styles")
            private Object styles;
            /**
             * comic
             */
            @SerializedName("comic")
            private Object comic;
            /**
             * producer
             */
            @SerializedName("producer")
            private List<Producer> producer;
            /**
             * source
             */
            @SerializedName("source")
            private String source;
            /**
             * avFeature
             */
            @SerializedName("av_feature")
            private Object avFeature;
            /**
             * isRec
             */
            @SerializedName("is_rec")
            private long isRec;
            /**
             * isFinish
             */
            @SerializedName("is_finish")
            private long isFinish;
            /**
             * isStarted
             */
            @SerializedName("is_started")
            private long isStarted;
            /**
             * isPlay
             */
            @SerializedName("is_play")
            private long isPlay;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public String getTrackId() {
                return trackId;
            }

            public void setTrackId(String trackId) {
                this.trackId = trackId;
            }

            public long getPos() {
                return pos;
            }

            public void setPos(long pos) {
                this.pos = pos;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getCardTypeEn() {
                return cardTypeEn;
            }

            public void setCardTypeEn(String cardTypeEn) {
                this.cardTypeEn = cardTypeEn;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public long getDuration() {
                return duration;
            }

            public void setDuration(long duration) {
                this.duration = duration;
            }

            public Stats getStats() {
                return stats;
            }

            public void setStats(Stats stats) {
                this.stats = stats;
            }

            public NewEp getNewEp() {
                return newEp;
            }

            public void setNewEp(NewEp newEp) {
                this.newEp = newEp;
            }

            public Object getStyles() {
                return styles;
            }

            public void setStyles(Object styles) {
                this.styles = styles;
            }

            public Object getComic() {
                return comic;
            }

            public void setComic(Object comic) {
                this.comic = comic;
            }

            public List<Producer> getProducer() {
                return producer;
            }

            public void setProducer(List<Producer> producer) {
                this.producer = producer;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public Object getAvFeature() {
                return avFeature;
            }

            public void setAvFeature(Object avFeature) {
                this.avFeature = avFeature;
            }

            public long getIsRec() {
                return isRec;
            }

            public void setIsRec(long isRec) {
                this.isRec = isRec;
            }

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

            public long getIsPlay() {
                return isPlay;
            }

            public void setIsPlay(long isPlay) {
                this.isPlay = isPlay;
            }

            public static class Stats {
                /**
                 * follow
                 */
                @SerializedName("follow")
                private long follow;
                /**
                 * view
                 */
                @SerializedName("view")
                private long view;
                /**
                 * danmaku
                 */
                @SerializedName("danmaku")
                private long danmaku;
                /**
                 * reply
                 */
                @SerializedName("reply")
                private long reply;
                /**
                 * coin
                 */
                @SerializedName("coin")
                private long coin;
                /**
                 * likes
                 */
                @SerializedName("likes")
                private long likes;
                /**
                 * favorite
                 */
                @SerializedName("favorite")
                private long favorite;

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

                public long getCoin() {
                    return coin;
                }

                public void setCoin(long coin) {
                    this.coin = coin;
                }

                public long getLikes() {
                    return likes;
                }

                public void setLikes(long likes) {
                    this.likes = likes;
                }

                public long getFavorite() {
                    return favorite;
                }

                public void setFavorite(long favorite) {
                    this.favorite = favorite;
                }
            }

            public static class NewEp {
                /**
                 * id
                 */
                @SerializedName("id")
                private long id;
                /**
                 * cover
                 */
                @SerializedName("cover")
                private String cover;
                /**
                 * title
                 */
                @SerializedName("title")
                private String title;
                /**
                 * duration
                 */
                @SerializedName("duration")
                private long duration;
                /**
                 * dayOfWeek
                 */
                @SerializedName("day_of_week")
                private long dayOfWeek;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public long getDuration() {
                    return duration;
                }

                public void setDuration(long duration) {
                    this.duration = duration;
                }

                public long getDayOfWeek() {
                    return dayOfWeek;
                }

                public void setDayOfWeek(long dayOfWeek) {
                    this.dayOfWeek = dayOfWeek;
                }
            }

            public static class Producer {
                /**
                 * mid
                 */
                @SerializedName("mid")
                private long mid;
                /**
                 * name
                 */
                @SerializedName("name")
                private String name;
                /**
                 * type
                 */
                @SerializedName("type")
                private long type;
                /**
                 * isContribute
                 */
                @SerializedName("is_contribute")
                private long isContribute;

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

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }

                public long getIsContribute() {
                    return isContribute;
                }

                public void setIsContribute(long isContribute) {
                    this.isContribute = isContribute;
                }
            }
        }
    }
}
