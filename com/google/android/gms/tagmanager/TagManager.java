package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager zzkkd;
    private final Context mContext;
    private final DataLayer zzkde;
    private final zzal zzkid;
    private final zza zzkka;
    private final zzfn zzkkb;
    private final ConcurrentMap<String, zzv> zzkkc;

    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal com_google_android_gms_tagmanager_zzal);
    }

    private TagManager(Context context, zza com_google_android_gms_tagmanager_TagManager_zza, DataLayer dataLayer, zzfn com_google_android_gms_tagmanager_zzfn) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzkkb = com_google_android_gms_tagmanager_zzfn;
        this.zzkka = com_google_android_gms_tagmanager_TagManager_zza;
        this.zzkkc = new ConcurrentHashMap();
        this.zzkde = dataLayer;
        this.zzkde.zza(new zzgb(this));
        this.zzkde.zza(new zzg(this.mContext));
        this.zzkid = new zzal();
        this.mContext.registerComponentCallbacks(new zzgd(this));
        zza.zzeb(this.mContext);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzkkd == null) {
                if (context == null) {
                    zzdj.m10e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzkkd = new TagManager(context, new zzgc(), new DataLayer(new zzat(context)), zzfo.zzbgg());
            }
            tagManager = zzkkd;
        }
        return tagManager;
    }

    private final void zzly(String str) {
        for (zzv zzld : this.zzkkc.values()) {
            zzld.zzld(str);
        }
    }

    public void dispatch() {
        this.zzkkb.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzkde;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        PendingResult zza = this.zzkka.zza(this.mContext, this, null, str, i, this.zzkid);
        zza.zzbdy();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.zzkka.zza(this.mContext, this, handler.getLooper(), str, i, this.zzkid);
        zza.zzbdy();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        PendingResult zza = this.zzkka.zza(this.mContext, this, null, str, i, this.zzkid);
        zza.zzbea();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.zzkka.zza(this.mContext, this, handler.getLooper(), str, i, this.zzkid);
        zza.zzbea();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        PendingResult zza = this.zzkka.zza(this.mContext, this, null, str, i, this.zzkid);
        zza.zzbdz();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.zzkka.zza(this.mContext, this, handler.getLooper(), str, i, this.zzkid);
        zza.zzbdz();
        return zza;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzdj.setLogLevel(z ? 2 : 5);
    }

    public final int zza(zzv com_google_android_gms_tagmanager_zzv) {
        this.zzkkc.put(com_google_android_gms_tagmanager_zzv.getContainerId(), com_google_android_gms_tagmanager_zzv);
        return this.zzkkc.size();
    }

    public final boolean zzb(zzv com_google_android_gms_tagmanager_zzv) {
        return this.zzkkc.remove(com_google_android_gms_tagmanager_zzv.getContainerId()) != null;
    }

    final synchronized boolean zzq(Uri uri) {
        boolean z;
        zzei zzbfo = zzei.zzbfo();
        if (zzbfo.zzq(uri)) {
            String containerId = zzbfo.getContainerId();
            switch (zzge.zzkkf[zzbfo.zzbfp().ordinal()]) {
                case 1:
                    zzv com_google_android_gms_tagmanager_zzv = (zzv) this.zzkkc.get(containerId);
                    if (com_google_android_gms_tagmanager_zzv != null) {
                        com_google_android_gms_tagmanager_zzv.zzle(null);
                        com_google_android_gms_tagmanager_zzv.refresh();
                        break;
                    }
                    break;
                case 2:
                case 3:
                    for (String str : this.zzkkc.keySet()) {
                        zzv com_google_android_gms_tagmanager_zzv2 = (zzv) this.zzkkc.get(str);
                        if (str.equals(containerId)) {
                            com_google_android_gms_tagmanager_zzv2.zzle(zzbfo.zzbfq());
                            com_google_android_gms_tagmanager_zzv2.refresh();
                        } else if (com_google_android_gms_tagmanager_zzv2.zzbdv() != null) {
                            com_google_android_gms_tagmanager_zzv2.zzle(null);
                            com_google_android_gms_tagmanager_zzv2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
