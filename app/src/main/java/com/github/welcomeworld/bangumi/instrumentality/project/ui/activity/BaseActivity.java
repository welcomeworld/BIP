package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.dylanc.viewbinding.base.ViewBindingUtil;

import java.lang.reflect.Method;


/**
 * Created by LokiFeng on 2018/1/10.
 * baseActivity
 */

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
    private boolean isResume;
    private VB viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNotch()) {
            setXiaoMiInNotouch();
        }
        setContentView(0);
    }

    @Override
    public void setContentView(int layoutResID) {
        viewBinding = ViewBindingUtil.inflateWithGeneric(this, getLayoutInflater());
        setContentView(viewBinding.getRoot());
    }

    @Override
    protected void onResume() {
        refreshSystemUIVisibility();
        super.onResume();
        isResume = true;
    }

    public void refreshSystemUIVisibility() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }

    public boolean isResume() {
        return isResume;
    }

    private void setXiaoMiInNotouch() {
        int flag = 0x00000100 | 0x00000200 | 0x00000400;
        try {
            Method method = Window.class.getMethod("addExtraFlags",
                    int.class);
            method.invoke(getWindow(), flag);
        } catch (Exception ignore) {
        }
    }

    private static boolean isNotch() {
        try {
            Method getInt = Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, int.class);
            int notch = (int) getInt.invoke(null, "ro.miui.notch", 0);
            return notch == 1;
        } catch (Throwable ignore) {
        }
        return false;
    }

    public VB getViewBinding() {
        return viewBinding;
    }
}
