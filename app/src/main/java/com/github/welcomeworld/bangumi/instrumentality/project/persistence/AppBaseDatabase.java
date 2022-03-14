package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;

@Database(entities = {HistoryBean.class},version = 1)
@TypeConverters({AppBaseConverter.class})
public abstract class AppBaseDatabase extends RoomDatabase {
    private static class AppBaseDatabaseHolder {
        private static final AppBaseDatabase instance = Room.databaseBuilder(BIPApp.getInstance(),AppBaseDatabase.class,"baseDb").fallbackToDestructiveMigration().build();
    }

    public static AppBaseDatabase getInstance() {
        return AppBaseDatabase.AppBaseDatabaseHolder.instance;
    }
    abstract HistoryDao getHistoryDao();
}
