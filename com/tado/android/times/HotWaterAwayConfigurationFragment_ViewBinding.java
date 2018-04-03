package com.tado.android.times;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.times.view.CommunicationView;
import com.tado.android.views.progressbar.ProgressBarComponent;

public class HotWaterAwayConfigurationFragment_ViewBinding<T extends HotWaterAwayConfigurationFragment> implements Unbinder {
    protected T target;

    @UiThread
    public HotWaterAwayConfigurationFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mProgressBar = (ProgressBarComponent) Utils.findRequiredViewAsType(source, C0676R.id.progressBar, "field 'mProgressBar'", ProgressBarComponent.class);
        target.communicationView = (CommunicationView) Utils.findRequiredViewAsType(source, C0676R.id.communication_area, "field 'communicationView'", CommunicationView.class);
        target.mAwayTemperatureButton = (LinearLayout) Utils.findRequiredViewAsType(source, C0676R.id.away_configuration_fixed_away_temperature_button, "field 'mAwayTemperatureButton'", LinearLayout.class);
        target.awayTemperatureTextView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.textview_away_temperature_value, "field 'awayTemperatureTextView'", TextView.class);
        target.awayTempDesc = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.away_temperature_description, "field 'awayTempDesc'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mProgressBar = null;
        target.communicationView = null;
        target.mAwayTemperatureButton = null;
        target.awayTemperatureTextView = null;
        target.awayTempDesc = null;
        this.target = null;
    }
}
