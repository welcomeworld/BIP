package com.github.welcomeworld.bangumi.instrumentality.project;

import android.app.Application;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;

public class BIPApp extends Application {

    private static BIPApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ParserManager.getInstance().addParser(new BiliParser());
    }

    public static BIPApp getInstance(){
        return mInstance;
    }
}
