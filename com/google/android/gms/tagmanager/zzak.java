package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzak extends zzga {
    private static final String ID = zzbg.CONTAINS.toString();

    public zzak() {
        super(ID);
    }

    protected final boolean zza(String str, String str2, Map<String, zzbs> map) {
        return str.contains(str2);
    }
}
