package com.tado.android.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.tado.C0676R;
import com.tado.android.login.LoginActivity;
import java.util.Locale;
import org.joda.time.DateTime;

public class LogoutNotification extends BaseNotification {
    private static final int NOTIFICATION_ID_LOGOUT = 182;

    protected int getNotificationId() {
        return NOTIFICATION_ID_LOGOUT;
    }

    public boolean shouldShow(Context ctx) {
        return false;
    }

    protected Notification getNotification(Context ctx) {
        return new Builder(ctx).setContentTitle("Tado has logged out").setContentText("Tado has received a 401").setSmallIcon(C0676R.drawable.logo_white_notification).setOnlyAlertOnce(true).setAutoCancel(true).setStyle(new BigTextStyle().bigText(String.format(Locale.US, "Tado has received a 401 at %s", new Object[]{new DateTime().toString()}))).setContentIntent(PendingIntent.getActivity(ctx, 0, new Intent(ctx, LoginActivity.class), 134217728)).build();
    }
}
