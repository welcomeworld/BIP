package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityBrowserBinding;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.WebUtil;

public class BrowserActivity extends BaseActivity<ActivityBrowserBinding> {
    public static final String EXTRA_URL = "extra_url";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewBinding().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        String mUrl = getIntent().getStringExtra(EXTRA_URL);
        WebUtil.initNormalWebView(getViewBinding().browserWebview,getViewBinding().browserWebFull);
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

    public void refreshSystemUIVisibility() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
}
