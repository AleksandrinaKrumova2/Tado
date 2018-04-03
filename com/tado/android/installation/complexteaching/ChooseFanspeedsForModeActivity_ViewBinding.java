package com.tado.android.installation.complexteaching;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class ChooseFanspeedsForModeActivity_ViewBinding<T extends ChooseFanspeedsForModeActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public ChooseFanspeedsForModeActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.mRadioButtonAuto = (RadioButton) Utils.findRequiredViewAsType(source, C0676R.id.radioButtonAuto, "field 'mRadioButtonAuto'", RadioButton.class);
        target.mRadioButton1 = (RadioButton) Utils.findRequiredViewAsType(source, C0676R.id.radioButton1, "field 'mRadioButton1'", RadioButton.class);
        target.mRadioButton2 = (RadioButton) Utils.findRequiredViewAsType(source, C0676R.id.radioButton2, "field 'mRadioButton2'", RadioButton.class);
        target.mRadioButton3 = (RadioButton) Utils.findRequiredViewAsType(source, C0676R.id.radioButton3, "field 'mRadioButton3'", RadioButton.class);
        target.mRadioButtonMore = (RadioButton) Utils.findRequiredViewAsType(source, C0676R.id.radioButtonMore, "field 'mRadioButtonMore'", RadioButton.class);
        target.mRadioButtonGroup = (RadioGroup) Utils.findRequiredViewAsType(source, C0676R.id.fanspeeds_radio_group, "field 'mRadioButtonGroup'", RadioGroup.class);
    }

    public void unbind() {
        ChooseFanspeedsForModeActivity target = (ChooseFanspeedsForModeActivity) this.target;
        super.unbind();
        target.mRadioButtonAuto = null;
        target.mRadioButton1 = null;
        target.mRadioButton2 = null;
        target.mRadioButton3 = null;
        target.mRadioButtonMore = null;
        target.mRadioButtonGroup = null;
    }
}
