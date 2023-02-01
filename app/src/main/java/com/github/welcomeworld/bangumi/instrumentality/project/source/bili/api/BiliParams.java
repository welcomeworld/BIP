package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api;

import android.os.Build;

public class BiliParams {

    public static String getBiliLocalId() {
        return "";
    }

    public static String getDeviceId() {
        return "";
    }

    public static String getUserAgent() {
        return "Mozilla/5.0 BiliDroid/6.21.0 (bbcallen@gmail.com)"+" os/android model/" + Build.MODEL + " build/" + BiliParams.getVersionCode() + "osVer/" + Build.VERSION.RELEASE + " sdkInt/" + Build.VERSION.SDK_INT + " network/2 BiliApp/" + BiliParams.getVersionCode() + " mobi_app/android channel/"+BiliParams.getChannel()+" Buvid/" + BiliParams.getBuvid() + " innerVer/" + BiliParams.getVersionCode() + " c_locale/" + BiliParams.getLocale() + " s_locale/" + BiliParams.getLocale();
    }

    public static String getDeviceName() {
        return Build.MANUFACTURER + Build.MODEL;
    }

    public static String getDevicePlatform() {
        return "Android" + Build.VERSION.RELEASE + Build.MANUFACTURER + Build.MODEL;
    }

    public static String getLocalId() {
        return getBuvid();
    }

    public static String getBuvid() {
        return "XWB4D15BA2D803A3045A6877CCCDE4E4975FA";
//        String guid = getGUid().replace("-", "").toUpperCase();
//        return "XW" + getDigest(guid) + guid;
    }

    public static String getAppKey(){
        return "1d8b6e7d45233436";
    }

    public static String getLocale(){
        return "zh_CN";
    }

    public static int getVersionCode(){
        return 6210600;
    }

    public static String getChannel(){
        return "bilih5";
    }


}
