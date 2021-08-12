package com.github.welcomeworld.devbase.utils;

import com.tencent.mmkv.MMKV;

public class KVUtil {
    private static final MMKV kv = MMKV.defaultMMKV();
    public static void putString(String key,String value){
        kv.putString(key,value);
    }

    public static String getString(String key){
        return getString(key,null);
    }

    public static String getString(String key,String value){
        return kv.getString(key,value);
    }
}
