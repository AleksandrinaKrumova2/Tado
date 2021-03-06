package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class zzai implements zzm {
    private static boolean DEBUG = zzae.DEBUG;
    @Deprecated
    private zzaq zzbp;
    private final zzah zzbq;
    private zzaj zzbr;

    public zzai(zzah com_google_android_gms_internal_zzah) {
        this(com_google_android_gms_internal_zzah, new zzaj(4096));
    }

    private zzai(zzah com_google_android_gms_internal_zzah, zzaj com_google_android_gms_internal_zzaj) {
        this.zzbq = com_google_android_gms_internal_zzah;
        this.zzbp = com_google_android_gms_internal_zzah;
        this.zzbr = com_google_android_gms_internal_zzaj;
    }

    @Deprecated
    public zzai(zzaq com_google_android_gms_internal_zzaq) {
        this(com_google_android_gms_internal_zzaq, new zzaj(4096));
    }

    @Deprecated
    private zzai(zzaq com_google_android_gms_internal_zzaq, zzaj com_google_android_gms_internal_zzaj) {
        this.zzbp = com_google_android_gms_internal_zzaq;
        this.zzbq = new zzag(com_google_android_gms_internal_zzaq);
        this.zzbr = com_google_android_gms_internal_zzaj;
    }

    private static List<zzl> zza(List<zzl> list, zzc com_google_android_gms_internal_zzc) {
        Set treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            for (zzl name : list) {
                treeSet.add(name.getName());
            }
        }
        List<zzl> arrayList = new ArrayList(list);
        if (com_google_android_gms_internal_zzc.zzg != null) {
            if (!com_google_android_gms_internal_zzc.zzg.isEmpty()) {
                for (zzl name2 : com_google_android_gms_internal_zzc.zzg) {
                    if (!treeSet.contains(name2.getName())) {
                        arrayList.add(name2);
                    }
                }
            }
        } else if (!com_google_android_gms_internal_zzc.zzf.isEmpty()) {
            for (Entry entry : com_google_android_gms_internal_zzc.zzf.entrySet()) {
                if (!treeSet.contains(entry.getKey())) {
                    arrayList.add(new zzl((String) entry.getKey(), (String) entry.getValue()));
                }
            }
        }
        return arrayList;
    }

    private static void zza(String str, zzr<?> com_google_android_gms_internal_zzr_, zzad com_google_android_gms_internal_zzad) throws zzad {
        zzaa zzi = com_google_android_gms_internal_zzr_.zzi();
        int zzh = com_google_android_gms_internal_zzr_.zzh();
        try {
            zzi.zza(com_google_android_gms_internal_zzad);
            com_google_android_gms_internal_zzr_.zzb(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzh)}));
        } catch (zzad e) {
            com_google_android_gms_internal_zzr_.zzb(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzh)}));
            throw e;
        }
    }

    private final byte[] zza(InputStream inputStream, int i) throws IOException, zzab {
        zzat com_google_android_gms_internal_zzat = new zzat(this.zzbr, i);
        if (inputStream == null) {
            try {
                throw new zzab();
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        zzae.zza("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.zzbr.zza(null);
                com_google_android_gms_internal_zzat.close();
            }
        } else {
            byte[] zzb = this.zzbr.zzb(1024);
            while (true) {
                int read = inputStream.read(zzb);
                if (read == -1) {
                    break;
                }
                com_google_android_gms_internal_zzat.write(zzb, 0, read);
            }
            byte[] toByteArray = com_google_android_gms_internal_zzat.toByteArray();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zzae.zza("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.zzbr.zza(zzb);
            com_google_android_gms_internal_zzat.close();
            return toByteArray;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzp zzc(com.google.android.gms.internal.zzr<?> r21) throws com.google.android.gms.internal.zzad {
        /*
        r20 = this;
        r18 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r9 = 0;
        r8 = java.util.Collections.emptyList();
        r4 = r21.zze();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        if (r4 != 0) goto L_0x0040;
    L_0x0010:
        r2 = java.util.Collections.emptyMap();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
    L_0x0014:
        r0 = r20;
        r4 = r0.zzbq;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r0 = r21;
        r17 = r4.zza(r0, r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r3 = r17.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r8 = r17.zzp();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r3 != r2) goto L_0x008b;
    L_0x002a:
        r2 = r21.zze();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        if (r2 != 0) goto L_0x0075;
    L_0x0030:
        r2 = new com.google.android.gms.internal.zzp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r3 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r4 = 0;
        r5 = 1;
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r6 = r6 - r18;
        r2.<init>(r3, r4, r5, r6, r8);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
    L_0x003f:
        return r2;
    L_0x0040:
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r5 = r4.zza;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        if (r5 == 0) goto L_0x0051;
    L_0x0049:
        r5 = "If-None-Match";
        r6 = r4.zza;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r2.put(r5, r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
    L_0x0051:
        r6 = r4.zzc;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r10 = 0;
        r5 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r5 <= 0) goto L_0x0014;
    L_0x0059:
        r5 = "If-Modified-Since";
        r6 = r4.zzc;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r4 = com.google.android.gms.internal.zzao.zzb(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        r2.put(r5, r4);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a1 }
        goto L_0x0014;
    L_0x0066:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.google.android.gms.internal.zzac;
        r3.<init>();
        r0 = r21;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x0075:
        r16 = zza(r8, r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r10 = new com.google.android.gms.internal.zzp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r11 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r12 = r2.data;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r13 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r14 = r2 - r18;
        r10.<init>(r11, r12, r13, r14, r16);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r2 = r10;
        goto L_0x003f;
    L_0x008b:
        r2 = r17.getContent();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        if (r2 == 0) goto L_0x0109;
    L_0x0091:
        r4 = r17.getContentLength();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        r0 = r20;
        r4 = r0.zza(r2, r4);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
    L_0x009b:
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r6 = r6 - r18;
        r2 = DEBUG;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        if (r2 != 0) goto L_0x00ab;
    L_0x00a5:
        r10 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r2 <= 0) goto L_0x00de;
    L_0x00ab:
        r5 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
        r2 = 5;
        r9 = new java.lang.Object[r2];	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r2 = 0;
        r9[r2] = r21;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r2 = 1;
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r9[r2] = r6;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r6 = 2;
        if (r4 == 0) goto L_0x010d;
    L_0x00be:
        r2 = r4.length;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
    L_0x00c3:
        r9[r6] = r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r2 = 3;
        r6 = java.lang.Integer.valueOf(r3);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r9[r2] = r6;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r2 = 4;
        r6 = r21.zzi();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r6 = r6.zzc();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r9[r2] = r6;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        com.google.android.gms.internal.zzae.zzb(r5, r9);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
    L_0x00de:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 < r2) goto L_0x00e6;
    L_0x00e2:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r3 <= r2) goto L_0x0111;
    L_0x00e6:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
    L_0x00ec:
        r2 = move-exception;
        r3 = r2;
        r4 = new java.lang.RuntimeException;
        r5 = "Bad URL ";
        r2 = r21.getUrl();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x0164;
    L_0x0101:
        r2 = r5.concat(r2);
    L_0x0105:
        r4.<init>(r2, r3);
        throw r4;
    L_0x0109:
        r2 = 0;
        r4 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x01a5 }
        goto L_0x009b;
    L_0x010d:
        r2 = "null";
        goto L_0x00c3;
    L_0x0111:
        r2 = new com.google.android.gms.internal.zzp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r5 = 0;
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        r6 = r6 - r18;
        r2.<init>(r3, r4, r5, r6, r8);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x00ec, IOException -> 0x011f }
        goto L_0x003f;
    L_0x011f:
        r2 = move-exception;
        r3 = r17;
    L_0x0122:
        if (r3 == 0) goto L_0x016a;
    L_0x0124:
        r3 = r3.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r5 = 2;
        r5 = new java.lang.Object[r5];
        r6 = 0;
        r7 = java.lang.Integer.valueOf(r3);
        r5[r6] = r7;
        r6 = 1;
        r7 = r21.getUrl();
        r5[r6] = r7;
        com.google.android.gms.internal.zzae.zzc(r2, r5);
        if (r4 == 0) goto L_0x0192;
    L_0x0141:
        r2 = new com.google.android.gms.internal.zzp;
        r5 = 0;
        r6 = android.os.SystemClock.elapsedRealtime();
        r6 = r6 - r18;
        r2.<init>(r3, r4, r5, r6, r8);
        r4 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r3 == r4) goto L_0x0155;
    L_0x0151:
        r4 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r3 != r4) goto L_0x0170;
    L_0x0155:
        r3 = "auth";
        r4 = new com.google.android.gms.internal.zza;
        r4.<init>(r2);
        r0 = r21;
        zza(r3, r0, r4);
        goto L_0x0004;
    L_0x0164:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x0105;
    L_0x016a:
        r3 = new com.google.android.gms.internal.zzq;
        r3.<init>(r2);
        throw r3;
    L_0x0170:
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r3 < r4) goto L_0x017e;
    L_0x0174:
        r4 = 499; // 0x1f3 float:6.99E-43 double:2.465E-321;
        if (r3 > r4) goto L_0x017e;
    L_0x0178:
        r3 = new com.google.android.gms.internal.zzg;
        r3.<init>(r2);
        throw r3;
    L_0x017e:
        r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r3 < r4) goto L_0x018c;
    L_0x0182:
        r4 = 599; // 0x257 float:8.4E-43 double:2.96E-321;
        if (r3 > r4) goto L_0x018c;
    L_0x0186:
        r3 = new com.google.android.gms.internal.zzab;
        r3.<init>(r2);
        throw r3;
    L_0x018c:
        r3 = new com.google.android.gms.internal.zzab;
        r3.<init>(r2);
        throw r3;
    L_0x0192:
        r2 = "network";
        r3 = new com.google.android.gms.internal.zzo;
        r3.<init>();
        r0 = r21;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x01a1:
        r2 = move-exception;
        r4 = r9;
        goto L_0x0122;
    L_0x01a5:
        r2 = move-exception;
        r4 = r9;
        r3 = r17;
        goto L_0x0122;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzai.zzc(com.google.android.gms.internal.zzr):com.google.android.gms.internal.zzp");
    }
}
