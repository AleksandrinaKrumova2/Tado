package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public final class zzasm extends zzaqa {
    private boolean zzdox;
    private String zzdqz;
    private String zzdra;
    private int zzdvo;
    protected int zzdxr;
    protected boolean zzdzk;
    private boolean zzdzl;

    public zzasm(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    public final boolean zzaai() {
        zzxf();
        return false;
    }

    public final boolean zzaaj() {
        zzxf();
        return this.zzdzl;
    }

    public final boolean zzaak() {
        zzxf();
        return this.zzdox;
    }

    protected final void zzvf() {
        ApplicationInfo applicationInfo;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
        } catch (NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzdx("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null) {
            int i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource");
            if (i > 0) {
                zzarp com_google_android_gms_internal_zzarp = (zzarp) new zzarn(zzwr()).zzav(i);
                if (com_google_android_gms_internal_zzarp != null) {
                    String str;
                    int i2;
                    zzdu("Loading global XML config values");
                    if (com_google_android_gms_internal_zzarp.zzdqz != null) {
                        str = com_google_android_gms_internal_zzarp.zzdqz;
                        this.zzdqz = str;
                        zzb("XML config - app name", str);
                    }
                    if (com_google_android_gms_internal_zzarp.zzdra != null) {
                        str = com_google_android_gms_internal_zzarp.zzdra;
                        this.zzdra = str;
                        zzb("XML config - app version", str);
                    }
                    if (com_google_android_gms_internal_zzarp.zzdxq != null) {
                        str = com_google_android_gms_internal_zzarp.zzdxq.toLowerCase();
                        i2 = "verbose".equals(str) ? 0 : "info".equals(str) ? 1 : "warning".equals(str) ? 2 : "error".equals(str) ? 3 : -1;
                        if (i2 >= 0) {
                            this.zzdvo = i2;
                            zza("XML config - log level", Integer.valueOf(i2));
                        }
                    }
                    if (com_google_android_gms_internal_zzarp.zzdxr >= 0) {
                        i2 = com_google_android_gms_internal_zzarp.zzdxr;
                        this.zzdxr = i2;
                        this.zzdzk = true;
                        zzb("XML config - dispatch period (sec)", Integer.valueOf(i2));
                    }
                    if (com_google_android_gms_internal_zzarp.zzdxs != -1) {
                        boolean z = com_google_android_gms_internal_zzarp.zzdxs == 1;
                        this.zzdox = z;
                        this.zzdzl = true;
                        zzb("XML config - dry run", Boolean.valueOf(z));
                    }
                }
            }
        }
    }

    public final String zzvi() {
        zzxf();
        return this.zzdqz;
    }

    public final String zzvj() {
        zzxf();
        return this.zzdra;
    }
}
