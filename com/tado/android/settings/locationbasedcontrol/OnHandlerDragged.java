package com.tado.android.settings.locationbasedcontrol;

import android.graphics.Point;
import android.graphics.PointF;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/OnHandlerDragged;", "", "onHandlerMove", "", "point", "Landroid/graphics/PointF;", "onHandlerRelease", "", "Landroid/graphics/Point;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: OnHandlerDragged.kt */
public interface OnHandlerDragged {
    float onHandlerMove(@NotNull PointF pointF);

    void onHandlerRelease(@NotNull Point point);
}
