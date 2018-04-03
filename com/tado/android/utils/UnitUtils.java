package com.tado.android.utils;

import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.tado.android.settings.locationbasedcontrol.ExtensionsKt;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0006\u0010\t\u001a\u00020\u0000J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0006\u0010\u0012\u001a\u00020\u0013R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/tado/android/utils/UnitUtils;", "", "meters", "", "(F)V", "getMeters", "()F", "formatDistance", "", "getDefault", "getDistance", "getDistanceRange", "Lkotlin/ranges/IntRange;", "getDistanceWithinRange", "getFrom", "locale", "Ljava/util/Locale;", "getRecommendedRange", "isInRange", "", "Imperial", "Metric", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: UnitUtils.kt */
public class UnitUtils {
    private final float meters;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/tado/android/utils/UnitUtils$Imperial;", "Lcom/tado/android/utils/UnitUtils;", "meters", "", "(F)V", "formatDistance", "", "getDistance", "getDistanceRange", "Lkotlin/ranges/IntRange;", "getDistanceWithinRange", "getRecommendedRange", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: UnitUtils.kt */
    public static final class Imperial extends UnitUtils {
        public Imperial(float meters) {
            super(meters);
        }

        @NotNull
        public String formatDistance() {
            if (((double) getMeters()) >= 804.67d) {
                return "" + ExtensionsKt.format(((double) getMeters()) * 6.21371E-4d, 2) + " mi";
            }
            if (((double) getMeters()) >= 274.32d) {
                return "" + (Math.round((((double) getMeters()) * 1.09361d) / ((double) 10)) * ((long) 10)) + " yd";
            }
            return "" + (Math.round((((double) getMeters()) * 3.2808399d) / ((double) 10)) * ((long) 10)) + " ft";
        }

        public float getDistance() {
            return getMeters() * 3.28084f;
        }

        @NotNull
        public IntRange getDistanceRange() {
            return new IntRange(170, 10560);
        }

        @NotNull
        public IntRange getRecommendedRange() {
            return new IntRange(650, 1650);
        }

        public float getDistanceWithinRange() {
            return Math.min((float) getDistanceRange().getLast(), Math.max((float) getDistanceRange().getFirst(), getDistance())) / 3.28084f;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/tado/android/utils/UnitUtils$Metric;", "Lcom/tado/android/utils/UnitUtils;", "meters", "", "(F)V", "formatDistance", "", "getDistance", "getDistanceRange", "Lkotlin/ranges/IntRange;", "getRecommendedRange", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: UnitUtils.kt */
    public static final class Metric extends UnitUtils {
        public Metric(float meters) {
            super(meters);
        }

        @NotNull
        public String formatDistance() {
            return "" + (Math.round(getMeters() / ((float) 10)) * 10) + " m";
        }

        public float getDistance() {
            return getMeters();
        }

        @NotNull
        public IntRange getDistanceRange() {
            return new IntRange(50, PathInterpolatorCompat.MAX_NUM_POINTS);
        }

        @NotNull
        public IntRange getRecommendedRange() {
            return new IntRange(Callback.DEFAULT_DRAG_ANIMATION_DURATION, 500);
        }
    }

    public UnitUtils() {
        this(0.0f, 1, null);
    }

    public UnitUtils(float meters) {
        this.meters = meters;
    }

    public /* synthetic */ UnitUtils(float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        this(f);
    }

    protected final float getMeters() {
        return this.meters;
    }

    @NotNull
    public final UnitUtils getDefault() {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        return getFrom(locale);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ UnitUtils getFrom$default(UnitUtils unitUtils, Locale locale, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFrom");
        }
        if ((i & 1) != 0) {
            locale = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        }
        return unitUtils.getFrom(locale);
    }

    @NotNull
    public final UnitUtils getFrom(@NotNull Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        String countryCode = locale.getCountry();
        if (Intrinsics.areEqual("US", countryCode)) {
            return new Imperial(this.meters);
        }
        if (Intrinsics.areEqual("LR", countryCode)) {
            return new Imperial(this.meters);
        }
        if (Intrinsics.areEqual("MM", countryCode)) {
            return new Imperial(this.meters);
        }
        return new Metric(this.meters);
    }

    public float getDistance() {
        return getDefault().getDistance();
    }

    @NotNull
    public String formatDistance() {
        return getDefault().formatDistance();
    }

    @NotNull
    public IntRange getDistanceRange() {
        return getDefault().getDistanceRange();
    }

    @NotNull
    public IntRange getRecommendedRange() {
        return getDefault().getRecommendedRange();
    }

    public final boolean isInRange() {
        return RangesKt___RangesKt.intRangeContains((ClosedRange) getDefault().getDistanceRange(), getDefault().getDistance());
    }

    public float getDistanceWithinRange() {
        float distance = Math.min((float) getDefault().getDistanceRange().getLast(), Math.max((float) getDefault().getDistanceRange().getFirst(), getDefault().getDistance()));
        Snitcher.start().log(3, "UnitUtils", "" + getDefault().getDistanceRange().getFirst() + " < " + distance + " < " + getDefault().getDistanceRange().getLast(), new Object[0]);
        return distance;
    }
}
