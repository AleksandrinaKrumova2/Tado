package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzcx {
    private static String zzkge;
    static Map<String, String> zzkgf = new HashMap();

    static void zzai(Context context, String str) {
        zzfu.zze(context, "gtm_install_referrer", "referrer", str);
        zzak(context, str);
    }

    public static String zzaj(Context context, String str) {
        if (zzkge == null) {
            synchronized (zzcx.class) {
                if (zzkge == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzkge = sharedPreferences.getString("referrer", "");
                    } else {
                        zzkge = "";
                    }
                }
            }
        }
        return zzax(zzkge, str);
    }

    public static void zzak(Context context, String str) {
        String zzax = zzax(str, "conv");
        if (zzax != null && zzax.length() > 0) {
            zzkgf.put(zzax, str);
            zzfu.zze(context, "gtm_click_referrers", zzax, str);
        }
    }

    public static String zzax(String str, String str2) {
        if (str2 == null) {
            return str.length() > 0 ? str : null;
        } else {
            String str3 = "http://hostname/?";
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).getQueryParameter(str2);
        }
    }

    public static void zzlr(String str) {
        synchronized (zzcx.class) {
            zzkge = str;
        }
    }
}
