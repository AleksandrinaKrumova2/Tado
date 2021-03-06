package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;
import org.joda.time.DateTimeConstants;

@WorkerThread
final class zzchu implements Runnable {
    private final String mPackageName;
    private final URL zzbxv;
    private final byte[] zzgfx;
    private final zzchs zzjck;
    private final Map<String, String> zzjcl;
    private /* synthetic */ zzchq zzjcm;

    public zzchu(zzchq com_google_android_gms_internal_zzchq, String str, URL url, byte[] bArr, Map<String, String> map, zzchs com_google_android_gms_internal_zzchs) {
        this.zzjcm = com_google_android_gms_internal_zzchq;
        zzbq.zzgm(str);
        zzbq.checkNotNull(url);
        zzbq.checkNotNull(com_google_android_gms_internal_zzchs);
        this.zzbxv = url;
        this.zzgfx = bArr;
        this.zzjck = com_google_android_gms_internal_zzchs;
        this.mPackageName = str;
        this.zzjcl = map;
    }

    public final void run() {
        Throwable e;
        Map map;
        int i;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        Throwable th;
        Map map2;
        int i2 = 0;
        this.zzjcm.zzawj();
        HttpURLConnection httpURLConnection2;
        OutputStream outputStream2;
        try {
            URLConnection openConnection = this.zzbxv.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                httpURLConnection2 = (HttpURLConnection) openConnection;
                httpURLConnection2.setDefaultUseCaches(false);
                httpURLConnection2.setConnectTimeout(DateTimeConstants.MILLIS_PER_MINUTE);
                httpURLConnection2.setReadTimeout(61000);
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setDoInput(true);
                try {
                    if (this.zzjcl != null) {
                        for (Entry entry : this.zzjcl.entrySet()) {
                            httpURLConnection2.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    if (this.zzgfx != null) {
                        byte[] zzq = this.zzjcm.zzawu().zzq(this.zzgfx);
                        this.zzjcm.zzawy().zzazj().zzj("Uploading data. size", Integer.valueOf(zzq.length));
                        httpURLConnection2.setDoOutput(true);
                        httpURLConnection2.addRequestProperty(HttpRequest.HEADER_CONTENT_ENCODING, HttpRequest.ENCODING_GZIP);
                        httpURLConnection2.setFixedLengthStreamingMode(zzq.length);
                        httpURLConnection2.connect();
                        outputStream2 = httpURLConnection2.getOutputStream();
                        try {
                            outputStream2.write(zzq);
                            outputStream2.close();
                        } catch (IOException e2) {
                            e = e2;
                            map = null;
                            i = 0;
                            OutputStream outputStream3 = outputStream2;
                            httpURLConnection = httpURLConnection2;
                            outputStream = outputStream3;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e3) {
                                    this.zzjcm.zzawy().zzazd().zze("Error closing HTTP compressed POST connection output stream. appId", zzchm.zzjk(this.mPackageName), e3);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i, e, null, map));
                        } catch (Throwable th2) {
                            th = th2;
                            map2 = null;
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (IOException e4) {
                                    this.zzjcm.zzawy().zzazd().zze("Error closing HTTP compressed POST connection output stream. appId", zzchm.zzjk(this.mPackageName), e4);
                                }
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i2, null, null, map2));
                            throw th;
                        }
                    }
                    i2 = httpURLConnection2.getResponseCode();
                    map2 = httpURLConnection2.getHeaderFields();
                    try {
                        byte[] zza = zzchq.zzc(httpURLConnection2);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i2, null, zza, map2));
                        return;
                    } catch (IOException e5) {
                        e = e5;
                        map = map2;
                        i = i2;
                        httpURLConnection = httpURLConnection2;
                        outputStream = null;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i, e, null, map));
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream2 = null;
                        if (outputStream2 != null) {
                            outputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i2, null, null, map2));
                        throw th;
                    }
                } catch (IOException e6) {
                    e = e6;
                    map = null;
                    i = i2;
                    httpURLConnection = httpURLConnection2;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i, e, null, map));
                } catch (Throwable th32) {
                    th = th32;
                    map2 = null;
                    outputStream2 = null;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i2, null, null, map2));
                    throw th;
                }
            }
            throw new IOException("Failed to obtain HTTP connection");
        } catch (IOException e7) {
            e = e7;
            map = null;
            i = 0;
            outputStream = null;
            httpURLConnection = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i, e, null, map));
        } catch (Throwable th4) {
            th = th4;
            map2 = null;
            outputStream2 = null;
            httpURLConnection2 = null;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            this.zzjcm.zzawx().zzg(new zzcht(this.mPackageName, this.zzjck, i2, null, null, map2));
            throw th;
        }
    }
}
