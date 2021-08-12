package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;

@Database(entities = {HistoryBean.class},version = 1)
@TypeConverters({AppBaseConverter.class})
public abstract class AppBaseDatabase extends RoomDatabase {
    abstract HistoryDao getHistoryDao();
}
