package com.google.android.gms.internal;

import com.google.android.gms.common.zzf;

public final class zzaqb {
    public static final String VERSION = String.valueOf(zzf.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    public static final String zzdtc;

    static {
        String str = "ma";
        String valueOf = String.valueOf(VERSION);
        zzdtc = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }
}
