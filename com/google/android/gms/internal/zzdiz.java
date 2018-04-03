package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import java.util.HashMap;
import java.util.Map;

public final class zzdiz {
    private final Context mContext;
    private final zzd zzata;
    private String zzkeh;
    private Map<String, Object> zzkrd;
    private final Map<String, Object> zzkre;
    private final zzdjj zzktp;

    public zzdiz(Context context) {
        this(context, new HashMap(), new zzdjj(context), zzh.zzamg());
    }

    private zzdiz(Context context, Map<String, Object> map, zzdjj com_google_android_gms_internal_zzdjj, zzd com_google_android_gms_common_util_zzd) {
        this.zzkeh = null;
        this.zzkrd = new HashMap();
        this.mContext = context;
        this.zzata = com_google_android_gms_common_util_zzd;
        this.zzktp = com_google_android_gms_internal_zzdjj;
        this.zzkre = map;
    }

    public final void zzng(String str) {
        this.zzkeh = str;
    }
}
