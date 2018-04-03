package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzbl extends zzga {
    private static final String ID = zzbg.ENDS_WITH.toString();

    public zzbl() {
        super(ID);
    }

    protected final boolean zza(String str, String str2, Map<String, zzbs> map) {
        return str.endsWith(str2);
    }
}
