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
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

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
            getVB().bipPlayerView.setBipPlayer(bipPlayer);
            VideoBean currentVideoBean = new VideoBean();
            currentVideoBean.setTitle(guessName(this, uri));
            getVB().bipPlayerView.setCurrentVideoBean(currentVideoBean);
            LogUtil.e("BIPPlayer", "local play" + uri);
            bipPlayer.setDataSource(this, uri);
            bipPlayer.prepareAsync();
            getVB().bipPlayerView.setOnPreparedListener(bp -> getVB().bipPlayerView.setFullScreen(true));
            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            controller.hide(WindowInsetsCompat.Type.systemBars());
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
                int pathIndex = cursor.getColumnIndex(filePathColumn[0]);
                if (pathIndex > 0 && !TextUtils.isEmpty(cursor.getString(pathIndex))) {
                    name = cursor.getString(pathIndex);
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
                    getVB().bipPlayerView.setBattery(level * 100 / scale);
                    break;
                case Intent.ACTION_TIME_TICK:
                    getVB().bipPlayerView.setTime();
                    break;
            }
        }
    };

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (isInMultiWindowMode) {
            if (getVB().bipPlayerView.isFullScreen()) {
                getVB().bipPlayerView.setFullScreen(false);
            }
        }
    }
}

