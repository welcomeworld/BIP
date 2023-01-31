package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class BangumiUrlBean {

    @SerializedName("accept_format")
    private String acceptFormat;
    @SerializedName("code")
    private int code;
    @SerializedName("seek_param")
    private String seekParam;
    @SerializedName("is_preview")
    private int isPreview;
    @SerializedName("format")
    private String format;
    @SerializedName("fnval")
    private int fnval;
    @SerializedName("video_project")
    private boolean videoProject;
    @SerializedName("fnver")
    private int fnver;
    @SerializedName("bp")
    private int bp;
    @SerializedName("quality")
    private int quality;
    @SerializedName("timelength")
    private int timelength;
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
    private int videoCodecid;
    @SerializedName("status")
    private int status;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSeekParam() {
        return seekParam;
    }

    public void setSeekParam(String seekParam) {
        this.seekParam = seekParam;
    }

    public int getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(int isPreview) {
        this.isPreview = isPreview;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getFnval() {
        return fnval;
    }

    public void setFnval(int fnval) {
        this.fnval = fnval;
    }

    public boolean isVideoProject() {
        return videoProject;
    }

    public void setVideoProject(boolean videoProject) {
        this.videoProject = videoProject;
    }

    public int getFnver() {
        return fnver;
    }

    public void setFnver(int fnver) {
        this.fnver = fnver;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
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

    public int getVideoCodecid() {
        return videoCodecid;
    }

    public void setVideoCodecid(int videoCodecid) {
        this.videoCodecid = videoCodecid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        private int order;
        private long length;
        private long size;
        private String url;
        private List<String> backup_url;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
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
            private int codecid;
            @SerializedName("bandwidth")
            private int bandwidth;
            @SerializedName("base_url")
            private String baseUrl1;
            @SerializedName("id")
            private int id;
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

            public int getCodecid() {
                return codecid;
            }

            public void setCodecid(int codecid) {
                this.codecid = codecid;
            }

            public int getBandwidth() {
                return bandwidth;
            }

            public void setBandwidth(int bandwidth) {
                this.bandwidth = bandwidth;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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
            private int bandwidth;
            @SerializedName("base_url")
            private String baseUrl1;
            @SerializedName("id")
            private int id;
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

            public int getBandwidth() {
                return bandwidth;
            }

            public void setBandwidth(int bandwidth) {
                this.bandwidth = bandwidth;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

