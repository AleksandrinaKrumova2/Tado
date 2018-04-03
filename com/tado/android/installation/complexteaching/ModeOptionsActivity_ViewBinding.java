package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class ModeOptionsActivity_ViewBinding<T extends ModeOptionsActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ModeOptionsActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.temperatureLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.temperature_layout, "field 'temperatureLayout'", LinearLayout.class);
        target.tempCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.temp_checkbox, "field 'tempCheckbox'", CheckBox.class);
        target.fanCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.fan_checkbox, "field 'fanCheckbox'", CheckBox.class);
        target.swingCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.swing_checkbox, "field 'swingCheckbox'", CheckBox.class);
    }

    public void unbind() {
        ModeOptionsActivity target = (ModeOptionsActivity) this.target;
        super.unbind();
        target.temperatureLayout = null;
        target.tempCheckbox = null;
        target.fanCheckbox = null;
        target.swingCheckbox = null;
    }
}
