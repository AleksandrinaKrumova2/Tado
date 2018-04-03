package com.tado.android.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.tado.C0676R;
import com.tado.android.utils.UserConfig;

class LocationSettingsNotification extends BaseNotification {
    static final int DATA = 2;

    LocationSettingsNotification() {
    }

    public int getNotificationId() {
        return 2;
    }

    public boolean shouldShow(Context ctx) {
        return (SettingsUtil.isAirplaneModeOn(ctx) || !UserConfig.isLocationBasedControlEnabled() || SettingsUtil.isLocationServicesOn(ctx)) ? false : true;
    }

    private Intent createNotificationIntent(Context ctx) {
        Intent universalIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        if (universalIntent.resolveActivity(ctx.getPackageManager()) == null) {
            return new Intent("android.settings.SETTINGS");
        }
        return universalIntent;
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.LOCATION_CHANNEL).setContentTitle(getNotificationTextForId(ctx, C0676R.string.notifications_enableLocationSettings_title)).setContentText(getNotificationTextForId(ctx, C0676R.string.notifications_enableLocationSettings_description)).setSmallIcon(C0676R.drawable.logo_white_notification).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(ctx), 134217728)).addAction(createDismissForeverAction(ctx)).setStyle(new BigTextStyle().bigText(getNotificationTextForId(ctx, C0676R.string.notifications_enableLocationSettings_description))).build();
    }
}
