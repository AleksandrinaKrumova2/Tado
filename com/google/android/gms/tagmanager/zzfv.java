package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

final class zzfv implements zzbe {
    private final Context mContext;
    private final String zzczb;
    private final zzfy zzkjy;
    private final zzfx zzkjz;

    zzfv(Context context, zzfx com_google_android_gms_tagmanager_zzfx) {
        this(new zzfw(), context, com_google_android_gms_tagmanager_zzfx);
    }

    private zzfv(zzfy com_google_android_gms_tagmanager_zzfy, Context context, zzfx com_google_android_gms_tagmanager_zzfx) {
        String str = null;
        this.zzkjy = com_google_android_gms_tagmanager_zzfy;
        this.mContext = context.getApplicationContext();
        this.zzkjz = com_google_android_gms_tagmanager_zzfx;
        String str2 = "GoogleTagManager";
        String str3 = "4.00";
        String str4 = VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                stringBuilder.append("-").append(locale.getCountry().toLowerCase());
            }
            str = stringBuilder.toString();
        }
        String str5 = Build.MODEL;
        String str6 = Build.ID;
        this.zzczb = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str2, str3, str4, str, str5, str6});
    }

    private static URL zzd(zzbx com_google_android_gms_tagmanager_zzbx) {
        try {
            return new URL(com_google_android_gms_tagmanager_zzbx.zzbfa());
        } catch (MalformedURLException e) {
            zzdj.m10e("Error trying to parse the GTM url.");
            return null;
        }
    }

    public final void zzal(List<zzbx> list) {
        Throwable th;
        InputStream inputStream;
        Object obj;
        Throwable th2;
        Object obj2;
        IOException iOException;
        int min = Math.min(list.size(), 40);
        Object obj3 = 1;
        int i = 0;
        while (i < min) {
            Object obj4;
            zzbx com_google_android_gms_tagmanager_zzbx = (zzbx) list.get(i);
            URL zzd = zzd(com_google_android_gms_tagmanager_zzbx);
            if (zzd == null) {
                zzdj.zzcu("No destination: discarding hit.");
                this.zzkjz.zzb(com_google_android_gms_tagmanager_zzbx);
                obj4 = obj3;
            } else {
                try {
                    HttpURLConnection zzc = this.zzkjy.zzc(zzd);
                    if (obj3 != null) {
                        try {
                            zzdo.zzej(this.mContext);
                            obj3 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = null;
                            obj = obj3;
                            th2 = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    obj2 = obj;
                                    iOException = e;
                                }
                            }
                            zzc.disconnect();
                            throw th2;
                        }
                    }
                    zzc.setRequestProperty("User-Agent", this.zzczb);
                    int responseCode = zzc.getResponseCode();
                    InputStream inputStream2 = zzc.getInputStream();
                    if (responseCode != Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                        try {
                            zzdj.zzcu("Bad response: " + responseCode);
                            this.zzkjz.zzc(com_google_android_gms_tagmanager_zzbx);
                        } catch (Throwable th32) {
                            th = th32;
                            inputStream = inputStream2;
                            obj = obj3;
                            th2 = th;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            zzc.disconnect();
                            throw th2;
                        }
                    }
                    this.zzkjz.zza(com_google_android_gms_tagmanager_zzbx);
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    zzc.disconnect();
                    obj4 = obj3;
                } catch (IOException e2) {
                    iOException = e2;
                    obj2 = obj3;
                    String str = "Exception sending hit: ";
                    String valueOf = String.valueOf(iOException.getClass().getSimpleName());
                    zzdj.zzcu(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    zzdj.zzcu(iOException.getMessage());
                    this.zzkjz.zzc(com_google_android_gms_tagmanager_zzbx);
                    obj4 = obj2;
                    i++;
                    obj3 = obj4;
                }
            }
            i++;
            obj3 = obj4;
        }
    }

    public final boolean zzbeq() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdj.m11v("...no network connectivity");
        return false;
    }
}
