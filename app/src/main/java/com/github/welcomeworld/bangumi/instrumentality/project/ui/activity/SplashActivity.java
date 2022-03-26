package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivitySplashBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.IntentUtil;
import com.nisigada.common.devbase.utils.ThreadUtil;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThreadUtil.postDelayed(500, new Runnable() {
            @Override
            public void run() {
                if (isTaskRoot()) {
                    IntentUtil.intentToMain(SplashActivity.this, null);
                }
                finish();
            }
        });
    }

    @Override
    public void refreshSystemUIVisibility() {
        //do nothing
    }
}
