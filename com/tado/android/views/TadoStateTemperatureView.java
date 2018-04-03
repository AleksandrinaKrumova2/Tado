package com.tado.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tado.C0676R;
import com.tado.android.utils.Constants;
import com.tado.android.utils.TypefaceHelper;
import java.util.Locale;

public class TadoStateTemperatureView extends RelativeLayout {
    private float baseTextSize;
    private float decimalTextSize;
    private float degreeTextSize;
    private float dotTextSize;
    private TextView mStateBaseTemperature;
    private TextView mStateDecimalTemperature;
    private TextView mStateTemperatureDegreeSymbol;
    private TextView mStateTemperatureDot;

    public TadoStateTemperatureView(Context context) {
        super(context);
        init(context, null);
    }

    public TadoStateTemperatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TadoStateTemperatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public TadoStateTemperatureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0676R.layout.tado_state_temperature_layout, this);
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, C0676R.styleable.TadoStateTemperatureView, 0, 0);
            try {
                this.baseTextSize = (float) a.getDimensionPixelSize(0, 5);
                this.decimalTextSize = (float) a.getDimensionPixelSize(1, 5);
                this.degreeTextSize = (float) a.getDimensionPixelSize(2, 5);
                this.dotTextSize = (float) a.getDimensionPixelSize(3, 5);
            } finally {
                a.recycle();
            }
        }
        this.mStateBaseTemperature = (TextView) findViewById(C0676R.id.tado_state_temperature_base);
        this.mStateDecimalTemperature = (TextView) findViewById(C0676R.id.tado_state_temperature_decimal);
        this.mStateTemperatureDot = (TextView) findViewById(C0676R.id.tado_state_temperature_dot);
        this.mStateTemperatureDegreeSymbol = (TextView) findViewById(C0676R.id.tado_state_temperature_degree_symbol);
        this.mStateBaseTemperature.setTextSize(0, this.baseTextSize);
        this.mStateDecimalTemperature.setTextSize(0, this.decimalTextSize);
        this.mStateTemperatureDot.setTextSize(0, this.dotTextSize);
        this.mStateTemperatureDegreeSymbol.setTextSize(0, this.degreeTextSize);
        initFonts(context);
        if (isInEditMode()) {
            setTemperatureAndPrecision(Float.valueOf(23.5f), Float.valueOf(0.1f));
        }
    }

    private void initFonts(Context context) {
        this.mStateBaseTemperature.setTypeface(TypefaceHelper.get(context, "Futura-Medium"));
        this.mStateDecimalTemperature.setTypeface(TypefaceHelper.get(context, "Futura-Medium"));
        this.mStateTemperatureDot.setTypeface(TypefaceHelper.get(context, "Futura-Medium"));
    }

    public void setTemperatureAndPrecision(Float temperature, Float precision) {
        if (temperature != null && precision != null) {
            int i;
            this.mStateTemperatureDot.setVisibility(precision.floatValue() == 1.0f ? 8 : 0);
            TextView textView = this.mStateDecimalTemperature;
            if (precision.floatValue() == 1.0f) {
                i = 4;
            } else {
                i = 0;
            }
            textView.setVisibility(i);
            this.mStateBaseTemperature.setText(String.format(Locale.US, "%d", new Object[]{Integer.valueOf(temperature.intValue())}));
            this.mStateDecimalTemperature.setText(String.format(Locale.US, "%d", new Object[]{Integer.valueOf((int) ((temperature.floatValue() - ((float) temperature.intValue())) * Constants.MAX_OFFSET_CELSIUS))}));
        }
    }
}
