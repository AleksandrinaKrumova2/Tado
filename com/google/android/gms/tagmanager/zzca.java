package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzdvl;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

final class zzca extends Thread implements zzbz {
    private static zzca zzkfz;
    private volatile boolean mClosed = false;
    private final Context mContext;
    private volatile boolean zzcir = false;
    private final LinkedBlockingQueue<Runnable> zzkfy = new LinkedBlockingQueue();
    private volatile zzcc zzkga;

    private zzca(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static zzca zzei(Context context) {
        if (zzkfz == null) {
            zzkfz = new zzca(context);
        }
        return zzkfz;
    }

    public final void run() {
        while (true) {
            boolean z = this.mClosed;
            try {
                Runnable runnable = (Runnable) this.zzkfy.take();
                if (!this.zzcir) {
                    runnable.run();
                }
            } catch (InterruptedException e) {
                zzdj.zzct(e.toString());
            } catch (Throwable th) {
                String str = "Error on Google TagManager Thread: ";
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream);
                zzdvl.zza(th, printStream);
                printStream.flush();
                String valueOf = String.valueOf(new String(byteArrayOutputStream.toByteArray()));
                zzdj.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                zzdj.m10e("Google TagManager is shutting down.");
                this.zzcir = true;
            }
        }
    }

    public final void zzl(Runnable runnable) {
        this.zzkfy.add(runnable);
    }

    public final void zzlq(String str) {
        zzl(new zzcb(this, this, System.currentTimeMillis(), str));
    }
}
