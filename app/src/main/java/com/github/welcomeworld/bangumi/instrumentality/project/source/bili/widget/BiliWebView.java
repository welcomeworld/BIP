package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliParams;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge.BiliJsBridgeCallHandlerAuth;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge.BiliJsBridgeCallHandlerGlobal;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge.BiliJsBridgeCallHandlerNetV2;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge.BiliSpecialJsBridge;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge.JsBridge;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge.JsBridgeDispatcher;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;


public class BiliWebView extends WebView {
    public BiliWebView(Context context) {
        super(context);
        init();
    }

    public BiliWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BiliWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BiliWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        initJsBridge();
        initWebSetting();
    }

    private void initWebSetting() {
        WebSettings webSettings = getSettings();
        String userAgent = webSettings.getUserAgentString();
        if (!userAgent.contains("Mobile")) {
            userAgent += "Mobile";
        }
        userAgent = userAgent + " os/android model/" + Build.MODEL + " build/" + BiliParams.getVersionCode() + "osVer/" + Build.VERSION.RELEASE + " sdkInt/" + Build.VERSION.SDK_INT + " network/2 BiliApp/" + BiliParams.getVersionCode() + " mobi_app/android channel/bilih5 Buvid/" + BiliParams.getBuvid() + " innerVer/" + BiliParams.getVersionCode() + " c_locale/" + BiliParams.getLocale() + " s_locale/" + BiliParams.getLocale();
        webSettings.setUserAgentString(userAgent);
    }

    private void initJsBridge() {
        LogUtil.e("JsBridgeDispatcher", "addJavascriptInterface biliInject");
        JsBridgeDispatcher jsBridgeDispatcher = new JsBridgeDispatcher();
        jsBridgeDispatcher.setBiliWebView(this);
        addJavascriptInterface(jsBridgeDispatcher, "biliInject");
        addJavascriptInterface(new JsBridge(), "__SmallApp");
        addJavascriptInterface(new BiliSpecialJsBridge(), "biliSpInject");

        BiliJsBridgeCallHandlerAuth biliJsBridgeCallHandlerAuth = new BiliJsBridgeCallHandlerAuth();
        biliJsBridgeCallHandlerAuth.setBiliWebView(this);
        jsBridgeDispatcher.registerJsBridge("auth", biliJsBridgeCallHandlerAuth);

        BiliJsBridgeCallHandlerGlobal biliJsBridgeCallHandlerGlobal = new BiliJsBridgeCallHandlerGlobal();
        biliJsBridgeCallHandlerGlobal.setBiliWebView(this);
        jsBridgeDispatcher.registerJsBridge("global", biliJsBridgeCallHandlerGlobal);

        BiliJsBridgeCallHandlerNetV2 biliJsBridgeCallHandlerNetV2 = new BiliJsBridgeCallHandlerNetV2();
        biliJsBridgeCallHandlerNetV2.setBiliWebView(this);
        jsBridgeDispatcher.registerJsBridge("net", biliJsBridgeCallHandlerNetV2);
    }

}
