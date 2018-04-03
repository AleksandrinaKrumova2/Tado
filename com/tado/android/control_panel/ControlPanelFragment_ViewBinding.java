package com.tado.android.control_panel;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.views.TadoZoneSettingsView;

public class ControlPanelFragment_ViewBinding<T extends ControlPanelFragment> implements Unbinder {
    protected T target;

    @UiThread
    public ControlPanelFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mOverlayTerminationView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_main_layout_manual_text, "field 'mOverlayTerminationView'", TextView.class);
        target.mControlPanelButtons = Utils.findRequiredView(source, C0676R.id.control_panel_buttons, "field 'mControlPanelButtons'");
        target.mTerminationView = Utils.findRequiredView(source, C0676R.id.control_panel_main_layout_termination_view, "field 'mTerminationView'");
        target.mTadoZoneSettingsView = (TadoZoneSettingsView) Utils.findRequiredViewAsType(source, C0676R.id.cooling_control_panel_zone_settings_view, "field 'mTadoZoneSettingsView'", TadoZoneSettingsView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mOverlayTerminationView = null;
        target.mControlPanelButtons = null;
        target.mTerminationView = null;
        target.mTadoZoneSettingsView = null;
        this.target = null;
    }
}
