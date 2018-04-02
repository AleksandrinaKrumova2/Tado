package com.google.android.gms.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class zzaqo extends zzaqa {
    private boolean mStarted;
    private final zzaql zzdum;
    private final zzarx zzdun;
    private final zzarw zzduo;
    private final zzaqg zzdup;
    private long zzduq = Long.MIN_VALUE;
    private final zzarf zzdur;
    private final zzarf zzdus;
    private final zzash zzdut;
    private long zzduu;
    private boolean zzduv;

    protected zzaqo(zzaqc com_google_android_gms_internal_zzaqc, zzaqe com_google_android_gms_internal_zzaqe) {
        super(com_google_android_gms_internal_zzaqc);
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqe);
        this.zzduo = new zzarw(com_google_android_gms_internal_zzaqc);
        this.zzdum = new zzaql(com_google_android_gms_internal_zzaqc);
        this.zzdun = new zzarx(com_google_android_gms_internal_zzaqc);
        this.zzdup = new zzaqg(com_google_android_gms_internal_zzaqc);
        this.zzdut = new zzash(zzws());
        this.zzdur = new zzaqp(this, com_google_android_gms_internal_zzaqc);
        this.zzdus = new zzaqq(this, com_google_android_gms_internal_zzaqc);
    }

    private final void zza(zzaqf com_google_android_gms_internal_zzaqf, zzape com_google_android_gms_internal_zzape) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqf);
        zzbq.checkNotNull(com_google_android_gms_internal_zzape);
        zzi com_google_android_gms_analytics_zza = new zza(zzwr());
        com_google_android_gms_analytics_zza.zzde(com_google_android_gms_internal_zzaqf.zzxn());
        com_google_android_gms_analytics_zza.enableAdvertisingIdCollection(com_google_android_gms_internal_zzaqf.zzxo());
        zzg zzun = com_google_android_gms_analytics_zza.zzun();
        zzapm com_google_android_gms_internal_zzapm = (zzapm) zzun.zzb(zzapm.class);
        com_google_android_gms_internal_zzapm.zzdp("data");
        com_google_android_gms_internal_zzapm.zzaj(true);
        zzun.zza((zzh) com_google_android_gms_internal_zzape);
        zzaph com_google_android_gms_internal_zzaph = (zzaph) zzun.zzb(zzaph.class);
        zzapd com_google_android_gms_internal_zzapd = (zzapd) zzun.zzb(zzapd.class);
        for (Entry entry : com_google_android_gms_internal_zzaqf.zzjh().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                com_google_android_gms_internal_zzapd.setAppName(str2);
            } else if ("av".equals(str)) {
                com_google_android_gms_internal_zzapd.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                com_google_android_gms_internal_zzapd.setAppId(str2);
            } else if ("aiid".equals(str)) {
                com_google_android_gms_internal_zzapd.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                com_google_android_gms_internal_zzapm.setUserId(str2);
            } else {
                com_google_android_gms_internal_zzaph.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", com_google_android_gms_internal_zzaqf.zzxn(), com_google_android_gms_internal_zzape);
        zzun.zzl(zzxa().zzzw());
        zzun.zzuv();
    }

    private final boolean zzeb(String str) {
        return zzbhf.zzdb(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    private final long zzxv() {
        zzj.zzve();
        zzxf();
        try {
            return this.zzdum.zzxv();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    private final void zzya() {
        zzb(new zzaqs(this));
    }

    private final void zzyb() {
        try {
            this.zzdum.zzxu();
            zzyf();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzdus.zzs(86400000);
    }

    private final void zzyc() {
        if (!this.zzduv && zzard.zzyq() && !this.zzdup.isConnected()) {
            if (this.zzdut.zzu(((Long) zzarl.zzdxk.get()).longValue())) {
                this.zzdut.start();
                zzdu("Connecting to service");
                if (this.zzdup.connect()) {
                    zzdu("Connected to service");
                    this.zzdut.clear();
                    onServiceConnected();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzyd() {
        /*
        r12 = this;
        r1 = 1;
        r2 = 0;
        com.google.android.gms.analytics.zzj.zzve();
        r12.zzxf();
        r0 = "Dispatching a batch of local hits";
        r12.zzdu(r0);
        r0 = r12.zzdup;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x002a;
    L_0x0016:
        r0 = r1;
    L_0x0017:
        r3 = r12.zzdun;
        r3 = r3.zzzs();
        if (r3 != 0) goto L_0x002c;
    L_0x001f:
        if (r0 == 0) goto L_0x002e;
    L_0x0021:
        if (r1 == 0) goto L_0x002e;
    L_0x0023:
        r0 = "No network or service available. Will retry later";
        r12.zzdu(r0);
    L_0x0029:
        return r2;
    L_0x002a:
        r0 = r2;
        goto L_0x0017;
    L_0x002c:
        r1 = r2;
        goto L_0x001f;
    L_0x002e:
        r0 = com.google.android.gms.internal.zzard.zzyu();
        r1 = com.google.android.gms.internal.zzard.zzyv();
        r0 = java.lang.Math.max(r0, r1);
        r6 = (long) r0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = 0;
    L_0x0042:
        r0 = r12.zzdum;	 Catch:{ all -> 0x01e0 }
        r0.beginTransaction();	 Catch:{ all -> 0x01e0 }
        r3.clear();	 Catch:{ all -> 0x01e0 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x00c8 }
        r8 = r0.zzo(r6);	 Catch:{ SQLiteException -> 0x00c8 }
        r0 = r8.isEmpty();	 Catch:{ SQLiteException -> 0x00c8 }
        if (r0 == 0) goto L_0x0075;
    L_0x0056:
        r0 = "Store is empty, nothing to dispatch";
        r12.zzdu(r0);	 Catch:{ SQLiteException -> 0x00c8 }
        r12.zzyh();	 Catch:{ SQLiteException -> 0x00c8 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x006a }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x006a }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x006a }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x006a }
        goto L_0x0029;
    L_0x006a:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x0075:
        r0 = "Hits loaded from store. count";
        r1 = r8.size();	 Catch:{ SQLiteException -> 0x00c8 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x00c8 }
        r12.zza(r0, r1);	 Catch:{ SQLiteException -> 0x00c8 }
        r1 = r8.iterator();	 Catch:{ all -> 0x01e0 }
    L_0x0087:
        r0 = r1.hasNext();	 Catch:{ all -> 0x01e0 }
        if (r0 == 0) goto L_0x00ea;
    L_0x008d:
        r0 = r1.next();	 Catch:{ all -> 0x01e0 }
        r0 = (com.google.android.gms.internal.zzarq) r0;	 Catch:{ all -> 0x01e0 }
        r10 = r0.zzzh();	 Catch:{ all -> 0x01e0 }
        r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0087;
    L_0x009b:
        r0 = "Database contains successfully uploaded hit";
        r1 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01e0 }
        r3 = r8.size();	 Catch:{ all -> 0x01e0 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x01e0 }
        r12.zzd(r0, r1, r3);	 Catch:{ all -> 0x01e0 }
        r12.zzyh();	 Catch:{ all -> 0x01e0 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x00bc }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00bc }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x00bc }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00bc }
        goto L_0x0029;
    L_0x00bc:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x00c8:
        r0 = move-exception;
        r1 = "Failed to read hits from persisted store";
        r12.zzd(r1, r0);	 Catch:{ all -> 0x01e0 }
        r12.zzyh();	 Catch:{ all -> 0x01e0 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x00de }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00de }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x00de }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00de }
        goto L_0x0029;
    L_0x00de:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x00ea:
        r0 = r12.zzdup;	 Catch:{ all -> 0x01e0 }
        r0 = r0.isConnected();	 Catch:{ all -> 0x01e0 }
        if (r0 == 0) goto L_0x0155;
    L_0x00f2:
        r0 = "Service connected, sending hits to the service";
        r12.zzdu(r0);	 Catch:{ all -> 0x01e0 }
    L_0x00f8:
        r0 = r8.isEmpty();	 Catch:{ all -> 0x01e0 }
        if (r0 != 0) goto L_0x0155;
    L_0x00fe:
        r0 = 0;
        r0 = r8.get(r0);	 Catch:{ all -> 0x01e0 }
        r0 = (com.google.android.gms.internal.zzarq) r0;	 Catch:{ all -> 0x01e0 }
        r1 = r12.zzdup;	 Catch:{ all -> 0x01e0 }
        r1 = r1.zzb(r0);	 Catch:{ all -> 0x01e0 }
        if (r1 == 0) goto L_0x0155;
    L_0x010d:
        r10 = r0.zzzh();	 Catch:{ all -> 0x01e0 }
        r4 = java.lang.Math.max(r4, r10);	 Catch:{ all -> 0x01e0 }
        r8.remove(r0);	 Catch:{ all -> 0x01e0 }
        r1 = "Hit sent do device AnalyticsService for delivery";
        r12.zzb(r1, r0);	 Catch:{ all -> 0x01e0 }
        r1 = r12.zzdum;	 Catch:{ SQLiteException -> 0x0133 }
        r10 = r0.zzzh();	 Catch:{ SQLiteException -> 0x0133 }
        r1.zzp(r10);	 Catch:{ SQLiteException -> 0x0133 }
        r0 = r0.zzzh();	 Catch:{ SQLiteException -> 0x0133 }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ SQLiteException -> 0x0133 }
        r3.add(r0);	 Catch:{ SQLiteException -> 0x0133 }
        goto L_0x00f8;
    L_0x0133:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r12.zze(r1, r0);	 Catch:{ all -> 0x01e0 }
        r12.zzyh();	 Catch:{ all -> 0x01e0 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x0149 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0149 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x0149 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0149 }
        goto L_0x0029;
    L_0x0149:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x0155:
        r0 = r4;
        r4 = r12.zzdun;	 Catch:{ all -> 0x01e0 }
        r4 = r4.zzzs();	 Catch:{ all -> 0x01e0 }
        if (r4 == 0) goto L_0x0187;
    L_0x015e:
        r4 = r12.zzdun;	 Catch:{ all -> 0x01e0 }
        r8 = r4.zzu(r8);	 Catch:{ all -> 0x01e0 }
        r9 = r8.iterator();	 Catch:{ all -> 0x01e0 }
        r4 = r0;
    L_0x0169:
        r0 = r9.hasNext();	 Catch:{ all -> 0x01e0 }
        if (r0 == 0) goto L_0x017e;
    L_0x016f:
        r0 = r9.next();	 Catch:{ all -> 0x01e0 }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x01e0 }
        r0 = r0.longValue();	 Catch:{ all -> 0x01e0 }
        r4 = java.lang.Math.max(r4, r0);	 Catch:{ all -> 0x01e0 }
        goto L_0x0169;
    L_0x017e:
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01a5 }
        r0.zzs(r8);	 Catch:{ SQLiteException -> 0x01a5 }
        r3.addAll(r8);	 Catch:{ SQLiteException -> 0x01a5 }
        r0 = r4;
    L_0x0187:
        r4 = r3.isEmpty();	 Catch:{ all -> 0x01e0 }
        if (r4 == 0) goto L_0x01c7;
    L_0x018d:
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x0199 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0199 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x0199 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0199 }
        goto L_0x0029;
    L_0x0199:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x01a5:
        r0 = move-exception;
        r1 = "Failed to remove successfully uploaded hits";
        r12.zze(r1, r0);	 Catch:{ all -> 0x01e0 }
        r12.zzyh();	 Catch:{ all -> 0x01e0 }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01bb }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01bb }
        r0 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01bb }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x01bb }
        goto L_0x0029;
    L_0x01bb:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x01c7:
        r4 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01d4 }
        r4.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01d4 }
        r4 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01d4 }
        r4.endTransaction();	 Catch:{ SQLiteException -> 0x01d4 }
        r4 = r0;
        goto L_0x0042;
    L_0x01d4:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
    L_0x01e0:
        r0 = move-exception;
        r1 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01ec }
        r1.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01ec }
        r1 = r12.zzdum;	 Catch:{ SQLiteException -> 0x01ec }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x01ec }
        throw r0;
    L_0x01ec:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzyh();
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqo.zzyd():boolean");
    }

    private final void zzyg() {
        zzari zzwy = zzwy();
        if (zzwy.zzze() && !zzwy.zzdx()) {
            long zzxv = zzxv();
            if (zzxv != 0 && Math.abs(zzws().currentTimeMillis() - zzxv) <= ((Long) zzarl.zzdwj.get()).longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzard.zzyt()));
                zzwy.schedule();
            }
        }
    }

    private final void zzyh() {
        if (this.zzdur.zzdx()) {
            zzdu("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzdur.cancel();
        zzari zzwy = zzwy();
        if (zzwy.zzdx()) {
            zzwy.cancel();
        }
    }

    private final long zzyi() {
        if (this.zzduq != Long.MIN_VALUE) {
            return this.zzduq;
        }
        long longValue = ((Long) zzarl.zzdwe.get()).longValue();
        zzaqa zzwz = zzwz();
        zzwz.zzxf();
        if (!zzwz.zzdzk) {
            return longValue;
        }
        zzaqa zzwz2 = zzwz();
        zzwz2.zzxf();
        return ((long) zzwz2.zzdxr) * 1000;
    }

    private final void zzyj() {
        zzxf();
        zzj.zzve();
        this.zzduv = true;
        this.zzdup.disconnect();
        zzyf();
    }

    protected final void onServiceConnected() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = this;
        com.google.android.gms.analytics.zzj.zzve();
        com.google.android.gms.analytics.zzj.zzve();
        r6.zzxf();
        r0 = com.google.android.gms.internal.zzard.zzyq();
        if (r0 != 0) goto L_0x0015;
    L_0x000f:
        r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService";
        r6.zzdx(r0);
    L_0x0015:
        r0 = r6.zzdup;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0024;
    L_0x001d:
        r0 = "Service not connected";
        r6.zzdu(r0);
    L_0x0023:
        return;
    L_0x0024:
        r0 = r6.zzdum;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0023;
    L_0x002c:
        r0 = "Dispatching local hits to device AnalyticsService";
        r6.zzdu(r0);
    L_0x0032:
        r0 = r6.zzdum;	 Catch:{ SQLiteException -> 0x0047 }
        r1 = com.google.android.gms.internal.zzard.zzyu();	 Catch:{ SQLiteException -> 0x0047 }
        r2 = (long) r1;	 Catch:{ SQLiteException -> 0x0047 }
        r1 = r0.zzo(r2);	 Catch:{ SQLiteException -> 0x0047 }
        r0 = r1.isEmpty();	 Catch:{ SQLiteException -> 0x0047 }
        if (r0 == 0) goto L_0x005e;	 Catch:{ SQLiteException -> 0x0047 }
    L_0x0043:
        r6.zzyf();	 Catch:{ SQLiteException -> 0x0047 }
        goto L_0x0023;
    L_0x0047:
        r0 = move-exception;
        r1 = "Failed to read hits from store";
        r6.zze(r1, r0);
        r6.zzyh();
        goto L_0x0023;
    L_0x0052:
        r1.remove(r0);
        r2 = r6.zzdum;	 Catch:{ SQLiteException -> 0x0077 }
        r4 = r0.zzzh();	 Catch:{ SQLiteException -> 0x0077 }
        r2.zzp(r4);	 Catch:{ SQLiteException -> 0x0077 }
    L_0x005e:
        r0 = r1.isEmpty();
        if (r0 != 0) goto L_0x0032;
    L_0x0064:
        r0 = 0;
        r0 = r1.get(r0);
        r0 = (com.google.android.gms.internal.zzarq) r0;
        r2 = r6.zzdup;
        r2 = r2.zzb(r0);
        if (r2 != 0) goto L_0x0052;
    L_0x0073:
        r6.zzyf();
        goto L_0x0023;
    L_0x0077:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r6.zze(r1, r0);
        r6.zzyh();
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqo.onServiceConnected():void");
    }

    final void start() {
        zzxf();
        zzbq.zza(!this.mStarted, (Object) "Analytics backend already started");
        this.mStarted = true;
        zzwv().zzc(new zzaqr(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zza(com.google.android.gms.internal.zzaqf r11, boolean r12) {
        /*
        r10 = this;
        com.google.android.gms.common.internal.zzbq.checkNotNull(r11);
        r10.zzxf();
        com.google.android.gms.analytics.zzj.zzve();
        r0 = r10.zzdum;	 Catch:{ SQLiteException -> 0x009f }
        r0.beginTransaction();	 Catch:{ SQLiteException -> 0x009f }
        r0 = r10.zzdum;	 Catch:{ SQLiteException -> 0x009f }
        r2 = r11.zzxm();	 Catch:{ SQLiteException -> 0x009f }
        r1 = r11.zzvz();	 Catch:{ SQLiteException -> 0x009f }
        com.google.android.gms.common.internal.zzbq.zzgm(r1);	 Catch:{ SQLiteException -> 0x009f }
        r0.zzxf();	 Catch:{ SQLiteException -> 0x009f }
        com.google.android.gms.analytics.zzj.zzve();	 Catch:{ SQLiteException -> 0x009f }
        r4 = r0.getWritableDatabase();	 Catch:{ SQLiteException -> 0x009f }
        r5 = "properties";
        r6 = "app_uid=? AND cid<>?";
        r7 = 2;
        r7 = new java.lang.String[r7];	 Catch:{ SQLiteException -> 0x009f }
        r8 = 0;
        r2 = java.lang.String.valueOf(r2);	 Catch:{ SQLiteException -> 0x009f }
        r7[r8] = r2;	 Catch:{ SQLiteException -> 0x009f }
        r2 = 1;
        r7[r2] = r1;	 Catch:{ SQLiteException -> 0x009f }
        r1 = r4.delete(r5, r6, r7);	 Catch:{ SQLiteException -> 0x009f }
        if (r1 <= 0) goto L_0x0048;
    L_0x003e:
        r2 = "Deleted property records";
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x009f }
        r0.zza(r2, r1);	 Catch:{ SQLiteException -> 0x009f }
    L_0x0048:
        r0 = r10.zzdum;	 Catch:{ SQLiteException -> 0x009f }
        r2 = r11.zzxm();	 Catch:{ SQLiteException -> 0x009f }
        r1 = r11.zzvz();	 Catch:{ SQLiteException -> 0x009f }
        r4 = r11.zzxn();	 Catch:{ SQLiteException -> 0x009f }
        r2 = r0.zza(r2, r1, r4);	 Catch:{ SQLiteException -> 0x009f }
        r0 = 1;
        r0 = r0 + r2;
        r11.zzm(r0);	 Catch:{ SQLiteException -> 0x009f }
        r4 = r10.zzdum;	 Catch:{ SQLiteException -> 0x009f }
        com.google.android.gms.common.internal.zzbq.checkNotNull(r11);	 Catch:{ SQLiteException -> 0x009f }
        r4.zzxf();	 Catch:{ SQLiteException -> 0x009f }
        com.google.android.gms.analytics.zzj.zzve();	 Catch:{ SQLiteException -> 0x009f }
        r5 = r4.getWritableDatabase();	 Catch:{ SQLiteException -> 0x009f }
        r0 = r11.zzjh();	 Catch:{ SQLiteException -> 0x009f }
        com.google.android.gms.common.internal.zzbq.checkNotNull(r0);	 Catch:{ SQLiteException -> 0x009f }
        r6 = new android.net.Uri$Builder;	 Catch:{ SQLiteException -> 0x009f }
        r6.<init>();	 Catch:{ SQLiteException -> 0x009f }
        r0 = r0.entrySet();	 Catch:{ SQLiteException -> 0x009f }
        r7 = r0.iterator();	 Catch:{ SQLiteException -> 0x009f }
    L_0x0083:
        r0 = r7.hasNext();	 Catch:{ SQLiteException -> 0x009f }
        if (r0 == 0) goto L_0x00ae;
    L_0x0089:
        r0 = r7.next();	 Catch:{ SQLiteException -> 0x009f }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ SQLiteException -> 0x009f }
        r1 = r0.getKey();	 Catch:{ SQLiteException -> 0x009f }
        r1 = (java.lang.String) r1;	 Catch:{ SQLiteException -> 0x009f }
        r0 = r0.getValue();	 Catch:{ SQLiteException -> 0x009f }
        r0 = (java.lang.String) r0;	 Catch:{ SQLiteException -> 0x009f }
        r6.appendQueryParameter(r1, r0);	 Catch:{ SQLiteException -> 0x009f }
        goto L_0x0083;
    L_0x009f:
        r0 = move-exception;
        r1 = "Failed to update Analytics property";
        r10.zze(r1, r0);	 Catch:{ all -> 0x0135 }
        r0 = r10.zzdum;	 Catch:{ SQLiteException -> 0x0144 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0144 }
    L_0x00ab:
        r0 = -1;
    L_0x00ad:
        return r0;
    L_0x00ae:
        r0 = r6.build();	 Catch:{ SQLiteException -> 0x009f }
        r0 = r0.getEncodedQuery();	 Catch:{ SQLiteException -> 0x009f }
        if (r0 != 0) goto L_0x0129;
    L_0x00b8:
        r0 = "";
        r1 = r0;
    L_0x00bc:
        r6 = new android.content.ContentValues;	 Catch:{ SQLiteException -> 0x009f }
        r6.<init>();	 Catch:{ SQLiteException -> 0x009f }
        r0 = "app_uid";
        r8 = r11.zzxm();	 Catch:{ SQLiteException -> 0x009f }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x009f }
        r6.put(r0, r7);	 Catch:{ SQLiteException -> 0x009f }
        r0 = "cid";
        r7 = r11.zzvz();	 Catch:{ SQLiteException -> 0x009f }
        r6.put(r0, r7);	 Catch:{ SQLiteException -> 0x009f }
        r0 = "tid";
        r7 = r11.zzxn();	 Catch:{ SQLiteException -> 0x009f }
        r6.put(r0, r7);	 Catch:{ SQLiteException -> 0x009f }
        r7 = "adid";
        r0 = r11.zzxo();	 Catch:{ SQLiteException -> 0x009f }
        if (r0 == 0) goto L_0x012b;
    L_0x00ec:
        r0 = 1;
    L_0x00ed:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ SQLiteException -> 0x009f }
        r6.put(r7, r0);	 Catch:{ SQLiteException -> 0x009f }
        r0 = "hits_count";
        r8 = r11.zzxp();	 Catch:{ SQLiteException -> 0x009f }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x009f }
        r6.put(r0, r7);	 Catch:{ SQLiteException -> 0x009f }
        r0 = "params";
        r6.put(r0, r1);	 Catch:{ SQLiteException -> 0x009f }
        r0 = "properties";
        r1 = 0;
        r7 = 5;
        r0 = r5.insertWithOnConflict(r0, r1, r6, r7);	 Catch:{ SQLiteException -> 0x012d }
        r6 = -1;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 != 0) goto L_0x011d;
    L_0x0117:
        r0 = "Failed to insert/update a property (got -1)";
        r4.zzdy(r0);	 Catch:{ SQLiteException -> 0x012d }
    L_0x011d:
        r0 = r10.zzdum;	 Catch:{ SQLiteException -> 0x009f }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x009f }
        r0 = r10.zzdum;	 Catch:{ SQLiteException -> 0x013c }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x013c }
    L_0x0127:
        r0 = r2;
        goto L_0x00ad;
    L_0x0129:
        r1 = r0;
        goto L_0x00bc;
    L_0x012b:
        r0 = 0;
        goto L_0x00ed;
    L_0x012d:
        r0 = move-exception;
        r1 = "Error storing a property";
        r4.zze(r1, r0);	 Catch:{ SQLiteException -> 0x009f }
        goto L_0x011d;
    L_0x0135:
        r0 = move-exception;
        r1 = r10.zzdum;	 Catch:{ SQLiteException -> 0x014d }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x014d }
    L_0x013b:
        throw r0;
    L_0x013c:
        r0 = move-exception;
        r1 = "Failed to end transaction";
        r10.zze(r1, r0);
        goto L_0x0127;
    L_0x0144:
        r0 = move-exception;
        r1 = "Failed to end transaction";
        r10.zze(r1, r0);
        goto L_0x00ab;
    L_0x014d:
        r1 = move-exception;
        r2 = "Failed to end transaction";
        r10.zze(r2, r1);
        goto L_0x013b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqo.zza(com.google.android.gms.internal.zzaqf, boolean):long");
    }

    public final void zza(zzarq com_google_android_gms_internal_zzarq) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        zzj.zzve();
        zzxf();
        if (this.zzduv) {
            zzdv("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", com_google_android_gms_internal_zzarq);
        }
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzarq.zzzm())) {
            Pair zzaad = zzxa().zzaab().zzaad();
            if (zzaad != null) {
                Long l = (Long) zzaad.second;
                String str = (String) zzaad.first;
                String valueOf = String.valueOf(l);
                valueOf = new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
                Map hashMap = new HashMap(com_google_android_gms_internal_zzarq.zzjh());
                hashMap.put("_m", valueOf);
                com_google_android_gms_internal_zzarq = new zzarq(this, hashMap, com_google_android_gms_internal_zzarq.zzzi(), com_google_android_gms_internal_zzarq.zzzk(), com_google_android_gms_internal_zzarq.zzzh(), com_google_android_gms_internal_zzarq.zzzg(), com_google_android_gms_internal_zzarq.zzzj());
            }
        }
        zzyc();
        if (this.zzdup.zzb(com_google_android_gms_internal_zzarq)) {
            zzdv("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzdum.zzc(com_google_android_gms_internal_zzarq);
            zzyf();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzwt().zza(com_google_android_gms_internal_zzarq, "deliver: failed to insert hit to database");
        }
    }

    protected final void zzb(zzaqf com_google_android_gms_internal_zzaqf) {
        zzj.zzve();
        zzb("Sending first hit to property", com_google_android_gms_internal_zzaqf.zzxn());
        if (!zzxa().zzzx().zzu(zzard.zzza())) {
            String zzaaa = zzxa().zzaaa();
            if (!TextUtils.isEmpty(zzaaa)) {
                zzape zza = zzasl.zza(zzwt(), zzaaa);
                zzb("Found relevant installation campaign", zza);
                zza(com_google_android_gms_internal_zzaqf, zza);
            }
        }
    }

    public final void zzb(zzarj com_google_android_gms_internal_zzarj) {
        long j = this.zzduu;
        zzj.zzve();
        zzxf();
        long j2 = -1;
        long zzzy = zzxa().zzzy();
        if (zzzy != 0) {
            j2 = Math.abs(zzws().currentTimeMillis() - zzzy);
        }
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(j2));
        zzyc();
        try {
            zzyd();
            zzxa().zzzz();
            zzyf();
            if (com_google_android_gms_internal_zzarj != null) {
                com_google_android_gms_internal_zzarj.zzd(null);
            }
            if (this.zzduu != j) {
                this.zzduo.zzzr();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zzxa().zzzz();
            zzyf();
            if (com_google_android_gms_internal_zzarj != null) {
                com_google_android_gms_internal_zzarj.zzd(th);
            }
        }
    }

    public final void zzec(String str) {
        zzbq.zzgm(str);
        zzj.zzve();
        zzape zza = zzasl.zza(zzwt(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        CharSequence zzaaa = zzxa().zzaaa();
        if (str.equals(zzaaa)) {
            zzdx("Ignoring duplicate install campaign");
        } else if (TextUtils.isEmpty(zzaaa)) {
            zzxa().zzef(str);
            if (zzxa().zzzx().zzu(zzard.zzza())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzaqf zza2 : this.zzdum.zzq(0)) {
                zza(zza2, zza);
            }
        } else {
            zzd("Ignoring multiple install campaigns. original, new", zzaaa, str);
        }
    }

    public final void zzr(long j) {
        zzj.zzve();
        zzxf();
        if (j < 0) {
            j = 0;
        }
        this.zzduq = j;
        zzyf();
    }

    protected final void zzvf() {
        this.zzdum.initialize();
        this.zzdun.initialize();
        this.zzdup.initialize();
    }

    public final void zzwm() {
        zzj.zzve();
        zzxf();
        zzdu("Delete all hits from local store");
        try {
            zzaqa com_google_android_gms_internal_zzaqa = this.zzdum;
            zzj.zzve();
            com_google_android_gms_internal_zzaqa.zzxf();
            com_google_android_gms_internal_zzaqa.getWritableDatabase().delete("hits2", null, null);
            com_google_android_gms_internal_zzaqa = this.zzdum;
            zzj.zzve();
            com_google_android_gms_internal_zzaqa.zzxf();
            com_google_android_gms_internal_zzaqa.getWritableDatabase().delete("properties", null, null);
            zzyf();
        } catch (SQLiteException e) {
            zzd("Failed to delete hits from store", e);
        }
        zzyc();
        if (this.zzdup.zzxq()) {
            zzdu("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    final void zzwq() {
        zzj.zzve();
        this.zzduu = zzws().currentTimeMillis();
    }

    protected final void zzxz() {
        zzxf();
        zzj.zzve();
        Context context = zzwr().getContext();
        if (!zzasc.zzbk(context)) {
            zzdx("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzasd.zzbo(context)) {
            zzdy("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzbk(context)) {
            zzdx("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzxa().zzzw();
        if (!zzeb("android.permission.ACCESS_NETWORK_STATE")) {
            zzdy("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzyj();
        }
        if (!zzeb("android.permission.INTERNET")) {
            zzdy("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzyj();
        }
        if (zzasd.zzbo(getContext())) {
            zzdu("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzdx("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!(this.zzduv || this.zzdum.isEmpty())) {
            zzyc();
        }
        zzyf();
    }

    public final void zzye() {
        zzj.zzve();
        zzxf();
        zzdv("Sync dispatching local hits");
        long j = this.zzduu;
        zzyc();
        try {
            zzyd();
            zzxa().zzzz();
            zzyf();
            if (this.zzduu != j) {
                this.zzduo.zzzr();
            }
        } catch (Throwable th) {
            zze("Sync local dispatch failed", th);
            zzyf();
        }
    }

    public final void zzyf() {
        zzj.zzve();
        zzxf();
        Object obj = (this.zzduv || zzyi() <= 0) ? null : 1;
        if (obj == null) {
            this.zzduo.unregister();
            zzyh();
        } else if (this.zzdum.isEmpty()) {
            this.zzduo.unregister();
            zzyh();
        } else {
            boolean z;
            if (((Boolean) zzarl.zzdxf.get()).booleanValue()) {
                z = true;
            } else {
                this.zzduo.zzzp();
                z = this.zzduo.isConnected();
            }
            if (z) {
                zzyg();
                long zzyi = zzyi();
                long zzzy = zzxa().zzzy();
                if (zzzy != 0) {
                    zzzy = zzyi - Math.abs(zzws().currentTimeMillis() - zzzy);
                    if (zzzy <= 0) {
                        zzzy = Math.min(zzard.zzys(), zzyi);
                    }
                } else {
                    zzzy = Math.min(zzard.zzys(), zzyi);
                }
                zza("Dispatch scheduled (ms)", Long.valueOf(zzzy));
                if (this.zzdur.zzdx()) {
                    this.zzdur.zzt(Math.max(1, zzzy + this.zzdur.zzzb()));
                    return;
                } else {
                    this.zzdur.zzs(zzzy);
                    return;
                }
            }
            zzyh();
            zzyg();
        }
    }
}
