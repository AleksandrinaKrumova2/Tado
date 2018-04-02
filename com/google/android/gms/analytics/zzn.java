package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzapd;
import com.google.android.gms.internal.zzapq;
import com.google.android.gms.internal.zzaqb;
import com.google.android.gms.internal.zzaqf;
import com.google.android.gms.internal.zzarq;
import com.google.android.gms.internal.zzasl;
import com.tado.android.utils.Constants;
import java.util.HashMap;
import java.util.Map;

final class zzn implements Runnable {
    private /* synthetic */ Map zzdqm;
    private /* synthetic */ boolean zzdqn;
    private /* synthetic */ String zzdqo;
    private /* synthetic */ long zzdqp;
    private /* synthetic */ boolean zzdqq;
    private /* synthetic */ boolean zzdqr;
    private /* synthetic */ String zzdqs;
    private /* synthetic */ Tracker zzdqt;

    zzn(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.zzdqt = tracker;
        this.zzdqm = map;
        this.zzdqn = z;
        this.zzdqo = str;
        this.zzdqp = j;
        this.zzdqq = z2;
        this.zzdqr = z3;
        this.zzdqs = str2;
    }

    public final void run() {
        boolean z = true;
        if (this.zzdqt.zzdqj.zzvg()) {
            this.zzdqm.put("sc", Constants.INSTALLATION_STATUS_START);
        }
        zza zzww = this.zzdqt.zzww();
        zzbq.zzgn("getClientId can not be called from the main thread");
        zzasl.zzc(this.zzdqm, "cid", zzww.zzum().zzxl().zzyk());
        String str = (String) this.zzdqm.get("sf");
        if (str != null) {
            double zza = zzasl.zza(str, 100.0d);
            if (zzasl.zza(zza, (String) this.zzdqm.get("cid"))) {
                this.zzdqt.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                return;
            }
        }
        zzapq zzb = this.zzdqt.zzxc();
        if (this.zzdqn) {
            zzasl.zzb(this.zzdqm, "ate", zzb.zzwb());
            zzasl.zzb(this.zzdqm, "adid", zzb.zzwi());
        } else {
            this.zzdqm.remove("ate");
            this.zzdqm.remove("adid");
        }
        zzapd zzxy = this.zzdqt.zzxd().zzxy();
        zzasl.zzb(this.zzdqm, "an", zzxy.zzvi());
        zzasl.zzb(this.zzdqm, "av", zzxy.zzvj());
        zzasl.zzb(this.zzdqm, "aid", zzxy.getAppId());
        zzasl.zzb(this.zzdqm, "aiid", zzxy.zzvk());
        this.zzdqm.put("v", "1");
        this.zzdqm.put("_v", zzaqb.zzdtc);
        zzasl.zzb(this.zzdqm, "ul", this.zzdqt.zzxe().zzzc().getLanguage());
        zzasl.zzb(this.zzdqm, "sr", this.zzdqt.zzxe().zzzd());
        boolean z2 = this.zzdqo.equals("transaction") || this.zzdqo.equals("item");
        if (z2 || this.zzdqt.zzdqi.zzzn()) {
            long zzei = zzasl.zzei((String) this.zzdqm.get("ht"));
            if (zzei == 0) {
                zzei = this.zzdqp;
            }
            if (this.zzdqq) {
                this.zzdqt.zzwt().zzc("Dry run enabled. Would have sent hit", new zzarq(this.zzdqt, this.zzdqm, zzei, this.zzdqr));
                return;
            }
            String str2 = (String) this.zzdqm.get("cid");
            Map hashMap = new HashMap();
            zzasl.zza(hashMap, "uid", this.zzdqm);
            zzasl.zza(hashMap, "an", this.zzdqm);
            zzasl.zza(hashMap, "aid", this.zzdqm);
            zzasl.zza(hashMap, "av", this.zzdqm);
            zzasl.zza(hashMap, "aiid", this.zzdqm);
            String str3 = this.zzdqs;
            if (TextUtils.isEmpty((CharSequence) this.zzdqm.get("adid"))) {
                z = false;
            }
            this.zzdqm.put("_s", String.valueOf(this.zzdqt.zzwx().zza(new zzaqf(0, str2, str3, z, 0, hashMap))));
            this.zzdqt.zzwx().zza(new zzarq(this.zzdqt, this.zzdqm, zzei, this.zzdqr));
            return;
        }
        this.zzdqt.zzwt().zzf(this.zzdqm, "Too many hits sent too quickly, rate limiting invoked");
    }
}
