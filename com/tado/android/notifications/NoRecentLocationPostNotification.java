package com.tado.android.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.tado.C0676R;
import com.tado.android.utils.UserConfig;

class NoRecentLocationPostNotification extends BaseNotification {
    static final int DATA = 4;
    private static final String HC_URL = "https://support.tado.com/hc/articles/206081216";
    private static final long NO_RECENT_LOCATION_TIMEOUT_MILLIS = (UserConfig.getLocationConfiguration() != null ? (long) (UserConfig.getLocationConfiguration().getWakeupIntervalMillis() * 3) : 43200000);

    NoRecentLocationPostNotification() {
    }

    public int getNotificationId() {
        return 4;
    }

    public boolean shouldShow(Context ctx) {
        return !SettingsUtil.isAirplaneModeOn(ctx) && UserConfig.isLocationBasedControlEnabled() && SettingsUtil.isLocationServicesOn(ctx) && getTimeElapsed() > NO_RECENT_LOCATION_TIMEOUT_MILLIS;
    }

    private Intent createNotificationIntent() {
        return new Intent("android.intent.action.VIEW", Uri.parse(HC_URL));
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.LOCATION_CHANNEL).setContentTitle(getNotificationTextForId(ctx, C0676R.string.notifications_locationNotSent_title)).setContentText(getNotificationTextForId(ctx, C0676R.string.notifications_locationNotSent_description)).setSmallIcon(C0676R.drawable.logo_white_notification).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(), 134217728)).addAction(createDismissForeverAction(ctx)).setStyle(new BigTextStyle().bigText(getNotificationTextForId(ctx, C0676R.string.notifications_locationNotSent_description))).build();
    }

    private long getTimeElapsed() {
        return System.currentTimeMillis() - Math.max(UserConfig.getLastSeenLocation() != null ? UserConfig.getLastSeenLocation().getTime() : 0, Math.max(UserConfig.getLastTimeAirplaneModeTurnedOff(), UserConfig.getLastTimeBooted()));
    }
}
