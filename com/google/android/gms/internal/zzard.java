package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import java.util.HashSet;
import java.util.Set;

public final class zzard {
    private final zzaqc zzdoh;
    private volatile Boolean zzdvl;
    private String zzdvm;
    private Set<Integer> zzdvn;

    protected zzard(zzaqc com_google_android_gms_internal_zzaqc) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqc);
        this.zzdoh = com_google_android_gms_internal_zzaqc;
    }

    public static boolean zzyq() {
        return ((Boolean) zzarl.zzdvx.get()).booleanValue();
    }

    public static int zzyr() {
        return ((Integer) zzarl.zzdwu.get()).intValue();
    }

    public static long zzys() {
        return ((Long) zzarl.zzdwf.get()).longValue();
    }

    public static long zzyt() {
        return ((Long) zzarl.zzdwi.get()).longValue();
    }

    public static int zzyu() {
        return ((Integer) zzarl.zzdwk.get()).intValue();
    }

    public static int zzyv() {
        return ((Integer) zzarl.zzdwl.get()).intValue();
    }

    public static String zzyw() {
        return (String) zzarl.zzdwn.get();
    }

    public static String zzyx() {
        return (String) zzarl.zzdwm.get();
    }

    public static String zzyy() {
        return (String) zzarl.zzdwo.get();
    }

    public static long zzza() {
        return ((Long) zzarl.zzdxc.get()).longValue();
    }

    public final boolean zzyp() {
        if (this.zzdvl == null) {
            synchronized (this) {
                if (this.zzdvl == null) {
                    ApplicationInfo applicationInfo = this.zzdoh.getContext().getApplicationInfo();
                    String zzamo = zzs.zzamo();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(zzamo);
                        this.zzdvl = Boolean.valueOf(z);
                    }
                    if ((this.zzdvl == null || !this.zzdvl.booleanValue()) && "com.google.android.gms.analytics".equals(zzamo)) {
                        this.zzdvl = Boolean.TRUE;
                    }
                    if (this.zzdvl == null) {
                        this.zzdvl = Boolean.TRUE;
                        this.zzdoh.zzwt().zzdy("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzdvl.booleanValue();
    }

    public final Set<Integer> zzyz() {
        String str = (String) zzarl.zzdwx.get();
        if (this.zzdvn == null || this.zzdvm == null || !this.zzdvm.equals(str)) {
            String[] split = TextUtils.split(str, ",");
            Set hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException e) {
                }
            }
            this.zzdvm = str;
            this.zzdvn = hashSet;
        }
        return this.zzdvn;
    }
}
