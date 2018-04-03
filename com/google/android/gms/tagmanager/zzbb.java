package com.google.android.gms.tagmanager;

import android.content.Context;

public final class zzbb implements zzby {
    private static final Object zzkcr = new Object();
    private static zzbb zzkff;
    private zzek zzkdt;
    private zzbz zzkfg;

    private zzbb(Context context) {
        this(zzca.zzei(context), new zzfm());
    }

    private zzbb(zzbz com_google_android_gms_tagmanager_zzbz, zzek com_google_android_gms_tagmanager_zzek) {
        this.zzkfg = com_google_android_gms_tagmanager_zzbz;
        this.zzkdt = com_google_android_gms_tagmanager_zzek;
    }

    public static zzby zzec(Context context) {
        zzby com_google_android_gms_tagmanager_zzby;
        synchronized (zzkcr) {
            if (zzkff == null) {
                zzkff = new zzbb(context);
            }
            com_google_android_gms_tagmanager_zzby = zzkff;
        }
        return com_google_android_gms_tagmanager_zzby;
    }

    public final boolean zzll(String str) {
        if (this.zzkdt.zzzn()) {
            this.zzkfg.zzlq(str);
            return true;
        }
        zzdj.zzcu("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }
}
