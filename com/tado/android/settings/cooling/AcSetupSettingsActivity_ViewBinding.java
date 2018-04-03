package com.tado.android.settings.cooling;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class AcSetupSettingsActivity_ViewBinding<T extends AcSetupSettingsActivity> implements Unbinder {
    protected T target;
    private View view2131297085;
    private View view2131297088;
    private View view2131297089;
    private View view2131297091;

    @UiThread
    public AcSetupSettingsActivity_ViewBinding(final T target, View source) {
        this.target = target;
        target.mRootView = Utils.findRequiredView(source, C0676R.id.ac_setup_command_set_layout, "field 'mRootView'");
        target.mCommandSetName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_name, "field 'mCommandSetName'", TextView.class);
        target.mTemperatureUnit = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_temperature_unit, "field 'mTemperatureUnit'", TextView.class);
        target.mFirstMode = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_first_mode, "field 'mFirstMode'", TextView.class);
        target.mSecondMode = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_second_mode, "field 'mSecondMode'", TextView.class);
        target.mThirdMode = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_third_mode, "field 'mThirdMode'", TextView.class);
        target.mFourthMode = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_fourth_mode, "field 'mFourthMode'", TextView.class);
        target.mFifthMode = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.ac_setup_command_set_fifth_mode, "field 'mFifthMode'", TextView.class);
        target.switchDriverInfoText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.thermostatic_control_settings_switch_driver_info, "field 'switchDriverInfoText'", TextView.class);
        View view = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_switch_driver_button, "field 'switchDriverButton' and method 'onSwitchDriverClick'");
        target.switchDriverButton = (Button) Utils.castView(view, C0676R.id.thermostatic_control_settings_switch_driver_button, "field 'switchDriverButton'", Button.class);
        this.view2131297089 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSwitchDriverClick(p0);
            }
        });
        target.title = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.thermostatic_control_settings_title, "field 'title'", TextView.class);
        target.info = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.thermostatic_control_settings_info, "field 'info'", TextView.class);
        view = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_reteach_button, "field 'reteachButton' and method 'onReteachClick'");
        target.reteachButton = (Button) Utils.castView(view, C0676R.id.thermostatic_control_settings_reteach_button, "field 'reteachButton'", Button.class);
        this.view2131297088 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onReteachClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_temperature_range, "field 'hysteresisLayout' and method 'onTemperatureClick'");
        target.hysteresisLayout = view;
        this.view2131297091 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onTemperatureClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_on_off_time, "field 'minOnOffLayout' and method 'onOnOffTimeClick'");
        target.minOnOffLayout = view;
        this.view2131297085 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onOnOffTimeClick(p0);
            }
        });
        target.complexDriverLayout = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_complex_driver_layout, "field 'complexDriverLayout'");
        target.complexDriverTitle = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_complex_driver_title, "field 'complexDriverTitle'");
        target.rangeSummary = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.thermostatic_control_settings_temperature_range_summary, "field 'rangeSummary'", TextView.class);
        target.minOnOffTimeSummary = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.thermostatic_control_settings_on_off_time_summary, "field 'minOnOffTimeSummary'", TextView.class);
        target.clcLayoutSeparator = Utils.findRequiredView(source, C0676R.id.thermostatic_control_settings_clc_separator, "field 'clcLayoutSeparator'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mRootView = null;
        target.mCommandSetName = null;
        target.mTemperatureUnit = null;
        target.mFirstMode = null;
        target.mSecondMode = null;
        target.mThirdMode = null;
        target.mFourthMode = null;
        target.mFifthMode = null;
        target.switchDriverInfoText = null;
        target.switchDriverButton = null;
        target.title = null;
        target.info = null;
        target.reteachButton = null;
        target.hysteresisLayout = null;
        target.minOnOffLayout = null;
        target.complexDriverLayout = null;
        target.complexDriverTitle = null;
        target.rangeSummary = null;
        target.minOnOffTimeSummary = null;
        target.clcLayoutSeparator = null;
        this.view2131297089.setOnClickListener(null);
        this.view2131297089 = null;
        this.view2131297088.setOnClickListener(null);
        this.view2131297088 = null;
        this.view2131297091.setOnClickListener(null);
        this.view2131297091 = null;
        this.view2131297085.setOnClickListener(null);
        this.view2131297085 = null;
        this.target = null;
    }
}
