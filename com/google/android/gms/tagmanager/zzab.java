package com.google.android.gms.tagmanager;

final class zzab implements zzac {
    private /* synthetic */ zzy zzkee;
    private Long zzkef;
    private /* synthetic */ boolean zzkeg;

    zzab(zzy com_google_android_gms_tagmanager_zzy, boolean z) {
        this.zzkee = com_google_android_gms_tagmanager_zzy;
        this.zzkeg = z;
    }

    public final boolean zzb(Container container) {
        if (!this.zzkeg) {
            return !container.isDefault();
        } else {
            long lastRefreshTime = container.getLastRefreshTime();
            if (this.zzkef == null) {
                this.zzkef = Long.valueOf(this.zzkee.zzkdv.zzbed());
            }
            return lastRefreshTime + this.zzkef.longValue() >= this.zzkee.zzata.currentTimeMillis();
        }
    }
}
