package com.github.welcomeworld.bangumi.instrumentality.project.persistence;

import androidx.room.Room;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;

public class AppDatabaseManager {
    public static AppBaseDatabase database;
    public static AppBaseDatabase getDatabase(){
        if(database == null){
            init();
        }
        return database;
    }

    public static void init(){
        if(database == null){
            database = Room.databaseBuilder(BIPApp.getInstance(),AppBaseDatabase.class,"appDb").fallbackToDestructiveMigration().build();
        }
    }
}
