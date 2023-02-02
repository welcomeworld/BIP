package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import android.os.Looper;

import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

public class HistoryConfig {

    public static void updateOrSaveHistory(HistoryBean data) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            AppBaseDatabase.getInstance().getHistoryDao().setHistory(data);
        } else {
            ThreadUtil.defer().when(() -> AppBaseDatabase.getInstance().getHistoryDao().setHistory(data));
        }
    }

    public static List<HistoryBean> getHistory() {
        return AppBaseDatabase.getInstance().getHistoryDao().getAllHistory();
    }

    public static List<HistoryBean> getFav() {
        List<HistoryBean> result = new ArrayList<>();
        List<HistoryBean> allFav = AppBaseDatabase.getInstance().getHistoryDao().getAllFav();
        for (HistoryBean fav : allFav) {
            List<VideoListBean> videoList = fav.getVideoData();
            if (videoList != null && videoList.size() > fav.getSelectSourceIndex()) {
                result.add(fav);
            } else if (videoList == null || videoList.size() == 0) {
                AppBaseDatabase.getInstance().getHistoryDao().deleteHistory(fav);
            }
        }
        return result;
    }

    public static HistoryBean findHistory(int uid, String vid) {
        return AppBaseDatabase.getInstance().getHistoryDao().findHistory(uid, vid);
    }
}
