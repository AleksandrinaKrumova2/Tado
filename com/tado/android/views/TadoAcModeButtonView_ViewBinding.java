package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class TadoAcModeButtonView_ViewBinding<T extends TadoAcModeButtonView> implements Unbinder {
    protected T target;

    @UiThread
    public TadoAcModeButtonView_ViewBinding(T target, View source) {
        this.target = target;
        target.mButton = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0676R.id.tado_ac_mode_button, "field 'mButton'", FloatingActionButton.class);
        target.mNameView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.tado_ac_mode_button_text, "field 'mNameView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mButton = null;
        target.mNameView = null;
        this.target = null;
    }
}
