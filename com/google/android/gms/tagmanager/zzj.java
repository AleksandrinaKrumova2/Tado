package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzj extends zzbr {
    private static final String ID = zzbg.APP_NAME.toString();
    private final Context mContext;

    public zzj(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return zzgk.zzam(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        } catch (Throwable e) {
            zzdj.zzb("App name is not found.", e);
            return zzgk.zzbgs();
        }
    }
}
