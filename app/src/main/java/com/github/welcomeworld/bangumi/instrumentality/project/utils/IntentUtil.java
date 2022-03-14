package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.BrowserActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.FavOrHistoryActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.LoginContainerActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.MainActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.SearchActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.VideoPlayActivity;

public class IntentUtil {
    public static void intentToMain(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToVideoPlay(Context context, Bundle bundle) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToSearch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToHistory(Context context, Bundle bundle) {
        Intent intent = new Intent(context, FavOrHistoryActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToFav(Context context, Bundle bundle) {
        Intent intent = new Intent(context, FavOrHistoryActivity.class);
        intent.putExtra(FavOrHistoryActivity.EXTRA_PAGE_INDEX, 1);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToLoginContainer(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginContainerActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToBrowser(Context context, String url) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(BrowserActivity.EXTRA_URL, url);
        context.startActivity(intent);
    }


}
