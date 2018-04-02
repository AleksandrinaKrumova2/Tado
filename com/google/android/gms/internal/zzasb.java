package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;
import java.util.UUID;
import kotlin.jvm.internal.LongCompanionObject;

public final class zzasb {
    private final String mName;
    private final long zzdyr;
    private /* synthetic */ zzarz zzdys;

    private zzasb(zzarz com_google_android_gms_internal_zzarz, String str, long j) {
        this.zzdys = com_google_android_gms_internal_zzarz;
        zzbq.zzgm(str);
        zzbq.checkArgument(j > 0);
        this.mName = str;
        this.zzdyr = j;
    }

    private final void zzaac() {
        long currentTimeMillis = this.zzdys.zzws().currentTimeMillis();
        Editor edit = this.zzdys.zzdyn.edit();
        edit.remove(zzaag());
        edit.remove(zzaah());
        edit.putLong(zzaaf(), currentTimeMillis);
        edit.commit();
    }

    private final long zzaae() {
        return this.zzdys.zzdyn.getLong(zzaaf(), 0);
    }

    private final String zzaaf() {
        return String.valueOf(this.mName).concat(":start");
    }

    private final String zzaag() {
        return String.valueOf(this.mName).concat(":count");
    }

    private final String zzaah() {
        return String.valueOf(this.mName).concat(":value");
    }

    public final Pair<String, Long> zzaad() {
        long zzaae = zzaae();
        zzaae = zzaae == 0 ? 0 : Math.abs(zzaae - this.zzdys.zzws().currentTimeMillis());
        if (zzaae < this.zzdyr) {
            return null;
        }
        if (zzaae > (this.zzdyr << 1)) {
            zzaac();
            return null;
        }
        String string = this.zzdys.zzdyn.getString(zzaah(), null);
        long j = this.zzdys.zzdyn.getLong(zzaag(), 0);
        zzaac();
        return (string == null || j <= 0) ? null : new Pair(string, Long.valueOf(j));
    }

    public final void zzeg(String str) {
        if (zzaae() == 0) {
            zzaac();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzdys.zzdyn.getLong(zzaag(), 0);
            if (j <= 0) {
                Editor edit = this.zzdys.zzdyn.edit();
                edit.putString(zzaah(), str);
                edit.putLong(zzaag(), 1);
                edit.apply();
                return;
            }
            Object obj = (UUID.randomUUID().getLeastSignificantBits() & LongCompanionObject.MAX_VALUE) < LongCompanionObject.MAX_VALUE / (j + 1) ? 1 : null;
            Editor edit2 = this.zzdys.zzdyn.edit();
            if (obj != null) {
                edit2.putString(zzaah(), str);
            }
            edit2.putLong(zzaag(), j + 1);
            edit2.apply();
        }
    }
}
