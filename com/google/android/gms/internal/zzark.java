package com.google.android.gms.internal;

import android.os.Build.VERSION;

public final class zzark {
    public static int version() {
        try {
            return Integer.parseInt(VERSION.SDK);
        } catch (NumberFormatException e) {
            zzaru.zzf("Invalid version number", VERSION.SDK);
            return 0;
        }
    }
}
