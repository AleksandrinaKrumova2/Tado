package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzasc;

public final class AnalyticsReceiver extends BroadcastReceiver {
    private zzasc zzdok;

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final void onReceive(Context context, Intent intent) {
        if (this.zzdok == null) {
            this.zzdok = new zzasc();
        }
        zzasc.onReceive(context, intent);
    }
}
