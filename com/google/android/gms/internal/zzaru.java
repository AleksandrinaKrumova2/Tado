package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public final class zzaru {
    private static volatile Logger zzdye = new zzare();

    public static Logger getLogger() {
        return zzdye;
    }

    public static void setLogger(Logger logger) {
        zzdye = logger;
    }

    public static void m3v(String str) {
        zzapz zzzo = zzarv.zzzo();
        if (zzzo != null) {
            zzzo.zzdu(str);
        } else if (zzae(0)) {
            Log.v((String) zzarl.zzdvy.get(), str);
        }
        Logger logger = zzdye;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    private static boolean zzae(int i) {
        return zzdye != null && zzdye.getLogLevel() <= i;
    }

    public static void zzcu(String str) {
        zzapz zzzo = zzarv.zzzo();
        if (zzzo != null) {
            zzzo.zzdx(str);
        } else if (zzae(2)) {
            Log.w((String) zzarl.zzdvy.get(), str);
        }
        Logger logger = zzdye;
        if (logger != null) {
            logger.warn(str);
        }
    }

    public static void zzf(String str, Object obj) {
        zzapz zzzo = zzarv.zzzo();
        if (zzzo != null) {
            zzzo.zze(str, obj);
        } else if (zzae(3)) {
            String stringBuilder;
            if (obj != null) {
                String valueOf = String.valueOf(obj);
                stringBuilder = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()).append(str).append(":").append(valueOf).toString();
            } else {
                stringBuilder = str;
            }
            Log.e((String) zzarl.zzdvy.get(), stringBuilder);
        }
        Logger logger = zzdye;
        if (logger != null) {
            logger.error(str);
        }
    }
}
