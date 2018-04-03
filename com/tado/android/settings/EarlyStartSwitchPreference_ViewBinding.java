package com.tado.android.settings;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class EarlyStartSwitchPreference_ViewBinding<T extends EarlyStartSwitchPreference> implements Unbinder {
    protected T target;

    @UiThread
    public EarlyStartSwitchPreference_ViewBinding(T target, View source) {
        this.target = target;
        target.summaryTextView = (TextView) Utils.findRequiredViewAsType(source, 16908304, "field 'summaryTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.summaryTextView = null;
        this.target = null;
    }
}
