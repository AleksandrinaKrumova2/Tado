package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzbg;
import com.google.android.gms.internal.zzbs;
import java.util.Map;

final class zzl extends zzbr {
    private static final String ID = zzbg.APP_VERSION_NAME.toString();
    private final Context mContext;

    public zzl(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public final boolean zzbdp() {
        return true;
    }

    public final zzbs zzv(Map<String, zzbs> map) {
        try {
            return zzgk.zzam(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            String packageName = this.mContext.getPackageName();
            String message = e.getMessage();
            zzdj.m10e(new StringBuilder((String.valueOf(packageName).length() + 25) + String.valueOf(message).length()).append("Package name ").append(packageName).append(" not found. ").append(message).toString());
            return zzgk.zzbgs();
        }
    }
}
