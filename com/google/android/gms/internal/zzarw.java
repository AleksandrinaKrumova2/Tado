package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.zzbq;

class zzarw extends BroadcastReceiver {
    private static String zzdyg = zzarw.class.getName();
    private boolean mRegistered;
    private final zzaqc zzdta;
    private boolean zzdyh;

    zzarw(zzaqc com_google_android_gms_internal_zzaqc) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzaqc);
        this.zzdta = com_google_android_gms_internal_zzaqc;
    }

    private final void zzzq() {
        this.zzdta.zzwt();
        this.zzdta.zzwx();
    }

    private final boolean zzzs() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzdta.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (SecurityException e) {
            return false;
        }
    }

    public final boolean isConnected() {
        if (!this.mRegistered) {
            this.zzdta.zzwt().zzdx("Connectivity unknown. Receiver not registered");
        }
        return this.zzdyh;
    }

    public void onReceive(Context context, Intent intent) {
        zzzq();
        String action = intent.getAction();
        this.zzdta.zzwt().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzzs = zzzs();
            if (this.zzdyh != zzzs) {
                this.zzdyh = zzzs;
                zzapz zzwx = this.zzdta.zzwx();
                zzwx.zza("Network connectivity status changed", Boolean.valueOf(zzzs));
                zzwx.zzwv().zzc(new zzapt(zzwx, zzzs));
            }
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzdta.zzwt().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else if (!intent.hasExtra(zzdyg)) {
            zzapz zzwx2 = this.zzdta.zzwx();
            zzwx2.zzdu("Radio powered up");
            zzwx2.zzwn();
        }
    }

    public final void unregister() {
        if (this.mRegistered) {
            this.zzdta.zzwt().zzdu("Unregistering connectivity change receiver");
            this.mRegistered = false;
            this.zzdyh = false;
            try {
                this.zzdta.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzdta.zzwt().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public final void zzzp() {
        zzzq();
        if (!this.mRegistered) {
            Context context = this.zzdta.getContext();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(this, intentFilter);
            this.zzdyh = zzzs();
            this.zzdta.zzwt().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzdyh));
            this.mRegistered = true;
        }
    }

    public final void zzzr() {
        Context context = this.zzdta.getContext();
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzdyg, true);
        context.sendOrderedBroadcast(intent, null);
    }
}
