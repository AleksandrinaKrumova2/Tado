package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.HashMap;
import java.util.Map;

public final class zzapl extends zzh<zzapl> {
    public String zzdrs;
    public boolean zzdrt;

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("description", this.zzdrs);
        hashMap.put(Param.FATAL, Boolean.valueOf(this.zzdrt));
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapl com_google_android_gms_internal_zzapl = (zzapl) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.zzdrs)) {
            com_google_android_gms_internal_zzapl.zzdrs = this.zzdrs;
        }
        if (this.zzdrt) {
            com_google_android_gms_internal_zzapl.zzdrt = this.zzdrt;
        }
    }
}
