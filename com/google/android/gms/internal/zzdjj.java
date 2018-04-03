package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzdjj {
    private static Integer zzktw = Integer.valueOf(0);
    private static Integer zzktx = Integer.valueOf(1);
    private final Context mContext;
    private final ExecutorService zzieo;

    public zzdjj(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    private zzdjj(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.zzieo = executorService;
    }
}
