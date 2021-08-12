package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api;

import android.content.Context;
import android.os.Build;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BiliParams {
    public static Context appContext;

    public static Map getDeviceMetaParams() {
        HashMap<String, Object> params = new HashMap<>();

        params.put("props", Collections.emptyMap());
        params.put("sys", Collections.emptyMap());

        params.put("buvid_local", "");
        params.put("oaid", "");
        params.put("udid", "");
        params.put("vaid", "");
        params.put("aaid", "");
        params.put("systemvolume", "");
        params.put("root", "");
        params.put("languages", "");
        params.put("free_memory", "");
        params.put("totalSpace", "");
        params.put("fstorage", "");
        params.put("countryIso", "");
        params.put("kernel_version", "");
        params.put("build_id", "");
        params.put("androidappcnt", "");
        params.put("androidsysapp20", "");
        params.put("androidapp20", "");
        params.put("ssid", "");
        params.put("bssid", "");
        params.put("wifimaclist", "");
        params.put("battery", "");
        params.put("batteryState", "");
        params.put("rc_app_code", "");
        return params;
    }

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

    public static String getGUid() {
        return UUID.randomUUID().toString();
    }

    public static String getDigest(String value) {
        try {
            return "" + value.charAt(2) + value.charAt(12) + value.charAt(22);
        } catch (IndexOutOfBoundsException unused_ex) {
            return "000";
        }
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
