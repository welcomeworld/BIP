package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebUtil {
    @SuppressLint("SetJavaScriptEnabled")
    public static void initNormalWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new NormalWebViewClient());
        webView.setWebChromeClient(new NormalWebChromeClient());
        webView.requestFocus();
    }

    private static class NormalWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
//            toolbar.setTitle(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.e("jsTest", "onJsAlert" + url);
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Log.e("jsTest", "onJsConfirm" + url);
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Log.e("jsTest", "JsPrompt" + url);
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }

    private static class NormalWebViewClient extends WebViewClient {

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                if(request.getUrl().toString().contains("h5/validate.15900da5b51764d86c4ed457f48a501cda6244c7.js")){
//                    Log.e("JsBridgeDispatcher","intercept"+request.getUrl().toString());
//                    try {
//                        InputStream localJs = view.getContext().getAssets().open("js/bili.js");
//                        WebResourceResponse response = new WebResourceResponse("application/x-javascript","UTF-8",localJs);
//                        return response;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }else {
                if (request.getUrl().toString().contains("passport.bilibili.com")) {
                    Log.e("JsBridgeDispatcher", "not intercept" + request.getUrl().toString());
//                        if(request.getUrl().toString().contains("code=")){
//                            BiliParser.acquireAccessTokenV2(request.getUrl().getQueryParameter("code"));
//                        }
                }
//                }
            }
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
