package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import java.util.HashMap;
import java.util.Map;

public final class zzapo extends zzh<zzapo> {
    public String zzdrp;
    public String zzdsh;
    public String zzdsi;

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("network", this.zzdsh);
        hashMap.put("action", this.zzdrp);
        hashMap.put("target", this.zzdsi);
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapo com_google_android_gms_internal_zzapo = (zzapo) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.zzdsh)) {
            com_google_android_gms_internal_zzapo.zzdsh = this.zzdsh;
        }
        if (!TextUtils.isEmpty(this.zzdrp)) {
            com_google_android_gms_internal_zzapo.zzdrp = this.zzdrp;
        }
        if (!TextUtils.isEmpty(this.zzdsi)) {
            com_google_android_gms_internal_zzapo.zzdsi = this.zzdsi;
        }
    }
}
