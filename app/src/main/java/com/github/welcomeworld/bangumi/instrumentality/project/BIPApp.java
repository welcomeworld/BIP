package com.github.welcomeworld.bangumi.instrumentality.project;

import android.app.Application;

import com.github.welcomeworld.bangumi.instrumentality.project.parser.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;

public class BIPApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParserManager.getInstance().addParser(new BiliParser());
    }
}
