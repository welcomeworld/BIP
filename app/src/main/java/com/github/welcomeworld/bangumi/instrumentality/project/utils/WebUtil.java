package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

public class WebUtil {
    @SuppressLint("SetJavaScriptEnabled")
    public static void initNormalWebView(WebView webView, ViewGroup fullParentView) {
        //解决在5.0以上cookie无法记住问题
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }

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
        private final WeakReference<ViewGroup> viewGroupWeakReference;
        protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        NormalWebChromeClient(ViewGroup parentView) {
            viewGroupWeakReference = new WeakReference<>(parentView);
        }

        private ViewGroup getViewGroup() {
            return viewGroupWeakReference.get();
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            if (customView != null) {
                callback.onCustomViewHidden();
                return;
            }
            ViewGroup parentView = getViewGroup();
            if (parentView == null) {
                return;
            }
            parentView.addView(view, COVER_SCREEN_PARAMS);
            parentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            customView = view;
            customCallback = callback;
        }

        @Override
        public void onHideCustomView() {
            if (customView == null) {
                return;
            }
            ViewGroup parentView = getViewGroup();
            if (parentView == null) {
                return;
            }
            parentView.removeView(customView);
            customView = null;
            customCallback.onCustomViewHidden();
        }
    }

    public static class NormalWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            if (uri.getHost().equalsIgnoreCase("feedback") || uri.getHost().equalsIgnoreCase("assistant")) {
                view.loadUrl("https://github.com/welcomeworld/SimpleBili/issues");
                return true;
            } else if (!uri.getHost().equalsIgnoreCase("http") && uri.getHost().equalsIgnoreCase("https")) {
                handleAppLink();
                return true;
            }
            return false;
        }

        private void handleAppLink() {
            //ignore
        }
    }
}
