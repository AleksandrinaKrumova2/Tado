package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zzf implements zzt {
    private final Map<String, List<zzr<?>>> zzp = new HashMap();
    private final zzd zzq;

    zzf(zzd com_google_android_gms_internal_zzd) {
        this.zzq = com_google_android_gms_internal_zzd;
    }

    private final synchronized boolean zzb(zzr<?> com_google_android_gms_internal_zzr_) {
        boolean z = false;
        synchronized (this) {
            String url = com_google_android_gms_internal_zzr_.getUrl();
            if (this.zzp.containsKey(url)) {
                List list = (List) this.zzp.get(url);
                if (list == null) {
                    list = new ArrayList();
                }
                com_google_android_gms_internal_zzr_.zzb("waiting-for-response");
                list.add(com_google_android_gms_internal_zzr_);
                this.zzp.put(url, list);
                if (zzae.DEBUG) {
                    zzae.zzb("Request for cacheKey=%s is in flight, putting on hold.", url);
                }
                z = true;
            } else {
                this.zzp.put(url, null);
                com_google_android_gms_internal_zzr_.zza((zzt) this);
                if (zzae.DEBUG) {
                    zzae.zzb("new request, sending to network %s", url);
                }
            }
        }
        return z;
    }

    public final synchronized void zza(zzr<?> com_google_android_gms_internal_zzr_) {
        String url = com_google_android_gms_internal_zzr_.getUrl();
        List list = (List) this.zzp.remove(url);
        if (!(list == null || list.isEmpty())) {
            if (zzae.DEBUG) {
                zzae.zza("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(list.size()), url);
            }
            zzr com_google_android_gms_internal_zzr = (zzr) list.remove(0);
            this.zzp.put(url, list);
            com_google_android_gms_internal_zzr.zza((zzt) this);
            try {
                this.zzq.zzi.put(com_google_android_gms_internal_zzr);
            } catch (InterruptedException e) {
                zzae.zzc("Couldn't add request to queue. %s", e.toString());
                Thread.currentThread().interrupt();
                this.zzq.quit();
            }
        }
    }

    public final void zza(zzr<?> com_google_android_gms_internal_zzr_, zzw<?> com_google_android_gms_internal_zzw_) {
        if (com_google_android_gms_internal_zzw_.zzbh == null || com_google_android_gms_internal_zzw_.zzbh.zza()) {
            zza(com_google_android_gms_internal_zzr_);
            return;
        }
        String url = com_google_android_gms_internal_zzr_.getUrl();
        synchronized (this) {
            List<zzr> list = (List) this.zzp.remove(url);
        }
        if (list != null) {
            if (zzae.DEBUG) {
                zzae.zza("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(list.size()), url);
            }
            for (zzr zzb : list) {
                this.zzq.zzk.zzb(zzb, com_google_android_gms_internal_zzw_);
            }
        }
    }
}
