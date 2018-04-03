package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class TimerView_ViewBinding<T extends TimerView> implements Unbinder {
    protected T target;

    @UiThread
    public TimerView_ViewBinding(T target, View source) {
        this.target = target;
        target.deleteButton = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.control_panel_timer_delete, "field 'deleteButton'", ImageView.class);
        target.keyOneView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_one, "field 'keyOneView'");
        target.keyTwoView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_two, "field 'keyTwoView'");
        target.keyThreeView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_three, "field 'keyThreeView'");
        target.keyFourView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_four, "field 'keyFourView'");
        target.keyFiveView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_five, "field 'keyFiveView'");
        target.keySixView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_six, "field 'keySixView'");
        target.keySevenView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_seven, "field 'keySevenView'");
        target.keyEightView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_eight, "field 'keyEightView'");
        target.keyNineView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_nine, "field 'keyNineView'");
        target.keyZeroView = Utils.findRequiredView(source, C0676R.id.control_panel_timer_key_zero, "field 'keyZeroView'");
        target.timeTextView = (TimeTextView) Utils.findRequiredViewAsType(source, C0676R.id.timerTextView, "field 'timeTextView'", TimeTextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.deleteButton = null;
        target.keyOneView = null;
        target.keyTwoView = null;
        target.keyThreeView = null;
        target.keyFourView = null;
        target.keyFiveView = null;
        target.keySixView = null;
        target.keySevenView = null;
        target.keyEightView = null;
        target.keyNineView = null;
        target.keyZeroView = null;
        target.timeTextView = null;
        this.target = null;
    }
}
