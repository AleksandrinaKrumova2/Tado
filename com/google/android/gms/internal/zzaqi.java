package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zza;

public final class zzaqi implements ServiceConnection {
    final /* synthetic */ zzaqg zzdub;
    private volatile zzarr zzduc;
    private volatile boolean zzdud;

    protected zzaqi(zzaqg com_google_android_gms_internal_zzaqg) {
        this.zzdub = com_google_android_gms_internal_zzaqg;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
        r4 = this;
        r1 = 0;
        r0 = "AnalyticsServiceConnection.onServiceConnected";
        com.google.android.gms.common.internal.zzbq.zzge(r0);
        monitor-enter(r4);
        if (r6 != 0) goto L_0x0017;
    L_0x000a:
        r0 = r4.zzdub;	 Catch:{ all -> 0x006a }
        r1 = "Service connected with null binder";
        r0.zzdy(r1);	 Catch:{ all -> 0x006a }
        r4.notifyAll();	 Catch:{ all -> 0x0048 }
        monitor-exit(r4);	 Catch:{ all -> 0x0048 }
    L_0x0016:
        return;
    L_0x0017:
        r0 = r6.getInterfaceDescriptor();	 Catch:{ RemoteException -> 0x005f }
        r2 = "com.google.android.gms.analytics.internal.IAnalyticsService";
        r2 = r2.equals(r0);	 Catch:{ RemoteException -> 0x005f }
        if (r2 == 0) goto L_0x006f;
    L_0x0024:
        if (r6 != 0) goto L_0x004b;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = r4.zzdub;	 Catch:{ RemoteException -> 0x0099 }
        r2 = "Bound to IAnalyticsService interface";
        r1.zzdu(r2);	 Catch:{ RemoteException -> 0x0099 }
    L_0x002f:
        if (r0 != 0) goto L_0x0079;
    L_0x0031:
        com.google.android.gms.common.stats.zza.zzamc();	 Catch:{ IllegalArgumentException -> 0x0097 }
        r0 = r4.zzdub;	 Catch:{ IllegalArgumentException -> 0x0097 }
        r0 = r0.getContext();	 Catch:{ IllegalArgumentException -> 0x0097 }
        r1 = r4.zzdub;	 Catch:{ IllegalArgumentException -> 0x0097 }
        r1 = r1.zzdtx;	 Catch:{ IllegalArgumentException -> 0x0097 }
        r0.unbindService(r1);	 Catch:{ IllegalArgumentException -> 0x0097 }
    L_0x0043:
        r4.notifyAll();	 Catch:{ all -> 0x0048 }
        monitor-exit(r4);	 Catch:{ all -> 0x0048 }
        goto L_0x0016;
    L_0x0048:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0048 }
        throw r0;
    L_0x004b:
        r0 = "com.google.android.gms.analytics.internal.IAnalyticsService";
        r0 = r6.queryLocalInterface(r0);	 Catch:{ RemoteException -> 0x005f }
        r2 = r0 instanceof com.google.android.gms.internal.zzarr;	 Catch:{ RemoteException -> 0x005f }
        if (r2 == 0) goto L_0x0059;
    L_0x0056:
        r0 = (com.google.android.gms.internal.zzarr) r0;	 Catch:{ RemoteException -> 0x005f }
        goto L_0x0027;
    L_0x0059:
        r0 = new com.google.android.gms.internal.zzars;	 Catch:{ RemoteException -> 0x005f }
        r0.<init>(r6);	 Catch:{ RemoteException -> 0x005f }
        goto L_0x0027;
    L_0x005f:
        r0 = move-exception;
        r0 = r1;
    L_0x0061:
        r1 = r4.zzdub;	 Catch:{ all -> 0x006a }
        r2 = "Service connect failed to get IAnalyticsService";
        r1.zzdy(r2);	 Catch:{ all -> 0x006a }
        goto L_0x002f;
    L_0x006a:
        r0 = move-exception;
        r4.notifyAll();	 Catch:{ all -> 0x0048 }
        throw r0;	 Catch:{ all -> 0x0048 }
    L_0x006f:
        r2 = r4.zzdub;	 Catch:{ RemoteException -> 0x005f }
        r3 = "Got binder with a wrong descriptor";
        r2.zze(r3, r0);	 Catch:{ RemoteException -> 0x005f }
        r0 = r1;
        goto L_0x002f;
    L_0x0079:
        r1 = r4.zzdud;	 Catch:{ all -> 0x006a }
        if (r1 != 0) goto L_0x0094;
    L_0x007d:
        r1 = r4.zzdub;	 Catch:{ all -> 0x006a }
        r2 = "onServiceConnected received after the timeout limit";
        r1.zzdx(r2);	 Catch:{ all -> 0x006a }
        r1 = r4.zzdub;	 Catch:{ all -> 0x006a }
        r1 = r1.zzwv();	 Catch:{ all -> 0x006a }
        r2 = new com.google.android.gms.internal.zzaqj;	 Catch:{ all -> 0x006a }
        r2.<init>(r4, r0);	 Catch:{ all -> 0x006a }
        r1.zzc(r2);	 Catch:{ all -> 0x006a }
        goto L_0x0043;
    L_0x0094:
        r4.zzduc = r0;	 Catch:{ all -> 0x006a }
        goto L_0x0043;
    L_0x0097:
        r0 = move-exception;
        goto L_0x0043;
    L_0x0099:
        r1 = move-exception;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqi.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbq.zzge("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzdub.zzwv().zzc(new zzaqk(this, componentName));
    }

    public final zzarr zzxt() {
        zzarr com_google_android_gms_internal_zzarr = null;
        zzj.zzve();
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        Context context = this.zzdub.getContext();
        intent.putExtra("app_package_name", context.getPackageName());
        zza zzamc = zza.zzamc();
        synchronized (this) {
            this.zzduc = null;
            this.zzdud = true;
            boolean zza = zzamc.zza(context, intent, this.zzdub.zzdtx, 129);
            this.zzdub.zza("Bind to service requested", Boolean.valueOf(zza));
            if (zza) {
                try {
                    wait(((Long) zzarl.zzdxh.get()).longValue());
                } catch (InterruptedException e) {
                    this.zzdub.zzdx("Wait for service connect was interrupted");
                }
                this.zzdud = false;
                com_google_android_gms_internal_zzarr = this.zzduc;
                this.zzduc = null;
                if (com_google_android_gms_internal_zzarr == null) {
                    this.zzdub.zzdy("Successfully bound to service but never got onServiceConnected callback");
                }
            } else {
                this.zzdud = false;
            }
        }
        return com_google_android_gms_internal_zzarr;
    }
}
