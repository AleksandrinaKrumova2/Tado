package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zze implements zzm {
    private static final Uri zzdpg;
    private final LogPrinter zzdph = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Builder builder = new Builder();
        builder.scheme("uri");
        builder.authority("local");
        zzdpg = builder.build();
    }

    public final void zzb(zzg com_google_android_gms_analytics_zzg) {
        List arrayList = new ArrayList(com_google_android_gms_analytics_zzg.zzut());
        Collections.sort(arrayList, new zzf(this));
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList arrayList2 = (ArrayList) arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            obj = ((zzh) obj).toString();
            if (!TextUtils.isEmpty(obj)) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(obj);
            }
        }
        this.zzdph.println(stringBuilder.toString());
    }

    public final Uri zzup() {
        return zzdpg;
    }
}
