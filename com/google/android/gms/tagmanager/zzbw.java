package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

final class zzbw extends zzbr {
    private static final String ID = zzbg.HASH.toString();
    private static final String zzkfo = zzbh.ARG0.toString();
    private static final String zzkfq = zzbh.INPUT_FORMAT.toString();
    private static final String zzkfu = zzbh.ALGORITHM.toString();

    public zzbw() {
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
        byte[] bytes;
        String zzb = zzgk.zzb(com_google_android_gms_internal_zzbs);
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfu);
        String zzb2 = com_google_android_gms_internal_zzbs == null ? "MD5" : zzgk.zzb(com_google_android_gms_internal_zzbs);
        com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkfq);
        Object zzb3 = com_google_android_gms_internal_zzbs == null ? "text" : zzgk.zzb(com_google_android_gms_internal_zzbs);
        if ("text".equals(zzb3)) {
            bytes = zzb.getBytes();
        } else if ("base16".equals(zzb3)) {
            bytes = zzo.decode(zzb);
        } else {
            zzb2 = "Hash: unknown input format: ";
            String valueOf = String.valueOf(zzb3);
            zzdj.m10e(valueOf.length() != 0 ? zzb2.concat(valueOf) : new String(zzb2));
            return zzgk.zzbgs();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(zzb2);
            instance.update(bytes);
            return zzgk.zzam(zzo.zzk(instance.digest()));
        } catch (NoSuchAlgorithmException e) {
            zzb = "Hash: unknown algorithm: ";
            valueOf = String.valueOf(zzb2);
            zzdj.m10e(valueOf.length() != 0 ? zzb.concat(valueOf) : new String(zzb));
            return zzgk.zzbgs();
        }
    }
}
