package com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.retrofit.databean;

import com.google.gson.annotations.SerializedName;

public class AgeVideoUrlBean {

    @SerializedName("success")
    private Integer success;
    @SerializedName("player")
    private String player;
    @SerializedName("type")
    private String type;
    @SerializedName("msg")
    private String msg;
    @SerializedName("title")
    private String title;
    @SerializedName("code")
    private Integer code;
    @SerializedName("url")
    private String url;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
