package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzdja;
import com.google.android.gms.internal.zzdjl;
import com.google.android.gms.internal.zzdjm;
import com.google.android.gms.internal.zzdjn;
import com.google.android.gms.internal.zzfjs;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

final class zzes implements Runnable {
    private final Context mContext;
    private final String zzkdd;
    private volatile String zzkeb;
    private final zzdjm zzkia;
    private final String zzkib;
    private zzdi<zzbr> zzkic;
    private volatile zzal zzkid;
    private volatile String zzkie;

    private zzes(Context context, String str, zzdjm com_google_android_gms_internal_zzdjm, zzal com_google_android_gms_tagmanager_zzal) {
        this.mContext = context;
        this.zzkia = com_google_android_gms_internal_zzdjm;
        this.zzkdd = str;
        this.zzkid = com_google_android_gms_tagmanager_zzal;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.zzkib = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.zzkeb = this.zzkib;
        this.zzkie = null;
    }

    public zzes(Context context, String str, zzal com_google_android_gms_tagmanager_zzal) {
        this(context, str, new zzdjm(), com_google_android_gms_tagmanager_zzal);
    }

    public final void run() {
        String str;
        if (this.zzkic == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        Object obj;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzdj.m11v("...no network connectivity");
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            this.zzkic.zzei(zzda.zzkgo);
            return;
        }
        zzdj.m11v("Start loading resource from network ...");
        String zzbei = this.zzkid.zzbei();
        String str2 = this.zzkeb;
        String str3 = "&v=a65833898";
        zzbei = new StringBuilder((String.valueOf(zzbei).length() + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(zzbei).append(str2).append(str3).toString();
        if (!(this.zzkie == null || this.zzkie.trim().equals(""))) {
            zzbei = String.valueOf(zzbei);
            str2 = "&pv=";
            str3 = this.zzkie;
            zzbei = new StringBuilder((String.valueOf(zzbei).length() + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(zzbei).append(str2).append(str3).toString();
        }
        if (zzei.zzbfo().zzbfp().equals(zza.CONTAINER_DEBUG)) {
            str2 = String.valueOf(zzbei);
            zzbei = String.valueOf("&gtm_debug=x");
            str2 = zzbei.length() != 0 ? str2.concat(zzbei) : new String(str2);
        } else {
            str2 = zzbei;
        }
        zzdjl zzbjj = zzdjm.zzbjj();
        InputStream inputStream = null;
        try {
            inputStream = zzbjj.zzmy(str2);
        } catch (FileNotFoundException e) {
            zzbei = this.zzkdd;
            zzdj.zzcu(new StringBuilder((String.valueOf(str2).length() + 79) + String.valueOf(zzbei).length()).append("No data is retrieved from the given url: ").append(str2).append(". Make sure container_id: ").append(zzbei).append(" is correct.").toString());
            this.zzkic.zzei(zzda.zzkgq);
            zzbjj.close();
            return;
        } catch (zzdjn e2) {
            str = "Error when loading resource for url: ";
            str3 = String.valueOf(str2);
            zzdj.zzcu(str3.length() != 0 ? str.concat(str3) : new String(str));
            this.zzkic.zzei(zzda.zzkgr);
        } catch (Throwable e3) {
            str3 = e3.getMessage();
            zzdj.zzc(new StringBuilder((String.valueOf(str2).length() + 40) + String.valueOf(str3).length()).append("Error when loading resources from url: ").append(str2).append(" ").append(str3).toString(), e3);
            this.zzkic.zzei(zzda.zzkgp);
            zzbjj.close();
            return;
        } catch (Throwable th) {
            zzbjj.close();
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            zzdja.zzb(inputStream, byteArrayOutputStream);
            zzbr com_google_android_gms_internal_zzbr = (zzbr) zzfjs.zza(new zzbr(), byteArrayOutputStream.toByteArray());
            str3 = String.valueOf(com_google_android_gms_internal_zzbr);
            zzdj.m11v(new StringBuilder(String.valueOf(str3).length() + 43).append("Successfully loaded supplemented resource: ").append(str3).toString());
            if (com_google_android_gms_internal_zzbr.zzyi == null && com_google_android_gms_internal_zzbr.zzyh.length == 0) {
                str = "No change for container: ";
                str3 = String.valueOf(this.zzkdd);
                zzdj.m11v(str3.length() != 0 ? str.concat(str3) : new String(str));
            }
            this.zzkic.onSuccess(com_google_android_gms_internal_zzbr);
            zzbjj.close();
            zzdj.m11v("Load resource from network finished.");
        } catch (Throwable e32) {
            str3 = e32.getMessage();
            zzdj.zzc(new StringBuilder((String.valueOf(str2).length() + 51) + String.valueOf(str3).length()).append("Error when parsing downloaded resources from url: ").append(str2).append(" ").append(str3).toString(), e32);
            this.zzkic.zzei(zzda.zzkgq);
            zzbjj.close();
        }
    }

    final void zza(zzdi<zzbr> com_google_android_gms_tagmanager_zzdi_com_google_android_gms_internal_zzbr) {
        this.zzkic = com_google_android_gms_tagmanager_zzdi_com_google_android_gms_internal_zzbr;
    }

    final void zzlf(String str) {
        if (str == null) {
            this.zzkeb = this.zzkib;
            return;
        }
        String str2 = "Setting CTFE URL path: ";
        String valueOf = String.valueOf(str);
        zzdj.zzbx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzkeb = str;
    }

    final void zzlv(String str) {
        String str2 = "Setting previous container version: ";
        String valueOf = String.valueOf(str);
        zzdj.zzbx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzkie = str;
    }
}
