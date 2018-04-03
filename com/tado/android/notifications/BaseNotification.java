package com.tado.android.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Action.Builder;
import com.tado.C0676R;
import com.tado.android.analytics.AnalyticsConstants.Events;
import com.tado.android.analytics.AnalyticsHelper;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Util;
import java.util.Locale;

public abstract class BaseNotification {
    private static final String DISMISS_FOREVER_INTENT_ACTION = "com.tado.DISMISS_FOREVER_INTENT_ACTION";
    static final String DISMISS_FOREVER_INTENT_EXTRA_CLASS_NAME = "DISMISS_FOREVER_INTENT_EXTRA_CLASS_NAME";
    private final String DISMISSED_BY_USER_KEY = String.format(Locale.US, "%d_%s", new Object[]{Integer.valueOf(getNotificationId()), "DISMISSED"});
    private final String DISMISSED_FOREVER_KEY = String.format(Locale.US, "%d_%s", new Object[]{Integer.valueOf(getNotificationId()), "DISMISSED_FOREVER"});
    private Boolean isDismissedForever;
    private Boolean isShowingOrDismissedByUser;

    protected abstract Notification getNotification(Context context);

    protected abstract int getNotificationId();

    public abstract boolean shouldShow(Context context);

    public void notify(Context ctx) {
        if (!isDismissedForever() && !isShowingOrDismissedByUser()) {
            setShowingOrDismissedByUser(true);
            getNotificationManager(ctx).notify(getNotificationId(), getNotification(ctx));
            AnalyticsHelper.trackEvent((TadoApplication) ctx.getApplicationContext(), Events.NOTIFICATIONS, "showNotification", getClass().getSimpleName(), Long.valueOf(0));
        }
    }

    public void dismiss(Context ctx) {
        getNotificationManager(ctx).cancel(getNotificationId());
        setShowingOrDismissedByUser(false);
    }

    private boolean isDismissedForever() {
        if (this.isDismissedForever == null) {
            this.isDismissedForever = Boolean.valueOf(NotificationConfig.getBoolean(this.DISMISSED_FOREVER_KEY));
        }
        return this.isDismissedForever.booleanValue();
    }

    void dismissForever(Context ctx) {
        this.isDismissedForever = Boolean.valueOf(true);
        NotificationConfig.setBoolean(this.DISMISSED_FOREVER_KEY, true);
        dismiss(ctx);
    }

    private void setShowingOrDismissedByUser(boolean showingOrDismissedByUser) {
        this.isShowingOrDismissedByUser = Boolean.valueOf(showingOrDismissedByUser);
        NotificationConfig.setBoolean(this.DISMISSED_BY_USER_KEY, showingOrDismissedByUser);
    }

    private boolean isShowingOrDismissedByUser() {
        if (this.isShowingOrDismissedByUser == null) {
            this.isShowingOrDismissedByUser = Boolean.valueOf(NotificationConfig.getBoolean(this.DISMISSED_BY_USER_KEY));
        }
        return this.isShowingOrDismissedByUser.booleanValue();
    }

    Action createDismissForeverAction(Context ctx) {
        Intent intent = new Intent(ctx, NotificationReceiver.class);
        intent.setAction(DISMISS_FOREVER_INTENT_ACTION);
        intent.putExtra(DISMISS_FOREVER_INTENT_EXTRA_CLASS_NAME, getNotificationId());
        return new Builder(17301560, ctx.getString(C0676R.string.notifications_dontShowAgainButton), PendingIntent.getBroadcast(ctx, 0, intent, 268435456)).build();
    }

    protected CharSequence getNotificationTextForId(Context ctx, int resId) {
        return Util.getText(ctx, resId, ctx.getString(C0676R.string.app_name));
    }

    public NotificationManager getNotificationManager(Context ctx) {
        return (NotificationManager) ctx.getSystemService("notification");
    }
}
