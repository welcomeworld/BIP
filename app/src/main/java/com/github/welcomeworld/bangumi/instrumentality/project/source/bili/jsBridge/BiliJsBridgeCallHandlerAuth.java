package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ThreadUtil;

public final class BiliJsBridgeCallHandlerAuth extends AbstractJsBridge{

    private JSONObject buildCallbackJson(int code, String message) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", message);
        return result;
    }

    private void f(Integer arg3, int arg4, String arg5) {
        if(arg3 != null) {
            this.callbackToJS(new Object[]{arg3, this.buildCallbackJson(arg4, arg5)});
        }
    }

    @Override
    @NonNull
    protected String[] getSupportFunctions() {
        return new String[]{"getUserInfo", "login", "exchangeTicket", "refreshUserInfo"};
    }

    @Override
    @NonNull
    protected String getTag() {
        return "BiliJsBridgeCallHandlerAuth";
    }

    @Override
    protected void invokeNative(@NonNull String methodName, @Nullable JSONObject jsonData, @Nullable String callbackId) {
        LogUtil.e(getTag()+"postMessage", methodName);
        int v5;
        switch(methodName.hashCode()) {
            case 103149417: {
                v5 = methodName.equals("login") ? 1 : -1;
                break;
            }
            case 1082053396: {
                v5 = methodName.equals("refreshUserInfo") ? 3 : -1;
                break;
            }
            case 1811096719: {
                v5 = methodName.equals("getUserInfo") ? 0 : -1;
                break;
            }
            case -444694769: {
                v5 = methodName.equals("exchangeTicket") ? 2 : -1;
                break;
            }
            default: {
                v5 = -1;
            }
        }
        if(v5==2&&jsonData!=null){
            exchangeTicket(jsonData,callbackId);
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

    private void exchangeTicket(JSONObject jsonData, @Nullable String callbackId) {
        if(!TextUtils.isEmpty(callbackId)) {
            callbackToJS(new Object[]{callbackId, buildCallbackJson(0, "")});
        }
        String code = jsonData.getString("ticket");
        if(!TextUtils.isEmpty(code)){
            ThreadUtil.defer().when(()-> BiliParser.acquireAccessTokenV2(code)).done((result)->{
                Integer onExchangeCallbackId = jsonData.getInteger("onExchangeCallbackId");
                if(onExchangeCallbackId==null){
                    return;
                }
                if("success".equals(result)){
                    callbackToJS(new Object[]{onExchangeCallbackId, buildCallbackJson(0, "get account info success")});
                }else {
                    callbackToJS(new Object[]{onExchangeCallbackId, buildCallbackJson(-1, "get account info failed")});
                }
            });
        }
    }

}