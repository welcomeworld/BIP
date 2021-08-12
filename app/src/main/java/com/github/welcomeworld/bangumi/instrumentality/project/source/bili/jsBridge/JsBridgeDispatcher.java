
package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsBridgeDispatcher extends AbstractJsBridge {
    private final HashMap<String,AbstractJsBridge> jsBridges = new HashMap<>();

    @JavascriptInterface
    public String postMessage(String message) {
        LogUtil.e("JsBridgeDispatcher postMessage", message);
        JSONObject v2_1 = null;
        String callbackId = null;
        if (!this.isDestroyed()) {
            if (this.enablePageJsBridge()) {
                try {
                    if (!TextUtils.isEmpty(message)) {
                        JSONObject v7_1 = JSON.parseObject(message);
                        String methodName = v7_1.getString("method");
                        if (!TextUtils.isEmpty(methodName) && (methodName.contains("."))) {
                            JSONObject jsonData = v7_1.getJSONObject("data");
                            callbackId = jsonData == null ? null : jsonData.getString("callbackId");
                            invokeNative(methodName, jsonData, callbackId);
                        }else {
                            throw new Exception("Invalid method format.");
                        }
                    }else {
                        throw new Exception("Invalid method format.");
                    }
                } catch (Exception v7) {
                    v2_1 = new JSONObject();
                    v2_1.put("error_code", 404);
                    v2_1.put("error_msg", "Invalid method format.");
                    LogUtil.w("JsBridgeDispatcher error", v7.getMessage());
                }
                if (!TextUtils.isEmpty(callbackId) && v2_1 != null) {
                    this.callbackToJS(new Object[]{(callbackId), "ok", v2_1});
                }

                return v2_1 == null ? null : "null";
            }
            return null;
        }
        return null;
    }

    @Override
    @NonNull
    protected String[] getSupportFunctions() {
        List<String> supportFunctions = getBridgeSupportFunctions();
        supportFunctions.add("global.import");
        supportFunctions.add("global.getAllSupport");
        for(String function:supportFunctions){
            LogUtil.d("BridegDispatcher getSupport function",function);
        }
        return supportFunctions.toArray(new String[0]);
    }

    List<String> getBridgeSupportFunctions() {
        ArrayList<String> result = new ArrayList<>();
        for(Map.Entry<String, AbstractJsBridge> entry: jsBridges.entrySet()) {
            String key = entry.getKey();
            String[] supportFunctions = entry.getValue().getSupportFunctions();
            int v5 = 0;
            while (v5 < supportFunctions.length) {
                result.add(key + "." + supportFunctions[v5]);
                ++v5;
            }
        }
        return result;
    }

    @Override
    @NonNull
    protected String getTag() {
        return "JsBridgeDispatcher";
    }

    @Override
    protected void invokeNative(@NonNull String methodName, @Nullable JSONObject jsonData, @Nullable String callbackId) {
        int v0 = methodName.indexOf(".");
        int v1 = -1;
        if (v0 != -1 && v0 != methodName.length() - 1) {
            String v4 = methodName.substring(0, v0);
            String v0_1 = methodName.substring(v0 + 1);
            int hashCode = methodName.hashCode();
            if (hashCode == "global.import".hashCode()) {
                if (methodName.equals("global.import")) {
                    v1 = 0;
                }
            } else if (hashCode == "global.getAllSupport".hashCode() && (methodName.equals("global.getAllSupport"))) {
                v1 = 1;
            }

            if (v1 != 0) {
                if (v1 != 1) {
                    AbstractJsBridge handleBridge = jsBridges.get(v4);
                    if(handleBridge!=null){
                        if(!handleBridge.isDestroyed()){
                            handleBridge.invokeNative(v0_1,jsonData, callbackId);
                        }
                    }
                    return;
                }
                if (!TextUtils.isEmpty(callbackId)) {
                    this.callbackToJS(new Object[]{callbackId, new JSONArray(Arrays.asList(this.getSupportFunctions()))});
                    return;
                }

                LogUtil.e("JsBridgeDispatcher", "getAllSupport: can't get callbackId from data");
                return;
            }

            if (jsonData != null) {
                String v8 = jsonData.getString("namespace");
                if (!TextUtils.isEmpty(v8)) {
//                    if(jsBridges.get(v8)!=null){
//                        //todo import jsBridges
//                    }
                    return;
                }

                LogUtil.e("JsBridgeDispatcher", "import: invalid json data");
                return;
            }

            LogUtil.e("JsBridgeDispatcher", "import: json data is null");
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

    public void registerJsBridge(String namespace,AbstractJsBridge jsBridge){
        jsBridges.put(namespace,jsBridge);
    }
}
