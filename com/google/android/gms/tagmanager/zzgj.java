package com.google.android.gms.tagmanager;

final class zzgj extends Number implements Comparable<zzgj> {
    private double zzkki;
    private long zzkkj;
    private boolean zzkkk = false;

    private zzgj(double d) {
        this.zzkki = d;
    }

    private zzgj(long j) {
        this.zzkkj = j;
    }

    public static zzgj zza(Double d) {
        return new zzgj(d.doubleValue());
    }

    public static zzgj zzbi(long j) {
        return new zzgj(j);
    }

    public static zzgj zzmb(String str) throws NumberFormatException {
        try {
            return new zzgj(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new zzgj(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(String.valueOf(str).concat(" is not a valid TypedNumber"));
            }
        }
    }

    public final byte byteValue() {
        return (byte) ((int) longValue());
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return zza((zzgj) obj);
    }

    public final double doubleValue() {
        return this.zzkkk ? (double) this.zzkkj : this.zzkki;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgj) && zza((zzgj) obj) == 0;
    }

    public final float floatValue() {
        return (float) doubleValue();
    }

    public final int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public final int intValue() {
        return (int) longValue();
    }

    public final long longValue() {
        return this.zzkkk ? this.zzkkj : (long) this.zzkki;
    }

    public final short shortValue() {
        return (short) ((int) longValue());
    }

    public final String toString() {
        return this.zzkkk ? Long.toString(this.zzkkj) : Double.toString(this.zzkki);
    }

    public final int zza(zzgj com_google_android_gms_tagmanager_zzgj) {
        return (this.zzkkk && com_google_android_gms_tagmanager_zzgj.zzkkk) ? new Long(this.zzkkj).compareTo(Long.valueOf(com_google_android_gms_tagmanager_zzgj.zzkkj)) : Double.compare(doubleValue(), com_google_android_gms_tagmanager_zzgj.doubleValue());
    }

    public final boolean zzbgk() {
        return !this.zzkkk;
    }

    public final boolean zzbgl() {
        return this.zzkkk;
    }
}
