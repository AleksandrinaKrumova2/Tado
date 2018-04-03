package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.zzdjy;

final class zzc {
    static Rect zza(Text text) {
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i4 = Math.min(i4, point.x);
            i3 = Math.max(i3, point.x);
            i = Math.min(i, point.y);
            i2 = Math.max(i2, point.y);
        }
        return new Rect(i4, i, i3, i2);
    }

    static Point[] zza(zzdjy com_google_android_gms_internal_zzdjy) {
        r0 = new Point[4];
        double sin = Math.sin(Math.toRadians((double) com_google_android_gms_internal_zzdjy.zzkyd));
        double cos = Math.cos(Math.toRadians((double) com_google_android_gms_internal_zzdjy.zzkyd));
        r0[0] = new Point(com_google_android_gms_internal_zzdjy.left, com_google_android_gms_internal_zzdjy.top);
        r0[1] = new Point((int) (((double) com_google_android_gms_internal_zzdjy.left) + (((double) com_google_android_gms_internal_zzdjy.width) * cos)), (int) (((double) com_google_android_gms_internal_zzdjy.top) + (((double) com_google_android_gms_internal_zzdjy.width) * sin)));
        r0[2] = new Point((int) (((double) r0[1].x) - (sin * ((double) com_google_android_gms_internal_zzdjy.height))), (int) ((cos * ((double) com_google_android_gms_internal_zzdjy.height)) + ((double) r0[1].y)));
        r0[3] = new Point(r0[0].x + (r0[2].x - r0[1].x), r0[0].y + (r0[2].y - r0[1].y));
        return r0;
    }
}
