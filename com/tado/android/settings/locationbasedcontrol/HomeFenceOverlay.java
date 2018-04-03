package com.tado.android.settings.locationbasedcontrol;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.LatLngBounds;
import com.tado.C0676R;
import com.tado.android.utils.Constants;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.UnitUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0018\u0010N\u001a\u00020O2\b\u0010P\u001a\u0004\u0018\u0001032\u0006\u0010Q\u001a\u00020\u001bJ\u0010\u0010R\u001a\u00020O2\u0006\u0010S\u001a\u00020TH\u0014J\u0018\u0010U\u001a\u00020O2\u0006\u0010V\u001a\u00020\t2\u0006\u0010W\u001a\u00020\tH\u0014J\u0010\u0010X\u001a\u00020#2\u0006\u0010Y\u001a\u00020ZH\u0016R\u0014\u0010\r\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001a\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u001a\u0010\"\u001a\u00020#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0017R\u0011\u0010.\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0017R\u0011\u00100\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001dR\u0010\u00102\u001a\u0004\u0018\u000103X\u000e¢\u0006\u0002\n\u0000R\u0011\u00104\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001dR\u000e\u00106\u001a\u000207X\u000e¢\u0006\u0002\n\u0000R\u001c\u00108\u001a\u0004\u0018\u000109X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020?¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0011\u0010B\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u001dR\u0014\u0010D\u001a\u00020\u001bXD¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u001dR\u0011\u0010F\u001a\u00020G¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0011\u0010J\u001a\u00020K¢\u0006\b\n\u0000\u001a\u0004\bL\u0010M¨\u0006["}, d2 = {"Lcom/tado/android/settings/locationbasedcontrol/HomeFenceOverlay;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "areaAlpha", "getAreaAlpha", "()I", "areaColor", "getAreaColor", "circleColor", "getCircleColor", "circleFillPaint", "Landroid/graphics/Paint;", "getCircleFillPaint", "()Landroid/graphics/Paint;", "circleStrokePaint", "getCircleStrokePaint", "distanceMeters", "", "getDistanceMeters", "()F", "setDistanceMeters", "(F)V", "distanceTextSize", "getDistanceTextSize", "dragging", "", "getDragging", "()Z", "setDragging", "(Z)V", "handlerCenter", "Landroid/graphics/PointF;", "getHandlerCenter", "()Landroid/graphics/PointF;", "handlerFillPaint", "getHandlerFillPaint", "linePaint", "getLinePaint", "lineStrokeWidth", "getLineStrokeWidth", "mapBounds", "Lcom/google/android/gms/maps/model/LatLngBounds;", "margin", "getMargin", "metersPerPixel", "", "onHandlerMoveListener", "Lcom/tado/android/settings/locationbasedcontrol/OnHandlerDragged;", "getOnHandlerMoveListener", "()Lcom/tado/android/settings/locationbasedcontrol/OnHandlerDragged;", "setOnHandlerMoveListener", "(Lcom/tado/android/settings/locationbasedcontrol/OnHandlerDragged;)V", "path", "Landroid/graphics/Path;", "getPath", "()Landroid/graphics/Path;", "radius", "getRadius", "step", "getStep", "textBounds", "Landroid/graphics/Rect;", "getTextBounds", "()Landroid/graphics/Rect;", "textPaint", "Landroid/text/TextPaint;", "getTextPaint", "()Landroid/text/TextPaint;", "adjustFence", "", "bounds", "distance", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: HomeFenceOverlay.kt */
public final class HomeFenceOverlay extends FrameLayout {
    private HashMap _$_findViewCache;
    private final int areaAlpha;
    private final int areaColor;
    private final int circleColor;
    @NotNull
    private final Paint circleFillPaint;
    @NotNull
    private final Paint circleStrokePaint;
    private float distanceMeters;
    private final float distanceTextSize;
    private boolean dragging;
    @NotNull
    private final PointF handlerCenter;
    @NotNull
    private final Paint handlerFillPaint;
    @NotNull
    private final Paint linePaint;
    private final float lineStrokeWidth;
    private LatLngBounds mapBounds;
    private final float margin;
    private double metersPerPixel;
    @Nullable
    private OnHandlerDragged onHandlerMoveListener;
    @NotNull
    private final Path path;
    private final float radius;
    private final float step;
    @NotNull
    private final Rect textBounds;
    @NotNull
    private final TextPaint textPaint;

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

    public HomeFenceOverlay(@Nullable Context context) {
        super(context);
        this.handlerCenter = new PointF();
        this.circleStrokePaint = new Paint(1);
        this.circleFillPaint = new Paint(1);
        this.handlerFillPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path = new Path();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.radius = resources.getDisplayMetrics().density * 8.0f;
        resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.margin = resources.getDisplayMetrics().density * 8.0f;
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.lineStrokeWidth = 2.0f * resources2.getDisplayMetrics().density;
        resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.distanceTextSize = 13.0f * resources2.getDisplayMetrics().density;
        this.areaAlpha = 51;
        this.textBounds = new Rect();
        this.circleColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_handler_and_line);
        this.areaColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_area);
        this.step = Constants.MAX_OFFSET_CELSIUS;
        Paint $receiver = this.circleStrokePaint;
        $receiver.setColor(this.areaColor);
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        $receiver = this.circleFillPaint;
        $receiver.setColor(this.areaColor);
        $receiver.setAlpha(this.areaAlpha);
        $receiver.setStyle(Style.FILL);
        $receiver = this.handlerFillPaint;
        $receiver.setColor(this.circleColor);
        $receiver.setStyle(Style.FILL);
        $receiver = this.linePaint;
        $receiver.setColor(this.circleColor);
        $receiver.setPathEffect(new DashPathEffect(new float[]{20.0f, Constants.MAX_OFFSET_CELSIUS}, 0.0f));
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        TextPaint $receiver2 = this.textPaint;
        $receiver2.setColor(this.circleColor);
        $receiver2.setTextSize(this.distanceTextSize);
        setWillNotDraw(false);
    }

    public HomeFenceOverlay(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.handlerCenter = new PointF();
        this.circleStrokePaint = new Paint(1);
        this.circleFillPaint = new Paint(1);
        this.handlerFillPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path = new Path();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.radius = resources.getDisplayMetrics().density * 8.0f;
        resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.margin = resources.getDisplayMetrics().density * 8.0f;
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.lineStrokeWidth = 2.0f * resources2.getDisplayMetrics().density;
        resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.distanceTextSize = 13.0f * resources2.getDisplayMetrics().density;
        this.areaAlpha = 51;
        this.textBounds = new Rect();
        this.circleColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_handler_and_line);
        this.areaColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_area);
        this.step = Constants.MAX_OFFSET_CELSIUS;
        Paint $receiver = this.circleStrokePaint;
        $receiver.setColor(this.areaColor);
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        $receiver = this.circleFillPaint;
        $receiver.setColor(this.areaColor);
        $receiver.setAlpha(this.areaAlpha);
        $receiver.setStyle(Style.FILL);
        $receiver = this.handlerFillPaint;
        $receiver.setColor(this.circleColor);
        $receiver.setStyle(Style.FILL);
        $receiver = this.linePaint;
        $receiver.setColor(this.circleColor);
        $receiver.setPathEffect(new DashPathEffect(new float[]{20.0f, Constants.MAX_OFFSET_CELSIUS}, 0.0f));
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        TextPaint $receiver2 = this.textPaint;
        $receiver2.setColor(this.circleColor);
        $receiver2.setTextSize(this.distanceTextSize);
        setWillNotDraw(false);
    }

    public HomeFenceOverlay(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.handlerCenter = new PointF();
        this.circleStrokePaint = new Paint(1);
        this.circleFillPaint = new Paint(1);
        this.handlerFillPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path = new Path();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.radius = resources.getDisplayMetrics().density * 8.0f;
        resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.margin = resources.getDisplayMetrics().density * 8.0f;
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.lineStrokeWidth = 2.0f * resources2.getDisplayMetrics().density;
        resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.distanceTextSize = 13.0f * resources2.getDisplayMetrics().density;
        this.areaAlpha = 51;
        this.textBounds = new Rect();
        this.circleColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_handler_and_line);
        this.areaColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_area);
        this.step = Constants.MAX_OFFSET_CELSIUS;
        Paint $receiver = this.circleStrokePaint;
        $receiver.setColor(this.areaColor);
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        $receiver = this.circleFillPaint;
        $receiver.setColor(this.areaColor);
        $receiver.setAlpha(this.areaAlpha);
        $receiver.setStyle(Style.FILL);
        $receiver = this.handlerFillPaint;
        $receiver.setColor(this.circleColor);
        $receiver.setStyle(Style.FILL);
        $receiver = this.linePaint;
        $receiver.setColor(this.circleColor);
        $receiver.setPathEffect(new DashPathEffect(new float[]{20.0f, Constants.MAX_OFFSET_CELSIUS}, 0.0f));
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        TextPaint $receiver2 = this.textPaint;
        $receiver2.setColor(this.circleColor);
        $receiver2.setTextSize(this.distanceTextSize);
        setWillNotDraw(false);
    }

    public HomeFenceOverlay(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.handlerCenter = new PointF();
        this.circleStrokePaint = new Paint(1);
        this.circleFillPaint = new Paint(1);
        this.handlerFillPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.path = new Path();
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.radius = resources.getDisplayMetrics().density * 8.0f;
        resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        this.margin = resources.getDisplayMetrics().density * 8.0f;
        Resources resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.lineStrokeWidth = 2.0f * resources2.getDisplayMetrics().density;
        resources2 = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources2, "resources");
        this.distanceTextSize = 13.0f * resources2.getDisplayMetrics().density;
        this.areaAlpha = 51;
        this.textBounds = new Rect();
        this.circleColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_handler_and_line);
        this.areaColor = ContextCompat.getColor(getContext(), C0676R.color.home_fence_area);
        this.step = Constants.MAX_OFFSET_CELSIUS;
        Paint $receiver = this.circleStrokePaint;
        $receiver.setColor(this.areaColor);
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        $receiver = this.circleFillPaint;
        $receiver.setColor(this.areaColor);
        $receiver.setAlpha(this.areaAlpha);
        $receiver.setStyle(Style.FILL);
        $receiver = this.handlerFillPaint;
        $receiver.setColor(this.circleColor);
        $receiver.setStyle(Style.FILL);
        $receiver = this.linePaint;
        $receiver.setColor(this.circleColor);
        $receiver.setPathEffect(new DashPathEffect(new float[]{20.0f, Constants.MAX_OFFSET_CELSIUS}, 0.0f));
        $receiver.setStrokeWidth(this.lineStrokeWidth);
        $receiver.setStyle(Style.STROKE);
        TextPaint $receiver2 = this.textPaint;
        $receiver2.setColor(this.circleColor);
        $receiver2.setTextSize(this.distanceTextSize);
        setWillNotDraw(false);
    }

    public /* synthetic */ HomeFenceOverlay(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, i, i2);
    }

    @NotNull
    public final PointF getHandlerCenter() {
        return this.handlerCenter;
    }

    @NotNull
    public final Paint getCircleStrokePaint() {
        return this.circleStrokePaint;
    }

    @NotNull
    public final Paint getCircleFillPaint() {
        return this.circleFillPaint;
    }

    @NotNull
    public final Paint getHandlerFillPaint() {
        return this.handlerFillPaint;
    }

    @NotNull
    public final Paint getLinePaint() {
        return this.linePaint;
    }

    @NotNull
    public final TextPaint getTextPaint() {
        return this.textPaint;
    }

    @NotNull
    public final Path getPath() {
        return this.path;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getMargin() {
        return this.margin;
    }

    public final float getLineStrokeWidth() {
        return this.lineStrokeWidth;
    }

    public final float getDistanceTextSize() {
        return this.distanceTextSize;
    }

    public final int getAreaAlpha() {
        return this.areaAlpha;
    }

    @NotNull
    public final Rect getTextBounds() {
        return this.textBounds;
    }

    public final int getCircleColor() {
        return this.circleColor;
    }

    public final int getAreaColor() {
        return this.areaColor;
    }

    public final boolean getDragging() {
        return this.dragging;
    }

    public final void setDragging(boolean <set-?>) {
        this.dragging = <set-?>;
    }

    @Nullable
    public final OnHandlerDragged getOnHandlerMoveListener() {
        return this.onHandlerMoveListener;
    }

    public final void setOnHandlerMoveListener(@Nullable OnHandlerDragged <set-?>) {
        this.onHandlerMoveListener = <set-?>;
    }

    public final float getDistanceMeters() {
        return this.distanceMeters;
    }

    public final void setDistanceMeters(float <set-?>) {
        this.distanceMeters = <set-?>;
    }

    public final float getStep() {
        return this.step;
    }

    public final void adjustFence(@Nullable LatLngBounds bounds, float distance) {
        this.mapBounds = bounds;
        this.distanceMeters = distance;
        if (!(bounds == null || bounds.northeast == null || bounds.southwest == null)) {
            this.metersPerPixel = Math.abs((bounds.southwest.longitude - bounds.northeast.longitude) / ((double) getMeasuredWidth()));
            this.handlerCenter.x = (float) (((double) (((float) getMeasuredWidth()) / 2.0f)) + (((double) distance) * this.metersPerPixel));
            this.handlerCenter.y = ((float) getMeasuredHeight()) / 2.0f;
        }
        invalidate();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.handlerCenter.y == 0.0f) {
            PointF $receiver = this.handlerCenter;
            $receiver.x = ((float) getMeasuredWidth()) / 2.0f;
            $receiver.y = ((float) getMeasuredHeight()) / 2.0f;
        }
    }

    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        String formattedText = new UnitUtils(this.distanceMeters).formatDistance();
        this.textPaint.getTextBounds(formattedText, 0, formattedText.length(), this.textBounds);
        Canvas canvas2 = canvas;
        Path $receiver = this.path;
        $receiver.reset();
        $receiver.moveTo(((float) getMeasuredWidth()) / 2.0f, ((float) getMeasuredHeight()) / 2.0f);
        $receiver.lineTo(this.handlerCenter.x, this.handlerCenter.y);
        canvas2.drawCircle(((float) getMeasuredWidth()) / 2.0f, ((float) getMeasuredHeight()) / 2.0f, Math.abs(this.handlerCenter.x - (((float) getMeasuredWidth()) / 2.0f)), this.circleFillPaint);
        canvas2.drawCircle(((float) getMeasuredWidth()) / 2.0f, ((float) getMeasuredHeight()) / 2.0f, Math.abs(this.handlerCenter.x - (((float) getMeasuredWidth()) / 2.0f)), this.circleStrokePaint);
        canvas2.drawCircle(this.handlerCenter.x, this.handlerCenter.y, this.radius, this.handlerFillPaint);
        canvas2.drawPath(this.path, this.linePaint);
        Snitcher.start().log(5, "onDraw", "circle radiusMeters " + Math.abs(this.handlerCenter.x - (((float) getMeasuredWidth()) / 2.0f)) + " x: " + this.handlerCenter.x + " width: " + getMeasuredWidth(), new Object[0]);
        canvas2.drawText(formattedText, ((((float) getMeasuredWidth()) / 2.0f) + (Math.abs((((float) getMeasuredWidth()) / 2.0f) - this.handlerCenter.x) / ((float) 2))) - (((float) this.textBounds.width()) / 2.0f), (((float) getMeasuredHeight()) / 2.0f) - this.radius, this.textPaint);
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(@NotNull MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        RectF circleBounds = new RectF((this.handlerCenter.x - this.radius) - this.margin, (this.handlerCenter.y - this.radius) - this.margin, (this.handlerCenter.x + this.radius) + this.margin, (this.handlerCenter.y + this.radius) + this.margin);
        Snitcher.start().log(3, "onTouchEvent", "action: %s x: %f y: %f dragging: %b", String.valueOf(event.getAction()), Float.valueOf(event.getX()), Float.valueOf(event.getY()), Boolean.valueOf(this.dragging));
        if (event.getAction() == 0 && !this.dragging && isEnabled()) {
            if (!circleBounds.contains(event.getX(), event.getY())) {
                return true;
            }
            this.dragging = true;
            return true;
        } else if (event.getAction() == 2 && this.dragging && isEnabled()) {
            if (event.getX() < ((float) getMeasuredWidth()) / 2.0f) {
                return true;
            }
            this.handlerCenter.x = event.getX();
            r2 = this.onHandlerMoveListener;
            Float valueOf = r2 != null ? Float.valueOf(r2.onHandlerMove(new PointF(event.getX(), event.getY()))) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            this.distanceMeters = valueOf.floatValue();
            Snitcher.start().log(3, "onTouchEvent", "distanceMeters: " + this.distanceMeters, new Object[0]);
            invalidate();
            return true;
        } else if (event.getAction() != 1 || !this.dragging || !isEnabled()) {
            return super.onTouchEvent(event);
        } else {
            r2 = this.onHandlerMoveListener;
            if (r2 != null) {
                r2.onHandlerRelease(new Point(Math.max((int) event.getX(), getMeasuredWidth() / 2), (int) event.getY()));
            }
            this.dragging = false;
            return true;
        }
    }
}
