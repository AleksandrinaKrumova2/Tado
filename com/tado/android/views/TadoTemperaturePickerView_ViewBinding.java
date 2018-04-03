package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class TadoTemperaturePickerView_ViewBinding<T extends TadoTemperaturePickerView> implements Unbinder {
    protected T target;

    @UiThread
    public TadoTemperaturePickerView_ViewBinding(T target, View source) {
        this.target = target;
        target.mTemperatureTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.tado_temperature_picker_view_temperature_text, "field 'mTemperatureTextView'", TextView.class);
        target.mTemperatureUpButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.tado_temperature_picker_view_up_button, "field 'mTemperatureUpButton'", ImageButton.class);
        target.mTemperatureDownButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.tado_temperature_picker_view_down_button, "field 'mTemperatureDownButton'", ImageButton.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mTemperatureTextView = null;
        target.mTemperatureUpButton = null;
        target.mTemperatureDownButton = null;
        this.target = null;
    }
}
