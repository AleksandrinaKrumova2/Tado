package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzapz;
import com.google.android.gms.internal.zzaqc;
import com.google.android.gms.internal.zzard;
import com.google.android.gms.internal.zzasl;

public class CampaignTrackingReceiver extends BroadcastReceiver {
    private static Boolean zzdoo;

    public static boolean zzbk(Context context) {
        zzbq.checkNotNull(context);
        if (zzdoo != null) {
            return zzdoo.booleanValue();
        }
        boolean zzb = zzasl.zzb(context, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
        zzdoo = Boolean.valueOf(zzb);
        return zzb;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onReceive(Context context, Intent intent) {
        zzaqc zzbm = zzaqc.zzbm(context);
        zzapz zzwt = zzbm.zzwt();
        if (intent == null) {
            zzwt.zzdx("CampaignTrackingReceiver received null intent");
            return;
        }
        String stringExtra = intent.getStringExtra("referrer");
        String action = intent.getAction();
        zzwt.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty(stringExtra)) {
            zzwt.zzdx("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        zzr(context, stringExtra);
        int zzyr = zzard.zzyr();
        if (stringExtra.length() > zzyr) {
            zzwt.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(stringExtra.length()), Integer.valueOf(zzyr));
            stringExtra = stringExtra.substring(0, zzyr);
        }
        zzbm.zzwx().zza(stringExtra, new zzc(this, goAsync()));
    }

    protected void zzr(Context context, String str) {
    }
}
