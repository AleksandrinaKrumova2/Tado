package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

final class zzary {
    private int zzdyk;
    private ByteArrayOutputStream zzdyl = new ByteArrayOutputStream();
    private /* synthetic */ zzarx zzdym;

    public zzary(zzarx com_google_android_gms_internal_zzarx) {
        this.zzdym = com_google_android_gms_internal_zzarx;
    }

    public final byte[] getPayload() {
        return this.zzdyl.toByteArray();
    }

    public final boolean zze(zzarq com_google_android_gms_internal_zzarq) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        if (this.zzdyk + 1 > zzard.zzyv()) {
            return false;
        }
        String zza = this.zzdym.zza(com_google_android_gms_internal_zzarq, false);
        if (zza == null) {
            this.zzdym.zzwt().zza(com_google_android_gms_internal_zzarq, "Error formatting hit");
            return true;
        }
        byte[] bytes = zza.getBytes();
        int length = bytes.length;
        if (length > zzard.zzyr()) {
            this.zzdym.zzwt().zza(com_google_android_gms_internal_zzarq, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzdyl.size() > 0) {
            length++;
        }
        if (this.zzdyl.size() + length > ((Integer) zzarl.zzdww.get()).intValue()) {
            return false;
        }
        try {
            if (this.zzdyl.size() > 0) {
                this.zzdyl.write(zzarx.zzdyj);
            }
            this.zzdyl.write(bytes);
            this.zzdyk++;
            return true;
        } catch (IOException e) {
            this.zzdym.zze("Failed to write payload when batching hits", e);
            return true;
        }
    }

    public final int zzzv() {
        return this.zzdyk;
    }
}
