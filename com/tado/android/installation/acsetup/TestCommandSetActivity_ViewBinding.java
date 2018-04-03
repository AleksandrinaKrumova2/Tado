package com.tado.android.installation.acsetup;

import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.ACInstallationBaseActivity_ViewBinding;

public class TestCommandSetActivity_ViewBinding<T extends TestCommandSetActivity> extends ACInstallationBaseActivity_ViewBinding<T> {
    @UiThread
    public TestCommandSetActivity_ViewBinding(T target, View source) {
        super(target, source);
        target.mRepeatView = Utils.findRequiredView(source, C0676R.id.repeat, "field 'mRepeatView'");
        target.mRepeatExplanationView = Utils.findRequiredView(source, C0676R.id.repeat_explanation, "field 'mRepeatExplanationView'");
        target.mCommandSetName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.test_command_set_name, "field 'mCommandSetName'", TextView.class);
        target.mPowerText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_ac_control_text_view, "field 'mPowerText'", TextView.class);
        target.mPowerSwitch = (Switch) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_power_switch, "field 'mPowerSwitch'", Switch.class);
        target.mFirstButtonLayout = Utils.findRequiredView(source, C0676R.id.control_first_button_layout, "field 'mFirstButtonLayout'");
        target.mFirstButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_first_button, "field 'mFirstButton'", FloatingActionButton.class);
        target.mFirstButtonText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_first_button_text, "field 'mFirstButtonText'", TextView.class);
        target.mSecondButtonLayout = Utils.findRequiredView(source, C0676R.id.control_second_button_layout, "field 'mSecondButtonLayout'");
        target.mSecondButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_second_button, "field 'mSecondButton'", FloatingActionButton.class);
        target.mSecondButtonText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_second_button_text, "field 'mSecondButtonText'", TextView.class);
        target.mThirdButtonLayout = Utils.findRequiredView(source, C0676R.id.control_third_button_layout, "field 'mThirdButtonLayout'");
        target.mThirdButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_third_button, "field 'mThirdButton'", FloatingActionButton.class);
        target.mThirdButtonText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_third_button_text, "field 'mThirdButtonText'", TextView.class);
        target.mFourthButtonLayout = Utils.findRequiredView(source, C0676R.id.control_fourth_button_layout, "field 'mFourthButtonLayout'");
        target.mFourthButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_fourth_button, "field 'mFourthButton'", FloatingActionButton.class);
        target.mFourthButtonText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_fourth_button_text, "field 'mFourthButtonText'", TextView.class);
        target.mFifthButtonLayout = Utils.findRequiredView(source, C0676R.id.control_fifth_button_layout, "field 'mFifthButtonLayout'");
        target.mFifthButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.control_fifth_button, "field 'mFifthButton'", FloatingActionButton.class);
        target.mFifthButtonText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_fifth_button_text, "field 'mFifthButtonText'", TextView.class);
        target.mTemperatureLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_temperature_layout, "field 'mTemperatureLayout'", RelativeLayout.class);
        target.mTemperatureUp = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_temperature_up, "field 'mTemperatureUp'", ImageView.class);
        target.mControlPanelTemperature = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_temperature, "field 'mControlPanelTemperature'", TextView.class);
        target.mTemperatureDown = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_temperature_down, "field 'mTemperatureDown'", ImageView.class);
        target.mControlPanelTemperatureIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_temperature_icon, "field 'mControlPanelTemperatureIcon'", ImageView.class);
        target.mControlPanelFanSpeed = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_fan, "field 'mControlPanelFanSpeed'", ImageView.class);
        target.mControlPanelSwingButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_swing_button, "field 'mControlPanelSwingButton'", Button.class);
        target.mControlPanelFanSpeedIcon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_fan_icon, "field 'mControlPanelFanSpeedIcon'", ImageView.class);
        target.mControlPanelFanText = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_fan_text, "field 'mControlPanelFanText'", TextView.class);
        target.mFanSpeedUp = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_fan_up, "field 'mFanSpeedUp'", ImageView.class);
        target.mFanSpeedDown = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_fan_down, "field 'mFanSpeedDown'", ImageView.class);
        target.mFanLayout = (RelativeLayout) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_fan_layout, "field 'mFanLayout'", RelativeLayout.class);
        target.progressBar = Utils.findRequiredView(source, C0676R.id.progressBar, "field 'progressBar'");
    }

    public void unbind() {
        TestCommandSetActivity target = (TestCommandSetActivity) this.target;
        super.unbind();
        target.mRepeatView = null;
        target.mRepeatExplanationView = null;
        target.mCommandSetName = null;
        target.mPowerText = null;
        target.mPowerSwitch = null;
        target.mFirstButtonLayout = null;
        target.mFirstButton = null;
        target.mFirstButtonText = null;
        target.mSecondButtonLayout = null;
        target.mSecondButton = null;
        target.mSecondButtonText = null;
        target.mThirdButtonLayout = null;
        target.mThirdButton = null;
        target.mThirdButtonText = null;
        target.mFourthButtonLayout = null;
        target.mFourthButton = null;
        target.mFourthButtonText = null;
        target.mFifthButtonLayout = null;
        target.mFifthButton = null;
        target.mFifthButtonText = null;
        target.mTemperatureLayout = null;
        target.mTemperatureUp = null;
        target.mControlPanelTemperature = null;
        target.mTemperatureDown = null;
        target.mControlPanelTemperatureIcon = null;
        target.mControlPanelFanSpeed = null;
        target.mControlPanelSwingButton = null;
        target.mControlPanelFanSpeedIcon = null;
        target.mControlPanelFanText = null;
        target.mFanSpeedUp = null;
        target.mFanSpeedDown = null;
        target.mFanLayout = null;
        target.progressBar = null;
    }
}
