package com.google.android.gms.tagmanager;

import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzbd extends zzbr {
    private static final String ID = zzbg.DEVICE_NAME.toString();

    public zzbd() {
        super(ID, new String[0]);
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        String str = Build.MANUFACTURER;
        Object obj = Build.MODEL;
        if (!(obj.startsWith(str) || str.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            obj = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(obj).length()).append(str).append(" ").append(obj).toString();
        }
        return zzgk.zzam(obj);
    }
}
