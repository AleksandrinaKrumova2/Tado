package com.tado.android.installation.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class CongratulationsFragment_ViewBinding<T extends CongratulationsFragment> implements Unbinder {
    protected T target;

    @UiThread
    public CongratulationsFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.doneButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.congratulations_done_button, "field 'doneButton'", Button.class);
        target.inviteButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.congratulations_invite_people_buttons, "field 'inviteButton'", Button.class);
        target.addDevicesButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.congratulations_add_devices_button, "field 'addDevicesButton'", Button.class);
        target.ribbon = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.congratulations_image, "field 'ribbon'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.doneButton = null;
        target.inviteButton = null;
        target.addDevicesButton = null;
        target.ribbon = null;
        this.target = null;
    }
}
