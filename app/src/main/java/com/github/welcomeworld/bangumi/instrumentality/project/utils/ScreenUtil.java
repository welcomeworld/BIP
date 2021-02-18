package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class ScreenUtil {

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int getStatusBarHeight(Context context) {
        try {
            int result = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getScreenWidth(Context context) {
        try {
            Point outSize = new Point();
            ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealSize(outSize);
            return Math.min(outSize.x, outSize.y);
//            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getScreenHeight(Context context) {
        try {
            Point outSize = new Point();
            ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealSize(outSize);
            return Math.max(outSize.x, outSize.y);
//            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
