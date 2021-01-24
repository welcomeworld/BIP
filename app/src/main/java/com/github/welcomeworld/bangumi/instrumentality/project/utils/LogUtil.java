package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.util.Log;

import com.github.welcomeworld.bangumi.instrumentality.project.BuildConfig;

public class LogUtil {
    static final boolean logSwitch = BuildConfig.DEBUG;
    public static void e(String tag, String msg){
        if(logSwitch){
            Log.e(tag,msg);
        }
    }
}
