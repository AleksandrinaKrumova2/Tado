package com.tado.android.times;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.times.view.AwayCirclesView;
import com.tado.android.times.view.CommunicationView;
import com.tado.android.views.progressbar.ProgressBarComponent;

public class HeatingAwayConfigurationFragment_ViewBinding<T extends HeatingAwayConfigurationFragment> implements Unbinder {
    protected T target;

    @UiThread
    public HeatingAwayConfigurationFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mEcoTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_comfort_eco_text_view, "field 'mEcoTextView'", TextView.class);
        target.mBalancedTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_comfort_balanced_text_view, "field 'mBalancedTextView'", TextView.class);
        target.mComfortTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_comfort_comfort_text_view, "field 'mComfortTextView'", TextView.class);
        target.mSeekBar = (SeekBar) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_comfort_settings_slider, "field 'mSeekBar'", SeekBar.class);
        target.mComfortSettingsInfo = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_comfort_settings_info, "field 'mComfortSettingsInfo'", TextView.class);
        target.mProgressBar = (ProgressBarComponent) Utils.findRequiredViewAsType(source, C0676R.id.progressBar, "field 'mProgressBar'", ProgressBarComponent.class);
        target.mAwayTemperatureButton = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_fixed_away_temperature_button, "field 'mAwayTemperatureButton'", LinearLayout.class);
        target.temperatureValueTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.textview_away_temperature_value, "field 'temperatureValueTextView'", TextView.class);
        target.awayCirclesView = (AwayCirclesView) Utils.findRequiredViewAsType(source, C0676R.id.away_circles_view, "field 'awayCirclesView'", AwayCirclesView.class);
        target.communicationView = (CommunicationView) Utils.findRequiredViewAsType(source, C0676R.id.communication_area, "field 'communicationView'", CommunicationView.class);
        target.preheatingLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0676R.id.preheating_layout, "field 'preheatingLayout'", ViewGroup.class);
        target.premiumLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0676R.id.premium_layout, "field 'premiumLayout'", ViewGroup.class);
        target.premiumButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.premium_button, "field 'premiumButton'", Button.class);
        target.awayTempLabel = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_temperature_label, "field 'awayTempLabel'", TextView.class);
        target.awayTempDesc = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_temperature_description, "field 'awayTempDesc'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mEcoTextView = null;
        target.mBalancedTextView = null;
        target.mComfortTextView = null;
        target.mSeekBar = null;
        target.mComfortSettingsInfo = null;
        target.mProgressBar = null;
        target.mAwayTemperatureButton = null;
        target.temperatureValueTextView = null;
        target.awayCirclesView = null;
        target.communicationView = null;
        target.preheatingLayout = null;
        target.premiumLayout = null;
        target.premiumButton = null;
        target.awayTempLabel = null;
        target.awayTempDesc = null;
        this.target = null;
    }
}
