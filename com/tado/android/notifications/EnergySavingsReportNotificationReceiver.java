package com.tado.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tado.android.fcm.model.EnergySavingsReportUpdate;
import com.tado.android.utils.Snitcher;

public class EnergySavingsReportNotificationReceiver extends BroadcastReceiver {
    private static String MONTH = "month";
    private static String SAVINGS = "savingsInPercent";

    public static Intent getIntent(Context context, EnergySavingsReportUpdate energySavingsReportUpdate) {
        Intent intent = new Intent(context, EnergySavingsReportNotificationReceiver.class);
        intent.putExtra(MONTH, energySavingsReportUpdate.getMonth());
        intent.putExtra(SAVINGS, energySavingsReportUpdate.getSavingsInPercent());
        return intent;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            new EnergySavingsReportUpdateNotification(intent.getStringExtra(MONTH), intent.getDoubleExtra(SAVINGS, 0.0d)).notify(context);
        } catch (Exception e) {
            Snitcher.start().toLogger().logException(e);
        }
    }
}
