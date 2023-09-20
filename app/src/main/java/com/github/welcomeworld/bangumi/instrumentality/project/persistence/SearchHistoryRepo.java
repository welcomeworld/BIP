package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.github.welcomeworld.bangumi.instrumentality.project.model.SearchHistory;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.List;

public class SearchHistoryRepo {

    public static void updateOrSaveHistory(SearchHistory data) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            AppBaseDatabase.getInstance().getSearchHistoryDao().setSearchHistory(data);
        } else {
            ThreadUtil.defer().when(() -> AppBaseDatabase.getInstance().getSearchHistoryDao().setSearchHistory(data));
        }
    }

    public static LiveData<List<String>> getHistory() {
        return AppBaseDatabase.getInstance().getSearchHistoryDao().getAllSearchHistory();
    }

    public static void deleteAllHistory() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            AppBaseDatabase.getInstance().getSearchHistoryDao().deleteAllSearchHistory();
        } else {
            ThreadUtil.defer().when(() -> AppBaseDatabase.getInstance().getSearchHistoryDao().deleteAllSearchHistory());
        }
    }
}
