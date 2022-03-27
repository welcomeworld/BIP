package com.github.welcomeworld.bangumi.instrumentality.project.persistence;


import com.nisigada.common.devbase.utils.KVUtil;

public class SettingConfig {
    private static final String DEFAULT_QUALITY_KEY = "setting.default.quality.key";
    private static String currentQuality;
    public static String getDefaultQuality(){
        return KVUtil.getString(DEFAULT_QUALITY_KEY,"1080P+");
    }

    public static String getCurrentQuality(){
        if(currentQuality == null){
            return getDefaultQuality();
        }
        return currentQuality;
    }

    public static void setCurrentQuality(String quality){
        currentQuality = quality;
    }

    public static void setDefaultQuality(String quality){
        KVUtil.putString(DEFAULT_QUALITY_KEY,quality);
    }

}
