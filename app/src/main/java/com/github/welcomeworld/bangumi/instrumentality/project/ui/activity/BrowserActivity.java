package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityBrowserBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.WebUtil;

public class BrowserActivity extends BaseActivity<ActivityBrowserBinding> {
    public static final String EXTRA_URL = "extra_url";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mUrl = getIntent().getStringExtra(EXTRA_URL);
        WebUtil.initNormalWebView(getViewBinding().browserWebview, findViewById(android.R.id.content));
        getViewBinding().browserWebview.loadUrl(mUrl);
    }

    @Override
    public void onBackPressed() {
        if (getViewBinding().browserWebview.canGoBack()) {
            getViewBinding().browserWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
