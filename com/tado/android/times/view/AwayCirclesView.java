package com.tado.android.times.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.tado.C0676R;
import com.tado.android.utils.ResourceFactory;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB+\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J0\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002J0\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'H\u0002J(\u0010(\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u000eH\u0002J(\u0010)\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u000eH\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/tado/android/times/view/AwayCirclesView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "circlePaint", "Landroid/graphics/Paint;", "homeDrawable", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "iconPaint", "value", "level", "getLevel", "()I", "setLevel", "(I)V", "margin", "", "userDrawable", "dispatchDraw", "", "canvas", "Landroid/graphics/Canvas;", "drawCircles", "cx", "cy", "drawDrawable", "drawable", "paint", "left", "", "drawHome", "drawUser", "4.9.3-1500409030_tadoRelease"}, k = 1, mv = {1, 1, 9})
/* compiled from: AwayCirclesView.kt */
public final class AwayCirclesView extends FrameLayout {
    private HashMap _$_findViewCache;
    private Paint circlePaint = new Paint(1);
    private final Bitmap homeDrawable = ResourceFactory.getBitmapFromResource(getContext(), C0676R.drawable.away_home_circle);
    private final Paint iconPaint = new Paint(1);
    private int level = 2;
    private final float margin;
    private final Bitmap userDrawable = ResourceFactory.getBitmapFromResource(getContext(), C0676R.drawable.away_phone);

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

    public AwayCirclesView(@Nullable Context context) {
        super(context);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Resources resources = context2.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        this.margin = resources.getDisplayMetrics().density * 4.0f;
        this.circlePaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.away_circle_base));
        setBackgroundResource(C0676R.drawable.away_map_bg);
    }

    public AwayCirclesView(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Resources resources = context2.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        this.margin = resources.getDisplayMetrics().density * 4.0f;
        this.circlePaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.away_circle_base));
        setBackgroundResource(C0676R.drawable.away_map_bg);
    }

    public AwayCirclesView(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Resources resources = context2.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        this.margin = resources.getDisplayMetrics().density * 4.0f;
        this.circlePaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.away_circle_base));
        setBackgroundResource(C0676R.drawable.away_map_bg);
    }

    public AwayCirclesView(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Resources resources = context2.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        this.margin = resources.getDisplayMetrics().density * 4.0f;
        this.circlePaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.away_circle_base));
        setBackgroundResource(C0676R.drawable.away_map_bg);
    }

    public final int getLevel() {
        return this.level;
    }

    public final void setLevel(int value) {
        this.level = value;
        invalidate();
    }

    protected void dispatchDraw(@Nullable Canvas canvas) {
        if (canvas == null) {
            super.dispatchDraw(canvas);
            return;
        }
        float f = this.margin;
        Bitmap bitmap = this.homeDrawable;
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "homeDrawable");
        Canvas canvas2 = canvas;
        drawCircles(canvas2, this.level, f + (((float) bitmap.getWidth()) / 2.0f), ((float) canvas.getHeight()) / 2.0f, this.circlePaint);
        Bitmap bitmap2 = this.homeDrawable;
        Intrinsics.checkExpressionValueIsNotNull(bitmap2, "homeDrawable");
        drawHome(bitmap2, canvas, this.margin, this.iconPaint);
        bitmap2 = this.userDrawable;
        Intrinsics.checkExpressionValueIsNotNull(bitmap2, "userDrawable");
        drawUser(bitmap2, canvas, this.margin, this.iconPaint);
        super.dispatchDraw(canvas);
    }

    private final void drawCircles(Canvas canvas, int level, float cx, float cy, Paint circlePaint) {
        if (level > 0) {
            for (int i = 3; i >= 1; i--) {
                circlePaint.setAlpha((i * 10) + 75);
                canvas.drawCircle(cx, cy, ((float) (canvas.getWidth() * i)) / (7.0f - ((float) level)), circlePaint);
            }
        }
    }

    private final void drawHome(Bitmap drawable, Canvas canvas, float margin, Paint paint) {
        drawDrawable(drawable, canvas, margin, paint, true);
    }

    private final void drawUser(Bitmap drawable, Canvas canvas, float margin, Paint paint) {
        drawDrawable(drawable, canvas, margin, paint, false);
    }

    private final void drawDrawable(Bitmap drawable, Canvas canvas, float margin, Paint paint, boolean left) {
        float dstWidth = (((float) canvas.getHeight()) - (((float) 2) * margin)) / (((float) drawable.getHeight()) / ((float) drawable.getWidth()));
        Rect src = new Rect(0, 0, drawable.getWidth(), drawable.getHeight());
        RectF dst = new RectF((((float) canvas.getWidth()) - margin) - dstWidth, margin, ((float) canvas.getWidth()) - margin, ((float) canvas.getHeight()) - margin);
        if (left) {
            dst = new RectF(margin, margin, margin + dstWidth, ((float) canvas.getHeight()) - margin);
        }
        canvas.drawBitmap(drawable, src, dst, paint);
    }
}
