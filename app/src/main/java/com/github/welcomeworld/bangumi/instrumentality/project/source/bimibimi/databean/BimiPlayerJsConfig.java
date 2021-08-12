package com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.databean;

import com.google.gson.annotations.SerializedName;

public class BimiPlayerJsConfig {
    @SerializedName("flag")
    private String flag;
    @SerializedName("encrypt")
    private Integer encrypt;
    @SerializedName("trysee")
    private Integer trysee;
    @SerializedName("points")
    private Integer points;
    @SerializedName("link")
    private String link;
    @SerializedName("link_next")
    private String linkNext;
    @SerializedName("link_pre")
    private String linkPre;
    @SerializedName("url")
    private String url;
    @SerializedName("url_next")
    private String urlNext;
    @SerializedName("from")
    private String from;
    @SerializedName("server")
    private String server;
    @SerializedName("note")
    private String note;
    @SerializedName("id")
    private String id;
    @SerializedName("sid")
    private Integer sid;
    @SerializedName("nid")
    private Integer nid;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    public Integer getTrysee() {
        return trysee;
    }

    public void setTrysee(Integer trysee) {
        this.trysee = trysee;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkNext() {
        return linkNext;
    }

    public void setLinkNext(String linkNext) {
        this.linkNext = linkNext;
    }

    public String getLinkPre() {
        return linkPre;
    }

    public void setLinkPre(String linkPre) {
        this.linkPre = linkPre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlNext() {
        return urlNext;
    }

    public void setUrlNext(String urlNext) {
        this.urlNext = urlNext;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }
}
