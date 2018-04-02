package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;

public final class zzapq extends zzaqa {
    private static boolean zzdsm;
    private Info zzdsn;
    private final zzash zzdso;
    private String zzdsp;
    private boolean zzdsq = false;
    private final Object zzdsr = new Object();

    zzapq(zzaqc com_google_android_gms_internal_zzaqc) {
        super(com_google_android_gms_internal_zzaqc);
        this.zzdso = new zzash(com_google_android_gms_internal_zzaqc.zzws());
    }

    private final boolean zza(Info info, Info info2) {
        Object obj = null;
        CharSequence id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String zzyk = zzxb().zzyk();
        synchronized (this.zzdsr) {
            String valueOf;
            String valueOf2;
            if (!this.zzdsq) {
                this.zzdsp = zzwl();
                this.zzdsq = true;
            } else if (TextUtils.isEmpty(this.zzdsp)) {
                if (info != null) {
                    obj = info.getId();
                }
                if (obj == null) {
                    valueOf = String.valueOf(id);
                    String valueOf3 = String.valueOf(zzyk);
                    boolean zzdt = zzdt(valueOf3.length() != 0 ? valueOf.concat(valueOf3) : new String(valueOf));
                    return zzdt;
                }
                valueOf2 = String.valueOf(obj);
                valueOf = String.valueOf(zzyk);
                this.zzdsp = zzds(valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2));
            }
            valueOf2 = String.valueOf(id);
            valueOf = String.valueOf(zzyk);
            obj = zzds(valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2));
            if (TextUtils.isEmpty(obj)) {
                return false;
            } else if (obj.equals(this.zzdsp)) {
                return true;
            } else {
                if (TextUtils.isEmpty(this.zzdsp)) {
                    valueOf = zzyk;
                } else {
                    zzdu("Resetting the client id because Advertising Id changed.");
                    obj = zzxb().zzyl();
                    zza("New client Id", obj);
                }
                String valueOf4 = String.valueOf(id);
                valueOf3 = String.valueOf(obj);
                zzdt = zzdt(valueOf3.length() != 0 ? valueOf4.concat(valueOf3) : new String(valueOf4));
                return zzdt;
            }
        }
    }

    private static String zzds(String str) {
        if (zzasl.zzek("MD5") == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzasl.zzek("MD5").digest(str.getBytes()))});
    }

    private final boolean zzdt(String str) {
        try {
            String zzds = zzds(str);
            zzdu("Storing hashed adid.");
            FileOutputStream openFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzds.getBytes());
            openFileOutput.close();
            this.zzdsp = zzds;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    private final synchronized Info zzwj() {
        if (this.zzdso.zzu(1000)) {
            this.zzdso.start();
            Info zzwk = zzwk();
            if (zza(this.zzdsn, zzwk)) {
                this.zzdsn = zzwk;
            } else {
                zzdy("Failed to reset client id on adid change. Not using adid");
                this.zzdsn = new Info("", false);
            }
        }
        return this.zzdsn;
    }

    private final Info zzwk() {
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException e) {
            zzdx("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
        } catch (Throwable th) {
            if (!zzdsm) {
                zzdsm = true;
                zzd("Error getting advertiser id", th);
            }
        }
        return info;
    }

    private final String zzwl() {
        Object obj;
        String str = null;
        try {
            FileInputStream openFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                zzdx("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                getContext().deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                zzdu("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    return str2;
                } catch (FileNotFoundException e) {
                    return str2;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    str = str2;
                    IOException iOException2 = iOException;
                    zzd("Error reading Hash file, deleting it", obj);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
            return null;
        } catch (IOException e4) {
            obj = e4;
            zzd("Error reading Hash file, deleting it", obj);
            getContext().deleteFile("gaClientIdData");
            return str;
        }
    }

    protected final void zzvf() {
    }

    public final boolean zzwb() {
        zzxf();
        Info zzwj = zzwj();
        return (zzwj == null || zzwj.isLimitAdTrackingEnabled()) ? false : true;
    }

    public final String zzwi() {
        zzxf();
        Info zzwj = zzwj();
        CharSequence id = zzwj != null ? zzwj.getId() : null;
        return TextUtils.isEmpty(id) ? null : id;
    }
}
