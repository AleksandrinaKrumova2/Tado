package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class ChooseAvailableModesActivity_ViewBinding<T extends ChooseAvailableModesActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ChooseAvailableModesActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.coolCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.cool_checkbox, "field 'coolCheckbox'", CheckBox.class);
        target.heatCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.heat_checkbox, "field 'heatCheckbox'", CheckBox.class);
        target.autoCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.auto_checkbox, "field 'autoCheckbox'", CheckBox.class);
        target.fanCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.fan_checkbox, "field 'fanCheckbox'", CheckBox.class);
        target.dryCheckbox = (CheckBox) Utils.findRequiredViewAsType(source, C0676R.id.dry_checkbox, "field 'dryCheckbox'", CheckBox.class);
    }

    public void unbind() {
        ChooseAvailableModesActivity target = (ChooseAvailableModesActivity) this.target;
        super.unbind();
        target.coolCheckbox = null;
        target.heatCheckbox = null;
        target.autoCheckbox = null;
        target.fanCheckbox = null;
        target.dryCheckbox = null;
    }
}
