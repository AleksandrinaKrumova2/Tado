package com.tado.android.installation.srt.view.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.installation.srt.view.HvacLayout;

public class SrtHvacInstallationFragment_ViewBinding<T extends SrtHvacInstallationFragment> implements Unbinder {
    protected T target;

    @UiThread
    public SrtHvacInstallationFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.mHvacLayout = (HvacLayout) Utils.findRequiredViewAsType(source, C0676R.id.hvac_layout, "field 'mHvacLayout'", HvacLayout.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mHvacLayout = null;
        this.target = null;
    }
}
