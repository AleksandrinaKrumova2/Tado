package com.tado.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tado.C0676R;
import com.tado.android.entities.CurrentState;
import com.tado.android.utils.Constants;

public class TooltipImage extends ImageView {
    private int iconCenterX = 0;

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public TooltipImage(Context context) {
        super(context);
    }

    public TooltipImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TooltipImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint({"NewApi", "DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        float scale = (float) (((double) getResources().getDisplayMetrics().scaledDensity) / 2.0d);
        Paint p = new Paint();
        CurrentState state = CurrentState.generateFromPreferences(getContext().getApplicationContext());
        if (state != null) {
            int gradientStart = 0;
            int gradientEnd = 0;
            if (state.getOperation().equals(Constants.HOME)) {
                gradientStart = ContextCompat.getColor(getContext(), C0676R.color.gradient_orange_start);
                gradientEnd = ContextCompat.getColor(getContext(), C0676R.color.gradient_orange_end);
            } else if (state.getOperation().equals(Constants.AWAY)) {
                gradientStart = ContextCompat.getColor(getContext(), C0676R.color.gradient_green_start);
                gradientEnd = ContextCompat.getColor(getContext(), C0676R.color.gradient_green_end);
            } else if (state.getOperation().equals(Constants.SLEEP)) {
                gradientStart = ContextCompat.getColor(getContext(), C0676R.color.gradient_blue_start);
                gradientEnd = ContextCompat.getColor(getContext(), C0676R.color.gradient_blue_end);
            }
            LinearGradient gradient = new LinearGradient((float) getWidth(), 0.0f, (float) getWidth(), (float) getHeight(), gradientStart, gradientEnd, TileMode.CLAMP);
            p.setColor(-1);
            p.setShadowLayer(3.0f * scale, 1.0f, 0.0f, ContextCompat.getColor(getContext(), C0676R.color.light_black));
            if (VERSION.SDK_INT >= 11) {
                setLayerType(1, p);
            }
            Path pt = new Path();
            pt.addRoundRect(new RectF(5.0f * scale, 15.0f * scale, ((float) getWidth()) - (5.0f * scale), ((float) getHeight()) - (20.0f * scale)), 5.0f * scale, 5.0f * scale, android.graphics.Path.Direction.CW);
            canvas.drawPath(pt, p);
            p.setColor(-1);
            canvas.drawPath(Calculate(new Point(((getWidth() - ((int) (5.0f * scale))) / 2) + this.iconCenterX, getHeight() - ((int) (20.0f * scale))), (int) (15.0f * scale), Direction.SOUTH), p);
            super.onDraw(canvas);
            p.clearShadowLayer();
            p.setShader(gradient);
            p.setColor(-1);
            p.setShadowLayer(3.0f * scale, 1.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            if (VERSION.SDK_INT >= 11) {
                setLayerType(1, p);
            }
            pt = new Path();
            pt.addRoundRect(new RectF(4.0f * scale, Constants.MAX_OFFSET_CELSIUS * scale, ((float) getWidth()) - (4.0f * scale), ((float) getHeight()) - (20.0f * scale)), 5.0f * scale, 5.0f * scale, android.graphics.Path.Direction.CW);
            canvas.drawPath(pt, p);
            p.setColor(-1);
            canvas.drawPath(Calculate(new Point(((getWidth() - ((int) (4.0f * scale))) / 2) + this.iconCenterX, getHeight() - ((int) (20.0f * scale))), (int) (15.0f * scale), Direction.SOUTH), p);
            super.onDraw(canvas);
        }
    }

    private Path Calculate(Point p1, int width, Direction direction) {
        Point p2 = null;
        Point p3 = null;
        if (direction == Direction.NORTH) {
            p2 = new Point(p1.x + width, p1.y);
            p3 = new Point(p1.x + (width / 2), p1.y - width);
        } else if (direction == Direction.SOUTH) {
            p2 = new Point(p1.x + width, p1.y);
            p3 = new Point(p1.x + (width / 2), p1.y + (width / 2));
        } else if (direction == Direction.EAST) {
            p2 = new Point(p1.x, p1.y + width);
            p3 = new Point(p1.x - width, p1.y + (width / 2));
        } else if (direction == Direction.WEST) {
            p2 = new Point(p1.x, p1.y + width);
            p3 = new Point(p1.x + width, p1.y + (width / 2));
        }
        Path path = new Path();
        path.moveTo((float) p1.x, (float) p1.y);
        path.lineTo((float) p2.x, (float) p2.y);
        path.lineTo((float) p3.x, (float) p3.y);
        return path;
    }

    public int getIconCenterX() {
        return this.iconCenterX;
    }

    public void setIconCenterX(int iconCenterX) {
        this.iconCenterX = iconCenterX;
    }
}
