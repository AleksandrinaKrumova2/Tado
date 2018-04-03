package com.tado.android.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class TadoAcModeButtonLayout_ViewBinding<T extends TadoAcModeButtonLayout> implements Unbinder {
    protected T target;

    @UiThread
    public TadoAcModeButtonLayout_ViewBinding(T target, View source) {
        this.target = target;
        target.mFirstButtonLayout = (TadoAcModeButtonView) Utils.findRequiredViewAsType(source, C0676R.id.first_button_layout, "field 'mFirstButtonLayout'", TadoAcModeButtonView.class);
        target.mSecondButtonLayout = (TadoAcModeButtonView) Utils.findRequiredViewAsType(source, C0676R.id.second_button_layout, "field 'mSecondButtonLayout'", TadoAcModeButtonView.class);
        target.mThirdButtonLayout = (TadoAcModeButtonView) Utils.findRequiredViewAsType(source, C0676R.id.third_button_layout, "field 'mThirdButtonLayout'", TadoAcModeButtonView.class);
        target.mFourthButtonLayout = (TadoAcModeButtonView) Utils.findRequiredViewAsType(source, C0676R.id.fourth_button_layout, "field 'mFourthButtonLayout'", TadoAcModeButtonView.class);
        target.mFifthButtonLayout = (TadoAcModeButtonView) Utils.findRequiredViewAsType(source, C0676R.id.fifth_button_layout, "field 'mFifthButtonLayout'", TadoAcModeButtonView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mFirstButtonLayout = null;
        target.mSecondButtonLayout = null;
        target.mThirdButtonLayout = null;
        target.mFourthButtonLayout = null;
        target.mFifthButtonLayout = null;
        this.target = null;
    }
}
