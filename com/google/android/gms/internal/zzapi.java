package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import java.util.HashMap;
import java.util.Map;

public final class zzapi extends zzh<zzapi> {
    public int zzchl;
    public int zzchm;
    private String zzdrl;
    public int zzdrm;
    public int zzdrn;
    public int zzdro;

    public final String getLanguage() {
        return this.zzdrl;
    }

    public final void setLanguage(String str) {
        this.zzdrl = str;
    }

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("language", this.zzdrl);
        hashMap.put("screenColors", Integer.valueOf(this.zzdrm));
        hashMap.put("screenWidth", Integer.valueOf(this.zzchl));
        hashMap.put("screenHeight", Integer.valueOf(this.zzchm));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzdrn));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzdro));
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapi com_google_android_gms_internal_zzapi = (zzapi) com_google_android_gms_analytics_zzh;
        if (this.zzdrm != 0) {
            com_google_android_gms_internal_zzapi.zzdrm = this.zzdrm;
        }
        if (this.zzchl != 0) {
            com_google_android_gms_internal_zzapi.zzchl = this.zzchl;
        }
        if (this.zzchm != 0) {
            com_google_android_gms_internal_zzapi.zzchm = this.zzchm;
        }
        if (this.zzdrn != 0) {
            com_google_android_gms_internal_zzapi.zzdrn = this.zzdrn;
        }
        if (this.zzdro != 0) {
            com_google_android_gms_internal_zzapi.zzdro = this.zzdro;
        }
        if (!TextUtils.isEmpty(this.zzdrl)) {
            com_google_android_gms_internal_zzapi.zzdrl = this.zzdrl;
        }
    }
}
