package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzfjq {
    private static Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    protected static final Charset UTF_8 = Charset.forName(HttpRequest.CHARSET_UTF8);
    public static final Object zzpnk = new Object();

    public static boolean equals(double[] dArr, double[] dArr2) {
        return (dArr == null || dArr.length == 0) ? dArr2 == null || dArr2.length == 0 : Arrays.equals(dArr, dArr2);
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        return (fArr == null || fArr.length == 0) ? fArr2 == null || fArr2.length == 0 : Arrays.equals(fArr, fArr2);
    }

    public static boolean equals(int[] iArr, int[] iArr2) {
        return (iArr == null || iArr.length == 0) ? iArr2 == null || iArr2.length == 0 : Arrays.equals(iArr, iArr2);
    }

    public static boolean equals(long[] jArr, long[] jArr2) {
        return (jArr == null || jArr.length == 0) ? jArr2 == null || jArr2.length == 0 : Arrays.equals(jArr, jArr2);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        boolean length = objArr == null ? false : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int i = 0;
        boolean z = false;
        while (true) {
            if (z >= length || objArr[z] != null) {
                int i2 = i;
                while (i2 < length2 && objArr2[i2] == null) {
                    i2++;
                }
                boolean z2 = z >= length;
                boolean z3 = i2 >= length2;
                if (z2 && z3) {
                    return true;
                }
                if (z2 != z3 || !objArr[z].equals(objArr2[i2])) {
                    return false;
                }
                i = i2 + 1;
                z++;
            } else {
                z++;
            }
        }
    }

    public static boolean equals(boolean[] zArr, boolean[] zArr2) {
        return (zArr == null || zArr.length == 0) ? zArr2 == null || zArr2.length == 0 : Arrays.equals(zArr, zArr2);
    }

    public static int hashCode(double[] dArr) {
        return (dArr == null || dArr.length == 0) ? 0 : Arrays.hashCode(dArr);
    }

    public static int hashCode(float[] fArr) {
        return (fArr == null || fArr.length == 0) ? 0 : Arrays.hashCode(fArr);
    }

    public static int hashCode(int[] iArr) {
        return (iArr == null || iArr.length == 0) ? 0 : Arrays.hashCode(iArr);
    }

    public static int hashCode(long[] jArr) {
        return (jArr == null || jArr.length == 0) ? 0 : Arrays.hashCode(jArr);
    }

    public static int hashCode(Object[] objArr) {
        int i = 0;
        int length = objArr == null ? 0 : objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = objArr[i2];
            if (obj != null) {
                i = (i * 31) + obj.hashCode();
            }
        }
        return i;
    }

    public static int hashCode(boolean[] zArr) {
        return (zArr == null || zArr.length == 0) ? 0 : Arrays.hashCode(zArr);
    }

    public static void zza(zzfjm com_google_android_gms_internal_zzfjm, zzfjm com_google_android_gms_internal_zzfjm2) {
        if (com_google_android_gms_internal_zzfjm.zzpnc != null) {
            com_google_android_gms_internal_zzfjm2.zzpnc = (zzfjo) com_google_android_gms_internal_zzfjm.zzpnc.clone();
        }
    }

    public static boolean zza(byte[][] bArr, byte[][] bArr2) {
        boolean length = bArr == null ? false : bArr.length;
        int length2 = bArr2 == null ? 0 : bArr2.length;
        int i = 0;
        boolean z = false;
        while (true) {
            if (z >= length || bArr[z] != null) {
                int i2 = i;
                while (i2 < length2 && bArr2[i2] == null) {
                    i2++;
                }
                boolean z2 = z >= length;
                boolean z3 = i2 >= length2;
                if (z2 && z3) {
                    return true;
                }
                if (z2 != z3 || !Arrays.equals(bArr[z], bArr2[i2])) {
                    return false;
                }
                i = i2 + 1;
                z++;
            } else {
                z++;
            }
        }
    }

    public static int zzd(byte[][] bArr) {
        int i = 0;
        int length = bArr == null ? 0 : bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            byte[] bArr2 = bArr[i2];
            if (bArr2 != null) {
                i = (i * 31) + Arrays.hashCode(bArr2);
            }
        }
        return i;
    }
}
