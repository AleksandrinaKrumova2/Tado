package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import java.io.IOException;

final class zzaa implements Runnable {
    private final zzu zznys;
    private final long zznzv;
    private final WakeLock zznzw = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId zznzx;

    zzaa(FirebaseInstanceId firebaseInstanceId, zzu com_google_firebase_iid_zzu, long j) {
        this.zznzx = firebaseInstanceId;
        this.zznys = com_google_firebase_iid_zzu;
        this.zznzv = j;
        this.zznzw.setReferenceCounted(false);
    }

    private final boolean zzcjn() {
        Exception e;
        String str;
        String valueOf;
        zzz zzciu = this.zznzx.zzciu();
        if (zzciu != null && !zzciu.zzro(this.zznys.zzcjg())) {
            return true;
        }
        String zzciv;
        try {
            zzciv = this.zznzx.zzciv();
            if (zzciv == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzciu != null && (zzciu == null || zzciv.equals(zzciu.zzldj))) {
                return true;
            }
            Context context = getContext();
            Parcelable intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
            Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
            intent2.setClass(context, FirebaseInstanceIdReceiver.class);
            intent2.putExtra("wrapped_intent", intent);
            context.sendBroadcast(intent2);
            return true;
        } catch (IOException e2) {
            e = e2;
            str = "FirebaseInstanceId";
            zzciv = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            Log.e(str, valueOf.length() == 0 ? zzciv.concat(valueOf) : new String(zzciv));
            return false;
        } catch (SecurityException e3) {
            e = e3;
            str = "FirebaseInstanceId";
            zzciv = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            Log.e(str, valueOf.length() == 0 ? zzciv.concat(valueOf) : new String(zzciv));
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzcjo() {
        /*
        r3 = this;
    L_0x0000:
        r1 = r3.zznzx;
        monitor-enter(r1);
        r0 = com.google.firebase.iid.FirebaseInstanceId.zzciw();	 Catch:{ all -> 0x0022 }
        r0 = r0.zzcjm();	 Catch:{ all -> 0x0022 }
        if (r0 != 0) goto L_0x0019;
    L_0x000d:
        r0 = "FirebaseInstanceId";
        r2 = "topic sync succeeded";
        android.util.Log.d(r0, r2);	 Catch:{ all -> 0x0022 }
        r0 = 1;
        monitor-exit(r1);	 Catch:{ all -> 0x0022 }
    L_0x0018:
        return r0;
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x0022 }
        r1 = r3.zzrp(r0);
        if (r1 != 0) goto L_0x0025;
    L_0x0020:
        r0 = 0;
        goto L_0x0018;
    L_0x0022:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0022 }
        throw r0;
    L_0x0025:
        r1 = com.google.firebase.iid.FirebaseInstanceId.zzciw();
        r1.zzri(r0);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzaa.zzcjo():boolean");
    }

    private final boolean zzrp(String str) {
        String str2;
        String valueOf;
        String[] split = str.split("!");
        if (split.length != 2) {
            return true;
        }
        String str3 = split[0];
        String str4 = split[1];
        int i = -1;
        try {
            switch (str3.hashCode()) {
                case 83:
                    if (str3.equals("S")) {
                        i = 0;
                        break;
                    }
                    break;
                case 85:
                    if (str3.equals("U")) {
                        boolean z = true;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    this.zznzx.zzrg(str4);
                    if (!FirebaseInstanceId.zzcix()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    return true;
                case 1:
                    this.zznzx.zzrh(str4);
                    if (!FirebaseInstanceId.zzcix()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    return true;
                default:
                    return true;
            }
        } catch (IOException e) {
            str2 = "FirebaseInstanceId";
            str3 = "Topic sync failed: ";
            valueOf = String.valueOf(e.getMessage());
            Log.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf));
            return false;
        }
        str2 = "FirebaseInstanceId";
        str3 = "Topic sync failed: ";
        valueOf = String.valueOf(e.getMessage());
        if (valueOf.length() == 0) {
        }
        Log.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf));
        return false;
    }

    final Context getContext() {
        return this.zznzx.getApp().getApplicationContext();
    }

    public final void run() {
        Object obj = 1;
        this.zznzw.acquire();
        try {
            this.zznzx.zzcr(true);
            if (this.zznys.zzcjf() == 0) {
                obj = null;
            }
            if (obj == null) {
                this.zznzx.zzcr(false);
            } else if (zzcjp()) {
                if (zzcjn() && zzcjo()) {
                    this.zznzx.zzcr(false);
                } else {
                    this.zznzx.zzcc(this.zznzv);
                }
                this.zznzw.release();
            } else {
                new zzab(this).zzcjq();
                this.zznzw.release();
            }
        } finally {
            this.zznzw.release();
        }
    }

    final boolean zzcjp() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
