package com.github.welcomeworld.bangumi.instrumentality.project;

import android.app.Application;

import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.AgeFansParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliParams;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bimibimi.BimiParser;

public class BIPApp extends Application {

    private static BIPApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        BiliParams.appContext = this;
        ParserManager.getInstance().addParser(new AgeFansParser());
        ParserManager.getInstance().addParser(new BimiParser());
        ParserManager.getInstance().addParser(BiliParser.getInstance());
//        UMConfigure.init(this,"608126355844f15425e47e5b","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
//        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
    }

    public static BIPApp getInstance(){
        return mInstance;
    }
}
