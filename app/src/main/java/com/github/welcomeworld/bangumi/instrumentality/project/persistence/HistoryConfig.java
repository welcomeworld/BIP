package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import android.os.Looper;

import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

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
        return AppBaseDatabase.getInstance().getHistoryDao().getAllFav();
    }

    public static HistoryBean findHistory(int uid, String vid) {
        return AppBaseDatabase.getInstance().getHistoryDao().findHistory(uid, vid);
    }
}
