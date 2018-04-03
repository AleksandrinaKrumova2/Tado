package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;

@TargetApi(12)
final class zzdc<K, V> implements zzp<K, V> {
    private LruCache<K, V> zzkgt;

    zzdc(int i, zzs<K, V> com_google_android_gms_tagmanager_zzs_K__V) {
        this.zzkgt = new zzdd(this, 1048576, com_google_android_gms_tagmanager_zzs_K__V);
    }

    public final V get(K k) {
        return this.zzkgt.get(k);
    }

    public final void zzf(K k, V v) {
        this.zzkgt.put(k, v);
    }
}
