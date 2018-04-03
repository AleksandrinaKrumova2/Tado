package com.tado.android.utils;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import com.tado.android.location.LocationUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DebugUtil {
    private static final String TAG = "DebugUtil";
    private static int batteryLevel = -1;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.GERMANY);
    public static final Lock debugLock = new ReentrantLock();
    private static final String debugStringTemplate = "attempt-%d-unsentFixes-%d-";
    private static List<Location> droppedLocations = new ArrayList();

    public static int getBatteryLevel() {
        return batteryLevel;
    }

    public static void setBatteryLevel(int lvl) {
        batteryLevel = lvl;
    }

    public static List<Location> getDroppedLocations() {
        return droppedLocations;
    }

    public static void logDroppedLocation(Location location) {
        droppedLocations.add(location);
    }

    public static String getIntentLog(Intent intent) {
        if (intent == null) {
            return "";
        }
        String out = ("" + "action: " + intent.getAction()) + "\ncomponent: " + intent.getComponent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return out;
        }
        for (String key : extras.keySet()) {
            out = out + "\nkey [" + key + "]: " + extras.get(key);
        }
        return out;
    }

    public static String getLocationLog(List<Location> locations) {
        StringBuilder log = new StringBuilder("[");
        for (Location location : locations) {
            log.append(String.format(Locale.US, "%f, %f (%f) %s: %s\n", new Object[]{Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getAccuracy()), dateFormat.format(new Date(location.getTime())), LocationUtil.getDroppedReasons(location)}));
        }
        log.append("]");
        return log.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDebugString(int r10, android.location.Location r11) {
        /*
        r0 = "";
        r2 = debugLock;	 Catch:{ Exception -> 0x00d9 }
        r2.lock();	 Catch:{ Exception -> 0x00d9 }
        r2 = java.util.Locale.US;	 Catch:{ Exception -> 0x00d9 }
        r3 = "attempt-%d-unsentFixes-%d-";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x00d9 }
        r5 = 0;
        r6 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x00d9 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00d9 }
        r5 = 1;
        r6 = droppedLocations;	 Catch:{ Exception -> 0x00d9 }
        r6 = r6.size();	 Catch:{ Exception -> 0x00d9 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x00d9 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00d9 }
        r0 = java.lang.String.format(r2, r3, r4);	 Catch:{ Exception -> 0x00d9 }
        r2 = droppedLocations;	 Catch:{ Exception -> 0x00d9 }
        r2 = r2.isEmpty();	 Catch:{ Exception -> 0x00d9 }
        if (r2 != 0) goto L_0x005f;
    L_0x0030:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d9 }
        r2.<init>();	 Catch:{ Exception -> 0x00d9 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x00d9 }
        r3 = "-dropped ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00d9 }
        r3 = droppedLocations;	 Catch:{ Exception -> 0x00d9 }
        r3 = r3.size();	 Catch:{ Exception -> 0x00d9 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00d9 }
        r3 = " Locations because did not pass the filters, locations dropped: ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00d9 }
        r3 = droppedLocations;	 Catch:{ Exception -> 0x00d9 }
        r3 = getLocationLog(r3);	 Catch:{ Exception -> 0x00d9 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00d9 }
        r0 = r2.toString();	 Catch:{ Exception -> 0x00d9 }
    L_0x005f:
        if (r11 == 0) goto L_0x00cf;
    L_0x0061:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d9 }
        r2.<init>();	 Catch:{ Exception -> 0x00d9 }
        r3 = r2.append(r0);	 Catch:{ Exception -> 0x00d9 }
        r2 = com.tado.android.location.LocationUtil.isMockLocation(r11);	 Catch:{ Exception -> 0x00d9 }
        if (r2 == 0) goto L_0x00d5;
    L_0x0070:
        r2 = "MOCK";
    L_0x0073:
        r2 = r3.append(r2);	 Catch:{ Exception -> 0x00d9 }
        r0 = r2.toString();	 Catch:{ Exception -> 0x00d9 }
        r2 = r11.getExtras();	 Catch:{ Exception -> 0x00d9 }
        if (r2 == 0) goto L_0x00cf;
    L_0x0081:
        r2 = r11.getExtras();	 Catch:{ Exception -> 0x00d9 }
        r3 = "overriden";
        r2 = r2.containsKey(r3);	 Catch:{ Exception -> 0x00d9 }
        if (r2 == 0) goto L_0x00cf;
    L_0x008e:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d9 }
        r2.<init>();	 Catch:{ Exception -> 0x00d9 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x00d9 }
        r3 = java.util.Locale.US;	 Catch:{ Exception -> 0x00d9 }
        r4 = " real-loc: %f,%f";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x00d9 }
        r6 = 0;
        r7 = r11.getExtras();	 Catch:{ Exception -> 0x00d9 }
        r8 = "realLat";
        r8 = r7.getDouble(r8);	 Catch:{ Exception -> 0x00d9 }
        r7 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x00d9 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x00d9 }
        r6 = 1;
        r7 = r11.getExtras();	 Catch:{ Exception -> 0x00d9 }
        r8 = "realLon";
        r8 = r7.getDouble(r8);	 Catch:{ Exception -> 0x00d9 }
        r7 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x00d9 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x00d9 }
        r3 = java.lang.String.format(r3, r4, r5);	 Catch:{ Exception -> 0x00d9 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00d9 }
        r0 = r2.toString();	 Catch:{ Exception -> 0x00d9 }
    L_0x00cf:
        r2 = debugLock;
        r2.unlock();
    L_0x00d4:
        return r0;
    L_0x00d5:
        r2 = "";
        goto L_0x0073;
    L_0x00d9:
        r1 = move-exception;
        r2 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x00eb }
        r2 = r2.toLogger();	 Catch:{ all -> 0x00eb }
        r2.logException(r1);	 Catch:{ all -> 0x00eb }
        r2 = debugLock;
        r2.unlock();
        goto L_0x00d4;
    L_0x00eb:
        r2 = move-exception;
        r3 = debugLock;
        r3.unlock();
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.utils.DebugUtil.getDebugString(int, android.location.Location):java.lang.String");
    }
}
