package com.google.android.gms.tagmanager;

import android.util.Log;

public final class zzba implements zzdk {
    private int zzdvo = 5;

    public final void mo2777e(String str) {
        if (this.zzdvo <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    public final void setLogLevel(int i) {
        this.zzdvo = i;
    }

    public final void mo2779v(String str) {
        if (this.zzdvo <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    public final void zzb(String str, Throwable th) {
        if (this.zzdvo <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public final void zzbx(String str) {
        if (this.zzdvo <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    public final void zzc(String str, Throwable th) {
        if (this.zzdvo <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }

    public final void zzct(String str) {
        if (this.zzdvo <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    public final void zzcu(String str) {
        if (this.zzdvo <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }
}
