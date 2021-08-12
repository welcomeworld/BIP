package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans;

import com.google.gson.annotations.SerializedName;

public class PlayUrlBean {

    @SerializedName("purl")
    private String purl;
    @SerializedName("purlf")
    private String purlf;
    @SerializedName("vurl")
    private String vurl;
    @SerializedName("playid")
    private String playid;
    @SerializedName("vurl_bak")
    private String vurlBak;
    @SerializedName("purl_mp4")
    private String purlMp4;
    @SerializedName("ex")
    private String ex;

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPurlf() {
        return purlf;
    }

    public void setPurlf(String purlf) {
        this.purlf = purlf;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getPlayid() {
        return playid;
    }

    public void setPlayid(String playid) {
        this.playid = playid;
    }

    public String getVurlBak() {
        return vurlBak;
    }

    public void setVurlBak(String vurlBak) {
        this.vurlBak = vurlBak;
    }

    public String getPurlMp4() {
        return purlMp4;
    }

    public void setPurlMp4(String purlMp4) {
        this.purlMp4 = purlMp4;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }
}
