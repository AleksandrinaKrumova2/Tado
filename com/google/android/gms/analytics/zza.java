package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzapm;
import com.google.android.gms.internal.zzapq;
import com.google.android.gms.internal.zzaqc;
import java.util.ListIterator;

public class zza extends zzi<zza> {
    private final zzaqc zzdoh;
    private boolean zzdoi;

    public zza(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc.zzwv(), com_google_android_gms_internal_zzaqc.zzws());
        this.zzdoh = com_google_android_gms_internal_zzaqc;
    }

    public final void enableAdvertisingIdCollection(boolean z) {
        this.zzdoi = z;
    }

    protected final void zza(zzg com_google_android_gms_analytics_zzg) {
        zzapm com_google_android_gms_internal_zzapm = (zzapm) com_google_android_gms_analytics_zzg.zzb(zzapm.class);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzapm.zzvz())) {
            com_google_android_gms_internal_zzapm.setClientId(this.zzdoh.zzxl().zzyk());
        }
        if (this.zzdoi && TextUtils.isEmpty(com_google_android_gms_internal_zzapm.zzwa())) {
            zzapq zzxk = this.zzdoh.zzxk();
            com_google_android_gms_internal_zzapm.zzdq(zzxk.zzwi());
            com_google_android_gms_internal_zzapm.zzai(zzxk.zzwb());
        }
    }

    public final void zzde(String str) {
        zzbq.zzgm(str);
        Uri zzdf = zzb.zzdf(str);
        ListIterator listIterator = this.zzdpt.getTransports().listIterator();
        while (listIterator.hasNext()) {
            if (zzdf.equals(((zzm) listIterator.next()).zzup())) {
                listIterator.remove();
            }
        }
        this.zzdpt.getTransports().add(new zzb(this.zzdoh, str));
    }

    final zzaqc zzum() {
        return this.zzdoh;
    }

    public final zzg zzun() {
        zzg zzus = this.zzdpt.zzus();
        zzus.zza(this.zzdoh.zzxd().zzxy());
        zzus.zza(this.zzdoh.zzxe().zzzc());
        zzd(zzus);
        return zzus;
    }
}
