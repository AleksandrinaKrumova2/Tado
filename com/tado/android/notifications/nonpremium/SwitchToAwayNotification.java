package com.tado.android.notifications.nonpremium;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.preference.PreferenceManager;
import com.tado.C0676R;
import com.tado.android.SplashScreenActivity;
import com.tado.android.notifications.NotificationUtil;

public class SwitchToAwayNotification extends OneTimeBaseNotification {
    public void notify(Context ctx) {
        if (NotificationUtil.isAwayModeNotificationEnabled(PreferenceManager.getDefaultSharedPreferences(ctx))) {
            super.notify(ctx);
        }
    }

    private Intent createNotificationIntent(Context context) {
        return new Intent(context, SplashScreenActivity.class);
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.LOCATION_CHANNEL).setContentTitle(ctx.getString(C0676R.string.premiumUpgrade_notifications_geofencing_switchToAwayTitle)).setContentText(getNotificationTextForId(ctx, C0676R.string.premiumUpgrade_notifications_geofencing_switchToAwayMessage)).setVibrate(getVibrationPattern()).setSound(System.DEFAULT_NOTIFICATION_URI).setSmallIcon(C0676R.drawable.ic_away_white).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(ctx), 134217728)).build();
    }
}
