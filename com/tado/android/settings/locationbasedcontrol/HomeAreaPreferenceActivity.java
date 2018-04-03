package com.tado.android.settings.locationbasedcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.tado.C0676R;
import com.tado.android.rest.callback.presenters.SendingErrorAlertPresenter;
import com.tado.android.rest.service.RestServiceGenerator;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UnitUtils;
import com.tado.android.utils.UserConfig;
import com.tado.android.views.progressbar.ProgressBarComponent;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ?2\u00020\u00012\u00020\u00022\u00020\u0003:\u0003?@AB\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0006J\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\u0006\u0010%\u001a\u00020\u0018J\u0006\u0010&\u001a\u00020'J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020\u0018J\u0016\u0010,\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020\u0018J\u0012\u0010-\u001a\u00020\u001b2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0010\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\nH\u0016J\u0010\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020\u00062\u0006\u00101\u001a\u000202H\u0016J\u0016\u0010<\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010=\u001a\u00020\u001b2\u0006\u0010>\u001a\u00020\u0018H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "Lcom/tado/android/settings/locationbasedcontrol/OnHandlerDragged;", "()V", "animationEnabled", "", "descriptionTextView", "Landroid/widget/TextView;", "map", "Lcom/google/android/gms/maps/GoogleMap;", "mapFragment", "Lcom/google/android/gms/maps/SupportMapFragment;", "overlay", "Lcom/tado/android/settings/locationbasedcontrol/HomeFenceOverlay;", "progressBar", "Lcom/tado/android/views/progressbar/ProgressBarComponent;", "getProgressBar", "()Lcom/tado/android/views/progressbar/ProgressBarComponent;", "setProgressBar", "(Lcom/tado/android/views/progressbar/ProgressBarComponent;)V", "resetButton", "Landroid/support/design/widget/FloatingActionButton;", "resetValue", "", "titleTextView", "adjustCameraToBounds", "", "distanceInMeters", "withAnimation", "adjustMapToPoint", "point", "Landroid/graphics/Point;", "bindData", "userFence", "Lcom/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity$FenceBindHelper;", "fitCircleToMap", "getHomeFence", "getHomeLocation", "Lcom/google/android/gms/maps/model/LatLng;", "getLatLngBounds", "Lcom/google/android/gms/maps/model/LatLngBounds;", "center", "offsetInMeters", "getLatLngOffset", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onHandlerMove", "Landroid/graphics/PointF;", "onHandlerRelease", "onMapReady", "googleMap", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "resetHomeFence", "saveChanges", "distance", "Companion", "FenceBindHelper", "UpdateAwayDistanceModel", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeAreaPreferenceActivity.kt */
public final class HomeAreaPreferenceActivity extends AppCompatActivity implements OnMapReadyCallback, OnHandlerDragged {
    public static final Companion Companion = new Companion();
    private static final int EARTH_RADIUS_IN_METERS = EARTH_RADIUS_IN_METERS;
    private HashMap _$_findViewCache;
    private final boolean animationEnabled = true;
    private TextView descriptionTextView;
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private HomeFenceOverlay overlay;
    @NotNull
    public ProgressBarComponent progressBar;
    private FloatingActionButton resetButton;
    private final float resetValue = getHomeFence();
    private TextView titleTextView;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity$Companion;", "", "()V", "EARTH_RADIUS_IN_METERS", "", "getEARTH_RADIUS_IN_METERS", "()I", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeAreaPreferenceActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public final int getEARTH_RADIUS_IN_METERS() {
            return HomeAreaPreferenceActivity.EARTH_RADIUS_IN_METERS;
        }

        @NotNull
        public final Intent newIntent(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, HomeAreaPreferenceActivity.class);
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u001c\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity$FenceBindHelper;", "", "radiusMeters", "", "unit", "Lcom/tado/android/utils/UnitUtils;", "(FLcom/tado/android/utils/UnitUtils;)V", "getRadiusMeters", "()F", "radiusString", "", "getRadiusString", "()Ljava/lang/String;", "getUnit", "()Lcom/tado/android/utils/UnitUtils;", "component1", "component2", "copy", "description", "", "resources", "Landroid/content/res/Resources;", "equals", "", "other", "hashCode", "", "title", "toString", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeAreaPreferenceActivity.kt */
    public static final class FenceBindHelper {
        private final float radiusMeters;
        @NotNull
        private final String radiusString;
        @NotNull
        private final UnitUtils unit;

        @NotNull
        public static /* bridge */ /* synthetic */ FenceBindHelper copy$default(FenceBindHelper fenceBindHelper, float f, UnitUtils unitUtils, int i, Object obj) {
            if ((i & 1) != 0) {
                f = fenceBindHelper.radiusMeters;
            }
            if ((i & 2) != 0) {
                unitUtils = fenceBindHelper.unit;
            }
            return fenceBindHelper.copy(f, unitUtils);
        }

        public final float component1() {
            return this.radiusMeters;
        }

        @NotNull
        public final UnitUtils component2() {
            return this.unit;
        }

        @NotNull
        public final FenceBindHelper copy(float radiusMeters, @NotNull UnitUtils unit) {
            Intrinsics.checkParameterIsNotNull(unit, "unit");
            return new FenceBindHelper(radiusMeters, unit);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
            r2 = this;
            if (r2 == r3) goto L_0x001c;
        L_0x0002:
            r0 = r3 instanceof com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.FenceBindHelper;
            if (r0 == 0) goto L_0x001e;
        L_0x0006:
            r3 = (com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.FenceBindHelper) r3;
            r0 = r2.radiusMeters;
            r1 = r3.radiusMeters;
            r0 = java.lang.Float.compare(r0, r1);
            if (r0 != 0) goto L_0x001e;
        L_0x0012:
            r0 = r2.unit;
            r1 = r3.unit;
            r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
            if (r0 == 0) goto L_0x001e;
        L_0x001c:
            r0 = 1;
        L_0x001d:
            return r0;
        L_0x001e:
            r0 = 0;
            goto L_0x001d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.FenceBindHelper.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int floatToIntBits = Float.floatToIntBits(this.radiusMeters) * 31;
            UnitUtils unitUtils = this.unit;
            return (unitUtils != null ? unitUtils.hashCode() : 0) + floatToIntBits;
        }

        public String toString() {
            return "FenceBindHelper(radiusMeters=" + this.radiusMeters + ", unit=" + this.unit + ")";
        }

        public FenceBindHelper(float radiusMeters, @NotNull UnitUtils unit) {
            Intrinsics.checkParameterIsNotNull(unit, "unit");
            this.radiusMeters = radiusMeters;
            this.unit = unit;
            this.radiusString = new UnitUtils(this.radiusMeters).formatDistance();
        }

        public /* synthetic */ FenceBindHelper(float f, UnitUtils unitUtils, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 2) != 0) {
                unitUtils = new UnitUtils(0.0f, 1, null).getDefault();
            }
            this(f, unitUtils);
        }

        public final float getRadiusMeters() {
            return this.radiusMeters;
        }

        @NotNull
        public final UnitUtils getUnit() {
            return this.unit;
        }

        @NotNull
        public final String getRadiusString() {
            return this.radiusString;
        }

        @NotNull
        public final CharSequence title(@NotNull Resources resources) {
            Intrinsics.checkParameterIsNotNull(resources, "resources");
            String titleTemplate = resources.getString(C0676R.string.settings_locationBasedControl_homeArea_infoTitle);
            StringCompanionObject stringCompanionObject;
            Object[] objArr;
            String format;
            if (this.radiusMeters < ((float) this.unit.getRecommendedRange().getFirst())) {
                stringCompanionObject = StringCompanionObject.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(titleTemplate, "titleTemplate");
                objArr = new Object[]{this.radiusString, resources.getText(C0676R.string.settings_locationBasedControl_homeArea_fenceSizes_smallLabel)};
                format = String.format(titleTemplate, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                return format;
            } else if (this.radiusMeters <= ((float) this.unit.getRecommendedRange().getLast())) {
                stringCompanionObject = StringCompanionObject.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(titleTemplate, "titleTemplate");
                objArr = new Object[]{this.radiusString, resources.getText(C0676R.string.settings_locationBasedControl_homeArea_fenceSizes_recommededLabel)};
                format = String.format(titleTemplate, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                return format;
            } else {
                stringCompanionObject = StringCompanionObject.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(titleTemplate, "titleTemplate");
                objArr = new Object[]{this.radiusString, resources.getText(C0676R.string.settings_locationBasedControl_homeArea_fenceSizes_largeLabel)};
                format = String.format(titleTemplate, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                return format;
            }
        }

        @NotNull
        public final CharSequence description(@NotNull Resources resources) {
            Intrinsics.checkParameterIsNotNull(resources, "resources");
            CharSequence text;
            if (this.radiusMeters < ((float) this.unit.getRecommendedRange().getFirst())) {
                text = resources.getText(C0676R.string.settings_locationBasedControl_homeArea_fenceSizes_smallDescription);
                Intrinsics.checkExpressionValueIsNotNull(text, "resources.getText(R.stri…ceSizes_smallDescription)");
                return text;
            } else if (this.radiusMeters > ((float) this.unit.getRecommendedRange().getLast())) {
                text = resources.getText(C0676R.string.settings_locationBasedControl_homeArea_fenceSizes_largeDescription);
                Intrinsics.checkExpressionValueIsNotNull(text, "resources.getText(R.stri…ceSizes_largeDescription)");
                return text;
            } else {
                text = resources.getText(C0676R.string.empty);
                Intrinsics.checkExpressionValueIsNotNull(text, "resources.getText(R.string.empty)");
                return text;
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/HomeAreaPreferenceActivity$UpdateAwayDistanceModel;", "", "awayRadiusInMeters", "", "(F)V", "getAwayRadiusInMeters", "()F", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
    /* compiled from: HomeAreaPreferenceActivity.kt */
    public static final class UpdateAwayDistanceModel {
        private final float awayRadiusInMeters;

        public UpdateAwayDistanceModel() {
            this(0.0f, 1, null);
        }

        @NotNull
        public static /* bridge */ /* synthetic */ UpdateAwayDistanceModel copy$default(UpdateAwayDistanceModel updateAwayDistanceModel, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = updateAwayDistanceModel.awayRadiusInMeters;
            }
            return updateAwayDistanceModel.copy(f);
        }

        public final float component1() {
            return this.awayRadiusInMeters;
        }

        @NotNull
        public final UpdateAwayDistanceModel copy(float awayRadiusInMeters) {
            return new UpdateAwayDistanceModel(awayRadiusInMeters);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
            r2 = this;
            if (r2 == r3) goto L_0x0012;
        L_0x0002:
            r0 = r3 instanceof com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.UpdateAwayDistanceModel;
            if (r0 == 0) goto L_0x0014;
        L_0x0006:
            r3 = (com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.UpdateAwayDistanceModel) r3;
            r0 = r2.awayRadiusInMeters;
            r1 = r3.awayRadiusInMeters;
            r0 = java.lang.Float.compare(r0, r1);
            if (r0 != 0) goto L_0x0014;
        L_0x0012:
            r0 = 1;
        L_0x0013:
            return r0;
        L_0x0014:
            r0 = 0;
            goto L_0x0013;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tado.android.settings.locationbasedcontrol.HomeAreaPreferenceActivity.UpdateAwayDistanceModel.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            return Float.floatToIntBits(this.awayRadiusInMeters);
        }

        public String toString() {
            return "UpdateAwayDistanceModel(awayRadiusInMeters=" + this.awayRadiusInMeters + ")";
        }

        public UpdateAwayDistanceModel(float awayRadiusInMeters) {
            this.awayRadiusInMeters = awayRadiusInMeters;
        }

        public /* synthetic */ UpdateAwayDistanceModel(float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                f = 0.0f;
            }
            this(f);
        }

        public final float getAwayRadiusInMeters() {
            return this.awayRadiusInMeters;
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @NotNull
    public static final /* synthetic */ GoogleMap access$getMap$p(HomeAreaPreferenceActivity $this) {
        GoogleMap googleMap = $this.map;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
        }
        return googleMap;
    }

    @NotNull
    public static final /* synthetic */ HomeFenceOverlay access$getOverlay$p(HomeAreaPreferenceActivity $this) {
        HomeFenceOverlay homeFenceOverlay = $this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        return homeFenceOverlay;
    }

    @NotNull
    public final ProgressBarComponent getProgressBar() {
        ProgressBarComponent progressBarComponent = this.progressBar;
        if (progressBarComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        return progressBarComponent;
    }

    public final void setProgressBar(@NotNull ProgressBarComponent <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.progressBar = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0676R.layout.activity_home_area);
        View findViewById = findViewById(C0676R.id.text_distance_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.text_distance_title)");
        this.titleTextView = (TextView) findViewById;
        findViewById = findViewById(C0676R.id.text_distance_summary);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.text_distance_summary)");
        this.descriptionTextView = (TextView) findViewById;
        findViewById = findViewById(C0676R.id.overlay);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<HomeFenceOverlay>(R.id.overlay)");
        this.overlay = (HomeFenceOverlay) findViewById;
        HomeFenceOverlay homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.setEnabled(false);
        findViewById = findViewById(C0676R.id.button_reset_fence);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<FloatingAct…(R.id.button_reset_fence)");
        this.resetButton = (FloatingActionButton) findViewById;
        FloatingActionButton floatingActionButton = this.resetButton;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resetButton");
        }
        floatingActionButton.setOnClickListener(new HomeAreaPreferenceActivity$onCreate$1(this));
        FloatingActionButton floatingActionButton2 = this.resetButton;
        if (floatingActionButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resetButton");
        }
        floatingActionButton2.bringToFront();
        findViewById = findViewById(C0676R.id.progressBar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<ProgressBar…ponent>(R.id.progressBar)");
        this.progressBar = (ProgressBarComponent) findViewById;
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(C0676R.id.map);
        if (findFragmentById == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        }
        this.mapFragment = (SupportMapFragment) findFragmentById;
        SupportMapFragment supportMapFragment = this.mapFragment;
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) getString(C0676R.string.settings_locationBasedControl_homeArea_title));
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onMapReady(@NotNull GoogleMap googleMap) {
        Intrinsics.checkParameterIsNotNull(googleMap, "googleMap");
        GoogleMap googleMap2 = googleMap;
        UiSettings $receiver = googleMap2.getUiSettings();
        $receiver.setMyLocationButtonEnabled(false);
        $receiver.setZoomGesturesEnabled(false);
        $receiver.setZoomControlsEnabled(false);
        $receiver.setScrollGesturesEnabled(false);
        googleMap2.setMapType(1);
        googleMap2.setOnMapLoadedCallback(new HomeAreaPreferenceActivity$onMapReady$$inlined$apply$lambda$1(this));
        this.map = googleMap;
        HomeFenceOverlay homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.bringToFront();
        homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.setOnHandlerMoveListener(this);
    }

    @NotNull
    public final LatLng getHomeLocation() {
        Location home = UserConfig.getHome();
        if (home == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(home, "UserConfig.getHome()!!");
        double latitude = home.getLatitude();
        home = UserConfig.getHome();
        if (home == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(home, "UserConfig.getHome()!!");
        return new LatLng(latitude, home.getLongitude());
    }

    public final float getHomeFence() {
        return UserConfig.getHomeFence();
    }

    @NotNull
    public final LatLngBounds getLatLngBounds(@NotNull LatLng center, float offsetInMeters) {
        Intrinsics.checkParameterIsNotNull(center, "center");
        return new LatLngBounds(getLatLngOffset(center, -offsetInMeters), getLatLngOffset(center, offsetInMeters));
    }

    @NotNull
    public final LatLng getLatLngOffset(@NotNull LatLng center, float offsetInMeters) {
        Intrinsics.checkParameterIsNotNull(center, "center");
        return new LatLng(center.latitude + (((double) (offsetInMeters / ((float) Companion.getEARTH_RADIUS_IN_METERS()))) * 57.29577951308232d), center.longitude + ((((double) (offsetInMeters / ((float) Companion.getEARTH_RADIUS_IN_METERS()))) * 57.29577951308232d) / Math.cos((MathKt.PI * center.latitude) / ((double) 180))));
    }

    public float onHandlerMove(@NotNull PointF point) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        GoogleMap googleMap = this.map;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
        }
        LatLng coords = googleMap.getProjection().fromScreenLocation(new Point((int) point.x, (int) point.y));
        LatLng homeLocation = getHomeLocation();
        Intrinsics.checkExpressionValueIsNotNull(coords, "coords");
        float distance = Math.abs(ExtensionsKt.distanceTo(homeLocation, coords));
        Snitcher.start().log(3, "onHandlerMove", "distanceMeters from " + point.x + " to " + coords.longitude + " = " + distance, new Object[0]);
        bindData(new FenceBindHelper(distance, null, 2, null));
        return distance;
    }

    public void onHandlerRelease(@NotNull Point point) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        adjustMapToPoint(point);
    }

    public final void bindData(@NotNull FenceBindHelper userFence) {
        Intrinsics.checkParameterIsNotNull(userFence, "userFence");
        TextView textView = this.titleTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
        }
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        textView.setText(userFence.title(resources));
        textView = this.descriptionTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("descriptionTextView");
        }
        resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        textView.setText(userFence.description(resources));
    }

    public final void resetHomeFence(float resetValue, boolean withAnimation) {
        LatLngBounds newBounds = getLatLngBounds(getHomeLocation(), resetValue);
        HomeFenceOverlay homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.adjustFence(newBounds, resetValue);
        adjustCameraToBounds(resetValue, withAnimation);
    }

    public final void adjustMapToPoint(@NotNull Point point) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        GoogleMap googleMap = this.map;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
        }
        LatLng coords = googleMap.getProjection().fromScreenLocation(point);
        LatLng homeLocation = getHomeLocation();
        Intrinsics.checkExpressionValueIsNotNull(coords, "coords");
        adjustCameraToBounds(new UnitUtils(Math.abs(ExtensionsKt.distanceTo(homeLocation, coords))).getDefault().getDistanceWithinRange(), this.animationEnabled);
    }

    public final void adjustCameraToBounds(float distanceInMeters, boolean withAnimation) {
        LatLngBounds newBounds = getLatLngBounds(getHomeLocation(), distanceInMeters);
        GoogleMap googleMap = this.map;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
        }
        googleMap.setOnCameraMoveListener(new HomeAreaPreferenceActivity$adjustCameraToBounds$1(this, distanceInMeters));
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "resources.displayMetrics");
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(newBounds, displayMetrics.widthPixels / 4);
        GoogleMap googleMap2;
        if (withAnimation) {
            googleMap2 = this.map;
            if (googleMap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
            }
            googleMap2.animateCamera(cameraUpdate);
        } else {
            googleMap2 = this.map;
            if (googleMap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
            }
            googleMap2.moveCamera(cameraUpdate);
        }
        fitCircleToMap(distanceInMeters);
    }

    private final void fitCircleToMap(float distanceInMeters) {
        LatLng newHandlerLocation = getLatLngOffset(getHomeLocation(), distanceInMeters);
        GoogleMap googleMap = this.map;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
        }
        Point newX = googleMap.getProjection().toScreenLocation(newHandlerLocation);
        HomeFenceOverlay homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.getHandlerCenter().x = (float) newX.x;
        homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.setDistanceMeters(distanceInMeters);
        homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.invalidate();
        bindData(new FenceBindHelper(distanceInMeters, null, 2, null));
    }

    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        getMenuInflater().inflate(C0676R.menu.menu_action_save, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        MenuItem findItem = menu.findItem(C0676R.id.action_save);
        Intrinsics.checkExpressionValueIsNotNull(findItem, "menu.findItem(R.id.action_save)");
        findItem.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case C0676R.id.action_save:
                HomeFenceOverlay homeFenceOverlay = this.overlay;
                if (homeFenceOverlay == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("overlay");
                }
                if (homeFenceOverlay.getDistanceMeters() != this.resetValue) {
                    homeFenceOverlay = this.overlay;
                    if (homeFenceOverlay == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("overlay");
                    }
                    saveChanges(homeFenceOverlay.getDistanceMeters());
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private final void saveChanges(float distance) {
        HomeFenceOverlay homeFenceOverlay = this.overlay;
        if (homeFenceOverlay == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlay");
        }
        homeFenceOverlay.setEnabled(false);
        ProgressBarComponent progressBarComponent = this.progressBar;
        if (progressBarComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        progressBarComponent.showView();
        RestServiceGenerator.getTadoRestService().putAwayRadius(UserConfig.getHomeId(), new UpdateAwayDistanceModel(distance), RestServiceGenerator.getCredentialsMap()).enqueue(new HomeAreaPreferenceActivity$saveChanges$1(this, distance, new SendingErrorAlertPresenter(this)));
    }
}
