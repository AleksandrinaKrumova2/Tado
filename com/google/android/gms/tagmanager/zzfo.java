package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

final class zzfo extends zzfn {
    private static final Object zzkjj = new Object();
    private static zzfo zzkjv;
    private boolean connected = true;
    private Context zzkjk;
    private zzcc zzkjl;
    private volatile zzbz zzkjm;
    private int zzkjn = 1800000;
    private boolean zzkjo = true;
    private boolean zzkjp = false;
    private boolean zzkjq = true;
    private zzcd zzkjr = new zzfp(this);
    private zzfr zzkjs;
    private zzdo zzkjt;
    private boolean zzkju = false;

    private zzfo() {
    }

    private final boolean isPowerSaveMode() {
        return this.zzkju || !this.connected || this.zzkjn <= 0;
    }

    public static zzfo zzbgg() {
        if (zzkjv == null) {
            zzkjv = new zzfo();
        }
        return zzkjv;
    }

    public final synchronized void dispatch() {
        if (this.zzkjp) {
            this.zzkjm.zzl(new zzfq(this));
        } else {
            zzdj.m11v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzkjo = true;
        }
    }

    final synchronized void zza(Context context, zzbz com_google_android_gms_tagmanager_zzbz) {
        if (this.zzkjk == null) {
            this.zzkjk = context.getApplicationContext();
            if (this.zzkjm == null) {
                this.zzkjm = com_google_android_gms_tagmanager_zzbz;
            }
        }
    }

    public final synchronized void zzbgf() {
        if (!isPowerSaveMode()) {
            this.zzkjs.zzbgj();
        }
    }

    final synchronized zzcc zzbgh() {
        if (this.zzkjl == null) {
            if (this.zzkjk == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzkjl = new zzec(this.zzkjr, this.zzkjk);
        }
        if (this.zzkjs == null) {
            this.zzkjs = new zzfs();
            if (this.zzkjn > 0) {
                this.zzkjs.zzs((long) this.zzkjn);
            }
        }
        this.zzkjp = true;
        if (this.zzkjo) {
            dispatch();
            this.zzkjo = false;
        }
        if (this.zzkjt == null && this.zzkjq) {
            this.zzkjt = new zzdo(this);
            BroadcastReceiver broadcastReceiver = this.zzkjt;
            Context context = this.zzkjk;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(broadcastReceiver, intentFilter);
            intentFilter = new IntentFilter();
            intentFilter.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
        return this.zzkjl;
    }

    public final synchronized void zzbv(boolean z) {
        zzd(this.zzkju, z);
    }

    final synchronized void zzd(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzkju = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            if (isPowerSaveMode()) {
                this.zzkjs.cancel();
                zzdj.m11v("PowerSaveMode initiated.");
            } else {
                this.zzkjs.zzs((long) this.zzkjn);
                zzdj.m11v("PowerSaveMode terminated.");
            }
        }
    }
}
