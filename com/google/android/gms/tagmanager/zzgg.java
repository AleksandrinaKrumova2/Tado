package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class zzgg {
    private Context mContext;
    private Tracker zzdor;
    private GoogleAnalytics zzdot;

    public zzgg(Context context) {
        this.mContext = context;
    }

    private final synchronized void zzma(String str) {
        if (this.zzdot == null) {
            this.zzdot = GoogleAnalytics.getInstance(this.mContext);
            this.zzdot.setLogger(new zzgh());
            this.zzdor = this.zzdot.newTracker(str);
        }
    }

    public final Tracker zzlz(String str) {
        zzma(str);
        return this.zzdor;
    }
}
