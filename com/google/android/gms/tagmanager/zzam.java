package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class zzam extends zzbr {
    private static final String ID = zzbg.FUNCTION_CALL.toString();
    private static final String zzkcx = zzbh.ADDITIONAL_PARAMS.toString();
    private static final String zzkei = zzbh.FUNCTION_CALL_NAME.toString();
    private final zzan zzkej;

    public zzam(zzan com_google_android_gms_tagmanager_zzan) {
        super(ID, zzkei);
        this.zzkej = com_google_android_gms_tagmanager_zzan;
    }

    public final boolean zzbdp() {
        return false;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        String zzb = zzgk.zzb((zzbs) map.get(zzkei));
        Map hashMap = new HashMap();
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkcx);
        if (com_google_android_gms_internal_zzbs != null) {
            Object zzg = zzgk.zzg(com_google_android_gms_internal_zzbs);
            if (zzg instanceof Map) {
                for (Entry entry : ((Map) zzg).entrySet()) {
                    hashMap.put(entry.getKey().toString(), entry.getValue());
                }
            } else {
                zzdj.zzcu("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzgk.zzbgs();
            }
        }
        try {
            return zzgk.zzam(this.zzkej.zzd(zzb, hashMap));
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.zzcu(new StringBuilder((String.valueOf(zzb).length() + 34) + String.valueOf(message).length()).append("Custom macro/tag ").append(zzb).append(" threw exception ").append(message).toString());
            return zzgk.zzbgs();
        }
    }
}
