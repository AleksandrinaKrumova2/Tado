package com.google.android.gms.common.api;

final class zza implements com.google.android.gms.common.api.PendingResult.zza {
    private /* synthetic */ Batch zzfmb;

    zza(Batch batch) {
        this.zzfmb = batch;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzr(com.google.android.gms.common.api.Status r6) {
        /*
        r5 = this;
        r0 = r5.zzfmb;
        r1 = r0.mLock;
        monitor-enter(r1);
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r0 = r0.isCanceled();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
    L_0x0010:
        return;
    L_0x0011:
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x003c;
    L_0x0017:
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r2 = 1;
        r0.zzflz = true;	 Catch:{ all -> 0x0039 }
    L_0x001d:
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r0.zzflx = r0.zzflx - 1;	 Catch:{ all -> 0x0039 }
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r0 = r0.zzflx;	 Catch:{ all -> 0x0039 }
        if (r0 != 0) goto L_0x0037;
    L_0x002a:
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r0 = r0.zzflz;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0049;
    L_0x0032:
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        super.cancel();	 Catch:{ all -> 0x0039 }
    L_0x0037:
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        goto L_0x0010;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        throw r0;
    L_0x003c:
        r0 = r6.isSuccess();	 Catch:{ all -> 0x0039 }
        if (r0 != 0) goto L_0x001d;
    L_0x0042:
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r2 = 1;
        r0.zzfly = true;	 Catch:{ all -> 0x0039 }
        goto L_0x001d;
    L_0x0049:
        r0 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r0 = r0.zzfly;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0069;
    L_0x0051:
        r0 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x0039 }
        r2 = 13;
        r0.<init>(r2);	 Catch:{ all -> 0x0039 }
    L_0x0058:
        r2 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r3 = new com.google.android.gms.common.api.BatchResult;	 Catch:{ all -> 0x0039 }
        r4 = r5.zzfmb;	 Catch:{ all -> 0x0039 }
        r4 = r4.zzfma;	 Catch:{ all -> 0x0039 }
        r3.<init>(r0, r4);	 Catch:{ all -> 0x0039 }
        r2.setResult(r3);	 Catch:{ all -> 0x0039 }
        goto L_0x0037;
    L_0x0069:
        r0 = com.google.android.gms.common.api.Status.zzfni;	 Catch:{ all -> 0x0039 }
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zza.zzr(com.google.android.gms.common.api.Status):void");
    }
}
