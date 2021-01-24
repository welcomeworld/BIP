package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.SearchActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.VideoPlayActivity;

public class IntentUtil {
    public static void intentToVideoPlay(Context context, Bundle bundle){
        Intent intent = new Intent(context, VideoPlayActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void intentToSearch(Context context, Bundle bundle){
        Intent intent = new Intent(context, SearchActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
