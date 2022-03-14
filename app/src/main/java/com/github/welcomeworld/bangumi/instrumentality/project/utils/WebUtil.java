package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

public class WebUtil {
    @SuppressLint("SetJavaScriptEnabled")
    public static void initNormalWebView(WebView webView,ViewGroup fullParentView) {
        //解决在5.0以上cookie无法记住问题
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webView, true);

        //设置scrollBar隐藏
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setScrollbarFadingEnabled(false);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        //自适应
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new NormalWebViewClient());
        webView.setWebChromeClient(new NormalWebChromeClient(fullParentView));
        webView.requestFocus();
    }

    private static class NormalWebChromeClient extends WebChromeClient {
        private View customView;
        CustomViewCallback customCallback;
        private WeakReference<ViewGroup> viewGroupWeakReference;
        protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        NormalWebChromeClient(ViewGroup parentView){
            viewGroupWeakReference = new WeakReference<>(parentView);
        }
        private ViewGroup getViewGroup(){
            return viewGroupWeakReference.get();
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
//            toolbar.setTitle(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            if (customView != null) {
                callback.onCustomViewHidden();
                return;
            }
            ViewGroup parentView = getViewGroup();
            if(parentView == null){
                return;
            }
            parentView.addView(view, COVER_SCREEN_PARAMS);
            parentView.setVisibility(View.VISIBLE);
            customView = view;
            customCallback = callback;
        }

        @Override
        public void onHideCustomView() {
            if (customView == null) {
                return;
            }
            ViewGroup parentView = getViewGroup();
            if(parentView == null){
                return;
            }
            parentView.removeView(customView);
            parentView.setVisibility(View.GONE);
            customView = null;
            customCallback.onCustomViewHidden();
        }
    }

    private static class NormalWebViewClient extends WebViewClient {

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("jsTest", "override" + url);
            Uri uri = Uri.parse(url);
            if (uri.getHost().equalsIgnoreCase("feedback") || uri.getHost().equalsIgnoreCase("assistant")) {
                view.loadUrl("https://github.com/welcomeworld/SimpleBili/issues");
                return true;
            }
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

    }
}
