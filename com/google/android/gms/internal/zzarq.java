package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzarq {
    private final Map<String, String> zzbsr;
    private final List<zzaqx> zzdxt;
    private final long zzdxu;
    private final long zzdxv;
    private final int zzdxw;
    private final boolean zzdxx;
    private final String zzdxy;

    public zzarq(zzapz com_google_android_gms_internal_zzapz, Map<String, String> map, long j, boolean z) {
        this(com_google_android_gms_internal_zzapz, map, j, z, 0, 0, null);
    }

    public zzarq(zzapz com_google_android_gms_internal_zzapz, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(com_google_android_gms_internal_zzapz, map, j, z, j2, i, null);
    }

    public zzarq(zzapz com_google_android_gms_internal_zzapz, Map<String, String> map, long j, boolean z, long j2, int i, List<zzaqx> list) {
        String zza;
        zzbq.checkNotNull(com_google_android_gms_internal_zzapz);
        zzbq.checkNotNull(map);
        this.zzdxv = j;
        this.zzdxx = z;
        this.zzdxu = j2;
        this.zzdxw = i;
        this.zzdxt = list != null ? list : Collections.emptyList();
        this.zzdxy = zzt(list);
        Map hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            if (zzn(entry.getKey())) {
                zza = zza(com_google_android_gms_internal_zzapz, entry.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(com_google_android_gms_internal_zzapz, entry.getValue()));
                }
            }
        }
        for (Entry entry2 : map.entrySet()) {
            if (!zzn(entry2.getKey())) {
                zza = zza(com_google_android_gms_internal_zzapz, entry2.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(com_google_android_gms_internal_zzapz, entry2.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(this.zzdxy)) {
            zzasl.zzb(hashMap, "_v", this.zzdxy);
            if (this.zzdxy.equals("ma4.0.0") || this.zzdxy.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzbsr = Collections.unmodifiableMap(hashMap);
    }

    private static String zza(zzapz com_google_android_gms_internal_zzapz, Object obj) {
        if (obj == null) {
            return null;
        }
        Object obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            com_google_android_gms_internal_zzapz.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        return TextUtils.isEmpty(obj2) ? null : obj2;
    }

    private static String zzb(zzapz com_google_android_gms_internal_zzapz, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        obj2 = obj2.substring(0, 8192);
        com_google_android_gms_internal_zzapz.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), obj2);
        return obj2;
    }

    private final String zzk(String str, String str2) {
        zzbq.zzgm(str);
        zzbq.checkArgument(!str.startsWith("&"), "Short param name required");
        String str3 = (String) this.zzbsr.get(str);
        return str3 != null ? str3 : str2;
    }

    private static boolean zzn(Object obj) {
        return obj == null ? false : obj.toString().startsWith("&");
    }

    private static String zzt(List<zzaqx> list) {
        CharSequence value;
        if (list != null) {
            for (zzaqx com_google_android_gms_internal_zzaqx : list) {
                if ("appendVersion".equals(com_google_android_gms_internal_zzaqx.getId())) {
                    value = com_google_android_gms_internal_zzaqx.getValue();
                    break;
                }
            }
        }
        value = null;
        return TextUtils.isEmpty(value) ? null : value;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ht=").append(this.zzdxv);
        if (this.zzdxu != 0) {
            stringBuffer.append(", dbId=").append(this.zzdxu);
        }
        if (this.zzdxw != 0) {
            stringBuffer.append(", appUID=").append(this.zzdxw);
        }
        List arrayList = new ArrayList(this.zzbsr.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String str = (String) obj;
            stringBuffer.append(", ");
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append((String) this.zzbsr.get(str));
        }
        return stringBuffer.toString();
    }

    public final Map<String, String> zzjh() {
        return this.zzbsr;
    }

    public final int zzzg() {
        return this.zzdxw;
    }

    public final long zzzh() {
        return this.zzdxu;
    }

    public final long zzzi() {
        return this.zzdxv;
    }

    public final List<zzaqx> zzzj() {
        return this.zzdxt;
    }

    public final boolean zzzk() {
        return this.zzdxx;
    }

    public final long zzzl() {
        return zzasl.zzei(zzk("_s", "0"));
    }

    public final String zzzm() {
        return zzk("_m", "");
    }
}
