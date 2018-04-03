package com.wdullaer.materialdatetimepicker.time;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

public class Timepoint implements Parcelable, Comparable<Timepoint> {
    public static final Creator<Timepoint> CREATOR = new C13271();
    private int hour;
    private int minute;
    private int second;

    static class C13271 implements Creator<Timepoint> {
        C13271() {
        }

        public Timepoint createFromParcel(Parcel in) {
            return new Timepoint(in);
        }

        public Timepoint[] newArray(int size) {
            return new Timepoint[size];
        }
    }

    public enum TYPE {
        HOUR,
        MINUTE,
        SECOND
    }

    public Timepoint(Timepoint time) {
        this(time.hour, time.minute, time.second);
    }

    public Timepoint(@IntRange(from = 0, to = 23) int hour, @IntRange(from = 0, to = 59) int minute, @IntRange(from = 0, to = 59) int second) {
        this.hour = hour % 24;
        this.minute = minute % 60;
        this.second = second % 60;
    }

    public Timepoint(@IntRange(from = 0, to = 23) int hour, @IntRange(from = 0, to = 59) int minute) {
        this(hour, minute, 0);
    }

    public Timepoint(@IntRange(from = 0, to = 23) int hour) {
        this(hour, 0);
    }

    public Timepoint(Parcel in) {
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.second = in.readInt();
    }

    @IntRange(from = 0, to = 23)
    public int getHour() {
        return this.hour;
    }

    @IntRange(from = 0, to = 59)
    public int getMinute() {
        return this.minute;
    }

    @IntRange(from = 0, to = 59)
    public int getSecond() {
        return this.second;
    }

    public boolean isAM() {
        return this.hour < 12;
    }

    public boolean isPM() {
        return !isAM();
    }

    public void setAM() {
        if (this.hour >= 12) {
            this.hour %= 12;
        }
    }

    public void setPM() {
        if (this.hour < 12) {
            this.hour = (this.hour + 12) % 24;
        }
    }

    public void add(TYPE type, int value) {
        if (type == TYPE.MINUTE) {
            value *= 60;
        }
        if (type == TYPE.HOUR) {
            value *= 3600;
        }
        value += toSeconds();
        switch (type) {
            case SECOND:
                this.second = (value % 3600) % 60;
                break;
            case MINUTE:
                break;
            case HOUR:
                break;
            default:
                return;
        }
        this.minute = (value % 3600) / 60;
        this.hour = (value / 3600) % 24;
    }

    public int get(@NonNull TYPE type) {
        switch (type) {
            case SECOND:
                return getSecond();
            case MINUTE:
                return getMinute();
            default:
                return getHour();
        }
    }

    public int toSeconds() {
        return ((this.hour * 3600) + (this.minute * 60)) + this.second;
    }

    public int hashCode() {
        return toSeconds();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (hashCode() != ((Timepoint) o).hashCode()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@android.support.annotation.Nullable com.wdullaer.materialdatetimepicker.time.Timepoint r6, @android.support.annotation.NonNull com.wdullaer.materialdatetimepicker.time.Timepoint.TYPE r7) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        if (r6 != 0) goto L_0x0005;
    L_0x0004:
        return r2;
    L_0x0005:
        r0 = 1;
        r3 = com.wdullaer.materialdatetimepicker.time.Timepoint.C13282.f424x4c4e919;
        r4 = r7.ordinal();
        r3 = r3[r4];
        switch(r3) {
            case 1: goto L_0x0013;
            case 2: goto L_0x0020;
            case 3: goto L_0x002d;
            default: goto L_0x0011;
        };
    L_0x0011:
        r2 = r0;
        goto L_0x0004;
    L_0x0013:
        if (r0 == 0) goto L_0x003b;
    L_0x0015:
        r3 = r6.getSecond();
        r4 = r5.getSecond();
        if (r3 != r4) goto L_0x003b;
    L_0x001f:
        r0 = r1;
    L_0x0020:
        if (r0 == 0) goto L_0x003d;
    L_0x0022:
        r3 = r6.getMinute();
        r4 = r5.getMinute();
        if (r3 != r4) goto L_0x003d;
    L_0x002c:
        r0 = r1;
    L_0x002d:
        if (r0 == 0) goto L_0x003f;
    L_0x002f:
        r3 = r6.getHour();
        r4 = r5.getHour();
        if (r3 != r4) goto L_0x003f;
    L_0x0039:
        r0 = r1;
    L_0x003a:
        goto L_0x0011;
    L_0x003b:
        r0 = r2;
        goto L_0x0020;
    L_0x003d:
        r0 = r2;
        goto L_0x002d;
    L_0x003f:
        r0 = r2;
        goto L_0x003a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wdullaer.materialdatetimepicker.time.Timepoint.equals(com.wdullaer.materialdatetimepicker.time.Timepoint, com.wdullaer.materialdatetimepicker.time.Timepoint$TYPE):boolean");
    }

    public int compareTo(@NonNull Timepoint t) {
        return hashCode() - t.hashCode();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.hour);
        out.writeInt(this.minute);
        out.writeInt(this.second);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "" + this.hour + "h " + this.minute + "m " + this.second + "s";
    }
}
