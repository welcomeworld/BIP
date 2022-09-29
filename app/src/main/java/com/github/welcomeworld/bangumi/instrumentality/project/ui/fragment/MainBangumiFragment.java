package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainBangumiBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.source.agefans.AgeFansNetApi;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.WebUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

public class MainBangumiFragment extends BaseFragment<FragmentMainBangumiBinding> {
    boolean goDetail = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebUtil.initNormalWebView(getViewBinding().bangumiWebview, getViewBinding().bangumiWebFullContainer);
        getViewBinding().bangumiWebview.setWebViewClient(new WebUtil.NormalWebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().getPath().contains("/detail/") || request.getUrl().getPath().contains("/play/")) {
                    interceptVideoDetail(request.getUrl().toString());
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (request.getUrl().getPath().contains("/detail/") || request.getUrl().getPath().contains("/play/")) {
                    interceptVideoDetail(request.getUrl().toString());
                    return new WebResourceResponse("application/json", "utf-8", null);
                }
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                ThreadUtil.post(() -> getViewBinding().bangumiWebviewRefresh.setVisibility(View.GONE));
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (!goDetail) {
                    ThreadUtil.post(() -> getViewBinding().bangumiWebviewRefresh.setVisibility(View.VISIBLE));
                }
            }
        });
        getViewBinding().bangumiWebview.loadUrl("https://web.age-spa.com:8443/#/catalog");
        getViewBinding().bangumiWebviewRefresh.setOnClickListener(v -> getViewBinding().bangumiWebview.reload());
    }

    @Override
    public void onStart() {
        super.onStart();
        goDetail = false;
    }

    private void interceptVideoDetail(String url) {
        Uri uri = Uri.parse(url);
        String path = uri.getPath().replace("/v2", "");
        ThreadUtil.post(() -> {
            getViewBinding().bangumiWebview.goBack();
            IntentUtil.intentToVideoPlay(getActivity(), Uri.parse(AgeFansNetApi.baseUrl + path));
        });
        goDetail = true;
    }
}
