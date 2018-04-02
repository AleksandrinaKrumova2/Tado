package com.google.android.gms.internal;

import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zza;
import java.util.Collections;

public final class zzaqg extends zzaqa {
    private final zzaqi zzdtx = new zzaqi(this);
    private zzarr zzdty;
    private final zzarf zzdtz;
    private final zzash zzdua;

    protected zzaqg(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
        this.zzdua = new zzash(com_google_android_gms_internal_zzaqc.zzws());
        this.zzdtz = new zzaqh(this, com_google_android_gms_internal_zzaqc);
    }

    private final void onServiceDisconnected(ComponentName componentName) {
        zzj.zzve();
        if (this.zzdty != null) {
            this.zzdty = null;
            zza("Disconnected from device AnalyticsService", componentName);
            zzwx().zzwp();
        }
    }

    private final void zza(zzarr com_google_android_gms_internal_zzarr) {
        zzj.zzve();
        this.zzdty = com_google_android_gms_internal_zzarr;
        zzxr();
        zzwx().onServiceConnected();
    }

    private final void zzxr() {
        this.zzdua.start();
        this.zzdtz.zzs(((Long) zzarl.zzdxg.get()).longValue());
    }

    private final void zzxs() {
        zzj.zzve();
        if (isConnected()) {
            zzdu("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public final boolean connect() {
        zzj.zzve();
        zzxf();
        if (this.zzdty != null) {
            return true;
        }
        zzarr zzxt = this.zzdtx.zzxt();
        if (zzxt == null) {
            return false;
        }
        this.zzdty = zzxt;
        zzxr();
        return true;
    }

    public final void disconnect() {
        zzj.zzve();
        zzxf();
        try {
            zza.zzamc();
            getContext().unbindService(this.zzdtx);
        } catch (IllegalStateException e) {
        } catch (IllegalArgumentException e2) {
        }
        if (this.zzdty != null) {
            this.zzdty = null;
            zzwx().zzwp();
        }
    }

    public final boolean isConnected() {
        zzj.zzve();
        zzxf();
        return this.zzdty != null;
    }

    public final boolean zzb(zzarq com_google_android_gms_internal_zzarq) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        zzj.zzve();
        zzxf();
        zzarr com_google_android_gms_internal_zzarr = this.zzdty;
        if (com_google_android_gms_internal_zzarr == null) {
            return false;
        }
        try {
            com_google_android_gms_internal_zzarr.zza(com_google_android_gms_internal_zzarq.zzjh(), com_google_android_gms_internal_zzarq.zzzi(), com_google_android_gms_internal_zzarq.zzzk() ? zzard.zzyw() : zzard.zzyx(), Collections.emptyList());
            zzxr();
            return true;
        } catch (RemoteException e) {
            zzdu("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    protected final void zzvf() {
    }

    public final boolean zzxq() {
        zzj.zzve();
        zzxf();
        zzarr com_google_android_gms_internal_zzarr = this.zzdty;
        if (com_google_android_gms_internal_zzarr == null) {
            return false;
        }
        try {
            com_google_android_gms_internal_zzarr.zzwm();
            zzxr();
            return true;
        } catch (RemoteException e) {
            zzdu("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
