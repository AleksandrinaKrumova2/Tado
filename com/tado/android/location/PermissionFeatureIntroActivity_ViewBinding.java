package com.tado.android.location;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class PermissionFeatureIntroActivity_ViewBinding<T extends PermissionFeatureIntroActivity> implements Unbinder {
    protected T target;

    @UiThread
    public PermissionFeatureIntroActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.featureIntroMainInfo = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.permission_feature_intro_main_text, "field 'featureIntroMainInfo'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.featureIntroMainInfo = null;
        this.target = null;
    }
}
