package com.tado.android.notifications.nonpremium;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.System;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.preference.PreferenceManager;
import com.tado.C0676R;
import com.tado.android.notifications.NotificationUtil;
import com.tado.android.utils.Util;
import java.util.List;

public class OpenWindowDetectedNotification extends OneTimeBaseNotification {
    private String notficationUrl;
    private List<Integer> zoneIds;

    public OpenWindowDetectedNotification(List<Integer> zoneIds) {
        this.zoneIds = zoneIds;
    }

    public void notify(Context ctx) {
        if (!NotificationUtil.isOpenWindowDetectionNotificationEnabled(PreferenceManager.getDefaultSharedPreferences(ctx)) || this.zoneIds.size() <= 0) {
            dismiss(ctx);
        } else {
            super.notify(ctx);
        }
    }

    private Intent createNotificationIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("tado://zones/" + this.zoneIds.get(this.zoneIds.size() - 1)));
        return intent;
    }

    public Notification getNotification(Context ctx) {
        CharSequence title;
        CharSequence message;
        if (this.zoneIds.size() > 1) {
            title = Util.getText(ctx, C0676R.string.premiumUpgrade_notifications_openWindowDetection_multiOpenWindowTitle, Integer.valueOf(this.zoneIds.size()));
            message = Util.getText(ctx, C0676R.string.premiumUpgrade_notifications_openWindowDetection_multiOpenWindowMessage, Integer.valueOf(this.zoneIds.size()));
        } else {
            title = Util.getText(ctx, C0676R.string.premiumUpgrade_notifications_openWindowDetection_singleOpenWindowTitle, new Object[0]);
            message = Util.getText(ctx, C0676R.string.premiumUpgrade_notifications_openWindowDetection_singleOpenWindowMessage, new Object[0]);
        }
        return new Builder(ctx, NotificationUtil.LOCATION_CHANNEL).setContentTitle(title).setContentText(message).setVibrate(getVibrationPattern()).setSound(System.DEFAULT_NOTIFICATION_URI).setSmallIcon(C0676R.drawable.ic_owd_white).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(), 134217728)).build();
    }
}
