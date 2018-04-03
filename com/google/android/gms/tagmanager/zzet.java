package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbr;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzet implements zzag {
    private boolean mClosed;
    private final Context mContext;
    private final String zzkdd;
    private String zzkeb;
    private zzdi<zzbr> zzkic;
    private zzal zzkid;
    private final ScheduledExecutorService zzkif;
    private final zzew zzkig;
    private ScheduledFuture<?> zzkih;

    public zzet(Context context, String str, zzal com_google_android_gms_tagmanager_zzal) {
        this(context, str, com_google_android_gms_tagmanager_zzal, null, null);
    }

    private zzet(Context context, String str, zzal com_google_android_gms_tagmanager_zzal, zzex com_google_android_gms_tagmanager_zzex, zzew com_google_android_gms_tagmanager_zzew) {
        this.zzkid = com_google_android_gms_tagmanager_zzal;
        this.mContext = context;
        this.zzkdd = str;
        this.zzkif = new zzeu(this).zzbfs();
        this.zzkig = new zzev(this);
    }

    private final synchronized void zzbfr() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    public final synchronized void release() {
        zzbfr();
        if (this.zzkih != null) {
            this.zzkih.cancel(false);
        }
        this.zzkif.shutdown();
        this.mClosed = true;
    }

    public final synchronized void zza(long j, String str) {
        String str2 = this.zzkdd;
        zzdj.m11v(new StringBuilder(String.valueOf(str2).length() + 55).append("loadAfterDelay: containerId=").append(str2).append(" delay=").append(j).toString());
        zzbfr();
        if (this.zzkic == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.zzkih != null) {
            this.zzkih.cancel(false);
        }
        ScheduledExecutorService scheduledExecutorService = this.zzkif;
        Runnable zza = this.zzkig.zza(this.zzkid);
        zza.zza(this.zzkic);
        zza.zzlf(this.zzkeb);
        zza.zzlv(str);
        this.zzkih = scheduledExecutorService.schedule(zza, j, TimeUnit.MILLISECONDS);
    }

    public final synchronized void zza(zzdi<zzbr> com_google_android_gms_tagmanager_zzdi_com_google_android_gms_internal_zzbr) {
        zzbfr();
        this.zzkic = com_google_android_gms_tagmanager_zzdi_com_google_android_gms_internal_zzbr;
    }

    public final synchronized void zzlf(String str) {
        zzbfr();
        this.zzkeb = str;
    }
}
