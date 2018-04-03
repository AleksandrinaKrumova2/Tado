package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;

final class zzv implements ContainerHolder {
    private Status mStatus;
    private final Looper zzall;
    private boolean zzgln;
    private Container zzkdl;
    private Container zzkdm;
    private zzx zzkdn;
    private zzw zzkdo;
    private TagManager zzkdp;

    public zzv(Status status) {
        this.mStatus = status;
        this.zzall = null;
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw com_google_android_gms_tagmanager_zzw) {
        this.zzkdp = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzall = looper;
        this.zzkdl = container;
        this.zzkdo = com_google_android_gms_tagmanager_zzw;
        this.mStatus = Status.zzfni;
        tagManager.zza(this);
    }

    private final void zzbdw() {
        if (this.zzkdn != null) {
            zzx com_google_android_gms_tagmanager_zzx = this.zzkdn;
            com_google_android_gms_tagmanager_zzx.sendMessage(com_google_android_gms_tagmanager_zzx.obtainMessage(1, this.zzkdm.zzbdt()));
        }
    }

    public final synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.zzgln) {
                zzdj.m10e("ContainerHolder is released.");
            } else {
                if (this.zzkdm != null) {
                    this.zzkdl = this.zzkdm;
                    this.zzkdm = null;
                }
                container = this.zzkdl;
            }
        }
        return container;
    }

    final String getContainerId() {
        if (!this.zzgln) {
            return this.zzkdl.getContainerId();
        }
        zzdj.m10e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final synchronized void refresh() {
        if (this.zzgln) {
            zzdj.m10e("Refreshing a released ContainerHolder.");
        } else {
            this.zzkdo.zzbdx();
        }
    }

    public final synchronized void release() {
        if (this.zzgln) {
            zzdj.m10e("Releasing a released ContainerHolder.");
        } else {
            this.zzgln = true;
            this.zzkdp.zzb(this);
            this.zzkdl.release();
            this.zzkdl = null;
            this.zzkdm = null;
            this.zzkdo = null;
            this.zzkdn = null;
        }
    }

    public final synchronized void setContainerAvailableListener(ContainerAvailableListener containerAvailableListener) {
        if (this.zzgln) {
            zzdj.m10e("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zzkdn = null;
        } else {
            this.zzkdn = new zzx(this, containerAvailableListener, this.zzall);
            if (this.zzkdm != null) {
                zzbdw();
            }
        }
    }

    public final synchronized void zza(Container container) {
        if (!this.zzgln) {
            this.zzkdm = container;
            zzbdw();
        }
    }

    final String zzbdv() {
        if (!this.zzgln) {
            return this.zzkdo.zzbdv();
        }
        zzdj.m10e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public final synchronized void zzld(String str) {
        if (!this.zzgln) {
            this.zzkdl.zzld(str);
        }
    }

    final void zzle(String str) {
        if (this.zzgln) {
            zzdj.m10e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzkdo.zzle(str);
        }
    }
}
