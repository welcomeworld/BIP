package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliOkHttpClientManager;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.api.BiliParams;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BiliJsBridgeCallHandlerNetV2 extends AbstractJsBridge {
    @Override
    protected String[] getSupportFunctions() {
        return new String[]{"request", "uploadImage", "requestWithSign", "requestV2", "requestWithSignV2", "getCsrf"};
    }

    @Override
    protected String getTag() {
        return "BiliJsBridgeCallHandlerNetV2";
    }

    @Override
    protected void invokeNative(String methodName, JSONObject jsonData, String callbackId) {
        LogUtil.e("JsBridgeDispatcher", "net invoke" + methodName);
        if (methodName.equals("getCsrf")) {
            JSONObject v1 = new JSONObject();
            v1.put("csrf", "");
            callbackToJS(new Object[]{callbackId, v1});
        } else if (methodName.equals("request")) {
            request(jsonData, callbackId);
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

    private void request(JSONObject jsonData, String callbackId) {
        String contentType;
        LogUtil.d("BiliJsBridgeCallHandlerNetV2", "net.request is called, data: " + jsonData);
        int v1 = 1;
        if (jsonData == null) {
            callbackToJS(new Object[]{callbackId, "error: data is null"});
            return;
        }

        String url = jsonData.getString("url");
        if (TextUtils.isEmpty(url)) {
            callbackToJS(new Object[]{callbackId, "error: url is null"});
            return;
        }

        String onLoadCallbackId = jsonData.getString("onLoadCallbackId");
//        if(this.v(url, v5)) {    //prefetch
//            return;
//        }

        String policy = jsonData.getString("policy");
        JSONObject headers = jsonData.getJSONObject("header");
        if (headers == null) {
            contentType = "application/x-www-form-urlencoded";
        } else {
            String tempContentType = headers.getString("Content-Type");
            contentType = tempContentType == null ? "application/x-www-form-urlencoded" : tempContentType;
        }

        String v6 = jsonData.getString("data");
        String v10 = v6;
        String method = jsonData.getString("method");
        if (method == null) {
            method = "GET";
        }
        Integer v7_2 = jsonData.getInteger("timeout");
        String v14 = jsonData.getString("csrfKey");
        callbackToJS(new Object[]{callbackId, "ok"});
//        if(("POST".equals(method)) && (v14 != null && !v14.isEmpty())) {
//            String v0 = this.r();
//            if(v0 == null) {
//                v10 = v6;
//            }
//            else {
//                if(v6 != null && !v6.isEmpty()) {
//                    v1 = 0;
//                }
//
//                v10 = v1 == 0 ? v6 + "&" + v14 + "=" + Uri.encode(v0) : v14 + "=" + Uri.encode(v0);
//            }
//        }
//        else {
//            v10 = v6;
//        }

        Request.Builder builder = new Request.Builder().url(url);
        method(builder, method, contentType, v10, policy);
        initHeader(builder, headers, method);
        BiliOkHttpClientManager.getInstance().getOkHttpClient().newCall(builder.build()).enqueue(new Callback() {
            JSONObject jsonObject = new JSONObject();

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                jsonObject.put("httpStatus", -1);
                callbackToJS(new Object[]{onLoadCallbackId, jsonObject});
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                String v4 = null;
                if ((response.isSuccessful()) && response.body() != null) {
                    try {
                        ResponseBody body = response.body();
                        if (body == null) {
                            throw new IOException();
                        }
                        v4 = body.string();
                        LogUtil.d("BiliJsBridgeCallHandlerNetV2", "response body: " + v4);
                    } catch (Exception v3) {
                        try {
                            LogUtil.e("BiliJsBridgeCallHandlerNetV2", v3.getMessage());
                        } catch (Throwable v7) {
                            close(response);
                            throw v7;
                        }
                        close(response);
                    } catch (Throwable v7) {
                        close(response);
                        throw v7;
                    }
                } else {
                    close(response);
                }
                this.jsonObject.put("httpStatus", code);
                this.jsonObject.put("response", v4);
                callbackToJS(new Object[]{onLoadCallbackId, this.jsonObject});
//                BiliJsBridgeCallHandlerNetV2.f(this.b, call, v4, code);
            }

            private void close(Response arg2) {
                if (arg2 != null && arg2.body() != null) {
                    arg2.close();
                }
            }
        });
    }

    private void initHeader(Request.Builder builder, JSONObject headersJson, String method) {
        builder.addHeader("native_api_from", "h5");
        builder.addHeader("Cookie", BiliParams.getBuvid());
        if (headersJson != null) {
            try {
                for (Object v0 : headersJson.entrySet()) {
                    Map.Entry v0_1 = (Map.Entry) v0;
                    builder.addHeader(((String) v0_1.getKey()), v0_1.getValue().toString());
                }
            } catch (Exception v4) {
                LogUtil.e("BiliJsBridgeCallHandlerNetV2", v4.getMessage());
            }
        }

//        if("POST".equals(method)) {
//            String v4_2 = this.r();
//            if(v4_2 != null) {
//                builder.addHeader("X-CSRF-TOKEN", v4_2);
//            }
//        }
    }

    private void method(Request.Builder builder, String method, String contentType, String data, String policy) {
        if (policy != null && policy.hashCode() == 49 && (policy.equals("1"))) {
            if (data != null) {
                if ("application/x-www-form-urlencoded".equals(contentType)) {
                    builder.method(method, this.createFormBody(data, true));
                    return;
                }
                MediaType.get(contentType);

                builder.method(method, RequestBody.create(MediaType.parse(contentType), data));
                return;
            }

            builder.method(method, null);
            return;
        }

        if (data != null) {
            builder.method(method, RequestBody.create(MediaType.parse(contentType), data));
            return;
        }

        builder.method(method, null);
    }

    private FormBody createFormBody(String data, boolean encoded) {
        okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
        for (String entry : data.split("&")) {
            String name = StringsKt.substringBefore(entry, "=", "");
            String value = StringsKt.substringAfter(entry, "=", "");
            if (encoded) {
                builder.addEncoded(name, value);
                continue;
            }
            builder.add(name, value);
        }
        return builder.build();
    }
}
