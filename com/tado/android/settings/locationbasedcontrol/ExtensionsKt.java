package com.tado.android.settings.locationbasedcontrol;

import android.graphics.PointF;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002\u001a\u001a\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"distanceTo", "", "Lcom/google/android/gms/maps/model/LatLng;", "other", "format", "", "kotlin.jvm.PlatformType", "", "digits", "", "minus", "Landroid/graphics/PointF;", "4.9.3-1500409030_tadoRelease"}, k = 2, mv = {1, 1, 9})
/* compiled from: Extensions.kt */
public final class ExtensionsKt {
    public static final float distanceTo(@NotNull LatLng $receiver, @NotNull LatLng other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        double latDiff = Math.toRadians(other.latitude - $receiver.latitude);
        double lngDiff = Math.toRadians(other.longitude - $receiver.longitude);
        double a = (Math.sin(latDiff / ((double) 2)) * Math.sin(latDiff / ((double) 2))) + (((Math.cos(Math.toRadians($receiver.latitude)) * Math.cos(Math.toRadians(other.latitude))) * Math.sin(lngDiff / ((double) 2))) * Math.sin(lngDiff / ((double) 2)));
        return (float) (((double) 1609) * (3958.75d * (((double) 2) * Math.atan2(Math.sqrt(a), Math.sqrt(((double) 1) - a)))));
    }

    @NotNull
    public static final PointF minus(@NotNull PointF $receiver, @NotNull PointF other) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new PointF($receiver.x - other.x, $receiver.y - other.y);
    }

    public static final String format(double $receiver, int digits) {
        return String.format("%." + digits + 'f', new Object[]{Double.valueOf($receiver)});
    }
}
