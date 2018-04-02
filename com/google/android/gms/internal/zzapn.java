package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.zzh;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class zzapn extends zzh<zzapn> {
    private String zzaqi;
    private int zzdsb;
    private int zzdsc;
    private String zzdsd;
    private String zzdse;
    private boolean zzdsf;
    private boolean zzdsg;

    public zzapn() {
        this(false);
    }

    private zzapn(boolean z) {
        UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits == 0) {
            leastSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L);
            if (leastSignificantBits == 0) {
                Log.e("GAv4", "UUID.randomUUID() returned 0.");
                leastSignificantBits = Integer.MAX_VALUE;
            }
        }
        this(false, leastSignificantBits);
    }

    private zzapn(boolean z, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        }
        this.zzdsb = i;
        this.zzdsg = false;
    }

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put("screenName", this.zzaqi);
        hashMap.put("interstitial", Boolean.valueOf(this.zzdsf));
        hashMap.put("automatic", Boolean.valueOf(this.zzdsg));
        hashMap.put("screenId", Integer.valueOf(this.zzdsb));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzdsc));
        hashMap.put("referrerScreenName", this.zzdsd);
        hashMap.put("referrerUri", this.zzdse);
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzapn com_google_android_gms_internal_zzapn = (zzapn) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.zzaqi)) {
            com_google_android_gms_internal_zzapn.zzaqi = this.zzaqi;
        }
        if (this.zzdsb != 0) {
            com_google_android_gms_internal_zzapn.zzdsb = this.zzdsb;
        }
        if (this.zzdsc != 0) {
            com_google_android_gms_internal_zzapn.zzdsc = this.zzdsc;
        }
        if (!TextUtils.isEmpty(this.zzdsd)) {
            com_google_android_gms_internal_zzapn.zzdsd = this.zzdsd;
        }
        if (!TextUtils.isEmpty(this.zzdse)) {
            Object obj = this.zzdse;
            if (TextUtils.isEmpty(obj)) {
                com_google_android_gms_internal_zzapn.zzdse = null;
            } else {
                com_google_android_gms_internal_zzapn.zzdse = obj;
            }
        }
        if (this.zzdsf) {
            com_google_android_gms_internal_zzapn.zzdsf = this.zzdsf;
        }
        if (this.zzdsg) {
            com_google_android_gms_internal_zzapn.zzdsg = this.zzdsg;
        }
    }

    public final String zzwf() {
        return this.zzaqi;
    }

    public final int zzwg() {
        return this.zzdsb;
    }

    public final String zzwh() {
        return this.zzdse;
    }
}
