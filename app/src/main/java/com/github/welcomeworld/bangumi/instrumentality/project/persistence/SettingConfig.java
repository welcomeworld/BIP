package com.github.welcomeworld.bangumi.instrumentality.project.persistence;


import androidx.lifecycle.LiveData;

import com.github.welcomeworld.bangumi.instrumentality.project.livedata.SafeLiveData;
import com.github.welcomeworld.devbase.utils.KVUtil;

public class SettingConfig {
    private static final String DEFAULT_QUALITY_KEY = "setting.default.quality.key";
    private static final String DANMAKU_SWITCH_KEY = "setting.danmaku.switch.key";
    private static final String SETTING_EXOPLAYER = "setting_exoplayer";
    private static final String SETTING_MEDIACODEC = "setting_mediacodec";
    private static final String SETTING_FULL_DEFAULT = "setting_full_default";

    private static final SafeLiveData<Boolean> useExoLive = new SafeLiveData<>(isUseExoPlayer());

    public static LiveData<Boolean> getUseExoLive() {
        return useExoLive;
    }

    private static final SafeLiveData<Boolean> useMediaCodecLive = new SafeLiveData<>(isUseMediaCodec());

    public static LiveData<Boolean> getUseMediaCodecLive() {
        return useMediaCodecLive;
    }

    private static final SafeLiveData<Boolean> fullDefaultLive = new SafeLiveData<>(isFullDefault());

    public static LiveData<Boolean> getFullDefaultLive() {
        return fullDefaultLive;
    }

    private static String currentQuality;

    public static String getDefaultQuality() {
        return KVUtil.getString(DEFAULT_QUALITY_KEY, "1080P+");
    }

    public static String getCurrentQuality() {
        if (currentQuality == null) {
            return getDefaultQuality();
        }
        return currentQuality;
    }

    public static void setCurrentQuality(String quality) {
        currentQuality = quality;
    }

    public static boolean isDanmakuOpen() {
        return KVUtil.getBoolean(DANMAKU_SWITCH_KEY, true);
    }

    public static void setDanmakuOpen(boolean open) {
        KVUtil.putBoolean(DANMAKU_SWITCH_KEY, open);
    }

    public static void setUseExoPlayer(boolean useExo) {
        KVUtil.putBoolean(SETTING_EXOPLAYER, useExo);
        useExoLive.updateValueSafe(useExo);
    }

    public static boolean isUseExoPlayer() {
        return KVUtil.getBoolean(SETTING_EXOPLAYER);
    }

    public static void setUseMediaCodec(boolean useMediaCodec) {
        KVUtil.putBoolean(SETTING_MEDIACODEC, useMediaCodec);
        useMediaCodecLive.updateValueSafe(useMediaCodec);
    }

    public static boolean isUseMediaCodec() {
        return KVUtil.getBoolean(SETTING_MEDIACODEC);
    }

    public static void setFullDefault(boolean fullDefault) {
        KVUtil.putBoolean(SETTING_FULL_DEFAULT, fullDefault);
        fullDefaultLive.updateValueSafe(fullDefault);
    }

    public static boolean isFullDefault() {
        return KVUtil.getBoolean(SETTING_FULL_DEFAULT);
    }

}
