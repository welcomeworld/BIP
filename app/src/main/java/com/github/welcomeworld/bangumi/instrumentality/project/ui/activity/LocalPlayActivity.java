package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.ActivityLocalPlayBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoBean;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.LogUtil;
import com.github.welcomeworld.bipplayer.BIPPlayer;
import com.github.welcomeworld.bipplayer.DefaultBIPPlayer;
import com.github.welcomeworld.devbase.utils.ToastUtil;

public class LocalPlayActivity extends BaseActivity<ActivityLocalPlayBinding> {
    BIPPlayer bipPlayer = new DefaultBIPPlayer();

    private static void checkPermission(Activity activity, String... permissions) {
        for (String permission : permissions) {
            int permissionCode = ContextCompat.checkSelfPermission(activity, permission);
            if (permissionCode != PackageManager.PERMISSION_GRANTED) {
                try {
                    ActivityCompat.requestPermissions(
                            activity, new String[]{permission}, 666);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSomething();
        checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Uri uri = getIntent().getData();
        if (uri != null) {
            initPlayer();
            getViewBinding().bipPlayerView.setBipPlayer(bipPlayer);
            VideoBean currentVideoBean = new VideoBean();
            currentVideoBean.setTitle(guessName(this, uri));
            getViewBinding().bipPlayerView.setCurrentVideoBean(currentVideoBean);
            LogUtil.e("BIPPlayer", "local play" + uri);
            bipPlayer.setDataSource(this, uri);
            bipPlayer.prepareAsync();
            getViewBinding().bipPlayerView.setOnPreparedListener(bp -> getViewBinding().bipPlayerView.setFullScreen(true));
        } else {
            ToastUtil.showToast(R.string.uri_null_tip);
            finish();
        }
    }

    private void initPlayer() {
        bipPlayer.setOption(DefaultBIPPlayer.OPT_CATEGORY_FORMAT, "allowed_extensions", "ALL");
        bipPlayer.setOption(DefaultBIPPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", "1");
    }

    private static String guessName(Context context, Uri uri) {
        String name = "";
        if (uri.getScheme().equals("file")) {
            name = uri.getPath();
        } else if (uri.getScheme().equals("content")) {
            ContentResolver contentResolver = context.getContentResolver();
            name = System.currentTimeMillis() + Math.round((Math.random() + 1.0D) * 1000.0D) + "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri));
            String[] filePathColumn = new String[]{"_display_name"};
            Cursor cursor = contentResolver.query(uri, filePathColumn, (String) null, (String[]) null, (String) null);
            if (cursor != null && cursor.moveToFirst()) {
                String tempDisplayName = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                if (!TextUtils.isEmpty(tempDisplayName)) {
                    name = tempDisplayName;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        }
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
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.BLACK);
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

