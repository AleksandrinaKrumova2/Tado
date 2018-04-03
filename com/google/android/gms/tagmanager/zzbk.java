package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzbk extends zzbr {
    private static final String ID = zzbg.ENCODE.toString();
    private static final String zzkfo = zzbh.ARG0.toString();
    private static final String zzkfp = zzbh.NO_PADDING.toString();
    private static final String zzkfq = zzbh.INPUT_FORMAT.toString();
    private static final String zzkfr = zzbh.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(ID, zzkfo);
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfo);
        if (com_google_android_gms_internal_zzbs == null || com_google_android_gms_internal_zzbs == zzgk.zzbgs()) {
            return zzgk.zzbgs();
        }
        String zzb = zzgk.zzb(com_google_android_gms_internal_zzbs);
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfq);
        if (com_google_android_gms_internal_zzbs == null) {
            Object obj = "text";
        } else {
            String zzb2 = zzgk.zzb(com_google_android_gms_internal_zzbs);
        }
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfr);
        if (com_google_android_gms_internal_zzbs == null) {
            Object obj2 = "base16";
        } else {
            String zzb3 = zzgk.zzb(com_google_android_gms_internal_zzbs);
        }
        int i = 2;
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfp);
        if (com_google_android_gms_internal_zzbs != null && zzgk.zzf(com_google_android_gms_internal_zzbs).booleanValue()) {
            i = 3;
        }
        try {
            byte[] bytes;
            String valueOf;
            Object zzk;
            if ("text".equals(obj)) {
                bytes = zzb.getBytes();
            } else if ("base16".equals(obj)) {
                bytes = zzo.decode(zzb);
            } else if ("base64".equals(obj)) {
                bytes = Base64.decode(zzb, i);
            } else if ("base64url".equals(obj)) {
                bytes = Base64.decode(zzb, i | 8);
            } else {
                zzb3 = "Encode: unknown input format: ";
                valueOf = String.valueOf(obj);
                zzdj.m10e(valueOf.length() != 0 ? zzb3.concat(valueOf) : new String(zzb3));
                return zzgk.zzbgs();
            }
            if ("base16".equals(obj2)) {
                zzk = zzo.zzk(bytes);
            } else if ("base64".equals(obj2)) {
                zzk = Base64.encodeToString(bytes, i);
            } else if ("base64url".equals(obj2)) {
                zzk = Base64.encodeToString(bytes, i | 8);
            } else {
                zzb2 = "Encode: unknown output format: ";
                valueOf = String.valueOf(obj2);
                zzdj.m10e(valueOf.length() != 0 ? zzb2.concat(valueOf) : new String(zzb2));
                return zzgk.zzbgs();
            }
            return zzgk.zzam(zzk);
        } catch (IllegalArgumentException e) {
            zzdj.m10e("Encode: invalid input:");
            return zzgk.zzbgs();
        }
    }
}
