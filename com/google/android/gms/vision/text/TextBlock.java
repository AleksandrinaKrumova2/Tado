package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdjy;
import com.google.android.gms.internal.zzdke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzdke[] zzkxx;
    private List<Line> zzkxy;
    private String zzkxz;
    private Rect zzkya;

    TextBlock(SparseArray<zzdke> sparseArray) {
        this.zzkxx = new zzdke[sparseArray.size()];
        for (int i = 0; i < this.zzkxx.length; i++) {
            this.zzkxx[i] = (zzdke) sparseArray.valueAt(i);
        }
    }

    private static Point[] zza(int i, int i2, int i3, int i4, zzdjy com_google_android_gms_internal_zzdjy) {
        int i5 = com_google_android_gms_internal_zzdjy.left;
        int i6 = com_google_android_gms_internal_zzdjy.top;
        double sin = Math.sin(Math.toRadians((double) com_google_android_gms_internal_zzdjy.zzkyd));
        double cos = Math.cos(Math.toRadians((double) com_google_android_gms_internal_zzdjy.zzkyd));
        Point[] pointArr = new Point[]{new Point(i, i2), new Point(i3, i2), new Point(i3, i4), new Point(i, i4)};
        for (int i7 = 0; i7 < 4; i7++) {
            int i8 = (int) ((((double) pointArr[i7].x) * sin) + (((double) pointArr[i7].y) * cos));
            pointArr[i7].x = (int) ((((double) pointArr[i7].x) * cos) - (((double) pointArr[i7].y) * sin));
            pointArr[i7].y = i8;
            pointArr[i7].offset(i5, i6);
        }
        return pointArr;
    }

    public Rect getBoundingBox() {
        if (this.zzkya == null) {
            this.zzkya = zzc.zza((Text) this);
        }
        return this.zzkya;
    }

    public List<? extends Text> getComponents() {
        if (this.zzkxx.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzkxy == null) {
            this.zzkxy = new ArrayList(this.zzkxx.length);
            for (zzdke line : this.zzkxx) {
                this.zzkxy.add(new Line(line));
            }
        }
        return this.zzkxy;
    }

    public Point[] getCornerPoints() {
        if (this.cornerPoints == null) {
            if (this.zzkxx.length == 0) {
                this.cornerPoints = new Point[0];
            } else {
                int i = Integer.MAX_VALUE;
                int i2 = Integer.MIN_VALUE;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MIN_VALUE;
                for (zzdke com_google_android_gms_internal_zzdke : this.zzkxx) {
                    zzdjy com_google_android_gms_internal_zzdjy = com_google_android_gms_internal_zzdke.zzkyf;
                    zzdjy com_google_android_gms_internal_zzdjy2 = this.zzkxx[0].zzkyf;
                    int i5 = -com_google_android_gms_internal_zzdjy2.left;
                    int i6 = -com_google_android_gms_internal_zzdjy2.top;
                    double sin = Math.sin(Math.toRadians((double) com_google_android_gms_internal_zzdjy2.zzkyd));
                    double cos = Math.cos(Math.toRadians((double) com_google_android_gms_internal_zzdjy2.zzkyd));
                    Point[] pointArr = new Point[4];
                    pointArr[0] = new Point(com_google_android_gms_internal_zzdjy.left, com_google_android_gms_internal_zzdjy.top);
                    pointArr[0].offset(i5, i6);
                    int i7 = (int) ((((double) pointArr[0].x) * cos) + (((double) pointArr[0].y) * sin));
                    i5 = (int) ((sin * ((double) (-pointArr[0].x))) + (cos * ((double) pointArr[0].y)));
                    pointArr[0].x = i7;
                    pointArr[0].y = i5;
                    pointArr[1] = new Point(com_google_android_gms_internal_zzdjy.width + i7, i5);
                    pointArr[2] = new Point(com_google_android_gms_internal_zzdjy.width + i7, com_google_android_gms_internal_zzdjy.height + i5);
                    pointArr[3] = new Point(i7, com_google_android_gms_internal_zzdjy.height + i5);
                    int i8 = 0;
                    while (i8 < 4) {
                        Point point = pointArr[i8];
                        i7 = Math.min(i, point.x);
                        i = Math.max(i2, point.x);
                        i2 = Math.min(i3, point.y);
                        i8++;
                        i4 = Math.max(i4, point.y);
                        i3 = i2;
                        i2 = i;
                        i = i7;
                    }
                }
                this.cornerPoints = zza(i, i3, i2, i4, this.zzkxx[0].zzkyf);
            }
        }
        return this.cornerPoints;
    }

    public String getLanguage() {
        if (this.zzkxz != null) {
            return this.zzkxz;
        }
        HashMap hashMap = new HashMap();
        for (zzdke com_google_android_gms_internal_zzdke : this.zzkxx) {
            hashMap.put(com_google_android_gms_internal_zzdke.zzkxz, Integer.valueOf((hashMap.containsKey(com_google_android_gms_internal_zzdke.zzkxz) ? ((Integer) hashMap.get(com_google_android_gms_internal_zzdke.zzkxz)).intValue() : 0) + 1));
        }
        this.zzkxz = (String) ((Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        if (this.zzkxz == null || this.zzkxz.isEmpty()) {
            this.zzkxz = "und";
        }
        return this.zzkxz;
    }

    public String getValue() {
        if (this.zzkxx.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(this.zzkxx[0].zzkyi);
        for (int i = 1; i < this.zzkxx.length; i++) {
            stringBuilder.append("\n");
            stringBuilder.append(this.zzkxx[i].zzkyi);
        }
        return stringBuilder.toString();
    }
}
