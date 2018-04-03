package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class zzay {
    final String zzbhb;
    final byte[] zzkfd;

    zzay(String str, byte[] bArr) {
        this.zzbhb = str;
        this.zzkfd = bArr;
    }

    public final String toString() {
        String str = this.zzbhb;
        return new StringBuilder(String.valueOf(str).length() + 54).append("KeyAndSerialized: key = ").append(str).append(" serialized hash = ").append(Arrays.hashCode(this.zzkfd)).toString();
    }
}
