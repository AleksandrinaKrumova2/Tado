package com.tado.android.times;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.views.TadoZoneSettingsView;
import com.tado.android.views.progressbar.ProgressBarComponent;

public class EditTimesDayActivity_ViewBinding<T extends EditTimesDayActivity> implements Unbinder {
    protected T target;

    @UiThread
    public EditTimesDayActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mStartBlockTime = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_start_value, "field 'mStartBlockTime'", TextView.class);
        target.mStartBlockLabel = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_start, "field 'mStartBlockLabel'", TextView.class);
        target.mEndBlockTime = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_end_value, "field 'mEndBlockTime'", TextView.class);
        target.mEndBlockLabel = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_end, "field 'mEndBlockLabel'", TextView.class);
        target.mAlwaysActiveSwitchView = (SwitchCompat) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_always_active_switch, "field 'mAlwaysActiveSwitchView'", SwitchCompat.class);
        target.mDelete = (Button) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_delete, "field 'mDelete'", Button.class);
        target.startTimeView = Utils.findRequiredView(source, C0676R.id.startTimeView, "field 'startTimeView'");
        target.endTimeView = Utils.findRequiredView(source, C0676R.id.endTimeView, "field 'endTimeView'");
        target.mAlwaysActiveInfo = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.edit_times_day_info, "field 'mAlwaysActiveInfo'", TextView.class);
        target.mAdvancedSettingsLayout = Utils.findRequiredView(source, C0676R.id.advanced_settings_layout, "field 'mAdvancedSettingsLayout'");
        target.mAdvancedSettingsArrowImage = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.advanced_settings_arrow_image, "field 'mAdvancedSettingsArrowImage'", ImageView.class);
        target.mProgressBar = (ProgressBarComponent) Utils.findRequiredViewAsType(source, C0676R.id.progressBar, "field 'mProgressBar'", ProgressBarComponent.class);
        target.mTadoZoneSettingsView = (TadoZoneSettingsView) Utils.findRequiredViewAsType(source, C0676R.id.edit_timeblock_zone_settings_view, "field 'mTadoZoneSettingsView'", TadoZoneSettingsView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mStartBlockTime = null;
        target.mStartBlockLabel = null;
        target.mEndBlockTime = null;
        target.mEndBlockLabel = null;
        target.mAlwaysActiveSwitchView = null;
        target.mDelete = null;
        target.startTimeView = null;
        target.endTimeView = null;
        target.mAlwaysActiveInfo = null;
        target.mAdvancedSettingsLayout = null;
        target.mAdvancedSettingsArrowImage = null;
        target.mProgressBar = null;
        target.mTadoZoneSettingsView = null;
        this.target = null;
    }
}
