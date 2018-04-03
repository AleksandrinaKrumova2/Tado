package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class TadoZoneSettingsView_ViewBinding<T extends TadoZoneSettingsView> implements Unbinder {
    protected T target;

    @UiThread
    public TadoZoneSettingsView_ViewBinding(T target, View source) {
        this.target = target;
        target.mView = Utils.findRequiredView(source, C0676R.id.tado_zone_settings_layout, "field 'mView'");
        target.mPowerSwitch = (TadoPowerSwitchView) Utils.findRequiredViewAsType(source, C0676R.id.zone_settings_view_power_switch, "field 'mPowerSwitch'", TadoPowerSwitchView.class);
        target.mTadoAcModeButtonsLayout = (TadoAcModeButtonLayout) Utils.findRequiredViewAsType(source, C0676R.id.zone_settings_view_ac_mode_buttons_layout, "field 'mTadoAcModeButtonsLayout'", TadoAcModeButtonLayout.class);
        target.mTadoSwingButton = (TadoSwingButton) Utils.findRequiredViewAsType(source, C0676R.id.zone_settings_view_swing_button, "field 'mTadoSwingButton'", TadoSwingButton.class);
        target.mTadoTemperaturePickerView = (TadoTemperaturePickerView) Utils.findRequiredViewAsType(source, C0676R.id.zone_settings_view_temperature_picker_view, "field 'mTadoTemperaturePickerView'", TadoTemperaturePickerView.class);
        target.mTadoFanSpeedPickerView = (TadoFanSpeedPickerView) Utils.findRequiredViewAsType(source, C0676R.id.zone_settings_view_fan_view, "field 'mTadoFanSpeedPickerView'", TadoFanSpeedPickerView.class);
        target.mFirstLine = Utils.findRequiredView(source, C0676R.id.zone_settings_view_first_line, "field 'mFirstLine'");
        target.mSecondLine = Utils.findRequiredView(source, C0676R.id.zone_settings_view_second_line, "field 'mSecondLine'");
        target.mFrostProtectionLayout = Utils.findRequiredView(source, C0676R.id.zone_settings_view_frost_protection_layout, "field 'mFrostProtectionLayout'");
        target.mSettingsLayout = Utils.findRequiredView(source, C0676R.id.zone_settings_view_main_controls, "field 'mSettingsLayout'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mView = null;
        target.mPowerSwitch = null;
        target.mTadoAcModeButtonsLayout = null;
        target.mTadoSwingButton = null;
        target.mTadoTemperaturePickerView = null;
        target.mTadoFanSpeedPickerView = null;
        target.mFirstLine = null;
        target.mSecondLine = null;
        target.mFrostProtectionLayout = null;
        target.mSettingsLayout = null;
        this.target = null;
    }
}
