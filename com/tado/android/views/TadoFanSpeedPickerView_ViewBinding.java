package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class TadoFanSpeedPickerView_ViewBinding<T extends TadoFanSpeedPickerView> implements Unbinder {
    protected T target;

    @UiThread
    public TadoFanSpeedPickerView_ViewBinding(T target, View source) {
        this.target = target;
        target.fanDownButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.tado_fan_speed_picker_view_fan_down, "field 'fanDownButton'", ImageButton.class);
        target.fanUpButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.tado_fan_speed_picker_view_fan_up, "field 'fanUpButton'", ImageButton.class);
        target.fanSpeedIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tado_fan_speed_picker_view_fan_icon, "field 'fanSpeedIcon'", ImageView.class);
        target.fanSpeedIconValueImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.tado_fan_speed_picker_view_fan_value_icon, "field 'fanSpeedIconValueImage'", ImageView.class);
        target.fanSpeedValueText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.tado_fan_speed_picker_view_fan_value_text, "field 'fanSpeedValueText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.fanDownButton = null;
        target.fanUpButton = null;
        target.fanSpeedIcon = null;
        target.fanSpeedIconValueImage = null;
        target.fanSpeedValueText = null;
        this.target = null;
    }
}
