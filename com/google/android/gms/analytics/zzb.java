package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzapd;
import com.google.android.gms.internal.zzape;
import com.google.android.gms.internal.zzapf;
import com.google.android.gms.internal.zzapg;
import com.google.android.gms.internal.zzaph;
import com.google.android.gms.internal.zzapi;
import com.google.android.gms.internal.zzapj;
import com.google.android.gms.internal.zzapk;
import com.google.android.gms.internal.zzapl;
import com.google.android.gms.internal.zzapm;
import com.google.android.gms.internal.zzapn;
import com.google.android.gms.internal.zzapo;
import com.google.android.gms.internal.zzapp;
import com.google.android.gms.internal.zzapz;
import com.google.android.gms.internal.zzaqb;
import com.google.android.gms.internal.zzaqc;
import com.google.android.gms.internal.zzaqf;
import com.google.android.gms.internal.zzarq;
import com.google.android.gms.internal.zzasl;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzb extends zzapz implements zzm {
    private static DecimalFormat zzdol;
    private final zzaqc zzdoh;
    private final String zzdom;
    private final Uri zzdon;

    public zzb(zzaqc com_google_android_gms_internal_zzaqc, String str) {
        this(com_google_android_gms_internal_zzaqc, str, true, false);
    }

    private zzb(zzaqc com_google_android_gms_internal_zzaqc, String str, boolean z, boolean z2) {
        super(com_google_android_gms_internal_zzaqc);
        zzbq.zzgm(str);
        this.zzdoh = com_google_android_gms_internal_zzaqc;
        this.zzdom = str;
        this.zzdon = zzdf(this.zzdom);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zzb(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            map.put(str, i + "x" + i2);
        }
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    private static String zzb(double d) {
        if (zzdol == null) {
            zzdol = new DecimalFormat("0.######");
        }
        return zzdol.format(d);
    }

    private static Map<String, String> zzc(zzg com_google_android_gms_analytics_zzg) {
        String str;
        CharSequence zzaj;
        Map hashMap = new HashMap();
        zzaph com_google_android_gms_internal_zzaph = (zzaph) com_google_android_gms_analytics_zzg.zza(zzaph.class);
        if (com_google_android_gms_internal_zzaph != null) {
            for (Entry entry : com_google_android_gms_internal_zzaph.zzvt().entrySet()) {
                Object obj;
                Boolean value = entry.getValue();
                if (value == null) {
                    obj = null;
                } else if (value instanceof String) {
                    str = (String) value;
                    if (TextUtils.isEmpty(str)) {
                        obj = null;
                    }
                } else if (value instanceof Double) {
                    Double d = (Double) value;
                    obj = d.doubleValue() != 0.0d ? zzb(d.doubleValue()) : null;
                } else {
                    obj = value instanceof Boolean ? value != Boolean.FALSE ? "1" : null : String.valueOf(value);
                }
                if (obj != null) {
                    hashMap.put((String) entry.getKey(), obj);
                }
            }
        }
        zzapm com_google_android_gms_internal_zzapm = (zzapm) com_google_android_gms_analytics_zzg.zza(zzapm.class);
        if (com_google_android_gms_internal_zzapm != null) {
            zza(hashMap, "t", com_google_android_gms_internal_zzapm.zzvy());
            zza(hashMap, "cid", com_google_android_gms_internal_zzapm.zzvz());
            zza(hashMap, "uid", com_google_android_gms_internal_zzapm.getUserId());
            zza(hashMap, "sc", com_google_android_gms_internal_zzapm.zzwc());
            zza(hashMap, "sf", com_google_android_gms_internal_zzapm.zzwe());
            zza(hashMap, "ni", com_google_android_gms_internal_zzapm.zzwd());
            zza(hashMap, "adid", com_google_android_gms_internal_zzapm.zzwa());
            zza(hashMap, "ate", com_google_android_gms_internal_zzapm.zzwb());
        }
        zzapn com_google_android_gms_internal_zzapn = (zzapn) com_google_android_gms_analytics_zzg.zza(zzapn.class);
        if (com_google_android_gms_internal_zzapn != null) {
            zza(hashMap, "cd", com_google_android_gms_internal_zzapn.zzwf());
            zza(hashMap, "a", (double) com_google_android_gms_internal_zzapn.zzwg());
            zza(hashMap, "dr", com_google_android_gms_internal_zzapn.zzwh());
        }
        zzapk com_google_android_gms_internal_zzapk = (zzapk) com_google_android_gms_analytics_zzg.zza(zzapk.class);
        if (com_google_android_gms_internal_zzapk != null) {
            zza(hashMap, "ec", com_google_android_gms_internal_zzapk.getCategory());
            zza(hashMap, "ea", com_google_android_gms_internal_zzapk.getAction());
            zza(hashMap, "el", com_google_android_gms_internal_zzapk.getLabel());
            zza(hashMap, "ev", (double) com_google_android_gms_internal_zzapk.getValue());
        }
        zzape com_google_android_gms_internal_zzape = (zzape) com_google_android_gms_analytics_zzg.zza(zzape.class);
        if (com_google_android_gms_internal_zzape != null) {
            zza(hashMap, "cn", com_google_android_gms_internal_zzape.getName());
            zza(hashMap, "cs", com_google_android_gms_internal_zzape.getSource());
            zza(hashMap, "cm", com_google_android_gms_internal_zzape.zzvl());
            zza(hashMap, "ck", com_google_android_gms_internal_zzape.zzvm());
            zza(hashMap, "cc", com_google_android_gms_internal_zzape.getContent());
            zza(hashMap, "ci", com_google_android_gms_internal_zzape.getId());
            zza(hashMap, "anid", com_google_android_gms_internal_zzape.zzvn());
            zza(hashMap, "gclid", com_google_android_gms_internal_zzape.zzvo());
            zza(hashMap, "dclid", com_google_android_gms_internal_zzape.zzvp());
            zza(hashMap, Param.ACLID, com_google_android_gms_internal_zzape.zzvq());
        }
        zzapl com_google_android_gms_internal_zzapl = (zzapl) com_google_android_gms_analytics_zzg.zza(zzapl.class);
        if (com_google_android_gms_internal_zzapl != null) {
            zza(hashMap, "exd", com_google_android_gms_internal_zzapl.zzdrs);
            zza(hashMap, "exf", com_google_android_gms_internal_zzapl.zzdrt);
        }
        zzapo com_google_android_gms_internal_zzapo = (zzapo) com_google_android_gms_analytics_zzg.zza(zzapo.class);
        if (com_google_android_gms_internal_zzapo != null) {
            zza(hashMap, "sn", com_google_android_gms_internal_zzapo.zzdsh);
            zza(hashMap, "sa", com_google_android_gms_internal_zzapo.zzdrp);
            zza(hashMap, "st", com_google_android_gms_internal_zzapo.zzdsi);
        }
        zzapp com_google_android_gms_internal_zzapp = (zzapp) com_google_android_gms_analytics_zzg.zza(zzapp.class);
        if (com_google_android_gms_internal_zzapp != null) {
            zza(hashMap, "utv", com_google_android_gms_internal_zzapp.zzdsj);
            zza(hashMap, "utt", (double) com_google_android_gms_internal_zzapp.zzdsk);
            zza(hashMap, "utc", com_google_android_gms_internal_zzapp.mCategory);
            zza(hashMap, "utl", com_google_android_gms_internal_zzapp.zzdrq);
        }
        zzapf com_google_android_gms_internal_zzapf = (zzapf) com_google_android_gms_analytics_zzg.zza(zzapf.class);
        if (com_google_android_gms_internal_zzapf != null) {
            for (Entry entry2 : com_google_android_gms_internal_zzapf.zzvr().entrySet()) {
                zzaj = zzd.zzaj(((Integer) entry2.getKey()).intValue());
                if (!TextUtils.isEmpty(zzaj)) {
                    hashMap.put(zzaj, (String) entry2.getValue());
                }
            }
        }
        zzapg com_google_android_gms_internal_zzapg = (zzapg) com_google_android_gms_analytics_zzg.zza(zzapg.class);
        if (com_google_android_gms_internal_zzapg != null) {
            for (Entry entry22 : com_google_android_gms_internal_zzapg.zzvs().entrySet()) {
                zzaj = zzd.zzal(((Integer) entry22.getKey()).intValue());
                if (!TextUtils.isEmpty(zzaj)) {
                    hashMap.put(zzaj, zzb(((Double) entry22.getValue()).doubleValue()));
                }
            }
        }
        zzapj com_google_android_gms_internal_zzapj = (zzapj) com_google_android_gms_analytics_zzg.zza(zzapj.class);
        if (com_google_android_gms_internal_zzapj != null) {
            ProductAction zzvu = com_google_android_gms_internal_zzapj.zzvu();
            if (zzvu != null) {
                for (Entry entry3 : zzvu.build().entrySet()) {
                    if (((String) entry3.getKey()).startsWith("&")) {
                        hashMap.put(((String) entry3.getKey()).substring(1), (String) entry3.getValue());
                    } else {
                        hashMap.put((String) entry3.getKey(), (String) entry3.getValue());
                    }
                }
            }
            int i = 1;
            for (Promotion zzdr : com_google_android_gms_internal_zzapj.zzvx()) {
                hashMap.putAll(zzdr.zzdr(zzd.zzap(i)));
                i++;
            }
            i = 1;
            for (Product zzdr2 : com_google_android_gms_internal_zzapj.zzvv()) {
                hashMap.putAll(zzdr2.zzdr(zzd.zzan(i)));
                i++;
            }
            i = 1;
            for (Entry entry222 : com_google_android_gms_internal_zzapj.zzvw().entrySet()) {
                List<Product> list = (List) entry222.getValue();
                String zzas = zzd.zzas(i);
                int i2 = 1;
                for (Product zzdr22 : list) {
                    String valueOf = String.valueOf(zzas);
                    String valueOf2 = String.valueOf(zzd.zzaq(i2));
                    hashMap.putAll(zzdr22.zzdr(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i2++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry222.getKey())) {
                    String valueOf3 = String.valueOf(zzas);
                    str = String.valueOf("nm");
                    hashMap.put(str.length() != 0 ? valueOf3.concat(str) : new String(valueOf3), (String) entry222.getKey());
                }
                i++;
            }
        }
        zzapi com_google_android_gms_internal_zzapi = (zzapi) com_google_android_gms_analytics_zzg.zza(zzapi.class);
        if (com_google_android_gms_internal_zzapi != null) {
            zza(hashMap, "ul", com_google_android_gms_internal_zzapi.getLanguage());
            zza(hashMap, "sd", (double) com_google_android_gms_internal_zzapi.zzdrm);
            zza(hashMap, "sr", com_google_android_gms_internal_zzapi.zzchl, com_google_android_gms_internal_zzapi.zzchm);
            zza(hashMap, "vp", com_google_android_gms_internal_zzapi.zzdrn, com_google_android_gms_internal_zzapi.zzdro);
        }
        zzapd com_google_android_gms_internal_zzapd = (zzapd) com_google_android_gms_analytics_zzg.zza(zzapd.class);
        if (com_google_android_gms_internal_zzapd != null) {
            zza(hashMap, "an", com_google_android_gms_internal_zzapd.zzvi());
            zza(hashMap, "aid", com_google_android_gms_internal_zzapd.getAppId());
            zza(hashMap, "aiid", com_google_android_gms_internal_zzapd.zzvk());
            zza(hashMap, "av", com_google_android_gms_internal_zzapd.zzvj());
        }
        return hashMap;
    }

    static Uri zzdf(String str) {
        zzbq.zzgm(str);
        Builder builder = new Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public final void zzb(zzg com_google_android_gms_analytics_zzg) {
        zzbq.checkNotNull(com_google_android_gms_analytics_zzg);
        zzbq.checkArgument(com_google_android_gms_analytics_zzg.zzuw(), "Can't deliver not submitted measurement");
        zzbq.zzgn("deliver should be called on worker thread");
        zzg zzus = com_google_android_gms_analytics_zzg.zzus();
        zzapm com_google_android_gms_internal_zzapm = (zzapm) zzus.zzb(zzapm.class);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzapm.zzvy())) {
            zzwt().zzf(zzc(zzus), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(com_google_android_gms_internal_zzapm.zzvz())) {
            zzwt().zzf(zzc(zzus), "Ignoring measurement without client id");
        } else if (!this.zzdoh.zzxi().getAppOptOut()) {
            double zzwe = com_google_android_gms_internal_zzapm.zzwe();
            if (zzasl.zza(zzwe, com_google_android_gms_internal_zzapm.zzvz())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzwe));
                return;
            }
            Map zzc = zzc(zzus);
            zzc.put("v", "1");
            zzc.put("_v", zzaqb.zzdtc);
            zzc.put("tid", this.zzdom);
            if (this.zzdoh.zzxi().isDryRunEnabled()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Entry entry : zzc.entrySet()) {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append((String) entry.getKey());
                    stringBuilder.append("=");
                    stringBuilder.append((String) entry.getValue());
                }
                zzc("Dry run is enabled. GoogleAnalytics would have sent", stringBuilder.toString());
                return;
            }
            Map hashMap = new HashMap();
            zzasl.zzb(hashMap, "uid", com_google_android_gms_internal_zzapm.getUserId());
            zzapd com_google_android_gms_internal_zzapd = (zzapd) com_google_android_gms_analytics_zzg.zza(zzapd.class);
            if (com_google_android_gms_internal_zzapd != null) {
                zzasl.zzb(hashMap, "an", com_google_android_gms_internal_zzapd.zzvi());
                zzasl.zzb(hashMap, "aid", com_google_android_gms_internal_zzapd.getAppId());
                zzasl.zzb(hashMap, "av", com_google_android_gms_internal_zzapd.zzvj());
                zzasl.zzb(hashMap, "aiid", com_google_android_gms_internal_zzapd.zzvk());
            }
            zzc.put("_s", String.valueOf(zzwx().zza(new zzaqf(0, com_google_android_gms_internal_zzapm.zzvz(), this.zzdom, !TextUtils.isEmpty(com_google_android_gms_internal_zzapm.zzwa()), 0, hashMap))));
            zzwx().zza(new zzarq(zzwt(), zzc, com_google_android_gms_analytics_zzg.zzuu(), true));
        }
    }

    public final Uri zzup() {
        return this.zzdon;
    }
}
