package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliParams;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.widget.BiliWebView;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

public class BiliJsBridgeCallHandlerGlobal extends AbstractJsBridge {
    @Override
    protected String[] getSupportFunctions() {
        return new String[]{"getContainerInfo", "closeBrowser"};
    }

    @Override
    protected String getTag() {
        return "BiliJsBridgeCallHandlerGlobal";
    }

    @Override
    protected void invokeNative(String methodName, JSONObject jsonData, String callbackId) {
        LogUtil.e("JsBridgeDispatcher", "global invoke" + methodName);
        if (methodName.equals("getContainerInfo")) {
            getContainerInfo(jsonData, callbackId);
        } else if (methodName.equals("closeBrowser")) {
            closeBrowser(callbackId);
        }
    }

    @Override
    protected void release() {

    }

    @Override
    String getJsCallbackKey() {
        return "window.biliInject.biliCallbackReceived";
    }

    @Override
    String getJsInterfaceKey() {
        return "biliInject";
    }

    private void getContainerInfo(JSONObject jsonData, String callbackId) {
        if (!TextUtils.isEmpty(callbackId)) {
            JSONObject v0 = new JSONObject();
            v0.put("platform", "android");
            v0.put("device", "phone");
            v0.put("build", BiliParams.getVersionCode());
            String v1 = Build.MODEL;
            if (TextUtils.isEmpty(v1)) {
                v1 = Build.BRAND;
                if (TextUtils.isEmpty(v1)) {
                    v1 = Build.MANUFACTURER;
                }
            }

            if (!TextUtils.isEmpty(v1)) {
                v0.put("modelName", v1);
            }
            v0.put("networkState", 2);
            v0.put("containerName", "mainsite web container 1.0");
            v0.put("channel", BiliParams.getChannel());
            v0.put("osVer", Build.VERSION.RELEASE);
            v0.put("mobi_app", "android");
            v0.put("appKey", BiliParams.getAppKey());
            v0.put("buvid", BiliParams.getBuvid());
            v0.put("localFingerprint", "");
            v0.put("fingerprint", "");
            v0.put("deviceName", BiliParams.getDeviceName());
            v0.put("devicePlatform", BiliParams.getDevicePlatform());
//            JSONObject v8_1 = v8.getExtraInfoContainerInfo();
//            if(v8_1 != null && !v8_1.isEmpty()) {
//                for(Object v5: v8_1.keySet()) {
//                    String v5_1 = (String)v5;
//                    v0.put(v5_1, v8_1.get(v5_1));
//                }
//            }

            callbackToJS(new Object[]{callbackId, v0});
        }
    }

    private void closeBrowser(String callbackId) {
        if (!TextUtils.isEmpty(callbackId)) {
            callbackToJS(new Object[]{callbackId});
        }
        BiliWebView biliWebView = getBiliWebView();
        if (biliWebView != null && biliWebView.getContext() instanceof Activity) {
            ((Activity) biliWebView.getContext()).finish();
        }
    }
}
