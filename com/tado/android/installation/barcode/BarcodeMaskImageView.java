package com.tado.android.installation.barcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tado.C0676R;
import com.tado.android.utils.Snitcher;

public class BarcodeMaskImageView extends ImageView {
    private static final String TAG = "BarcodeMask";
    private Paint backgroundPaint;
    private float heigthScaleFactor;
    private int mPreviewHeight;
    private int mPreviewWidth;
    private Paint maskPaint;
    private Rect[] overlay;
    private Paint overlayPaint;
    private float radius;
    private RectF rect;
    private Paint strokePaint;
    private float widthScaleFactor;

    public BarcodeMaskImageView(Context context) {
        super(context);
        this.rect = new RectF();
        this.overlay = new Rect[0];
        this.widthScaleFactor = 1.0f;
        this.heigthScaleFactor = 1.0f;
        init();
    }

    public BarcodeMaskImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.rect = new RectF();
        this.overlay = new Rect[0];
        this.widthScaleFactor = 1.0f;
        this.heigthScaleFactor = 1.0f;
        init();
    }

    public BarcodeMaskImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.rect = new RectF();
        this.overlay = new Rect[0];
        this.widthScaleFactor = 1.0f;
        this.heigthScaleFactor = 1.0f;
        init();
    }

    public BarcodeMaskImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.rect = new RectF();
        this.overlay = new Rect[0];
        this.widthScaleFactor = 1.0f;
        this.heigthScaleFactor = 1.0f;
        init();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float squareSize = (float) getResources().getDimensionPixelSize(C0676R.dimen.barcode_square_size);
        float horizontalMargin = (((float) w) - squareSize) / 2.0f;
        this.rect.left = horizontalMargin;
        this.rect.right = ((float) w) - horizontalMargin;
        float verticalMargin = (((float) h) - squareSize) / 2.0f;
        this.rect.top = verticalMargin;
        this.rect.bottom = ((float) h) - verticalMargin;
    }

    private void init() {
        setLayerType(2, null);
        this.radius = getResources().getDimension(C0676R.dimen.barcode_corner_radius);
        float strokeSize = getResources().getDimension(C0676R.dimen.barcode_stroke_size);
        float detectedStrokeSize = getResources().getDimension(C0676R.dimen.barcode_detected_stroke_size);
        this.backgroundPaint = new Paint(1);
        this.backgroundPaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.barcode_overlay));
        this.backgroundPaint.setStyle(Style.FILL);
        this.maskPaint = new Paint(1);
        this.maskPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.maskPaint.setStyle(Style.FILL);
        this.strokePaint = new Paint(1);
        this.strokePaint.setStyle(Style.STROKE);
        this.strokePaint.setStrokeWidth(strokeSize);
        this.strokePaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.barcode_frame));
        this.overlayPaint = new Paint(1);
        this.overlayPaint.setStyle(Style.STROKE);
        this.overlayPaint.setStrokeWidth(detectedStrokeSize);
        this.overlayPaint.setColor(ContextCompat.getColor(getContext(), C0676R.color.barcode_detection_overlay));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(this.backgroundPaint);
        canvas.drawRoundRect(this.rect, this.radius, this.radius, this.maskPaint);
        canvas.drawRoundRect(this.rect, this.radius, this.radius, this.strokePaint);
        if (!(this.mPreviewWidth == 0 || this.mPreviewHeight == 0)) {
            this.widthScaleFactor = ((float) canvas.getWidth()) / ((float) this.mPreviewWidth);
            this.heigthScaleFactor = ((float) canvas.getHeight()) / ((float) this.mPreviewHeight);
        }
        for (Rect square : this.overlay) {
            square.left = (int) (((float) square.left) * this.widthScaleFactor);
            square.top = (int) (((float) square.top) * this.heigthScaleFactor);
            square.right = (int) (((float) square.right) * this.widthScaleFactor);
            square.bottom = (int) (((float) square.bottom) * this.heigthScaleFactor);
            canvas.drawRect(square, this.overlayPaint);
        }
    }

    public void setCameraInfo(int previewWidth, int previewHeight) {
        this.mPreviewWidth = previewWidth;
        this.mPreviewHeight = previewHeight;
    }

    public void setDetectedBounds(Rect[] rect) {
        if (rect != null) {
            this.overlay = rect;
        } else {
            this.overlay = new Rect[0];
        }
        invalidate();
    }

    public boolean isCodeInFrameBounds(Rect codeBoundingBox) {
        codeBoundingBox.left = (int) (((float) codeBoundingBox.left) * this.widthScaleFactor);
        codeBoundingBox.top = (int) (((float) codeBoundingBox.top) * this.heigthScaleFactor);
        codeBoundingBox.right = (int) (((float) codeBoundingBox.right) * this.widthScaleFactor);
        codeBoundingBox.bottom = (int) (((float) codeBoundingBox.bottom) * this.heigthScaleFactor);
        Rect rounded = new Rect();
        this.rect.roundOut(rounded);
        Snitcher.start().log(3, TAG, "scan area %s barcode bounds %s", rounded.toString(), codeBoundingBox.toString());
        return rounded.contains(codeBoundingBox);
    }
}
