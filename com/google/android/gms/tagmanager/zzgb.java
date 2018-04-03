package com.google.android.gms.tagmanager;

import java.util.Map;

final class zzgb implements zzb {
    private /* synthetic */ TagManager zzkke;

    zzgb(TagManager tagManager) {
        this.zzkke = tagManager;
    }

    public final void zzw(Map<String, Object> map) {
        Object obj = map.get("event");
        if (obj != null) {
            this.zzkke.zzly(obj.toString());
        }
    }
}
