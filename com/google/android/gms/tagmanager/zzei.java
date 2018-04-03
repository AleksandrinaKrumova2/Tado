package com.google.android.gms.tagmanager;

import android.net.Uri;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzei {
    private static zzei zzkhm;
    private volatile String zzkdd = null;
    private volatile zza zzkhn = zza.NONE;
    private volatile String zzkho = null;
    private volatile String zzkhp = null;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzei() {
    }

    static zzei zzbfo() {
        zzei com_google_android_gms_tagmanager_zzei;
        synchronized (zzei.class) {
            if (zzkhm == null) {
                zzkhm = new zzei();
            }
            com_google_android_gms_tagmanager_zzei = zzkhm;
        }
        return com_google_android_gms_tagmanager_zzei;
    }

    private static String zzlu(String str) {
        return str.split("&")[0].split("=")[1];
    }

    final String getContainerId() {
        return this.zzkdd;
    }

    final zza zzbfp() {
        return this.zzkhn;
    }

    final String zzbfq() {
        return this.zzkho;
    }

    final synchronized boolean zzq(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), HttpRequest.CHARSET_UTF8);
                String str;
                String valueOf;
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    str = "Container preview url: ";
                    valueOf = String.valueOf(decode);
                    zzdj.m11v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.zzkhn = zza.CONTAINER_DEBUG;
                    } else {
                        this.zzkhn = zza.CONTAINER;
                    }
                    this.zzkhp = uri.getQuery().replace("&gtm_debug=x", "");
                    if (this.zzkhn == zza.CONTAINER || this.zzkhn == zza.CONTAINER_DEBUG) {
                        decode = String.valueOf("/r?");
                        valueOf = String.valueOf(this.zzkhp);
                        this.zzkho = valueOf.length() != 0 ? decode.concat(valueOf) : new String(decode);
                    }
                    this.zzkdd = zzlu(this.zzkhp);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    str = "Invalid preview uri: ";
                    String valueOf2 = String.valueOf(decode);
                    zzdj.zzcu(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                    z = false;
                } else if (zzlu(uri.getQuery()).equals(this.zzkdd)) {
                    decode = "Exit preview mode for container: ";
                    valueOf = String.valueOf(this.zzkdd);
                    zzdj.m11v(valueOf.length() != 0 ? decode.concat(valueOf) : new String(decode));
                    this.zzkhn = zza.NONE;
                    this.zzkho = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }
}
