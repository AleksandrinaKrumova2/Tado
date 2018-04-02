package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

final class zzare implements Logger {
    private boolean zzdoz;
    private int zzdvo = 2;

    zzare() {
    }

    public final void error(Exception exception) {
    }

    public final void error(String str) {
    }

    public final int getLogLevel() {
        return this.zzdvo;
    }

    public final void info(String str) {
    }

    public final void setLogLevel(int i) {
        this.zzdvo = i;
        if (!this.zzdoz) {
            String str = (String) zzarl.zzdvy.get();
            Log.i((String) zzarl.zzdvy.get(), new StringBuilder(String.valueOf(str).length() + 91).append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str).append(" DEBUG").toString());
            this.zzdoz = true;
        }
    }

    public final void verbose(String str) {
    }

    public final void warn(String str) {
    }
}
