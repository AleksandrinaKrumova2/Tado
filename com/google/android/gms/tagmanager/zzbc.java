package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzbc extends zzbr {
    private static final String ID = zzbg.DEVICE_ID.toString();
    private final Context mContext;

    public zzbc(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
        return string == null ? zzgk.zzbgs() : zzgk.zzam(string);
    }
}
