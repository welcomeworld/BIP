package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.github.welcomeworld.bangumi.instrumentality.project.R;


public class NotifyManager {
    public static final int CODE_NOTIFICATION_BAR = 233;
    private static final String CHANNEL_BAR_DESCRIPTION = "App Fast Switch";
    private static final String CHANNEL_BAR_ID = "2Space App Fast Switch";
    private static final String CHANNEL_BAR_NAME = "2Space App Fast Switch Channel";

    public static void showNotify(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(context, NotifyManager.CHANNEL_BAR_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                .setAutoCancel(false)
                .setContentIntent(PendingIntent.getActivities(context,233,new Intent[]{new Intent("fsjdlk")},PendingIntent.FLAG_UPDATE_CURRENT))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_notification_small);
        RemoteViews largeView = new RemoteViews(context.getPackageName(), R.layout.layout_notification_large);
        remoteViews.setTextViewText(R.id.notification_title,"TestTitle");
        remoteViews.setTextViewText(R.id.notification_body,"TestBody");

        largeView.setTextViewText(R.id.notification_title,"TestLargeTitle");
        largeView.setTextViewText(R.id.notification_body,"TestLargefdsfdsfdsfdsBody\nfdsjfljdslj\nfjdslfj");

        builder.setCustomContentView(remoteViews);
        builder.setCustomBigContentView(largeView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(NotifyManager.CHANNEL_BAR_ID, NotifyManager.CHANNEL_BAR_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationChannel.setDescription(NotifyManager.CHANNEL_BAR_DESCRIPTION);
            manager.createNotificationChannel(mNotificationChannel);
        }
        Notification notification = builder.build();
        manager.notify(NotifyManager.CODE_NOTIFICATION_BAR, notification);
        LogUtil.e("NotifyManager","showNotification");
    }
}
