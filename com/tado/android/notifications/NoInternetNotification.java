package com.tado.android.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.tado.C0676R;
import com.tado.android.utils.UserConfig;

class NoInternetNotification extends BaseNotification {
    static final int DATA = 1;

    NoInternetNotification() {
    }

    public int getNotificationId() {
        return 1;
    }

    public boolean shouldShow(Context ctx) {
        return (SettingsUtil.isAirplaneModeOn(ctx) || !UserConfig.isLocationBasedControlEnabled() || SettingsUtil.isMobileDataOn(ctx) || SettingsUtil.isWifiOn(ctx)) ? false : true;
    }

    private Intent createNotificationIntent(Context ctx) {
        return new Intent("android.settings.SETTINGS");
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.LOCATION_CHANNEL).setContentTitle(getNotificationTextForId(ctx, C0676R.string.notifications_enableInternetSettings_title)).setContentText(getNotificationTextForId(ctx, C0676R.string.notifications_enableInternetSettings_description)).setSmallIcon(C0676R.drawable.logo_white_notification).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(ctx), 134217728)).addAction(createDismissForeverAction(ctx)).setStyle(new BigTextStyle().bigText(getNotificationTextForId(ctx, C0676R.string.notifications_enableInternetSettings_description))).build();
    }
}
