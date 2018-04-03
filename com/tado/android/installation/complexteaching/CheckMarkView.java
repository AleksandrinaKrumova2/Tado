package com.tado.android.installation.complexteaching;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.report.ChartUtils;

public class CheckMarkView extends View {
    private int endX;
    private int endY;
    private int mHeight;
    private Paint mPaint;
    private Path mPath;
    private int mWidth;
    private int middleX;
    private int middleY;
    private int startX;
    private int startY;

    public CheckMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckMarkView(Context context) {
        super(context);
        init();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initDimensions();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void init() {
        this.mPaint = ChartUtils.getPaint("#FFFFFF", 22.0f, Style.STROKE);
        this.mPaint.setStrokeCap(Cap.ROUND);
        this.mPaint.setStrokeJoin(Join.ROUND);
        this.mPath = new Path();
    }

    private void initDimensions() {
        this.mHeight = getMeasuredHeight();
        this.mWidth = getMeasuredWidth();
        this.startX = (int) (((float) this.mWidth) * 0.27f);
        this.startY = (int) (((float) this.mHeight) * 0.54f);
        this.middleX = (int) (((float) this.mWidth) * 0.43f);
        this.middleY = (int) (((float) this.mHeight) * 0.69f);
        this.endX = (int) (((float) this.mWidth) * 0.8f);
        this.endY = (int) (((float) this.mHeight) * 0.33f);
        this.mPath.moveTo((float) this.startX, (float) this.startY);
        this.mPath.lineTo((float) this.middleX, (float) this.middleY);
        this.mPath.lineTo((float) this.endX, (float) this.endY);
        setBackgroundResource(C0676R.drawable.commands_grid_green_background);
    }

    protected void onDraw(Canvas canvas) {
        if (!this.mPath.isEmpty()) {
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }
}
