package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import java.util.HashMap;
import java.util.Map;

public final class zzapd extends zzh<zzapd> {
    private String mAppId;
    private String zzdqz;
    private String zzdra;
    private String zzdrb;

    public final String getAppId() {
        return this.mAppId;
    }

    public final void setAppId(String str) {
        this.mAppId = str;
    }

    public final void setAppInstallerId(String str) {
        this.zzdrb = str;
    }

    public final void setAppName(String str) {
        this.zzdqz = str;
    }

    public final void setAppVersion(String str) {
        this.zzdra = str;
    }

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("appName", this.zzdqz);
        hashMap.put("appVersion", this.zzdra);
        hashMap.put("appId", this.mAppId);
        hashMap.put("appInstallerId", this.zzdrb);
        return zzh.zzl(hashMap);
    }

    public final void zza(zzapd com_google_android_gms_internal_zzapd) {
        if (!TextUtils.isEmpty(this.zzdqz)) {
            com_google_android_gms_internal_zzapd.zzdqz = this.zzdqz;
        }
        if (!TextUtils.isEmpty(this.zzdra)) {
            com_google_android_gms_internal_zzapd.zzdra = this.zzdra;
        }
        if (!TextUtils.isEmpty(this.mAppId)) {
            com_google_android_gms_internal_zzapd.mAppId = this.mAppId;
        }
        if (!TextUtils.isEmpty(this.zzdrb)) {
            com_google_android_gms_internal_zzapd.zzdrb = this.zzdrb;
        }
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zza((zzapd) com_google_android_gms_analytics_zzh);
    }

    public final String zzvi() {
        return this.zzdqz;
    }

    public final String zzvj() {
        return this.zzdra;
    }

    public final String zzvk() {
        return this.zzdrb;
    }
}
