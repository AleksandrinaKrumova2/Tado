package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;

public class zzapz {
    private final zzaqc zzdta;

    protected zzapz(zzaqc com_google_android_gms_internal_zzaqc) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqc);
        this.zzdta = com_google_android_gms_internal_zzaqc;
    }

    private final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzarv com_google_android_gms_internal_zzarv = null;
        if (this.zzdta != null) {
            com_google_android_gms_internal_zzarv = this.zzdta.zzxh();
        }
        if (com_google_android_gms_internal_zzarv != null) {
            String str2 = (String) zzarl.zzdvy.get();
            if (Log.isLoggable(str2, i)) {
                Log.println(i, str2, zzc(str, obj, obj2, obj3));
            }
            if (i >= 5) {
                com_google_android_gms_internal_zzarv.zzb(i, str, obj, obj2, obj3);
                return;
            }
            return;
        }
        String str3 = (String) zzarl.zzdvy.get();
        if (Log.isLoggable(str3, i)) {
            Log.println(i, str3, zzc(str, obj, obj2, obj3));
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            Object obj4 = "";
        }
        Object zzm = zzm(obj);
        Object zzm2 = zzm(obj2);
        Object zzm3 = zzm(obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(obj4);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzm)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzm);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzm2)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzm2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzm3)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzm3);
        }
        return stringBuilder.toString();
    }

    private static String zzm(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj instanceof Boolean ? obj == Boolean.TRUE ? "true" : "false" : obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
    }

    public static boolean zzpz() {
        return Log.isLoggable((String) zzarl.zzdvy.get(), 2);
    }

    protected final Context getContext() {
        return this.zzdta.getContext();
    }

    public final void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public final void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public final void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public final void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public final void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public final void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public final void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public final void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    public final void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public final void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public final void zzdu(String str) {
        zza(2, str, null, null, null);
    }

    public final void zzdv(String str) {
        zza(3, str, null, null, null);
    }

    public final void zzdw(String str) {
        zza(4, str, null, null, null);
    }

    public final void zzdx(String str) {
        zza(5, str, null, null, null);
    }

    public final void zzdy(String str) {
        zza(6, str, null, null, null);
    }

    public final void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public final zzaqc zzwr() {
        return this.zzdta;
    }

    protected final zzd zzws() {
        return this.zzdta.zzws();
    }

    protected final zzarv zzwt() {
        return this.zzdta.zzwt();
    }

    protected final zzard zzwu() {
        return this.zzdta.zzwu();
    }

    protected final zzj zzwv() {
        return this.zzdta.zzwv();
    }

    public final GoogleAnalytics zzww() {
        return this.zzdta.zzxi();
    }

    protected final zzapr zzwx() {
        return this.zzdta.zzwx();
    }

    protected final zzari zzwy() {
        return this.zzdta.zzwy();
    }

    protected final zzasm zzwz() {
        return this.zzdta.zzwz();
    }

    protected final zzarz zzxa() {
        return this.zzdta.zzxa();
    }

    protected final zzaqu zzxb() {
        return this.zzdta.zzxl();
    }

    protected final zzapq zzxc() {
        return this.zzdta.zzxk();
    }

    protected final zzaqn zzxd() {
        return this.zzdta.zzxd();
    }

    protected final zzarh zzxe() {
        return this.zzdta.zzxe();
    }
}
