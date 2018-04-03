package com.tado.android.settings.zonesettings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;
import com.tado.android.views.progressbar.ProgressBarComponent;

public class ZonePreferenceActivity_ViewBinding<T extends ZonePreferenceActivity> implements Unbinder {
    protected T target;

    @UiThread
    public ZonePreferenceActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.progressBar = (ProgressBarComponent) Utils.findRequiredViewAsType(source, C0676R.id.progressBar, "field 'progressBar'", ProgressBarComponent.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.progressBar = null;
        this.target = null;
    }
}
