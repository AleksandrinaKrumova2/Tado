package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.internal.zzbo;
import com.google.android.gms.internal.zzdiy;
import com.google.android.gms.internal.zzdja;
import com.google.android.gms.internal.zzdje;
import com.google.android.gms.internal.zzdji;
import com.google.android.gms.internal.zzfjr;
import com.google.android.gms.internal.zzfjs;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

final class zzey implements zzah {
    private final Context mContext;
    private final ExecutorService zzieo = Executors.newSingleThreadExecutor();
    private final String zzkdd;
    private zzdi<zzdiy> zzkic;

    zzey(Context context, String str) {
        this.mContext = context;
        this.zzkdd = str;
    }

    private static zzdje zza(ByteArrayOutputStream byteArrayOutputStream) {
        zzdje com_google_android_gms_internal_zzdje = null;
        try {
            com_google_android_gms_internal_zzdje = zzdb.zzls(byteArrayOutputStream.toString(HttpRequest.CHARSET_UTF8));
        } catch (UnsupportedEncodingException e) {
            zzdj.zzbx("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
        } catch (JSONException e2) {
            zzdj.zzcu("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
        }
        return com_google_android_gms_internal_zzdje;
    }

    private final File zzbfu() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzkdd);
        return new File(this.mContext.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private static zzdje zzw(byte[] bArr) {
        try {
            zzdje zza = zzdja.zza((zzbo) zzfjs.zza(new zzbo(), bArr));
            if (zza == null) {
                return zza;
            }
            zzdj.m11v("The container was successfully loaded from the resource (using binary file)");
            return zza;
        } catch (zzfjr e) {
            zzdj.m10e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzdji e2) {
            zzdj.zzcu("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    public final synchronized void release() {
        this.zzieo.shutdown();
    }

    public final void zza(zzdiy com_google_android_gms_internal_zzdiy) {
        this.zzieo.execute(new zzfa(this, com_google_android_gms_internal_zzdiy));
    }

    public final void zza(zzdi<zzdiy> com_google_android_gms_tagmanager_zzdi_com_google_android_gms_internal_zzdiy) {
        this.zzkic = com_google_android_gms_tagmanager_zzdi_com_google_android_gms_internal_zzdiy;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final boolean zzb(com.google.android.gms.internal.zzdiy r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.zzbfu();
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0016 }
        r2.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0016 }
        r3 = com.google.android.gms.internal.zzfjs.zzc(r5);	 Catch:{ IOException -> 0x0026 }
        r2.write(r3);	 Catch:{ IOException -> 0x0026 }
        r2.close();	 Catch:{ IOException -> 0x001e }
    L_0x0014:
        r0 = 1;
    L_0x0015:
        return r0;
    L_0x0016:
        r1 = move-exception;
        r1 = "Error opening resource file for writing";
        com.google.android.gms.tagmanager.zzdj.m10e(r1);
        goto L_0x0015;
    L_0x001e:
        r0 = move-exception;
        r0 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r0);
        goto L_0x0014;
    L_0x0026:
        r3 = move-exception;
        r3 = "Error writing resource to disk. Removing resource from disk.";
        com.google.android.gms.tagmanager.zzdj.zzcu(r3);	 Catch:{ all -> 0x003c }
        r1.delete();	 Catch:{ all -> 0x003c }
        r2.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x0015;
    L_0x0034:
        r1 = move-exception;
        r1 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r1);
        goto L_0x0015;
    L_0x003c:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x0041 }
    L_0x0040:
        throw r0;
    L_0x0041:
        r1 = move-exception;
        r1 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r1);
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzey.zzb(com.google.android.gms.internal.zzdiy):boolean");
    }

    public final void zzbec() {
        this.zzieo.execute(new zzez(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void zzbft() {
        /*
        r3 = this;
        r0 = r3.zzkic;
        if (r0 != 0) goto L_0x000d;
    L_0x0004:
        r0 = new java.lang.IllegalStateException;
        r1 = "Callback must be set before execute";
        r0.<init>(r1);
        throw r0;
    L_0x000d:
        r0 = "Attempting to load resource from disk";
        com.google.android.gms.tagmanager.zzdj.m11v(r0);
        r0 = com.google.android.gms.tagmanager.zzei.zzbfo();
        r0 = r0.zzbfp();
        r1 = com.google.android.gms.tagmanager.zzei.zza.CONTAINER;
        if (r0 == r1) goto L_0x002b;
    L_0x001f:
        r0 = com.google.android.gms.tagmanager.zzei.zzbfo();
        r0 = r0.zzbfp();
        r1 = com.google.android.gms.tagmanager.zzei.zza.CONTAINER_DEBUG;
        if (r0 != r1) goto L_0x0043;
    L_0x002b:
        r0 = r3.zzkdd;
        r1 = com.google.android.gms.tagmanager.zzei.zzbfo();
        r1 = r1.getContainerId();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0043;
    L_0x003b:
        r0 = r3.zzkic;
        r1 = com.google.android.gms.tagmanager.zzda.zzkgo;
        r0.zzei(r1);
    L_0x0042:
        return;
    L_0x0043:
        r1 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x008c }
        r0 = r3.zzbfu();	 Catch:{ FileNotFoundException -> 0x008c }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x008c }
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r0.<init>();	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        com.google.android.gms.internal.zzdja.zzb(r1, r0);	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r0 = r0.toByteArray();	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r2 = new com.google.android.gms.internal.zzdiy;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r2.<init>();	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r0 = com.google.android.gms.internal.zzfjs.zza(r2, r0);	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r0 = (com.google.android.gms.internal.zzdiy) r0;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r2 = r0.zzyi;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        if (r2 != 0) goto L_0x009b;
    L_0x0067:
        r2 = r0.zzkto;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        if (r2 != 0) goto L_0x009b;
    L_0x006b:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r2 = "Resource and SupplementedResource are NULL.";
        r0.<init>(r2);	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        throw r0;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
    L_0x0074:
        r0 = move-exception;
        r0 = r3.zzkic;	 Catch:{ all -> 0x00ce }
        r2 = com.google.android.gms.tagmanager.zzda.zzkgp;	 Catch:{ all -> 0x00ce }
        r0.zzei(r2);	 Catch:{ all -> 0x00ce }
        r0 = "Failed to read the resource from disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r0);	 Catch:{ all -> 0x00ce }
        r1.close();	 Catch:{ IOException -> 0x00ac }
    L_0x0085:
        r0 = "The Disk resource was successfully read.";
        com.google.android.gms.tagmanager.zzdj.m11v(r0);
        goto L_0x0042;
    L_0x008c:
        r0 = move-exception;
        r0 = "Failed to find the resource in the disk";
        com.google.android.gms.tagmanager.zzdj.zzbx(r0);
        r0 = r3.zzkic;
        r1 = com.google.android.gms.tagmanager.zzda.zzkgo;
        r0.zzei(r1);
        goto L_0x0042;
    L_0x009b:
        r2 = r3.zzkic;	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r2.onSuccess(r0);	 Catch:{ IOException -> 0x0074, IllegalArgumentException -> 0x00b4 }
        r1.close();	 Catch:{ IOException -> 0x00a4 }
        goto L_0x0085;
    L_0x00a4:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r0);
        goto L_0x0085;
    L_0x00ac:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r0);
        goto L_0x0085;
    L_0x00b4:
        r0 = move-exception;
        r0 = r3.zzkic;	 Catch:{ all -> 0x00ce }
        r2 = com.google.android.gms.tagmanager.zzda.zzkgp;	 Catch:{ all -> 0x00ce }
        r0.zzei(r2);	 Catch:{ all -> 0x00ce }
        r0 = "Failed to read the resource from disk. The resource is inconsistent";
        com.google.android.gms.tagmanager.zzdj.zzcu(r0);	 Catch:{ all -> 0x00ce }
        r1.close();	 Catch:{ IOException -> 0x00c6 }
        goto L_0x0085;
    L_0x00c6:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r0);
        goto L_0x0085;
    L_0x00ce:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x00d3 }
    L_0x00d2:
        throw r0;
    L_0x00d3:
        r1 = move-exception;
        r1 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzdj.zzcu(r1);
        goto L_0x00d2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzey.zzbft():void");
    }

    public final zzdje zzej(int i) {
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            String resourceName = this.mContext.getResources().getResourceName(i);
            zzdj.m11v(new StringBuilder(String.valueOf(resourceName).length() + 66).append("Attempting to load a container from the resource ID ").append(i).append(" (").append(resourceName).append(")").toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzdja.zzb(openRawResource, byteArrayOutputStream);
                zzdje zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzw(byteArrayOutputStream.toByteArray());
                }
                zzdj.m11v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException e) {
                String resourceName2 = this.mContext.getResources().getResourceName(i);
                zzdj.zzcu(new StringBuilder(String.valueOf(resourceName2).length() + 67).append("Error reading the default container with resource ID ").append(i).append(" (").append(resourceName2).append(")").toString());
                return null;
            }
        } catch (NotFoundException e2) {
            zzdj.zzcu("Failed to load the container. No default container resource found with the resource ID " + i);
            return null;
        }
    }
}
