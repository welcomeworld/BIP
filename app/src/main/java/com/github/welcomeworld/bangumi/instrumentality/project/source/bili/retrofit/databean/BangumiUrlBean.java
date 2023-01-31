package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class BangumiUrlBean {

    @SerializedName("accept_format")
    private String acceptFormat;
    @SerializedName("code")
    private long code;
    @SerializedName("seek_param")
    private String seekParam;
    @SerializedName("is_preview")
    private long isPreview;
    @SerializedName("format")
    private String format;
    @SerializedName("fnval")
    private long fnval;
    @SerializedName("video_project")
    private boolean videoProject;
    @SerializedName("fnver")
    private long fnver;
    @SerializedName("bp")
    private long bp;
    @SerializedName("quality")
    private long quality;
    @SerializedName("timelength")
    private long timelength;
    @SerializedName("result")
    private String result;
    @SerializedName("seek_type")
    private String seekType;
    @SerializedName("has_paid")
    private boolean hasPaid;
    @SerializedName("from")
    private String from;
    @SerializedName("dash")
    private DashBean dash;
    @SerializedName("video_codecid")
    private long videoCodecid;
    @SerializedName("status")
    private long status;
    @SerializedName("accept_quality")
    private List<Integer> acceptQuality;
    @SerializedName("accept_description")
    private List<String> acceptDescription;
    private List<DurlBean> durl;

    public String getAcceptFormat() {
        return acceptFormat;
    }

    public void setAcceptFormat(String acceptFormat) {
        this.acceptFormat = acceptFormat;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getSeekParam() {
        return seekParam;
    }

    public void setSeekParam(String seekParam) {
        this.seekParam = seekParam;
    }

    public long getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(long isPreview) {
        this.isPreview = isPreview;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getFnval() {
        return fnval;
    }

    public void setFnval(long fnval) {
        this.fnval = fnval;
    }

    public boolean isVideoProject() {
        return videoProject;
    }

    public void setVideoProject(boolean videoProject) {
        this.videoProject = videoProject;
    }

    public long getFnver() {
        return fnver;
    }

    public void setFnver(long fnver) {
        this.fnver = fnver;
    }

    public long getBp() {
        return bp;
    }

    public void setBp(long bp) {
        this.bp = bp;
    }

    public long getQuality() {
        return quality;
    }

    public void setQuality(long quality) {
        this.quality = quality;
    }

    public long getTimelength() {
        return timelength;
    }

    public void setTimelength(long timelength) {
        this.timelength = timelength;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSeekType() {
        return seekType;
    }

    public void setSeekType(String seekType) {
        this.seekType = seekType;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public DashBean getDash() {
        return dash;
    }

    public void setDash(DashBean dash) {
        this.dash = dash;
    }

    public long getVideoCodecid() {
        return videoCodecid;
    }

    public void setVideoCodecid(long videoCodecid) {
        this.videoCodecid = videoCodecid;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public List<Integer> getAcceptQuality() {
        return acceptQuality;
    }

    public void setAcceptQuality(List<Integer> acceptQuality) {
        this.acceptQuality = acceptQuality;
    }

    public List<String> getAcceptDescription() {
        return acceptDescription;
    }

    public void setAcceptDescription(List<String> acceptDescription) {
        this.acceptDescription = acceptDescription;
    }

    public List<DurlBean> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlBean> durl) {
        this.durl = durl;
    }

    public static class DurlBean {
        private long order;
        private long length;
        private long size;
        private String url;
        private List<String> backup_url;

        public long getOrder() {
            return order;
        }

        public void setOrder(long order) {
            this.order = order;
        }

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }

        public void setBackup_url(List<String> backup_url) {
            this.backup_url = backup_url;
        }
    }

    public static class DashBean {
        @SerializedName("video")
        private List<VideoBean> video;
        @SerializedName("audio")
        private List<AudioBean> audio;

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public List<AudioBean> getAudio() {
            return audio;
        }

        public void setAudio(List<AudioBean> audio) {
            this.audio = audio;
        }

        public static class VideoBean {
            @SerializedName("baseUrl")
            private String baseUrl;
            @SerializedName("codecid")
            private long codecid;
            @SerializedName("bandwidth")
            private long bandwidth;
            @SerializedName("base_url")
            private String baseUrl1;
            @SerializedName("id")
            private long id;
            @SerializedName("backupUrl")
            private List<String> backupUrl;
            @SerializedName("backup_url")
            private List<String> backupUrl1;

            public String getBaseUrl1() {
                return baseUrl1;
            }

            public void setBaseUrl1(String baseUrl) {
                this.baseUrl1 = baseUrl;
            }

            public long getCodecid() {
                return codecid;
            }

            public void setCodecid(long codecid) {
                this.codecid = codecid;
            }

            public long getBandwidth() {
                return bandwidth;
            }

            public void setBandwidth(long bandwidth) {
                this.bandwidth = bandwidth;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public List<String> getBackupUrl1() {
                return backupUrl1;
            }

            public void setBackupUrl1(List<String> backupUrl) {
                this.backupUrl1 = backupUrl;
            }

            public List<String> getBackupUrl() {
                return backupUrl;
            }

            public void setBackupUrl(List<String> backupUrl) {
                this.backupUrl = backupUrl;
            }
        }

        public static class AudioBean {
            @SerializedName("baseUrl")
            private String baseUrl;
            @SerializedName("bandwidth")
            private long bandwidth;
            @SerializedName("base_url")
            private String baseUrl1;
            @SerializedName("id")
            private long id;
            @SerializedName("backupUrl")
            private List<String> backupUrl;
            @SerializedName("backup_url")
            private List<String> backupUrl1;

            public String getBaseUrl1() {
                return baseUrl1;
            }

            public void setBaseUrl1(String baseUrl) {
                this.baseUrl1 = baseUrl;
            }

            public long getBandwidth() {
                return bandwidth;
            }

            public void setBandwidth(long bandwidth) {
                this.bandwidth = bandwidth;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public List<String> getBackupUrl1() {
                return backupUrl1;
            }

            public void setBackupUrl1(List<String> backupUrl) {
                this.backupUrl1 = backupUrl;
            }

            public List<String> getBackupUrl() {
                return backupUrl;
            }

            public void setBackupUrl(List<String> backupUrl) {
                this.backupUrl = backupUrl;
            }
        }
    }
}

