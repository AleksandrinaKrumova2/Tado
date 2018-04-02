package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzh;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.tado.android.installation.CreateHomeContactDetailsActivity;
import java.util.HashMap;
import java.util.Map;

public final class zzape extends zzh<zzape> {
    private String mName;
    private String zzbsw;
    private String zzbuz;
    private String zzdrc;
    private String zzdrd;
    private String zzdre;
    private String zzdrf;
    private String zzdrg;
    private String zzdrh;
    private String zzdri;

    public final String getContent() {
        return this.zzbsw;
    }

    public final String getId() {
        return this.zzbuz;
    }

    public final String getName() {
        return this.mName;
    }

    public final String getSource() {
        return this.zzdrc;
    }

    public final void setName(String str) {
        this.mName = str;
    }

    public final String toString() {
        Map hashMap = new HashMap();
        hashMap.put(CreateHomeContactDetailsActivity.INTENT_NAME, this.mName);
        hashMap.put(Param.SOURCE, this.zzdrc);
        hashMap.put(Param.MEDIUM, this.zzdrd);
        hashMap.put("keyword", this.zzdre);
        hashMap.put(Param.CONTENT, this.zzbsw);
        hashMap.put(ToolTipRelativeLayout.ID, this.zzbuz);
        hashMap.put("adNetworkId", this.zzdrf);
        hashMap.put("gclid", this.zzdrg);
        hashMap.put("dclid", this.zzdrh);
        hashMap.put(Param.ACLID, this.zzdri);
        return zzh.zzl(hashMap);
    }

    public final /* synthetic */ void zzb(zzh com_google_android_gms_analytics_zzh) {
        zzape com_google_android_gms_internal_zzape = (zzape) com_google_android_gms_analytics_zzh;
        if (!TextUtils.isEmpty(this.mName)) {
            com_google_android_gms_internal_zzape.mName = this.mName;
        }
        if (!TextUtils.isEmpty(this.zzdrc)) {
            com_google_android_gms_internal_zzape.zzdrc = this.zzdrc;
        }
        if (!TextUtils.isEmpty(this.zzdrd)) {
            com_google_android_gms_internal_zzape.zzdrd = this.zzdrd;
        }
        if (!TextUtils.isEmpty(this.zzdre)) {
            com_google_android_gms_internal_zzape.zzdre = this.zzdre;
        }
        if (!TextUtils.isEmpty(this.zzbsw)) {
            com_google_android_gms_internal_zzape.zzbsw = this.zzbsw;
        }
        if (!TextUtils.isEmpty(this.zzbuz)) {
            com_google_android_gms_internal_zzape.zzbuz = this.zzbuz;
        }
        if (!TextUtils.isEmpty(this.zzdrf)) {
            com_google_android_gms_internal_zzape.zzdrf = this.zzdrf;
        }
        if (!TextUtils.isEmpty(this.zzdrg)) {
            com_google_android_gms_internal_zzape.zzdrg = this.zzdrg;
        }
        if (!TextUtils.isEmpty(this.zzdrh)) {
            com_google_android_gms_internal_zzape.zzdrh = this.zzdrh;
        }
        if (!TextUtils.isEmpty(this.zzdri)) {
            com_google_android_gms_internal_zzape.zzdri = this.zzdri;
        }
    }

    public final void zzdg(String str) {
        this.zzdrc = str;
    }

    public final void zzdh(String str) {
        this.zzdrd = str;
    }

    public final void zzdi(String str) {
        this.zzdre = str;
    }

    public final void zzdj(String str) {
        this.zzbsw = str;
    }

    public final void zzdk(String str) {
        this.zzbuz = str;
    }

    public final void zzdl(String str) {
        this.zzdrf = str;
    }

    public final void zzdm(String str) {
        this.zzdrg = str;
    }

    public final void zzdn(String str) {
        this.zzdrh = str;
    }

    public final void zzdo(String str) {
        this.zzdri = str;
    }

    public final String zzvl() {
        return this.zzdrd;
    }

    public final String zzvm() {
        return this.zzdre;
    }

    public final String zzvn() {
        return this.zzdrf;
    }

    public final String zzvo() {
        return this.zzdrg;
    }

    public final String zzvp() {
        return this.zzdrh;
    }

    public final String zzvq() {
        return this.zzdri;
    }
}
