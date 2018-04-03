package com.tado.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class TemperatureGridView extends GridView {
    public TemperatureGridView(Context context) {
        super(context);
    }

    public TemperatureGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TemperatureGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TemperatureGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;
        if (getLayoutParams().height == -2) {
            heightSpec = MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        } else {
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
