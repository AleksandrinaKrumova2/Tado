package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    private static String[] zzkek = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzkel = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzkem;
    private final Map<String, Object> zzken;
    private final ReentrantLock zzkeo;
    private final LinkedList<Map<String, Object>> zzkep;
    private final zzc zzkeq;
    private final CountDownLatch zzker;

    static final class zza {
        public final Object mValue;
        public final String zzbhb;

        zza(String str, Object obj) {
            this.zzbhb = str;
            this.mValue = obj;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_tagmanager_DataLayer_zza = (zza) obj;
            return this.zzbhb.equals(com_google_android_gms_tagmanager_DataLayer_zza.zzbhb) && this.mValue.equals(com_google_android_gms_tagmanager_DataLayer_zza.mValue);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzbhb.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public final String toString() {
            String str = this.zzbhb;
            String obj = this.mValue.toString();
            return new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(obj).length()).append("Key: ").append(str).append(" value: ").append(obj).toString();
        }
    }

    interface zzb {
        void zzw(Map<String, Object> map);
    }

    interface zzc {
        void zza(zzaq com_google_android_gms_tagmanager_zzaq);

        void zza(List<zza> list, long j);

        void zzli(String str);
    }

    DataLayer() {
        this(new zzao());
    }

    DataLayer(zzc com_google_android_gms_tagmanager_DataLayer_zzc) {
        this.zzkeq = com_google_android_gms_tagmanager_DataLayer_zzc;
        this.zzkem = new ConcurrentHashMap();
        this.zzken = new HashMap();
        this.zzkeo = new ReentrantLock();
        this.zzkep = new LinkedList();
        this.zzker = new CountDownLatch(1);
        this.zzkeq.zza(new zzap(this));
    }

    public static List<Object> listOf(Object... objArr) {
        List<Object> arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        Map<String, Object> hashMap = new HashMap();
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] instanceof String) {
                hashMap.put((String) objArr[i], objArr[i + 1]);
                i += 2;
            } else {
                String valueOf = String.valueOf(objArr[i]);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 21).append("key is not a string: ").append(valueOf).toString());
            }
        }
        return hashMap;
    }

    private final void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Entry entry : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String str3 = (String) entry.getKey();
            str3 = new StringBuilder((String.valueOf(str).length() + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(str2).append(str3).toString();
            if (entry.getValue() instanceof Map) {
                zza((Map) entry.getValue(), str3, collection);
            } else if (!str3.equals("gtm.lifetime")) {
                collection.add(new zza(str3, entry.getValue()));
            }
        }
    }

    private final void zzb(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                zzb((List) obj, (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                zzd((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }

    private final void zzd(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                zzb((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                zzd((Map) obj, (Map) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    private static Long zzlh(String str) {
        Matcher matcher = zzkel.matcher(str);
        String str2;
        String valueOf;
        if (matcher.matches()) {
            long parseLong;
            try {
                parseLong = Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                str2 = "illegal number in _lifetime value: ";
                valueOf = String.valueOf(str);
                zzdj.zzcu(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                parseLong = 0;
            }
            if (parseLong <= 0) {
                str2 = "non-positive _lifetime: ";
                valueOf = String.valueOf(str);
                zzdj.zzct(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            }
            valueOf = matcher.group(2);
            if (valueOf.length() == 0) {
                return Long.valueOf(parseLong);
            }
            switch (valueOf.charAt(0)) {
                case 'd':
                    return Long.valueOf((((parseLong * 1000) * 60) * 60) * 24);
                case 'h':
                    return Long.valueOf(((parseLong * 1000) * 60) * 60);
                case 'm':
                    return Long.valueOf((parseLong * 1000) * 60);
                case 's':
                    return Long.valueOf(parseLong * 1000);
                default:
                    str2 = "unknown units in _lifetime: ";
                    valueOf = String.valueOf(str);
                    zzdj.zzcu(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
            }
        }
        str2 = "unknown _lifetime: ";
        valueOf = String.valueOf(str);
        zzdj.zzct(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        return null;
    }

    static Map<String, Object> zzn(String str, Object obj) {
        Map hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        Map map = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap2 = new HashMap();
            map.put(split[i], hashMap2);
            i++;
            Object obj2 = hashMap2;
        }
        map.put(split[split.length - 1], obj);
        return hashMap;
    }

    private final void zzy(Map<String, Object> map) {
        this.zzkeo.lock();
        try {
            this.zzkep.offer(map);
            if (this.zzkeo.getHoldCount() == 1) {
                int i = 0;
                while (true) {
                    Map map2 = (Map) this.zzkep.poll();
                    if (map2 == null) {
                        break;
                    }
                    synchronized (this.zzken) {
                        for (String str : map2.keySet()) {
                            zzd(zzn(str, map2.get(str)), this.zzken);
                        }
                    }
                    for (zzb zzw : this.zzkem.keySet()) {
                        zzw.zzw(map2);
                    }
                    int i2 = i + 1;
                    if (i2 > 500) {
                        this.zzkep.clear();
                        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                    }
                    i = i2;
                }
            }
            Object zzz = zzz(map);
            Long zzlh = zzz == null ? null : zzlh(zzz.toString());
            if (zzlh != null) {
                Object arrayList = new ArrayList();
                zza(map, "", arrayList);
                this.zzkeq.zza(arrayList, zzlh.longValue());
            }
            this.zzkeo.unlock();
        } catch (Throwable th) {
            this.zzkeo.unlock();
        }
    }

    private static Object zzz(Map<String, Object> map) {
        String[] strArr = zzkek;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            Object obj2 = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(obj2);
        }
        return obj;
    }

    public Object get(String str) {
        synchronized (this.zzken) {
            Map map = this.zzken;
            String[] split = str.split("\\.");
            int length = split.length;
            Object obj = map;
            int i = 0;
            while (i < length) {
                Object obj2 = split[i];
                if (obj instanceof Map) {
                    obj2 = ((Map) obj).get(obj2);
                    if (obj2 == null) {
                        return null;
                    }
                    i++;
                    obj = obj2;
                } else {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(String str, Object obj) {
        push(zzn(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzker.await();
        } catch (InterruptedException e) {
            zzdj.zzcu("DataLayer.push: unexpected InterruptedException");
        }
        zzy(map);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        Map hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public String toString() {
        String stringBuilder;
        synchronized (this.zzken) {
            StringBuilder stringBuilder2 = new StringBuilder();
            for (Entry entry : this.zzken.entrySet()) {
                stringBuilder2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{entry.getKey(), entry.getValue()}));
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    final void zza(zzb com_google_android_gms_tagmanager_DataLayer_zzb) {
        this.zzkem.put(com_google_android_gms_tagmanager_DataLayer_zzb, Integer.valueOf(0));
    }

    final void zzlg(String str) {
        push(str, null);
        this.zzkeq.zzli(str);
    }
}
