package com.tado.android.installation.srt.view.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class SrtRegisterDeviceFragment_ViewBinding<T extends SrtRegisterDeviceFragment> implements Unbinder {
    protected T target;

    @UiThread
    public SrtRegisterDeviceFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.messageView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_message, "field 'messageView'", TextView.class);
        target.imageView = (ImageView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_image, "field 'imageView'", ImageView.class);
        target.titleView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_title, "field 'titleView'", TextView.class);
        target.serialNumberView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_serial_number_view, "field 'serialNumberView'", TextView.class);
        target.authCodeView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_auth_code_view, "field 'authCodeView'", TextView.class);
        target.errorView = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_error_view, "field 'errorView'", TextView.class);
        target.scrollView = (ScrollView) Utils.findRequiredViewAsType(source, C0676R.id.register_device_layout, "field 'scrollView'", ScrollView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.messageView = null;
        target.imageView = null;
        target.titleView = null;
        target.serialNumberView = null;
        target.authCodeView = null;
        target.errorView = null;
        target.scrollView = null;
        this.target = null;
    }
}
