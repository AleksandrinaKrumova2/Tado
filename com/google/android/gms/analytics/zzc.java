package com.google.android.gms.analytics;

import android.content.BroadcastReceiver.PendingResult;

final class zzc implements Runnable {
    private /* synthetic */ PendingResult zzdop;

    zzc(CampaignTrackingReceiver campaignTrackingReceiver, PendingResult pendingResult) {
        this.zzdop = pendingResult;
    }

    public final void run() {
        if (this.zzdop != null) {
            this.zzdop.finish();
        }
    }
}
