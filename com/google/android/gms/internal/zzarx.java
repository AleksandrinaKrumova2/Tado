package com.google.android.gms.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import kotlin.text.Typography;

final class zzarx extends zzaqa {
    private static final byte[] zzdyj = "\n".getBytes();
    private final String zzczb;
    private final zzash zzdyi;

    zzarx(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
        String str = zzaqb.VERSION;
        String str2 = VERSION.RELEASE;
        String zza = zzasl.zza(Locale.getDefault());
        String str3 = Build.MODEL;
        String str4 = Build.ID;
        this.zzczb = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleAnalytics", str, str2, zza, str3, str4});
        this.zzdyi = new zzash(com_google_android_gms_internal_zzaqc.zzws());
    }

    private final int zza(java.net.URL r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0032 in list [B:7:0x002f]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        com.google.android.gms.common.internal.zzbq.checkNotNull(r5);
        r0 = "GET request";
        r4.zzb(r0, r5);
        r1 = 0;
        r1 = r4.zzb(r5);	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r1.connect();	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r4.zzb(r1);	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r0 = r1.getResponseCode();	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        if (r0 != r2) goto L_0x0023;	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
    L_0x001c:
        r2 = r4.zzwx();	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r2.zzwq();	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
    L_0x0023:
        r2 = "GET status";	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r3 = java.lang.Integer.valueOf(r0);	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r4.zzb(r2, r3);	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        if (r1 == 0) goto L_0x0032;
    L_0x002f:
        r1.disconnect();
    L_0x0032:
        return r0;
    L_0x0033:
        r0 = move-exception;
        r2 = "Network GET connection error";	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        r4.zzd(r2, r0);	 Catch:{ IOException -> 0x0033, all -> 0x0041 }
        if (r1 == 0) goto L_0x003f;
    L_0x003c:
        r1.disconnect();
    L_0x003f:
        r0 = 0;
        goto L_0x0032;
    L_0x0041:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0047;
    L_0x0044:
        r1.disconnect();
    L_0x0047:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzarx.zza(java.net.URL):int");
    }

    private final int zza(URL url, byte[] bArr) {
        Object e;
        Throwable th;
        OutputStream outputStream = null;
        zzbq.checkNotNull(url);
        zzbq.checkNotNull(bArr);
        zzb("POST bytes, url", Integer.valueOf(bArr.length), url);
        if (zzapz.zzpz()) {
            zza("Post payload\n", new String(bArr));
        }
        HttpURLConnection zzb;
        try {
            getContext().getPackageName();
            zzb = zzb(url);
            try {
                zzb.setDoOutput(true);
                zzb.setFixedLengthStreamingMode(bArr.length);
                zzb.connect();
                outputStream = zzb.getOutputStream();
                outputStream.write(bArr);
                zzb(zzb);
                int responseCode = zzb.getResponseCode();
                if (responseCode == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                    zzwx().zzwq();
                }
                zzb("POST status", Integer.valueOf(responseCode));
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        zze("Error closing http post connection output stream", e2);
                    }
                }
                if (zzb == null) {
                    return responseCode;
                }
                zzb.disconnect();
                return responseCode;
            } catch (IOException e3) {
                e = e3;
                try {
                    zzd("Network POST connection error", e);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            zze("Error closing http post connection output stream", e4);
                        }
                    }
                    if (zzb != null) {
                        zzb.disconnect();
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e22) {
                            zze("Error closing http post connection output stream", e22);
                        }
                    }
                    if (zzb != null) {
                        zzb.disconnect();
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            zzb = outputStream;
            zzd("Network POST connection error", e);
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzb != null) {
                zzb.disconnect();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            zzb = outputStream;
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzb != null) {
                zzb.disconnect();
            }
            throw th;
        }
    }

    private static void zza(StringBuilder stringBuilder, String str, String str2) throws UnsupportedEncodingException {
        if (stringBuilder.length() != 0) {
            stringBuilder.append(Typography.amp);
        }
        stringBuilder.append(URLEncoder.encode(str, HttpRequest.CHARSET_UTF8));
        stringBuilder.append('=');
        stringBuilder.append(URLEncoder.encode(str2, HttpRequest.CHARSET_UTF8));
    }

    private final int zzb(URL url, byte[] bArr) {
        HttpURLConnection zzb;
        Object e;
        HttpURLConnection httpURLConnection;
        Throwable th;
        OutputStream outputStream = null;
        zzbq.checkNotNull(url);
        zzbq.checkNotNull(bArr);
        try {
            OutputStream outputStream2;
            getContext().getPackageName();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            zza("POST compressed size, ratio %, url", Integer.valueOf(toByteArray.length), Long.valueOf((100 * ((long) toByteArray.length)) / ((long) bArr.length)), url);
            if (toByteArray.length > bArr.length) {
                zzc("Compressed payload is larger then uncompressed. compressed, uncompressed", Integer.valueOf(toByteArray.length), Integer.valueOf(bArr.length));
            }
            if (zzapz.zzpz()) {
                String str = "Post payload";
                String str2 = "\n";
                String valueOf = String.valueOf(new String(bArr));
                zza(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            zzb = zzb(url);
            try {
                zzb.setDoOutput(true);
                zzb.addRequestProperty(HttpRequest.HEADER_CONTENT_ENCODING, HttpRequest.ENCODING_GZIP);
                zzb.setFixedLengthStreamingMode(toByteArray.length);
                zzb.connect();
                outputStream2 = zzb.getOutputStream();
            } catch (IOException e2) {
                e = e2;
                httpURLConnection = zzb;
                try {
                    zzd("Network compressed POST connection error", e);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e3) {
                            zze("Error closing http compressed post connection output stream", e3);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    zzb = httpURLConnection;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            zze("Error closing http compressed post connection output stream", e4);
                        }
                    }
                    if (zzb != null) {
                        zzb.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzb != null) {
                    zzb.disconnect();
                }
                throw th;
            }
            try {
                outputStream2.write(toByteArray);
                outputStream2.close();
                zzb(zzb);
                int responseCode = zzb.getResponseCode();
                if (responseCode == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                    zzwx().zzwq();
                }
                zzb("POST status", Integer.valueOf(responseCode));
                if (zzb == null) {
                    return responseCode;
                }
                zzb.disconnect();
                return responseCode;
            } catch (IOException e5) {
                e = e5;
                outputStream = outputStream2;
                httpURLConnection = zzb;
                zzd("Network compressed POST connection error", e);
                if (outputStream != null) {
                    outputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return 0;
            } catch (Throwable th4) {
                th = th4;
                outputStream = outputStream2;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzb != null) {
                    zzb.disconnect();
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            httpURLConnection = null;
            zzd("Network compressed POST connection error", e);
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return 0;
        } catch (Throwable th5) {
            th = th5;
            zzb = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzb != null) {
                zzb.disconnect();
            }
            throw th;
        }
    }

    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(((Integer) zzarl.zzdxa.get()).intValue());
            httpURLConnection.setReadTimeout(((Integer) zzarl.zzdxb.get()).intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("User-Agent", this.zzczb);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    private final URL zzb(zzarq com_google_android_gms_internal_zzarq, String str) {
        String zzyy;
        String zzyw;
        if (com_google_android_gms_internal_zzarq.zzzk()) {
            zzyw = zzard.zzyw();
            zzyy = zzard.zzyy();
            zzyy = new StringBuilder(((String.valueOf(zzyw).length() + 1) + String.valueOf(zzyy).length()) + String.valueOf(str).length()).append(zzyw).append(zzyy).append("?").append(str).toString();
        } else {
            zzyw = zzard.zzyx();
            zzyy = zzard.zzyy();
            zzyy = new StringBuilder(((String.valueOf(zzyw).length() + 1) + String.valueOf(zzyy).length()) + String.valueOf(str).length()).append(zzyw).append(zzyy).append("?").append(str).toString();
        }
        try {
            return new URL(zzyy);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final void zzb(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
        }
    }

    private final URL zzd(zzarq com_google_android_gms_internal_zzarq) {
        String valueOf;
        String valueOf2;
        if (com_google_android_gms_internal_zzarq.zzzk()) {
            valueOf = String.valueOf(zzard.zzyw());
            valueOf2 = String.valueOf(zzard.zzyy());
            valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            valueOf = String.valueOf(zzard.zzyx());
            valueOf2 = String.valueOf(zzard.zzyy());
            valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        try {
            return new URL(valueOf);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private final List<Long> zzv(List<zzarq> list) {
        List<Long> arrayList = new ArrayList(list.size());
        for (zzarq com_google_android_gms_internal_zzarq : list) {
            boolean z;
            zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
            String zza = zza(com_google_android_gms_internal_zzarq, !com_google_android_gms_internal_zzarq.zzzk());
            if (zza == null) {
                zzwt().zza(com_google_android_gms_internal_zzarq, "Error formatting hit for upload");
                z = true;
            } else {
                URL zzb;
                if (zza.length() <= ((Integer) zzarl.zzdwq.get()).intValue()) {
                    zzb = zzb(com_google_android_gms_internal_zzarq, zza);
                    if (zzb == null) {
                        zzdy("Failed to build collect GET endpoint url");
                    } else {
                        z = zza(zzb) == Callback.DEFAULT_DRAG_ANIMATION_DURATION;
                    }
                } else {
                    String zza2 = zza(com_google_android_gms_internal_zzarq, false);
                    if (zza2 == null) {
                        zzwt().zza(com_google_android_gms_internal_zzarq, "Error formatting hit for POST upload");
                        z = true;
                    } else {
                        byte[] bytes = zza2.getBytes();
                        if (bytes.length > ((Integer) zzarl.zzdwv.get()).intValue()) {
                            zzwt().zza(com_google_android_gms_internal_zzarq, "Hit payload exceeds size limit");
                            z = true;
                        } else {
                            zzb = zzd(com_google_android_gms_internal_zzarq);
                            if (zzb == null) {
                                zzdy("Failed to build collect POST endpoint url");
                            } else if (zza(zzb, bytes) == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                                z = true;
                            }
                        }
                    }
                }
                z = false;
            }
            if (!z) {
                break;
            }
            arrayList.add(Long.valueOf(com_google_android_gms_internal_zzarq.zzzh()));
            if (arrayList.size() >= zzard.zzyu()) {
                break;
            }
        }
        return arrayList;
    }

    private final URL zzzt() {
        String valueOf = String.valueOf(zzard.zzyw());
        String valueOf2 = String.valueOf((String) zzarl.zzdwp.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    final String zza(zzarq com_google_android_gms_internal_zzarq, boolean z) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzarq);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : com_google_android_gms_internal_zzarq.zzjh().entrySet()) {
                String str = (String) entry.getKey();
                if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str) || "z".equals(str) || "_gmsv".equals(str))) {
                    zza(stringBuilder, str, (String) entry.getValue());
                }
            }
            zza(stringBuilder, "ht", String.valueOf(com_google_android_gms_internal_zzarq.zzzi()));
            zza(stringBuilder, "qt", String.valueOf(zzws().currentTimeMillis() - com_google_android_gms_internal_zzarq.zzzi()));
            if (z) {
                long zzzl = com_google_android_gms_internal_zzarq.zzzl();
                zza(stringBuilder, "z", zzzl != 0 ? String.valueOf(zzzl) : String.valueOf(com_google_android_gms_internal_zzarq.zzzh()));
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    public final List<Long> zzu(List<zzarq> list) {
        boolean z;
        boolean z2;
        zzary com_google_android_gms_internal_zzary;
        List<Long> arrayList;
        URL zzzt;
        int zzb;
        boolean z3 = true;
        zzj.zzve();
        zzxf();
        zzbq.checkNotNull(list);
        if (zzwu().zzyz().isEmpty() || !this.zzdyi.zzu(((long) ((Integer) zzarl.zzdwy.get()).intValue()) * 1000)) {
            z = false;
        } else {
            z = zzaqt.zzed((String) zzarl.zzdwr.get()) != zzaqt.NONE;
            if (zzaqz.zzee((String) zzarl.zzdws.get()) == zzaqz.GZIP) {
                z2 = true;
                if (z) {
                    return zzv(list);
                }
                if (list.isEmpty()) {
                    z3 = false;
                }
                zzbq.checkArgument(z3);
                zza("Uploading batched hits. compression, count", Boolean.valueOf(z2), Integer.valueOf(list.size()));
                com_google_android_gms_internal_zzary = new zzary(this);
                arrayList = new ArrayList();
                for (zzarq com_google_android_gms_internal_zzarq : list) {
                    if (com_google_android_gms_internal_zzary.zze(com_google_android_gms_internal_zzarq)) {
                        break;
                    }
                    arrayList.add(Long.valueOf(com_google_android_gms_internal_zzarq.zzzh()));
                }
                if (com_google_android_gms_internal_zzary.zzzv() == 0) {
                    return arrayList;
                }
                zzzt = zzzt();
                if (zzzt != null) {
                    zzdy("Failed to build batching endpoint url");
                } else {
                    zzb = z2 ? zzb(zzzt, com_google_android_gms_internal_zzary.getPayload()) : zza(zzzt, com_google_android_gms_internal_zzary.getPayload());
                    if (Callback.DEFAULT_DRAG_ANIMATION_DURATION != zzb) {
                        zza("Batched upload completed. Hits batched", Integer.valueOf(com_google_android_gms_internal_zzary.zzzv()));
                        return arrayList;
                    }
                    zza("Network error uploading hits. status code", Integer.valueOf(zzb));
                    if (zzwu().zzyz().contains(Integer.valueOf(zzb))) {
                        zzdx("Server instructed the client to stop batching");
                        this.zzdyi.start();
                    }
                }
                return Collections.emptyList();
            }
        }
        z2 = false;
        if (z) {
            return zzv(list);
        }
        if (list.isEmpty()) {
            z3 = false;
        }
        zzbq.checkArgument(z3);
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z2), Integer.valueOf(list.size()));
        com_google_android_gms_internal_zzary = new zzary(this);
        arrayList = new ArrayList();
        for (zzarq com_google_android_gms_internal_zzarq2 : list) {
            if (com_google_android_gms_internal_zzary.zze(com_google_android_gms_internal_zzarq2)) {
                break;
            }
            arrayList.add(Long.valueOf(com_google_android_gms_internal_zzarq2.zzzh()));
        }
        if (com_google_android_gms_internal_zzary.zzzv() == 0) {
            return arrayList;
        }
        zzzt = zzzt();
        if (zzzt != null) {
            if (z2) {
            }
            if (Callback.DEFAULT_DRAG_ANIMATION_DURATION != zzb) {
                zza("Network error uploading hits. status code", Integer.valueOf(zzb));
                if (zzwu().zzyz().contains(Integer.valueOf(zzb))) {
                    zzdx("Server instructed the client to stop batching");
                    this.zzdyi.start();
                }
            } else {
                zza("Batched upload completed. Hits batched", Integer.valueOf(com_google_android_gms_internal_zzary.zzzv()));
                return arrayList;
            }
        }
        zzdy("Failed to build batching endpoint url");
        return Collections.emptyList();
    }

    protected final void zzvf() {
        zza("Network initialized. User agent", this.zzczb);
    }

    public final boolean zzzs() {
        NetworkInfo activeNetworkInfo;
        zzj.zzve();
        zzxf();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdu("No network connectivity");
        return false;
    }
}
