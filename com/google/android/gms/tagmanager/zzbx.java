package com.google.android.gms.tagmanager;

import android.text.TextUtils;

final class zzbx {
    private final long zzdxv;
    private final long zzkfv;
    private final long zzkfw;
    private String zzkfx;

    zzbx(long j, long j2, long j3) {
        this.zzkfv = j;
        this.zzdxv = j2;
        this.zzkfw = j3;
    }

    final long zzbey() {
        return this.zzkfv;
    }

    final long zzbez() {
        return this.zzkfw;
    }

    final String zzbfa() {
        return this.zzkfx;
    }

    final void zzlp(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzkfx = str;
        }
    }
}
