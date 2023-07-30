package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.dylanc.viewbinding.base.ViewBindingUtil;

import java.lang.reflect.Method;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    public VB getVB() {
        return viewBinding;
    }
}
