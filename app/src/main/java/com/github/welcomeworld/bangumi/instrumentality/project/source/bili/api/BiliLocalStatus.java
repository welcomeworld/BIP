package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.LoginResultBean;
import com.google.gson.Gson;
import com.github.welcomeworld.devbase.utils.KVUtil;

public class BiliLocalStatus {
    private static String accessKey = "";
    private static boolean isLogin = false;
    private static long mid = -1;
    private static String avatar = "";
    private static String name = "";

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

    public static long getMid() {
        return mid;
    }

    public static void setMid(long mid) {
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
}
