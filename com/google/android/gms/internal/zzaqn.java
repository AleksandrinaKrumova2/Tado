package com.google.android.gms.internal;

public final class zzaqn extends zzaqa {
    private final zzapd zzdpz = new zzapd();

    zzaqn(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    protected final void zzvf() {
        zzwv().zzvc().zza(this.zzdpz);
        zzasm zzwz = zzwz();
        String zzvi = zzwz.zzvi();
        if (zzvi != null) {
            this.zzdpz.setAppName(zzvi);
        }
        String zzvj = zzwz.zzvj();
        if (zzvj != null) {
            this.zzdpz.setAppVersion(zzvj);
        }
    }

    public final zzapd zzxy() {
        zzxf();
        return this.zzdpz;
    }
}
