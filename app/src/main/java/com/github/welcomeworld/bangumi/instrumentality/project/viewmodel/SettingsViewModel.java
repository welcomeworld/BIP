package com.github.welcomeworld.bangumi.instrumentality.project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.welcomeworld.app_update.UpdateManager;
import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;
import com.github.welcomeworld.bangumi.instrumentality.project.BuildConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.constants.Constants;
import com.github.welcomeworld.bangumi.instrumentality.project.livedata.SafeLiveData;
import com.github.welcomeworld.bangumi.instrumentality.project.persistence.SettingConfig;
import com.github.welcomeworld.bangumi.instrumentality.project.source.bili.BiliParser;

public class SettingsViewModel extends ViewModel {

    private final SafeLiveData<Boolean> biliLoginLive = new SafeLiveData<>(BiliParser.checkLogin());

    public LiveData<Boolean> getBiliLoginLive() {
        return biliLoginLive;
    }

    public LiveData<Boolean> getUseExoLive() {
        return SettingConfig.getUseExoLive();
    }

    public LiveData<Boolean> getUseMediaCodecLive() {
        return SettingConfig.getUseMediaCodecLive();
    }

    public LiveData<Boolean> getFullDefaultLive() {
        return SettingConfig.getFullDefaultLive();
    }

    public void checkUpdate() {
        UpdateManager.checkUpdate(BIPApp.getInstance(), Constants.UPDATE_URL, BuildConfig.VERSION_CODE);
    }

    public void logout() {
        BiliParser.clearLoginStatus();
        biliLoginLive.updateValueSafe(false);
    }

    public void setUseExoPlayer(boolean useExo) {
        SettingConfig.setUseExoPlayer(useExo);
    }

    public void setUseMediaCodec(boolean useMediaCodec) {
        SettingConfig.setUseMediaCodec(useMediaCodec);
    }

    public void setFullDefault(boolean fullDefault) {
        SettingConfig.setFullDefault(fullDefault);
    }
}
