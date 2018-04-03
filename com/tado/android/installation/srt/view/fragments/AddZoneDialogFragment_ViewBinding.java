package com.tado.android.installation.srt.view.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class AddZoneDialogFragment_ViewBinding<T extends AddZoneDialogFragment> implements Unbinder {
    protected T target;

    @UiThread
    public AddZoneDialogFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.zoneNameView = (EditText) Utils.findRequiredViewAsType(source, C0676R.id.dialog_assign_zone_new_name, "field 'zoneNameView'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.zoneNameView = null;
        this.target = null;
    }
}
