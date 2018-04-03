package com.tado.android.notifications.nonpremium;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Util;

public abstract class OneTimeBaseNotification {
    protected abstract Notification getNotification(Context context);

    public int getNotificationId() {
        return getClass().getCanonicalName().hashCode();
    }

    public void notify(Context ctx) {
        getNotificationManager(ctx).notify(getNotificationId(), getNotification(ctx));
        AnalyticsHelper.trackEvent((TadoApplication) ctx.getApplicationContext(), Events.NOTIFICATIONS, "showNotification", getClass().getSimpleName(), Long.valueOf(0));
    }

    public void dismiss(Context ctx) {
        getNotificationManager(ctx).cancel(getNotificationId());
    }

    CharSequence getNotificationTextForId(Context ctx, int resId) {
        return Util.getText(ctx, resId, ctx.getString(C0676R.string.app_name));
    }

    private static NotificationManager getNotificationManager(Context ctx) {
        return (NotificationManager) ctx.getSystemService("notification");
    }

    long[] getVibrationPattern() {
        return new long[]{1000, 1000};
    }
}
