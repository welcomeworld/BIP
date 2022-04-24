package com.github.welcomeworld.bangumi.instrumentality.project.utils;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.activity.SimpleContainerActivity;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment.DownloadFragment;


public class NotifyManager {
    private static final String CHANNEL_DOWNLOAD_DESCRIPTION = "BIP Download";
    private static final String CHANNEL_DOWNLOAD_ID = "BIP download media";
    private static final String CHANNEL_DOWNLOAD_NAME = "BIP download media Channel";

    @SuppressLint("UnspecifiedImmutableFlag")
    public static void showDownloadNotify(Context context, int notifyId, int progress, String contentTitle) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_DOWNLOAD_ID);
        String title = contentTitle + "-" + context.getString(R.string.downloading);
        Intent intent = new Intent(context, SimpleContainerActivity.class);
        intent.putExtras(SimpleContainerActivity.getStartBundle(DownloadFragment.class.getName(), null));
        PendingIntent contentIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        builder.setContentTitle(title)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_download)
                .setProgress(100, progress, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(CHANNEL_DOWNLOAD_ID, CHANNEL_DOWNLOAD_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationChannel.setDescription(NotifyManager.CHANNEL_DOWNLOAD_DESCRIPTION);
            manager.createNotificationChannel(mNotificationChannel);
        }
        manager.notify(notifyId, builder.build());
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    public static void showDownloadCompletedNotify(Context context, int notifyId, String content) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_DOWNLOAD_ID);
        Intent intent = new Intent(context, SimpleContainerActivity.class);
        intent.putExtras(SimpleContainerActivity.getStartBundle(DownloadFragment.class.getName(), null));
        PendingIntent contentIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        builder.setContentTitle(context.getString(R.string.downloaded))
                .setContentText(content)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_download);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(CHANNEL_DOWNLOAD_ID, CHANNEL_DOWNLOAD_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationChannel.setDescription(NotifyManager.CHANNEL_DOWNLOAD_DESCRIPTION);
            manager.createNotificationChannel(mNotificationChannel);
        }
        manager.notify(notifyId, builder.build());
    }

    public static void cancelNotify(Context context, int notifyId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notifyId);
    }
}
