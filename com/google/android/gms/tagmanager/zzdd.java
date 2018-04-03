package com.google.android.gms.tagmanager;

import android.util.LruCache;

final class zzdd extends LruCache<K, V> {
    private /* synthetic */ zzs zzkgu;

    zzdd(zzdc com_google_android_gms_tagmanager_zzdc, int i, zzs com_google_android_gms_tagmanager_zzs) {
        this.zzkgu = com_google_android_gms_tagmanager_zzs;
        super(i);
    }

    protected final int sizeOf(K k, V v) {
        return this.zzkgu.sizeOf(k, v);
    }
}
