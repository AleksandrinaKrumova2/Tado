package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzx;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> zzoag = new ArrayDeque(10);

    static boolean zzaj(Bundle bundle) {
        return bundle == null ? false : "1".equals(bundle.getString("google.c.a.e"));
    }

    static void zzq(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleIntent(android.content.Intent r11) {
        /*
        r10 = this;
        r5 = 3;
        r4 = 2;
        r2 = -1;
        r3 = 1;
        r1 = 0;
        r0 = r11.getAction();
        if (r0 != 0) goto L_0x000e;
    L_0x000b:
        r0 = "";
    L_0x000e:
        r6 = r0.hashCode();
        switch(r6) {
            case 75300319: goto L_0x0040;
            case 366519424: goto L_0x0035;
            default: goto L_0x0015;
        };
    L_0x0015:
        r0 = r2;
    L_0x0016:
        switch(r0) {
            case 0: goto L_0x004b;
            case 1: goto L_0x0192;
            default: goto L_0x0019;
        };
    L_0x0019:
        r1 = "FirebaseMessaging";
        r2 = "Unknown intent action: ";
        r0 = r11.getAction();
        r0 = java.lang.String.valueOf(r0);
        r3 = r0.length();
        if (r3 == 0) goto L_0x01a1;
    L_0x002d:
        r0 = r2.concat(r0);
    L_0x0031:
        android.util.Log.d(r1, r0);
    L_0x0034:
        return;
    L_0x0035:
        r6 = "com.google.android.c2dm.intent.RECEIVE";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x0015;
    L_0x003e:
        r0 = r1;
        goto L_0x0016;
    L_0x0040:
        r6 = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x0015;
    L_0x0049:
        r0 = r3;
        goto L_0x0016;
    L_0x004b:
        r0 = "google.message_id";
        r6 = r11.getStringExtra(r0);
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 == 0) goto L_0x00a2;
    L_0x0058:
        r0 = r1;
    L_0x0059:
        if (r0 != 0) goto L_0x0089;
    L_0x005b:
        r0 = "message_type";
        r0 = r11.getStringExtra(r0);
        if (r0 != 0) goto L_0x0067;
    L_0x0064:
        r0 = "gcm";
    L_0x0067:
        r7 = r0.hashCode();
        switch(r7) {
            case -2062414158: goto L_0x00f4;
            case 102161: goto L_0x00e9;
            case 814694033: goto L_0x010c;
            case 814800675: goto L_0x0100;
            default: goto L_0x006e;
        };
    L_0x006e:
        r1 = r2;
    L_0x006f:
        switch(r1) {
            case 0: goto L_0x0118;
            case 1: goto L_0x0159;
            case 2: goto L_0x015e;
            case 3: goto L_0x016a;
            default: goto L_0x0072;
        };
    L_0x0072:
        r1 = "FirebaseMessaging";
        r2 = "Received message with unknown type: ";
        r0 = java.lang.String.valueOf(r0);
        r3 = r0.length();
        if (r3 == 0) goto L_0x018b;
    L_0x0082:
        r0 = r2.concat(r0);
    L_0x0086:
        android.util.Log.w(r1, r0);
    L_0x0089:
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 != 0) goto L_0x0034;
    L_0x008f:
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = "google.message_id";
        r0.putString(r1, r6);
        r1 = com.google.firebase.iid.zzi.zzev(r10);
        r1.zzh(r4, r0);
        goto L_0x0034;
    L_0x00a2:
        r0 = zzoag;
        r0 = r0.contains(r6);
        if (r0 == 0) goto L_0x00d2;
    L_0x00aa:
        r0 = "FirebaseMessaging";
        r0 = android.util.Log.isLoggable(r0, r5);
        if (r0 == 0) goto L_0x00ca;
    L_0x00b3:
        r7 = "FirebaseMessaging";
        r8 = "Received duplicate message: ";
        r0 = java.lang.String.valueOf(r6);
        r9 = r0.length();
        if (r9 == 0) goto L_0x00cc;
    L_0x00c3:
        r0 = r8.concat(r0);
    L_0x00c7:
        android.util.Log.d(r7, r0);
    L_0x00ca:
        r0 = r3;
        goto L_0x0059;
    L_0x00cc:
        r0 = new java.lang.String;
        r0.<init>(r8);
        goto L_0x00c7;
    L_0x00d2:
        r0 = zzoag;
        r0 = r0.size();
        r7 = 10;
        if (r0 < r7) goto L_0x00e1;
    L_0x00dc:
        r0 = zzoag;
        r0.remove();
    L_0x00e1:
        r0 = zzoag;
        r0.add(r6);
        r0 = r1;
        goto L_0x0059;
    L_0x00e9:
        r3 = "gcm";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x006e;
    L_0x00f2:
        goto L_0x006f;
    L_0x00f4:
        r1 = "deleted_messages";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x006e;
    L_0x00fd:
        r1 = r3;
        goto L_0x006f;
    L_0x0100:
        r1 = "send_event";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x006e;
    L_0x0109:
        r1 = r4;
        goto L_0x006f;
    L_0x010c:
        r1 = "send_error";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x006e;
    L_0x0115:
        r1 = r5;
        goto L_0x006f;
    L_0x0118:
        r0 = r11.getExtras();
        r0 = zzaj(r0);
        if (r0 == 0) goto L_0x0125;
    L_0x0122:
        com.google.firebase.messaging.zzd.zzf(r10, r11);
    L_0x0125:
        r0 = r11.getExtras();
        if (r0 != 0) goto L_0x0130;
    L_0x012b:
        r0 = new android.os.Bundle;
        r0.<init>();
    L_0x0130:
        r1 = "android.support.content.wakelockid";
        r0.remove(r1);
        r1 = com.google.firebase.messaging.zza.zzag(r0);
        if (r1 == 0) goto L_0x014f;
    L_0x013c:
        r1 = com.google.firebase.messaging.zza.zzex(r10);
        r1 = r1.zzs(r0);
        if (r1 != 0) goto L_0x0089;
    L_0x0146:
        r1 = zzaj(r0);
        if (r1 == 0) goto L_0x014f;
    L_0x014c:
        com.google.firebase.messaging.zzd.zzi(r10, r11);
    L_0x014f:
        r1 = new com.google.firebase.messaging.RemoteMessage;
        r1.<init>(r0);
        r10.onMessageReceived(r1);
        goto L_0x0089;
    L_0x0159:
        r10.onDeletedMessages();
        goto L_0x0089;
    L_0x015e:
        r0 = "google.message_id";
        r0 = r11.getStringExtra(r0);
        r10.onMessageSent(r0);
        goto L_0x0089;
    L_0x016a:
        r0 = "google.message_id";
        r0 = r11.getStringExtra(r0);
        if (r0 != 0) goto L_0x017a;
    L_0x0173:
        r0 = "message_id";
        r0 = r11.getStringExtra(r0);
    L_0x017a:
        r1 = new com.google.firebase.messaging.SendException;
        r2 = "error";
        r2 = r11.getStringExtra(r2);
        r1.<init>(r2);
        r10.onSendError(r0, r1);
        goto L_0x0089;
    L_0x018b:
        r0 = new java.lang.String;
        r0.<init>(r2);
        goto L_0x0086;
    L_0x0192:
        r0 = r11.getExtras();
        r0 = zzaj(r0);
        if (r0 == 0) goto L_0x0034;
    L_0x019c:
        com.google.firebase.messaging.zzd.zzh(r10, r11);
        goto L_0x0034;
    L_0x01a1:
        r0 = new java.lang.String;
        r0.<init>(r2);
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.handleIntent(android.content.Intent):void");
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exception) {
    }

    protected final Intent zzp(Intent intent) {
        return zzx.zzcjk().zzcjl();
    }

    public final boolean zzq(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (zzaj(intent.getExtras())) {
            zzd.zzg(this, intent);
        }
        return true;
    }
}
