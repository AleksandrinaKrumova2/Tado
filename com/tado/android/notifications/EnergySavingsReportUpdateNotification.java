package com.tado.android.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.tado.C0676R;
import com.tado.android.notifications.nonpremium.OneTimeBaseNotification;
import com.tado.android.utils.Util;
import java.util.Locale;

public class EnergySavingsReportUpdateNotification extends OneTimeBaseNotification {
    private String month;
    private double savingsInPercent;

    public EnergySavingsReportUpdateNotification(String month, double savingsInPercent) {
        this.month = month;
        this.savingsInPercent = savingsInPercent;
    }

    public void notify(Context ctx) {
        if (NotificationUtil.isEnergySavingsReportUpdateNotificationEnabled(PreferenceManager.getDefaultSharedPreferences(ctx))) {
            super.notify(ctx);
        }
    }

    private Intent createNotificationIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("tado://energy-savings-report"));
        return intent;
    }

    private CharSequence getNotificationText(Context ctx) {
        String displayMonth = NotificationFormatterKt.getDisplayMonth(this.month, Locale.getDefault());
        if (this.savingsInPercent >= 5.0d) {
            return Util.getText(ctx, C0676R.string.notifications_energySavingsReportAvailable_savingsMessage, displayMonth, NotificationFormatterKt.getFormattedSavings(this.savingsInPercent, Locale.getDefault()));
        }
        return Util.getText(ctx, C0676R.string.notifications_energySavingsReportAvailable_generalMessage, displayMonth);
    }

    public Notification getNotification(Context ctx) {
        return new Builder(ctx, NotificationUtil.GENERAL_CHANNEL).setContentText(getNotificationText(ctx)).setStyle(new BigTextStyle().bigText(getNotificationText(ctx))).setSmallIcon(C0676R.drawable.esr_notification_icon).setOnlyAlertOnce(true).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(ctx, 0, createNotificationIntent(), 134217728)).build();
    }
}
