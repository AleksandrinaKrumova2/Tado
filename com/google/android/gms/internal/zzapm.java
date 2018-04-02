package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzbq;
import java.util.HashMap;
import java.util.Map;

public final class zzapm extends zzh<zzapm> {
    private String zzauv;
    private String zzdru;
    private String zzdrv;
    private String zzdrw;
    private boolean zzdrx;
    private String zzdry;
    private boolean zzdrz;
    private double zzdsa;

    public final String getUserId() {
        return this.zzauv;
    }

    public final void setClientId(String str) {
        this.zzdrv = str;
    }

    public final void setUserId(String str) {
        this.zzauv = str;
    }

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("hitType", this.zzdru);
        hashMap.put("clientId", this.zzdrv);
        hashMap.put("userId", this.zzauv);
        hashMap.put("androidAdId", this.zzdrw);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzdrx));
        hashMap.put("sessionControl", this.zzdry);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzdrz));
        hashMap.put("sampleRate", Double.valueOf(this.zzdsa));
        return zzh.zzl(hashMap);
    }

    public final void zzai(boolean z) {
        this.zzdrx = z;
    }

    public final void zzaj(boolean z) {
        this.zzdrz = true;
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        boolean z = true;
        zzapm com_google_android_gms_internal_zzapm = (zzapm) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.zzdru)) {
            com_google_android_gms_internal_zzapm.zzdru = this.zzdru;
        }
        if (!TextUtils.isEmpty(this.zzdrv)) {
            com_google_android_gms_internal_zzapm.zzdrv = this.zzdrv;
        }
        if (!TextUtils.isEmpty(this.zzauv)) {
            com_google_android_gms_internal_zzapm.zzauv = this.zzauv;
        }
        if (!TextUtils.isEmpty(this.zzdrw)) {
            com_google_android_gms_internal_zzapm.zzdrw = this.zzdrw;
        }
        if (this.zzdrx) {
            com_google_android_gms_internal_zzapm.zzdrx = true;
        }
        if (!TextUtils.isEmpty(this.zzdry)) {
            com_google_android_gms_internal_zzapm.zzdry = this.zzdry;
        }
        if (this.zzdrz) {
            com_google_android_gms_internal_zzapm.zzdrz = this.zzdrz;
        }
        if (this.zzdsa != 0.0d) {
            double d = this.zzdsa;
            if (d < 0.0d || d > 100.0d) {
                z = false;
            }
            zzbq.checkArgument(z, "Sample rate must be between 0% and 100%");
            com_google_android_gms_internal_zzapm.zzdsa = d;
        }
    }

    public final void zzdp(String str) {
        this.zzdru = str;
    }

    public final void zzdq(String str) {
        this.zzdrw = str;
    }

    public final String zzvy() {
        return this.zzdru;
    }

    public final String zzvz() {
        return this.zzdrv;
    }

    public final String zzwa() {
        return this.zzdrw;
    }

    public final boolean zzwb() {
        return this.zzdrx;
    }

    public final String zzwc() {
        return this.zzdry;
    }

    public final boolean zzwd() {
        return this.zzdrz;
    }

    public final double zzwe() {
        return this.zzdsa;
    }
}
