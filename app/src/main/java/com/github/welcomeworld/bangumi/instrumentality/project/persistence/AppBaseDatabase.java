package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.model.DownloadInfoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.HistoryBean;
import com.github.welcomeworld.bangumi.instrumentality.project.model.SearchHistory;

@Database(entities = {HistoryBean.class, DownloadInfoBean.class, SearchHistory.class}, version = 3)
@TypeConverters({AppBaseConverter.class})
public abstract class AppBaseDatabase extends RoomDatabase {
    private static class AppBaseDatabaseHolder {
        private static final AppBaseDatabase instance = Room.databaseBuilder(BIPApp.getInstance(), AppBaseDatabase.class, "appDb")
                .addMigrations(MIGRATION1_2, MIGRATION1_3)
                .build();
    }

    public static AppBaseDatabase getInstance() {
        return AppBaseDatabase.AppBaseDatabaseHolder.instance;
    }

    static final Migration MIGRATION1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `download` (`mediaId` TEXT NOT NULL, `title` TEXT, `sourceUrl` TEXT, `downloadState` INTEGER NOT NULL, `localPath` TEXT, `contentLength` INTEGER NOT NULL, `cover` TEXT, `downloadUrl` TEXT, `coverPortrait` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `downloadTime` INTEGER NOT NULL, `sourceName` TEXT, `videoData` TEXT, `selectSourceIndex` INTEGER NOT NULL, `downloadTargetPosition` INTEGER NOT NULL, `downloadingProgress` INTEGER NOT NULL, PRIMARY KEY(`mediaId`))");
        }
    };

    static final Migration MIGRATION1_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `search_history` (`searchKey` TEXT NOT NULL, `searchTime` INTEGER NOT NULL,  PRIMARY KEY(`searchKey`))");
        }
    };

    abstract HistoryDao getHistoryDao();

    abstract DownloadInfoDao getDownloadInfoDao();

    abstract SearchHistoryDao getSearchHistoryDao();
}
