package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BvToAvBean {

    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private Integer ttl;
    @SerializedName("data")
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
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
        private Integer aid;
        @SerializedName("videos")
        private Integer videos;
        @SerializedName("tid")
        private Integer tid;
        @SerializedName("tname")
        private String tname;
        @SerializedName("copyright")
        private Integer copyright;
        @SerializedName("pic")
        private String pic;
        @SerializedName("title")
        private String title;
        @SerializedName("pubdate")
        private Integer pubdate;
        @SerializedName("ctime")
        private Integer ctime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("desc_v2")
        private List<DescV2> descV2;
        @SerializedName("state")
        private Integer state;
        @SerializedName("duration")
        private Integer duration;
        @SerializedName("mission_id")
        private Integer missionId;
        @SerializedName("rights")
        private Rights rights;
        @SerializedName("owner")
        private Owner owner;
        @SerializedName("stat")
        private Stat stat;
        @SerializedName("dynamic")
        private String dynamic;
        @SerializedName("cid")
        private Integer cid;
        @SerializedName("dimension")
        private Dimension dimension;
        @SerializedName("season_id")
        private Integer seasonId;
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

        public Integer getAid() {
            return aid;
        }

        public void setAid(Integer aid) {
            this.aid = aid;
        }

        public Integer getVideos() {
            return videos;
        }

        public void setVideos(Integer videos) {
            this.videos = videos;
        }

        public Integer getTid() {
            return tid;
        }

        public void setTid(Integer tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public Integer getCopyright() {
            return copyright;
        }

        public void setCopyright(Integer copyright) {
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

        public Integer getPubdate() {
            return pubdate;
        }

        public void setPubdate(Integer pubdate) {
            this.pubdate = pubdate;
        }

        public Integer getCtime() {
            return ctime;
        }

        public void setCtime(Integer ctime) {
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

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Integer getMissionId() {
            return missionId;
        }

        public void setMissionId(Integer missionId) {
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

        public Integer getCid() {
            return cid;
        }

        public void setCid(Integer cid) {
            this.cid = cid;
        }

        public Dimension getDimension() {
            return dimension;
        }

        public void setDimension(Dimension dimension) {
            this.dimension = dimension;
        }

        public Integer getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(Integer seasonId) {
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
            private Integer bp;
            @SerializedName("elec")
            private Integer elec;
            @SerializedName("download")
            private Integer download;
            @SerializedName("movie")
            private Integer movie;
            @SerializedName("pay")
            private Integer pay;
            @SerializedName("hd5")
            private Integer hd5;
            @SerializedName("no_reprint")
            private Integer noReprint;
            @SerializedName("autoplay")
            private Integer autoplay;
            @SerializedName("ugc_pay")
            private Integer ugcPay;
            @SerializedName("is_cooperation")
            private Integer isCooperation;
            @SerializedName("ugc_pay_preview")
            private Integer ugcPayPreview;
            @SerializedName("no_background")
            private Integer noBackground;
            @SerializedName("clean_mode")
            private Integer cleanMode;
            @SerializedName("is_stein_gate")
            private Integer isSteinGate;

            public Integer getBp() {
                return bp;
            }

            public void setBp(Integer bp) {
                this.bp = bp;
            }

            public Integer getElec() {
                return elec;
            }

            public void setElec(Integer elec) {
                this.elec = elec;
            }

            public Integer getDownload() {
                return download;
            }

            public void setDownload(Integer download) {
                this.download = download;
            }

            public Integer getMovie() {
                return movie;
            }

            public void setMovie(Integer movie) {
                this.movie = movie;
            }

            public Integer getPay() {
                return pay;
            }

            public void setPay(Integer pay) {
                this.pay = pay;
            }

            public Integer getHd5() {
                return hd5;
            }

            public void setHd5(Integer hd5) {
                this.hd5 = hd5;
            }

            public Integer getNoReprint() {
                return noReprint;
            }

            public void setNoReprint(Integer noReprint) {
                this.noReprint = noReprint;
            }

            public Integer getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(Integer autoplay) {
                this.autoplay = autoplay;
            }

            public Integer getUgcPay() {
                return ugcPay;
            }

            public void setUgcPay(Integer ugcPay) {
                this.ugcPay = ugcPay;
            }

            public Integer getIsCooperation() {
                return isCooperation;
            }

            public void setIsCooperation(Integer isCooperation) {
                this.isCooperation = isCooperation;
            }

            public Integer getUgcPayPreview() {
                return ugcPayPreview;
            }

            public void setUgcPayPreview(Integer ugcPayPreview) {
                this.ugcPayPreview = ugcPayPreview;
            }

            public Integer getNoBackground() {
                return noBackground;
            }

            public void setNoBackground(Integer noBackground) {
                this.noBackground = noBackground;
            }

            public Integer getCleanMode() {
                return cleanMode;
            }

            public void setCleanMode(Integer cleanMode) {
                this.cleanMode = cleanMode;
            }

            public Integer getIsSteinGate() {
                return isSteinGate;
            }

            public void setIsSteinGate(Integer isSteinGate) {
                this.isSteinGate = isSteinGate;
            }
        }

        public static class Owner {
            @SerializedName("mid")
            private Integer mid;
            @SerializedName("name")
            private String name;
            @SerializedName("face")
            private String face;

            public Integer getMid() {
                return mid;
            }

            public void setMid(Integer mid) {
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
            private Integer aid;
            @SerializedName("view")
            private Integer view;
            @SerializedName("danmaku")
            private Integer danmaku;
            @SerializedName("reply")
            private Integer reply;
            @SerializedName("favorite")
            private Integer favorite;
            @SerializedName("coin")
            private Integer coin;
            @SerializedName("share")
            private Integer share;
            @SerializedName("now_rank")
            private Integer nowRank;
            @SerializedName("his_rank")
            private Integer hisRank;
            @SerializedName("like")
            private Integer like;
            @SerializedName("dislike")
            private Integer dislike;
            @SerializedName("evaluation")
            private String evaluation;
            @SerializedName("argue_msg")
            private String argueMsg;

            public Integer getAid() {
                return aid;
            }

            public void setAid(Integer aid) {
                this.aid = aid;
            }

            public Integer getView() {
                return view;
            }

            public void setView(Integer view) {
                this.view = view;
            }

            public Integer getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(Integer danmaku) {
                this.danmaku = danmaku;
            }

            public Integer getReply() {
                return reply;
            }

            public void setReply(Integer reply) {
                this.reply = reply;
            }

            public Integer getFavorite() {
                return favorite;
            }

            public void setFavorite(Integer favorite) {
                this.favorite = favorite;
            }

            public Integer getCoin() {
                return coin;
            }

            public void setCoin(Integer coin) {
                this.coin = coin;
            }

            public Integer getShare() {
                return share;
            }

            public void setShare(Integer share) {
                this.share = share;
            }

            public Integer getNowRank() {
                return nowRank;
            }

            public void setNowRank(Integer nowRank) {
                this.nowRank = nowRank;
            }

            public Integer getHisRank() {
                return hisRank;
            }

            public void setHisRank(Integer hisRank) {
                this.hisRank = hisRank;
            }

            public Integer getLike() {
                return like;
            }

            public void setLike(Integer like) {
                this.like = like;
            }

            public Integer getDislike() {
                return dislike;
            }

            public void setDislike(Integer dislike) {
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
            private Integer width;
            @SerializedName("height")
            private Integer height;
            @SerializedName("rotate")
            private Integer rotate;

            public Integer getWidth() {
                return width;
            }

            public void setWidth(Integer width) {
                this.width = width;
            }

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
            private Integer id;
            @SerializedName("title")
            private String title;
            @SerializedName("cover")
            private String cover;
            @SerializedName("mid")
            private Integer mid;
            @SerializedName("intro")
            private String intro;
            @SerializedName("sign_state")
            private Integer signState;
            @SerializedName("attribute")
            private Integer attribute;
            @SerializedName("sections")
            private List<Sections> sections;
            @SerializedName("stat")
            private Stat stat;
            @SerializedName("ep_count")
            private Integer epCount;
            @SerializedName("season_type")
            private Integer seasonType;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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

            public Integer getMid() {
                return mid;
            }

            public void setMid(Integer mid) {
                this.mid = mid;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public Integer getSignState() {
                return signState;
            }

            public void setSignState(Integer signState) {
                this.signState = signState;
            }

            public Integer getAttribute() {
                return attribute;
            }

            public void setAttribute(Integer attribute) {
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

            public Integer getEpCount() {
                return epCount;
            }

            public void setEpCount(Integer epCount) {
                this.epCount = epCount;
            }

            public Integer getSeasonType() {
                return seasonType;
            }

            public void setSeasonType(Integer seasonType) {
                this.seasonType = seasonType;
            }

            public static class Stat {
                @SerializedName("season_id")
                private Integer seasonId;
                @SerializedName("view")
                private Integer view;
                @SerializedName("danmaku")
                private Integer danmaku;
                @SerializedName("reply")
                private Integer reply;
                @SerializedName("fav")
                private Integer fav;
                @SerializedName("coin")
                private Integer coin;
                @SerializedName("share")
                private Integer share;
                @SerializedName("now_rank")
                private Integer nowRank;
                @SerializedName("his_rank")
                private Integer hisRank;
                @SerializedName("like")
                private Integer like;

                public Integer getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(Integer seasonId) {
                    this.seasonId = seasonId;
                }

                public Integer getView() {
                    return view;
                }

                public void setView(Integer view) {
                    this.view = view;
                }

                public Integer getDanmaku() {
                    return danmaku;
                }

                public void setDanmaku(Integer danmaku) {
                    this.danmaku = danmaku;
                }

                public Integer getReply() {
                    return reply;
                }

                public void setReply(Integer reply) {
                    this.reply = reply;
                }

                public Integer getFav() {
                    return fav;
                }

                public void setFav(Integer fav) {
                    this.fav = fav;
                }

                public Integer getCoin() {
                    return coin;
                }

                public void setCoin(Integer coin) {
                    this.coin = coin;
                }

                public Integer getShare() {
                    return share;
                }

                public void setShare(Integer share) {
                    this.share = share;
                }

                public Integer getNowRank() {
                    return nowRank;
                }

                public void setNowRank(Integer nowRank) {
                    this.nowRank = nowRank;
                }

                public Integer getHisRank() {
                    return hisRank;
                }

                public void setHisRank(Integer hisRank) {
                    this.hisRank = hisRank;
                }

                public Integer getLike() {
                    return like;
                }

                public void setLike(Integer like) {
                    this.like = like;
                }
            }

            public static class Sections {
                @SerializedName("season_id")
                private Integer seasonId;
                @SerializedName("id")
                private Integer id;
                @SerializedName("title")
                private String title;
                @SerializedName("type")
                private Integer type;
                @SerializedName("episodes")
                private List<Episodes> episodes;

                public Integer getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(Integer seasonId) {
                    this.seasonId = seasonId;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
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

                public List<Episodes> getEpisodes() {
                    return episodes;
                }

                public void setEpisodes(List<Episodes> episodes) {
                    this.episodes = episodes;
                }

                public static class Episodes {
                    @SerializedName("season_id")
                    private Integer seasonId;
                    @SerializedName("section_id")
                    private Integer sectionId;
                    @SerializedName("id")
                    private Integer id;
                    @SerializedName("aid")
                    private Integer aid;
                    @SerializedName("cid")
                    private Integer cid;
                    @SerializedName("title")
                    private String title;
                    @SerializedName("attribute")
                    private Integer attribute;
                    @SerializedName("arc")
                    private Arc arc;
                    @SerializedName("page")
                    private Page page;
                    @SerializedName("bvid")
                    private String bvid;

                    public Integer getSeasonId() {
                        return seasonId;
                    }

                    public void setSeasonId(Integer seasonId) {
                        this.seasonId = seasonId;
                    }

                    public Integer getSectionId() {
                        return sectionId;
                    }

                    public void setSectionId(Integer sectionId) {
                        this.sectionId = sectionId;
                    }

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public Integer getAid() {
                        return aid;
                    }

                    public void setAid(Integer aid) {
                        this.aid = aid;
                    }

                    public Integer getCid() {
                        return cid;
                    }

                    public void setCid(Integer cid) {
                        this.cid = cid;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public Integer getAttribute() {
                        return attribute;
                    }

                    public void setAttribute(Integer attribute) {
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
                        private Integer aid;
                        @SerializedName("videos")
                        private Integer videos;
                        @SerializedName("type_id")
                        private Integer typeId;
                        @SerializedName("type_name")
                        private String typeName;
                        @SerializedName("copyright")
                        private Integer copyright;
                        @SerializedName("pic")
                        private String pic;
                        @SerializedName("title")
                        private String title;
                        @SerializedName("pubdate")
                        private Integer pubdate;
                        @SerializedName("ctime")
                        private Integer ctime;
                        @SerializedName("desc")
                        private String desc;
                        @SerializedName("state")
                        private Integer state;
                        @SerializedName("duration")
                        private Integer duration;
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

                        public Integer getAid() {
                            return aid;
                        }

                        public void setAid(Integer aid) {
                            this.aid = aid;
                        }

                        public Integer getVideos() {
                            return videos;
                        }

                        public void setVideos(Integer videos) {
                            this.videos = videos;
                        }

                        public Integer getTypeId() {
                            return typeId;
                        }

                        public void setTypeId(Integer typeId) {
                            this.typeId = typeId;
                        }

                        public String getTypeName() {
                            return typeName;
                        }

                        public void setTypeName(String typeName) {
                            this.typeName = typeName;
                        }

                        public Integer getCopyright() {
                            return copyright;
                        }

                        public void setCopyright(Integer copyright) {
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

                        public Integer getPubdate() {
                            return pubdate;
                        }

                        public void setPubdate(Integer pubdate) {
                            this.pubdate = pubdate;
                        }

                        public Integer getCtime() {
                            return ctime;
                        }

                        public void setCtime(Integer ctime) {
                            this.ctime = ctime;
                        }

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }

                        public Integer getState() {
                            return state;
                        }

                        public void setState(Integer state) {
                            this.state = state;
                        }

                        public Integer getDuration() {
                            return duration;
                        }

                        public void setDuration(Integer duration) {
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
                            private Integer bp;
                            @SerializedName("elec")
                            private Integer elec;
                            @SerializedName("download")
                            private Integer download;
                            @SerializedName("movie")
                            private Integer movie;
                            @SerializedName("pay")
                            private Integer pay;
                            @SerializedName("hd5")
                            private Integer hd5;
                            @SerializedName("no_reprint")
                            private Integer noReprint;
                            @SerializedName("autoplay")
                            private Integer autoplay;
                            @SerializedName("ugc_pay")
                            private Integer ugcPay;
                            @SerializedName("is_cooperation")
                            private Integer isCooperation;
                            @SerializedName("ugc_pay_preview")
                            private Integer ugcPayPreview;

                            public Integer getBp() {
                                return bp;
                            }

                            public void setBp(Integer bp) {
                                this.bp = bp;
                            }

                            public Integer getElec() {
                                return elec;
                            }

                            public void setElec(Integer elec) {
                                this.elec = elec;
                            }

                            public Integer getDownload() {
                                return download;
                            }

                            public void setDownload(Integer download) {
                                this.download = download;
                            }

                            public Integer getMovie() {
                                return movie;
                            }

                            public void setMovie(Integer movie) {
                                this.movie = movie;
                            }

                            public Integer getPay() {
                                return pay;
                            }

                            public void setPay(Integer pay) {
                                this.pay = pay;
                            }

                            public Integer getHd5() {
                                return hd5;
                            }

                            public void setHd5(Integer hd5) {
                                this.hd5 = hd5;
                            }

                            public Integer getNoReprint() {
                                return noReprint;
                            }

                            public void setNoReprint(Integer noReprint) {
                                this.noReprint = noReprint;
                            }

                            public Integer getAutoplay() {
                                return autoplay;
                            }

                            public void setAutoplay(Integer autoplay) {
                                this.autoplay = autoplay;
                            }

                            public Integer getUgcPay() {
                                return ugcPay;
                            }

                            public void setUgcPay(Integer ugcPay) {
                                this.ugcPay = ugcPay;
                            }

                            public Integer getIsCooperation() {
                                return isCooperation;
                            }

                            public void setIsCooperation(Integer isCooperation) {
                                this.isCooperation = isCooperation;
                            }

                            public Integer getUgcPayPreview() {
                                return ugcPayPreview;
                            }

                            public void setUgcPayPreview(Integer ugcPayPreview) {
                                this.ugcPayPreview = ugcPayPreview;
                            }
                        }

                        public static class Author {
                            @SerializedName("mid")
                            private Integer mid;
                            @SerializedName("name")
                            private String name;
                            @SerializedName("face")
                            private String face;

                            public Integer getMid() {
                                return mid;
                            }

                            public void setMid(Integer mid) {
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
                            private Integer aid;
                            @SerializedName("view")
                            private Integer view;
                            @SerializedName("danmaku")
                            private Integer danmaku;
                            @SerializedName("reply")
                            private Integer reply;
                            @SerializedName("fav")
                            private Integer fav;
                            @SerializedName("coin")
                            private Integer coin;
                            @SerializedName("share")
                            private Integer share;
                            @SerializedName("now_rank")
                            private Integer nowRank;
                            @SerializedName("his_rank")
                            private Integer hisRank;
                            @SerializedName("like")
                            private Integer like;
                            @SerializedName("dislike")
                            private Integer dislike;
                            @SerializedName("evaluation")
                            private String evaluation;
                            @SerializedName("argue_msg")
                            private String argueMsg;

                            public Integer getAid() {
                                return aid;
                            }

                            public void setAid(Integer aid) {
                                this.aid = aid;
                            }

                            public Integer getView() {
                                return view;
                            }

                            public void setView(Integer view) {
                                this.view = view;
                            }

                            public Integer getDanmaku() {
                                return danmaku;
                            }

                            public void setDanmaku(Integer danmaku) {
                                this.danmaku = danmaku;
                            }

                            public Integer getReply() {
                                return reply;
                            }

                            public void setReply(Integer reply) {
                                this.reply = reply;
                            }

                            public Integer getFav() {
                                return fav;
                            }

                            public void setFav(Integer fav) {
                                this.fav = fav;
                            }

                            public Integer getCoin() {
                                return coin;
                            }

                            public void setCoin(Integer coin) {
                                this.coin = coin;
                            }

                            public Integer getShare() {
                                return share;
                            }

                            public void setShare(Integer share) {
                                this.share = share;
                            }

                            public Integer getNowRank() {
                                return nowRank;
                            }

                            public void setNowRank(Integer nowRank) {
                                this.nowRank = nowRank;
                            }

                            public Integer getHisRank() {
                                return hisRank;
                            }

                            public void setHisRank(Integer hisRank) {
                                this.hisRank = hisRank;
                            }

                            public Integer getLike() {
                                return like;
                            }

                            public void setLike(Integer like) {
                                this.like = like;
                            }

                            public Integer getDislike() {
                                return dislike;
                            }

                            public void setDislike(Integer dislike) {
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
                            private Integer width;
                            @SerializedName("height")
                            private Integer height;
                            @SerializedName("rotate")
                            private Integer rotate;

                            public Integer getWidth() {
                                return width;
                            }

                            public void setWidth(Integer width) {
                                this.width = width;
                            }

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
                        }
                    }

                    public static class Page {
                        @SerializedName("cid")
                        private Integer cid;
                        @SerializedName("page")
                        private Integer page;
                        @SerializedName("from")
                        private String from;
                        @SerializedName("part")
                        private String part;
                        @SerializedName("duration")
                        private Integer duration;
                        @SerializedName("vid")
                        private String vid;
                        @SerializedName("weblink")
                        private String weblink;
                        @SerializedName("dimension")
                        private Dimension dimension;

                        public Integer getCid() {
                            return cid;
                        }

                        public void setCid(Integer cid) {
                            this.cid = cid;
                        }

                        public Integer getPage() {
                            return page;
                        }

                        public void setPage(Integer page) {
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

                        public Integer getDuration() {
                            return duration;
                        }

                        public void setDuration(Integer duration) {
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
                            private Integer width;
                            @SerializedName("height")
                            private Integer height;
                            @SerializedName("rotate")
                            private Integer rotate;

                            public Integer getWidth() {
                                return width;
                            }

                            public void setWidth(Integer width) {
                                this.width = width;
                            }

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
            private Integer type;
            @SerializedName("biz_id")
            private Integer bizId;

            public String getRawText() {
                return rawText;
            }

            public void setRawText(String rawText) {
                this.rawText = rawText;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }

            public Integer getBizId() {
                return bizId;
            }

            public void setBizId(Integer bizId) {
                this.bizId = bizId;
            }
        }

        public static class Pages {
            @SerializedName("cid")
            private Integer cid;
            @SerializedName("page")
            private Integer page;
            @SerializedName("from")
            private String from;
            @SerializedName("part")
            private String part;
            @SerializedName("duration")
            private Integer duration;
            @SerializedName("vid")
            private String vid;
            @SerializedName("weblink")
            private String weblink;
            @SerializedName("dimension")
            private Dimension dimension;

            public Integer getCid() {
                return cid;
            }

            public void setCid(Integer cid) {
                this.cid = cid;
            }

            public Integer getPage() {
                return page;
            }

            public void setPage(Integer page) {
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

            public Integer getDuration() {
                return duration;
            }

            public void setDuration(Integer duration) {
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
                private Integer width;
                @SerializedName("height")
                private Integer height;
                @SerializedName("rotate")
                private Integer rotate;

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

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
            }
        }
    }
}
