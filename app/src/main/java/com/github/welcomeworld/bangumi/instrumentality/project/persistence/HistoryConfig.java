package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

import java.util.List;

public class HistoryConfig {

    public static void updateOrSaveHistory(HistoryBean data){
        ThreadUtil.defer().when(()->{
            AppDatabaseManager.getDatabase().getHistoryDao().setHistory(data);
        });
    }

    public static List<HistoryBean> getHistory(){
        return AppDatabaseManager.getDatabase().getHistoryDao().getAllHistory();
    }

    public static List<HistoryBean> getFav(){
        return AppDatabaseManager.getDatabase().getHistoryDao().getAllFav();
    }

    public static HistoryBean findHistory(int uid,String vid){
        return AppDatabaseManager.getDatabase().getHistoryDao().findHistory(uid,vid);
    }
}
