package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.LoginResultBean;
import com.google.gson.Gson;
import com.nisigada.common.devbase.utils.KVUtil;

public class BiliLocalStatus {
    private static String accessKey = "";
    private static boolean isLogin = false;
    private static int mid = -1;
    private static String avatar = "";
    private static String name = "";
    private static boolean isWebLogin = false;

    static {
        LoginResultBean.TokenInfoBean token = getToken();
        if (token != null) {
            BiliLocalStatus.setLogin(true);
            BiliLocalStatus.setAccessKey(token.getAccessToken());
            BiliLocalStatus.setMid(token.getMid());
        }
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static void setAccessKey(String key) {
        accessKey = key;
    }

    public static boolean isLogin() {
        return isLogin;
    }

    public static void setLogin(boolean login) {
        isLogin = login;
    }

    public static int getMid() {
        return mid;
    }

    public static void setMid(int mid) {
        BiliLocalStatus.mid = mid;
    }

    public static String getAvatar() {
        return avatar;
    }

    public static void setAvatar(String avatar) {
        BiliLocalStatus.avatar = avatar;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        BiliLocalStatus.name = name;
    }

    public static void setToken(LoginResultBean.TokenInfoBean token) {
        KVUtil.putString("bili_local_token", new Gson().toJson(token));
    }

    public static LoginResultBean.TokenInfoBean getToken() {
        return new Gson().fromJson(KVUtil.getString("bili_local_token"), LoginResultBean.TokenInfoBean.class);
    }

    public static boolean isIsWebLogin() {
        return isWebLogin;
    }

    public static void setIsWebLogin(boolean isWebLogin) {
        BiliLocalStatus.isWebLogin = isWebLogin;
        KVUtil.putBoolean("bili_local_status_web_login", isWebLogin);
    }
}
