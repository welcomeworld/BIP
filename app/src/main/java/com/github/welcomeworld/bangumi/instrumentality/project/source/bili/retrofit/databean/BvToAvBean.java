package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class BvToAvBean {

    @SerializedName("code")
    private Long code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private Long ttl;
    @SerializedName("data")
    private Data data;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("bvid")
        private String bvid;
        @SerializedName("aid")
        private Long aid;
        @SerializedName("videos")
        private Long videos;
        @SerializedName("tid")
        private Long tid;
        @SerializedName("tname")
        private String tname;
        @SerializedName("copyright")
        private Long copyright;
        @SerializedName("pic")
        private String pic;
        @SerializedName("title")
        private String title;
        @SerializedName("pubdate")
        private Long pubdate;
        @SerializedName("ctime")
        private Long ctime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("desc_v2")
        private List<DescV2> descV2;
        @SerializedName("state")
        private Long state;
        @SerializedName("duration")
        private Long duration;
        @SerializedName("mission_id")
        private Long missionId;
        @SerializedName("rights")
        private Rights rights;
        @SerializedName("owner")
        private Owner owner;
        @SerializedName("stat")
        private Stat stat;
        @SerializedName("dynamic")
        private String dynamic;
        @SerializedName("cid")
        private Long cid;
        @SerializedName("dimension")
        private Dimension dimension;
        @SerializedName("season_id")
        private Long seasonId;
        @SerializedName("no_cache")
        private Boolean noCache;
        @SerializedName("pages")
        private List<Pages> pages;
        @SerializedName("subtitle")
        private Subtitle subtitle;
        @SerializedName("ugc_season")
        private UgcSeason ugcSeason;
        @SerializedName("user_garb")
        private UserGarb userGarb;

        public String getBvid() {
            return bvid;
        }

        public void setBvid(String bvid) {
            this.bvid = bvid;
        }

        public Long getAid() {
            return aid;
        }

        public void setAid(Long aid) {
            this.aid = aid;
        }

        public Long getVideos() {
            return videos;
        }

        public void setVideos(Long videos) {
            this.videos = videos;
        }

        public Long getTid() {
            return tid;
        }

        public void setTid(Long tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public Long getCopyright() {
            return copyright;
        }

        public void setCopyright(Long copyright) {
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

        public Long getPubdate() {
            return pubdate;
        }

        public void setPubdate(Long pubdate) {
            this.pubdate = pubdate;
        }

        public Long getCtime() {
            return ctime;
        }

        public void setCtime(Long ctime) {
            this.ctime = ctime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<DescV2> getDescV2() {
            return descV2;
        }

        public void setDescV2(List<DescV2> descV2) {
            this.descV2 = descV2;
        }

        public Long getState() {
            return state;
        }

        public void setState(Long state) {
            this.state = state;
        }

        public Long getDuration() {
            return duration;
        }

        public void setDuration(Long duration) {
            this.duration = duration;
        }

        public Long getMissionId() {
            return missionId;
        }

        public void setMissionId(Long missionId) {
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

        public Long getCid() {
            return cid;
        }

        public void setCid(Long cid) {
            this.cid = cid;
        }

        public Dimension getDimension() {
            return dimension;
        }

        public void setDimension(Dimension dimension) {
            this.dimension = dimension;
        }

        public Long getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(Long seasonId) {
            this.seasonId = seasonId;
        }

        public Boolean getNoCache() {
            return noCache;
        }

        public void setNoCache(Boolean noCache) {
            this.noCache = noCache;
        }

        public List<Pages> getPages() {
            return pages;
        }

        public void setPages(List<Pages> pages) {
            this.pages = pages;
        }

        public Subtitle getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(Subtitle subtitle) {
            this.subtitle = subtitle;
        }

        public UgcSeason getUgcSeason() {
            return ugcSeason;
        }

        public void setUgcSeason(UgcSeason ugcSeason) {
            this.ugcSeason = ugcSeason;
        }

        public UserGarb getUserGarb() {
            return userGarb;
        }

        public void setUserGarb(UserGarb userGarb) {
            this.userGarb = userGarb;
        }

        public static class Rights {
            @SerializedName("bp")
            private Long bp;
            @SerializedName("elec")
            private Long elec;
            @SerializedName("download")
            private Long download;
            @SerializedName("movie")
            private Long movie;
            @SerializedName("pay")
            private Long pay;
            @SerializedName("hd5")
            private Long hd5;
            @SerializedName("no_reprint")
            private Long noReprint;
            @SerializedName("autoplay")
            private Long autoplay;
            @SerializedName("ugc_pay")
            private Long ugcPay;
            @SerializedName("is_cooperation")
            private Long isCooperation;
            @SerializedName("ugc_pay_preview")
            private Long ugcPayPreview;
            @SerializedName("no_background")
            private Long noBackground;
            @SerializedName("clean_mode")
            private Long cleanMode;
            @SerializedName("is_stein_gate")
            private Long isSteinGate;

            public Long getBp() {
                return bp;
            }

            public void setBp(Long bp) {
                this.bp = bp;
            }

            public Long getElec() {
                return elec;
            }

            public void setElec(Long elec) {
                this.elec = elec;
            }

            public Long getDownload() {
                return download;
            }

            public void setDownload(Long download) {
                this.download = download;
            }

            public Long getMovie() {
                return movie;
            }

            public void setMovie(Long movie) {
                this.movie = movie;
            }

            public Long getPay() {
                return pay;
            }

            public void setPay(Long pay) {
                this.pay = pay;
            }

            public Long getHd5() {
                return hd5;
            }

            public void setHd5(Long hd5) {
                this.hd5 = hd5;
            }

            public Long getNoReprint() {
                return noReprint;
            }

            public void setNoReprint(Long noReprint) {
                this.noReprint = noReprint;
            }

            public Long getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(Long autoplay) {
                this.autoplay = autoplay;
            }

            public Long getUgcPay() {
                return ugcPay;
            }

            public void setUgcPay(Long ugcPay) {
                this.ugcPay = ugcPay;
            }

            public Long getIsCooperation() {
                return isCooperation;
            }

            public void setIsCooperation(Long isCooperation) {
                this.isCooperation = isCooperation;
            }

            public Long getUgcPayPreview() {
                return ugcPayPreview;
            }

            public void setUgcPayPreview(Long ugcPayPreview) {
                this.ugcPayPreview = ugcPayPreview;
            }

            public Long getNoBackground() {
                return noBackground;
            }

            public void setNoBackground(Long noBackground) {
                this.noBackground = noBackground;
            }

            public Long getCleanMode() {
                return cleanMode;
            }

            public void setCleanMode(Long cleanMode) {
                this.cleanMode = cleanMode;
            }

            public Long getIsSteinGate() {
                return isSteinGate;
            }

            public void setIsSteinGate(Long isSteinGate) {
                this.isSteinGate = isSteinGate;
            }
        }

        public static class Owner {
            @SerializedName("mid")
            private Long mid;
            @SerializedName("name")
            private String name;
            @SerializedName("face")
            private String face;

            public Long getMid() {
                return mid;
            }

            public void setMid(Long mid) {
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
            private Long aid;
            @SerializedName("view")
            private Long view;
            @SerializedName("danmaku")
            private Long danmaku;
            @SerializedName("reply")
            private Long reply;
            @SerializedName("favorite")
            private Long favorite;
            @SerializedName("coin")
            private Long coin;
            @SerializedName("share")
            private Long share;
            @SerializedName("now_rank")
            private Long nowRank;
            @SerializedName("his_rank")
            private Long hisRank;
            @SerializedName("like")
            private Long like;
            @SerializedName("dislike")
            private Long dislike;
            @SerializedName("evaluation")
            private String evaluation;
            @SerializedName("argue_msg")
            private String argueMsg;

            public Long getAid() {
                return aid;
            }

            public void setAid(Long aid) {
                this.aid = aid;
            }

            public Long getView() {
                return view;
            }

            public void setView(Long view) {
                this.view = view;
            }

            public Long getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(Long danmaku) {
                this.danmaku = danmaku;
            }

            public Long getReply() {
                return reply;
            }

            public void setReply(Long reply) {
                this.reply = reply;
            }

            public Long getFavorite() {
                return favorite;
            }

            public void setFavorite(Long favorite) {
                this.favorite = favorite;
            }

            public Long getCoin() {
                return coin;
            }

            public void setCoin(Long coin) {
                this.coin = coin;
            }

            public Long getShare() {
                return share;
            }

            public void setShare(Long share) {
                this.share = share;
            }

            public Long getNowRank() {
                return nowRank;
            }

            public void setNowRank(Long nowRank) {
                this.nowRank = nowRank;
            }

            public Long getHisRank() {
                return hisRank;
            }

            public void setHisRank(Long hisRank) {
                this.hisRank = hisRank;
            }

            public Long getLike() {
                return like;
            }

            public void setLike(Long like) {
                this.like = like;
            }

            public Long getDislike() {
                return dislike;
            }

            public void setDislike(Long dislike) {
                this.dislike = dislike;
            }

            public String getEvaluation() {
                return evaluation;
            }

            public void setEvaluation(String evaluation) {
                this.evaluation = evaluation;
            }

            public String getArgueMsg() {
                return argueMsg;
            }

            public void setArgueMsg(String argueMsg) {
                this.argueMsg = argueMsg;
            }
        }

        public static class Dimension {
            @SerializedName("width")
            private Long width;
            @SerializedName("height")
            private Long height;
            @SerializedName("rotate")
            private Long rotate;

            public Long getWidth() {
                return width;
            }

            public void setWidth(Long width) {
                this.width = width;
            }

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
        }

        public static class Subtitle {
            @SerializedName("allow_submit")
            private Boolean allowSubmit;
            @SerializedName("list")
            private List<?> list;

            public Boolean getAllowSubmit() {
                return allowSubmit;
            }

            public void setAllowSubmit(Boolean allowSubmit) {
                this.allowSubmit = allowSubmit;
            }

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }
        }

        public static class UgcSeason {
            @SerializedName("id")
            private Long id;
            @SerializedName("title")
            private String title;
            @SerializedName("cover")
            private String cover;
            @SerializedName("mid")
            private Long mid;
            @SerializedName("intro")
            private String intro;
            @SerializedName("sign_state")
            private Long signState;
            @SerializedName("attribute")
            private Long attribute;
            @SerializedName("sections")
            private List<Sections> sections;
            @SerializedName("stat")
            private Stat stat;
            @SerializedName("ep_count")
            private Long epCount;
            @SerializedName("season_type")
            private Long seasonType;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
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

            public Long getMid() {
                return mid;
            }

            public void setMid(Long mid) {
                this.mid = mid;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public Long getSignState() {
                return signState;
            }

            public void setSignState(Long signState) {
                this.signState = signState;
            }

            public Long getAttribute() {
                return attribute;
            }

            public void setAttribute(Long attribute) {
                this.attribute = attribute;
            }

            public List<Sections> getSections() {
                return sections;
            }

            public void setSections(List<Sections> sections) {
                this.sections = sections;
            }

            public Stat getStat() {
                return stat;
            }

            public void setStat(Stat stat) {
                this.stat = stat;
            }

            public Long getEpCount() {
                return epCount;
            }

            public void setEpCount(Long epCount) {
                this.epCount = epCount;
            }

            public Long getSeasonType() {
                return seasonType;
            }

            public void setSeasonType(Long seasonType) {
                this.seasonType = seasonType;
            }

            public static class Stat {
                @SerializedName("season_id")
                private Long seasonId;
                @SerializedName("view")
                private Long view;
                @SerializedName("danmaku")
                private Long danmaku;
                @SerializedName("reply")
                private Long reply;
                @SerializedName("fav")
                private Long fav;
                @SerializedName("coin")
                private Long coin;
                @SerializedName("share")
                private Long share;
                @SerializedName("now_rank")
                private Long nowRank;
                @SerializedName("his_rank")
                private Long hisRank;
                @SerializedName("like")
                private Long like;

                public Long getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(Long seasonId) {
                    this.seasonId = seasonId;
                }

                public Long getView() {
                    return view;
                }

                public void setView(Long view) {
                    this.view = view;
                }

                public Long getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(Long danmaku) {
                    this.danmaku = danmaku;
                }

                public Long getReply() {
                    return reply;
                }

                public void setReply(Long reply) {
                    this.reply = reply;
                }

                public Long getFav() {
                    return fav;
                }

                public void setFav(Long fav) {
                    this.fav = fav;
                }

                public Long getCoin() {
                    return coin;
                }

                public void setCoin(Long coin) {
                    this.coin = coin;
                }

                public Long getShare() {
                    return share;
                }

                public void setShare(Long share) {
                    this.share = share;
                }

                public Long getNowRank() {
                    return nowRank;
                }

                public void setNowRank(Long nowRank) {
                    this.nowRank = nowRank;
                }

                public Long getHisRank() {
                    return hisRank;
                }

                public void setHisRank(Long hisRank) {
                    this.hisRank = hisRank;
                }

                public Long getLike() {
                    return like;
                }

                public void setLike(Long like) {
                    this.like = like;
                }
            }

            public static class Sections {
                @SerializedName("season_id")
                private Long seasonId;
                @SerializedName("id")
                private Long id;
                @SerializedName("title")
                private String title;
                @SerializedName("type")
                private Long type;
                @SerializedName("episodes")
                private List<Episodes> episodes;

                public Long getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(Long seasonId) {
                    this.seasonId = seasonId;
                }

                public Long getId() {
                    return id;
                }

                public void setId(Long id) {
                    this.id = id;
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

                public List<Episodes> getEpisodes() {
                    return episodes;
                }

                public void setEpisodes(List<Episodes> episodes) {
                    this.episodes = episodes;
                }

                public static class Episodes {
                    @SerializedName("season_id")
                    private Long seasonId;
                    @SerializedName("section_id")
                    private Long sectionId;
                    @SerializedName("id")
                    private Long id;
                    @SerializedName("aid")
                    private Long aid;
                    @SerializedName("cid")
                    private Long cid;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("attribute")
                    private Long attribute;
                    @SerializedName("arc")
                    private Arc arc;
                    @SerializedName("page")
                    private Page page;
                    @SerializedName("bvid")
                    private String bvid;

                    public Long getSeasonId() {
                        return seasonId;
                    }

                    public void setSeasonId(Long seasonId) {
                        this.seasonId = seasonId;
                    }

                    public Long getSectionId() {
                        return sectionId;
                    }

                    public void setSectionId(Long sectionId) {
                        this.sectionId = sectionId;
                    }

                    public Long getId() {
                        return id;
                    }

                    public void setId(Long id) {
                        this.id = id;
                    }

                    public Long getAid() {
                        return aid;
                    }

                    public void setAid(Long aid) {
                        this.aid = aid;
                    }

                    public Long getCid() {
                        return cid;
                    }

                    public void setCid(Long cid) {
                        this.cid = cid;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public Long getAttribute() {
                        return attribute;
                    }

                    public void setAttribute(Long attribute) {
                        this.attribute = attribute;
                    }

                    public Arc getArc() {
                        return arc;
                    }

                    public void setArc(Arc arc) {
                        this.arc = arc;
                    }

                    public Page getPage() {
                        return page;
                    }

                    public void setPage(Page page) {
                        this.page = page;
                    }

                    public String getBvid() {
                        return bvid;
                    }

                    public void setBvid(String bvid) {
                        this.bvid = bvid;
                    }

                    public static class Arc {
                        @SerializedName("aid")
                        private Long aid;
                        @SerializedName("videos")
                        private Long videos;
                        @SerializedName("type_id")
                        private Long typeId;
                        @SerializedName("type_name")
                        private String typeName;
                        @SerializedName("copyright")
                        private Long copyright;
                        @SerializedName("pic")
                        private String pic;
                        @SerializedName("title")
                        private String title;
                        @SerializedName("pubdate")
                        private Long pubdate;
                        @SerializedName("ctime")
                        private Long ctime;
                        @SerializedName("desc")
                        private String desc;
                        @SerializedName("state")
                        private Long state;
                        @SerializedName("duration")
                        private Long duration;
                        @SerializedName("rights")
                        private Rights rights;
                        @SerializedName("author")
                        private Author author;
                        @SerializedName("stat")
                        private Stat stat;
                        @SerializedName("dynamic")
                        private String dynamic;
                        @SerializedName("dimension")
                        private Dimension dimension;
                        @SerializedName("desc_v2")
                        private Object descV2;

                        public Long getAid() {
                            return aid;
                        }

                        public void setAid(Long aid) {
                            this.aid = aid;
                        }

                        public Long getVideos() {
                            return videos;
                        }

                        public void setVideos(Long videos) {
                            this.videos = videos;
                        }

                        public Long getTypeId() {
                            return typeId;
                        }

                        public void setTypeId(Long typeId) {
                            this.typeId = typeId;
                        }

                        public String getTypeName() {
                            return typeName;
                        }

                        public void setTypeName(String typeName) {
                            this.typeName = typeName;
                        }

                        public Long getCopyright() {
                            return copyright;
                        }

                        public void setCopyright(Long copyright) {
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

                        public Long getPubdate() {
                            return pubdate;
                        }

                        public void setPubdate(Long pubdate) {
                            this.pubdate = pubdate;
                        }

                        public Long getCtime() {
                            return ctime;
                        }

                        public void setCtime(Long ctime) {
                            this.ctime = ctime;
                        }

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }

                        public Long getState() {
                            return state;
                        }

                        public void setState(Long state) {
                            this.state = state;
                        }

                        public Long getDuration() {
                            return duration;
                        }

                        public void setDuration(Long duration) {
                            this.duration = duration;
                        }

                        public Rights getRights() {
                            return rights;
                        }

                        public void setRights(Rights rights) {
                            this.rights = rights;
                        }

                        public Author getAuthor() {
                            return author;
                        }

                        public void setAuthor(Author author) {
                            this.author = author;
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

                        public Dimension getDimension() {
                            return dimension;
                        }

                        public void setDimension(Dimension dimension) {
                            this.dimension = dimension;
                        }

                        public Object getDescV2() {
                            return descV2;
                        }

                        public void setDescV2(Object descV2) {
                            this.descV2 = descV2;
                        }

                        public static class Rights {
                            @SerializedName("bp")
                            private Long bp;
                            @SerializedName("elec")
                            private Long elec;
                            @SerializedName("download")
                            private Long download;
                            @SerializedName("movie")
                            private Long movie;
                            @SerializedName("pay")
                            private Long pay;
                            @SerializedName("hd5")
                            private Long hd5;
                            @SerializedName("no_reprint")
                            private Long noReprint;
                            @SerializedName("autoplay")
                            private Long autoplay;
                            @SerializedName("ugc_pay")
                            private Long ugcPay;
                            @SerializedName("is_cooperation")
                            private Long isCooperation;
                            @SerializedName("ugc_pay_preview")
                            private Long ugcPayPreview;

                            public Long getBp() {
                                return bp;
                            }

                            public void setBp(Long bp) {
                                this.bp = bp;
                            }

                            public Long getElec() {
                                return elec;
                            }

                            public void setElec(Long elec) {
                                this.elec = elec;
                            }

                            public Long getDownload() {
                                return download;
                            }

                            public void setDownload(Long download) {
                                this.download = download;
                            }

                            public Long getMovie() {
                                return movie;
                            }

                            public void setMovie(Long movie) {
                                this.movie = movie;
                            }

                            public Long getPay() {
                                return pay;
                            }

                            public void setPay(Long pay) {
                                this.pay = pay;
                            }

                            public Long getHd5() {
                                return hd5;
                            }

                            public void setHd5(Long hd5) {
                                this.hd5 = hd5;
                            }

                            public Long getNoReprint() {
                                return noReprint;
                            }

                            public void setNoReprint(Long noReprint) {
                                this.noReprint = noReprint;
                            }

                            public Long getAutoplay() {
                                return autoplay;
                            }

                            public void setAutoplay(Long autoplay) {
                                this.autoplay = autoplay;
                            }

                            public Long getUgcPay() {
                                return ugcPay;
                            }

                            public void setUgcPay(Long ugcPay) {
                                this.ugcPay = ugcPay;
                            }

                            public Long getIsCooperation() {
                                return isCooperation;
                            }

                            public void setIsCooperation(Long isCooperation) {
                                this.isCooperation = isCooperation;
                            }

                            public Long getUgcPayPreview() {
                                return ugcPayPreview;
                            }

                            public void setUgcPayPreview(Long ugcPayPreview) {
                                this.ugcPayPreview = ugcPayPreview;
                            }
                        }

                        public static class Author {
                            @SerializedName("mid")
                            private Long mid;
                            @SerializedName("name")
                            private String name;
                            @SerializedName("face")
                            private String face;

                            public Long getMid() {
                                return mid;
                            }

                            public void setMid(Long mid) {
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
                            private Long aid;
                            @SerializedName("view")
                            private Long view;
                            @SerializedName("danmaku")
                            private Long danmaku;
                            @SerializedName("reply")
                            private Long reply;
                            @SerializedName("fav")
                            private Long fav;
                            @SerializedName("coin")
                            private Long coin;
                            @SerializedName("share")
                            private Long share;
                            @SerializedName("now_rank")
                            private Long nowRank;
                            @SerializedName("his_rank")
                            private Long hisRank;
                            @SerializedName("like")
                            private Long like;
                            @SerializedName("dislike")
                            private Long dislike;
                            @SerializedName("evaluation")
                            private String evaluation;
                            @SerializedName("argue_msg")
                            private String argueMsg;

                            public Long getAid() {
                                return aid;
                            }

                            public void setAid(Long aid) {
                                this.aid = aid;
                            }

                            public Long getView() {
                                return view;
                            }

                            public void setView(Long view) {
                                this.view = view;
                            }

                            public Long getDanmaku() {
                                return danmaku;
                            }

                            public void setDanmaku(Long danmaku) {
                                this.danmaku = danmaku;
                            }

                            public Long getReply() {
                                return reply;
                            }

                            public void setReply(Long reply) {
                                this.reply = reply;
                            }

                            public Long getFav() {
                                return fav;
                            }

                            public void setFav(Long fav) {
                                this.fav = fav;
                            }

                            public Long getCoin() {
                                return coin;
                            }

                            public void setCoin(Long coin) {
                                this.coin = coin;
                            }

                            public Long getShare() {
                                return share;
                            }

                            public void setShare(Long share) {
                                this.share = share;
                            }

                            public Long getNowRank() {
                                return nowRank;
                            }

                            public void setNowRank(Long nowRank) {
                                this.nowRank = nowRank;
                            }

                            public Long getHisRank() {
                                return hisRank;
                            }

                            public void setHisRank(Long hisRank) {
                                this.hisRank = hisRank;
                            }

                            public Long getLike() {
                                return like;
                            }

                            public void setLike(Long like) {
                                this.like = like;
                            }

                            public Long getDislike() {
                                return dislike;
                            }

                            public void setDislike(Long dislike) {
                                this.dislike = dislike;
                            }

                            public String getEvaluation() {
                                return evaluation;
                            }

                            public void setEvaluation(String evaluation) {
                                this.evaluation = evaluation;
                            }

                            public String getArgueMsg() {
                                return argueMsg;
                            }

                            public void setArgueMsg(String argueMsg) {
                                this.argueMsg = argueMsg;
                            }
                        }

                        public static class Dimension {
                            @SerializedName("width")
                            private Long width;
                            @SerializedName("height")
                            private Long height;
                            @SerializedName("rotate")
                            private Long rotate;

                            public Long getWidth() {
                                return width;
                            }

                            public void setWidth(Long width) {
                                this.width = width;
                            }

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
                        }
                    }

                    public static class Page {
                        @SerializedName("cid")
                        private Long cid;
                        @SerializedName("page")
                        private Long page;
                        @SerializedName("from")
                        private String from;
                        @SerializedName("part")
                        private String part;
                        @SerializedName("duration")
                        private Long duration;
                        @SerializedName("vid")
                        private String vid;
                        @SerializedName("weblink")
                        private String weblink;
                        @SerializedName("dimension")
                        private Dimension dimension;

                        public Long getCid() {
                            return cid;
                        }

                        public void setCid(Long cid) {
                            this.cid = cid;
                        }

                        public Long getPage() {
                            return page;
                        }

                        public void setPage(Long page) {
                            this.page = page;
                        }

                        public String getFrom() {
                            return from;
                        }

                        public void setFrom(String from) {
                            this.from = from;
                        }

                        public String getPart() {
                            return part;
                        }

                        public void setPart(String part) {
                            this.part = part;
                        }

                        public Long getDuration() {
                            return duration;
                        }

                        public void setDuration(Long duration) {
                            this.duration = duration;
                        }

                        public String getVid() {
                            return vid;
                        }

                        public void setVid(String vid) {
                            this.vid = vid;
                        }

                        public String getWeblink() {
                            return weblink;
                        }

                        public void setWeblink(String weblink) {
                            this.weblink = weblink;
                        }

                        public Dimension getDimension() {
                            return dimension;
                        }

                        public void setDimension(Dimension dimension) {
                            this.dimension = dimension;
                        }

                        public static class Dimension {
                            @SerializedName("width")
                            private Long width;
                            @SerializedName("height")
                            private Long height;
                            @SerializedName("rotate")
                            private Long rotate;

                            public Long getWidth() {
                                return width;
                            }

                            public void setWidth(Long width) {
                                this.width = width;
                            }

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
                        }
                    }
                }
            }
        }

        public static class UserGarb {
            @SerializedName("url_image_ani_cut")
            private String urlImageAniCut;

            public String getUrlImageAniCut() {
                return urlImageAniCut;
            }

            public void setUrlImageAniCut(String urlImageAniCut) {
                this.urlImageAniCut = urlImageAniCut;
            }
        }

        public static class DescV2 {
            @SerializedName("raw_text")
            private String rawText;
            @SerializedName("type")
            private Long type;
            @SerializedName("biz_id")
            private Long bizId;

            public String getRawText() {
                return rawText;
            }

            public void setRawText(String rawText) {
                this.rawText = rawText;
            }

            public Long getType() {
                return type;
            }

            public void setType(Long type) {
                this.type = type;
            }

            public Long getBizId() {
                return bizId;
            }

            public void setBizId(Long bizId) {
                this.bizId = bizId;
            }
        }

        public static class Pages {
            @SerializedName("cid")
            private Long cid;
            @SerializedName("page")
            private Long page;
            @SerializedName("from")
            private String from;
            @SerializedName("part")
            private String part;
            @SerializedName("duration")
            private Long duration;
            @SerializedName("vid")
            private String vid;
            @SerializedName("weblink")
            private String weblink;
            @SerializedName("dimension")
            private Dimension dimension;

            public Long getCid() {
                return cid;
            }

            public void setCid(Long cid) {
                this.cid = cid;
            }

            public Long getPage() {
                return page;
            }

            public void setPage(Long page) {
                this.page = page;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public Long getDuration() {
                return duration;
            }

            public void setDuration(Long duration) {
                this.duration = duration;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getWeblink() {
                return weblink;
            }

            public void setWeblink(String weblink) {
                this.weblink = weblink;
            }

            public Dimension getDimension() {
                return dimension;
            }

            public void setDimension(Dimension dimension) {
                this.dimension = dimension;
            }

            public static class Dimension {
                @SerializedName("width")
                private Long width;
                @SerializedName("height")
                private Long height;
                @SerializedName("rotate")
                private Long rotate;

                public Long getWidth() {
                    return width;
                }

                public void setWidth(Long width) {
                    this.width = width;
                }

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
            }
        }
    }
}
