package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.webkit.JavascriptInterface;

import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

public class JsBridge {
    @JavascriptInterface
    public final String postMessage(String message){
        LogUtil.e("JsBridge postMessage", message);
        return "";
    }
}
