package com.tado.android.installation.srt.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class SrtInstallationActivity_ViewBinding<T extends SrtInstallationActivity> implements Unbinder {
    protected T target;

    @UiThread
    public SrtInstallationActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mBackButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.back_button, "field 'mBackButton'", Button.class);
        target.mNextButton = (Button) Utils.findRequiredViewAsType(source, C0676R.id.next_button, "field 'mNextButton'", Button.class);
        target.mNavigationBar = Utils.findRequiredView(source, C0676R.id.bottom_bar, "field 'mNavigationBar'");
        target.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0676R.id.toolbar, "field 'toolbar'", Toolbar.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mBackButton = null;
        target.mNextButton = null;
        target.mNavigationBar = null;
        target.toolbar = null;
        this.target = null;
    }
}
