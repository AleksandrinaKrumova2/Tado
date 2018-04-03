package com.tado.android.control_panel;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.views.TimerView;

public class ControlPanelOverlayTimerFragment_ViewBinding<T extends ControlPanelOverlayTimerFragment> implements Unbinder {
    protected T target;

    @UiThread
    public ControlPanelOverlayTimerFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.runningModeLayout = Utils.findRequiredView(source, C0676R.id.control_panel_timer_running_mode, "field 'runningModeLayout'");
        target.editModeLayout = (TimerView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_timer_edit_mode, "field 'editModeLayout'", TimerView.class);
        target.runningModeTimeLeft = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_timer_running_mode_time_left, "field 'runningModeTimeLeft'", TextView.class);
        target.mCircleView = (CircleView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_timer_running_mode_circle, "field 'mCircleView'", CircleView.class);
        target.backButton = (ImageButton) Utils.findRequiredViewAsType(source, C0676R.id.timer_overlay_back_button, "field 'backButton'", ImageButton.class);
        target.resetButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.timer_overlay_reset_button, "field 'resetButton'", Button.class);
        target.mButtonsView = Utils.findRequiredView(source, C0676R.id.control_panel_buttons, "field 'mButtonsView'");
        target.mStartButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.timer_start_button, "field 'mStartButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.runningModeLayout = null;
        target.editModeLayout = null;
        target.runningModeTimeLeft = null;
        target.mCircleView = null;
        target.backButton = null;
        target.resetButton = null;
        target.mButtonsView = null;
        target.mStartButton = null;
        this.target = null;
    }
}
