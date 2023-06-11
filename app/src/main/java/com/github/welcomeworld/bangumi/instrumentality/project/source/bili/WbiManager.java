package com.github.welcomeworld.bangumi.instrumentality.project.source.bili;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.BiliRetrofitManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.api.UserWebAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean.WebNavBean;
import com.github.welcomeworld.devbase.utils.KVUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import retrofit2.Response;

public class WbiManager {
    private final static long TIME_OUT = 1000 * 60 * 60 * 12; //12 hours
    private final static String KEY_UPDATE_TIME = "key_update_time";
    private final static String KEY_WBI = "key_wbi";
    private static final int[] mixinKey = new int[]{46, 47, 18, 2, 53, 8, 23, 32, 15, 50, 10, 31, 58, 3, 45, 35, 27, 43, 5, 49,
            33, 9, 42, 19, 29, 28, 14, 39, 12, 38, 41, 13, 37, 48, 7, 16, 24, 55, 40,
            61, 26, 17, 0, 1, 60, 51, 30, 4, 22, 25, 54, 21, 56, 59, 6, 63, 57, 62, 11,
            36, 20, 34, 44, 52};

    private WbiManager() {

    }

    private static WbiManager instance;

    public static WbiManager getInstance() {
        if (instance == null) {
            instance = new WbiManager();
        }
        return instance;
    }

    private void saveWbiKey(String wbi) {
        KVUtil.putString(KEY_WBI, wbi);
    }

    private String getWbiKey() {
        checkWbiKey();
        return KVUtil.getString(KEY_WBI, "");
    }

    private void saveUpdateTime() {
        KVUtil.putLong(KEY_UPDATE_TIME, System.currentTimeMillis());
    }

    private long getUpdateTime() {
        return KVUtil.getLong(KEY_UPDATE_TIME, 0);
    }


    private void checkWbiKey() {
        if (isWbiAvailable()) {
            return;
        }
        UserWebAPI userWebAPI = BiliRetrofitManager.getNormalRetrofit(BaseUrl.APIURL).create(UserWebAPI.class);
        try {
            Response<WebNavBean> response = userWebAPI.getNav().execute();
            if (response.body() == null || response.body().getData() == null) {
                return;
            }
            WebNavBean.Data.WbiImg wbiData = response.body().getData().getWbiImg();
            String imgUrl = wbiData.getImgUrl();
            String subUrl = wbiData.getSubUrl();
            String imgKey = imgUrl.substring(imgUrl.indexOf("wbi/") + 4, imgUrl.indexOf(".png"));
            String subKey = subUrl.substring(imgUrl.indexOf("wbi/") + 4, imgUrl.indexOf(".png"));
            saveWbiKey(generateWbiKey(imgKey + subKey));
            saveUpdateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isWbiAvailable() {
        long current = System.currentTimeMillis();
        long lastUpdate = getUpdateTime();
        Calendar lastUpdateCalendar = Calendar.getInstance();
        lastUpdateCalendar.setTimeInMillis(lastUpdate);
        return current - lastUpdate < TIME_OUT && Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == lastUpdateCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private String generateWbiKey(String wbiSeed) {
        StringBuilder wbiBuilder = new StringBuilder();
        if (wbiSeed.length() >= 32) {
            for (int i = 0; i < 32; i++) {
                wbiBuilder.append(wbiSeed.charAt(mixinKey[i]));
            }
        }
        return wbiBuilder.toString();
    }

    public String signWithWbi(String queryString) {
        try {
            String wts = "&wts=" + System.currentTimeMillis() / 1000;
            String result = queryString + wts;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((result + getWbiKey()).getBytes());
            String md5 = new BigInteger(1, messageDigest.digest()).toString(16);
            //md5 不满 32 位时左边加 0
            return queryString + "&w_rid=" + ("00000000000000000000000000000000" + md5).substring(md5.length()) + wts;
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }
}
