package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzgk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzdjf {
    private String zzffg;
    private final List<zzdjg> zzksd;
    private final Map<String, List<zzdjc>> zzkse;
    private int zzksf;

    private zzdjf() {
        this.zzksd = new ArrayList();
        this.zzkse = new HashMap();
        this.zzffg = "";
        this.zzksf = 0;
    }

    public final zzdjf zzb(zzdjg com_google_android_gms_internal_zzdjg) {
        this.zzksd.add(com_google_android_gms_internal_zzdjg);
        return this;
    }

    public final zzdje zzbjf() {
        return new zzdje(this.zzksd, this.zzkse, this.zzffg, this.zzksf);
    }

    public final zzdjf zzc(zzdjc com_google_android_gms_internal_zzdjc) {
        String zzb = zzgk.zzb((zzbs) com_google_android_gms_internal_zzdjc.zzbik().get(zzbh.INSTANCE_NAME.toString()));
        List list = (List) this.zzkse.get(zzb);
        if (list == null) {
            list = new ArrayList();
            this.zzkse.put(zzb, list);
        }
        list.add(com_google_android_gms_internal_zzdjc);
        return this;
    }

    public final zzdjf zzev(int i) {
        this.zzksf = i;
        return this;
    }

    public final zzdjf zznh(String str) {
        this.zzffg = str;
        return this;
    }
}
