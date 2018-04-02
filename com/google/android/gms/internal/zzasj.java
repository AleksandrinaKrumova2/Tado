package com.google.android.gms.internal;

final class zzasj extends zzapz implements zzarc<zzask> {
    private final zzask zzdzc = new zzask();

    public zzasj(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    public final void zzc(String str, boolean z) {
        int i = 1;
        zzask com_google_android_gms_internal_zzask;
        if ("ga_autoActivityTracking".equals(str)) {
            com_google_android_gms_internal_zzask = this.zzdzc;
            if (!z) {
                i = 0;
            }
            com_google_android_gms_internal_zzask.zzdzf = i;
        } else if ("ga_anonymizeIp".equals(str)) {
            com_google_android_gms_internal_zzask = this.zzdzc;
            if (!z) {
                i = 0;
            }
            com_google_android_gms_internal_zzask.zzdzg = i;
        } else if ("ga_reportUncaughtExceptions".equals(str)) {
            com_google_android_gms_internal_zzask = this.zzdzc;
            if (!z) {
                i = 0;
            }
            com_google_android_gms_internal_zzask.zzdzh = i;
        } else {
            zzd("bool configuration name not recognized", str);
        }
    }

    public final void zzd(String str, int i) {
        if ("ga_sessionTimeout".equals(str)) {
            this.zzdzc.zzdze = i;
        } else {
            zzd("int configuration name not recognized", str);
        }
    }

    public final void zzi(String str, String str2) {
        this.zzdzc.zzdzi.put(str, str2);
    }

    public final void zzj(String str, String str2) {
        if ("ga_trackingId".equals(str)) {
            this.zzdzc.zzdom = str2;
        } else if ("ga_sampleFrequency".equals(str)) {
            try {
                this.zzdzc.zzdzd = Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                zzc("Error parsing ga_sampleFrequency value", str2, e);
            }
        } else {
            zzd("string configuration name not recognized", str);
        }
    }

    public final /* synthetic */ zzara zzyo() {
        return this.zzdzc;
    }
}
