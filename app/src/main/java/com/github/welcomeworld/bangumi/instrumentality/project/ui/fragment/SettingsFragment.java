package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.welcomeworld.app_update.UpdateManager;
import com.github.welcomeworld.bangumi.instrumentality.project.BuildConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentSettingsBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.SimpleContainerActivity;
import com.github.welcomeworld.devbase.utils.KVUtil;

public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> implements View.OnClickListener {
    public static String TAG = "setting_fragment";
    private static final String SETTING_BACKUP_UPDATE = "setting_backup_update";
    private static final String SETTING_MEDIACODEC = "setting_mediacodec";
    private static final String SETTING_EXOPLAYER = "setting_exoplayer";

    private static final String SETTING_FULL_DEFAULT = "setting_full_default";


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getVB().topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(view.getContext());
        getVB().settingVersionName.setText(BuildConfig.VERSION_NAME);
        getVB().settingVersion.setOnClickListener(this);
        getVB().settingOpensource.setOnClickListener(this);
        getVB().settingBackup.setSelected(KVUtil.getBoolean(SETTING_BACKUP_UPDATE));
        getVB().settingBackup.setOnClickListener(this);
        getVB().settingMediaCodec.setSelected(KVUtil.getBoolean(SETTING_MEDIACODEC));
        getVB().settingMediaCodec.setOnClickListener(this);
        getVB().settingExoPlayer.setSelected(KVUtil.getBoolean(SETTING_EXOPLAYER));
        getVB().settingExoPlayer.setOnClickListener(this);
        getVB().settingFullDefault.setSelected(KVUtil.getBoolean(SETTING_FULL_DEFAULT));
        getVB().settingFullDefault.setOnClickListener(this);
        getVB().settingLogout.setVisibility(BiliParser.checkLogin() ? View.VISIBLE : View.GONE);
        getVB().settingLogout.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setting_version) {
            if (KVUtil.getBoolean(SETTING_BACKUP_UPDATE)) {
                UpdateManager.checkUpdate(view.getContext(), "https://gitee.com/nisigada/software-update/raw/master/bip/version_bak.json", BuildConfig.VERSION_NAME);
            } else {
                UpdateManager.checkUpdate(view.getContext(), "https://gitee.com/nisigada/software-update/raw/master/bip/version.json", BuildConfig.VERSION_NAME);
            }
        } else if (id == R.id.setting_opensource) {
            SimpleContainerActivity.addFragment(getActivity(), GratitudeFragment.class, null);
        } else if (id == R.id.setting_logout) {
            BiliParser.clearLoginStatus();
            getVB().settingLogout.setVisibility(View.GONE);
        } else if (id == R.id.setting_exoPlayer) {
            view.setSelected(!view.isSelected());
            KVUtil.putBoolean(SETTING_EXOPLAYER, view.isSelected());
        } else if (id == R.id.setting_backup) {
            view.setSelected(!view.isSelected());
            KVUtil.putBoolean(SETTING_BACKUP_UPDATE, view.isSelected());
        } else if (id == R.id.setting_mediaCodec) {
            view.setSelected(!view.isSelected());
            KVUtil.putBoolean(SETTING_MEDIACODEC, view.isSelected());
        } else if (id == R.id.setting_full_default) {
            view.setSelected(!view.isSelected());
            KVUtil.putBoolean(SETTING_FULL_DEFAULT, view.isSelected());
        }
    }

    public static boolean useMediaCodec() {
        return KVUtil.getBoolean(SETTING_MEDIACODEC);
    }

    public static boolean useExoPlayer() {
        return KVUtil.getBoolean(SETTING_EXOPLAYER);
    }

    public static boolean fullDefault() {
        return KVUtil.getBoolean(SETTING_FULL_DEFAULT);
    }
}
