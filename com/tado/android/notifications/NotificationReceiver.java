package com.tado.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("DISMISS_FOREVER_INTENT_EXTRA_CLASS_NAME", -1);
        if (notificationId != -1) {
            ((BaseNotification) NotificationUtil.notifications.get(Integer.valueOf(notificationId))).dismissForever(context);
        }
    }
}
