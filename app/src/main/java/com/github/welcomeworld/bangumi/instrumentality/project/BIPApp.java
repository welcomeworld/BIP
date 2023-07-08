package com.github.welcomeworld.bangumi.instrumentality.project;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.AgeFansParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.BimiParser;

public class BIPApp extends MultiDexApplication {

    private static BIPApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ParserManager.getInstance().addParser(new AgeFansParser());
        ParserManager.getInstance().addParser(new BimiParser());
        ParserManager.getInstance().addParser(BiliParser.getInstance());
        ParserManager.getInstance().initParsers();
    }

    public static BIPApp getInstance(){
        return mInstance;
    }
}
