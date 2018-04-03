package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.zzbq;
import java.util.Random;

public final class zzai {
    private final Context mContext;
    private final Random zzbfo;
    private final String zzkdd;

    public zzai(Context context, String str) {
        this(context, str, new Random());
    }

    @VisibleForTesting
    private zzai(Context context, String str, Random random) {
        this.mContext = (Context) zzbq.checkNotNull(context);
        this.zzkdd = (String) zzbq.checkNotNull(str);
        this.zzbfo = random;
    }

    private final SharedPreferences zzbeh() {
        Context context = this.mContext;
        String valueOf = String.valueOf("_gtmContainerRefreshPolicy_");
        String valueOf2 = String.valueOf(this.zzkdd);
        return context.getSharedPreferences(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), 0);
    }

    private final long zzg(long j, long j2) {
        SharedPreferences zzbeh = zzbeh();
        long max = Math.max(0, zzbeh.getLong("FORBIDDEN_COUNT", 0));
        return (long) (((float) (((long) ((((float) max) / ((float) ((Math.max(0, zzbeh.getLong("SUCCESSFUL_COUNT", 0)) + max) + 1))) * ((float) (j2 - j)))) + j)) * this.zzbfo.nextFloat());
    }

    public final long zzbed() {
        return 43200000 + zzg(7200000, 259200000);
    }

    public final long zzbee() {
        return 3600000 + zzg(600000, 86400000);
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzbef() {
        SharedPreferences zzbeh = zzbeh();
        long j = zzbeh.getLong("FORBIDDEN_COUNT", 0);
        long j2 = zzbeh.getLong("SUCCESSFUL_COUNT", 0);
        Editor edit = zzbeh.edit();
        long min = j == 0 ? 3 : Math.min(10, 1 + j);
        j = Math.max(0, Math.min(j2, 10 - min));
        edit.putLong("FORBIDDEN_COUNT", min);
        edit.putLong("SUCCESSFUL_COUNT", j);
        edit.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzbeg() {
        SharedPreferences zzbeh = zzbeh();
        long j = zzbeh.getLong("SUCCESSFUL_COUNT", 0);
        long j2 = zzbeh.getLong("FORBIDDEN_COUNT", 0);
        j = Math.min(10, j + 1);
        j2 = Math.max(0, Math.min(j2, 10 - j));
        Editor edit = zzbeh.edit();
        edit.putLong("SUCCESSFUL_COUNT", j);
        edit.putLong("FORBIDDEN_COUNT", j2);
        edit.apply();
    }
}
