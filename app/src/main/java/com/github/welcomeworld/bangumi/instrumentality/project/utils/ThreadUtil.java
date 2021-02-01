package com.github.welcomeworld.bangumi.instrumentality.project.utils;
import android.os.Handler;
import android.os.Looper;

import org.jdeferred.android.AndroidDeferredManager;

/**
 * @author Lody
 *         <p>
 *         A set of tools for UI.
 */
public class ThreadUtil {
    private static final AndroidDeferredManager gDM = new AndroidDeferredManager();
    private static final Handler gUiHandler = new Handler(Looper.getMainLooper());

    public static AndroidDeferredManager defer() {
        return gDM;
    }

    public static void post(Runnable r) {
        gUiHandler.post(r);
    }

    public static void postDelayed(long delay, Runnable r) {
        gUiHandler.postDelayed(r, delay);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
