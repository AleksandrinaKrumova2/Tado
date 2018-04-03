package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdjc;
import com.google.android.gms.internal.zzdjg;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzff implements zzfh {
    private /* synthetic */ Map zzkix;
    private /* synthetic */ Map zzkiy;
    private /* synthetic */ Map zzkiz;
    private /* synthetic */ Map zzkja;

    zzff(zzfc com_google_android_gms_tagmanager_zzfc, Map map, Map map2, Map map3, Map map4) {
        this.zzkix = map;
        this.zzkiy = map2;
        this.zzkiz = map3;
        this.zzkja = map4;
    }

    public final void zza(zzdjg com_google_android_gms_internal_zzdjg, Set<zzdjc> set, Set<zzdjc> set2, zzer com_google_android_gms_tagmanager_zzer) {
        List list = (List) this.zzkix.get(com_google_android_gms_internal_zzdjg);
        this.zzkiy.get(com_google_android_gms_internal_zzdjg);
        if (list != null) {
            set.addAll(list);
            com_google_android_gms_tagmanager_zzer.zzbfe();
        }
        list = (List) this.zzkiz.get(com_google_android_gms_internal_zzdjg);
        this.zzkja.get(com_google_android_gms_internal_zzdjg);
        if (list != null) {
            set2.addAll(list);
            com_google_android_gms_tagmanager_zzer.zzbff();
        }
    }
}
