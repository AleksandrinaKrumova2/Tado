package com.tado.android.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.tado.android.app.TadoApplication;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import org.joda.time.DateTime;

@Deprecated
public class CheckLocationServicesReceiver extends WakefulBroadcastReceiver {
    private static String TAG = "CheckLocationServicesReceiver";
    public static final int WINDOW_LENGTH_MILLIS = 300000;

    @Deprecated
    public static synchronized void setupNextAlarm(Context context) {
        synchronized (CheckLocationServicesReceiver.class) {
            long nextAlarmMillis = System.currentTimeMillis() + ((long) TadoApplication.locationManager.getLocationConfiguration().getWakeupIntervalMillis());
            Snitcher.start().toLogger().log(4, TAG, "Setting up next alarm at %s (%d minutes difference)", new DateTime(nextAlarmMillis).toString(), Long.valueOf(((long) TadoApplication.locationManager.getLocationConfiguration().getWakeupIntervalMillis()) / 60000));
            PendingIntent pendingIntent = getCheckLocationServicesIntent(context);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (VERSION.SDK_INT >= 23) {
                alarmManager.setAndAllowWhileIdle(0, nextAlarmMillis, pendingIntent);
            } else if (VERSION.SDK_INT >= 19) {
                alarmManager.setWindow(0, nextAlarmMillis, Constants.WAIT_FOR_FIRMWARE_UPDATE_TIMEOUT, pendingIntent);
            } else {
                alarmManager.set(0, nextAlarmMillis, pendingIntent);
            }
        }
    }

    private static PendingIntent getCheckLocationServicesIntent(Context ctx) {
        return PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, CheckLocationServicesReceiver.class), 134217728);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
        r7 = this;
        r2 = "power";
        r2 = r8.getSystemService(r2);
        r2 = (android.os.PowerManager) r2;
        r3 = 1;
        r4 = TAG;
        r1 = r2.newWakeLock(r3, r4);
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r1.acquire(r2);
        r2 = com.tado.android.utils.UserConfig.isLocationBasedControlEnabled();	 Catch:{ Exception -> 0x004a }
        if (r2 == 0) goto L_0x0044;
    L_0x001b:
        r2 = com.tado.android.utils.Snitcher.start();	 Catch:{ Exception -> 0x004a }
        r2 = r2.toLogger();	 Catch:{ Exception -> 0x004a }
        r3 = 3;
        r4 = TAG;	 Catch:{ Exception -> 0x004a }
        r5 = "checking if location tracking is till running (aka LocationServicesLifeline)";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x004a }
        r2.log(r3, r4, r5, r6);	 Catch:{ Exception -> 0x004a }
        r2 = com.tado.android.app.TadoApplication.locationManager;	 Catch:{ Exception -> 0x004a }
        r3 = com.tado.android.location.LocationAcquisitionMode.LAST_KNOWN_LOCATION;	 Catch:{ Exception -> 0x004a }
        r4 = com.tado.android.location.LocationTrigger.ALARM;	 Catch:{ Exception -> 0x004a }
        r2.postLastKnownLocation(r3, r4);	 Catch:{ Exception -> 0x004a }
        r2 = com.tado.android.app.TadoApplication.locationManager;	 Catch:{ Exception -> 0x004a }
        r2.startTrackingIfEnabled();	 Catch:{ Exception -> 0x004a }
        setupNextAlarm(r8);	 Catch:{ Exception -> 0x004a }
    L_0x0040:
        r1.release();
    L_0x0043:
        return;
    L_0x0044:
        r2 = com.tado.android.app.TadoApplication.locationManager;	 Catch:{ Exception -> 0x004a }
        r2.stopTracking();	 Catch:{ Exception -> 0x004a }
        goto L_0x0040;
    L_0x004a:
        r0 = move-exception;
        r2 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x005a }
        r2 = r2.toLogger();	 Catch:{ all -> 0x005a }
        r2.logException(r0);	 Catch:{ all -> 0x005a }
        r1.release();
        goto L_0x0043;
    L_0x005a:
        r2 = move-exception;
        r1.release();
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.location.CheckLocationServicesReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
