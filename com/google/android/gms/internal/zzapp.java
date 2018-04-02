package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import java.util.HashMap;
import java.util.Map;

public final class zzapp extends zzh<zzapp> {
    public String mCategory;
    public String zzdrq;
    public String zzdsj;
    public long zzdsk;

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("variableName", this.zzdsj);
        hashMap.put("timeInMillis", Long.valueOf(this.zzdsk));
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzdrq);
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapp com_google_android_gms_internal_zzapp = (zzapp) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.zzdsj)) {
            com_google_android_gms_internal_zzapp.zzdsj = this.zzdsj;
        }
        if (this.zzdsk != 0) {
            com_google_android_gms_internal_zzapp.zzdsk = this.zzdsk;
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            com_google_android_gms_internal_zzapp.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzdrq)) {
            com_google_android_gms_internal_zzapp.zzdrq = this.zzdrq;
        }
    }
}
