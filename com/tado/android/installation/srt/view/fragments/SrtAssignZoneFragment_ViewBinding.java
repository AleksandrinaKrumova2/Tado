package com.tado.android.installation.srt.view.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class SrtAssignZoneFragment_ViewBinding<T extends SrtAssignZoneFragment> implements Unbinder {
    protected T target;

    @UiThread
    public SrtAssignZoneFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.zonesRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(source, C0676R.id.assign_zone_radio_group, "field 'zonesRadioGroup'", RadioGroup.class);
        target.createNewZoneButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.assign_zone_create_new_zone_button, "field 'createNewZoneButton'", Button.class);
        target.acceptButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.assign_zone_accept_button, "field 'acceptButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.zonesRadioGroup = null;
        target.createNewZoneButton = null;
        target.acceptButton = null;
        this.target = null;
    }
}
