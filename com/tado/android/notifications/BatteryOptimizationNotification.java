package com.tado.android.notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.tado.C0676R;
import com.tado.android.utils.Util;

class BatteryOptimizationNotification extends BaseNotification {
    static final int DATA = 3;

    BatteryOptimizationNotification() {
    }

    public int getNotificationId() {
        return 3;
    }

    public boolean shouldShow(Context ctx) {
        return VERSION.SDK_INT >= 23 && Util.hasBatteryOptimization(ctx);
    }

    @SuppressLint({"InlinedApi"})
    private Intent createNotificationIntent(Context ctx) {
        return new Intent("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.LOCATION_CHANNEL).setContentTitle(getNotificationTextForId(ctx, C0676R.string.notifications_ignoreBatteryOptimization_title)).setContentText(getNotificationTextForId(ctx, C0676R.string.notifications_ignoreBatteryOptimization_description)).setSmallIcon(C0676R.drawable.logo_white_notification).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(ctx), 134217728)).addAction(createDismissForeverAction(ctx)).setStyle(new BigTextStyle().bigText(getNotificationTextForId(ctx, C0676R.string.notifications_ignoreBatteryOptimization_description))).build();
    }
}
