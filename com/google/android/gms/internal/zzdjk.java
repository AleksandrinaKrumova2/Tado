package com.google.android.gms.internal;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.tagmanager.zzdj;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class zzdjk implements zzdjl {
    private HttpURLConnection zzkry;
    private InputStream zzkrz = null;

    zzdjk() {
    }

    public final void close() {
        HttpURLConnection httpURLConnection = this.zzkry;
        try {
            if (this.zzkrz != null) {
                this.zzkrz.close();
            }
        } catch (Throwable e) {
            Throwable th = e;
            String str = "HttpUrlConnectionNetworkClient: Error when closing http input stream: ";
            String valueOf = String.valueOf(th.getMessage());
            zzdj.zzb(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public final InputStream zzmy(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        this.zzkry = httpURLConnection;
        httpURLConnection = this.zzkry;
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            this.zzkrz = httpURLConnection.getInputStream();
            return this.zzkrz;
        }
        String str2 = "Bad response: " + responseCode;
        if (responseCode == 404) {
            throw new FileNotFoundException(str2);
        } else if (responseCode == 503) {
            throw new zzdjn(str2);
        } else {
            throw new IOException(str2);
        }
    }
}
