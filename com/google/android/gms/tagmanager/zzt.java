package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzt extends zzbr {
    private static final String ID = zzbg.CONSTANT.toString();
    private static final String VALUE = zzbh.VALUE.toString();

    public zzt() {
        super(ID, VALUE);
    }

    public static String zzbdr() {
        return ID;
    }

    public static String zzbds() {
        return VALUE;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        return (zzbs) map.get(VALUE);
    }
}
