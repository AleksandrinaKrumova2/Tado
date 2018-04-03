package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class zzdo extends BroadcastReceiver {
    private static String zzdyg = zzdo.class.getName();
    private final zzfn zzkgy;

    zzdo(zzfn com_google_android_gms_tagmanager_zzfn) {
        this.zzkgy = com_google_android_gms_tagmanager_zzfn;
    }

    public static void zzej(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzdyg, true);
        context.sendBroadcast(intent);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            Bundle extras = intent.getExtras();
            Boolean bool = Boolean.FALSE;
            if (extras != null) {
                bool = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            }
            this.zzkgy.zzbv(!bool.booleanValue());
        } else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(zzdyg)) {
            this.zzkgy.zzbgf();
        }
    }
}
