package com.google.android.gms.internal;

final class zzaro implements zzarc<zzarp> {
    private final zzaqc zzdta;
    private final zzarp zzdxp = new zzarp();

    public zzaro(zzaqc com_google_android_gms_internal_zzaqc) {
        this.zzdta = com_google_android_gms_internal_zzaqc;
    }

    public final void zzc(String str, boolean z) {
        if ("ga_dryRun".equals(str)) {
            this.zzdxp.zzdxs = z ? 1 : 0;
            return;
        }
        this.zzdta.zzwt().zzd("Bool xml configuration name not recognized", str);
    }

    public final void zzd(String str, int i) {
        if ("ga_dispatchPeriod".equals(str)) {
            this.zzdxp.zzdxr = i;
        } else {
            this.zzdta.zzwt().zzd("Int xml configuration name not recognized", str);
        }
    }

    public final void zzi(String str, String str2) {
    }

    public final void zzj(String str, String str2) {
        if ("ga_appName".equals(str)) {
            this.zzdxp.zzdqz = str2;
        } else if ("ga_appVersion".equals(str)) {
            this.zzdxp.zzdra = str2;
        } else if ("ga_logLevel".equals(str)) {
            this.zzdxp.zzdxq = str2;
        } else {
            this.zzdta.zzwt().zzd("String xml configuration name not recognized", str);
        }
    }

    public final /* synthetic */ zzara zzyo() {
        return this.zzdxp;
    }
}
