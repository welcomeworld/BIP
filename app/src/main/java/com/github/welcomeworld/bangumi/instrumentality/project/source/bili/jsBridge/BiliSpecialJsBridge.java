package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.webkit.JavascriptInterface;

import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

public class BiliSpecialJsBridge {
    @JavascriptInterface
    public final void setPVInfo(String message) {
        LogUtil.e("BiliSpecialJsBridge setPVInfo", message);

    }
}
