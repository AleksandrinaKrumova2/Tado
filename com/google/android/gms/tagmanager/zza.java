package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;

public final class zza {
    private static Object zzkcr = new Object();
    private static zza zzkcs;
    private volatile boolean mClosed;
    private final Context mContext;
    private final zzd zzata;
    private final Thread zzdac;
    private volatile Info zzdsn;
    private volatile long zzkcl;
    private volatile long zzkcm;
    private volatile long zzkcn;
    private volatile long zzkco;
    private final Object zzkcp;
    private zzd zzkcq;

    private zza(Context context) {
        this(context, null, zzh.zzamg());
    }

    private zza(Context context, zzd com_google_android_gms_tagmanager_zzd, zzd com_google_android_gms_common_util_zzd) {
        this.zzkcl = 900000;
        this.zzkcm = 30000;
        this.mClosed = false;
        this.zzkcp = new Object();
        this.zzkcq = new zzb(this);
        this.zzata = com_google_android_gms_common_util_zzd;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.zzkcn = this.zzata.currentTimeMillis();
        this.zzdac = new Thread(new zzc(this));
    }

    private final void zzbdk() {
        synchronized (this) {
            try {
                if (!this.mClosed) {
                    zzbdl();
                    wait(500);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private final void zzbdl() {
        if (this.zzata.currentTimeMillis() - this.zzkcn > this.zzkcm) {
            synchronized (this.zzkcp) {
                this.zzkcp.notify();
            }
            this.zzkcn = this.zzata.currentTimeMillis();
        }
    }

    private final void zzbdm() {
        if (this.zzata.currentTimeMillis() - this.zzkco > 3600000) {
            this.zzdsn = null;
        }
    }

    private final void zzbdn() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            Info zzbdo = this.zzkcq.zzbdo();
            if (zzbdo != null) {
                this.zzdsn = zzbdo;
                this.zzkco = this.zzata.currentTimeMillis();
                zzdj.zzct("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                notifyAll();
            }
            try {
                synchronized (this.zzkcp) {
                    this.zzkcp.wait(this.zzkcl);
                }
            } catch (InterruptedException e) {
                zzdj.zzct("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    public static zza zzeb(Context context) {
        if (zzkcs == null) {
            synchronized (zzkcr) {
                if (zzkcs == null) {
                    zza com_google_android_gms_tagmanager_zza = new zza(context);
                    zzkcs = com_google_android_gms_tagmanager_zza;
                    com_google_android_gms_tagmanager_zza.zzdac.start();
                }
            }
        }
        return zzkcs;
    }

    public final void close() {
        this.mClosed = true;
        this.zzdac.interrupt();
    }

    public final boolean isLimitAdTrackingEnabled() {
        if (this.zzdsn == null) {
            zzbdk();
        } else {
            zzbdl();
        }
        zzbdm();
        return this.zzdsn == null ? true : this.zzdsn.isLimitAdTrackingEnabled();
    }

    public final String zzbdj() {
        if (this.zzdsn == null) {
            zzbdk();
        } else {
            zzbdl();
        }
        zzbdm();
        return this.zzdsn == null ? null : this.zzdsn.getId();
    }
}
