package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.jsBridge;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.widget.BiliWebView;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;

import java.lang.ref.WeakReference;

public abstract class AbstractJsBridge {
    static final String CONFIG_URL_WHITE_LIST = "webview.jsb_enable_url_pattern";
    private boolean mDebug;
    private final Handler mHandler;
    private WeakReference<BiliWebView> biliWebViewWeakReference;

    public AbstractJsBridge() {
        this.mDebug = false;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public final void callbackToJS(@NonNull Object[] arg6) {
        if (this.isDestroyed()) {
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("javascript:");
        v0.append("try{");
        v0.append(this.getJsCallbackKey());
        v0.append('(');
        int v2;
        for (v2 = 0; v2 < arg6.length; ++v2) {
            Object v3 = arg6[v2];
            if (v3 != null) {
                if (!(v3 instanceof JSONObject) && !(v3 instanceof JSONArray)) {
                    v0.append('\'');
                    v0.append(v3.toString());
                    v0.append('\'');
                } else {
                    v0.append(JSON.toJSONString(v3));
                }

                v0.append(',');
            }
        }

        v0.deleteCharAt(v0.length() - 1);
        v0.append(");");
        v0.append("}catch(error){");
        v0.append("console.error('");
        v0.append(this.getJsInterfaceKey());
        v0.append(":'+error.message);}");
        String v6 = v0.toString();
        LogUtil.i("JsBridgeDispatcher", "evaluateJavascript: script=" + v6);
        this.evaluateJavascript(v6);
    }

    boolean enablePageJsBridge() {
        return true;
    }

    private void evaluateJavascript(String jsString) {
        if (!jsString.startsWith("javascript")) {
            jsString = "javascript:" + jsString;
        }
        String finalJsString = jsString;
        this.runOnUiThread(() -> {
            BiliWebView biliWebView = this.getBiliWebView();
            if (biliWebView == null) {
                LogUtil.w(this.getTag(), "evaluateJavascript fail, webView is null");
                return;
            }
            try {
                biliWebView.evaluateJavascript(finalJsString, null);
                return;
            } catch (Exception v1) {
                LogUtil.w(this.getTag(), "evaluateJavascript error");
            }
            try {
                biliWebView.loadUrl(finalJsString);
            } catch (NullPointerException v5) {
                LogUtil.w(this.getTag(), "loadUrl() to run Javascript error");
            }
        });
    }

    protected abstract String[] getSupportFunctions();

    protected abstract String getTag();

    protected abstract void invokeNative(String methodName, JSONObject jsonData, String callbackId);

    @CallSuper
    public boolean isDestroyed() {
        return false;
    }

    protected abstract void release();

    public final void runOnUiThread(Runnable arg3) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (!this.isDestroyed()) {
                arg3.run();
                return;
            }

            LogUtil.i(this.getTag(), "the host is destroyed before runOnUiThread");
            return;
        }

        this.mHandler.post(() -> {
            if (!this.isDestroyed()) {
                arg3.run();
                return;
            }

            LogUtil.i(this.getTag(), "the host is destroyed before runOnUiThread");
        });
    }

    public void setDebug(boolean arg1) {
        this.mDebug = arg1;
    }

    abstract String getJsCallbackKey();

    abstract String getJsInterfaceKey();

    BiliWebView getBiliWebView(){
        return biliWebViewWeakReference == null?null:biliWebViewWeakReference.get();
    }

    public void setBiliWebView(BiliWebView biliWebView){
        biliWebViewWeakReference = new WeakReference<>(biliWebView);
    }


}
