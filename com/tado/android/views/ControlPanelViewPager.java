package com.tado.android.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class ControlPanelViewPager extends ViewPager {
    private int height = 0;

    public ControlPanelViewPager(Context context) {
        super(context);
    }

    public ControlPanelViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            int h = child.getMeasuredHeight();
            if (h > this.height) {
                this.height = h;
            }
        }
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(this.height, 1073741824));
    }
}
