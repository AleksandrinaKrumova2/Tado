package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.HashMap;
import java.util.Map;

public final class zzapk extends zzh<zzapk> {
    private String mCategory;
    private String zzdrp;
    private String zzdrq;
    private long zzdrr;

    public final String getAction() {
        return this.zzdrp;
    }

    public final String getCategory() {
        return this.mCategory;
    }

    public final String getLabel() {
        return this.zzdrq;
    }

    public final long getValue() {
        return this.zzdrr;
    }

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("category", this.mCategory);
        hashMap.put("action", this.zzdrp);
        hashMap.put("label", this.zzdrq);
        hashMap.put(Param.VALUE, Long.valueOf(this.zzdrr));
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapk com_google_android_gms_internal_zzapk = (zzapk) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.mCategory)) {
            com_google_android_gms_internal_zzapk.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzdrp)) {
            com_google_android_gms_internal_zzapk.zzdrp = this.zzdrp;
        }
        if (!TextUtils.isEmpty(this.zzdrq)) {
            com_google_android_gms_internal_zzapk.zzdrq = this.zzdrq;
        }
        if (this.zzdrr != 0) {
            com_google_android_gms_internal_zzapk.zzdrr = this.zzdrr;
        }
    }
}
