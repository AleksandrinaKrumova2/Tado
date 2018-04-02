package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzaqe {
    private final Context mApplicationContext;
    private final Context zzdts;

    public zzaqe(Context context) {
        zzbq.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        zzbq.checkNotNull(applicationContext, "Application context can't be null");
        this.mApplicationContext = applicationContext;
        this.zzdts = applicationContext;
    }

    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public final Context zzxg() {
        return this.zzdts;
    }
}
