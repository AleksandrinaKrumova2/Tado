package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbs;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

final class zzgo {
    private static zzea<zzbs> zza(zzea<zzbs> com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs) {
        try {
            return new zzea(zzgk.zzam(zzmg(zzgk.zzb((zzbs) com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs.getObject()))), com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs.zzbfk());
        } catch (Throwable e) {
            zzdj.zzb("Escape URI: unsupported encoding", e);
            return com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs;
        }
    }

    static zzea<zzbs> zza(zzea<zzbs> com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs, int... iArr) {
        int length = iArr.length;
        int i = 0;
        zzea<zzbs> com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2 = com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs;
        while (i < length) {
            zzea<zzbs> zza;
            int i2 = iArr[i];
            if (zzgk.zzg((zzbs) com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2.getObject()) instanceof String) {
                switch (i2) {
                    case 12:
                        zza = zza(com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2);
                        break;
                    default:
                        zzdj.m10e("Unsupported Value Escaping: " + i2);
                        zza = com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2;
                        break;
                }
            }
            zzdj.m10e("Escaping can only be applied to strings.");
            zza = com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2;
            i++;
            com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2 = zza;
        }
        return com_google_android_gms_tagmanager_zzea_com_google_android_gms_internal_zzbs2;
    }

    static String zzmg(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, HttpRequest.CHARSET_UTF8).replaceAll("\\+", "%20");
    }
}
