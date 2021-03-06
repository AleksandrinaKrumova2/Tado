package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzae {
    public static boolean DEBUG;
    private static String TAG;

    static class zza {
        public static final boolean zzbl = zzae.DEBUG;
        private final List<zzaf> zzbm = new ArrayList();
        private boolean zzbn = false;

        zza() {
        }

        protected final void finalize() throws Throwable {
            if (!this.zzbn) {
                zzc("Request on the loose");
                zzae.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public final synchronized void zza(String str, long j) {
            if (this.zzbn) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.zzbm.add(new zzaf(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void zzc(String str) {
            long j;
            this.zzbn = true;
            if (this.zzbm.size() == 0) {
                j = 0;
            } else {
                j = ((zzaf) this.zzbm.get(this.zzbm.size() - 1)).time - ((zzaf) this.zzbm.get(0)).time;
            }
            if (j > 0) {
                long j2 = ((zzaf) this.zzbm.get(0)).time;
                zzae.zzb("(%-4d ms) %s", Long.valueOf(j), str);
                j = j2;
                for (zzaf com_google_android_gms_internal_zzaf : this.zzbm) {
                    zzae.zzb("(+%-4d) [%2d] %s", Long.valueOf(com_google_android_gms_internal_zzaf.time - j), Long.valueOf(com_google_android_gms_internal_zzaf.zzbo), com_google_android_gms_internal_zzaf.name);
                    j = com_google_android_gms_internal_zzaf.time;
                }
            }
        }
    }

    static {
        String str = "Volley";
        TAG = str;
        DEBUG = Log.isLoggable(str, 2);
    }

    public static void zza(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, zzd(str, objArr));
        }
    }

    public static void zza(Throwable th, String str, Object... objArr) {
        Log.e(TAG, zzd(str, objArr), th);
    }

    public static void zzb(String str, Object... objArr) {
        Log.d(TAG, zzd(str, objArr));
    }

    public static void zzc(String str, Object... objArr) {
        Log.e(TAG, zzd(str, objArr));
    }

    private static String zzd(String str, Object... objArr) {
        String methodName;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str2 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(zzae.class)) {
                str2 = stackTrace[i].getClassName();
                str2 = str2.substring(str2.lastIndexOf(46) + 1);
                str2 = str2.substring(str2.lastIndexOf(36) + 1);
                methodName = stackTrace[i].getMethodName();
                methodName = new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(methodName).length()).append(str2).append(".").append(methodName).toString();
                break;
            }
        }
        methodName = str2;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), methodName, str});
    }
}
