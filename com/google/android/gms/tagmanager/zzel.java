package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzel extends zzbr {
    private static final String ID = zzbg.REGEX_GROUP.toString();
    private static final String zzkhw = zzbh.ARG0.toString();
    private static final String zzkhx = zzbh.ARG1.toString();
    private static final String zzkhy = zzbh.IGNORE_CASE.toString();
    private static final String zzkhz = zzbh.GROUP.toString();

    public zzel() {
        super(ID, zzkhw, zzkhx);
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkhw);
        zzbs com_google_android_gms_internal_zzbs2 = (zzbs) map.get(zzkhx);
        if (com_google_android_gms_internal_zzbs == null || com_google_android_gms_internal_zzbs == zzgk.zzbgs() || com_google_android_gms_internal_zzbs2 == null || com_google_android_gms_internal_zzbs2 == zzgk.zzbgs()) {
            return zzgk.zzbgs();
        }
        int intValue;
        int i = 64;
        if (zzgk.zzf((zzbs) map.get(zzkhy)).booleanValue()) {
            i = 66;
        }
        zzbs com_google_android_gms_internal_zzbs3 = (zzbs) map.get(zzkhz);
        if (com_google_android_gms_internal_zzbs3 != null) {
            Long zzd = zzgk.zzd(com_google_android_gms_internal_zzbs3);
            if (zzd == zzgk.zzbgn()) {
                return zzgk.zzbgs();
            }
            intValue = zzd.intValue();
            if (intValue < 0) {
                return zzgk.zzbgs();
            }
        }
        intValue = 1;
        try {
            CharSequence zzb = zzgk.zzb(com_google_android_gms_internal_zzbs);
            Object obj = null;
            Matcher matcher = Pattern.compile(zzgk.zzb(com_google_android_gms_internal_zzbs2), i).matcher(zzb);
            if (matcher.find() && matcher.groupCount() >= intValue) {
                obj = matcher.group(intValue);
            }
            return obj == null ? zzgk.zzbgs() : zzgk.zzam(obj);
        } catch (PatternSyntaxException e) {
            return zzgk.zzbgs();
        }
    }
}
