package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbs;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzm extends zzgi {
    private static final String ID = zzbg.ARBITRARY_PIXEL.toString();
    private static final String URL = zzbh.URL.toString();
    private static final String zzkcx = zzbh.ADDITIONAL_PARAMS.toString();
    private static final String zzkcy = zzbh.UNREPEATABLE.toString();
    private static String zzkcz;
    private static final Set<String> zzkda = new HashSet();
    private final Context mContext;
    private final zza zzkdb;

    public interface zza {
        zzby zzbdq();
    }

    static {
        String str = ID;
        zzkcz = new StringBuilder(String.valueOf(str).length() + 17).append("gtm_").append(str).append("_unrepeatable").toString();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    private zzm(Context context, zza com_google_android_gms_tagmanager_zzm_zza) {
        super(ID, URL);
        this.zzkdb = com_google_android_gms_tagmanager_zzm_zza;
        this.mContext = context;
    }

    private final synchronized boolean zzla(String str) {
        boolean z = true;
        synchronized (this) {
            if (!zzkda.contains(str)) {
                if (this.mContext.getSharedPreferences(zzkcz, 0).contains(str)) {
                    zzkda.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    public final void zzx(Map<String, zzbs> map) {
        String zzb = map.get(zzkcy) != null ? zzgk.zzb((zzbs) map.get(zzkcy)) : null;
        if (zzb == null || !zzla(zzb)) {
            String valueOf;
            Builder buildUpon = Uri.parse(zzgk.zzb((zzbs) map.get(URL))).buildUpon();
            zzbs com_google_android_gms_internal_zzbs = (zzbs) map.get(zzkcx);
            if (com_google_android_gms_internal_zzbs != null) {
                Object zzg = zzgk.zzg(com_google_android_gms_internal_zzbs);
                if (zzg instanceof List) {
                    for (Object zzg2 : (List) zzg2) {
                        if (zzg2 instanceof Map) {
                            for (Entry entry : ((Map) zzg2).entrySet()) {
                                buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            }
                        } else {
                            zzb = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ";
                            valueOf = String.valueOf(buildUpon.build().toString());
                            zzdj.m10e(valueOf.length() != 0 ? zzb.concat(valueOf) : new String(zzb));
                            return;
                        }
                    }
                }
                zzb = "ArbitraryPixel: additional params not a list: not sending partial hit: ";
                valueOf = String.valueOf(buildUpon.build().toString());
                zzdj.m10e(valueOf.length() != 0 ? zzb.concat(valueOf) : new String(zzb));
                return;
            }
            valueOf = buildUpon.build().toString();
            this.zzkdb.zzbdq().zzll(valueOf);
            String str = "ArbitraryPixel: url = ";
            valueOf = String.valueOf(valueOf);
            zzdj.m11v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (zzb != null) {
                synchronized (zzm.class) {
                    zzkda.add(zzb);
                    zzfu.zze(this.mContext, zzkcz, zzb, "true");
                }
            }
        }
    }
}
