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

    public static void i(String tag, String msg) {
        if(logSwitch){
            Log.i(tag,msg);
        }
    }

    public static void w(String tag, String msg) {
        if(logSwitch){
            Log.w(tag,msg);
        }
    }

    public static void d(String tag, String msg) {
        if(logSwitch){
            Log.d(tag,msg);
        }
    }
}
