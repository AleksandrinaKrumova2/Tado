package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzbo;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzdiy;
import com.google.android.gms.internal.zzdiz;
import com.google.android.gms.internal.zzdje;
import com.tado.android.utils.Constants;

public final class zzy extends BasePendingResult<ContainerHolder> {
    private final Context mContext;
    private final Looper zzall;
    private final zzd zzata;
    private final String zzkdd;
    private long zzkdi;
    private final TagManager zzkdp;
    private final zzaf zzkds;
    private final zzek zzkdt;
    private final int zzkdu;
    private final zzai zzkdv;
    private zzah zzkdw;
    private zzdiz zzkdx;
    private volatile zzv zzkdy;
    private volatile boolean zzkdz;
    private zzbr zzkea;
    private String zzkeb;
    private zzag zzkec;
    private zzac zzked;

    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah com_google_android_gms_tagmanager_zzah, zzag com_google_android_gms_tagmanager_zzag, zzdiz com_google_android_gms_internal_zzdiz, zzd com_google_android_gms_common_util_zzd, zzek com_google_android_gms_tagmanager_zzek, zzai com_google_android_gms_tagmanager_zzai) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzkdp = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzall = looper;
        this.zzkdd = str;
        this.zzkdu = i;
        this.zzkdw = com_google_android_gms_tagmanager_zzah;
        this.zzkec = com_google_android_gms_tagmanager_zzag;
        this.zzkdx = com_google_android_gms_internal_zzdiz;
        this.zzkds = new zzaf();
        this.zzkea = new zzbr();
        this.zzata = com_google_android_gms_common_util_zzd;
        this.zzkdt = com_google_android_gms_tagmanager_zzek;
        this.zzkdv = com_google_android_gms_tagmanager_zzai;
        if (zzbeb()) {
            zzle(zzei.zzbfo().zzbfq());
        }
    }

    public zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzal com_google_android_gms_tagmanager_zzal) {
        zzey com_google_android_gms_tagmanager_zzey = new zzey(context, str);
        zzet com_google_android_gms_tagmanager_zzet = new zzet(context, str, com_google_android_gms_tagmanager_zzal);
        Context context2 = context;
        TagManager tagManager2 = tagManager;
        Looper looper2 = looper;
        String str2 = str;
        int i2 = i;
        zzey com_google_android_gms_tagmanager_zzey2 = com_google_android_gms_tagmanager_zzey;
        zzet com_google_android_gms_tagmanager_zzet2 = com_google_android_gms_tagmanager_zzet;
        this(context2, tagManager2, looper2, str2, i2, com_google_android_gms_tagmanager_zzey2, com_google_android_gms_tagmanager_zzet2, new zzdiz(context), zzh.zzamg(), new zzdh(1, 5, 900000, Constants.CHECK_FOR_SERVER_CONNECTION_TIMEOUT, "refreshing", zzh.zzamg()), new zzai(context, str));
        this.zzkdx.zzng(com_google_android_gms_tagmanager_zzal.zzbei());
    }

    private final synchronized void zza(zzbr com_google_android_gms_internal_zzbr) {
        if (this.zzkdw != null) {
            zzdiy com_google_android_gms_internal_zzdiy = new zzdiy();
            com_google_android_gms_internal_zzdiy.zzktn = this.zzkdi;
            com_google_android_gms_internal_zzdiy.zzyi = new zzbo();
            com_google_android_gms_internal_zzdiy.zzkto = com_google_android_gms_internal_zzbr;
            this.zzkdw.zza(com_google_android_gms_internal_zzdiy);
        }
    }

    private final synchronized void zza(zzbr com_google_android_gms_internal_zzbr, long j, boolean z) {
        if (z) {
            boolean z2 = this.zzkdz;
        }
        if (!(isReady() && this.zzkdy == null)) {
            this.zzkea = com_google_android_gms_internal_zzbr;
            this.zzkdi = j;
            long zzbed = this.zzkdv.zzbed();
            zzbg(Math.max(0, Math.min(zzbed, (this.zzkdi + zzbed) - this.zzata.currentTimeMillis())));
            Container container = new Container(this.mContext, this.zzkdp.getDataLayer(), this.zzkdd, j, com_google_android_gms_internal_zzbr);
            if (this.zzkdy == null) {
                this.zzkdy = new zzv(this.zzkdp, this.zzall, container, this.zzkds);
            } else {
                this.zzkdy.zza(container);
            }
            if (!isReady() && this.zzked.zzb(container)) {
                setResult(this.zzkdy);
            }
        }
    }

    private final boolean zzbeb() {
        zzei zzbfo = zzei.zzbfo();
        return (zzbfo.zzbfp() == zza.CONTAINER || zzbfo.zzbfp() == zza.CONTAINER_DEBUG) && this.zzkdd.equals(zzbfo.getContainerId());
    }

    private final synchronized void zzbg(long j) {
        if (this.zzkec == null) {
            zzdj.zzcu("Refresh requested, but no network load scheduler.");
        } else {
            this.zzkec.zza(j, this.zzkea.zzyj);
        }
    }

    private final void zzbt(boolean z) {
        this.zzkdw.zza(new zzad());
        this.zzkec.zza(new zzae());
        zzdje zzej = this.zzkdw.zzej(this.zzkdu);
        if (zzej != null) {
            this.zzkdy = new zzv(this.zzkdp, this.zzall, new Container(this.mContext, this.zzkdp.getDataLayer(), this.zzkdd, 0, zzej), this.zzkds);
        }
        this.zzked = new zzab(this, z);
        if (zzbeb()) {
            this.zzkec.zza(0, "");
        } else {
            this.zzkdw.zzbec();
        }
    }

    protected final ContainerHolder zzam(Status status) {
        if (this.zzkdy != null) {
            return this.zzkdy;
        }
        if (status == Status.zzfnl) {
            zzdj.m10e("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return zzam(status);
    }

    final synchronized String zzbdv() {
        return this.zzkeb;
    }

    public final void zzbdy() {
        zzdje zzej = this.zzkdw.zzej(this.zzkdu);
        if (zzej != null) {
            setResult(new zzv(this.zzkdp, this.zzall, new Container(this.mContext, this.zzkdp.getDataLayer(), this.zzkdd, 0, zzej), new zzaa(this)));
        } else {
            String str = "Default was requested, but no default container was found";
            zzdj.m10e(str);
            setResult(zzam(new Status(10, str, null)));
        }
        this.zzkec = null;
        this.zzkdw = null;
    }

    public final void zzbdz() {
        zzbt(false);
    }

    public final void zzbea() {
        zzbt(true);
    }

    final synchronized void zzle(String str) {
        this.zzkeb = str;
        if (this.zzkec != null) {
            this.zzkec.zzlf(str);
        }
    }
}
