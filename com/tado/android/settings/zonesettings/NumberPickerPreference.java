package com.tado.android.settings.zonesettings;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;
import com.tado.C0676R;

public class NumberPickerPreference extends DialogPreference {
    private static final int MAX_VALUE = 60;
    private static final int MIN_VALUE = 5;
    private int minValue = 5;
    private NumberPicker picker;
    private int value;

    public NumberPickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public NumberPickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberPickerPreference(Context context) {
        super(context, null);
        init();
    }

    private void init() {
        setDialogLayoutResource(C0676R.layout.dialog_number_picker_view);
    }

    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        this.picker = (NumberPicker) view.findViewById(C0676R.id.number_picker_view);
        this.picker.setMinValue(this.minValue);
        this.picker.setMaxValue(60);
        this.picker.setValue(getValue());
    }

    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(defaultValue);
        if (defaultValue instanceof Integer) {
            this.value = ((Integer) defaultValue).intValue();
            if (this.picker != null) {
                this.picker.setValue(this.value);
            }
        }
    }

    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            this.picker.clearFocus();
            int newValue = this.picker.getValue();
            if (callChangeListener(Integer.valueOf(newValue))) {
                setValue(newValue);
            }
        }
    }

    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setValue(restorePersistedValue ? getPersistedInt(5) : ((Integer) defaultValue).intValue());
    }

    public void setValue(int value) {
        this.value = value;
        persistInt(this.value);
    }

    public int getValue() {
        return this.value;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
}
