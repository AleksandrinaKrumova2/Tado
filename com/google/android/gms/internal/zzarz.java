package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;

public final class zzarz extends zzaqa {
    private SharedPreferences zzdyn;
    private long zzdyo;
    private long zzdyp = -1;
    private final zzasb zzdyq = new zzasb(this, "monitoring", ((Long) zzarl.zzdxl.get()).longValue());

    protected zzarz(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
    }

    public final String zzaaa() {
        zzj.zzve();
        zzxf();
        CharSequence string = this.zzdyn.getString("installation_campaign", null);
        return TextUtils.isEmpty(string) ? null : string;
    }

    public final zzasb zzaab() {
        return this.zzdyq;
    }

    public final void zzef(String str) {
        zzj.zzve();
        zzxf();
        Editor edit = this.zzdyn.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzdx("Failed to commit campaign data");
        }
    }

    protected final void zzvf() {
        this.zzdyn = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public final long zzzw() {
        zzj.zzve();
        zzxf();
        if (this.zzdyo == 0) {
            long j = this.zzdyn.getLong("first_run", 0);
            if (j != 0) {
                this.zzdyo = j;
            } else {
                j = zzws().currentTimeMillis();
                Editor edit = this.zzdyn.edit();
                edit.putLong("first_run", j);
                if (!edit.commit()) {
                    zzdx("Failed to commit first run time");
                }
                this.zzdyo = j;
            }
        }
        return this.zzdyo;
    }

    public final zzash zzzx() {
        return new zzash(zzws(), zzzw());
    }

    public final long zzzy() {
        zzj.zzve();
        zzxf();
        if (this.zzdyp == -1) {
            this.zzdyp = this.zzdyn.getLong("last_dispatch", 0);
        }
        return this.zzdyp;
    }

    public final void zzzz() {
        zzj.zzve();
        zzxf();
        long currentTimeMillis = zzws().currentTimeMillis();
        Editor edit = this.zzdyn.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzdyp = currentTimeMillis;
    }
}
