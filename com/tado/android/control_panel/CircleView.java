package com.tado.android.control_panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.utils.Constants;

public class CircleView extends View {
    private static final int START_ANGLE = -90;
    private float mAngle;
    private float mDegreesToRad;
    private float mPadding;
    private Paint mPaint;
    private float mRadius;
    private RectF mRect;
    private int mStrokeWidth;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initDimensions();
    }

    private void initDimensions() {
        this.mStrokeWidth = 16;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeWidth((float) this.mStrokeWidth);
        this.mPaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.control_panel_line_color));
        this.mPadding = 20.0f;
        this.mAngle = 0.0f;
        this.mRadius = ((((float) getWidth()) / 2.0f) - ((float) this.mStrokeWidth)) - this.mPadding;
        this.mRect = new RectF(((float) this.mStrokeWidth) + this.mPadding, ((float) this.mStrokeWidth) + this.mPadding, ((float) (getWidth() - this.mStrokeWidth)) - this.mPadding, ((float) (getHeight() - this.mStrokeWidth)) - this.mPadding);
        this.mDegreesToRad = 0.017453292f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.control_panel_line_color));
        this.mPaint.setStyle(Style.STROKE);
        canvas.drawArc(this.mRect, 0.0f, 360.0f, false, this.mPaint);
        this.mPaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.timer_text_color));
        canvas.drawArc(this.mRect, -90.0f, -this.mAngle, false, this.mPaint);
        this.mPaint.setStyle(Style.FILL_AND_STROKE);
        canvas.drawCircle(getCurrentIndicatorX(), getCurrentIndicatorY(), Constants.MAX_OFFSET_CELSIUS, this.mPaint);
    }

    public float getCurrentIndicatorX() {
        return (float) (((double) this.mRect.centerX()) + (((double) this.mRadius) * Math.cos((double) ((-(this.mAngle - -90.0f)) * this.mDegreesToRad))));
    }

    public float getCurrentIndicatorY() {
        return (float) (((double) this.mRect.centerY()) + (((double) this.mRadius) * Math.sin((double) ((-(this.mAngle - -90.0f)) * this.mDegreesToRad))));
    }

    public float getAngle() {
        return this.mAngle;
    }

    public void setAngle(float angle) {
        this.mAngle = angle;
    }
}
