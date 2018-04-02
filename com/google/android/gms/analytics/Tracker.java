package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzaqc;
import com.google.android.gms.internal.zzart;
import com.google.android.gms.internal.zzask;
import com.google.android.gms.internal.zzasl;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Tracker extends zzaqa {
    private final Map<String, String> zzbsr = new HashMap();
    private boolean zzdqg;
    private final Map<String, String> zzdqh = new HashMap();
    private final zzart zzdqi;
    private final zza zzdqj;
    private ExceptionReporter zzdqk;
    private zzask zzdql;

    class zza extends zzaqa implements zza {
        private /* synthetic */ Tracker zzdqt;
        private boolean zzdqu;
        private int zzdqv;
        private long zzdqw = -1;
        private boolean zzdqx;
        private long zzdqy;

        protected zza(Tracker tracker, zzaqc com_google_android_gms_internal_zzaqc) {
            this.zzdqt = tracker;
            super(com_google_android_gms_internal_zzaqc);
        }

        private final void zzvh() {
            if (this.zzdqw >= 0 || this.zzdqu) {
                zzww().zza(this.zzdqt.zzdqj);
            } else {
                zzww().zzb(this.zzdqt.zzdqj);
            }
        }

        public final void enableAutoActivityTracking(boolean z) {
            this.zzdqu = z;
            zzvh();
        }

        public final void setSessionTimeout(long j) {
            this.zzdqw = j;
            zzvh();
        }

        public final void zzl(Activity activity) {
            if (this.zzdqv == 0) {
                if (zzws().elapsedRealtime() >= this.zzdqy + Math.max(1000, this.zzdqw)) {
                    this.zzdqx = true;
                }
            }
            this.zzdqv++;
            if (this.zzdqu) {
                String str;
                Intent intent = activity.getIntent();
                if (intent != null) {
                    this.zzdqt.setCampaignParamsOnNextHit(intent.getData());
                }
                Map hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker tracker = this.zzdqt;
                String str2 = "&cd";
                if (this.zzdqt.zzdql != null) {
                    zzask zzk = this.zzdqt.zzdql;
                    String canonicalName = activity.getClass().getCanonicalName();
                    str = (String) zzk.zzdzi.get(canonicalName);
                    if (str == null) {
                        str = canonicalName;
                    }
                } else {
                    str = activity.getClass().getCanonicalName();
                }
                tracker.set(str2, str);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    CharSequence charSequence;
                    zzbq.checkNotNull(activity);
                    intent = activity.getIntent();
                    if (intent == null) {
                        charSequence = null;
                    } else {
                        charSequence = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (TextUtils.isEmpty(charSequence)) {
                            charSequence = null;
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        hashMap.put("&dr", charSequence);
                    }
                }
                this.zzdqt.send(hashMap);
            }
        }

        public final void zzm(Activity activity) {
            this.zzdqv--;
            this.zzdqv = Math.max(0, this.zzdqv);
            if (this.zzdqv == 0) {
                this.zzdqy = zzws().elapsedRealtime();
            }
        }

        protected final void zzvf() {
        }

        public final synchronized boolean zzvg() {
            boolean z;
            z = this.zzdqx;
            this.zzdqx = false;
            return z;
        }
    }

    Tracker(zzaqc com_google_android_gms_internal_zzaqc, String str, zzart com_google_android_gms_internal_zzart) {
        super(com_google_android_gms_internal_zzaqc);
        if (str != null) {
            this.zzbsr.put("&tid", str);
        }
        this.zzbsr.put("useSecure", "1");
        this.zzbsr.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzdqi = new zzart("tracking", zzws());
        this.zzdqj = new zza(this, com_google_android_gms_internal_zzaqc);
    }

    private static String zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        int i = (!str.startsWith("&") || str.length() < 2) ? 0 : 1;
        return i == 0 ? null : ((String) entry.getKey()).substring(1);
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzbq.checkNotNull(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zza = zza(entry);
                if (zza != null) {
                    map2.put(zza, (String) entry.getValue());
                }
            }
        }
    }

    private static void zzc(Map<String, String> map, Map<String, String> map2) {
        zzbq.checkNotNull(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zza = zza(entry);
                if (!(zza == null || map2.containsKey(zza))) {
                    map2.put(zza, (String) entry.getValue());
                }
            }
        }
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzdqg = z;
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzdqj.enableAutoActivityTracking(z);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableExceptionReporting(boolean r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.zzdqk;	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = 1;
    L_0x0006:
        if (r0 != r4) goto L_0x000c;
    L_0x0008:
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
    L_0x0009:
        return;
    L_0x000a:
        r0 = 0;
        goto L_0x0006;
    L_0x000c:
        if (r4 == 0) goto L_0x002d;
    L_0x000e:
        r0 = r3.getContext();	 Catch:{ all -> 0x002a }
        r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler();	 Catch:{ all -> 0x002a }
        r2 = new com.google.android.gms.analytics.ExceptionReporter;	 Catch:{ all -> 0x002a }
        r2.<init>(r3, r1, r0);	 Catch:{ all -> 0x002a }
        r3.zzdqk = r2;	 Catch:{ all -> 0x002a }
        r0 = r3.zzdqk;	 Catch:{ all -> 0x002a }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ all -> 0x002a }
        r0 = "Uncaught exceptions will be reported to Google Analytics";
        r3.zzdu(r0);	 Catch:{ all -> 0x002a }
    L_0x0028:
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        goto L_0x0009;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        throw r0;
    L_0x002d:
        r0 = r3.zzdqk;	 Catch:{ all -> 0x002a }
        r0 = r0.zzuq();	 Catch:{ all -> 0x002a }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ all -> 0x002a }
        r0 = "Uncaught exceptions will not be reported to Google Analytics";
        r3.zzdu(r0);	 Catch:{ all -> 0x002a }
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public String get(String str) {
        zzxf();
        return TextUtils.isEmpty(str) ? null : this.zzbsr.containsKey(str) ? (String) this.zzbsr.get(str) : str.equals("&ul") ? zzasl.zza(Locale.getDefault()) : str.equals("&cid") ? zzxb().zzyk() : str.equals("&sr") ? zzxe().zzzd() : str.equals("&aid") ? zzxd().zzxy().getAppId() : str.equals("&an") ? zzxd().zzxy().zzvi() : str.equals("&av") ? zzxd().zzxy().zzvj() : str.equals("&aiid") ? zzxd().zzxy().zzvk() : null;
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzws().currentTimeMillis();
        if (zzww().getAppOptOut()) {
            zzdv("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzww().isDryRunEnabled();
        Map hashMap = new HashMap();
        zzb(this.zzbsr, hashMap);
        zzb(map, hashMap);
        boolean zzd = zzasl.zzd((String) this.zzbsr.get("useSecure"), true);
        zzc(this.zzdqh, hashMap);
        this.zzdqh.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzwt().zzf(hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzwt().zzf(hashMap, "Missing tracking id parameter");
            return;
        }
        boolean z = this.zzdqg;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzbsr.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzbsr.put("&a", Integer.toString(parseInt));
            }
        }
        zzwv().zzc(new zzn(this, hashMap, z, str, currentTimeMillis, isDryRunEnabled, zzd, str2));
    }

    public void set(String str, String str2) {
        zzbq.checkNotNull(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzbsr.put(str, str2);
        }
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzasl.zzak(z));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            CharSequence queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String str = "http://hostname/?";
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                str = parse.getQueryParameter("utm_id");
                if (str != null) {
                    this.zzdqh.put("&ci", str);
                }
                str = parse.getQueryParameter("anid");
                if (str != null) {
                    this.zzdqh.put("&anid", str);
                }
                str = parse.getQueryParameter("utm_campaign");
                if (str != null) {
                    this.zzdqh.put("&cn", str);
                }
                str = parse.getQueryParameter("utm_content");
                if (str != null) {
                    this.zzdqh.put("&cc", str);
                }
                str = parse.getQueryParameter("utm_medium");
                if (str != null) {
                    this.zzdqh.put("&cm", str);
                }
                str = parse.getQueryParameter("utm_source");
                if (str != null) {
                    this.zzdqh.put("&cs", str);
                }
                str = parse.getQueryParameter("utm_term");
                if (str != null) {
                    this.zzdqh.put("&ck", str);
                }
                str = parse.getQueryParameter("dclid");
                if (str != null) {
                    this.zzdqh.put("&dclid", str);
                }
                str = parse.getQueryParameter("gclid");
                if (str != null) {
                    this.zzdqh.put("&gclid", str);
                }
                valueOf = parse.getQueryParameter(Param.ACLID);
                if (valueOf != null) {
                    this.zzdqh.put("&aclid", valueOf);
                }
            }
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            set("&sr", i + "x" + i2);
        } else {
            zzdx("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long j) {
        this.zzdqj.setSessionTimeout(1000 * j);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzasl.zzak(z));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    final void zza(zzask com_google_android_gms_internal_zzask) {
        boolean z = true;
        zzdu("Loading Tracker config values");
        this.zzdql = com_google_android_gms_internal_zzask;
        if (this.zzdql.zzdom != null) {
            String str = this.zzdql.zzdom;
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        if (this.zzdql.zzdzd >= 0.0d) {
            str = Double.toString(this.zzdql.zzdzd);
            set("&sf", str);
            zza("Sample frequency loaded", str);
        }
        if (this.zzdql.zzdze >= 0) {
            int i = this.zzdql.zzdze;
            setSessionTimeout((long) i);
            zza("Session timeout loaded", Integer.valueOf(i));
        }
        if (this.zzdql.zzdzf != -1) {
            boolean z2 = this.zzdql.zzdzf == 1;
            enableAutoActivityTracking(z2);
            zza("Auto activity tracking loaded", Boolean.valueOf(z2));
        }
        if (this.zzdql.zzdzg != -1) {
            z2 = this.zzdql.zzdzg == 1;
            if (z2) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z2));
        }
        if (this.zzdql.zzdzh != 1) {
            z = false;
        }
        enableExceptionReporting(z);
    }

    protected final void zzvf() {
        this.zzdqj.initialize();
        String zzvi = zzwz().zzvi();
        if (zzvi != null) {
            set("&an", zzvi);
        }
        zzvi = zzwz().zzvj();
        if (zzvi != null) {
            set("&av", zzvi);
        }
    }
}
