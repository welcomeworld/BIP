package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySplashBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getSplashScreen().setOnExitAnimationListener(view -> {
                //do nothing to keep splash screen
            });
        }
        ThreadUtil.postDelayed(500, () -> {
            if (isTaskRoot()) {
                IntentUtil.intentToMain(SplashActivity.this, null);
            }
            finish();
        });
    }
}
