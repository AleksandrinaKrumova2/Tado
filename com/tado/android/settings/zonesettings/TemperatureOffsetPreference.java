package com.tado.android.settings.zonesettings;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import com.tado.C0676R;
import com.tado.android.views.TadoTemperaturePickerView;
import com.tado.android.views.TadoTemperaturePickerView.OnTemperatureValueChangedListener;

public class TemperatureOffsetPreference extends Preference implements OnTemperatureValueChangedListener {
    private float current;
    private float max;
    private float min;
    private float step;

    public TemperatureOffsetPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public TemperatureOffsetPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TemperatureOffsetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TemperatureOffsetPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        setWidgetLayoutResource(C0676R.layout.preference_temperature_offset);
    }

    public void setMinValue(float min) {
        this.min = min;
    }

    public void setMaxValue(float max) {
        this.max = max;
    }

    public void setCurrentOffset(float current) {
        this.current = current;
    }

    public void setStep(float step) {
        this.step = step;
    }

    protected void onBindView(View view) {
        super.onBindView(view);
        TadoTemperaturePickerView temperaturePickerView = (TadoTemperaturePickerView) view.findViewById(C0676R.id.temp_offset_picker);
        temperaturePickerView.setMinValue(this.min);
        temperaturePickerView.setMaxValue(this.max);
        temperaturePickerView.setStep(this.step);
        temperaturePickerView.setCurrentTemperature(this.current);
        temperaturePickerView.setOnTemperatureValueChangedListener(this);
        temperaturePickerView.setPressAndHoldEnabled(false);
    }

    public void onTemperatureValueChanged(float value) {
        this.current = value;
        getOnPreferenceChangeListener().onPreferenceChange(this, Float.valueOf(this.current));
    }
}
