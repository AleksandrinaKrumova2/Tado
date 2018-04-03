package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdjc;
import com.google.android.gms.internal.zzdjg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzfj {
    private final Set<zzdjg> zzkit = new HashSet();
    private final Map<zzdjg, List<zzdjc>> zzkjd = new HashMap();
    private final Map<zzdjg, List<zzdjc>> zzkje = new HashMap();
    private final Map<zzdjg, List<String>> zzkjf = new HashMap();
    private final Map<zzdjg, List<String>> zzkjg = new HashMap();
    private zzdjc zzkjh;

    public final void zza(zzdjg com_google_android_gms_internal_zzdjg) {
        this.zzkit.add(com_google_android_gms_internal_zzdjg);
    }

    public final void zza(zzdjg com_google_android_gms_internal_zzdjg, zzdjc com_google_android_gms_internal_zzdjc) {
        List list = (List) this.zzkjd.get(com_google_android_gms_internal_zzdjg);
        if (list == null) {
            list = new ArrayList();
            this.zzkjd.put(com_google_android_gms_internal_zzdjg, list);
        }
        list.add(com_google_android_gms_internal_zzdjc);
    }

    public final void zza(zzdjg com_google_android_gms_internal_zzdjg, String str) {
        List list = (List) this.zzkjf.get(com_google_android_gms_internal_zzdjg);
        if (list == null) {
            list = new ArrayList();
            this.zzkjf.put(com_google_android_gms_internal_zzdjg, list);
        }
        list.add(str);
    }

    public final void zzb(zzdjc com_google_android_gms_internal_zzdjc) {
        this.zzkjh = com_google_android_gms_internal_zzdjc;
    }

    public final void zzb(zzdjg com_google_android_gms_internal_zzdjg, zzdjc com_google_android_gms_internal_zzdjc) {
        List list = (List) this.zzkje.get(com_google_android_gms_internal_zzdjg);
        if (list == null) {
            list = new ArrayList();
            this.zzkje.put(com_google_android_gms_internal_zzdjg, list);
        }
        list.add(com_google_android_gms_internal_zzdjc);
    }

    public final void zzb(zzdjg com_google_android_gms_internal_zzdjg, String str) {
        List list = (List) this.zzkjg.get(com_google_android_gms_internal_zzdjg);
        if (list == null) {
            list = new ArrayList();
            this.zzkjg.put(com_google_android_gms_internal_zzdjg, list);
        }
        list.add(str);
    }

    public final Set<zzdjg> zzbfz() {
        return this.zzkit;
    }

    public final Map<zzdjg, List<zzdjc>> zzbga() {
        return this.zzkjd;
    }

    public final Map<zzdjg, List<String>> zzbgb() {
        return this.zzkjf;
    }

    public final Map<zzdjg, List<String>> zzbgc() {
        return this.zzkjg;
    }

    public final Map<zzdjg, List<zzdjc>> zzbgd() {
        return this.zzkje;
    }

    public final zzdjc zzbge() {
        return this.zzkjh;
    }
}
