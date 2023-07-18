package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import com.github.welcomeworld.bangumi.instrumentality.project.BIPApp;

public class UIUtil {
    public static int dp2px(int dp) {
        final float scale = BIPApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
