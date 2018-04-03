package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzej extends zzbr {
    private static final String ID = zzbg.RANDOM.toString();
    private static final String zzkhu = zzbh.MIN.toString();
    private static final String zzkhv = zzbh.MAX.toString();

    public zzej() {
        super(ID, new String[0]);
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        double doubleValue;
        double d;
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkhu);
        zzbs com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzkhv);
        if (!(com_google_android_gms_internal_zzbs == null || com_google_android_gms_internal_zzbs == zzgk.zzbgs() || com_google_android_gms_internal_zzbs2 == null || com_google_android_gms_internal_zzbs2 == zzgk.zzbgs())) {
            zzgj zzc = zzgk.zzc(com_google_android_gms_internal_zzbs);
            zzgj zzc2 = zzgk.zzc(com_google_android_gms_internal_zzbs2);
            if (!(zzc == zzgk.zzbgq() || zzc2 == zzgk.zzbgq())) {
                double doubleValue2 = zzc.doubleValue();
                doubleValue = zzc2.doubleValue();
                if (doubleValue2 <= doubleValue) {
                    d = doubleValue2;
                    return zzgk.zzam(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
                }
            }
        }
        doubleValue = 2.147483647E9d;
        d = 0.0d;
        return zzgk.zzam(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
    }
}
