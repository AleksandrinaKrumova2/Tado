package com.tado.android.settings.users;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class MobileDevicesViewHolder_ViewBinding<T extends MobileDevicesViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public MobileDevicesViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.mobileDeviceName = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.mobile_device_name, "field 'mobileDeviceName'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mobileDeviceName = null;
        this.target = null;
    }
}
