package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzh extends zzbr {
    private static final String ID = zzbg.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzkcv = zzbh.COMPONENT.toString();
    private static final String zzkcw = zzbh.CONVERSION_ID.toString();
    private final Context zzair;

    public zzh(Context context) {
        super(ID, zzkcw);
        this.zzair = context;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkcw);
        if (com_google_android_gms_internal_zzbs == null) {
            return zzgk.zzbgs();
        }
        String zzb = zzgk.zzb(com_google_android_gms_internal_zzbs);
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkcv);
        String zzb2 = com_google_android_gms_internal_zzbs != null ? zzgk.zzb(com_google_android_gms_internal_zzbs) : null;
        Context context = this.zzair;
        String str = (String) zzcx.zzkgf.get(zzb);
        if (str == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str = sharedPreferences != null ? sharedPreferences.getString(zzb, "") : "";
            zzcx.zzkgf.put(zzb, str);
        }
        str = zzcx.zzax(str, zzb2);
        return str != null ? zzgk.zzam(str) : zzgk.zzbgs();
    }
}
