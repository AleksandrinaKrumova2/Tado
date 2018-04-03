package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzeb extends zzbr {
    private static final String ID = zzbg.OS_VERSION.toString();

    public zzeb() {
        super(ID, new String[0]);
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        return zzgk.zzam(VERSION.RELEASE);
    }
}
