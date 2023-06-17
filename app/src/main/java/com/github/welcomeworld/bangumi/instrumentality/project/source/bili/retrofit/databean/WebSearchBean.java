package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebSearchBean {

    @SerializedName("code")
    private long code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private long ttl;
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
        @SerializedName("seid")
        private String seid;
        @SerializedName("page")
        private long page;
        @SerializedName("pagesize")
        private long pagesize;
        @SerializedName("numResults")
        private long numResults;
        @SerializedName("numPages")
        private long numPages;
        @SerializedName("suggest_keyword")
        private String suggestKeyword;
        @SerializedName("rqt_type")
        private String rqtType;
        @SerializedName("cost_time")
        private CostTime costTime;
        @SerializedName("exp_list")
        private ExpList expList;
        @SerializedName("egg_hit")
        private long eggHit;
        @SerializedName("result")
        private List<Result> result;
        @SerializedName("show_column")
        private long showColumn;
        @SerializedName("in_black_key")
        private long inBlackKey;
        @SerializedName("in_white_key")
        private long inWhiteKey;

        public String getSeid() {
            return seid;
        }

        public void setSeid(String seid) {
            this.seid = seid;
        }

        public long getPage() {
            return page;
        }

        public void setPage(long page) {
            this.page = page;
        }

        public long getPagesize() {
            return pagesize;
        }

        public void setPagesize(long pagesize) {
            this.pagesize = pagesize;
        }

        public long getNumResults() {
            return numResults;
        }

        public void setNumResults(long numResults) {
            this.numResults = numResults;
        }

        public long getNumPages() {
            return numPages;
        }

        public void setNumPages(long numPages) {
            this.numPages = numPages;
        }

        public String getSuggestKeyword() {
            return suggestKeyword;
        }

        public void setSuggestKeyword(String suggestKeyword) {
            this.suggestKeyword = suggestKeyword;
        }

        public String getRqtType() {
            return rqtType;
        }

        public void setRqtType(String rqtType) {
            this.rqtType = rqtType;
        }

        public CostTime getCostTime() {
            return costTime;
        }

        public void setCostTime(CostTime costTime) {
            this.costTime = costTime;
        }

        public ExpList getExpList() {
            return expList;
        }

        public void setExpList(ExpList expList) {
            this.expList = expList;
        }

        public long getEggHit() {
            return eggHit;
        }

        public void setEggHit(long eggHit) {
            this.eggHit = eggHit;
        }

        public List<Result> getResult() {
            return result;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }

        public long getShowColumn() {
            return showColumn;
        }

        public void setShowColumn(long showColumn) {
            this.showColumn = showColumn;
        }

        public long getInBlackKey() {
            return inBlackKey;
        }

        public void setInBlackKey(long inBlackKey) {
            this.inBlackKey = inBlackKey;
        }

        public long getInWhiteKey() {
            return inWhiteKey;
        }

        public void setInWhiteKey(long inWhiteKey) {
            this.inWhiteKey = inWhiteKey;
        }

        public static class CostTime {
            @SerializedName("as_request")
            private String asRequest;
            @SerializedName("as_request_format")
            private String asRequestFormat;
            @SerializedName("as_response_format")
            private String asResponseFormat;
            @SerializedName("deserialize_response")
            private String deserializeResponse;
            @SerializedName("illegal_handler")
            private String illegalHandler;
            @SerializedName("is_risk_query")
            private String isRiskQuery;
            @SerializedName("main_handler")
            private String mainHandler;
            @SerializedName("params_check")
            private String paramsCheck;
            @SerializedName("total")
            private String total;

            public String getAsRequest() {
                return asRequest;
            }

            public void setAsRequest(String asRequest) {
                this.asRequest = asRequest;
            }

            public String getAsRequestFormat() {
                return asRequestFormat;
            }

            public void setAsRequestFormat(String asRequestFormat) {
                this.asRequestFormat = asRequestFormat;
            }

            public String getAsResponseFormat() {
                return asResponseFormat;
            }

            public void setAsResponseFormat(String asResponseFormat) {
                this.asResponseFormat = asResponseFormat;
            }

            public String getDeserializeResponse() {
                return deserializeResponse;
            }

            public void setDeserializeResponse(String deserializeResponse) {
                this.deserializeResponse = deserializeResponse;
            }

            public String getIllegalHandler() {
                return illegalHandler;
            }

            public void setIllegalHandler(String illegalHandler) {
                this.illegalHandler = illegalHandler;
            }

            public String getIsRiskQuery() {
                return isRiskQuery;
            }

            public void setIsRiskQuery(String isRiskQuery) {
                this.isRiskQuery = isRiskQuery;
            }

            public String getMainHandler() {
                return mainHandler;
            }

            public void setMainHandler(String mainHandler) {
                this.mainHandler = mainHandler;
            }

            public String getParamsCheck() {
                return paramsCheck;
            }

            public void setParamsCheck(String paramsCheck) {
                this.paramsCheck = paramsCheck;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }
        }

        public static class ExpList {
            @SerializedName("5511")
            private boolean $5511;
            @SerializedName("6609")
            private boolean $6609;
            @SerializedName("7705")
            private boolean $7705;
            @SerializedName("9902")
            private boolean $9902;
            @SerializedName("9924")
            private boolean $9924;
            @SerializedName("9931")
            private boolean $9931;

            public boolean is$5511() {
                return $5511;
            }

            public void set$5511(boolean $5511) {
                this.$5511 = $5511;
            }

            public boolean is$6609() {
                return $6609;
            }

            public void set$6609(boolean $6609) {
                this.$6609 = $6609;
            }

            public boolean is$7705() {
                return $7705;
            }

            public void set$7705(boolean $7705) {
                this.$7705 = $7705;
            }

            public boolean is$9902() {
                return $9902;
            }

            public void set$9902(boolean $9902) {
                this.$9902 = $9902;
            }

            public boolean is$9924() {
                return $9924;
            }

            public void set$9924(boolean $9924) {
                this.$9924 = $9924;
            }

            public boolean is$9931() {
                return $9931;
            }

            public void set$9931(boolean $9931) {
                this.$9931 = $9931;
            }
        }

        public static class Result {
            @SerializedName("type")
            private String type;
            @SerializedName("id")
            private long id;
            @SerializedName("author")
            private String author;
            @SerializedName("mid")
            private long mid;
            @SerializedName("typeid")
            private String typeid;
            @SerializedName("typename")
            private String typename;
            @SerializedName("arcurl")
            private String arcurl;
            @SerializedName("aid")
            private long aid;
            @SerializedName("bvid")
            private String bvid;
            @SerializedName("title")
            private String title;
            @SerializedName("description")
            private String description;
            @SerializedName("arcrank")
            private String arcrank;
            @SerializedName("pic")
            private String pic;
            @SerializedName("play")
            private long play;
            @SerializedName("video_review")
            private long videoReview;
            @SerializedName("favorites")
            private long favorites;
            @SerializedName("tag")
            private String tag;
            @SerializedName("review")
            private long review;
            @SerializedName("pubdate")
            private long pubdate;
            @SerializedName("senddate")
            private long senddate;
            @SerializedName("duration")
            private String duration;
            @SerializedName("badgepay")
            private boolean badgepay;
            @SerializedName("hit_columns")
            private List<String> hitColumns;
            @SerializedName("view_type")
            private String viewType;
            @SerializedName("is_pay")
            private long isPay;
            @SerializedName("is_union_video")
            private long isUnionVideo;
            @SerializedName("rec_tags")
            private Object recTags;
            @SerializedName("new_rec_tags")
            private List<?> newRecTags;
            @SerializedName("rank_score")
            private long rankScore;
            @SerializedName("like")
            private long like;
            @SerializedName("upic")
            private String upic;
            @SerializedName("corner")
            private String corner;
            @SerializedName("cover")
            private String cover;
            @SerializedName("desc")
            private String desc;
            @SerializedName("url")
            private String url;
            @SerializedName("rec_reason")
            private String recReason;
            @SerializedName("danmaku")
            private long danmaku;
            @SerializedName("biz_data")
            private Object bizData;
            @SerializedName("is_charge_video")
            private long isChargeVideo;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public long getMid() {
                return mid;
            }

            public void setMid(long mid) {
                this.mid = mid;
            }

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getArcurl() {
                return arcurl;
            }

            public void setArcurl(String arcurl) {
                this.arcurl = arcurl;
            }

            public long getAid() {
                return aid;
            }

            public void setAid(long aid) {
                this.aid = aid;
            }

            public String getBvid() {
                return bvid;
            }

            public void setBvid(String bvid) {
                this.bvid = bvid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getArcrank() {
                return arcrank;
            }

            public void setArcrank(String arcrank) {
                this.arcrank = arcrank;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public long getPlay() {
                return play;
            }

            public void setPlay(long play) {
                this.play = play;
            }

            public long getVideoReview() {
                return videoReview;
            }

            public void setVideoReview(long videoReview) {
                this.videoReview = videoReview;
            }

            public long getFavorites() {
                return favorites;
            }

            public void setFavorites(long favorites) {
                this.favorites = favorites;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public long getReview() {
                return review;
            }

            public void setReview(long review) {
                this.review = review;
            }

            public long getPubdate() {
                return pubdate;
            }

            public void setPubdate(long pubdate) {
                this.pubdate = pubdate;
            }

            public long getSenddate() {
                return senddate;
            }

            public void setSenddate(long senddate) {
                this.senddate = senddate;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public boolean isBadgepay() {
                return badgepay;
            }

            public void setBadgepay(boolean badgepay) {
                this.badgepay = badgepay;
            }

            public List<String> getHitColumns() {
                return hitColumns;
            }

            public void setHitColumns(List<String> hitColumns) {
                this.hitColumns = hitColumns;
            }

            public String getViewType() {
                return viewType;
            }

            public void setViewType(String viewType) {
                this.viewType = viewType;
            }

            public long getIsPay() {
                return isPay;
            }

            public void setIsPay(long isPay) {
                this.isPay = isPay;
            }

            public long getIsUnionVideo() {
                return isUnionVideo;
            }

            public void setIsUnionVideo(long isUnionVideo) {
                this.isUnionVideo = isUnionVideo;
            }

            public Object getRecTags() {
                return recTags;
            }

            public void setRecTags(Object recTags) {
                this.recTags = recTags;
            }

            public List<?> getNewRecTags() {
                return newRecTags;
            }

            public void setNewRecTags(List<?> newRecTags) {
                this.newRecTags = newRecTags;
            }

            public long getRankScore() {
                return rankScore;
            }

            public void setRankScore(long rankScore) {
                this.rankScore = rankScore;
            }

            public long getLike() {
                return like;
            }

            public void setLike(long like) {
                this.like = like;
            }

            public String getUpic() {
                return upic;
            }

            public void setUpic(String upic) {
                this.upic = upic;
            }

            public String getCorner() {
                return corner;
            }

            public void setCorner(String corner) {
                this.corner = corner;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getRecReason() {
                return recReason;
            }

            public void setRecReason(String recReason) {
                this.recReason = recReason;
            }

            public long getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(long danmaku) {
                this.danmaku = danmaku;
            }

            public Object getBizData() {
                return bizData;
            }

            public void setBizData(Object bizData) {
                this.bizData = bizData;
            }

            public long getIsChargeVideo() {
                return isChargeVideo;
            }

            public void setIsChargeVideo(long isChargeVideo) {
                this.isChargeVideo = isChargeVideo;
            }
        }
    }
}
