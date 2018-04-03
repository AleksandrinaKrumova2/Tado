package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbr;

final class zzae implements zzdi<zzbr> {
    private /* synthetic */ zzy zzkee;

    private zzae(zzy com_google_android_gms_tagmanager_zzy) {
        this.zzkee = com_google_android_gms_tagmanager_zzy;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void onSuccess(java.lang.Object r6) {
        /*
        r5 = this;
        r6 = (com.google.android.gms.internal.zzbr) r6;
        r0 = r5.zzkee;
        r0 = r0.zzkdv;
        r0.zzbeg();
        r1 = r5.zzkee;
        monitor-enter(r1);
        r0 = r6.zzyi;	 Catch:{ all -> 0x007b }
        if (r0 != 0) goto L_0x003d;
    L_0x0012:
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r0 = r0.zzkea;	 Catch:{ all -> 0x007b }
        r0 = r0.zzyi;	 Catch:{ all -> 0x007b }
        if (r0 != 0) goto L_0x0033;
    L_0x001c:
        r0 = "Current resource is null; network resource is also null";
        com.google.android.gms.tagmanager.zzdj.m10e(r0);	 Catch:{ all -> 0x007b }
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r0 = r0.zzkdv;	 Catch:{ all -> 0x007b }
        r2 = r0.zzbee();	 Catch:{ all -> 0x007b }
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r0.zzbg(r2);	 Catch:{ all -> 0x007b }
        monitor-exit(r1);	 Catch:{ all -> 0x007b }
    L_0x0032:
        return;
    L_0x0033:
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r0 = r0.zzkea;	 Catch:{ all -> 0x007b }
        r0 = r0.zzyi;	 Catch:{ all -> 0x007b }
        r6.zzyi = r0;	 Catch:{ all -> 0x007b }
    L_0x003d:
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r2 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r2 = r2.zzata;	 Catch:{ all -> 0x007b }
        r2 = r2.currentTimeMillis();	 Catch:{ all -> 0x007b }
        r4 = 0;
        r0.zza(r6, r2, r4);	 Catch:{ all -> 0x007b }
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r2 = r0.zzkdi;	 Catch:{ all -> 0x007b }
        r0 = 58;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007b }
        r4.<init>(r0);	 Catch:{ all -> 0x007b }
        r0 = "setting refresh time to current time: ";
        r0 = r4.append(r0);	 Catch:{ all -> 0x007b }
        r0 = r0.append(r2);	 Catch:{ all -> 0x007b }
        r0 = r0.toString();	 Catch:{ all -> 0x007b }
        com.google.android.gms.tagmanager.zzdj.m11v(r0);	 Catch:{ all -> 0x007b }
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r0 = r0.zzbeb();	 Catch:{ all -> 0x007b }
        if (r0 != 0) goto L_0x0079;
    L_0x0074:
        r0 = r5.zzkee;	 Catch:{ all -> 0x007b }
        r0.zza(r6);	 Catch:{ all -> 0x007b }
    L_0x0079:
        monitor-exit(r1);	 Catch:{ all -> 0x007b }
        goto L_0x0032;
    L_0x007b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x007b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzae.onSuccess(java.lang.Object):void");
    }

    public final void zzei(int i) {
        if (i == zzda.zzkgr) {
            this.zzkee.zzkdv.zzbef();
        }
        synchronized (this.zzkee) {
            if (!this.zzkee.isReady()) {
                if (this.zzkee.zzkdy != null) {
                    this.zzkee.setResult(this.zzkee.zzkdy);
                } else {
                    this.zzkee.setResult(this.zzkee.zzam(Status.zzfnl));
                }
            }
        }
        this.zzkee.zzbg(this.zzkee.zzkdv.zzbee());
    }
}
