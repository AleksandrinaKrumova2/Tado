package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.util.zzn;
import com.google.android.gms.internal.zzbfm;
import com.google.android.gms.internal.zzbfp;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;

public final class MapStyleOptions extends zzbfm {
    public static final Creator<MapStyleOptions> CREATOR = new zzg();
    private static final String TAG = MapStyleOptions.class.getSimpleName();
    private String zzive;

    public MapStyleOptions(String str) {
        this.zzive = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i) throws NotFoundException {
        try {
            return new MapStyleOptions(new String(zzn.zza(context.getResources().openRawResource(i), true), HttpRequest.CHARSET_UTF8));
        } catch (IOException e) {
            String valueOf = String.valueOf(e);
            throw new NotFoundException(new StringBuilder(String.valueOf(valueOf).length() + 37).append("Failed to read resource ").append(i).append(": ").append(valueOf).toString());
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbfp.zze(parcel);
        zzbfp.zza(parcel, 2, this.zzive, false);
        zzbfp.zzai(parcel, zze);
    }
}
