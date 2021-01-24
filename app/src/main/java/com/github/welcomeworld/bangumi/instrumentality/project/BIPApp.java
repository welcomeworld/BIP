package com.github.welcomeworld.bangumi.instrumentality.project;

import android.app.Application;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.BimiParser;

public class BIPApp extends Application {

    private static BIPApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ParserManager.getInstance().addParser(new BiliParser());
        ParserManager.getInstance().addParser(new BimiParser());
    }

    public static BIPApp getInstance(){
        return mInstance;
    }
}
