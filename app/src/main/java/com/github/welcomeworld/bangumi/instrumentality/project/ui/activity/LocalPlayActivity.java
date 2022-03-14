package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityLocalPlayBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;
import com.nisigada.common.devbase.utils.FileUtil;
import com.nisigada.common.devbase.utils.ToastUtil;

import java.io.File;

public class LocalPlayActivity extends BaseActivity<ActivityLocalPlayBinding> {
    BIPPlayer bipPlayer = new DefaultBIPPlayer();

    private static boolean checkPermission(Activity activity, String... permissions) {
        boolean permissionGranted = true;
        for (String permission : permissions) {
            int permissionCode = ContextCompat.checkSelfPermission(activity, permission);
            if (permissionCode != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
                try {
                    ActivityCompat.requestPermissions(
                            activity, new String[]{permission}, 666);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return permissionGranted;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSomething();
        checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Uri uri = getIntent().getData();
        if (uri != null) {
            File videoFile = FileUtil.uriToFile(uri, this);
            initPlayer();
            getViewBinding().bipPlayerView.setBipPlayer(bipPlayer);
            VideoBean currentVideoBean = new VideoBean();
            currentVideoBean.setTitle(guessName(videoFile.getName()));
            getViewBinding().bipPlayerView.setCurrentVideoBean(currentVideoBean);
            LogUtil.e("BIPPlayer", "local play" + uri + "with file path" + videoFile.getAbsolutePath());
            bipPlayer.setDataSource(videoFile.getAbsolutePath());
            bipPlayer.prepareAsync();
//            getViewBinding().bipPlayerView.setFullScreen(true);
        } else {
            ToastUtil.showToast(R.string.uri_null_tip);
            finish();
        }
    }

    private void initPlayer() {
        bipPlayer.setOption(DefaultBIPPlayer.OPT_CATEGORY_FORMAT,"allowed_extensions","ALL");
        bipPlayer.setOption(DefaultBIPPlayer.OPT_CATEGORY_PLAYER,"start-on-prepared","1");
    }

    private static String guessName(String name) {
        if (name.indexOf('.') > 0) {
            return name.substring(0, name.lastIndexOf('.'));
        }
        return name;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSomething();
        bipPlayer.release();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void refreshSystemUIVisibility() {
        if (getViewBinding().bipPlayerView.isFullScreen()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {//4.4到5.0
                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.BLACK);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                getWindow().setStatusBarColor(Color.BLACK);
            }
        }
    }

    private void registerSomething() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(playReceiver, intentFilter);
    }

    private void unregisterSomething() {
        unregisterReceiver(playReceiver);
    }

    private final BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Intent.ACTION_BATTERY_CHANGED:
                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                    getViewBinding().bipPlayerView.setBattery(level * 100 / scale);
                    break;
                case Intent.ACTION_TIME_TICK:
                    getViewBinding().bipPlayerView.setTime();
                    break;
            }
        }
    };

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode) {
            if (getViewBinding().bipPlayerView.isFullScreen()) {
                getViewBinding().bipPlayerView.setFullScreen(false);
            }
        }
    }
}

